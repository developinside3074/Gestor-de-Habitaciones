INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('osmar','123456',true, 'Osmar', 'Gonzalez','og1142s@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('esther','123456',true, 'Esther', 'Perez','eter19861@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('alejandro','123456',true, 'Alejandro', 'Rojas','alej1478@gmail.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_GERENTE');
INSERT INTO `roles` (nombre) VALUES ('ROLE_RECEPCIONISTA');
INSERT INTO `roles` (nombre) VALUES ('ROLE_CLIENTE');


INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (3, 3);