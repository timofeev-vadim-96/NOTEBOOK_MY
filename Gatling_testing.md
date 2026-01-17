Gatling - для нагрузочного тестирования

`Зависимости`
```xml
<dependency>
    <groupId>io.gatling</groupId>
    <artifactId>gatling-app</artifactId>
    <version>3.7.2</version>
</dependency>

<dependency>
    <groupId>io.gatling.highcharts</groupId>
    <artifactId>gatling-charts-highcharts</artifactId>
    <version>3.7.2</version>
</dependency>

<plugin>
    <groupId>io.gatling</groupId>
    <artifactId>gatling-maven-plugin</artifactId>
    <version>4.2.9</version>
    <configuration>
        <simulationClass>org.baeldung.EmployeeRegistrationSimulation</simulationClass>
    </configuration>
</plugin>
```