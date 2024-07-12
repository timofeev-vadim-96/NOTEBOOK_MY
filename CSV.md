
## OpenCSV

Библиотека для чтения CSV-файлов: `opencsv`
```xml
        <dependency>
            <groupId>com.opencsv</groupId>
            <artifactId>opencsv</artifactId>
            <version>${opencsv.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>commons-collections</groupId>
                    <artifactId>commons-collections</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```

пример текста для парсинга в файле:  
```
# Добавить сюда своих вопросов. Эту строку надо пропустить при настройке CsvToBean (withSkipLines)
Is there life on Mars?;Science doesn't know this yet%true|Certainly. The red UFO is from Mars. And green is from Venus%false|Absolutely not%false
How should resources be loaded form jar in Java?;ClassLoader#geResourceAsStream or ClassPathResource#getInputStream%true|ClassLoader#geResource#getFile + FileReader%false|Wingardium Leviosa%false
Which option is a good way to handle the exception?;@SneakyThrow%false|e.printStackTrace()%false|Rethrow with wrapping in business exception (for example, QuestionReadException)%true|Ignoring exception%false
```

Аннотированный класс вопроса:  
```java
@Data
public class QuestionDto {

    @CsvBindByPosition(position = 0)
    private String text;

    @CsvBindAndSplitByPosition(position = 1, collectionType = ArrayList.class, elementType = Answer.class,
            converter = AnswerCsvConverter.class, splitOn = "\\|")
    private List<Answer> answers;

    public Question toDomainObject() {
        return new Question(text, answers);
    }
}
```

Класс ответа:  
```java
public class AnswerCsvConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String value) {
        var valueArr = value.split("%");
        return new Answer(valueArr[0], Boolean.parseBoolean(valueArr[1]));
    }
}
```

метод парсинга:  
```java
    public List<Question> findAll() {
        try {
            ClassPathResource resource = new ClassPathResource(fileNameProvider.getTestFileName());
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            CsvToBean<QuestionDto> csvToBean = new CsvToBeanBuilder<QuestionDto>(reader)
                    .withSeparator(';') //отделяет вопрос от ответов
                    .withType(QuestionDto.class)
                    .withSkipLines(1) // Пропускаем первую строку, содержащую комментарий
                    .build();

            // Использовать CsvToBean
            // https://opencsv.sourceforge.net/#collection_based_bean_fields_one_to_many_mappings
            // Использовать QuestionReadException
            // Про ресурсы: https://mkyong.com/java/java-read-a-file-from-resources-folder/
            return csvToBean.parse()
                    .stream()
                    .map(QuestionDto::toDomainObject)
                    .toList();
        } catch (IOException e) {
            throw new QuestionReadException(e.getMessage(), e);
        }
    }
```