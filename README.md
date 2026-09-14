# 🧵 Garment Business Management System

A full-stack **Garment Business Management System** designed to digitize and simplify the day-to-day operations of small garment businesses.

The system helps business owners manage employees, track production, calculate earnings, monitor payments and expenses, analyze revenue and profit, and measure employee performance — all from a centralized dashboard.

Employees also get their own dashboard to track their production, earnings, payments, pending amounts, and performance growth.

---

## 🚀 Project Goals

Small garment businesses often rely on manual records or spreadsheets for managing:

* 👥 Employees
* 📦 Production
* 💰 Employee earnings
* 💳 Payments
* 🧾 Business expenses
* 📈 Revenue and profit
* 📊 Performance tracking

This project aims to replace those manual workflows with a **reliable, scalable and easy-to-use business management application**.

The primary goal is not simply to use multiple technologies, but to apply each technology to a **real-world problem where it provides meaningful value**.

---

## ✨ Features

### 👤 Employee Management

* Add, update and manage employee details
* Assign employee roles
* Activate/deactivate employees
* View individual employee profiles
* Track employee production history
* Track employee earnings and payments
* Monitor employee performance and growth

### 📦 Production Management

* Record daily production
* Track weekly and monthly production
* Associate production with employees
* Calculate earnings based on production
* View production trends
* Analyze individual and overall production

### 💰 Earnings & Payments

* Automatically calculate employee earnings
* Track total earnings
* Record payments made to employees
* Calculate pending amounts
* View payment history
* Monitor outstanding employee payments

### 🧾 Expense Management

* Record business expenses
* Categorize expenses
* Track expenses over time
* View daily, monthly and yearly expenses
* Analyze expense trends

### 📈 Revenue & Profit

* Track business revenue
* Calculate total expenses
* Calculate profit
* Compare revenue and profit across different periods
* Analyze current-year performance against previous years

### 📊 Dashboards & Analytics

Business owners can view:

* Total production
* Total revenue
* Total expenses
* Total profit
* Employee earnings
* Pending payments
* Production trends
* Expense trends
* Profit trends
* Year-over-year comparisons
* Employee performance rankings

### 👷 Employee Dashboard

Employees can view:

* 👤 Personal profile
* 📦 Production history
* 💰 Total earnings
* 💳 Payments received
* ⏳ Pending amount
* 📈 Performance over time
* 📊 Production growth

---

# 🏗️ System Architecture

The application follows a modern full-stack architecture:

```text
                    ┌─────────────────────┐
                    │      Frontend       │
                    │ React + TypeScript  │
                    │ Redux Toolkit       │
                    │ React Query         │
                    └──────────┬──────────┘
                               │
                               │ REST APIs
                               ▼
                    ┌─────────────────────┐
                    │       Backend       │
                    │    Spring Boot      │
                    │   Spring Security   │
                    │        JWT          │
                    └──────────┬──────────┘
                               │
                 ┌─────────────┼─────────────┐
                 │             │             │
                 ▼             ▼             ▼
           ┌──────────┐   ┌──────────┐  ┌──────────┐
           │ MongoDB  │   │  Redis   │  │ RabbitMQ │
           │ Business │   │  Cache   │  │  Async   │
           │   Data   │   │ & Limits │  │  Events  │
           └──────────┘   └──────────┘  └──────────┘
                              
                    ┌─────────────────────┐
                    │    CI / CD          │
                    │   GitHub Actions    │
                    └─────────────────────┘
```

---

# 🛠️ Tech Stack

## Frontend

| Technology       | Purpose                                           |
| ---------------- | ------------------------------------------------- |
| React            | User interface                                    |
| TypeScript       | Type-safe frontend development                    |
| Redux Toolkit    | Global application state                          |
| React Query      | Server-state management and API caching           |
| Charting Library | Production, revenue and performance visualization |

## Backend

| Technology      | Purpose                            |
| --------------- | ---------------------------------- |
| Java            | Backend development                |
| Spring Boot     | REST API and application framework |
| Spring Security | Authentication and authorization   |
| JWT             | Stateless API authentication       |
| REST APIs       | Frontend-backend communication     |

## Database & Infrastructure

| Technology     | Purpose                                            |
| -------------- | -------------------------------------------------- |
| MongoDB        | Primary business data storage                      |
| Redis          | Caching, rate limiting and leaderboards            |
| RabbitMQ       | Asynchronous processing and event-driven workflows |
| GitHub Actions | CI/CD automation                                   |

---

# 🧠 Why These Technologies?

One of the main objectives of this project is to use technologies based on **actual business requirements**, rather than adding technologies just to make the stack larger.

