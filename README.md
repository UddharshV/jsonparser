# JSON Parser & Object Mapping in Java

A lightweight Java learning project for understanding how JSON moves through a backend system: from raw request payloads, to Jackson tree models, to strongly typed Java objects, and back again.

This project focuses on the practical side of JSON handling in Java using Jackson. The goal is not just to “parse JSON,” but to understand the object-mapping pipeline that shows up everywhere in backend development, REST APIs, service-to-service communication, and test automation.[web:144][web:199][web:278]

---

## What this project covers

This project currently explores four core stages of JSON processing:

1. **Parse** a raw JSON string into a Jackson `JsonNode` tree for inspection and traversal.[web:199][web:339]
2. **Deserialize** JSON into Java POJOs using `ObjectMapper`-backed mapping methods.[web:144][web:284]
3. **Serialize** Java objects back into JSON using Jackson’s tree and writer APIs.[web:144][web:278]
4. **Pretty-print** cleaned or transformed JSON for easier debugging and testing.[web:144]

That workflow mirrors how many backend systems actually handle request and response bodies in production.[web:278][web:284]

---

## Features

### JSON tree parsing
Parse raw JSON strings into Jackson `JsonNode` trees.

```java
JsonNode node = UserProfileParser.parse(jsonSource);
```

This is useful when the structure needs to be inspected before mapping, or when only a few fields need to be extracted directly from the JSON tree.[web:199][web:339]

### POJO deserialization
Convert JSON structures into strongly typed Java objects.

```java
AuthorPOJO author = AuthorJsonParser.fromJson(node, AuthorPOJO.class);
```

Jackson’s `ObjectMapper` is the central class used for reading JSON into Java objects and writing Java objects back to JSON.[web:144][web:284]

### POJO serialization
Convert Java objects back into `JsonNode` trees.

```java
JsonNode node = UserProfileParser.toNode(userProfilePojo);
```

This helps model the other half of API work: building JSON responses from Java objects rather than only consuming request payloads.[web:144][web:278]

### Nested object and list mapping
Supports nested JSON structures including:

- Objects
- Arrays
- Lists
- Boolean fields
- Date fields using `LocalDate`

This was exercised using author/book and order/customer/items style payloads, which are close to the kinds of nested request bodies seen in real APIs.[web:312][web:332]

### Unknown field handling
Gracefully ignore extra input fields during deserialization when they are not part of the target POJO.

```java
defaultObjectMapper.configure(
    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
    false
);
```

This is useful when incoming JSON contains fields that are not needed by the application model.[web:193][web:194]

### Java time support
Uses `LocalDate` instead of legacy `java.util.Date` for date-only values.

```java
objectMapper.registerModule(new JavaTimeModule());
objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
```

Jackson needs `JavaTimeModule` to support Java 8+ time types, and disabling timestamp output helps serialize dates in readable string form such as `2000-03-19`.[web:250][web:252]

### Unit testing
Includes JUnit-based tests for validating:

- JSON parsing
- Field extraction
- POJO conversion
- Nested object handling
- Date serialization behavior
- Ignoring extra fields

Testing each step of the mapping pipeline makes the learning process much clearer and mirrors how backend teams validate request/response handling.[cite:1][web:144]

---

## Example JSON

```json
{
  "authorName": "Stephen King",
  "books": [
    {
      "title": "The Shining",
      "inPrint": true,
      "publishDate": "1977-01-28"
    },
    {
      "title": "11/22/63",
      "inPrint": true,
      "publishDate": "2011-11-08"
    }
  ]
}
```

---

## Example nested payload

```json
{
  "orderId": "999",
  "customer": {
    "name": "Uddharsh Vasili",
    "email": "uddharsh_vasili@outlook.com"
  },
  "items": [
    {
      "name": "Diet Coke",
      "quantity": 12
    },
    {
      "name": "Eggs",
      "quantity": 36
    }
  ]
}
```

This payload demonstrates how nested JSON objects map naturally to nested POJOs, and how JSON arrays map to `List<T>` in Java.[web:312][web:332]

---

## Example object model

```text
AuthorPOJO
 ├── authorName
 └── books
      └── BookPOJO
            ├── title
            ├── inPrint
            └── publishDate

OrderPOJO
 ├── orderId
 ├── customer
 │    └── CustomerPOJO
 │          ├── name
 │          └── email
 └── items
      └── ItemPOJO
            ├── name
            └── quantity
```

---

## Tech stack

| Technology | Purpose |
|---|---|
| Java 23 | Core language |
| Maven | Dependency management and build tool |
| Jackson Databind | JSON parsing, serialization, and deserialization [web:144] |
| Jackson JSR-310 Module | `LocalDate` and Java time support [web:250] |
| JUnit 5 | Unit testing |
| IntelliJ IDEA | Development environment |

---

## Project structure

```text
jsonparser/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/uddharshcodes/jsonparser/
│   │           ├── parser classes
│   │           └── pojo/
│   │
│   └── test/
│       └── java/
│           └── com/uddharshcodes/jsonparser/
│               └── JUnit tests
│
├── pom.xml
└── README.md
```

---

## Learning highlights so far

This project has already helped reinforce several important backend concepts:

- **Jackson tree model vs object model**: when to work with `JsonNode` directly and when to map into POJOs.[web:199][web:284]
- **Serialization vs deserialization**: JSON-to-object and object-to-JSON are related, but not identical, processes.[web:144][web:278]
- **Type modeling matters**: `LocalDate` is a better fit than `Date` when the JSON represents only a calendar date.[web:250][web:252]
- **Unknown field strategy**: extra JSON fields can be tolerated safely without bloating the domain model.[web:193][web:197]
- **Nested payload design**: real API payloads are often object graphs, not flat maps.[web:312][web:332]
- **Testing the pipeline**: each transformation step can and should be asserted independently.[cite:1][web:144]

---

## Why this matters

JSON mapping is a foundational skill for backend engineering because HTTP APIs commonly send request and response bodies as JSON, and server-side code usually needs to transform those payloads into typed application models.[web:278][web:284] Understanding this pipeline early makes it easier to move into server development, request handling, validation, persistence layers, and eventually distributed systems work.[cite:1][web:144]

---

## Running the project

### Clone repository

```bash
git clone https://github.com/UddharshV/jsonparser.git
cd jsonparser
```

### Build

```bash
mvn clean install
```

### Run tests

```bash
mvn test
```

---

## Sample mapping flow

```text
Raw JSON String
      ↓
Jackson JsonNode
      ↓
POJO Mapping
      ↓
Java Object Graph
      ↓
JsonNode / JSON Output
```

A lot of backend work is just this flow repeated with more rules, more validation, and more moving parts.[cite:1][web:278]

---

## Current learning focus

The current focus areas are:

- JSON processing internals
- Serialization and deserialization
- Java POJO modeling
- Generic helper methods
- Object mapping pipelines
- Nested JSON handling
- Date/time modeling with `LocalDate`
- Backend engineering fundamentals

---

## Next steps

Planned next steps include:

- Simple HTTP server implementation in Java
- Handling request bodies with JSON parsing
- Building API-style request/response flows
- Validation for required fields
- Custom serializers and deserializers
- Better exception handling
- Generic collection deserialization
- Streaming parser exploration
- Performance comparisons and benchmarking

---

## Author

**Uddharsh Vasili**

Master’s student in Computer Science, focused on backend engineering, DevOps, distributed systems, and infrastructure engineering.

This repository is part learning lab, part backend foundation builder, and part proof that small exercises can teach big ideas.
