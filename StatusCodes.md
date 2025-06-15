# Essential HTTP Status Codes for Spring Boot Developers

HTTP status codes are issued by a server in response to a client's request made to the server. They are part of the HTTP/1.1 standard and provide clients with information about the result of their request. As a Spring Boot developer, understanding these codes is critical for building robust, RESTful APIs.

---
## HTTP Status Code Range

```
Informational responses (100 – 199)
Successful responses (200 – 299)
Redirection messages (300 – 399)
Client error responses (400 – 499)
Server error responses (500 – 599)
```
## 🏁 Quick Reference Table

| Code | Name                    | When to Use                                               | Spring Boot Usage Example                        |
|------|-------------------------|-----------------------------------------------------------|--------------------------------------------------|
| 🟢 **200**  | OK                      | Request succeeded (GET/PUT/DELETE/POST)                  | `return ResponseEntity.ok(body);`                |
| 🟢 **201**  | Created                 | New resource created (POST)                              | `return ResponseEntity.status(201).body(obj);`   |
| 🟢 **202**  | Accepted                | Request accepted, processing asynchronously              | `return ResponseEntity.accepted().build();`      |
| 🟢 **204**  | No Content              | Success, but nothing to return (DELETE/PUT)              | `return ResponseEntity.noContent().build();`     |
| 🟢 **206**  | Partial Content         | Part of resource returned (e.g., file download)          | `return ResponseEntity.status(206).body(part);`  |
| 🟢 **207**  | Multi-Status            | Multiple statuses for batch ops (WebDAV)                 | *Custom, rarely used*                            |
| 🟢 **208**  | Already Reported        | Results already reported (WebDAV, batch ops)             | *Custom, rarely used*                            |
| 🟢 **226**  | IM Used                 | Delta encoding applied                                   | *Rarely used*                                    |
| 🟠 **300**  | Multiple Choices        | Multiple representations available                       | `return ResponseEntity.status(300).build();`     |
| 🟠 **301**  | Moved Permanently       | Resource URL changed                                     | *Handled by Spring redirect*                     |
| 🟠 **302**  | Found                   | Temporary redirect                                       | *Handled by Spring redirect*                     |
| 🟠 **304**  | Not Modified            | Resource hasn’t changed (caching)                        | *Spring handles ETag responses*                  |
| 🟠 **307**  | Temporary Redirect      | Temporary redirect, method preserved                     | *Handled by Spring redirect*                     |
| 🟠 **308**  | Permanent Redirect      | Permanent redirect, method preserved                     | *Handled by Spring redirect*                     |
| 🟡 **400**  | Bad Request             | Client sent invalid data                                 | `throw new ResponseStatusException(400);`        |
| 🟡 **401**  | Unauthorized            | Authentication required/failed                           | `throw new ResponseStatusException(401);`        |
| 🟡 **402**  | Payment Required        | Payment needed (reserved, rarely used)                   | *Custom, rarely used*                            |
| 🟡 **403**  | Forbidden               | Authenticated but not allowed                            | `throw new ResponseStatusException(403);`        |
| 🟡 **404**  | Not Found               | Resource doesn’t exist                                   | `return ResponseEntity.notFound().build();`      |
| 🟡 **405**  | Method Not Allowed      | HTTP method not supported                                | *Handled by Spring automatically*                |
| 🟡 **406**  | Not Acceptable          | Cannot produce acceptable content type                   | *Handled by Spring automatically*                |
| 🟡 **408**  | Request Timeout         | Client took too long                                     | *Handled by server*                              |
| 🟡 **409**  | Conflict                | Data conflict (e.g., duplicate)                          | `throw new ResponseStatusException(409);`        |
| 🟡 **410**  | Gone                    | Resource permanently removed                             | `throw new ResponseStatusException(410);`        |
| 🟡 **411**  | Length Required         | Content-Length header missing                            | *Handled by server*                              |
| 🟡 **412**  | Precondition Failed     | Request preconditions not met                            | *Custom, rarely used*                            |
| 🟡 **413**  | Payload Too Large       | Request body too large                                   | *Handled by Spring automatically*                |
| 🟡 **414**  | URI Too Long            | URI is too long                                          | *Handled by server*                              |
| 🟡 **415**  | Unsupported Media Type  | Wrong `Content-Type` header                              | *Handled by Spring automatically*                |
| 🟡 **416**  | Range Not Satisfiable   | Requested range not available                            | *Spring handles for downloads*                   |
| 🟡 **417**  | Expectation Failed      | Expect header not met                                    | *Custom, rarely used*                            |
| 🟡 **418**  | I'm a Teapot            | April Fools joke (RFC 2324, not for real use)            | *Fun, rarely used*                               |
| 🟡 **422**  | Unprocessable Entity    | Data valid, but semantically wrong (validation error)    | `throw new ResponseStatusException(422);`        |
| 🟡 **423**  | Locked                  | Resource is locked (WebDAV)                              | *Custom, rarely used*                            |
| 🟡 **424**  | Failed Dependency       | Previous request failed (WebDAV)                         | *Custom, rarely used*                            |
| 🟡 **426**  | Upgrade Required        | Client must switch protocols                             | *Custom, rarely used*                            |
| 🟡 **428**  | Precondition Required   | Required condition missing                               | *Custom, rarely used*                            |
| 🟠 **429**  | Too Many Requests       | Rate limit exceeded                                      | `throw new ResponseStatusException(429);`        |
| 🟡 **431**  | Request Header Fields Too Large | Headers too large                              | *Custom, rarely used*                            |
| 🟡 **451**  | Unavailable For Legal Reasons   | Resource blocked by law                        | *Custom, rarely used*                            |
| 🔴 **500**  | Internal Server Error   | Server error (unexpected issue)                          | *Handled by Spring automatically*                |
| 🔴 **501**  | Not Implemented         | Endpoint/method not implemented                          | `throw new ResponseStatusException(501);`        |
| 🔴 **502**  | Bad Gateway             | Error from upstream server (proxy)                       | *Handled by server*                              |
| 🔴 **503**  | Service Unavailable     | Server down or overloaded                                | `throw new ResponseStatusException(503);`        |
| 🔴 **504**  | Gateway Timeout         | Upstream server timeout                                  | *Handled by server*                              |
| 🔴 **505**  | HTTP Version Not Supported | HTTP version not supported                          | *Custom, rarely used*                            |
| 🔴 **507**  | Insufficient Storage    | Server can’t store representation (WebDAV)               | *Custom, rarely used*                            |
| 🔴 **508**  | Loop Detected           | Infinite loop in processing (WebDAV)                     | *Custom, rarely used*                            |
| 🔴 **510**  | Not Extended            | More extensions required                                 | *Custom, rarely used*                            |
| 🔴 **511**  | Network Authentication Required | Network login required                         | *Custom, rarely used*                            |

