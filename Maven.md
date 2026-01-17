> [НАЗАД к СОДЕРЖАНИЮ](README.md)

---

У ВК и Яндекс свои собственные сборщики проектов. 

## Maven - для легаси проектов

`Maven` - это инструмент для автоматической сборки проектов на основе описания их структуры в специальных файлах на языке POM (Project Object Model)

```xml
    <!--after_properties-->
    
    <!--AssertJ-->

        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.24.2</version>
            <scope>test</scope>
        </dependency>


    <!--GSON-->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
            <scope>compile</scope>
        </dependency>
    

    <!--JUnit-->
  
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
<!--MOCKITO АННОТАЦИИ-->
<dependency>
    <groupId>org.mockito</groupId>
     <artifactId>mockito-junit-jupiter</artifactId>
     <version>3.5.10</version>
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

<!--SPRING-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>6.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>6.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>6.1.2</version>
        </dependency>
        <dependency>
        <!--SPRING (Для работы с MVC-приложениями)-->
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>6.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>6.1.2</version>
        </dependency>

<!--Themeleaf Spring6 шаблонизатор. ВАЖНО!! Версия такая же, как и SPRING!-->
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring6</artifactId>
            <version>3.1.2.RELEASE</version>
        </dependency>

<!--Jakarta Servlet (старая Javax)-->
<!--Использует класс AbstractAnnotationConfigDispatcherServletInitializer, для замены web.xml-->
    <dependency>
      <groupId>jakarta.servlet</groupId>
      <artifactId>jakarta.servlet-api</artifactId>
      <version>6.0.0</version>
      <scope>provided</scope>
    </dependency>

<!--Hibernate validator-->
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>8.0.0.Final</version>
</dependency>

<!-- quartz-scheduler/quartz -->
<dependency>
    <groupId>org.quartz-scheduler</groupId>
    <artifactId>quartz</artifactId>
    <version>2.3.2</version>
</dependency>

<!--плагн для тестирования ВСЕГО ПРОЕКТА-->
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M5</version>
        </dependency>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <dependencies>
                    <dependency>
                        <groupId>org.junit.jupiter</groupId>
                        <artifactId>junit-jupiter-engine</artifactId>
                        <version>5.7.0</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

<!--плагн для тестирования ВСЕГО ПРОЕКТА + LOMBOK-->

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <dependencies>
                    <dependency>
                        <groupId>org.junit.jupiter</groupId>
                        <artifactId>junit-jupiter-engine</artifactId>
                        <version>5.7.0</version>
                    </dependency>
                </dependencies>
                <configuration>
                    <excludes>
                        <exclude>org.projectlombok:lombok</exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

`groupId, artifactId и version (GAV)` - индентификаторы проекта    
* groupId - наименование организации или подразделения, туда записывают доменное имя организации или сайта проекта.
Например: com.google
* artifactId — название проекта.
Например: apache
* version - версия. Пример: 1.0

```xml
<properties></properties> - настройки проекта
<build></build> - параметры сборки проекта
```

Подуровень "build":
```xml
* <resources></resources> - доп.ресурсы. Например, свои доп. каталоги или файлы (например, файл с длинным SQL-запросом) (структура папок должна соответствовать проекту) org/example (именно через слэш, чтобы создалось две папки) и ниже (если есть).
* <finalName>имя, которое будет у проекта (структура папок), игнорируя GAV</finalName> //ХЗ, или в build, или ниже в плагине сборки проекта, под GAV
```

**Доп.инфа**  
`Компиллятор java` (тот, который по дефолту указан в "properties"), это плагин maven. Все эти настройки проекта можно также пересобрать в блоке "build".  
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

<!--для сборки Spring-приложения в JAR-->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                <!--jar можно будет запускать на винде-->
                    <executable>true</executable>
                </configuration>
            </plugin>

<!--правила, проверяемые при сборке проекта-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-enforcer-plugin</artifactId>
                <version>3.0.0-M2</version>
                <executions>
                    <execution>
                        <id>enforce</id>
                        <goals>
                            <goal>enforce</goal>
                        </goals>
                        <configuration>
                            <!--запрет дупликации зависимостей в проекте-->
                            <rules>
                                <banDuplicatePomDependencyVersions/>
                            </rules>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

<!--ЧЕКСТАЙЛ-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-checkstyle-plugin</artifactId>
                <version>${checkstyle-plugin.version}</version>
                <dependencies>
                    <dependency>
                        <groupId>com.puppycrawl.tools</groupId>
                        <artifactId>checkstyle</artifactId>
                        <version>${checkstyle.version}</version>
                    </dependency>
                </dependencies>
                <configuration>
                    <configLocation>${checkstyle.config.url}</configLocation>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>check</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

`Жизненный цикл Maven - описывает жизненный цикл разработки ПО`
* default - основной. Отвечает за сборку, тестирование, пакетирование и развертывание проекта
* clean - чистит проект от target и out (ранее скомпиллированные классы)
* validate - проверяет структуру и отсуствтие некорректных зависимостей (например, циклические зависимости, когда есть две зависимости, зависящие друг от друга). Maven ожидает, что все зависимости выстроятся в древовидную структуру, а не созависимую.
* compile - откомпиллит классы в target
* test - выполнение всех юнит-тестов в проекте
* package - создание jar
* verify - проверка, что пакет соответствует критериям проекта
* install - собранный артефакт сохраняется в локальный репозиторий на диске. В дальнейшем, можно будет либо испльзовать программу в других проектах, либо залить на maven repo
  * путь к локальному репо по умолчанию: «C:\Users\${UserName}\.m2\repository», где UserName это имя вашей учетной записи
* site - генерация документации
* deploy - заливка приложения на удаленный сервер и его запуск

`как добавить переменную в pom.xml: ` 
```xml
<spring.version>6.1.2</spring.version>
```
и в нужное место - ${}

---

С курса по спрингу:  

`Плагины` — это расширения Maven, предоставляющие дополнительные функции и возможности для
управления **процессом сборки** вашего проекта. Плагины состоят из одной или нескольких задач (goals),
которые могут быть вызваны в различных фазах жизненного цикла сборки.
Размещаются в   
```xml
<build><plugins><plugin>here</plugin></plugins></build>
```


`Репозитории` — это централизованные хранилища артефактов, таких как библиотеки и плагины. В Maven
существует три типа репозиториев: локальный, центральный, удаленный.
В случае с банком - это всегда локальный репозиторий (типа Nexus)   
Пример:
```xml
    <repositories>
        <repository>
            <id>example-repo</id>
            <url>https://example.com/repo</url>
        </repository>
    </repositories>
