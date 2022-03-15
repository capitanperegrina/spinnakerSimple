package com.capitanperegrina.common.seguridad.recaptcha;

/**
 * The Class ReCaptchaSettings.
 */
public class ReCaptchaSettings {

	/** The site. */
	private String site;

	/** The secret. */
	private String secret;

	/**
	 * Instantiates a new re captcha settings.
	 */
	public ReCaptchaSettings() {
		super();
	}

	/**
	 * Gets the site.
	 *
	 * @return the site
	 */
	public String getSite() {
		return this.site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site
	 *            the new site
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Gets the secret.
	 *
	 * @return the secret
	 */
	public String getSecret() {
		return this.secret;
	}

	/**
	 * Sets the secret.
	 *
	 * @param secret
	 *            the new secret
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CaptchaSettings [site=" + this.site + ", secret=" + this.secret + "]";
	}
}