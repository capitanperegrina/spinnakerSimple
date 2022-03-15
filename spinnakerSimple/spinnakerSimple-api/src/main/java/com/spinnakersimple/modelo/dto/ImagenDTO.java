package com.spinnakersimple.modelo.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The Class ImagenDTO.
 */
public class ImagenDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5720640939469868904L;

	/** The contenido. */
	private byte[] bytes;

	/** The mime type. */
	private String contentType;

	/**
	 * Instantiates a new imagen DTO.
	 */
	public ImagenDTO() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImagenDTO [bytes=" + Arrays.toString(this.bytes) + ", contentType=" + this.contentType + "]";
	}

	/**
	 * Gets the bytes.
	 *
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return this.bytes;
	}

	/**
	 * Sets the bytes.
	 *
	 * @param bytes
	 *            the new bytes
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * Gets the content type.
	 *
	 * @return the content type
	 */
	public String getContentType() {
		return this.contentType;
	}

	/**
	 * Sets the content type.
	 *
	 * @param contentType
	 *            the new content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
