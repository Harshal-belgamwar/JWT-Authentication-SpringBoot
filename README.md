# 🔐 JWT Authentication – Spring Boot (Login & Signup)

This project implements **JWT (JSON Web Token) based authentication** using **Spring Boot and Spring Security**.  
It provides secure **User Registration (Signup)** and **User Login** functionality with **stateless authentication**.

---

## 🚀 Project Overview

JWT Authentication is widely used in modern backend systems to secure REST APIs.  
This project demonstrates how to:
- Register users securely
- Authenticate users using username & password
- Generate JWT tokens on successful login
- Validate JWT tokens for protected APIs
- Secure endpoints using Spring Security filters

---

## ✨ Features

- User Signup (Register API)
- User Login (Authenticate API)
- JWT Token Generation
- JWT Token Validation
- Secure Password Storage using BCrypt
- Stateless Authentication (No Sessions)
- Spring Security Integration
- RESTful API Design

---

## 🛠 Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Hibernate / JPA
- MySQL / H2 Database
- Maven

---

## 📂 Project Structure
src/main/java

├── config → Security configuration & JWT setup

├── controller → REST controllers (Login & Signup)

├── service → Business logic

├── repository → Database repositories

├── model → Entity classes

├── dto → Request & Response DTOs

├── filter → JWT authentication filter

└── util → JWT utility class

## 🔐 Authentication Flow

1. User registers using the signup API
2. Password is encrypted using BCrypt before storing in database
3. User logs in using valid credentials
4. Server generates a JWT token
5. Client stores the token
6. Client sends token in every request header
7. JWT filter validates token before allowing access

 JWT Generation

   <img width="800" height="400" alt="image" src="https://github.com/user-attachments/assets/a0ce9f31-6e77-482a-8b55-eb8a3bef1bdf" />
   



## ▶️ Steps to Run the Project Locally

1. Install required software on your system: Java JDK 17 or higher, Maven, MySQL (or H2), Git, Postman, and any IDE such as IntelliJ IDEA, Eclipse, or VS Code.

2. Clone the GitHub repository using the command:
   
  git clone https://github.com/Harshal-belgamwar/JWT-Authentication-SpringBoot.git

4. Navigate into the project directory:
   
  cd JWT-Authentication-SpringBoot

5. Build the project using Maven:

  mvn clean install

6. Run the Spring Boot application:

  mvn spring-boot:run
