# 📚 Book Manager API

![Book Manager Banner](https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format\&fit=crop\&w=1350\&q=80)

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Dockerized-%E2%9C%94-blue?style=for-the-badge&logo=docker)
![JWT](https://img.shields.io/badge/Security-JWT-yellowgreen?style=for-the-badge&logo=jwt)

A secure and scalable Book Management REST API built with **Java**, **Spring Boot**, **PostgreSQL**, **JWT**, and **Docker**. It supports full CRUD functionality, user authentication, validation, rate limiting, and follows clean architectural practices.

---

## ✨ Features

- ✅ CRUD operations on books (`title`, `author`, `isbn`, `publicationYear`)
- 🔐 JWT-based authentication & authorization
- 📏 Field validation with custom validators
- 🚫 Global exception handling
- 🧵 Rate limiting using filter-based logic (IP/User)
- 📦 Docker & Docker Compose support
- 🧪 Easily extendable and testable

---

## 📂 Project Structure

```plaintext
src/main/java/in/rahulojha/bookmanagerapi
├── auth                      # JWT auth logic (controller, service, models)
├── config                    # Spring Security configuration
├── controller                # BookController for API endpoints
├── entity                    # JPA entities (Book, AppUser)
├── exception                 # Global exception handling
├── filter                    # Filters: JWT + Rate Limiting
├── model                     # DTOs and API response models
├── repository                # Spring Data JPA repositories
├── service                   # Core business logic
├── utils                     # JWT utility class
└── validators                # Custom field-level validators

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

| Method | Endpoint          | Description                |
| ------ |-------------------|----------------------------|
| POST   | `/auth/register`  | Create a new user          |
| POST   | `/auth/login`     | Login to get JWT token |
| POST   | `/api/books`      | Create a new book          |
| GET    | `/api/books`      | Get all books              |
| GET    | `/api/books/{id}` | Get a book by ID           |
| PUT    | `/api/books/{id}` | Update a book              |
| DELETE | `/api/books/{id}` | Delete a book              |

---
## 🔐 Authentication

### 🔐 Login to Get Token

```http
POST /auth/login
Content-Type: application/json

{
  "username": "user1",
  "password": "password123"
}
```

### 🔐 Use Token for Requests

Add to headers:

```http
Authorization: Bearer <your-jwt-token>
```
---
## 📊 Rate Limiting

* **Unauthenticated Users** → Tracked by IP
* **Authenticated Users** → Tracked by `username` in JWT
* Limit: `5 requests/minute`

Returns:

```http
429 Too Many Requests
Rate limit exceeded. Try again later.
```

---

## 🧪 Running Tests

```bash
./gradlew test
```

> Integration tests use H2 in-memory database

---

## 🧰 Tech Stack

* Java 17
* Spring Boot 3.4+
* Spring Security + JWT
* PostgreSQL
* Gradle
* Docker & Docker Compose
* JPA/Hibernate
* Log4j2

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
    volumes:
      - ./logs:/app/logs
    networks:
      - book-network

volumes:
  postgres_data:

networks:
  book-network:
    driver: bridge
```
[README.md](README.md)
---

## ✅ To Do

* [x] CRUD endpoints
* [x] Global error handling
* [x] Field validations
* [x] Docker support
* [x] PostgreSQL integration
* [ ] Unit and Integration Tests
* [x] Swagger/OpenAPI docs
* [ ] CI/CD via GitHub Actions

---

## 📬 Contact

> Created by [Rahul Ojha](https://github.com/rahul-ojha-07) · Feel free to reach out!
>

[![Facebook](https://img.shields.io/badge/Facebook-%231877F2.svg?logo=Facebook&logoColor=white)](https://facebook.com/rahul.ojha.07) [![Instagram](https://img.shields.io/badge/Instagram-%23E4405F.svg?logo=Instagram&logoColor=white)](https://instagram.com/rahul_ojha_07) [![LinkedIn](https://img.shields.io/badge/LinkedIn-%230077B5.svg?logo=linkedin&logoColor=white)](https://linkedin.com/in/rahulojha07) [![Medium](https://img.shields.io/badge/Medium-12100E?logo=medium&logoColor=white)](https://medium.com/@@rahul-ojha-07) [![Pinterest](https://img.shields.io/badge/Pinterest-%23E60023.svg?logo=Pinterest&logoColor=white)](https://pinterest.com/rahul_ojha_07) [![Quora](https://img.shields.io/badge/Quora-%23B92B27.svg?logo=Quora&logoColor=white)](https://quora.com/profile/Rahul-Ojha-29) [![Stack Overflow](https://img.shields.io/badge/-Stackoverflow-FE7A16?logo=stack-overflow&logoColor=white)](https://stackoverflow.com/users/8400785) [![X](https://img.shields.io/badge/X-black.svg?logo=X&logoColor=white)](https://x.com/rahul_ojha_07) [![YouTube](https://img.shields.io/badge/YouTube-%23FF0000.svg?logo=YouTube&logoColor=white)](https://youtube.com/@@rahul_ojha_07) 


---

> "Code is like humor. When you have to explain it, it’s bad." – Cory House
