package com.capitanperegrina.i18n.modelo.common.basebeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.capitanperegrina.i18n.modelo.common.exceptions.IdiomaNoExisteException;

/**
 * Clase base para los singletons localizados.
 * @author USUARIO
 *
 * @param <K> tipo para la clave
 * @param <T> tipo para el objeto de la tabla de ayuda
 */
public abstract class I18nBaseSingleton <K,T> implements Serializable
{
	private static final long serialVersionUID = - 3751459016510341777L;

	protected Integer version;
    protected Map<String,Map<K,T>> mapa;
    protected Map<String,Map<String,String>> options;
    protected Map<String,List<T>> lista;

    protected abstract void initialize();

    /**
     * Método generico para sincronizar singletons.
     */
	public void sincronizaSingleton()
	{
		initialize();
	}

	/**
	 * Método que debuelve el ojeto de la tabla de ayuda a partir de su código para un idioma dado.
	 * @param lang Código de idioma
	 * @param codigo Clave a buscar
	 * @return Ojeto de la tabla de ayuda que se corresponde con el código o null si no existe.
	 */
    public T getPojo( final String lang, final K codigo )
    {
    	 return this.mapa.get( lang.toUpperCase() ).get( codigo );
    }

    /**
     * Método que devuelte todos los objetos de la tabla de ayuda para un idioma dado.
	 * @param lang Código de idioma
     * @return Lista con todos los objetos de la tabla de ayuda.
     */
    public List<T> getLista( final String lang ) {
    	return this.lista.get( lang );
    }

    /**
     * Obtiene un mapa de clave - descripción para un idioma dado
     * @param lang Idioma
     * @return el mapa
     */
    public Map<String,String> getMapaParaFormulario( final String lang ) {
    	return this.options.get( lang.toUpperCase() );
    }

    /**
     * Método que indica si una clave está en la tabla de ayuda para un idioma dado.
	 * @param lang Código de idioma
     * @param codigo Clave a buscar
     * @return true si existe, false si no existe.
     */
    public boolean contiene( final String lang, final K codigo ) {
    	if ( this.mapa.get( lang.toUpperCase() ) != null ) {
            return this.mapa.get( lang ).get( codigo ) != null;
    	} else {
    		throw new IdiomaNoExisteException( lang );
    	}
    }
}
