Преподаватель - Евгений Борисов (Spring-потрошитель)

LLM - это Large Language Model - тип искусственного интеллекта, обученный работать с текстом: понимать, генерировать, пересказывать, дополнять, переводить и анализировать его.

`ChatClientRequest`
![LLM_ChatClientRequest](/images/LLM_ChatClientRequest.png)

Модели нужно настраивать по параметрам (options). Так выглядит обычный запрос к LLLM:
```json
{
    "model": "gamma...", // имя модели
    "prompt": "Какой-то текстовый запрос к модели", 
    "stream": false, //должна ли модель отвечать в реальном времени (буквально печатать в терминал)
    "options": {
        "nub_predict": 100, // макс количество-во токенов/символов в ответе
        "temperature": 0.7, // чем выше, тем больше вероятность попадания в ответ маловероятных токенов. Позволяет выдавать каждый раз уникальный ответ. Если 0 - то вариативность ответа будет стремиться к нулю. Большинство моделей настраивается от 0 до 2, так что следует читать про каждую модель отдельно
        "top-k:": 40, // (по количесву) макс количество вариантов к рассмотрению при генерации каждого отдельного слова
        "top-p": 0.9, //(по качеству) 90% - качество. мин. вероятность правильного подобранного слова, исходя из контекста. (суммарная вероятность уже подобранных вариантов - тип дальше не искать варианты)
        "repeat-penalty": 1.1 //штраф модели за повторение. Выставляется для избегания тавтологии в ответе ИИ
    }
}
```

`Зависимость для работы с Ollama-LLM:`
```xml
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-starter-model-ollama</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-starter-model-chat-memory-repository-jdbc</artifactId>
        </dependency>

<dependencyManagement>
<dependencies>
<dependency>
<groupId>org.springframework.ai</groupId>
<artifactId>spring-ai-bom</artifactId>
<version>${spring-ai.version}</version> --1.0.0 first stable version
<type>pom</type>
<scope>import</scope>
</dependency>
</dependencies>
</dependencyManagement>
```

`docker-compose для Ollama`  
```yml
version: '3.7'
services:
  ollama:
    image: ollama/ollama
    container_name: ollama
    ports:
      - "11431:11434"
    volumes:
      - ./ollama:/root/.ollama
    entrypoint: >
      /bin/sh -c "
        ollama serve &
        sleep 2 &&
        ollama pull gemma3:4b-it-q4_K_M &&
        ollama pull mxbai-embed-large &&
        wait
      "
    restart: unless-stopped
```

`application.yml` (также, здесь можно настраивать все параметры, как в CURL выше. oprions.temperature, options.top-k и т.д.)
```properties
spring.ai.ollama.base-url=http://localhost:11431 //url модели
spring.ai.ollama.chat.model=gemma3:4b-it-q4_K_M //какая конкретно модель. Их может быть несколько в Ollama и подобных
```

`типы Advisors и их ордеры` (порядок вызова. Начинается от меньшего к большему): 
1. MessageChatMemoryAdvisor - значения order-a = Integer.MIN_VALUE + 1000 (дефолтное)
2. QuestionAnswerAdvisor - значение order-a = 0 (дефолтное)
3. SimpleLoggerAdvisor - значение order-a = 0 (дефолтное). Логгер можно настраивать по поведению того, что он будет выводить с помощью методов requestToString()/responseToString()


Бин ChatClient для общения промптами с LLM - его билдер уже находится в стартере -> Application Context  
<details>  
<summary>Click to expand!</summary>  

