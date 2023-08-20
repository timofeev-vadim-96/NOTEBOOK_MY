# d = [1, 2, [True, False], ["Москва", "Уфа", [100, 101], ['True', [-2, -1]]], 7.89]
# С помощью рекурсивной функции get_line_list создать на его основе одномерный список из значений элементов списка d. 
# Функция должна возвращать новый созданный одномерный список.
d = [1, 2, [True, False], ["Москва", "Уфа", [100, 101], ['True', [-2, -1]]], 7.89]

# 1 способ
def get_line_list(input_list, arr = list(), counter = 0):
    if counter == len(input_list): return arr
    else: 
        arr.append(input_list[counter])
        counter +=1
        return get_line_list(input_list,arr,counter)
print(get_line_list(d))

#2 способ, но он уничтожает исходный список
def get_line_list(input_list, arr = list()):
    if len(input_list) == 0: return arr
    else: 
        arr.append(input_list[0])
        input_list.pop(0)
        return get_line_list(input_list,arr)
print(get_line_list(d))
print(d)
    