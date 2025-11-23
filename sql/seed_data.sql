
USE eams_db;

INSERT INTO users (username, password_hash, role)
VALUES ('admin', '$2a$10$7sYjQw5uZg2Qq6f2KJZsUe0pGQF9Y9qZ6rF6rF6rF6rF6rF6rF6rF', 'ADMIN'),
       ('operator1', '$2a$10$7sYjQw5uZg2Qq6f2KJZsUe0pGQF9Y9qZ6rF6rF6rF6rF6rF6rF6rF', 'OPERATOR');

INSERT INTO alerts (title, message, severity, created_by)
VALUES ('Test Alert', 'This is a seeded test alert', 'LOW', 1);
