# 📘 Book Manager API - User Stories & Acceptance Criteria

## 🧱 User Story #1: Project Setup

**As a developer**, I want to set up a Spring Boot project with the necessary dependencies, so that I can start building the Book Manager API.

**Acceptance Criteria:**

* ✅ Given a new project, when I generate it with Spring Initializr, then it includes dependencies: Spring Web, Spring Data JPA, H2/PostgreSQL, and Lombok.
* ✅ Should use Maven or Gradle for build management.
* ✅ Should run without errors using `./mvnw spring-boot:run` or `./gradlew bootRun`.

---

## 📦 User Story #2: Create Book Entity

**As a developer**, I want to define a `Book` entity class, so that I can model book data in the application.

**Acceptance Criteria:**

* ✅ Given the entity class, when I define it, then it includes fields: `id`, `title`, `author`, `isbn`, and `publicationYear`.
* ✅ `id` should be auto-generated using `@Id` and `@GeneratedValue`.
* ✅ Should use JPA annotations to map the class to a table.
* ✅ Should create a corresponding table in the database on application start.

---

## 🛠️ User Story #3: Implement Create Book (POST)

**As a user**, I want to add a new book, so that I can store book details in the system.

**Acceptance Criteria:**

* ✅ Given valid book data, when I send a POST request to `/api/books`, then a new book is created and returned with HTTP 201.
* ✅ Should handle invalid input (e.g. blank title, future publication year).
* ✅ Returns HTTP 400 for bad requests.
* ✅ ISBN should be unique; duplicates return HTTP 409 Conflict.

---

## 🔍 User Story #4: Get All Books (GET)

**As a user**, I want to retrieve a list of all books, so that I can see everything in the system.

**Acceptance Criteria:**

* ✅ Given books in the system, when I send a GET request to `/api/books`, then I receive a list of all books with HTTP 200.
* ✅ Returns an empty list if no books are present.
* ✅ Each book includes `id`, `title`, `author`, `isbn`, and `publicationYear`.

---

## 🔎 User Story #5: Get Book by ID (GET)

**As a user**, I want to retrieve a single book by ID, so that I can view its details.

**Acceptance Criteria:**

* ✅ Given a valid book ID, when I send a GET request to `/api/books/{id}`, then the book is returned with HTTP 200.
* ✅ Given an invalid/non-existent ID, then the response is HTTP 404 with an appropriate message.

---

## ✏️ User Story #6: Update Book (PUT)

**As a user**, I want to update the details of a book, so that I can correct or change information.

**Acceptance Criteria:**

* ✅ Given a valid ID and valid data, when I send a PUT request to `/api/books/{id}`, then the book is updated with HTTP 200.
* ✅ Returns HTTP 400 for invalid input (e.g. missing fields, future publication year).
* ✅ Returns HTTP 404 for non-existent book ID.
* ✅ ISBN changes should still be unique.

---

## 🗑️ User Story #7: Delete Book (DELETE)

**As a user**, I want to delete a book by ID, so that I can remove outdated or incorrect records.

**Acceptance Criteria:**

* ✅ Given a valid book ID, when I send a DELETE request to `/api/books/{id}`, then the book is deleted with HTTP 204.
* ✅ Given a non-existent ID, returns HTTP 404.

---

## 🧪 User Story #8: Input Validation

**As a developer**, I want to validate input data, so that only correct book data is processed.

**Acceptance Criteria:**

* ✅ Should reject blank title/author, ISBN with incorrect format.
* ✅ Should reject publication year in the future.
* ✅ Uses JSR-380 (Bean Validation) annotations.
* ✅ Returns clear validation errors with HTTP 400.

---

## 🚨 User Story #9: Global Error Handling

**As a user**, I want to receive consistent and informative error responses, so that I can understand and fix issues.

**Acceptance Criteria:**

* ✅ Given any exception, when it occurs, then the API returns a structured error message with HTTP status code.
* ✅ Uses `@ControllerAdvice` for global exception handling.
* ✅ Includes timestamp, message, status, and path in error response body.

---

## 🧪 User Story #10: Unit & Integration Testing

**As a developer**, I want to write tests for each API endpoint, so that I can ensure correctness and catch regressions.

**Acceptance Criteria:**

* ✅ Uses JUnit 5 and Spring Boot Test.
* ✅ Tests cover happy and failure paths for each endpoint.
* ✅ Integration tests use H2 in-memory DB.
* ✅ Test reports show >80% code coverage.

---

## ☁️ User Story #11: Application Deployment (Optional)

**As a developer**, I want to deploy the API to a cloud platform, so that others can access it remotely.

**Acceptance Criteria:**

* ✅ Given a cloud provider (e.g. Render, Heroku, Railway), when I push code, then the API is accessible via a public URL.
* ✅ Uses PostgreSQL for persistence in production.
* ✅ Includes `application-prod.properties` for environment-specific config.
* ✅ Secures DB credentials using environment variables.