### Redis

Redis can be used for:

* Frequently accessed dashboard data
* API response caching
* Rate limiting
* Employee performance leaderboards
* Temporary data
* Reducing unnecessary database queries

Example:

```text
Dashboard Request
       │
       ▼
    Redis?
    /    \
   /      \
 HIT      MISS
  │         │
  ▼         ▼
Return    MongoDB
Data        │
            ▼
         Redis
            │
            ▼
       Return Data
```

### JWT + Spring Security

JWT-based authentication will be used to secure REST APIs.

The system can support role-based access such as:

```text
OWNER
  │
  ├── Employee Management
  ├── Production Management
  ├── Payments
  ├── Expenses
  ├── Revenue
  └── Analytics

EMPLOYEE
  │
  ├── Own Production
  ├── Own Earnings
  ├── Own Payments
  └── Own Performance
```

### RabbitMQ

RabbitMQ will be used for operations that don't need to block the user's request.

Potential use cases include:

* Generating reports
* Processing production-related events
* Updating analytics
* Sending notifications
* Processing payment events
* Background calculations

Example:

```text
Production Created
        │
        ▼
    Spring Boot
        │
        ▼
     RabbitMQ
        │
        ├──────────────► Analytics Consumer
        │
        ├──────────────► Notification Consumer
        │
        └──────────────► Reporting Consumer
```

### MongoDB

MongoDB will store the application's primary business data, such as:

* Employees
* Production records
* Payments
* Expenses
* Revenue
* Business configuration
* Performance data

The data model will be designed around the application's actual access patterns and business requirements.

---

# 📂 Project Structure

The project will be organized into separate frontend and backend applications.

```text
garment-business-management/
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── features/
│   │   ├── hooks/
│   │   ├── services/
│   │   ├── store/
│   │   ├── types/
│   │   └── utils/
│   └── package.json
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── ...
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── docker/
│   └── ...
│
├── .github/
│   └── workflows/
│       └── ...
│
└── README.md
```

---

# 🔐 Authentication & Authorization

The application will use **Spring Security + JWT** for authentication.

### Authentication Flow

```text
User Login
    │
    ▼
Spring Security
    │
    ▼
Validate Credentials
    │
    ▼
Generate JWT
    │
    ▼
Frontend
    │
    ▼
API Request + JWT
    │
    ▼
JWT Validation
    │
    ▼
Authorized Request
```

Role-based authorization will ensure that employees can only access data they are permitted to view.

---

# 📊 Main Modules

```text
┌───────────────────────────────────────┐
│          Garment Management           │
├───────────────────────────────────────┤
│                                       │
│  👤 Employee Management               │
│                                       │
│  📦 Production Management             │
│                                       │
│  💰 Earnings & Payments               │
│                                       │
│  🧾 Expense Management                │
│                                       │
│  💵 Revenue Management                │
│                                       │
│  📊 Analytics & Reports               │
│                                       │
│  🔐 Authentication & Authorization    │
│                                       │
│  📈 Employee Performance              │
│                                       │
└───────────────────────────────────────┘
```

---

# 📈 Analytics

The dashboard will provide visual insights into the business.

Examples include:

### Production

```text
Production
│
│             █
│       █     █
│   █   █     █
│   █   █ █   █
│ █ █   █ █ █ █
└──────────────────
  Mon Tue Wed Thu Fri
```

### Revenue vs Expenses

```text
Revenue   ███████████████████
Expenses  █████████
Profit    ██████████
```

### Employee Performance

Employees can be compared based on metrics such as:

* Total production
* Average daily production
* Production growth
* Earnings
* Completed work
* Performance over time

---

# 🔄 Production & Earnings Flow

A simplified business workflow:

```text
Employee
    │
    ▼
Production Recorded
    │
    ▼
Production Quantity
    │
    ▼
Rate Applied
    │
    ▼
Employee Earnings
    │
    ├──────────────► Payment Made
    │
    └──────────────► Pending Amount
```

For example:

```text
Production = 100 units
Rate       = ₹5 / unit

Total Earnings = 100 × ₹5
               = ₹500
```

---

# 💵 Business Profit Calculation

The system can calculate profit using:

```text
Profit = Revenue - Expenses
```

Employee production-based payments can be included as an operating expense depending on the business's accounting model.

The exact calculation rules will be configurable according to the business requirements.

---

# 🧪 Testing

Testing will be introduced across different layers of the application.

### Backend

* Unit tests
* Service-layer tests
* Repository tests
* Controller/API tests
* Security tests

### Frontend

* Component tests
* Integration tests
* API-state testing

### API Testing