```java
    private static final PromptTemplate MY_PROMPT_TEMPLATE = new PromptTemplate( //текстовка аналогична дефолтной для данного темплейта, только переведена на русский
            "{query}\n\n" +
                    "Контекст:\n" +
                    "---------------------\n" +
                    "{question_answer_context}\n" +
                    "---------------------\n\n" +
                    "Отвечай только на основе контекста выше. Если информации нет в контексте, сообщи, что не можешь ответить."
    );

    private static final PromptTemplate SYSTEM_PROMPT = new PromptTemplate( //для использования в кастомном RAG-advisor
            """
                    Ты — Евгений Борисов, Java-разработчик и эксперт по Spring. Отвечай от первого лица, кратко и по делу.
                    
                    Вопрос может быть о СЛЕДСТВИИ факта из Context.
                    ВСЕГДА связывай: факт Context → вопрос.
                    
                    Нет связи, даже косвенной = "я не говорил об этом в докладах".
                    Есть связь = отвечай.
                    """
    );

    @Autowired
    private ChatModel chatModel; //базовый бин для общения с LLM. Тип темплейт

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultAdvisors(
            ExpansoinQueryAdvisor.builder().chatModel(chatModel).order(0).build(), //кастомный advisor
            getHistoryAdvisor(), //история переписки. здесь order = 1
            //logging.level.org.springframework.ai.chat.client.advisor=DEBUG  - чтобы видеть логи SpringLoggerAdvisor
            SimpleLoggerAdvisor.builder().order(2).build(), //для логирования истории
            getRagAdviser(), //здесь order = 3
            SimpleLoggerAdvisor.builder().order(4).build() //для логирования дообогащения RAG-a
        )
        //options для данного бина темплейта
        .defaultOptions(OllamaOptions.builder()
            .topP(0.7)
            .topK(20)
            .repeatPenalty(1.1)
            .temperature(0.3) //топ рекомендованных - 0.2 - 0.3
            .build())
        .defaultSystem(SYSTEM_PROMPT.render()) //системный промпт с доп. инструкциями для LLM по ответу на пользовательский prompt
        .build();
    }

    //бин для stream-api для донаполнения контекста инфы для ответа LLM (LLM может больше знать за счет наших локальных данных в бд)
    private Advisor getRagAdviser() {
        return QuestionAnswerAdvisor.builder(vectorStore).promptTemplate(MY_PROMPT_TEMPLATE)
            .searchRequest(SearchRequest.builder()
                .topK(4) //сколько макс. взять чанков из RAG. 4 - default. Чанки берутся по релевантности
                .similarityThreshold(0.65) //аналог topP. Насколько содержание чанка подходит для контекста вопроса. Проверяет каждый чанк в количестве до topK
                .build())
            .order(3) //для определиня порядка в цепочке вызова advisors
            .build();
    }

    //бин для stream-api класса ChatClient для общения с LLM
    private Advisor getHistoryAdvisor() {
       return MessageChatMemoryAdvisor //дефолтный класс стартера spring ai
            .builder(getChatMemory())
            .order(1) //для определиня порядка в цепочке вызова advisors
            .build();
    }

    //сервис по управлению историй сообщений чата с LLM
    private ChatMemory getChatMemory() {
       return PostgresChatMemory.builder()
                .maxMessages(12)
                .chatMemoryRepository(chatRepository) //свой кастомный JPA-репозиторий
                .build();
    }

//пример вызова промпта
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoWebinar1NoReactApplication.class, args);
        ChatClient chatClient = context.getBean(ChatClient.class);
        System.out.println(chatClient.prompt().user("Дай первую строчку Bohemian Rhapsody").call().content()); //call() - блокир. метод
    }
```

</details>

### Режим стриминга
---

`SSE` - server send event - события(месседжи), которые отдаются на эндпоинте, как в трубу, по одному  

REST-контроллеры будут продюсировать MediaType.TEXT_EVENT_STREAM_VALUE и возвращать `SseEmitter`   
```java
    @GetMapping(value = "/chat-stream/{chatId}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter talkToModel(@PathVariable Long chatId, @RequestParam String userPrompt){
        return chatService.proceedInteractionWithStreaming(chatId,userPrompt);
    }
```

Как работать с `SseEmitter` на сервисном уровне   
<details>  
<summary>Click to expand!</summary>  

