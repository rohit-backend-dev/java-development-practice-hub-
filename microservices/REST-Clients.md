# Ultimate Guide to REST Clients in Spring: RestClient, WebClient, RestTemplate, and HTTP Interface

---

## Introduction

REST clients are critical in modern Java development for consuming HTTP APIs—whether for microservices, integrating with external systems, or connecting legacy backends. Spring offers several powerful HTTP clients, each suited to different needs:

- **RestClient**: Modern, synchronous, fluent API (Spring 6.1+, Java 17+)
- **WebClient**: Non-blocking, reactive, and highly concurrent
- **RestTemplate**: Synchronous, legacy template API
- **HTTP Interface**: Annotated Java interfaces with generated proxies (works with any underlying client, powered by `HttpServiceProxyFactory` from Spring 6+)

This guide provides a practical, example-driven overview of each, making it easy to choose and use the right client for your scenario.

---

## Quick Comparison Table

| Scenario                                | Recommended Client      |
|------------------------------------------|------------------------|
| Blocking, synchronous calls              | RestClient             |
| Reactive, async, high concurrency        | WebClient              |
| Legacy codebase                          | RestTemplate           |
| Strongly-typed, interface-driven clients | HTTP Interface + Adapter|

> **Tip:** Use **RestClient** for most new blocking code, and **WebClient** for reactive/asynchronous flows.

---

## 1. RestClient (Modern Synchronous Client)

### **Highlights**

- Synchronous, thread-safe, and reusable
- Fluent, chainable API (`.get().uri().header()...`)
- Pluggable HTTP libraries (Apache, Jetty, Java built-in, etc.)
- Automatic message conversion (JSON, XML, etc.)
- Easy error handling
- Supports multipart, custom headers/cookies, interceptors
- **Available since Spring Framework 6.1+; requires Java 17+**

### **Creating and Configuring RestClient**

```java
// Default configuration
RestClient restClient = RestClient.create();

// Custom configuration
RestClient customClient = RestClient.builder()
    .baseUrl("https://api.example.com")
    .defaultHeader("Authorization", "Bearer <token>")
    .defaultCookie("sessionId", "xyz")
    .build();
```

#### As a Spring Bean

```java
@Configuration
public class RestClientConfig {
    @Value("${external.api.base-url}")
    private String baseUrl;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
```

#### Advanced Customization

```java
RestClient advancedClient = RestClient.builder()
    .requestFactory(new HttpComponentsClientHttpRequestFactory()) // use Apache HTTP client
    .messageConverters(converters -> converters.add(new MyCustomMessageConverter()))
    .defaultUriVariables(Map.of("variable", "foo"))
    .defaultHeader("My-Header", "Foo")
    .defaultCookie("My-Cookie", "Bar")
    .requestInterceptor(myCustomInterceptor)
    .requestInitializer(myCustomInitializer)
    .build();
```

---

### **Timeouts and Connection Pooling**

#### **Using Apache HttpClient (for RestClient or RestTemplate)**

```java
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.config.RequestConfig;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

CloseableHttpClient httpClient = HttpClients.custom()
    .setDefaultRequestConfig(RequestConfig.custom()
        .setConnectTimeout(5000) // ms
        .setSocketTimeout(5000)
        .build())
    .build();

RestClient client = RestClient.builder()
    .requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient))
    .build();
```

---

### **Logging Requests and Responses**

You can log HTTP traffic for debugging using a custom `ClientHttpRequestInterceptor`:

```java
public class LoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        // Log request details
        System.out.println("Request: " + request.getMethod() + " " + request.getURI());
        ClientHttpResponse response = execution.execute(request, body);
        // Log response details
        System.out.println("Response: " + response.getStatusCode());
        return response;
    }
}

// Register it
RestClient.builder()
    .requestInterceptor(new LoggingInterceptor())
    .build();
```

---

### **Making Requests with RestClient**

#### GET Request

```java
String result = restClient.get()
    .uri("/orders/{id}", 42)
    .accept(MediaType.APPLICATION_JSON)
    .retrieve()
    .body(String.class);
```

#### POST Request with Body

```java
Order newOrder = ...;
Order created = restClient.post()
    .uri("/orders")
    .body(newOrder)
    .retrieve()
    .body(Order.class);
```

#### Using ParameterizedTypeReference (for generics)

```java
ApiResponse<List<EmployeeDto>> response = restClient.get()
    .uri("/employees")
    .retrieve()
    .body(new ParameterizedTypeReference<>() {});
List<EmployeeDto> employees = response.getData();
```

#### Custom Headers and Cookies

