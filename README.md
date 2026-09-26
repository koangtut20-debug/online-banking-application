# Online Banking Application

A browser-based Online Banking Application developed using Java, Spring Boot, MongoDB, HTML, CSS, and JavaScript.

## 🌐 Live Application

https://online-banking-application-gtof.onrender.com

## 📌 Project Description

The Online Banking Application allows users to create an account, log in securely, view their bank account information, check their balance, deposit money, withdraw money, and view transaction history.

The application uses Spring Boot for the backend and MongoDB Atlas for storing user, account, and transaction data.

## ✨ Features

* User registration
* User login
* Automatic account number generation
* Savings account management
* Account balance display
* Deposit money
* Withdraw money
* Transaction history
* Account status display
* MongoDB database integration
* Responsive web dashboard
* Cloud deployment using Render

## 🛠️ Technologies Used

### Backend

* Java
* Spring Boot
* Spring Data MongoDB
* Maven

### Frontend

* HTML
* CSS
* JavaScript

### Database

* MongoDB Atlas

### Deployment

* GitHub
* Render
* Docker

## 🏦 Main Dashboard

The dashboard contains:

* Balance
* Account Details
* Deposit
* Withdraw
* Transaction History

The main banking functions are displayed horizontally for easy access.

## 🗄️ Database

MongoDB Atlas is used to store application data.

The application uses separate collections for:

* Users
* Accounts
* Transactions

Database credentials are stored using environment variables and are not included in the GitHub repository.

## 🚀 Running the Project Locally

### 1. Clone the repository

```bash
git clone https://github.com/koangtut20-debug/online-banking-application.git
```

### 2. Open the project

```bash
cd online-banking-application/banking-web
```

### 3. Configure MongoDB

Create a `.env` file inside the `banking-web` folder:

```text
MONGODB_URI=your_mongodb_connection_string
MONGODB_DATABASE=online_banking
```

Do not upload the `.env` file to GitHub.

### 4. Run the application

```bash
export $(grep -v '^#' .env | xargs)
mvn spring-boot:run
```

### 5. Open the application

Open:

```text
http://localhost:8080
```

## ☁️ Deployment

The application is deployed using Render.

GitHub is used for source-code management, while MongoDB Atlas provides the cloud database.

Every new version can be pushed to GitHub and deployed to Render.

## 🔐 Security

* MongoDB credentials are stored using environment variables.
* `.env` is excluded from GitHub.
* User passwords are handled by the application backend.
* Database credentials are not stored directly in the source code.

## 📂 Project Structure

```text
online-banking-application/
│
├── banking-web/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   │       ├── static/
│   │   │       └── application.properties
│   │
│   ├── pom.xml
│   ├── Dockerfile
│   └── .env
│
├── old-console-version/
│
├── .gitignore
└── README.md
```

## 🎓 Learning Objectives

This project demonstrates practical knowledge of:

* Java programming
* Spring Boot development
* REST API development
* MongoDB database integration
* CRUD operations
* Web application development
* Environment variables
* Git and GitHub
* Docker
* Cloud deployment with Render

## 👨‍💻 Author

**Koang Tut Panom**

Online Banking Application Project

