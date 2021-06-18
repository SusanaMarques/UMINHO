#!/usr/bin/env python
# -*- coding: utf-8 -*-

import nltk
nltk.download('punkt')
nltk.download('wordnet')
from nltk.stem import WordNetLemmatizer
lemmatizer = WordNetLemmatizer()
import pickle
import numpy as np

from keras.models import load_model
model = load_model('chatbot_model.h5')
import json
import random
words = pickle.load(open('words.pkl','rb'))
classes = pickle.load(open('classes.pkl','rb'))
import json
import socket
import spacy
import pandas as pd

def clean_up_sentence(sentence):
    sentence_words = nltk.word_tokenize(sentence)
    sentence_words = [lemmatizer.lemmatize(word.lower()) for word in sentence_words]
    return sentence_words

# return bag of words array: 0 or 1 for each word in the bag that exists in the sentence

def bow(sentence, words, show_details=True):
    # tokenize the pattern
    sentence_words = clean_up_sentence(sentence)
    # bag of words - matrix of N words, vocabulary matrix
    bag = [0]*len(words)
    for s in sentence_words:
        for i,w in enumerate(words):
            if w == s:
                # assign 1 if current word is in the vocabulary position
                bag[i] = 1
                if show_details:
                    print ("found in bag: %s" % w)
    return(np.array(bag))

def predict_class(sentence, model):
    # filter out predictions below a threshold
    p = bow(sentence, words,show_details=False)
    res = model.predict(np.array([p]))[0]
    ERROR_THRESHOLD = 0.25
    results = [[i,r] for i,r in enumerate(res) if r>ERROR_THRESHOLD]
    # sort by strength of probability
    results.sort(key=lambda x: x[1], reverse=True)
    return_list = []
    for r in results:
        return_list.append({"intent": classes[r[0]], "probability": str(r[1])})
    return return_list

def getResponse(ints, intents_json):
    tag = ints[0]['intent']
    list_of_intents = intents_json['intents']
    for i in list_of_intents:
        if(i['tag']== tag):
            result = random.choice(i['responses'])
            break
    return result

def chatbot_response(msg,intents):
    ints = predict_class(msg, model)
    res = getResponse(ints, intents)
    return res





# HOST & PORT
    

HOST = '127.0.0.1'
PORT = 8000

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((HOST, PORT))

server.listen(5)

intents = json.loads(open('intents.json').read()) 

while True:
    conn, addr = server.accept()
    clientMessage = str(conn.recv(1024), encoding='utf-8')
    if clientMessage== "reload_model":
        # Message with response to the user 
        intents = json.loads(open('intents.json').read()) 

        model = load_model('chatbot_model.h5')
        words = pickle.load(open('words.pkl','rb'))
        classes = pickle.load(open('classes.pkl','rb'))
        conn.close()

    else:
         # Message with response to the user 
        intents = json.loads(open('intents.json').read()) 
        model = load_model('chatbot_model.h5')
        words = pickle.load(open('words.pkl','rb'))
        classes = pickle.load(open('classes.pkl','rb'))
        message =chatbot_response(clientMessage,intents)

        serverMessage = message
        conn.sendall(serverMessage.encode())
        conn.close()


