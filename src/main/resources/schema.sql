DROP TABLE if EXISTS users;
CREATE TABLE users
(
    id         NUMERIC PRIMARY KEY,
    email      VARCHAR(50)  NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    password   VARCHAR(150) NOT NULL,
    enabled    BIT DEFAULT 1
);

DROP TABLE if EXISTS ROLE;
CREATE TABLE role
(
    id   NUMERIC PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

DROP TABLE if EXISTS privilege;
CREATE TABLE privilege
(
    id   NUMERIC PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

DROP TABLE if EXISTS user_roles;
CREATE TABLE user_roles
(
    user_id NUMERIC REFERENCES users (id),
    role_id NUMERIC REFERENCES role (id)
);

DROP TABLE if EXISTS role_privileges;
CREATE TABLE role_privileges
(
    role_id      NUMERIC REFERENCES role (id),
    privilege_id NUMERIC REFERENCES privilege (id)
);