---

## 1xx Informational
> **For protocol communication. Rarely used in typical APIs.**

- **100 Continue:**  
  Initial part of a request is OK, client can continue sending.
- **101 Switching Protocols:**  
  Server switches protocols (e.g., HTTP/2).
- **102 Processing (WebDAV):**  
  Request is being processed, no response yet.

---

## 2xx Success
> **Request was received, understood, and accepted.**

- **200 OK:**  
  Everything went well! (GET returns data, POST may return created data)
- **201 Created:**  
  New resource was created (POST).
- **202 Accepted:**  
  Request received, but processing not completed yet.
- **203 Non-Authoritative Information:**  
  Metadata may not be from origin server.
- **204 No Content:**  
  Success, but nothing to send back.
- **205 Reset Content:**  
  Success, client should reset the document view.
- **206 Partial Content:**  
  Only part of resource returned (e.g., file download).
- **207 Multi-Status (WebDAV):**  
  Multiple independent operations’ status.

---

## 3xx Redirection
> **Client must take additional action to complete request.**

- **300 Multiple Choices:**  
  Multiple options for resource available.
- **301 Moved Permanently:**  
  Resource moved (update your links!).
- **302 Found:**  
  Resource temporarily at a different URL.
- **303 See Other:**  
  Response found at another URI using GET.
- **304 Not Modified:**  
  Resource unchanged (use cache).
- **305 Use Proxy:**  
  Resource only available via proxy.
- **307 Temporary Redirect:**  
  Temporary redirect, use original method.
- **308 Permanent Redirect:**  
  Resource permanently moved, use original method.

---

## 4xx Client Errors
> **Client made a mistake (invalid request, unauthorized, etc).**

- **400 Bad Request:**  
  Bad input (malformed JSON, missing fields).
- **401 Unauthorized:**  
  Authentication needed or failed.
- **402 Payment Required:**  
  Reserved for future use.
