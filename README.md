# Order Delivery Point Service
## Overview
Сервис для управления пунктами выдачи заказов (ПВЗ) и приемками.
## Требования
- JDK 17+
- PostgreSQL
- Docker и Docker Compose (опционально)
## Установка и запуск
1. Клонирование репозитория
```
git clone https://github.com/BeloeColeno/order-delivery-point-service.git
cd order-delivery-point-service
```
2. Запуск с помощью Docker
```
docker-compose up -d
```
Или при сборке приложения Docker должен подняться самостоятельно.
3. Инициализация базы данных в ручную
```
psql -U postgres -d pvz_db -f src/main/resources/schema.sql
```
## Использование api
### Аутентификация
Для доступа к API используйте HTTP Basic Authentication:
```
GET "http://localhost:8080/pvz" -u admin:admin
```
Или для аутентификации в виде пользователя:
```
GET "http://localhost:8080/pvz" -u user:password
```
## Работа с ПВЗ
1. Создание ПВЗ (требуется роль MODERATOR)
```
POST "http://localhost:8080/pvz"
```
Пример запроса:
```
'{
"name": "ПВЗ №1",
"address": "ул. Ленина, 10",
"status": "ACTIVE"
}'
```
2. Получение списка ПВЗ (требуется роль EMPLOYEE или MODERATOR)
```
GET "http://localhost:8080/pvz"
```
#### Остальное не успел сделать :(