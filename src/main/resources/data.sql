INSERT INTO role (id, name)
VALUES (1, 'ROLE_USER');
INSERT INTO role (id, name)
VALUES (2, 'ROLE_AGENT');
INSERT INTO role (id, name)
VALUES (3, 'ROLE_LIBRARIAN');
INSERT INTO role (id, name)
VALUES (4, 'ROLE_ADMIN');

INSERT INTO privilege (id, name)
VALUES (1, 'USER_CREATE');
INSERT INTO privilege (id, name)
VALUES (2, 'LIBRARIAN_CREATE');
INSERT INTO privilege (id, name)
VALUES (3, 'AGENT_CREATE');
INSERT INTO privilege (id, name)
VALUES (4, 'ROLE_MODIFY');
INSERT INTO privilege (id, name)
VALUES (5, 'PRIVILEGE_MODIFY');

INSERT INTO role_privileges (role_id, privilege_id)
VALUES (2, 1);
INSERT INTO role_privileges (role_id, privilege_id)
VALUES (3, 1);
INSERT INTO role_privileges (role_id, privilege_id)
VALUES (4, 2);
INSERT INTO role_privileges (role_id, privilege_id)
VALUES (4, 3);
INSERT INTO role_privileges (role_id, privilege_id)
VALUES (4, 4);
INSERT INTO role_privileges (role_id, privilege_id)
VALUES (4, 5);

INSERT INTO users (id, email, first_name, last_name, password)
VALUES (1, 'admin@email.com', 'admin', 'admin', '$2y$10$MsjSF2MdinfKDTmFosyWJen9oCIb4Pv99qIyHJANDgPbWA62YlGRu');

INSERT INTO users (id, email, first_name, last_name, password)
VALUES (2, 'agent@email.com', 'agent', 'agent', '$2y$10$lrRH77TJWROfKv03xjV96u01FgX6mS259quR6nRhm8kyb4LybBRPy');

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 4);
INSERT INTO user_roles (user_id, role_id)
VALUES (2, 2);