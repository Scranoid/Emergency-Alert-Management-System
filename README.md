
# 🚨 **Emergency Alert Management System (EAMS)**
*A Java + MySQL based emergency alert broadcasting system.*

## 📘 Overview
The **Emergency Alert Management System (EAMS)** is a Java-based application that allows authorized users to:
- Create urgent alerts  
- Broadcast alerts  
- View pending alerts  
- Store alerts securely in a MySQL database  

This project demonstrates:
- ✔ Core Java  
- ✔ Object-Oriented Programming  
- ✔ JDBC Connectivity  
- ✔ DAO Pattern  
- ✔ MySQL schema design  
- ✔ Proper project structure (Maven)

---

## 📂 Project Structure
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
        │       ├── dao/
        │       │   ├── AlertDAO.java
        │       │   └── UserDAO.java
        │       ├── model/
        │       │   ├── Alert.java
        │       │   └── User.java
        │       └── util/
        │           └── DBConnection.java
        │
        └── resources/
            └── application.properties
```

---

## 🛠️ Technologies Used
| Technology | Purpose |
|-----------|----------|
| **Java 11+** | Core backend |
| **MySQL 8+** | Database |
| **JDBC** | Connectivity |
| **Maven** | Build & Dependencies |
| **jBCrypt** | Password hashing |
| **MySQL Connector/J** | MySQL driver |

---

## 🗄️ Database Schema (MySQL)
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

## 🔌 JDBC Configuration
```properties
jdbc.url=jdbc:mysql://localhost:3306/eams_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
jdbc.username=root
jdbc.password=YOUR_PASSWORD
jdbc.driver=com.mysql.cj.jdbc.Driver
```

---

## 🧱 Build the Project
```bash
mvn clean package
```

---

## ▶️ Run the Application
```bash
java -jar target/eams-1.0-SNAPSHOT-jar-with-dependencies.jar
```

---

## 💻 Core Java Snippets

### Alert Model
```java
public class Alert {
    private int id;
    private String title;
    private String message;
    private String severity;
    private int createdBy;
    private Local.time.LocalDateTime createdAt;
    private boolean isBroadcasted;
    private Local.time.LocalDateTime broadcastedAt;
}
```

### AlertDAO Example
```java
public int createAlert(Alert alert) throws SQLException {
    String sql = "INSERT INTO alerts (title, message, severity, created_by) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, alert.getTitle());
        ps.setString(2, alert.getMessage());
        ps.setString(3, alert.getSeverity());
        ps.setInt(4, alert.getCreatedBy());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) return rs.getInt(1);
    }
    return -1;
}
```

### Main Application
```java
public class MainApp {
    public static void main(String[] args) {
        System.out.println("EAMS - Demo Start");
        try {
            UserDAO userDAO = new UserDAO();
            User admin = userDAO.findByUsername("admin");

            AlertDAO alertDAO = new AlertDAO();
            Alert alert = new Alert("Roadblock", "Major accident on Highway 7.", "HIGH", admin.getId());
            int id = alertDAO.createAlert(alert);

            alertDAO.listPendingAlerts().forEach(System.out::println);

            alertDAO.markBroadcasted(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 📤 Importing Database

CMD method:
```bash
mysql -u root -p eams_db < sql/schema.sql
mysql -u root -p eams_db < sql/seed_data.sql
```

---

## 🚀 Features Implemented
- ✔ Create alerts  
- ✔ List pending alerts  
- ✔ Broadcast alerts  
- ✔ DAO + JDBC design  
- ✔ MySQL integration  
- ✔ BCrypt hashing support  

---

## 📌 Future Enhancements
- 🔐 Login system  
- 🖥 GUI (Swing / JavaFX)  
- 📡 Real-time notifications  
- 📱 SMS/Email gateway  
- 📊 Analytics dashboard  

---

## 👨‍💻 Author
**Abhinav Jha , Maradul Krishna Bhardwaj and Anurag Upadhyay**  
Built with Java, MySQL, Maven & JDBC.

---

## 🏁 Conclusion
A complete backend system for managing and broadcasting emergency alerts with clean project structure and extensible design.