```java
    public SseEmitter proceedInteractionWithStreaming(Long chatId, String userPrompt) {
        myProxy.addChatEntry(chatId, userPrompt, USER); //УБРАТЬ, если есть вызов метода .advisor(), т.к. он сам сохранит чат (если сохраняет чат, а не его сообщение)

        //создаем объект Sse
        SseEmitter sseEmitter = new SseEmitter(0L);
        //для конкатенации полного ответа из режима стрима
        final StringBuilder answer = new StringBuilder();

        chatClient
                .prompt(userPrompt)
                .stream()
                .chatResponse()
                .subscribe(
                        //что сделать с очередным полученным токеном/месседжем
                        (ChatResponse response) -> {        
                            var token = response.getResult().getOutput();
                            emitter.send(token);
                            answer.append(token.getText());
                        },
                        //что сделать в случае ошибки
                        sseEmitter::completeWithError,
                        //что сделать, когда LLM отдаст весь ответ, закончив отвечать на prompt. УБРАТЬ логику сохранения, если есть вызов метода .advisor(), т.к. он сам сохранит сообщение
                        () -> {        
                            Chat chat = chatRepo.findById(chatId).orElseThrow();
                            chat.addChatEntry(ChatEntry.builder().content(prompt).role(role).build());
                            sseEmitter::complete; //обязательно закрывать соединение эммитера (эту строку не удалять даже при использовании advisor)
                        }
                    );
        return sseEmitter;
    }
```

</details>

### Context Injection (Учет контекста диалога при ответе LLM)
---

Чтобы при ответе LLM на каждый отдельный prompt учитывалась история диалога, нужно:
```java
//code...
        chatClient
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId)) //при использовании advisors, обязательно он должен знать id чата, историю которого необходимо учитывать в диалоге с LLM
//code...
```

Пример имплементации интерфейса ChatMemory для созданя advisor-a для учета истории переписки в чате с LLM
<details>  
<summary>Click to expand!</summary>  

```java
@Builder
public class PostgresChatMemory implements ChatMemory {

    private ChatRepository chatMemoryRepository;

    private int maxMessages;

    @Override
    public void add(String conversationId, List<Message> messages) { //у объекта Message есть поле messageType, определяющее чье это было сообщение (USER/ASSISTANT/SYSTEM)
        Chat chat = chatMemoryRepository.findById(Long.valueOf(conversationId)).orElseThrow();
        for (Message message : messages) {
            chat.addChatEntry(ChatEntry.toChatEntry(message));
        }
        chatMemoryRepository.save(chat);

    }

    @Override
    public List<Message> get(String conversationId) {
        Chat chat = chatMemoryRepository.findById(Long.valueOf(conversationId)).orElseThrow();
        long messagesToSkip = Math.max(0, chat.getHistory().size() - maxMessages); //если сообщений в истории меньше, чем max - то не скипать в стриме ниже, то есть 0
        return chat.getHistory().stream()
                .skip(maxMessages) //чтобы взять последние n сообщений в последовательном списке
                .sorted(Comparator.comparing(ChatEntry::getCreatedAt))
                .map(ChatEntry::toMessage)
                .toList();
    }

    @Override
    public void clear(String conversationId) {
        //not implemented
    }
}
```

</details>

### RAG (Retrieval-Augmented Generation)
---

`RAG` (Retrieval-Augmented Generation) — это методология обработки естественного языка, применяемая в системах искусственного интеллекта, 
где традиционная модель генерирует текст, дополненный информацией извне, извлечённой из внешних источников через механизм поиска релевантной информации («retrieval»). 
Основная цель RAG заключается в повышении качества и точности моделей ИИ путём дополнения их знаний внешними источниками информации.

`Основные компоненты RAG`:

- Чанкинг: Сообщение разбивается на небольшие фрагменты (чанки), чтобы облегчить обработку и хранение.  
- Векторизация: Чанки преобразуются в векторные представления (векторы).  
- Хранение векторов: Вектора сохраняются в специализированную базу данных (например, Pinecone, Weaviate, Qdrant и др.).  
- Поиск схожих фрагментов: Когда поступает новый запрос от пользователя, система ищет наиболее похожие вектора среди сохранённых ранее.  
- Генерация текста: Модель дополняет свою генерацию результатами найденных фрагментов.  

