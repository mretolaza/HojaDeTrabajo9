# Matriz de rutas mas cortas, algoritmo de Floyd y centro del Grafo. 
# María Mercedes Retolaza Reyna, 16339 


#funcion para leer el documento de texto
def leertxtenlista():
    archi=open('guategrafo.txt','r')
    linea=archi.readline()  
    while linea!="":        
    #primero,segundo y tercero guardan los indices de los espacios
    #los cuales son los que determinan el nombre de la ciudad 1(primero)
    #la ciudad 2 (segundo) y la distancia (tercero)
        primero= linea.find(str)
        segundo= linea.find(str,primero+1)
        tercero= linea.find(str,segundo)
    #uno,dos y tres guardan las palabras y/o numeros de nombres y distancias
    #lo hace por medio se "sub Strings" 
        uno= linea[0:primero]
        dos=linea[primero+1:segundo]
        tres=linea[segundo+1:]
    #creamos el grafo de forma (ciudad1,ciudad2,distancia de 1 a 2)
        g.add_edge(uno,dos,weight=int(tres))
        linea=archi.readline()
    archi.close()
    #cerramos el archivo
#importamos las librerias necesarias
import networkx as nx
g = nx.DiGraph()
#creamos el grafo
#creamos variables
a=""
#lista_nombres guarda los nombres ordenados de los nodos para la matriz
lista_nombres=[]
#lista_numeros guarda los valores de las distancias para calcular el centro del grafo
lista_numeros=[]
#variables para guardar las posiciones
primero=0
segundo=0
tercero=0
#variable para guardar el mayor temporal
mayor=0
#variable para comparar con el menor, valor infinito para que cumpla
menor="inf"
#variable de separador a buscar
str=" "
#leemos el documento y creamos el grafo
leertxtenlista()
print("Bienvenido al programa")
#matriz para dar los resultados, el tamaño es la cantidad de nodos en filas y columnas
distancia = [[0 for i in xrange(len(g))] for i in xrange(len(g))]
#se aplica el algoritmo 
predecesor, distance = nx.floyd_warshall_predecessor_and_distance(g)
print ("Matriz de distancia mas corta: ")
ni=-1
nj=0
for i in distance:  #guardar el resultado en la matriz distancia
    dis = distance[i]
    nj=0
    ni=ni+1
    for j in dis:
        distancia[ni][nj] = dis[j]
        nj=nj+1
print("Columnas/filas(orden):")
#ciclo para guardar el orden de las columnas/filas 
for i in distance:
    a=a+','+i
    lista_nombres.append(i)
#imprimimos la lista que los contiene
print a[1:]
#imprimimos la matriz
for f in distancia:
    print (f)
nx=0
ny=0
#ciclo para cuardar el valor mas grande de cada columnta
for x in distancia:
    nx=0
    for y in range (len (x)):
        if distancia[nx][ny]> mayor:
            mayor=distancia[nx][ny]
        nx=nx+1
    #cuando se encuentra se guarda en una lista de los valores por columna
    lista_numeros.append(mayor)
    ny=ny+1
#ciclo para buscar el menor de los mayores de las columnas
for x in range(len(lista_numeros)):
    if lista_numeros[x]<menor:
        menor=lista_numeros[x]
        #se guarda como "menor"
        
print ("El centro del grafo es el nodo:")
#el centro del grafo sera el indice del menor pero en la lista de los nombres
print lista_nombres[lista_numeros.index(menor)]