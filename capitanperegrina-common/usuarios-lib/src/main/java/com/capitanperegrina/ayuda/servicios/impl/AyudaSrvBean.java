package com.capitanperegrina.ayuda.servicios.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitanperegrina.ayuda.modelo.dao.impl.tabla.PaginaAyudaDao;
import com.capitanperegrina.ayuda.modelo.entidad.tabla.PaginaAyudaPOJO;
import com.capitanperegrina.ayuda.servicios.AyudaSrv;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.exceptions.ServicioException;
import com.capitanperegrina.common.utils.Cadenas;

@Service
public class AyudaSrvBean implements AyudaSrv {

	static Logger log = Logger.getLogger( AyudaSrvBean.class );
	
	@Autowired
	PaginaAyudaDao paginaAyudaDao;
	
	@Override
	public PaginaAyudaPOJO buscaContenidoAyuda( Integer idAyuda ) {
		try {
			PaginaAyudaPOJO obj = new PaginaAyudaPOJO();
			obj.getPk().setIdAyuda( idAyuda );
			return this.paginaAyudaDao.busca( obj );			
		} catch ( ServicioException e ) {
			log.warn( "", e );
			return null;
		}
	}

	@Override
	public PaginaAyudaPOJO buscaContenidoAyuda( String idForm, String idElemento, String idSubelemento ) {
		try {
			PaginaAyudaPOJO obj = new PaginaAyudaPOJO();
			obj.setIdForm( idForm );
			obj.setIdElemento( idElemento );
			obj.setIdSubelemento( idSubelemento );
			obj = this.paginaAyudaDao.buscaPorFormElemento( obj );

			obj.setTitulo( Cadenas.substituyeVariables( obj.getTitulo(), DefaultGlobalNames.DELIMITADOR_INICIAL_VARIABLES_SUBSTITUIBLES, DefaultGlobalNames.DELIMITADOR_FINAL_VARIABLES_SUBSTITUIBLES ) );
			obj.setContenido( Cadenas.substituyeVariables( obj.getContenido(), DefaultGlobalNames.DELIMITADOR_INICIAL_VARIABLES_SUBSTITUIBLES, DefaultGlobalNames.DELIMITADOR_FINAL_VARIABLES_SUBSTITUIBLES ) );

			return obj; 			
		} catch ( ServicioException e ) {
			log.trace( "", e );
			return null;
		}
	}
}
