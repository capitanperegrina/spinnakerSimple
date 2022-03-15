CREATE TABLE renovacion_anuncio (
    id_renocacion_anuncio   INTEGER NOT NULL PRIMARY KEY,
    id_anuncio          INTEGER NOT NULL REFERENCES anuncio(id_anuncio),
    id_usuario_alta          INTEGER NOT NULL,
    fec_alta                 DATETIME NOT NULL,
    id_usuario_modif         INTEGER NOT NULL,
    fec_modif                DATETIME NOT NULL
);


alter table anuncio add gramaje decimal(15,4) after tipo_vela;
alter table anuncio add pais char(3) after precio;

alter table anuncio_his add gramaje decimal(15,4) after tipo_vela;
alter table anuncio_his add pais char(3) after precio;