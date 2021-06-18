#!/usr/bin/env python
# -*- coding: utf-8 -*-
import os
import sys
import json
import socket

from PyQt5 import QtWidgets, QtCore
from PyQt5.QtCore import Qt, QPoint
from PyQt5.QtWidgets import QApplication,QWidget,QTextEdit,QVBoxLayout,QPushButton, QLabel, QStyle, QDesktopWidget

from interface import Ui_MainWindow, Ui_MainWindow1
import socket
import time
import spacy
import pandas as pd
# HOST & PORT
    

neg_words = pd.read_csv('data/negative-words.txt', sep="\n", index_col=0)
pos_words =  pd.read_csv('data/positive-words.txt', sep="\n", index_col=0)


nlp = spacy.load('en_core_web_sm')

# BEGIN LOGGERS LIB
from pynput import keyboard
import calendar
import time

import numpy as np

import tkinter as tk
from pandas import DataFrame
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

# END LOGGERS LIB

#Begin Logger Methods
class Loggers():
#detect key press

    def on_press(key):
        try:
            f = open("keyboardL.txt","a")
            print('alphanumeric key {0} pressed'.format(key.char))
            ts = calendar.timegm(time.gmtime())
            print(ts)
            upper = str(key.char).upper()
            f.write(str(ts) + ':' + 'KeyPressed' + ':' + upper + '\n')
            f.close()
        except AttributeError:
            f = open("keyboardL.txt", "a")
            print('special key {0} pressed'.format(key))
            ts = calendar.timegm(time.gmtime())
            upper = str(key).upper()
            f.write(str(ts) + ':' + 'KeyPressed' + ':' + upper + '\n')
            f.close()

#detect key releases
    def on_release(key):
        try:
            f = open("keyboardL.txt","a")
            print('alphanumeric key {0} released'.format(key.char))
            ts = calendar.timegm(time.gmtime())
            print(ts)
            upper = str(key.char).upper()
            f.write(str(ts) + ':' + 'KeyReleased' + ':' + upper + '\n')
            f.close()
        except AttributeError:
            f = open("keyboardL.txt", "a")
            print('special key {0} released'.format(key))
            ts = calendar.timegm(time.gmtime())
            upper = str(key).upper()
            f.write(str(ts) + ':' + 'KeyReleased' + ':' + upper + '\n')
            f.close()
            if key == keyboard.Key.esc:
                #Stop Listener
                print('Stop')
                return False
#End Logger Methos

#Begin Aux
def getDuplicatesWithCount(listOfElems):
    ''' Get frequency count of duplicate elements in the given list '''
    dictOfElems = dict()
    # Iterate over each element in list
    for elem in listOfElems:
        # If element exists in dict then increment its value else add it in dict
        if elem in dictOfElems:
            dictOfElems[elem] += 1
        else:
            dictOfElems[elem] = 1    
 
    # Filter key-value pairs in dictionary. Keep pairs whose value is greater than 1 i.e. only duplicate elements from list.
    dictOfElems = { key:value for key, value in dictOfElems.items() if value > 1}
    # Returns a dict of duplicate elements and thier frequency count

    lx = []
    ly = []

    for key, value in dictOfElems.items():
            #print(key , ' :: ', value)
            lx.append(key)
            ly.append(value/2)

    data2 = {'Key': lx, 'Count': ly}

    return data2

def getTapsWithCount(time,type,key):
    ''' Get frequency count of duplicate elements in the given list '''
    dictOfElems = dict()
    # Iterate over each element in list

    i=0

    for elem in key:
        # If element exists in dict then increment its value else add it in dict

        if type[i] == 'KeyPressed' and ((int(time[i+1])-int(time[i])) < 1):
            val = 'quick_tap'
            if val in dictOfElems:
                dictOfElems[val] += 1
            else:
                dictOfElems[val] = 1
        else:
            val = 'long_press'
            if val in dictOfElems:
                dictOfElems[val] += 1
            else:
                dictOfElems[val] = 1
        
        if (elem=='KEY.BACKSPACE\n'):
            if elem in dictOfElems:
                dictOfElems[elem] += 1
            else:
                dictOfElems[elem] = 1
        
        i=i+1
 
    # Filter key-value pairs in dictionary. Keep pairs whose value is greater than 1 i.e. only duplicate elements from list.
    dictOfElems = { key:value for key, value in dictOfElems.items() if value > 1}
    # Returns a dict of duplicate elements and thier frequency count

    lx = []
    ly = []

    for key, value in dictOfElems.items():
            #print(key , ' :: ', value)
            lx.append(key)
            ly.append(value)

    data3 = {'Type': lx, 'Count': ly}

    return data3