```

**Настройка проекта Maven**

![maven-настройка](images/Maven_настройка.png)

**profiles** - настройка запуска приложения на разных средах

![профили](images/maven_profiles.png)

**УСТАНОВКА MAVEN:**   
https://maven.apache.org/install.html (распаковать и добавить в PATH папку bin)

`Команды mvn:`  
```bash
mvn archetype:generate -DgroupID=ru.geekbrains -DafrifactId="myProject" -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false #DarchetypeArtifactId - шаблон проекта, DinteractiveMode=false - создание папок с дефолтными именами  
mvn archetype:generate #создание проекта в интерактивном режиме  
mvn package # - сборка  
mvn package -P development # подключить профиль   development  
``` 

> плагин для проверки чистоты кода - checkstyle plugin

Для исключения зависимости из сборки - `scope`

![исключение зависимости из сборки](images/dependency_scope.png)


`Создание собственного плагина: `  
Шаг 1: Создание проекта Maven
Создайте новый проект Maven:

Вы можете создать новый проект Maven с помощью команды:
```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=my-maven-plugin -DarchetypeArtifactId=maven-archetype-mojo -DinteractiveMode=false
```
Эта команда создаст базовую структуру проекта для Maven плагина.

Откройте проект в вашей IDE:

Откройте созданный проект в вашей любимой IDE (например, IntelliJ IDEA или Eclipse).

Шаг 2: Создание Mojo класса
Mojo (Maven plain Old Java Object) — это класс, который реализует логику вашего плагина.

Создайте новый класс в пакете com.example.my.maven.plugin:

Например, создайте класс HelloMojo.java:
```java
package com.example.my.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "hello")
public class HelloMojo extends AbstractMojo {

