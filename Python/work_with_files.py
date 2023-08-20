# режим a - append
colors = ['red', 'green', 'blue']
data = open('file.txt', 'a')
data.writelines(colors)
data.close() # нужно не забывать закрывать файл

# режим w - write
with open('file.txt', 'w') as data: # file.txt - пусть к файлу. Указывается название, если файл в той же папке, 
    # что и проект
    data.write('line 1\n')
    data.write('line 3\n') # файл закроется сам после выполнения тела кода

# режим r - read
path = 'file.txt'
data = open('file.txt', 'r')
for line in data:
    print(line)
data.close()