```java
restClient.get()
    .uri("/secure")
    .header("X-Custom-Header", "value")
    .cookie("session_id", "abc123")
    .retrieve()
    .body(String.class);
```

#### Accessing Full Response (status, headers, body)

```java
ResponseEntity<String> result = restClient.get()
    .uri("/orders/42")
    .retrieve()
    .toEntity(String.class);

System.out.println(result.getStatusCode());
System.out.println(result.getHeaders());
System.out.println(result.getBody());
```

### **Error Handling**

- By default, throws `RestClientException` for 4xx/5xx responses.
- Customize with `.onStatus(...)` or global handler.

```java
restClient.get()
    .uri("/not-found")
    .retrieve()
    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
        throw new CustomNotFoundException("Not found: " + res.getStatusCode());
    })
    .body(String.class);
```

#### Global Handler

```java
RestClient.builder()
    .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
        throw new RuntimeException("Server Error!");
    })
    .build();
```

---

### **Retry and Resilience**

For retry logic, integrate with **Spring Retry** or **Resilience4j**:

```java
@Retryable(
    value = { IOException.class },
    maxAttempts = 3,
    backoff = @Backoff(delay = 2000)
)
public EmployeeDto getEmployeeById(Long id) {
    return restClient.get()...
}
```

---

### **Security Integration**

#### **Basic Auth / API Key**

```java
RestClient.builder()
    .defaultHeader(HttpHeaders.AUTHORIZATION, 
        "Basic " + Base64.getEncoder().encodeToString("user:pass".getBytes()))
    // For API Key
    .defaultHeader("x-api-key", "<your-api-key>")
    .build();
```

#### **OAuth2 with WebClient**

```java
@Bean
public WebClient webClient(ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository auth) {
    ServletOAuth2AuthorizedClientExchangeFilterFunction oauth = 
        new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, auth);
    return WebClient.builder().apply(oauth.oauth2Configuration()).build();
}
```

---

### **Multipart/Form Data**

```java
MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
parts.add("textPart", "value");
parts.add("filePart", new FileSystemResource("/path/to/file.png"));

restClient.post()
    .uri("/upload")
    .body(parts)
    .retrieve()
    .toBodilessEntity();
```

---

### **Exchange Method for Advanced Scenarios**

> Inject or instantiate `ObjectMapper` as needed (`ObjectMapper objectMapper = new ObjectMapper()`).

```java
restClient.get()
    .uri("/pets/{id}", id)
    .exchange((request, response) -> {
        if (response.getStatusCode().is4xxClientError()) {
            throw new NotFoundException();
        }
        // ObjectMapper should be injected or created
        return objectMapper.readValue(response.getBody(), Pet.class);
    });
```

---

## 2. WebClient (Reactive & Non-blocking Client)

### **Highlights**

- Asynchronous and non-blocking (but can be used synchronously)
- Supports backpressure, streaming, and high concurrency
- Fluent, lambda-friendly API
- Works with Mono/Flux (Project Reactor)
- Fully supports HTTP/2, cookies, multipart, headers, etc.

### **Creating and Configuring WebClient**

```java
// Default WebClient
WebClient webClient = WebClient.create();

// With base URL and default headers
WebClient client = WebClient.builder()
    .baseUrl("https://api.example.com")
    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    .build();
```

#### As a Spring Bean

```java
@Configuration
public class WebClientConfig {
    @Value("${external.api.base-url}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
```

---

### **Timeouts and Connection Pooling (Reactor Netty)**

```java
import reactor.netty.http.client.HttpClient;
import io.netty.channel.ChannelOption;
import java.time.Duration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

ReactorClientHttpConnector connector = new ReactorClientHttpConnector(
    HttpClient.create()
        .responseTimeout(Duration.ofSeconds(5))
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
);

WebClient client = WebClient.builder()
    .clientConnector(connector)
    .build();
```

---

### **Making Requests with WebClient**

#### GET Request (reactive)

```java
Mono<String> result = webClient.get()
    .uri("/orders/{id}", 42)
    .retrieve()
    .bodyToMono(String.class);

// Blocking (not recommended in reactive apps!)
String order = result.block();
```

#### POST Request

```java
Order newOrder = ...;
Mono<Order> created = webClient.post()
    .uri("/orders")
    .bodyValue(newOrder)
    .retrieve()
    .bodyToMono(Order.class);
```

#### Streaming (Flux)

```java
Flux<Order> orderStream = webClient.get()
    .uri("/orders/stream")
    .retrieve()
    .bodyToFlux(Order.class);
```

#### Server-Sent Events (SSE)