    @Parameter(property = "hello.name", defaultValue = "World")
    private String name;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello, " + name + "!");
    }
}
```
В этом примере:

Аннотация @Mojo(name = "hello") указывает, что этот Mojo будет вызываться командой mvn hello.

Аннотация @Parameter используется для определения параметров плагина.

Метод execute() содержит логику плагина.

Шаг 3: Настройка pom.xml
Обновите pom.xml для вашего плагина:

Убедитесь, что ваш pom.xml содержит следующие зависимости и настройки:
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>my-maven-plugin</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>maven-plugin</packaging>

    <name>My Maven Plugin</name>

    <dependencies>
        <dependency>
            <groupId>org.apache.maven</groupId>
            <artifactId>maven-plugin-api</artifactId>
            <version>3.6.3</version>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugin-tools</groupId>
            <artifactId>maven-plugin-annotations</artifactId>
            <version>3.6.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-plugin-plugin</artifactId>
                <version>3.6.0</version>
                <configuration>
                    <goalPrefix>myplugin</goalPrefix>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
В этом примере:

packaging установлен в maven-plugin.

Добавлены зависимости для maven-plugin-api и maven-plugin-annotations.

Настроен maven-plugin-plugin для генерации документации и других ресурсов плагина.

Шаг 4: Сборка и использование плагина
Сборка плагина:

Выполните команду:

mvn clean install
Это соберет и установит ваш плагин в локальный репозиторий Maven.

Использование плагина в другом проекте:

В другом проекте добавьте ваш плагин в pom.xml:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.example</groupId>
            <artifactId>my-maven-plugin</artifactId>
            <version>1.0-SNAPSHOT</version>
            <executions>
                <execution>
                    <goals>
                        <goal>hello</goal>
                    </goals>
                    <configuration>
                        <name>Maven</name>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
Затем выполните команду:

mvn com.example:my-maven-plugin:hello
Или, если вы используете goalPrefix:

mvn myplugin:hello
Вы увидите вывод:

[INFO] Hello, Maven!
Теперь у вас есть собственный Maven плагин, который можно использовать в других проектах. Вы можете расширять его, добавляя новые Mojo классы и параметры, чтобы создать более сложные и полезные плагины.

`Версия проекта` 1.0.0 - мажор/минор/патч  

Для исключения отдельных компонентов зависимости
```xml
<exclusions></exclusions>
```

Для исключения данной библиотеки
```xml
<optional>true</optional>
```

> Подбор версий зависимостей автоматически и сбор проекта - используя https://start.spring.io/

Для запуска конкретной фазы жизненного цикла без предыдущих, начиная с определенной:
```bash
mvn clean install --resume-from test
```

Пропустить этап тестирования
```xml
mvn clean install -DskipTests
```

Пропускать тесты во время сборки
```bash
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.2</version> <!-- Укажите актуальную версию плагина -->
            <configuration>
                <skipTests>true</skipTests>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Публикация артефактов: Если вы разрабатываете библиотеку или приложение и хотите поделиться своим кодом с другими, вы можете опубликовать свои артефакты в удаленном репозитории. Для этого используется команда mvn deploy.
```xml
<distributionManagement>
    <repository>
        <id>remote-repository</id>
        <url>https://example.com/repo</url>
    </repository>
</distributionManagement>
```
  * далее - mvn deploy

Локальный репозиторий по умолчанию: Maven использует локальный репозиторий по умолчанию, который расположен в директории пользователя в папке `.m2/repository`. Здесь хранятся все скачанные зависимости.

`settings.xml` - это конфигурационный файл Maven, который содержит настройки для среды выполнения Maven. Этот файл можно использовать для определения различных параметров, таких как настройки прокси, профили, удаленные репозитории и другие конфигурационные параметры.

В общем, файл settings.xml обычно находится в директории ${Maven_Home}/conf (где ${Maven_Home} - это директория, в которую установлен Maven). Он может также быть расположен в директории .m2 в домашней директории пользователя. Если файл не существует в пользовательской директории, Maven использует файл из директории ${Maven_Home}/conf.

Вот некоторые основные элементы, которые вы можете определить в файле settings.xml:

localRepository: Этот элемент определяет путь к локальному репозиторию Maven. Если он не указан, Maven использует локальный репозиторий по умолчанию (.m2/repository в домашней директории пользователя).

Пример:

```xml
<localRepository>/полный/путь/к/локальному/репозиторию</localRepository>
```

`mirrors:` mirrors позволяет настроить зеркала репозиториев. Зеркало - это копия удаленного репозитория, которая может использоваться для ускорения загрузки зависимостей.

Пример:

```xml
<mirrors>
    <mirror>
        <id>mirrorId</id>
        <url>http://mirror.example.com/repo</url>
        <mirrorOf>central</mirrorOf>
    </mirror>
</mirrors>
```

`proxies:` Этот элемент позволяет настроить параметры прокси, если они необходимы для выхода в Интернет через прокси-сервер.

