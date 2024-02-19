package Юзабельные_классы;

import java.io.*;

/**
 * Класс для серриализации и дессериализации любых объектов в какой-то свой вид в ФАЙЛ
 */
public class ObjectSerializerUtil {

    public static Object deserialize(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return objectInputStream.readObject();
        }
    }

    public static void serialize(Object object, String filename) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        }
    }
}
