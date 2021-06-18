#!/usr/bin/env python
# -*- coding: utf-8 -*- 

import re
def csv2json(file, filename):
    
    # array que permite verificar qual ou quais sao as funçoes (sum, avg, max, min) que se encontram no csv ou se nao tem funçao ('')
    func=[]
    #array que contém o indice de cada um dos campos do array func
    listas_aninhadas =[]

    #create a json file
    #lê o nome do ficheiro .csv e cria um ficheiro .json com o mesmo nome
    json_f = re.search(r'(.*).csv',filename).group(1)
    js_f = json_f + '.json'
    json_final = open(js_f, "w+")
  

    #linha 1 do ficheiro
    linha1 = re.search(r'((.*))',file) 
    #tambem poderia ser definido com split
    campos = re.findall(r'[^;]+', linha1.group())
    
    #campos é um array que poderá ter um numero infinito onde se encontra cada um dos campos do ficheiro da linha 1
   
    for campo in campos:
        if(re.search(r'\*(avg|sum|min|max)',campo)):
            #coloca o nome da funçao no array func e o indice do campo onde essa funcao se encontra no array listas_aninhadas
            func.append(re.search(r'\*(.+)',campo).group(1))
            listas_aninhadas.append(campos.index(campo))
            #substitui notas*avg por notas_avg
            campo1 = re.sub(r'\*','_',campo)
            campos[campos.index(campo)] = campo1

        #coloca o nome da funcao (neste caso '', ja que nao existe) no array func e o indice do campo onde essa funcao se encontra no array listas_aninhadas
        elif(re.search(r'\*.*',campo)):
            #ao indicar apenas '' colocamos como se fosse uma flag que nos permite saber que nao iremos usar nenhuma função, apenas mostrar as listas alinhadas
            func.append('')
            listas_aninhadas.append(campos.index(campo))
            #substitui notas* por notas
            campo1=re.sub(r'\*.*','',campo)
            campos[campos.index(campo)] = campo1


    #array de array de linhas do ficheiro, excluindo a primeira
    linhas = re.findall(r'(?<=[\n]).+',file)

    ##(?<=[\n]).* verifica se tudo o que esta precedente do .* é uma nova linha. (positive look-behind) caso seja vai ser encontrado tudo exluindo a primeira linha. usamos .+ e não .* devido a espaços brancos##


    #criação de um array que irá ter o conteúdo final do json criado
    json_finall =''

    #primeira sibstutiçao no json final pelo primeiro carater de um ficheiro json: [
    json_finall = re.sub('\Z','[',json_finall)
    
    #por cada linha do ficheiro .csv
    for linha in linhas:
        #array com todos os valores de cada campo da linha
        valorr = re.split(r';',linha) 
        
        #ao invês da função filter() poderiamos tb usar o findall() como feito em cima para produzir o mesmo resultado com regex; mas como no enunciado nos é pedido o uso de funções split no trabalho, optamos por resolver estes dois problemas idênticos de forma diferente para abordarmos uma forma mais geral da resolução

        #filtra todos as listas vazias no array
        valorr =list(filter(bool,valorr))
        
        #se existir um campo em branco
        if len(valorr)!=len(campos):
            print('Existem valores nulos no ficheiro.csv!')
            return


        #acrescentar aspas no inicio e final de cada valor, criando um novo array com todos os valores 
        valor = [re.sub(r'^','"',v) for v in valorr]
        valor = [re.sub(r'$','"',v) for v in valor]
        for i in range(len(func)):
            if(func[i] =='sum'):
                #substitui a lista onde o campo é primeira linha é 'sum' pela sua soma 
                valor[listas_aninhadas[i]] = re.sub(r'"|\(|\)','',valor[listas_aninhadas[i]])
                #cria lista para usar o sum
                li = list(valor[listas_aninhadas[i]].split(","))
                try:
                    li=[float(i) for i in li] 
                    valor[listas_aninhadas[i]]= str(round(sum(li),2))
                except Exception:
                    print('O ficheiro csv encontra-se com carateres que não são números dentro de listas onde se pretende que tenham números, por favor tente de novo depois de compor o ficheiro .csv')
                

            if(func[i] =='avg'):
                #substitui a lista onde o campo da primeira linha é 'avg' pela sua média
                valor[listas_aninhadas[i]] = re.sub(r'"|\(|\)','',valor[listas_aninhadas[i]])
                #cria lista para calcular a média
                li = list(valor[listas_aninhadas[i]].split(","))
                try:
                    li=[float(i) for i in li] 
                    '''average'''
                    valor[listas_aninhadas[i]]= str(round(sum(li)/len(li),2))
                except Exception:
                    print('O ficheiro csv encontra-se com carateres que não são números dentro de listas onde se pretende que tenham números, por favor tente de novo depois de compor o ficheiro .csv')
                

            if(func[i] =='max'):
                #substitui a lista onde o campo da primeira linha é 'max' pelo seu valor máximo 
                valor[listas_aninhadas[i]] = re.sub(r'"|\(|\)','',valor[listas_aninhadas[i]])
                #cria lista para usar o max
                li = list(valor[listas_aninhadas[i]].split(","))
                try:
                    li=[float(i) for i in li] 
                    valor[listas_aninhadas[i]]= str(round(max(li),2))
                except Exception:
                        print('O ficheiro csv encontra-se com carateres que não são números dentro de listas onde se pretende que tenham números, por favor tente de novo depois de compor o ficheiro .csv')
                

            if(func[i] =='min'):
                #substitui a lista onde o campo da primeira linha é 'min' pelo seu valor minimo
                valor[listas_aninhadas[i]] = re.sub('"|\(|\)','', valor[listas_aninhadas[i]])
                #cria lista para usar o min
                li = list(valor[listas_aninhadas[i]].split(","))
                try:    
                    li=[float(i) for i in li] 
                    valor[listas_aninhadas[i]]= str(round(min(li),2))
                except Exception:
                    print('O ficheiro csv encontra-se com carateres que não são números dentro de listas onde se pretende que tenham números, por favor tente de novo depois de compor o ficheiro .csv')
            
            elif(func[i]==''):
                valor[listas_aninhadas[i]]= re.sub(r'"','',valor[listas_aninhadas[i]])
                valor[listas_aninhadas[i]] = re.sub('\(|^','[', valor[listas_aninhadas[i]])
                valor[listas_aninhadas[i]] = re.sub('\)|$',']', valor[listas_aninhadas[i]],1)

        #substitui no array json final no final da string por { para cada linha do ficheiro .csv
        json_finall = re.sub('\Z','\n\t{\n',json_finall)
        i=0
        while(i!=len(valor)):
            #para cada linha do .csv substitui no array do json_finall no final pelos valores dessa linha e campos já alterados previamente
            json_finall = re.sub('\Z','\t\t"'+campos[i]+'": '+valor[i]+'\n',json_finall)
            
            i=i+1
        #substitui por }, para linha
        json_finall = re.sub('\Z','\t},\n',json_finall) 
    #no final transformar a ultima virgula e \n por \n e ]
    json_finall = re.sub(',\n\Z','\n]',json_finall)
    
    #print para verificar que o ficheiro lê corretamente. retirar quando o ficheiro for demasiado grande por questões de performance
    print(json_finall)
    
    #escreve o array json_final para o ficheiro json criado e depois fecha-o
    json_final.write(json_finall)
    json_final.close()


    ##PROGRAMA COMEÇA AQUI##
while(True):
    print("Inserir ficheiro .csv como parâmetro pra ser convertido num ficheiro .json")
    inputFromUser = input("> ")
    #verifica se o input termina em .csv
    if(re.search('.csv$',inputFromUser)):
        try:
            #abre o ficheiro se ele existir e caso exista lê o conteúdo
            with open(inputFromUser) as f:
                content = f.read()
                #função que permite converter o ficheiro .csv em .json
                csv2json(content, inputFromUser)
        except getattr(__builtins__,'FileNotFoundError', IOError):
            print("Ficheiro não encontrado!")
    else: print('Ficheiro com extensão diferente de .csv!')
