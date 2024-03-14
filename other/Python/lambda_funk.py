
A = 20

def fibonachi(N):
    if N == 1 or N == 2: return 1
    else:
        return fibonachi(N-2) + fibonachi (N-1)
    
def fibo_list(f):
    basic_list = []
    for i in range(1, A+1):
        basic_list.append(f(i))
    return basic_list

def couples_square(input_list):
    res_list = []
    for i in input_list:
        if i%2 == 0:
            res_list.append((i, i*i))
    return res_list

fib_list = fibo_list(fibonachi)
res_list = couples_square(fib_list)
print(fib_list)
print(res_list)

# 2 способ - с лямбда функцией

# def select(f, data):
#     return [f(x) for x in data]
# def where(f, data):
#     return [x for x in data if f(x)]
# res_list = select(int, fib_list)
# res_list = where(lambda x: x%2 == 0, res_list)
# res_list = list(select(lambda x: (x, x**2), res_list))
# print(fib_list)
# print(res_list)