Зависимости для работы с рагом:
```xml
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-starter-vector-store-pgvector</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-advisors-vector-store</artifactId> --здесь лежит класс VectorStore, который из стартера выше строит бин
        </dependency>
```

> для корректной работы RAG, требуется таблица vector_store (для нее Entity писать НЕ НУЖНО)

```sql
-- Таблица для векторного хранилища. ОБЯЗАТЕЛЬНАЯ
CREATE TABLE IF NOT EXISTS vector_store (
                                            id        VARCHAR(255) PRIMARY KEY,
    content   TEXT,
    metadata  JSON,
    embedding VECTOR(1024) --массив float (float[]). VECTOR тип поддерживается в расширении pgvector
    );

-- Индекс HNSW для быстрого векторного поиска
CREATE INDEX IF NOT EXISTS vector_store_hnsw_index
    ON vector_store USING hnsw (embedding vector_cosine_ops);

-- =============================================
-- Комментарии по типам индексов:
-- =============================================
-- HNSW (Hierarchical Navigable Small World) - эффективный для высокоразмерных векторов
-- IVFFlat - Inverted File с плоским хранением кластеров, хорош для больших объемов данных
-- Без индекса - прямой перебор всех векторов (медленно, но точно)
```

> переименовать таблицу vectore_store можно через: spring.ai.vectorstore.pgvector.table-name=

Вспомогательная таблица для удобного пополнения RAG информацией из локальной базы данных/википедии в виде файлов:  
```sql
-- Таблица загруженных документов
CREATE TABLE IF NOT EXISTS loaded_document (
    id            SERIAL PRIMARY KEY,
    filename      VARCHAR(255) NOT NULL,
    content_hash  VARCHAR(64) NOT NULL,
    document_type VARCHAR(10) NOT NULL,
    chunk_count   INTEGER,
    loaded_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT unique_document UNIQUE (filename, content_hash)
    );

-- Индекс для быстрого поиска по имени файла
CREATE INDEX IF NOT EXISTS idx_loaded_documents_filename
    ON loaded_document(filename);
```

Репо для работы с таблицей загруженных документов
```java
public interface DocumentRepository extends JpaRepository<LoadedDocument, Long> {
    //для поиска уникальных записей имя файла-его хэш
    boolean existsByFilenameAndContentHash(String filename, String contentHash);
}
```

`Сервис для работы с документами и нарезания их на чанки`
<details>  
<summary>Click to expand!</summary>  

```java
@Service
public class DocumentLoaderService implements CommandLineRunner {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ResourcePatternResolver resolver;

    @Autowired
    private VectorStore vectorStore;


    @SneakyThrows
    public void loadDocuments() {
        List<Resource> resources = Arrays.stream(resolver.getResources("classpath:/knowledgebase/**/*.txt")).toList();

        resources.stream()
                .map(resource -> Pair.of(resource, calcContentHash(resource)))
                .filter(pair -> !documentRepository.existsByFilenameAndContentHash(pair.getFirst().getFilename(), pair.getSecond()))
                .forEach(pair -> {
                    Resource resource = pair.getFirst();
                    List<Document> documents = new TextReader(resource).get();
                    TokenTextSplitter textSplitter = TokenTextSplitter.builder()
                        .withChunkSize(200) //в соответствии с докой, размер чанков должен быть от 200 до 800. Но если чанк подходит, придется брать весь, а он может не опдходить по смыслу в других частях текста, поэтому 200
                        .build();
                    List<Document> chunks = textSplitter.apply(documents);
                    vectorStore.accept(chunks);

                    LoadedDocument loadedDocument = LoadedDocument.builder()
                            .documentType("txt")
                            .chunkCount(chunks.size())
                            .filename(resource.getFilename())
                            .contentHash(pair.getSecond())
                            .build();
                    documentRepository.save(loadedDocument);

                });


    }

    //метод для определения хэша файла
    @SneakyThrows
    private String calcContentHash(Resource resource) {
        return DigestUtils.md5DigestAsHex(resource.getInputStream());
    }

    //запуск загрузки документов при перезапуске приложения
    @Override
    public void run(String... args) throws Exception {
        loadDocuments();
    }
}
```

