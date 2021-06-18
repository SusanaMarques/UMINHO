#!/usr/bin/env python
# -*- coding: utf-8 -*-

from PyQt5 import QtCore, QtGui, QtWidgets, QtGui
from PyQt5.QtGui import QPixmap
from PyQt5.QtGui import QIcon
from PyQt5.QtWidgets import QApplication, QWidget, QLabel
from PyQt5.QtGui import QIcon, QPixmap
from PyQt5.QtGui import QMovie

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(700, 700)
        
        
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.textBrowser = QtWidgets.QTextBrowser(self.centralwidget)
        
        self.textBrowser.setGeometry(QtCore.QRect(0, 0, 550, 590))
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setGeometry(QtCore.QRect(0, 0, 200, 300))
        self.label.setObjectName("label")
        MainWindow.setCentralWidget(self.centralwidget)
        font = QtGui.QFont()
        font.setFamily("Montserrat")
        font.setPointSize(12)
        font.setBold(True)
        font.setWeight(75)
        self.label2 = QLabel(self.centralwidget)
        self.pixmap2 = QPixmap('imgs/angie.png')
        self.pixmap3 = self.pixmap2.scaled(140,140)
        self.label2.setPixmap(self.pixmap3)
        self.label2.move(490,455)

        self.label3 = QLabel(self.centralwidget)
        self.pixmap4 = QPixmap('imgs/sugestions.png')
        self.pixmap5 = self.pixmap4.scaled(130,80)
        self.label3.setPixmap(self.pixmap5)
        self.label3.move(565,420)

        #begin graphs
        self.label3 = QLabel(self.centralwidget)
        self.pixmap4 = QPixmap('imgs/graphs.png')
        self.pixmap5 = self.pixmap4.scaled(60,60)
        self.label3.setPixmap(self.pixmap5)
        self.label3.move(561,80)
        #end graphs

        self.label4 = QLabel(self.centralwidget)
        self.pixmap5 = QPixmap('imgs/sendv7.png')
        self.pixmap6 = self.pixmap5.scaled(60,60)
        self.label4.setPixmap(self.pixmap6)
        self.label4.move(468,615)

        self.label5 = QLabel(self.centralwidget)
        self.pixmap6 = QPixmap('imgs/refresh.png')
        self.pixmap7 = self.pixmap6.scaled(50,50)
        self.label5.setPixmap(self.pixmap7)
        self.label5.move(565,10)

        self.textBrowser.setFont(font)
        self.textBrowser.setObjectName("textBrowser")
        self.textEdit = QtWidgets.QTextEdit(self.centralwidget)
        self.textEdit.setGeometry(QtCore.QRect(5, 600, 440, 90))
        font = QtGui.QFont()
        font.setFamily("Montserrat")
        font.setPointSize(12)
        font.setBold(True)
        font.setWeight(75)
        self.textEdit.setFont(font)
        self.textEdit.setObjectName("textEdit")

        #REFRESH
        self.pushButton2 = QtWidgets.QPushButton("      ",self.centralwidget)
        self.pushButton2.setGeometry(QtCore.QRect(565, 10, 50, 50))
        font = QtGui.QFont()
        font.setFamily("Montserrat")
        font.setPointSize(20)
        font.setBold(True)
        font.setWeight(75)
        self.pushButton2.setFont(font)
        self.pushButton2.setStyleSheet("background-color: none; border: none;")
        self.pushButton2.setCursor(QtGui.QCursor(QtCore.Qt.PointingHandCursor))
        
        #button
        self.pushButton = QtWidgets.QPushButton("      ",self.centralwidget)
        self.pushButton1 = QtWidgets.QPushButton("Sugestions?",self.centralwidget)
        self.pushButton1.setGeometry(QtCore.QRect(508, 1, 89, 89))
        self.pushButton.setGeometry(QtCore.QRect(450, 600, 100, 89))
        font = QtGui.QFont()
        font.setFamily("Montserrat")
        font.setPointSize(20)
        font.setBold(True)
        font.setWeight(75)
        self.pushButton.setFont(font)
        self.pushButton.setStyleSheet("border:none; background-color: none;")
        self.pushButton.setCursor(QtGui.QCursor(QtCore.Qt.PointingHandCursor))
        self.pushButton1.move(577,441)
        self.pushButton1.setFixedHeight(20)
        self.pushButton1.setFixedWidth(100)
        self.pushButton1.setStyleSheet("border: none; background-color: #fcc4d4; color: #919191;\n")
        self.pushButton1.setFont(QtGui.QFont('Montserrat', 11,QtGui.QFont.Bold))
        self.pushButton1.setCursor(QtGui.QCursor(QtCore.Qt.PointingHandCursor))

        #begin button graphs
        self.pushButton4 = QtWidgets.QPushButton("      ",self.centralwidget)
        self.pushButton4.setGeometry(QtCore.QRect(508, 1, 60, 60))
        font = QtGui.QFont()
        font.setFamily("Montserrat")
        font.setPointSize(20)
        font.setBold(True)
        font.setWeight(75)
        self.pushButton4.move(561,80)
        #self.pushButton4.setFixedHeight(20)
        #self.pushButton4.setFixedWidth(100)
        self.pushButton4.setStyleSheet("border: #101010; background-color: none;\n")
        #self.pushButton4.setFont(QtGui.QFont('Montserrat', 11,QtGui.QFont.Bold))
        self.pushButton4.setCursor(QtGui.QCursor(QtCore.Qt.PointingHandCursor))
        #end button graphs

