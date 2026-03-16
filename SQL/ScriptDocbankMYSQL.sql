CREATE DATABASE docbank;
USE docbank;

CREATE TABLE usuarios (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
senha VARCHAR(255) NOT NULL,
cargo VARCHAR(50) DEFAULT 'Usuario'
);

CREATE TABLE documentos (
id INT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(255) NOT NULL,
topico VARCHAR(100) NOT NULL,
identificador TEXT NOT NULL,
status ENUM('revisao', 'aprovado') DEFAULT 'revisao'
);

CREATE TABLE favoritos (
usuario_id INT,
documento_id INT,
PRIMARY KEY (usuario_id, documento_id),
FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
FOREIGN KEY (documento_id) REFERENCES documentos(id) ON DELETE CASCADE
);