Пример:
```xml
<proxies>
    <proxy>
        <id>example-proxy</id>
        <active>true</active>
        <protocol>http</protocol>
        <host>proxy.example.com</host>
        <port>8080</port>
        <username>proxy-user</username>
        <password>proxy-password</password>
        <nonProxyHosts>www.google.com|*.example.com</nonProxyHosts>
    </proxy>
</proxies>
```
Обычно на каждом проекте новым разработчикам дают готовый settings.xml в который нужно прописать свои учетные данные.

## Версионирование в Maven

Теория версионирования обычно включает в себя систему трех уровней версий: мажорные (Major), минорные (Minor) и патчи (Patch). Это стандартный способ обозначения изменений в программном обеспечении, предоставляя информацию о характере изменений и их совместимости с предыдущими версиями.

Мажорные версии (Major):
Увеличение мажорной версии обычно свидетельствует о внесении несовместимых изменений, которые могут повлиять на API, интерфейсы пользователя и т. д. Повышение мажорной версии может также указывать на внедрение значительных новых функциональных возможностей.

Минорные версии (Minor):
Увеличение минорной версии обычно свидетельствует о внесении обратно совместимых новых функций или улучшений, не нарушающих существующие интерфейсы. Минорные обновления могут также включать в себя исправления ошибок.

Патчи (Patch):
Увеличение патча обычно связано с внесением обратно совместимых исправлений ошибок и мелких улучшений. Эти изменения не должны вносить несовместимости и не должны влиять на функциональность, предоставленную API.

Пример версионирования в формате MAJOR.MINOR.PATCH:

1.2.3:

1 - мажорная версия

2 - минорная версия

3 - патч

Когда разработчики выпускают новую версию программного обеспечения, они выбирают, какой из уровней версии нужно обновить в зависимости от характера внесенных изменений. Это обеспечивает стандартизированный способ обозначения изменений и помогает пользователям оценить, насколько новая версия совместима с их текущими системами.

Примеры сравнения версий:

1.2.3 и 1.2.4: Предполагается, что это безопасное обновление, так как изменения затрагивают только патч.

1.2.3 и 1.3.0: Минорное изменение может добавить новые функции, но, согласно SemVer, не должно нарушать обратную совместимость.

1.2.3 и 2.0.0: Мажорное изменение может включать несовместимые изменения API, и обновление может потребовать внесения изменений в ваш код.

"SemVer" - это сокращение от "Semantic Versioning" (Семантическое версионирование). Это стандарт, который определяет схему версионирования для программного обеспечения, предоставляя четкий и структурированный способ обозначения изменений в версиях программного продукта.

Версионирование билдов в Maven может включать в себя использование различных стратегий для уникальной идентификации и отслеживания каждой версии вашего проекта в процессе разработки. В Maven существует несколько подходов к версионированию билдов:

Фиксированная версия:
Проект может иметь фиксированную версию, например,<version>1.0</version>. Этот подход подходит, если вы не планируете выпускать регулярные обновления и предпочитаете иметь стабильную версию.

Инкрементальные версии:
Вы можете использовать инкрементальное увеличение версии с каждым новым билдом. Например, <version>1.0.1</version>, <version>1.0.2</version>, и так далее. Этот метод подходит для регулярных обновлений, когда каждый билд представляет собой небольшое изменение в проекте.

Снимки (Snapshots):
Когда проект находится в активной разработке, вы можете использовать SNAPSHOT версии, как я описал в предыдущем ответе. Например, <version>1.0-SNAPSHOT</version>. SNAPSHOT версии обеспечивают динамическое обновление зависимостей и предоставляют возможность автоматически использовать самые свежие изменения при сборке проекта.

Временные метки (Timestamps) в SNAPSHOT версиях:
Для более точной идентификации SNAPSHOT версий, Maven добавляет временные метки к ним при каждой сборке. Например, <version>1.0-20220101.012345-1</version>, где 20220101.012345 - временная метка.

Пример использования временных меток в SNAPSHOT версии в файле pom.xml:

<version>1.0-20220101.012345-1</version>
Выбор стратегии версионирования зависит от потребностей вашего проекта. Фиксированные версии могут быть предпочтительными для стабильных релизов, в то время как использование SNAPSHOT версий может быть удобным для проектов в активной разработке.

Дерево зависимостей в Maven, включая транзитивные зависимости (выполнять из корня проекта)
```bash
mvn dependency:tree
```

перезагрузить зависимости внутреннего репозитория
```bash
   mvn dependency:purge-local-repository
```