- **403 Forbidden:**  
  Authenticated, but not permitted.
- **404 Not Found:**  
  Resource not found (wrong URL or entity).
- **405 Method Not Allowed:**  
  Method not supported for this resource.
- **406 Not Acceptable:**  
  Can't generate content matching accept headers.
- **407 Proxy Authentication Required:**  
  Client must authenticate with proxy.
- **408 Request Timeout:**  
  Server timed out waiting for request.
- **409 Conflict:**  
  Conflict (duplicate entry, concurrent edit).
- **410 Gone:**  
  Resource permanently gone.
- **411 Length Required:**  
  Content-Length header missing.
- **412 Precondition Failed:**  
  Precondition in headers not met.
- **413 Payload Too Large:**  
  Request too large for server.
- **414 URI Too Long:**  
  URI too long for server to process.
- **415 Unsupported Media Type:**  
  Wrong Content-Type (e.g., sent text/plain but expected JSON).
- **416 Range Not Satisfiable:**  
  Invalid range in request.
- **417 Expectation Failed:**  
  Cannot meet request expectations.
- **418 I'm a teapot:**  
  Joke code, not for real use.
- **422 Unprocessable Entity (WebDAV):**  
  Well-formed but semantically incorrect (business validation error).
- **423 Locked (WebDAV):**  
  Resource is locked.
- **424 Failed Dependency (WebDAV):**  
  Previous request failed.
- **426 Upgrade Required:**  
  Client must switch protocols.
- **428 Precondition Required:**  
  Request must be conditional.
- **429 Too Many Requests:**  
  Too many requests (rate limiting).
- **431 Request Header Fields Too Large:**  
  Headers are too large.
- **451 Unavailable For Legal Reasons:**  
  Resource blocked due to legal reasons.

---

## 5xx Server Errors
> **Server failed to handle a valid request (server-side issue).**

- **500 Internal Server Error:**  
  Generic error (unexpected exception).
- **501 Not Implemented:**  
  Server does not support the functionality.
- **502 Bad Gateway:**  
  Server received invalid response from upstream.
- **503 Service Unavailable:**  
  Server is down or overloaded.
- **504 Gateway Timeout:**  
  Upstream server timeout.
- **505 HTTP Version Not Supported:**  
  HTTP version not supported.
- **506 Variant Also Negotiates:**  
  Negotiation for content resulted in a loop.
- **507 Insufficient Storage (WebDAV):**  
  Server cannot store the representation.
- **508 Loop Detected (WebDAV):**  
  Infinite loop detected.
- **510 Not Extended:**  
  Further extensions required.
- **511 Network Authentication Required:**  
  Client must authenticate to network.

---

## Notes for Spring Boot Developers

- **Custom Responses:**  
  Set custom status codes using `@ResponseStatus(HttpStatus.CODE)` or `ResponseEntity`.
- **Exception Handling:**  
  Use `@ControllerAdvice` to globally handle exceptions and map them to status codes.
- **REST Best Practices:**  
  Always use the most specific status code. E.g., 409 for conflicts, 422 for validation errors (not 400), avoid using 500 for expected errors.

---

## 🌱 Spring Boot Practical Tips

#### 1. Set Status Codes in Controllers

- **Return specific responses:**  
  ```java
  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
      User user = userService.findById(id);
      if (user == null) {
          return ResponseEntity.notFound().build(); // 404
      }
      return ResponseEntity.ok(user); // 200
  }
  ```

- **For async operations:**  
  ```java
  @PostMapping("/start-job")
  public ResponseEntity<Void> startJob(@RequestBody JobRequest req) {
      jobService.startJobAsync(req);
      return ResponseEntity.accepted().build(); // 202
  }
  ```

- **Throw exceptions for errors:**  
  ```java
  if (!user.isAuthorized()) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN); // 403
  }
  ```

#### 2. Global Exception Handling

Use `@ControllerAdvice` to map exceptions to status codes:

```java
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleNotFound() {
        return ResponseEntity.notFound().build(); // 404
    }
    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<String> handleTooManyRequests() {
        return ResponseEntity.status(429).body("Too many requests");
    }
}
```

---

## References

- [MDN: HTTP Response Status Codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)
- [RFC 7231: HTTP/1.1 Semantics and Content](https://tools.ietf.org/html/rfc7231)
- [Spring Boot - Building REST Services](https://spring.io/guides/gs/rest-service/)