</details>

### Написание своих кастомных Advisors  
---

> при написании своих Advisors, имплементировать интерфейс BaseAdvisor - для потокового ответа, а также блокирующего вызова LLM

> Advisors в ChatClient вызываются по паттерну Chain of Responsibilities (за счет метода эдвайзера before и after), а **порядок их вызова** определяет метод order()!

`Кастомный advisor`
<details>  
<summary>Click to expand!</summary>  

```java
@Builder
public class ExpansionQueryAdvisor implements BaseAdvisor {

    private static final PromptTemplate template = PromptTemplate.builder()
            .template("""
                Instruction: Расширь поисковый запрос, добавив наиболее релевантные термины.
                
                СПЕЦИАЛИЗАЦИЯ ПО SPRING FRAMEWORK:
                - Жизненный цикл Spring бинов: конструктор → BeanPostProcessor → PostConstruct → прокси → ContextListener
                - Технологии: Dynamic Proxy, CGLib, reflection, аннотации, XML конфигурация
                - Компоненты: BeanFactory, ApplicationContext, BeanDefinition, MBean, JMX
                - Паттерны: dependency injection, AOP, профилирование, перехват методов

                ПРАВИЛА:
                1. Сохрани ВСЕ слова из исходного вопроса
                2. Добавь МАКСИМУМ ПЯТЬ наиболее важных термина
                3. Выбирай самые специфичные и релевантные слова
                4. Результат - простой список слов через пробел

                СТРАТЕГИЯ ВЫБОРА:
                - Приоритет: специализированные термины
                - Избегай общих слов
                - Фокусируйся на ключевых понятиях

                ПРИМЕРЫ:
                "что такое спринг" → "что такое спринг фреймворк Java"
                "как создать файл" → "как создать файл документ программа"

                Question: {question}
                Expanded query:
                """).build();


    public static final String ENRICHED_QUESTION = "ENRICHED_QUESTION";
    public static final String ORIGINAL_QUESTION = "ORIGINAL_QUESTION";
    public static final String EXPANSION_RATIO = "EXPANSION_RATIO";

//в цепочке advisors будем делать доп. вызов LLM
    private ChatClient chatClient;

    public static ExpansionQueryAdvisorBuilder builder(ChatModel chatModel) {
        return new ExpansionQueryAdvisorBuilder().chatClient(ChatClient.builder(chatModel)
                .defaultOptions(OllamaOptions.builder()
                    .temperature(0.0) //исключить вариативность ответа
                    .topK(1) //взять первый вариант
                    .topP(0.1) //практически любой вариант подойдет
                    .repeatPenalty(1.0) //не штрафовать за повторение
                    .build()) //использует свой кастомный ChatClient
                .build());
    }

    @Getter
    @NonNull
    private final int order;

    @Override
    public ChatClientRequest before(ChatClientRequest chatClientRequest, AdvisorChain advisorChain) {

        String userQuestion = chatClientRequest.prompt().getUserMessage().getText(); //оригинальный запрос пользователя
        String enrichedQuestion = chatClient //обогащенный/измененный запрос
                .prompt()
                .user(template.render(Map.of("question", userQuestion)))
                .call()
                .content();

        double ratio = enrichedQuestion.length() / (double) userQuestion.length(); //насколько расширился обогащенный LLM запрос, относительно исходного промпта

        return chatClientRequest.mutate() //обязательно для изменения
                .context(ORIGINAL_QUESTION, userQuestion) //оригинальный запрос
                .context(ENRICHED_QUESTION, enrichedQuestion) //расширенный запрос
                .context(EXPANSION_RATIO, ratio) // насколько увеличилось количество токенов в расширенной версии запроса
                .build();
    }

    @Override
    public ChatClientResponse after(ChatClientResponse chatClientResponse, AdvisorChain advisorChain) {
        //без какой-либо доп. логики
        return chatClientResponse;
    }

//для определения порядка вызова в цепочке advisors
    @Override
    public int getOrder() {
        return order;
    }
}
```

