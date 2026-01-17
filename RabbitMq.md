# RabbitMQ

`Зависимость`
```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

[Пример OTUS](https://github.com/OtusTeam/Spring/tree/master/2024-05/spring-36-rabbit)

`docker-compose`
```yml
version: '3'
services:
  rabbitmq:
    image: rabbitmq:management
    ports:
      - "5672:5672"
      - "15672:15672"
```

`Очередь сообщений` это
- такая структура данных
- реализует FIFO
- туда можно класть и забирать объекты

`Зачем нужно`
- для коммуникации между приложениями/потоками/компонентами А и Б
- хранение сообщений в виде объектов

`Плюсы`:
1. позволяют работать асинхронно
2. за счет ассинхронности позволяет отложить обработку данных
3. повышение отказоустойчивости (если точка Б отвалилась, сообщения к ней будут копиться в очереди)

`RabbitMQ`
- приложение-сервер для создания и хранения очередей
- написан на Elrang

`Схема работы RabbitMQ`:  
Producers --> Exchanges --> Queues --> Consumers
  * Exchanges - маршрутизаторы сообщений, определяющие по параметрам в какую очередь отправить сообщение

RabbitMQ реализует `протокол AMQP` (Advanced Message Quening Protocol). В основе данного протокола лежат несколько понятий:
1. Поставщик (producer)
2. Подписчик (consumer)
3. Сообщение (message)
4. Очередь (queue)
5. Точка обмена (exchange)
6. Ключ маршрутизации (routing key) - с помощью него exchange определяет, в какую очередь направить сообщение от продюсера

`Виды exchanges`:
1. direct (default) - кладет в одну конкретную очередь, имя которой совпадает с ключом маршрутизации
2. topic - в зависимости соответствия ключа одному из паттернов, кладет сообщение в нужные очереди
3. fanout - кладет сообщение во все очереди, подключенные к exchange

`Бины` RabbitMQ, необходимые для работы приложения-продюсера:
1. RabbitTemplate
2. Exchange (представление объекта в очереди)

`Java-конфигурация продюсера`
```java
@Configuration
public class RabbitMqConfig {
private static final String MAIN_EXCHANGE_NAME = "main-exchange";

//конвертер для работы с сообщениями в виде объектов
@Bean
public Jackson2JsonMessageConverter jsonConverter() {
return new Jackson2JsonMessageConverter();
}

@Bean
public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
{
RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
rabbitTemplate.setExchange(MAIN_EXCHANGE_NAME); //задание exchange (у него конкретный тип)
rabbitTemplate.setMessageConverter(jsonConverter()); //для работы с json
return rabbitTemplate;
}

//создание exchange типа Topic
@Bean
public TopicExchange topicExchange(){
return new TopicExchange(MAIN_EXCHANGE_NAME);
}
}
```

`Простой сервис отправки сообщения`
```java
@Service
@RequiredArgsConstructor
public class EmitterService {
private static final String MAIN_EXCHANGE_NAME = "main-exchange";

private final RabbitTemplate rabbitTemplate;

public void sendMessage(String message, String routingKey) {
rabbitTemplate.convertAndSend(
MAIN_EXCHANGE_NAME //в какую очередь писать
, routingKey //ключ, по которому будет определяться в какую очередь направить сообщение
, message);
}

//отправка сообщения с ожиданием ответа сразу. время ожидания default = 5sec. Задается в темплейте
private final RabbitTemplate rabbitTemplate;
public AnyClass sendMessage(AnyClass messageBody, String routingKey) {
return (AnyClass) rabbitTemplate.convertSendAndReceive(routingKey,
messageBody);
}

}
```

`Конфигурация приложения-консюмера`
```java
@Configuration
public class RabbitMqConfig {
private static final String MAIN_EXCHANGE_NAME = "main-exchange";
@Bean
public TopicExchange topicExchange(){
return new TopicExchange(MAIN_EXCHANGE_NAME); 
}

	@Bean
	public Queue statCalcCommandsQueue() {
		return QueueBuilder.durable("stat-calc-commands-queue")
				.maxLength(5)
				.deadLetterExchange("dead-letter-exchange")
				.build();
	}

//очередь для сообщений, которые по каким-то причинам не были обработаны, либо очередь для них переполена и т.д. МОЖНО НЕ СОЗДАВАТЬ ЕСЛИ НЕ НУЖНА
	@Bean
	public Queue deadLetterQueue() {
		return new Queue("dead-letter-queue");
	}

//привязка очереди к exchange
@Bean
public Binding anyQueueBinding(){
return BindingBuilder.bind(anyQueue()) //очередь
.to(topicExchange()) //exchange
.with("any.activity.*"); //паттерн для ключа
}
}
```

`Подписаться на получение сообщение из очереди`
```java
@Component
public class RabbitMqListener {
@RabbitListener(queues = "any-queue")
//любой класс, если подключен конвертер
//можно и не void, если продюсер ждет ответа (отправляет через метод convertSendAndReceive())
public void processMessagesFromAnyQueue(AnyClass message) {
// Code for message processing
}
}
```

`Подтверждение сообщений`
- по умолчанию прочитанные сообщения автоматически удаляется из очереди
- есть возможность ручного подтверждения удаления сообщения через вызов метода

типы `подтверждения в sping boot`
1. NONE = классическому AUTO
2. AUTO = если метод лисенера завершился успешно
3. MANUAL - пример можно посмотреть в примере от OTUS в классе RabbitMQListener

`Допонительные настройки`:
* для очередей и exchange можно при создании указать параметры durable (default = true) и autodelete
* первый отвечает за то, переживет ли объект перезагрузку сервера
* второй за удаление объекта, когда он не используется
* объект считается используемым, пока к нему кто-то привязан
* подписчик для очереди и очереди для exchange
* так же, при создании очередей можно задать параметр exclusive и Map<Object, Object> arguments
* exclusive означает, что к данной очереди может быть подключен только один слушатель (p2p)
* В arguments можно передавать такие настройки
как (вкратце что делать, если что-то пошло не так):
  * x-message-ttl;
  * x-max-length/x-max-lenght-bytes
  * x-overflow: (drop-head, reject-publish)
  * x-dead-letter-exchange - куда будут падать проблемные месседжы (время на обработку истекло, или очередь переполнена)


Добавить `эндпоинт со статистикой` по пользователям, отправлявшим сообщения
```java
@RequiredArgsConstructor
@Component
@Endpoint(id = "activity-stat")
public class ActivityStatEndpoint {

	private final ActivityStatRepository activityStatRepository;

	@ReadOperation
	public List<ActivityStatElem> getAppUsersActivityStat() {
		return activityStatRepository.findAll();
	}
}
```