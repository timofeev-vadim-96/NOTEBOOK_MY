
# Spring Cloud Feign 

- популярный декларативный REST-клиент

> в соответствии с официальной документацией, команда Spring более не поддерживает Feign

[Пример OTUS](https://github.com/petrelevich/jvm-digging/blob/main/spring-cloud/service-client/src/main/java/ru/demo/config/ServiceClientApplConf.java)

`FeignClient`

`Зависимость`
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

Пример
```java
public interface ClientAdditionalInfoClient {

    @GetMapping(value ="/additional-info", consumes = "application/json")
    ClientData additionalInfo(@RequestHeader(HEADER_X_REQUEST_ID) String xRequestId, URI baseUri, @RequestParam("name") String nameVal);
}
```
  * value = url
  * consumes = что принимает в ответ

Пример настройки Feign/фейна ВЫШЕ в java-стиле
```java
    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder().build();
    }

    @Bean
    public ClientAdditionalInfoClient clientAdditionalInfoClient(
            Decoder decoder,
            Encoder encoder,
            Contract contract,
            ObjectMapper mapper,
            MeterRegistry meterRegistry,
            ObservationRegistry observationRegistry) {

        return Feign.builder()
                .encoder(new RequestEncoder(encoder)) //кодировка стандартно необходимо для работы с телом в виде json. Но так как тело пустое, маппер не нужен
                .decoder(new ResponseDecoder(decoder, mapper)) //а здесь нужен (ответ в виде json)
                .contract(contract)
                .logLevel(Logger.Level.FULL)
                .addCapability(new MicrometerObservationCapability(observationRegistry))
                .addCapability(new MicrometerCapability(meterRegistry))
                .retryer(new Retryer.Default(500, 5_000, 10)) //параметры повторных ретраев
                .target(ClientAdditionalInfoClient.class,"http"); //http - здесь харкодится URL куда обращаться, но при использовании Eureka не имеет значения что здесь написано. НЕ ПОДСТАВЛЯТЬ КОНКРЕТНЫЙ URL ИЗ ЭВРИКИ, Т.К. ПРИБЬЕМ ГВОЗДЯМИ ИНСТАНС ПРИЛОЖЕНИЯ, К КОТОРОМУ ОБРАЩАЕМСЯ
    }
```