package Юзабельные_классы;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.model.Student;

import java.io.*;

/**
 * Класс для серриализации и дессериализации с использованием Jackson
 */
public class JacksonConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();

    /**
     * Серриализация объекта в файл
     */
    public void saveStudentToFile(String fileName, Student student) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), student);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    outputStream.writeObject(student);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(new File(fileName), student);
            }
            else System.out.println("Incorrect file type");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Дессериализация объекта из файла
     */
    public Student loadStudentFromFile(String fileName) {
        Student student = new Student();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    student = objectMapper.readValue(file, Student.class);
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                        student = (Student) inputStream.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    student = xmlMapper.readValue(file, Student.class);
                }
                else System.out.println("Incorrect file type");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return student;
    }
}
