package com.capitanperegrina.common.net.html;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

/**
 * Clase que contiene métodos de tareas comunes con elementos html mediante la
 * librería Jsoup.
 */
public class HtmlUtils 
{
	static Logger log = Logger.getLogger( HtmlUtils.class );

	private HtmlUtils()
	{
	}

	/**
	 * Elimina todos los atributos de un elemento html y todos los hijos que contenga.
	 * @param el El elemento a tratar.
	 * @return El elemento limpio de atributos.
	 */
	public static Element eliminaAtributosRecursivamente( Element el )
	{
		Attributes at = el.attributes();
		for ( Attribute a : at ) 
		{
			el.removeAttr(a.getKey());
		}
		
		for ( Element elHijo : el.children() )
		{
			eliminaAtributosRecursivamente( elHijo );
		}
		
		return el;
	}
}
