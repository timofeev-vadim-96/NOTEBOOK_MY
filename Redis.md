Redis

> туториал https://www.youtube.com/watch?v=QpBaA6B1U90
  * здесь на 17 минуте пример нагрузочного тестирования через Postman

тип NoSQL: `КЛЮЧ-ЗНАЧЕНИЕ`

Главная особенность - работа в `оперативной памяти`

`Типы данных значений`:
- strings
- lists (массивы)
- hashes (хэш-таблицы)
- sets (множества)
- sorted sets

Для взаимодействия с Redis-server:
- redis-cli
- iredis
- приложения

атомарные операции:
- lpush - добавление элемента в список
- hincrby - инкремент в hashMap

Установка (Linux)
```bash
#установка
sudo apt-get install redis
#подключение к удаленному
redis-cli -h 127.0.0.1 -p 6379
#подключение к локальному
redis-cli
#проверка
PING
```

`DOCKER`
```bash
docker run -d -p 6379:6379 redis
#входим в контейнер
docker exec -it id_container redis-cli
#проверка
PING
```

`КОМАНДЫ` для работы со `строками`
```bash
#создание ключ-значения
set firstKey "hello"
#получение значения по ключу
get firstKey
#удалить ключ
del firstKey
#весь список ключей
keys *
```

> если значений по ключу не найдено, то получим nil (тип аналог null)

`КОМАНДЫ` для работы со `списками`
```bash
#добавить значение В НАЧАЛО списка
lpush cars toyota
#добавить значение В КОНЕЦ списка
rpush cars bmw
#получить выборку из списка (задается с границами)
lrange cars 0 -1 #-1 - до конца списка
#получить первый элемент списка и удалить его
lpop cars
#размер списка
llen cars
#переместить элемент из одного списка в другой
lmove cars sold left left #cars - откуда, sold - куда, left left - из начала списка в начало списка
```

`КОМАНДЫ` для работы с `хэш-таблицами`
```bash
#создать хэш таблицу с именем iphone, и дальше элементы ключ-значение
hset iphone brand apple model "iphone x" memory 64 color black
#получить по ключу
hget iphone model
#получить несколько значений по нескольким ключам
hmget iphone brand model memory
#получить все элементы ключ-значение
hgetall iphone
```

`КОМАНДЫ` для работы с `сетами`
```bash
#содержимое сета employee
smemrers employee
```

`КОМАНДЫ` времени жизни записей (дефолт = бесконечно)
```bash
#посмотреть время жизни списка cars
TTL cars
#задать время жизни (в секундах)
expire cars 10
#создать переменную с временем жизни
setex varWithTtl 20 10 #20 - время жизни, 10 - значение по ключу
```

`КОМАНДЫ` ОБЩИЕ
```bash
#удалить все данные из redis
flushall
#тип значения по ключу employee
type employee
```