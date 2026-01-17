
package Паттерны;

class Singleton {
    //volatile - для видимости изменений во всех потоках
    private static volatile Singleton instance;

    //предотвращаем создание через дефолтный конструктор
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) { //Первая проверка, без блокировки
            synchronized (Singleton.class) {
                if (instance == null) { //вторая проверка
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}