</details>

Context - это мапа со значениями. Из нее advisor-ы работают для формирования окончательного Prompt. `Мапа контекста не попадает в LLM`  

```java
chatClientRequest.mutate().context() // для обогащения информации для ответа по UserMessage от LLM  
chatClientRequest.mutate() // для изменения иммутабельного сообщения пользователя
chatClientRequest.prompt().augmentUserMessage(extendedMessage) //заменить сообщение пользователя
```

`Кастомный RAG-advistor`
<details>  
<summary>Click to expand!</summary>  

```java
@Builder
public class RagAdvisor implements BaseAdvisor {
    public static final String ENRICHED_QUESTION = "ENRICHED_QUESTION";

    @Builder.Default
    private static final PromptTemplate template = PromptTemplate.builder().template("""
            CONTEXT: {context}
            Question: {question}
            """).build();



    private VectorStore vectorStore;

    @Builder.Default
    private SearchRequest searchRequest = SearchRequest.builder().topK(4).similarityThreshold(0.62).build();

    @Getter
    private final int order;

    public static RagAdvisorBuilder build(VectorStore vectorStore) {
        return new RagAdvisorBuilder().vectorStore(vectorStore);
    }

    @Override
    public ChatClientRequest before(ChatClientRequest chatClientRequest, AdvisorChain advisorChain) {
        String originalUserQuestion = chatClientRequest.prompt().getUserMessage().getText();
        String queryToRag = chatClientRequest.context().getOrDefault(ENRICHED_QUESTION, originalUserQuestion).toString(); //получить из контекста обогащенный запрос, либо использовать оригинальный пользовательский

        List<Document> documents = vectorStore.similaritySearch(SearchRequest
                .from(searchRequest) //from - для перезадавливания только тех параметров SearchRequest, которые необходимо
                .query(queryToRag)
                .topK(searchRequest.getTopK()*2) //*2 для того, чтобы захватить доп. документы для анализа реранкером
                .build()); //список чанков 

        if (documents == null || documents.isEmpty()) {
            return chatClientRequest.mutate().context("CONTEXT","ТУТ ПУСТО - ни один документ моя собачка не обнаружила").build();
        }

        //реранкер. Токенизирует все слова. Проверяет, подходит ли чанк с точки зрения смысловой нагрузки, относительно промпта
        BM25RerankEngine rerankEngine = BM25RerankEngine.builder().build();

        documents = rerankEngine.rerank(documents, queryToRag, searchRequest.getTopK()); //вернет количество элементов до topK, как и требовалось изначально

        String llmContext = documents.stream().map(Document::getText).collect(Collectors.joining(System.lineSeparator())); //текст чанков, разделенный новой строкой

        String finalUserPrompt = template.render( //засетить значения в шаблон промпта 
                Map.of("context", llmContext, "question", originalUserQuestion)
        );


        return chatClientRequest.mutate().prompt(chatClientRequest.prompt().augmentUserMessage(finalUserPrompt)).build();
    }

    @Override
    public ChatClientResponse after(ChatClientResponse chatClientResponse, AdvisorChain advisorChain) {
        //без доп логики
        return chatClientResponse;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
``` 

</details> 

`Реранкер`

<details>  
<summary>Click to expand!</summary>  

