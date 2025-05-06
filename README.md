# 📚 Book Manager API

![Book Manager Banner](https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format\&fit=crop\&w=1350\&q=80)

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge\&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge\&logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-7.x-blue?style=for-the-badge\&logo=gradle)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15.x-blue?style=for-the-badge\&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?style=for-the-badge\&logo=docker)

> A RESTful API for managing books built with Spring Boot, Gradle, PostgreSQL, and Docker — designed for easy deployment and clean architecture.

---

## 🚀 Features

* 📘 CRUD operations for books
* 🧾 Validation & error handling
* 🐘 PostgreSQL database (via Docker Compose)
* 🔍 Clean architecture with service-layer abstraction
* 🐳 Dockerfile & Docker Compose support
* 🧪 Unit and integration tests

---

## 🗂️ Project Structure

```
.
├── BookManagerApiApplication.java        # Main entry point
├── config/                               # App configurations
├── controller/                           # REST Controllers
│   └── BookController.java
├── entity/                               # JPA Entities
│   └── Book.java
├── exception/                            # Custom Exceptions
│   ├── BookNotFoundException.java
│   └── GlobalExceptionHandler.java
├── model/                                # Request/Response Models
│   ├── BookModel.java
│   ├── ErrorResponse.java
│   └── ValidationResponse.java
├── repository/                           # Spring Data JPA Repos
│   └── BookRepository.java
├── service/                              # Business Logic
│   └── BookService.java
├── utils/                                # Utility classes
└── validators/                           # Field validation logic
    ├── FieldValidator.java
    └── impl/
        ├── AuthorValidator.java
        ├── IsbnValidator.java
        ├── PublicationYearValidator.java
        └── TitleValidator.java
```

---

## ⚙️ Getting Started

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/book-manager-api.git
cd book-manager-api
```

### 2️⃣ Run with Docker Compose

```bash
docker-compose up --build
```

This will:

* Build the Spring Boot app via Dockerfile
* Spin up PostgreSQL container
* Expose API at `http://localhost:8080`

### 3️⃣ API Endpoints

| Method | Endpoint          | Description       |
| ------ | ----------------- | ----------------- |
| POST   | `/api/books`      | Create a new book |
| GET    | `/api/books`      | Get all books     |
| GET    | `/api/books/{id}` | Get a book by ID  |
| PUT    | `/api/books/{id}` | Update a book     |
| DELETE | `/api/books/{id}` | Delete a book     |

---

## 🧪 Running Tests

```bash
./gradlew test
```

> Integration tests use H2 in-memory database

---

## 🧰 Tech Stack

* Java 17
* Spring Boot 3.x
* Gradle 7.x
* PostgreSQL 15 (via Docker Compose)
* JPA/Hibernate
* JUnit 5 + Mockito

---

## 📦 Docker & Deployment

### 📄 Dockerfile

```Dockerfile
# ---------- Stage 1: Build ----------
FROM gradle:8.4-jdk17 AS builder

# Copy project files
WORKDIR /app
COPY --chown=gradle:gradle . .

# Build the Spring Boot app (fat JAR)
RUN gradle build -Pdb=postgres -x test --no-daemon

# ---------- Stage 2: Run ----------
FROM openjdk:17-jdk
LABEL authors="rahulojha"
# Create non-root user for security

# Copy the JAR from builder stage
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Expose port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar" , "/app/app.jar"]
```

### 🐳 docker-compose.yaml

```yaml
version: "3.8"

services:
  postgres:
    image: postgres:15
    container_name: postgres
    environment:
      POSTGRES_USER: bookuser
      POSTGRES_PASSWORD: bookpass
      POSTGRES_DB: bookdb
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - book-network

  book-manager-api:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: book-manager-api
    environment:
      SPRING_PROFILES_ACTIVE: prod
      DATABASE_URL: jdbc:postgresql://postgres:5432/bookdb
      DATABASE_DRIVER: org.postgresql.Driver
      DATABASE_USERNAME: bookuser
      DATABASE_PASSWORD: bookpass
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    networks:
      - book-network

volumes:
  postgres_data:

networks:
  book-network:
    driver: bridge
```

---

## ✅ To Do

* [x] CRUD endpoints
* [x] Global error handling
* [x] Field validations
* [x] Docker support
* [x] PostgreSQL integration
* [ ] Unit and Integration Tests
* [ ] Swagger/OpenAPI docs
* [ ] CI/CD via GitHub Actions

---

## 📬 Contact

> Created by [Rahul Ojha](https://github.com/rahul-ojha-07) · Feel free to reach out!
>

[![Facebook](https://img.shields.io/badge/Facebook-%231877F2.svg?logo=Facebook&logoColor=white)](https://facebook.com/rahul.ojha.07) [![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?logo=Instagram&logoColor=white)](https://instagram.com/rahul_ojha_07) [![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5.svg?logo=linkedin&logoColor=white)](https://linkedin.com/in/rahulojha07) [![Medium](https://img.shields.io/badge/Medium-12100E?logo=medium&logoColor=white)](https://medium.com/@@rahul-ojha-07) [![Pinterest](https://img.shields.io/badge/Pinterest-%23E60023.svg?logo=Pinterest&logoColor=white)](https://pinterest.com/rahul_ojha_07) [![Quora](https://img.shields.io/badge/Quora-%23B92B27.svg?logo=Quora&logoColor=white)](https://quora.com/profile/Rahul-Ojha-29) [![Stack Overflow](https://img.shields.io/badge/-Stackoverflow-FE7A16?logo=stack-overflow&logoColor=white)](https://stackoverflow.com/users/8400785) [![X](https://img.shields.io/badge/X-black.svg?logo=X&logoColor=white)](https://x.com/rahul_ojha_07) [![YouTube](https://img.shields.io/badge/YouTube-%23FF0000.svg?logo=YouTube&logoColor=white)](https://youtube.com/@@rahul_ojha_07) 


---

> "Code is like humor. When you have to explain it, it’s bad." – Cory House