```java
Flux<ServerSentEvent<String>> eventStream = webClient.get()
    .uri("/sse")
    .accept(MediaType.TEXT_EVENT_STREAM)
    .retrieve()
    .bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {});
```

#### Custom Headers, Cookies, Query Params

```java
webClient.get()
    .uri(uriBuilder -> uriBuilder
        .path("/search")
        .queryParam("q", "laptop")
        .build())
    .header("X-Search", "value")
    .cookie("session_id", "abc123")
    .retrieve()
    .bodyToMono(String.class);
```

### **Error Handling**

```java
webClient.get()
    .uri("/not-found")
    .retrieve()
    .onStatus(HttpStatus::is4xxClientError, response ->
        response.bodyToMono(String.class)
            .flatMap(errorBody -> Mono.error(new NotFoundException(errorBody)))
    )
    .bodyToMono(String.class);
```

#### Global Handler

```java
WebClient.builder()
    .defaultStatusHandler(HttpStatus::isError, resp -> {
        // custom logic here
        return Mono.error(new RuntimeException("HTTP Error"));
    })
    .build();
```

---

### **Retry and Resilience**

Use **reactor-retry**, **resilience4j**, or **Spring Retry** with reactive support:

```java
webClient.get()
    .uri("/unstable-endpoint")
    .retrieve()
    .bodyToMono(String.class)
    .retry(3); // Simple retry, or see resilience4j/reactor-retry for advanced scenarios
```

---

### **Security Integration**

#### **OAuth2 (Reactive)**

```java
@Bean
public WebClient webClient(ReactiveClientRegistrationRepository clients, ServerOAuth2AuthorizedClientRepository auth) {
    ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = 
        new ServerOAuth2AuthorizedClientExchangeFilterFunction(clients, auth);
    return WebClient.builder().apply(oauth.oauth2Configuration()).build();
}
```

---

### **Multipart/Form Data**

```java
MultipartBodyBuilder builder = new MultipartBodyBuilder();
builder.part("file", new FileSystemResource("/path/to/file.png"));
builder.part("description", "A file upload");

webClient.post()
    .uri("/upload")
    .contentType(MediaType.MULTIPART_FORM_DATA)
    .body(BodyInserters.fromMultipartData(builder.build()))
    .retrieve()
    .bodyToMono(Void.class);
```

---

### **Testability Best Practices**

