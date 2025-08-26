DROP DATABASE IF EXISTS ejercicio;
CREATE DATABASE ejercicio;
USE ejercicio;

CREATE TABLE usuario (
    id_usuario INTEGER AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(100) NOT NULL,
    rol ENUM('USER', 'ADMIN')
);

INSERT INTO usuario (nombre, email, contrasena, rol) VALUES 
('Valeria Martinez', 'valeria@gmail.com', '1234', 'USER');

INSERT INTO usuario (nombre, email, contrasena, rol) VALUES 
('Elisa Perez', 'elisa@gmail.com', '1234', 'ADMIN');

SELECT * FROM usuario; 