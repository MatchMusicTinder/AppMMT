
        
CREATE TABLE Comentarios
(
  IdComentario           INT          NOT NULL,
  ContenidoDelComentario VARCHAR(200) NOT NULL,
  FechaDelComentario     DATETIME     NOT NULL,
  IdUsuario              INT          NOT NULL,
  IdPosteo               INT          NOT NULL,
  PRIMARY KEY (IdComentario)
);

CREATE TABLE Conexiones
(
  IdConexiones      INT      NOT NULL,
  IdUsuarioSeguidor INT      NOT NULL,
  IdUsuarioSeguido  INT      NOT NULL,
  FechaDeConexion   DATETIME NOT NULL,
  PRIMARY KEY (IdConexiones)
);

CREATE TABLE Favoritos
(
  IdFavoritos          INT      NOT NULL,
  IdUsuario            INT      NOT NULL,
  IdPosteo             INT      NOT NULL,
  FechaMarcadoFavorito DATETIME NOT NULL,
  PRIMARY KEY (IdFavoritos)
);

CREATE TABLE Posteo
(
  IdPosteo               INT          NOT NULL,
  IdUsuario              INT          NOT NULL,
  TituloDelPosteo        VARCHAR(45)  NOT NULL,
  ContenidoDelPosteo     VARCHAR(200) NOT NULL,
  FechaDePublicación     DATETIME     NOT NULL,
  GeneroMusicalDelPosteo VARCHAR(45)  NOT NULL,
  Ubicacion              VARCHAR(45)  NOT NULL,
  IdTipoDePosteo         INT          NOT NULL,
  PRIMARY KEY (IdPosteo)
);

CREATE TABLE TipoDePosteo
(
  IdTipoDePosteo     INT          NOT NULL,
  NombreTipoDePosteo VARCHAR(100) NOT NULL,
  PRIMARY KEY (IdTipoDePosteo)
);

CREATE TABLE Usuario
(
  IdUsuario             INT         NOT NULL,
  NombreDeUsuario       VARCHAR(45) NOT NULL,
  CorreoElectronico     VARCHAR(45) NOT NULL,
  Contraseña            VARCHAR(45) NOT NULL,
  FechaDeRegistro       DATETIME    NOT NULL,
  GeneroMusicalFavorito VARCHAR(45) NOT NULL,
  PRIMARY KEY (IdUsuario)
);

ALTER TABLE Conexiones
  ADD CONSTRAINT FK_Usuario_TO_Conexiones
    FOREIGN KEY (IdUsuarioSeguidor)
    REFERENCES Usuario (IdUsuario);

ALTER TABLE Conexiones
  ADD CONSTRAINT FK_Usuario_TO_Conexiones1
    FOREIGN KEY (IdUsuarioSeguido)
    REFERENCES Usuario (IdUsuario);

ALTER TABLE Favoritos
  ADD CONSTRAINT FK_Usuario_TO_Favoritos
    FOREIGN KEY (IdUsuario)
    REFERENCES Usuario (IdUsuario);

ALTER TABLE Posteo
  ADD CONSTRAINT FK_Usuario_TO_Posteo
    FOREIGN KEY (IdUsuario)
    REFERENCES Usuario (IdUsuario);

ALTER TABLE Favoritos
  ADD CONSTRAINT FK_Posteo_TO_Favoritos
    FOREIGN KEY (IdPosteo)
    REFERENCES Posteo (IdPosteo);

ALTER TABLE Posteo
  ADD CONSTRAINT FK_TipoDePosteo_TO_Posteo
    FOREIGN KEY (IdTipoDePosteo)
    REFERENCES TipoDePosteo (IdTipoDePosteo);

ALTER TABLE Comentarios
  ADD CONSTRAINT FK_Usuario_TO_Comentarios
    FOREIGN KEY (IdUsuario)
    REFERENCES Usuario (IdUsuario);

ALTER TABLE Comentarios
  ADD CONSTRAINT FK_Posteo_TO_Comentarios
    FOREIGN KEY (IdPosteo)
    REFERENCES Posteo (IdPosteo);


        
      