```java
package org.jeka.demowebinar1no_react.advisors.rag;


import com.github.pemistahl.lingua.api.Language;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import lombok.Builder;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.ai.document.Document;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Builder
public class BM25RerankEngine {
    @Builder.Default
    private static final LanguageDetector languageDetector = LanguageDetectorBuilder
            .fromLanguages(Language.ENGLISH, Language.RUSSIAN)
            .build();

    // BM25 parameters
    @Builder.Default
    private final double K = 1.2;
    @Builder.Default
    private final double B = 0.75;

    public List<Document> rerank(List<Document> corpus, String query, int limit) {

        if (corpus == null || corpus.isEmpty()) {
            return new ArrayList<>();
        }

        // Compute corpus statistics
        CorpusStats stats = computeCorpusStats(corpus);

        // Tokenize query
        List<String> queryTerms = tokenize(query);

        // Score and sort documents
        return corpus.stream()
                .sorted((d1, d2) -> Double.compare(
                        score(queryTerms, d2, stats),
                        score(queryTerms, d1, stats)
                ))
                .limit(limit)
                .collect(Collectors.toList());
    }

    private CorpusStats computeCorpusStats(List<Document> corpus) {
        Map<String, Integer> docFreq = new HashMap<>();
        Map<Document, List<String>> tokenizedDocs = new HashMap<>();
        int totalLength = 0;
        int totalDocs = corpus.size();

        // Process each document
        for (Document doc : corpus) {
            List<String> tokens = tokenize(doc.getText());
            tokenizedDocs.put(doc, tokens);
            totalLength += tokens.size();

            // Update document frequencies
            Set<String> uniqueTerms = new HashSet<>(tokens);
            for (String term : uniqueTerms) {
                docFreq.put(term, docFreq.getOrDefault(term, 0) + 1);
            }
        }

        double avgDocLength = (double) totalLength / totalDocs;

        return new CorpusStats(docFreq, tokenizedDocs, avgDocLength, totalDocs);
    }

    private double score(List<String> queryTerms, Document doc, CorpusStats stats) {
        List<String> tokens = stats.tokenizedDocs.get(doc);
        if (tokens == null) {
            return 0.0;
        }

        // Calculate term frequencies for this document
        Map<String, Integer> tfMap = new HashMap<>();
        for (String token : tokens) {
            tfMap.put(token, tfMap.getOrDefault(token, 0) + 1);
        }

        int docLength = tokens.size();
        double score = 0.0;

        // Calculate BM25 score
        for (String term : queryTerms) {
            int tf = tfMap.getOrDefault(term, 0); //просто его count - то есть этого влияет на его вес в документе
            int df = stats.docFreq.getOrDefault(term, 1);

            // BM25 IDF calculation редкость слова - оно поднимает
            double idf = Math.log(1 + (stats.totalDocs - df + 0.5) / (df + 0.5));

            // BM25 term score calculation
            double numerator = tf * (K + 1);
            double denominator = tf + K * (1 - B + B * docLength / stats.avgDocLength);
            score += idf * (numerator / denominator);
        }

        return score;
    }

    private List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        Analyzer analyzer = detectLanguageAnalyzer(text);

        try (TokenStream stream = analyzer.tokenStream(null, text)) {
            stream.reset();
            while (stream.incrementToken()) {
                tokens.add(stream.getAttribute(CharTermAttribute.class).toString());
            }
            stream.end();
        } catch (IOException e) {
            throw new RuntimeException("Tokenization failed", e);
        }

        return tokens;
    }

    //поддержка исключительно русского и анлийского языков
    private Analyzer detectLanguageAnalyzer(String text) {
        Language lang = languageDetector.detectLanguageOf(text);
        if (lang == Language.ENGLISH) {
            return new EnglishAnalyzer();
        } else if (lang == Language.RUSSIAN) {
            return new RussianAnalyzer();
        } else {
            // Fallback to English analyzer for unsupported languages
            return new EnglishAnalyzer();
        }
    }

    // Inner class to hold corpus statistics
    private class CorpusStats {
        final Map<String, Integer> docFreq; //токен - сколько раз он встречается
        final Map<Document, List<String>> tokenizedDocs;
        final double avgDocLength;
        final int totalDocs;

        CorpusStats(Map<String, Integer> docFreq,
                    Map<Document, List<String>> tokenizedDocs,
                    double avgDocLength,
                    int totalDocs) {
            this.docFreq = docFreq;
            this.tokenizedDocs = tokenizedDocs;
            this.avgDocLength = avgDocLength;
            this.totalDocs = totalDocs;
        }
    }

}
```

</details>
