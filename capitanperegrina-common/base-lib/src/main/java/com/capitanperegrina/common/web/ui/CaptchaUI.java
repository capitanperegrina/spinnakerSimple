package com.capitanperegrina.common.web.ui;

import java.io.Serializable;

public class CaptchaUI implements Serializable {

	private static final long serialVersionUID = -4776301829369759769L;

	protected String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Override
	public String toString() {
		return "CaptchaUI [captcha=" + captcha + "]";
	}	
}
