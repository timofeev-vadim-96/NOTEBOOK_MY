> У ВК и Яндекс свои собственные сборщики проектов. 

## Maven - для легаси проектов

Maven - это инструмент для автоматической сборки проектов на основе описания их структуры в специальных файлах на языке POM (Project Object Model)

```xml
    <!--after_properties-->
    
    <!--AssertJ-->
    <dependencies>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.24.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <!--GSON-->
    <dependencies>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>

    <!--JUnit-->
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-commons</artifactId>
            <version>1.9.2</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>5.9.1</version>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <!--Для корректной сборк в jar-->
        <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>org.mainClass.Main</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build></project>


<!--MOCKITO-->
    <!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>4.11.0</version>
    <scope>test</scope>
</dependency>


<!--Testcontainers-->
<!-- https://mvnrepository.com/artifact/org.testcontainers/testcontainers -->
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>testcontainers</artifactId>
    <version>1.17.6</version>
    <scope>test</scope>
</dependency>

<!--HIBERNATE-->
<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.6.15.Final</version>
</dependency>

<!---MySQL-->
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.28</version>
</dependency>

<!-- LOMBOK-->
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.24</version>
    <scope>provided</scope>
</dependency>

<!--JAXB для работы с XML-форматом-->
<!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->
<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.1</version>
</dependency>
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.3</version>
</dependency>

<!--SELENIUM_FOR_E2E_TESTS-->
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.8.3</version>
</dependency>



```

`groupId, artifactId и version (GAV)` - индентификаторы проекта    
* groupId - наименование организации или подразделения, туда записывают доменное имя организации или сайта проекта.
Например: com.google
* artifactId — название проекта.
Например: apache
* version - версия. Пример: 1.0

<properties></properties> - настройки проекта
<build></build> - параметры сборки проекта

Подуровень "build":
* <resources></resources> - доп.ресурсы. Например, свои доп. каталоги или файлы (например, файл с длинным SQL-запросом) (структура папок должна соответствовать проекту) org/example (именно через слэш, чтобы создалось две папки) и ниже (если есть).
* <finalName>имя, которое будет у проекта (структура папок), игнорируя GAV</finalName> //ХЗ, или в build, или ниже в плагине сборки проекта, под GAV

Доп.инфа: //сомнительно полезно
Компиллятор java (тот, который по дефолту указан в "properties"), это плагин maven. Все эти настройки проекта можно также пересобрать в блоке "build".
Вот так:
```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>20</source>
                    <target>20</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

Еще один плагин. "`maven assembly`" - для упаковывания всех ресурсов(и зависимостей) вместе с проектом в jar-файл. `Походу, обязательная история при использовании внешних библиотек`

```xml  
     <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>red.dragon.Main</mainClass> <!--точка входа в программу-->
                        </manifest>
                    </archive>
                    <descriptorRefs>
                    <!--настройка, позволяющая упаковывать все зависимости в архив-->
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                    <!--ПРОСТО ДЛЯ ПРИМЕРА: ФИНАЛЬНОЕ ИМЯ JAR СЮДА:-->
                    <finalName>HelloApp</finalName>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id> <!--plugin_id-->
                        <phase>package</phase> <!--фаза сборки, когда будет испльзоваться-->
                        <goals>
                            <goal>single</goal><!--утилита плагина, которая будет запущена в данной фазе-->
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

`Жизненный цикл Maven - описывает жизненный цикл разработки ПО`
* clean - чистит проект от target и out (ранее скомпиллированные классы)
* validate - проверяет структуру и отсуствтие некорректных зависимостей (например, циклические зависимости, когда есть две зависимости, зависящие друг от друга). Maven ожидает, что все зависимости выстроятся в древовидную структуру, а не созависимую.
* compile - откомпиллит классы в target
* test - выполнение всех юнит-тестов в проекте
* package - создание jar
* verify - запуск интеграционных тестов (эмм, они как-то помечаются??)
* install - собранный артефакт сохраняется в локальный репозиторий на диске. В дальнейшем, можно будет либо испльзовать программу в других проектах, либо залить на maven repo
  * путь к локальному репо по умолчанию: «C:\Users\${UserName}\.m2\repository», где UserName это имя вашей учетной записи
* site - генерация документации
* deploy - заливка приложения на удаленный сервер и его запуск

---

## Gradle - для более молодых проектов. Формирует сервисный объект на JVM, отслеживающий состояние проекта. Позволяет писать собственные сценарии и задачи (для отдельных этапов жизненного цикла)

> долгий билд изначального проекта (подкачка платформы и junit 150 мб)

структура проекта:  
settings.gradle.kts: имя проекта  
gradlew: скрипты, осуществляющие построение на bash
build.gradle (на Kotlin) - для сборки. `Аналог pom`
здесь:
* plugins - плагины (в поиске "gradle плагины")
* depe... - зависимости с тегами
  * стандартная зависимость - implementation()
* task...
  * здесь, если нужно испльзовать какие-то утилиты(таски) при сборке

Жизненный цикл в Gradle:
* build - собрать проект. НО! не определен эндпоинт(входная точка, Main)
Чтобы добавить манифест (просто ниже стандартного таска с тестом):
tasks.jar {
    manifest.attributes["Main-Class"] = "org.example.Main"
}
