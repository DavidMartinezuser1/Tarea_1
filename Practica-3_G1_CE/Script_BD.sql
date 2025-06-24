drop schema if exists practica;
drop user if exists usuario_prueba;
CREATE SCHEMA practica;
USE practica;

CREATE USER 'usuario_practica' IDENTIFIED BY 'la_Clave';
GRANT ALL PRIVILEGES ON practica.* TO 'usuario_practica'@'%';

CREATE TABLE arbol (
  id_arbol INT NOT NULL AUTO_INCREMENT,
  ruta_imagen VARCHAR(1024),
  nombre VARCHAR(50) NOT NULL,
  tipo_flor VARCHAR(30) NOT NULL,
  tiempo_vida INT NOT NULL,
  PRIMARY KEY (id_arbol))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;