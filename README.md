# 🛠️ Issue Tracking & Workflow Management System

A role-based issue tracking system built to manage, assign, and resolve civic or organizational issues through a structured workflow.  
The project emphasizes backend architecture, workflow enforcement, and clean separation of concerns, with a lightweight frontend to demonstrate end-to-end functionality.

---

## 📌 Overview

This system enables multiple user roles to interact with issues in a controlled and transparent manner:

- 👤 Users can report issues  
- 🧑‍💼 Admins assign issues to staff  
- 🧑‍🔧 Staff update issue status until resolution  

The backend is implemented using **Core Java and JDBC** with **PostgreSQL** for data persistence.  
A simple frontend built with **HTML, CSS, Bootstrap, and JavaScript** simulates the workflow and is structured to be API-ready for future backend integration.

---

## ✨ Key Features

- 🔐 Role-based workflow (USER, ADMIN, STAFF)
- 🔄 Complete issue lifecycle management
- 🧱 Layered backend architecture
- 🗄️ PostgreSQL database with foreign key constraints
- 📝 Audit logging for issue actions
- 💻 Console-based backend execution
- 🌐 Frontend workflow simulation using JavaScript

---

## 🧰 Tech Stack

### Backend
- ☕ Core Java  
- 🔗 JDBC  
- 🐘 PostgreSQL  

### Frontend
- 🧾 HTML  
- 🎨 CSS  
- 🅱️ Bootstrap  
- ⚙️ JavaScript  

### Tools
- 🧠 IntelliJ IDEA  
- 🌱 Git & GitHub  

---

## 🏗️ Project Structure

issue-tracker/
├── src/
│ ├── Main.java
│ ├── main/
│ ├── model/
│ ├── repository/
│ ├── service/
│ └── util/
│
├── frontend/
│ ├── index.html
│ ├── user.html
│ ├── admin.html
│ ├── staff.html
│ └── js/
│ └── app.js
│
├── .gitignore
└── README.md

---

## 🔁 Workflow

1. 👤 USER creates an issue  
2. 🧑‍💼 ADMIN assigns the issue to a staff member  
3. 🧑‍🔧 STAFF updates the issue status (IN_PROGRESS / RESOLVED)  
4. 📝 All actions are logged for traceability  

---

## ▶️ How to Run

### Backend
1. Set up PostgreSQL and create the required tables  
2. Update database credentials in the connection utility  
3. Run the application from `Main.java`  

### Frontend
1. Open `frontend/index.html` in a browser  
2. Navigate using role-based options  
3. Follow the workflow (User → Admin → Staff)  

---

## 🚀 Future Enhancements

- 🌐 REST API layer  
- 🔑 Authentication and authorization  
- 🔗 Frontend-backend integration  
- 📊 Web-based admin dashboard  
- ☁️ Cloud deployment  

---

## 👨‍💻 Author

**Romin Kevadiya**  
Computer Science student focused on backend development, system design, and practical problem-solving.
