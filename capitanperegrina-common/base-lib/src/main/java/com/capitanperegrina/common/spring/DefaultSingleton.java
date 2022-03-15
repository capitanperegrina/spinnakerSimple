package com.capitanperegrina.common.spring;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Clase abstracta que define la estructura que debe tener un singleton de tabla de ayuda.
 * @param <K> Tipo de la clave del objeto de la tabla de ayuda.
 * @param <T> Tipo del objeto de la tabla de ayuda
 */
public abstract class DefaultSingleton<K,T> implements Serializable
{
	private static final long serialVersionUID = - 3751459016510341777L;
	
	protected Integer version;
    protected Map<K,T> mapa;
    protected List<T> lista;

    protected abstract void initialize();

    /**
     * Método generico para sincronizar singletons.
     */
	public void sincronizaSingleton()
	{
		initialize();
	}

	/**
	 * Método que debuelve el ojeto de la tabla de ayuda a partir de su código.
	 * @param codigo Clave a buscar
	 * @return Ojeto de la tabla de ayuda que se corresponde con el código o null si no existe.
	 */
    public T getPojo( K codigo )
    {
    	 return this.mapa.get( codigo );
    }

    /**
     * Método que devuelte todos los objetos de la tabla de ayuda.
     * @return Lista con todos los objetos de la tabla de ayuda.
     */
    public List<T> getLista()
    {
    	return this.lista;
    }
    
    public Map<K,T> getMapa()
    {
    	return this.mapa;
    }
    
    /**
     * Método que indica si una clave está en la tabla de ayuda.
     * @param codigo Clave a buscar
     * @return true si existe, false si no existe.
     */
    public boolean contiene( K codigo )
    {
        return this.mapa.get( codigo ) != null;    	
    }
}