- **Mocking**: Use `WebClient`'s `ExchangeFunction` or [WebClientTest](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#webtestclient)
- **WireMock**: Simulate external HTTP endpoints for integration tests ([WireMock docs](http://wiremock.org/))
- **MockWebServer**: For simple HTTP server stubs (from OkHttp)
- **RestClient**: Can be wrapped or injected with test stubs/mocks

---

## 3. RestTemplate (Legacy Synchronous Client)

### **Highlights**

- Synchronous, template-method API
- Easy for simple, blocking HTTP requests
- Deprecated for new development (use RestClient)
- Still widely used in legacy codebases

### **Common Methods and Migration to RestClient**

| RestTemplate                                  | RestClient Equivalent                                     |
|-----------------------------------------------|-----------------------------------------------------------|
| getForObject("/uri", Class)                   | get().uri("/uri").retrieve().body(Class)                  |
| postForEntity("/uri", obj, Class)             | post().uri("/uri").body(obj).retrieve().toEntity(Class)   |
| put("/uri", obj)                              | put().uri("/uri").body(obj).retrieve().toBodilessEntity() |
| delete("/uri")                                | delete().uri("/uri").retrieve().toBodilessEntity()        |
| exchange(..., HttpMethod.POST, ...)           | method(HttpMethod.POST)...retrieve().toEntity(Class)      |

> See the full migration table in the [Spring docs](https://spring.io/blog/2023/10/05/restclient-in-spring-framework-6-1).

---

## 4. HTTP Interface Proxies (Strongly-Typed, Annotated Interfaces)

### **What is an HTTP Interface?**

- Java interface annotated with `@HttpExchange`, `@GetExchange`, etc.
- Proxy is generated to handle HTTP calls based on method annotations.
- Works with RestClient, WebClient, or RestTemplate (via adapter).
- Powered by **HttpServiceProxyFactory** from Spring 6+.

### **Example Interface**

```java
public interface RepoService {
    @GetExchange("/repos/{owner}/{repo}")
    Repo getRepo(@PathVariable String owner, @PathVariable String repo);
}
```

### **Creating a Proxy (with RestClient)**

```java
RestClient restClient = RestClient.builder().baseUrl("https://api.github.com/").build();
RestClientAdapter adapter = RestClientAdapter.create(restClient);
HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
RepoService service = factory.createClient(RepoService.class);

Repo repo = service.getRepo("spring-projects", "spring-framework");
```

### **Supported Parameter Annotations**

- `@PathVariable`, `@RequestParam`, `@RequestHeader`, `@RequestBody`, `@CookieValue`, `@RequestPart`, `@RequestAttribute`
- Flexible method signatures, supports custom argument resolvers

### **Custom Argument Resolver Example**

For complex parameter mapping, implement `HttpServiceArgumentResolver` and register it with your factory.

---

## 5. Client Request Factories

RestClient (and RestTemplate) can use different underlying HTTP libraries:

- `JdkClientHttpRequestFactory` – Java’s built-in HttpClient
- `HttpComponentsClientHttpRequestFactory` – Apache HttpClient
- `JettyClientHttpRequestFactory` – Jetty HttpClient
- `ReactorNettyClientRequestFactory` – Reactor Netty
- `SimpleClientHttpRequestFactory` – fallback/default

Configure via `.requestFactory(...)` on the builder.

---

## 6. Error Handling Best Practices

- Use `.onStatus(...)` (RestClient/WebClient) for per-request error handling.
- Configure global handlers for consistent error processing.
- Log error responses for troubleshooting.
- Define custom exceptions for meaningful errors.

---

## 7. Sample: REST Client Integration

Suppose you have two services:

- `prod_ready_features` (port 9090)
- `employees` (port 8080)

**application.properties** (for prod_ready_features):

```properties
employeeService.base.url=http://localhost:8080
```

**RestClientConfig.java**

```java
@Configuration
public class RestClientConfig {
    @Value("${employeeService.base.url}")
    private String baseUrl;

    @Bean
    public RestClient employeeRestClient() {
        return RestClient.builder()
            .baseUrl(baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }
}
```

**EmployeeClient.java**

```java
@Service
public class EmployeeClient {
    private final RestClient restClient;

    public EmployeeClient(@Qualifier("employeeRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    public EmployeeDto getEmployeeById(Long id) {
        ApiResponse<EmployeeDto> response = restClient.get()
            .uri("/employees/{id}", id)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
        return response.getData();
    }

    public List<EmployeeDto> getAllEmployees() {
        ApiResponse<List<EmployeeDto>> response = restClient.get()
            .uri("/employees")
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
        return response.getData();
    }

    public EmployeeDto createNewEmployee(EmployeeDto input) {
        ApiResponse<EmployeeDto> response = restClient.post()
            .uri("/employees")
            .body(input)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});
        return response.getData();
    }
}
```

---

## 8. Testing with RestClient and WebClient

- **Mocking:** Use [MockWebServer](https://square.github.io/okhttp/features/mockwebserver/) or [WireMock](http://wiremock.org/) for HTTP stubs.
- **Spring Boot's @WebClientTest**: For slice tests with only WebClient beans.
- **Inject test stubs**: Use dependency injection to supply mock or test RestClient/WebClient beans in your test config.
- **Example with MockWebServer:**

```java
MockWebServer server = new MockWebServer();
server.enqueue(new MockResponse().setBody("{\"id\":1,\"name\":\"John\"}"));

RestClient client = RestClient.builder()
    .baseUrl(server.url("/").toString())
    .build();

String json = client.get().uri("/employees/1").retrieve().body(String.class);
```

---

## 9. Migration: RestTemplate to RestClient (Cheat Sheet)

| RestTemplate usage                        | RestClient equivalent                                   |
|-------------------------------------------|---------------------------------------------------------|
| getForObject(String, Class, Object...)    | get().uri(String, Object...).retrieve().body(Class)     |
| getForEntity(URI, Class)                  | get().uri(URI).retrieve().toEntity(Class)              |
| postForObject(String, Object, Class)      | post().uri(String).body(Object).retrieve().body(Class)  |
| put(URI, Object)                          | put().uri(URI).body(Object).retrieve().toBodilessEntity()|
| delete(String, Object...)                 | delete().uri(String, Object...).retrieve().toBodilessEntity()|
| exchange(String, HttpMethod, HttpEntity, Class, Object...) | method(HttpMethod).uri(String, Object...).headers(...).body(...).retrieve().toEntity(Class) |

---

## 10. Further Reading

- [Spring Framework Documentation: RestClient](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#rest-client)
- [Spring Framework Documentation: WebClient](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-client)
- [Migrating from RestTemplate to RestClient](https://spring.io/blog/2023/10/05/restclient-in-spring-framework-6-1)
- [HTTP Interface Proxies in Spring](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#rest-http-interface)
- [Spring Blog](https://spring.io/blog/)
