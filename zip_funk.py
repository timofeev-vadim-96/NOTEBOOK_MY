users = ['user1','user2','user3','user4','user5']
ids = [1,2,3,4]
salary = [100,200,300]
data = list(zip(users,ids,salary))
# или:
# users, ids, salary = list(zip(users,ids,salary))
# обратно: 
# users, ids, salary = list(zip(users,ids,salary)) # обратно отдельными значениями
# или: 
new_data = list(zip(*zip(users,ids,salary))) # обратно списком
print(data)
print(new_data)