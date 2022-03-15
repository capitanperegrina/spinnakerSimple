package com.capitanperegrina.common.net.mail;

import java.io.Serializable;

public class FicheroAdjunto implements Serializable 
{
	private static final long serialVersionUID = -3141778427289540541L;

	private String nombreFichero;
	private byte[] contenido;
	private String contentType;

	public FicheroAdjunto()
	{
		super();
	}

	public String getNombreFichero()
	{
		return nombreFichero;
	}

	public void setNombreFichero( String nombreFichero )
	{
		this.nombreFichero = nombreFichero;
	}

	public byte[] getContenido()
	{
		return contenido;
	}

	public void setContenido( byte[] contenido )
	{
		this.contenido = contenido;
	}

	public String getContentType()
	{
		return contentType;
	}

	public void setContentType( String contentType )
	{
		this.contentType = contentType;
	}

	/**
	 * Construye un <code>String</code> con todos los atributos
	 * según el formato "nombre = valor".
	 *
	 * @return Una representación en forma de <code>String</code>
	 * de este objeto.
	 */
	@Override
   public String toString()
   {
		StringBuilder sb = new StringBuilder();
		sb.append( "Fichero : " ).append( this.nombreFichero ).append( " ("  ).append( this.contentType ).append(  ") "  ).append( this.contenido.length  ).append( " bytes." );
		return sb.toString();
   }
}
