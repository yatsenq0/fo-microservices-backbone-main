# FO Service Backbone

Микросервис на Spring Boot для управления пользователями с событийно-ориентированной архитектурой.

## 📋 Описание

Service Backbone — это RESTful веб-сервис, построенный на Spring Boot 3.5.4 с использованием Java 21. Сервис предоставляет функциональность управления пользователями и использует PostgreSQL для хранения данных, Apache Kafka для обмена событиями между сервисами.

## 🚀 Основные возможности

- ✅ CRUD операции для управления пользователями
- ✅ Событийно-ориентированная архитектура с Apache Kafka
- ✅ Миграции базы данных с Liquibase
- ✅ Интеграционное тестирование с Testcontainers
- ✅ Мониторинг и метрики с Actuator и Prometheus
- ✅ Контейнеризация с Docker

## 🛠 Технологический стек

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **PostgreSQL**
- **Apache Kafka**
- **Liquibase**
- **Testcontainers**
- **Docker & Docker Compose**
- **Lombok**

## 📦 Установка и запуск

### Предварительные требования

- Java 21
- Docker и Docker Compose
- Gradle (используется Gradle Wrapper)

### Быстрый старт

1. **Клонируйте репозиторий:**
   ```bash
   git clone <repository-url>
   cd service-backbone
   ```

2. **Запустите все сервисы с помощью Docker Compose:**
   ```bash
   docker-compose up
   ```

   Это запустит:
   - PostgreSQL (порт 5432)
   - Apache Kafka (порт 9092)
   - Service Backbone (порт 8080)

### Локальная разработка

1. **Запустите только зависимости:**
   ```bash
   docker-compose up postgres kafka
   ```

2. **Запустите приложение:**
   ```bash
   ./gradlew bootRun
   ```

### Сборка проекта

```bash
# Сборка проекта
./gradlew build

# Запуск тестов
./gradlew test

# Очистка и пересборка
./gradlew clean build
```

## 🔧 API Endpoints

Базовый путь: `http://localhost:8080/api/users`

| Метод | Endpoint | Описание |
|-------|----------|----------|
| GET | `/api/users` | Получить список всех пользователей |
| GET | `/api/users/{id}` | Получить пользователя по ID |
| POST | `/api/users` | Создать нового пользователя |
| PUT | `/api/users/{id}` | Обновить существующего пользователя |
| DELETE | `/api/users/{id}` | Удалить пользователя |

### Примеры запросов

**Создание пользователя:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

**Получение всех пользователей:**
```bash
curl http://localhost:8080/api/users
```

## 🏗 Архитектура

Проект следует принципам Domain-Driven Design и состоит из следующих слоев:

```
com.nikriv.fo.servicebackbone/
├── config/          # Конфигурация (JPA, и др.)
├── controller/      # REST контроллеры
├── dto/            # Объекты передачи данных
├── entity/         # JPA сущности
├── event/          # Модели событий
├── repository/     # Spring Data репозитории
└── service/        # Бизнес-логика
```

### Событийная архитектура

При выполнении операций с пользователями (создание, обновление, удаление) сервис публикует события в Kafka:

- `USER_CREATED` - пользователь создан
- `USER_UPDATED` - пользователь обновлен
- `USER_DELETED` - пользователь удален

События отправляются в топик `user-events-topic`.

## 🗄 База данных

Проект использует PostgreSQL с миграциями Liquibase:

- **Файл changelog:** `src/main/resources/db/changelog/db.changelog-master.yml`
- **Миграции:** `src/main/resources/db/changelog/`

## 🧪 Тестирование

Проект использует JUnit 5 и Testcontainers для интеграционного тестирования:

```bash
# Запуск всех тестов
./gradlew test

# Запуск конкретного теста
./gradlew test --tests "ServiceBackboneApplicationTests"
```

## 📊 Мониторинг

Actuator endpoints доступны для мониторинга:

- **Health Check:** `http://localhost:8080/actuator/health`
- **Metrics:** `http://localhost:8080/actuator/metrics`
- **Prometheus:** `http://localhost:8080/actuator/prometheus`

## 🐳 Docker

### Сборка образа
```bash
docker build -t service-backbone .
```

### Docker Compose
Файл `docker-compose.yml` содержит конфигурацию для всех необходимых сервисов:
- PostgreSQL
- Apache Kafka
- Service Backbone

## ⚙️ Конфигурация

Основные настройки находятся в `src/main/resources/application.properties`:

- **База данных:** PostgreSQL (localhost:5432/service_backbone)
- **Kafka:** localhost:9092
- **Consumer Group:** service-backbone

## 📝 Логирование

Сервис использует SLF4J с Logback для логирования событий и операций.

## 🤝 Участие в разработке

1. Создайте ветку для новой функции
2. Внесите изменения
3. Запустите тесты: `./gradlew test`
4. Создайте Pull Request
