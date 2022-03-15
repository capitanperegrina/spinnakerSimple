alter table anuncio add destacado integer after comentado;

alter table anuncio_his add destacado integer after comentado;

update anuncio set destacado = 0;

update anuncio_his set destacado = 0;