#End Aux

class MainWindow1(QtWidgets.QMainWindow):
    def __init__(self, parent=None):
        super(MainWindow1, self).__init__(parent)
        self.ui = Ui_MainWindow1()
        self.ui.setupUi(self)
        self.ui.textBrowser.append('<font color="#f36373">When someone asks:</font>')

        self.ui.textBrowser1.append('<font color="#f36373">Angie will answer:</font>')

        self.ui.pushButton1.clicked.connect(self.buttonEvent)
    
    def center(self):
        # geometry of the main window
        qr1 = self.frameGeometry()

        # center point of screen
        cp1 = QDesktopWidget().availableGeometry().center()

        # move rectangle's center point to screen's center point
        qr1.moveCenter(cp1)

        # top left of rectangle becomes top left of window centering it
        self.move(qr1.topLeft())

    def buttonEvent(self):
        text1 = self.ui.textEdit.toPlainText()
        text2 = self.ui.textEdit1.toPlainText() 
        if str(text1)== '' or str(text2)== '':
            pass
        else:

           # self.ui.textBrowser.append(text1)
           # self.ui.textBrowser.append(text2)
            data_set={"tag": text1, "patterns": [text1], "responses": [text2],"context": [""]}
  
            def write_json(data):
                with open("intents.json",'w') as f:
                    json.dump(data,f,indent=4)
            z=0
            with open("intents.json") as json_file:
                data=json.load(json_file)

                temp=data["intents"]
                for i in temp:
                    for pat in i['patterns']:
                        if pat== text1:
                            print(pat)
                            z=1   
                            i['responses'].append(text2)
            if z==0:
                temp.append(data_set)
            
            write_json(data)

    
            self.ui.textEdit.clear()
            self.ui.textEdit1.clear()
    


        self.close() 

