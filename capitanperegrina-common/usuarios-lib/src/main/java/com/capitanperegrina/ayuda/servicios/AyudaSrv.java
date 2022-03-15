package com.capitanperegrina.ayuda.servicios;

import com.capitanperegrina.ayuda.modelo.entidad.tabla.PaginaAyudaPOJO;

public interface AyudaSrv {

	public PaginaAyudaPOJO buscaContenidoAyuda( Integer idAyuda );

	public PaginaAyudaPOJO buscaContenidoAyuda( String idForm, String idElemento, String idSubelemento );
}
