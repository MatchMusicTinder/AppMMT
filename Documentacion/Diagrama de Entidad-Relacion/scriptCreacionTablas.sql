CREATE DATABASE matchmusic;
USE matchmusic;
        
CREATE TABLE Comentarios(
  IdComentario           INT          NOT NULL,
  ContenidoDelComentario VARCHAR(200) NOT NULL,
  FechaDelComentario     DATETIME     NOT NULL,
  IdUsuario              INT          NOT NULL,
  IdPosteo               INT          NOT NULL,
  PRIMARY KEY (IdComentario),
  CONSTRAINT idposteo
    FOREIGN KEY (idPosteo)
    REFERENCES matchmusic.posteo (idPosteo),
  CONSTRAINT idusuario
    FOREIGN KEY (idUsuario)
    REFERENCES matchmusic.usuario (idUsuario)
);

CREATE TABLE Conexiones(
  IdConexiones        INT    NOT NULL,
  IdUsuario_que_sigue INT    NOT NULL,
  IdUsuarioSeguido    INT      NOT NULL,
  FechaDeConexion   DATETIME NOT NULL,
  PRIMARY KEY (IdConexiones),
  CONSTRAINT idUsuarioSeguidor
    FOREIGN KEY (IdUsuario_que_sigue)
    REFERENCES matchmusic.usuario (idUsuario),
  CONSTRAINT IdUsuarioSeguido
    FOREIGN KEY (idUsuarioSeguido)
    REFERENCES matchmusic.usuario (idUsuario) 
);

CREATE TABLE Favoritos(
  IdFavoritos          INT      NOT NULL,
  IdUsuario            INT      NOT NULL,
  IdPosteo             INT      NOT NULL,
  FechaMarcadoFavorito DATETIME NOT NULL,
  PRIMARY KEY (IdFavoritos),
  CONSTRAINT idPosteo
    FOREIGN KEY (idPosteo)
    REFERENCES matchmusic.posteo (idPosteo),
  CONSTRAINT idUsuario
    FOREIGN KEY (idUsuario)
    REFERENCES matchmusic.usuario (idUsuario)
);

CREATE TABLE Posteo(
  IdPosteo               INT          NOT NULL,
  IdUsuario              INT          NOT NULL,
  TituloDelPosteo        VARCHAR(45)  NOT NULL,
  ContenidoDelPosteo     VARCHAR(200) NOT NULL,
  FechaDePublicación     DATETIME     NOT NULL,
  GeneroMusicalDelPosteo VARCHAR(45)  NOT NULL,
  Ubicacion              VARCHAR(45)  NOT NULL,
  IdTipoDePosteo         INT          NOT NULL,
  PRIMARY KEY (IdPosteo),
  CONSTRAINT idUsuario
    FOREIGN KEY (idUsuario)
    REFERENCES matchmusic.usuario (idUsuario)
);

CREATE TABLE TipoDePosteo(
  IdTipoDePosteo     INT          NOT NULL,
  NombreTipoDePosteo VARCHAR(100) NOT NULL,
  PRIMARY KEY (IdTipoDePosteo),
  CONSTRAINT NombreTipoDePosteo
    FOREIGN KEY (idTipoDePosteo)
    REFERENCES matchmusic.posteo (idPosteo)
);

CREATE TABLE Usuario(
  IdUsuario             INT         NOT NULL,
  NombreDeUsuario       VARCHAR(45) NOT NULL,
  CorreoElectronico     VARCHAR(45) NOT NULL,
  Contraseña            VARCHAR(45) NOT NULL,
  FechaDeRegistro       DATETIME    NOT NULL,
  GeneroMusicalFavorito VARCHAR(45) NOT NULL,
  Edad 					INT,
  Ubicacion 			VARCHAR(45),
  Posteos 				INT,
  Banda 				VARCHAR(45),
  Mensajes			 	INT,
  Instrumentos 			VARCHAR(45),
  PRIMARY KEY (IdUsuario),
  CONSTRAINT banda
    FOREIGN KEY (banda)
    REFERENCES matchmusic.banda (idBanda),
  CONSTRAINT instrumentos
    FOREIGN KEY (instrumentos)
    REFERENCES matchmusic.instrumento (idInstrumento),
  CONSTRAINT mensajes
    FOREIGN KEY (mensajes)
    REFERENCES matchmusic.mensaje (idMensaje),
  CONSTRAINT posteos
    FOREIGN KEY (posteos)
    REFERENCES matchmusic.posteo (idPosteo),
  CONSTRAINT ubicacion
    FOREIGN KEY (ubicacion)
    REFERENCES matchmusic.ubicación (idUbicación)
);

CREATE TABLE Mensaje(
	idMensaje INT NOT NULL,
    Contenido VARCHAR(150),
    Remitente INT,
    Destinatario INT,
    Fecha_envío DATETIME,
	PRIMARY KEY (idMensaje)
);

CREATE TABLE Banda(
	idBanda INT NOT NULL,
    nombre VARCHAR (50) NOT NULL,
    idGenero INT NOT NULL,
    miembros VARCHAR (45) NOT NULL,
    PRIMARY KEY (idBanda),
    CONSTRAINT idGenero
		FOREIGN KEY (idGenero)
		REFERENCES matchmusic.genero_musical (idGenero)
);

CREATE TABLE Ubicación(
	idUbicación INT NOT NULL,
    nombre VARCHAR (100) NOT NULL,
    dirección VARCHAR (100) NOT NULL,
    PRIMARY KEY (idUbicación)
);

CREATE TABLE Instrumento(
	idInstrumento INT NOT NULL,
    nombre VARCHAR (50) NOT NULL,
    tipo VARCHAR (50) NOT NULL,
    PRIMARY KEY (idInstrumento)
);

CREATE TABLE Genero_Musical(
	idGenero INT NOT NULL,
    nombre VARCHAR(50),
    PRIMARY KEY (idGenero)
);