class MainWindow(QtWidgets.QMainWindow):
    def __init__(self,parent=None):
        super(MainWindow, self).__init__(parent)
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        self.ui.textBrowser.append('<font color="#f36373">Angie: Hello! I\'m Angie, how can i help you?</font>')

        

        self.HOST = '127.0.0.1'
        self.PORT = 8000

        self.ui.pushButton.clicked.connect(self.buttonEvent)
        
        self.ui.pushButton4.clicked.connect(self.buttonEvent4)

        self.ui.pushButton1.clicked.connect(self.buttonEvent1)
        self.dialog = MainWindow1(self)
        self.dialog.center()

        self.ui.pushButton2.clicked.connect(self.buttonEvent2)
    
    def center(self):
        # geometry of the main window
        qr = self.frameGeometry()

        # center point of screen
        cp = QDesktopWidget().availableGeometry().center()

        # move rectangle's center point to screen's center point
        qr.moveCenter(cp)

        # top left of rectangle becomes top left of window centering it
        self.move(qr.topLeft())
 
    def buttonEvent(self):
        text = self.ui.textEdit.toPlainText()     
        
        if str(text)== '':
            pass
        else:
            doc = nlp(text)

            # part of speech tagging using spaCy
            sentimental_rate=0;
            # tokenization
            #&
            #removing stop words
            filtered_tokens=[token for token in doc if not token.is_stop]

            #normalization: lemmatization
            lemmas = [
                #f"Token: {token}, lemma: {token.lemma_}"
                token.lemma_
                for token in filtered_tokens
            ]

            #list of contrast words
            nots =["nt","cannot","not","no","n't"]

            for token in lemmas:
        
                for label in neg_words.index:
             
                     if token == label:
                         sentimental_rate = sentimental_rate -1

                for label in pos_words.index:
                     if token == label:
                        sentimental_rate = sentimental_rate +1  
            #tokenize
            for token in doc:
                for label in nots:
                    print (token)
                    if token.text == label:
                        if(sentimental_rate >0): sentimental_rate = sentimental_rate -10000
                        elif(sentimental_rate<0): sentimental_rate=sentimental_rate +10000

            #print(sentimental_rate)
            if(sentimental_rate >0): messagee = "Positive"
            elif(sentimental_rate <0):messagee = "Negative"
            else: messagee = "Neutral"
            sentimental_rate =0;

            #Begin write mood
            f = open("moodL.txt","a")
            print(messagee)
            ts = calendar.timegm(time.gmtime())
            print(ts)
            f.write(str(ts) + ':' + 'Mood' + ':' + messagee + '\n')
            f.close()
            #End write mood

            self.ui.textEdit.clear()
            self.ui.textBrowser.append('User: '+text +'  ('+messagee+')')

            self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.client.connect((self.HOST, self.PORT))
            self.client.sendall(text.encode())

            # Server message
            serverMessage = str(self.client.recv(1024), encoding='utf-8')
            self.ui.textBrowser.append('<font color="#f36373">Angie: '+serverMessage+'</font>')
            self.client.close()
    
    def buttonEvent4(self):

        with open("moodL.txt") as f:
            data = f.readlines()
            x = [line.split(':')[0] for line in data]
            y = [line.split(':')[2] for line in data]

        data1 = {'Date': x,'Mood': y}
        df1 = DataFrame(data1, columns=['Date','Mood'])
        print(df1)

        with open("keyboardL.txt") as f:
            data = f.readlines()
            date1 = [line.split(':')[0] for line in data]
            ktype = [line.split(':')[1] for line in data]
            key = [line.split(':')[2] for line in data]

        data2 = getDuplicatesWithCount(key)
        df2 = DataFrame(data2, columns=['Key','Count'])
        print(df2)

        data3 = getTapsWithCount(date1, ktype, key)
        df3 = DataFrame(data3, columns=['Type','Count'])
        print(df3)
 
        root= tk.Tk()

        figure1 = plt.Figure(figsize=(6,6), dpi=100)
        ax1 = figure1.add_subplot(111)
        ax1.plot(df1['Mood'], color = 'g')
        plot = FigureCanvasTkAgg(figure1, root)
        plot.get_tk_widget().pack(side=tk.LEFT, fill=tk.BOTH)
        ax1.legend(['Mood'])
        ax1.set_xlabel('Time')
        ax1.set_title('Mood vs Time')

        figure3 = plt.Figure(figsize=(6,6), dpi=100)
        ax3 = figure3.add_subplot(111)
        pie3 = FigureCanvasTkAgg(figure3, root)
        pie3.get_tk_widget().pack(side=tk.LEFT, fill=tk.BOTH)
        df3 = df3[['Type','Count']].groupby('Type').sum()
        df3.plot(kind='pie', subplots=True, legend=True, autopct='%1.1f%%', shadow=True, startangle=90, ax=ax3)
        ax3.set_title('LongPresses vs QuickTaps vs Deletions')

        figure2 = plt.Figure(figsize=(6,6), dpi=100)
        ax2 = figure2.add_subplot(111)
        bar2 = FigureCanvasTkAgg(figure2, root)
        bar2.get_tk_widget().pack(side=tk.LEFT, fill=tk.BOTH)
        df2 = df2[['Key','Count']].groupby('Key').sum()
        df2.plot(kind='bar', legend=True, ax=ax2)
        ax2.set_title('Key Count')

        root.mainloop()

    def buttonEvent1(self):
        self.dialog.show()

    def buttonEvent2(self):
        self.ui.pushButton2.setEnabled(False)
        
        self.HOST = '127.0.0.1'
        self.PORT = 8000
        
        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        text="reload_model"
        self.client.connect((self.HOST, self.PORT))
        self.client.sendall(text.encode()) 
        self.ui.textBrowser.clear()
        
        os.system('python3 train_chatbot.py')
        self.ui.textBrowser.append('<font color="#f36373">Angie: Hello! I\'m Angie, how can i help you?</font>')
        self.ui.pushButton2.setEnabled(True)  

#Main
def main():
    listener = keyboard.Listener(on_press=Loggers.on_press, on_release=Loggers.on_release)
    listener.start()
    app = QtWidgets.QApplication([])
    window = MainWindow()
    window.show()
    window.center()
    sys.exit(app.exec_())

if __name__ == '__main__':
    main()
