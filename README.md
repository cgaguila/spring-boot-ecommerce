# 🛒 E-commerce Backend (Spring Boot)

Backend REST API for an e-commerce system built with Spring Boot.

## 🚀 Features

- Product management
- Order creation with validation
- Unit price calculation
- Global exception handling
- Standardized API responses
- Logging for debugging and monitoring

## 🛠 Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database
- Maven

## 📦 API Endpoints

### Products
- GET /products
- POST /products

### Orders
- GET /orders
- POST /orders

## 📄 Example Request

```json
{
  "productIds": [1,2]
}

## 📄 Example Response
{
  "success": true,
  "data": {
    "id": 1,
    "total": 250.0,
    "products": ["Dog Food"]
  },
  "message": "Order created successfully"
}

## ⚠️ Error Handling
{
  "success": false,
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid products"
}
