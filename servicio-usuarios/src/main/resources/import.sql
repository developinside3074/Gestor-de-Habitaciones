INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('osmar','$2a$10$u0.ylGlWMuiFPvzvDcVtS.idorlp.JMdZsjv6Czhj8ZakZ/L50Fh.',true, 'Osmar', 'Gonzalez','og1142s@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('esther','$2a$10$YeqdjXo/4qIhEQeypAUDgeBLjCAhPQqv6OlMyihbfQfOEEaFg1A/C',true, 'Esther', 'Perez','eter19861@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('alejandro','$2a$10$Kn9Je9H.eyih8YXSqX5npuoj/dUEnxF6Wkvzg9r280GoqaylFIhny',true, 'Alejandro', 'Rojas','alej1478@gmail.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_GERENTE');
INSERT INTO `roles` (nombre) VALUES ('ROLE_RECEPCIONISTA');
INSERT INTO `roles` (nombre) VALUES ('ROLE_CLIENTE');


INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, rol_id) VALUES (3, 3);