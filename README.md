
# Emergency Alert Management System (EAMS)

This is a sample Java-based project that demonstrates core Java, JDBC connectivity, and DAO pattern for managing emergency alerts.

## What is included
- Maven project (pom.xml)
- Java source under `src/main/java/com/eams/...`
- `application.properties` (sample) under `src/main/resources/`
- SQL schema and seed data under `sql/`
- Presentation outline under `presentation/`
- README and a packaged executable assembly configuration in `pom.xml` (jar-with-dependencies)

## Prerequisites
- Java 11+
- Maven
- MySQL (or compatible) server

## Setup
1. Clone the project and enter the directory.
2. Create DB/schema: `mysql -u root -p < sql/schema.sql`
3. Seed data (optional): `mysql -u root -p < sql/seed_data.sql`
4. Update `src/main/resources/application.properties` with your DB credentials.
5. Build: `mvn clean package`
6. Run: `java -jar target/eams-1.0-SNAPSHOT-jar-with-dependencies.jar`

## Notes
- Passwords in seed_data are placeholder bcrypt hashes; replace accordingly.
- This project is a demo scaffold. For production, add proper auth, input validation, connection pooling, and secure secret storage.
