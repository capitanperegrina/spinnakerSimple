package com.spinnakersimple.modelo.bean;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SocialBean.
 */
public class SocialBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1309037616547942486L;

	/** The og url. */
	private String ogUrl;

    /** The og type. */
    private String ogType;

    /** The og tittle. */
    private String ogTittle;

    /** The og description. */
    private String ogDescription;

    /** The og image. */
    private String ogImage;

    /** The texto. */
    private String texto;

    /** The url. */
    private String url;

    /** The via. */
    private String via;

    /**
     * Instantiates a new social bean.
     */
    public SocialBean() {
    	super();
    }

	/**
	 * Gets the og url.
	 *
	 * @return the og url
	 */
	public String getOgUrl() {
		return this.ogUrl;
	}

	/**
	 * Sets the og url.
	 *
	 * @param ogUrl the new og url
	 */
	public void setOgUrl(final String ogUrl) {
		this.ogUrl = ogUrl;
	}

	/**
	 * Gets the og type.
	 *
	 * @return the og type
	 */
	public String getOgType() {
		return this.ogType;
	}

	/**
	 * Sets the og type.
	 *
	 * @param ogType the new og type
	 */
	public void setOgType(final String ogType) {
		this.ogType = ogType;
	}

	/**
	 * Gets the og tittle.
	 *
	 * @return the og tittle
	 */
	public String getOgTittle() {
		return this.ogTittle;
	}

	/**
	 * Sets the og tittle.
	 *
	 * @param ogTittle the new og tittle
	 */
	public void setOgTittle(final String ogTittle) {
		this.ogTittle = ogTittle;
	}

	/**
	 * Gets the og description.
	 *
	 * @return the og description
	 */
	public String getOgDescription() {
		return this.ogDescription;
	}

	/**
	 * Sets the og description.
	 *
	 * @param ogDescription the new og description
	 */
	public void setOgDescription(final String ogDescription) {
		this.ogDescription = ogDescription;
	}

	/**
	 * Gets the og image.
	 *
	 * @return the og image
	 */
	public String getOgImage() {
		return this.ogImage;
	}

	/**
	 * Sets the og image.
	 *
	 * @param ogImage the new og image
	 */
	public void setOgImage(final String ogImage) {
		this.ogImage = ogImage;
	}

	/**
	 * Gets the texto.
	 *
	 * @return the texto
	 */
	public String getTexto() {
		return this.texto;
	}

	/**
	 * Sets the texto.
	 *
	 * @param texto the new texto
	 */
	public void setTexto(final String texto) {
		this.texto = texto;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	/**
	 * Gets the via.
	 *
	 * @return the via
	 */
	public String getVia() {
		return this.via;
	}

	/**
	 * Sets the via.
	 *
	 * @param via the new via
	 */
	public void setVia(final String via) {
		this.via = via;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("SocialBean [ogUrl=").append(this.ogUrl).append(", ogType=").append(this.ogType).append(", ogTittle=").append(this.ogTittle).append(", ogDescription=").append(this.ogDescription).append(", ogImage=").append(this.ogImage).append(", texto=").append(this.texto).append(", url=").append(this.url).append(", via=").append(this.via).append("]");
		return builder.toString();
	}
}