REST APIs can be tested using tools such as:

* Postman
* Swagger / OpenAPI

---

# ⚙️ Local Development

## Prerequisites

Make sure you have installed:

* Java
* Node.js
* npm
* MongoDB
* Redis
* RabbitMQ
* Git

Optional:

* Docker
* Docker Compose

---

## Clone the Repository

```bash
git clone <repository-url>

cd garment-business-management
```

---

## Run Backend

```bash
cd backend

./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

---

## Run Frontend

```bash
cd frontend

npm install
npm run dev
```

---

# 🔧 Environment Variables

Create environment configuration files for local development.

Example backend configuration:

```env
MONGODB_URI=
REDIS_HOST=
REDIS_PORT=
RABBITMQ_HOST=
RABBITMQ_PORT=

JWT_SECRET=
JWT_EXPIRATION=
```

Frontend:

```env
VITE_API_BASE_URL=
```

> Never commit production secrets, JWT secrets, database credentials, or API keys to Git.

---

# 🐳 Docker

Docker support can be used to simplify local development by running infrastructure services such as:

```text
MongoDB
Redis
RabbitMQ
```

through Docker Compose.

Example:

```bash
docker compose up -d
```

---

# 🚀 CI/CD

GitHub Actions will be used to automate the development workflow.

Potential pipeline:

```text
Push / Pull Request
        │
        ▼
   GitHub Actions
        │
        ├── Build Frontend
        │
        ├── Build Backend
        │
        ├── Run Tests
        │
        ├── Code Quality Checks
        │
        └── Build / Deploy
```

---

# 🗺️ Development Roadmap

## Phase 1 — Project Setup

* [ ] Repository setup
* [ ] Frontend setup
* [ ] Spring Boot setup
* [ ] MongoDB integration
* [ ] Development environment

## Phase 2 — Authentication

* [ ] User registration
* [ ] Login
* [ ] JWT authentication
* [ ] Spring Security configuration
* [ ] Role-based authorization

## Phase 3 — Employee Management

* [ ] Employee CRUD
* [ ] Employee roles
* [ ] Employee profiles
* [ ] Employee status

## Phase 4 — Production

* [ ] Production records
* [ ] Daily production
* [ ] Weekly/monthly aggregation
* [ ] Production history
* [ ] Production-based earnings

## Phase 5 — Payments

* [ ] Earnings calculation
* [ ] Payment records
* [ ] Pending amounts
* [ ] Payment history

## Phase 6 — Expenses & Revenue

* [ ] Expense management
* [ ] Revenue tracking
* [ ] Profit calculation
* [ ] Financial summaries

## Phase 7 — Dashboards

* [ ] Owner dashboard
* [ ] Employee dashboard
* [ ] Production charts
* [ ] Revenue charts
* [ ] Expense charts
* [ ] Profit charts
* [ ] Year-over-year comparison

## Phase 8 — Redis

* [ ] Dashboard caching
* [ ] Rate limiting
* [ ] Leaderboard
* [ ] Cache invalidation strategy

## Phase 9 — RabbitMQ

* [ ] Event publishing
* [ ] Asynchronous processing
* [ ] Background jobs
* [ ] Notification processing

## Phase 10 — CI/CD & Deployment

* [ ] GitHub Actions
* [ ] Automated tests
* [ ] Docker
* [ ] Production configuration
* [ ] Deployment

---

# 📚 Learning & Engineering Focus

This project is also an opportunity to explore how modern technologies can solve real-world engineering problems.

Key areas of focus:

* REST API design
* Clean backend architecture
* Authentication and authorization
* Database modeling
* State management
* Server-state management
* Caching strategies
* Event-driven architecture
* Asynchronous processing
* Performance optimization
* Error handling
* Testing
* CI/CD
* Production-ready application design

---

# 🤝 Contributing

Contributions, suggestions and discussions are welcome.

If you find a bug or have an idea for improving the project:

1. Open an issue
2. Describe the problem or proposed improvement
3. Explain the expected behavior
4. Submit a pull request if you would like to implement it

---

# 📌 Project Status

🚧 **Currently in active development**

The architecture and features may evolve as the project progresses and real-world requirements become clearer.

---

# 👨‍💻 Development Journey

I’ll be documenting the development journey through:

* Technical decisions
* Architecture choices
* Database design
* API design
* Challenges encountered
* Performance improvements
* Lessons learned
* Production considerations

The goal is to build not just a demo application, but a **practical full-stack business application based on a real-world workflow**.

---

## ⭐ Support

If you find this project interesting, consider giving the repository a ⭐.

Follow the project as it evolves from an idea into a complete production-oriented application.
