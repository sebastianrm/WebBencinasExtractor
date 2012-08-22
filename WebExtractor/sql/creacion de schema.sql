
CREATE DATABASE `bencineras`;





CREATE TABLE `bencineras`.`regiones` (
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`nombre`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB;





CREATE TABLE `bencineras`.`servicentros` (
  `empresa` varchar(100) NOT NULL,
  `direccion` varchar(150) NOT NULL,
  `latitud` float NOT NULL DEFAULT '0',
  `longitud` float NOT NULL DEFAULT '0',
  `fkregion` varchar(100) NOT NULL,
  PRIMARY KEY (`empresa`,`direccion`,`fkregion`,`latitud`,`longitud`),
  KEY `fk_reg` (`fkregion`),
  CONSTRAINT `fk_reg` FOREIGN KEY (`fkregion`) REFERENCES `regiones` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;




CREATE TABLE `bencineras`.`bencinas` (
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`nombre`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB;




CREATE TABLE `bencineras`.`precios` (
  `precio` float NOT NULL DEFAULT '0',
  `fkbencina` varchar(100) NOT NULL,
  `fkempresa` varchar(100) NOT NULL,
  `fkdireccion` varchar(150) NOT NULL,
  `fkregion` varchar(100) NOT NULL,
  `fecha_actualizacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fklatitud` float NOT NULL DEFAULT '0',
  `fklongitud` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`precio`,`fkbencina`,`fkempresa`,`fkdireccion`,`fkregion`,`fecha_actualizacion`,`fklatitud`,`fklongitud`),
  KEY `fkbencina` (`fkbencina`),
  KEY `fkservicentro` (`fkempresa`,`fkdireccion`,`fkregion`,`fklatitud`,`fklongitud`),
  CONSTRAINT `fkbencina` FOREIGN KEY (`fkbencina`) REFERENCES `bencinas` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkservicentro` FOREIGN KEY (`fkempresa`, `fkdireccion`, `fkregion`,`fklatitud`,`fklongitud`) REFERENCES `servicentros` (`empresa`, `direccion`, `fkregion`,`latitud`,`longitud`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB;




CREATE TABLE `bencineras`.`direcciones_google` (
  `fkempresa` varchar(100) NOT NULL,
  `fkdireccion` varchar(100) NOT NULL,
  `fkregion` varchar(100) NOT NULL,
  `dirGooogle` varchar(200) NOT NULL,
  PRIMARY KEY (`fkempresa`,`fkdireccion`,`fkregion`,`dirGooogle`),
  KEY `fk_servicentro` (`fkempresa`,`fkdireccion`,`fkregion`),
  CONSTRAINT `fk_servicentro` FOREIGN KEY (`fkempresa`, `fkdireccion`, `fkregion`) REFERENCES `servicentros` (`empresa`, `direccion`, `fkregion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB ;