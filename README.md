
# 🚨 Emergency Alert Management System (EAMS)
*A Java + MySQL based emergency alert broadcasting system with a full Swing-based GUI.*

---

# 📘 Overview
The **Emergency Alert Management System (EAMS)** is a complete Java-based application that enables authorized users to:
- Create emergency alerts  
- View pending alerts  
- Broadcast alerts  
- Login securely  
- Interact with a clean **Swing GUI**  
- Store alerts in a MySQL database  

This project demonstrates:
- ✔ Core Java  
- ✔ OOP  
- ✔ JDBC  
- ✔ Swing GUI  
- ✔ DAO Pattern  
- ✔ MySQL schema  
- ✔ Maven-based modular structure  

---

# 📂 Updated Project Structure (Including GUI)

```
EAMS_project/
│
├── pom.xml
├── README.md
├── presentation/
│   └── EAMS_presentation_outline.md
│
├── sql/
│   ├── schema.sql
│   └── seed_data.sql
│
└── src/
    └── main/
        ├── java/
        │   └── com/eams/
        │       ├── MainApp.java              
        │       │
        │       ├── gui/                      
        │       │   ├── LoginWindow.java
        │       │   ├── Dashboard.java
        │       │   ├── CreateAlertForm.java
        │       │   └── PendingAlertsWindow.java
        │       │
        │       ├── dao/
        │       │   ├── AlertDAO.java
        │       │   └── UserDAO.java
        │       │
        │       ├── model/
        │       │   ├── Alert.java
        │       │   └── User.java
        │       │
        │       └── util/
        │           └── DBConnection.java
        │
        └── resources/
            └── application.properties
```

---

# 🖥️ GUI Features (New Section)

## ✔ 1. Login Window
Provides secure authentication using BCrypt + UserDAO.

- Username/password input  
- DAO-based validation  
- Hash comparison  
- Loads Dashboard after successful login  

---

## ✔ 2. Dashboard Window
Central hub for user actions.

Contains buttons:
- Create Alert  
- View Pending Alerts  
- Exit Application  

---

## ✔ 3. Create Alert Form
Allows users to create new alerts visually.

Fields:
- Title  
- Message  
- Severity (LOW / MEDIUM / HIGH / CRITICAL)

On submission:
- Uses `AlertDAO.createAlert()`
- Saves alert to MySQL  
- Shows success prompt  

---

## ✔ 4. Pending Alerts Window
Displays all pending alerts in a JTable.

Features:
- List alerts using `AlertDAO.listPendingAlerts()`  
- Broadcast selected alert using `AlertDAO.markBroadcasted()`  
- Auto-refresh after broadcast  

---

# 🧱 Build the Project

```bash
mvn clean package
```

---

# ▶️ Run the Application (GUI Mode)

```bash
java -jar target/eams-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Or run from an IDE:
- Open `MainApp.java`
- Click Run ▶️

---

# 🗄️ Database Schema

```sql
CREATE DATABASE IF NOT EXISTS eams_db;
USE eams_db;

CREATE TABLE IF NOT EXISTS users(
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role ENUM('ADMIN', 'OPERATOR') NOT NULL DEFAULT 'OPERATOR',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS alerts(
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  message TEXT NOT NULL,
  severity ENUM('LOW','MEDIUM','HIGH','CRITICAL') NOT NULL,
  created_by INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  is_broadcasted BOOLEAN DEFAULT FALSE,
  broadcasted_at TIMESTAMP NULL,
  FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL
);
```

---

# 🔌 JDBC Configuration

```properties
jdbc.url=jdbc:mysql://localhost:3306/eams_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
jdbc.username=root
jdbc.password=YOUR_PASSWORD
jdbc.driver=com.mysql.cj.jdbc.Driver
```

---

# 💻 MainApp (GUI Version)

```java
package com.eams;

import com.eams.gui.LoginWindow;

public class MainApp {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });
    }
}
```

---

# 📤 Importing Database

CMD method:
```bash
mysql -u root -p eams_db < sql/schema.sql
mysql -u root -p eams_db < sql/seed_data.sql
```

---

# 🚀 Features Implemented

- ✔ Console backend + GUI  
- ✔ Login system  
- ✔ Alert creation  
- ✔ Pending alert table  
- ✔ Broadcasting system  
- ✔ Secure database integration  
- ✔ DAO abstraction  
- ✔ BCrypt password hashing  

---

# 📌 Future Enhancements

- 🔐 Admin/operator roles in GUI  
- 🌐 Web dashboard using Spring Boot  
- 📡 Real-time notifications  
- 📱 Email/SMS broadcasting  
- 📊 Analytics dashboard  
- 🗂 Logging & audit system  

---

# 👨‍💻 Authors

**Team Leader & System Architect:**  
**Abhinav Jha (Scranoid)**  
Adm No: 24scse1010454  
GitHub: https://github.com/Scranoid  

**Lead Designer:**  
**Mradul Krishna Bhardwaj**  
Adm No: 24scse1010433  
GitHub: https://github.com/chhayabhardwaj111-create  

**System Admin & Maintainer:**  
**Anurag Upadhyay**  
Adm No: 24scse1011310  
GitHub: https://github.com/anurag282024  

---

# 🏁 Conclusion

The EAMS includes a **fully functional Swing GUI** built on top of a solid Java backend.  
It is clean, modular, extendable, and perfect for academic submission or real-world scaling.