"background-color: #ffffff;\n"

class Ui_MainWindow1(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow1")
        MainWindow.resize(700, 700)
 
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setGeometry(QtCore.QRect(0, 0, 200, 300))
        self.label.setObjectName("label")
        MainWindow.setCentralWidget(self.centralwidget)
        font = QtGui.QFont()
        font.setFamily("Montserrat")
        font.setPointSize(12)
        font.setBold(True)
        font.setWeight(75)
        self.label2 = QLabel(self.centralwidget)
        self.pixmap2 = QPixmap('imgs/angie_no.png')
        self.pixmap3 = self.pixmap2.scaled(190,190)
        self.label2.setPixmap(self.pixmap3)
        self.label2.move(245,360)

        #SPEECH BALLOONS
        self.label4 = QLabel(self.centralwidget)
        self.pixmap6 = QPixmap('imgs/speech_balloon_right.png')
        self.pixmap7 = self.pixmap6
        self.label4.setPixmap(self.pixmap7)
        self.label4.move(10,88)

        self.label5 = QLabel(self.centralwidget)
        self.pixmap8 = QPixmap('imgs/speech_balloon_left.png')
        self.pixmap9 = self.pixmap8
        self.label5.setPixmap(self.pixmap9)
        self.label5.move(229,240)
  
        
        self.textBrowser = QtWidgets.QTextEdit(self.centralwidget)
        self.textBrowser.setGeometry(QtCore.QRect(20, 63, 440, 70))
        self.textBrowser.setFont(font)
        self.textBrowser.setStyleSheet("border: none; background-color: rgba(0,255,0,0%); \n")

        self.textBrowser1 = QtWidgets.QTextEdit(self.centralwidget)
        self.textBrowser1.setGeometry(QtCore.QRect(240, 215, 440, 70))
        self.textBrowser1.setStyleSheet("border: none; background-color:  rgba(0,255,0,0%);  \n")
        self.textBrowser1.setFont(font)

        self.textBrowser.setReadOnly(True)
        self.textBrowser1.setReadOnly(True)

        self.textEdit = QtWidgets.QTextEdit(self.centralwidget)
        self.textEdit.setGeometry(QtCore.QRect(30, 110, 420, 65))
        self.textEdit.setStyleSheet("border: none;")
        self.textEdit1 = QtWidgets.QTextEdit(self.centralwidget)
        self.textEdit1.setGeometry(QtCore.QRect(250, 260, 420, 65))
        self.textEdit1.setStyleSheet("border: none;")

        #button
        self.pushButton1 = QtWidgets.QPushButton("LEARN",self.centralwidget)
        self.pushButton1.setGeometry(QtCore.QRect(508, 1, 89, 89))
        self.pushButton1.move(287,460)
        self.pushButton1.setFixedHeight(20)
        self.pushButton1.setFixedWidth(100)
        self.pushButton1.setStyleSheet("border: none; background-color: none; color: #919191;\n")
        self.pushButton1.setFont(QtGui.QFont('Montserrat', 18,QtGui.QFont.Bold))
        self.pushButton1.setCursor(QtGui.QCursor(QtCore.Qt.PointingHandCursor))
        font = QtGui.QFont()
        font.setFamily("Montserrat")
        font.setPointSize(20)
        font.setBold(True)
        font.setWeight(75)
"background-color: #ffffff;\n"



