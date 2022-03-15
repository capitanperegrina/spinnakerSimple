package com.spinnakersimple.web.ui.bean;

import java.io.Serializable;

import com.capitanperegrina.common.web.ui.CaptchaUI;

/**
 * The Class LoginUI.
 */
public class LoginUI extends CaptchaUI implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -731923349973487755L;

	/** The tipo uso. */
	protected String tipoUso;

	/** The email login. */
	protected String emailLogin;

	/** The pass login. */
	protected String passLogin;

	/** The pass new. */
	protected String passNew;

	/** The pass repetida. */
	protected String passRepetida;

	/**
	 * Instantiates a new login form DTO.
	 */
	public LoginUI() {
		super();
	}

	/**
	 * Gets the email login.
	 *
	 * @return the email login
	 */
	public String getEmailLogin() {
		return this.emailLogin;
	}

	/**
	 * Gets the pass login.
	 *
	 * @return the pass login
	 */
	public String getPassLogin() {
		return this.passLogin;
	}

	/**
	 * Gets the pass new.
	 *
	 * @return the pass new
	 */
	public String getPassNew() {
		return this.passNew;
	}

	/**
	 * Gets the pass repetida.
	 *
	 * @return the pass repetida
	 */
	public String getPassRepetida() {
		return this.passRepetida;
	}

	/**
	 * Gets the tipo uso.
	 *
	 * @return the tipo uso
	 */
	public String getTipoUso() {
		return this.tipoUso;
	}

	/**
	 * Sets the email login.
	 *
	 * @param emailLogin
	 *            the new email login
	 */
	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	/**
	 * Sets the pass login.
	 *
	 * @param passLogin
	 *            the new pass login
	 */
	public void setPassLogin(String passLogin) {
		this.passLogin = passLogin;
	}

	/**
	 * Sets the pass new.
	 *
	 * @param passNew
	 *            the new pass new
	 */
	public void setPassNew(String passNew) {
		this.passNew = passNew;
	}

	/**
	 * Sets the pass repetida.
	 *
	 * @param passRepetida
	 *            the new pass repetida
	 */
	public void setPassRepetida(String passRepetida) {
		this.passRepetida = passRepetida;
	}

	/**
	 * Sets the tipo uso.
	 *
	 * @param tipoUso
	 *            the new tipo uso
	 */
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	@Override
	public String toString() {
		return "LoginUI [tipoUso=" + this.tipoUso + ", emailLogin=" + this.emailLogin + ", passLogin=" + this.passLogin
				+ ", passNew=" + this.passNew + ", passRepetida=" + this.passRepetida + "]";
	}
}
