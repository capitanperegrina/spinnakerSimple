package com.capitanperegrina.i18n.modelo.entidad.tabla;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.capitanperegrina.common.exceptions.ServicioErrorException;

/**
 * POJO identificado con la tabla <code>divisas</code>
 */
public class DivisasPOJO implements Serializable {
	private static final long serialVersionUID = 63321061227L;

	protected String codigoDivisa;
	protected String descripcionDivisa;
	protected String simboloDivisa;

	/**
	 * Constructor por defecto.
	 */
	public DivisasPOJO() {
		super();
	}

	public String getCodigoDivisa() {
		return this.codigoDivisa;
	}

	public void setCodigoDivisa(String codigoDivisa) {
		this.codigoDivisa = codigoDivisa;
	}

	public String getDescripcionDivisa() {
		return this.descripcionDivisa;
	}

	public void setDescripcionDivisa(String descripcionDivisa) {
		this.descripcionDivisa = descripcionDivisa;
	}

	public String getSimboloDivisa() {
		return this.simboloDivisa;
	}

	public void setSimboloDivisa(String simboloDivisa) {
		this.simboloDivisa = simboloDivisa;
	}

	public String getDescripcionCompleta() {
		return this.codigoDivisa + " - " + this.descripcionDivisa + " - " + this.simboloDivisa + "";
	}

	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ReflectionToStringBuilder.toString(this);
	}

	public static BigDecimal parseMoney(String numberString, Locale locale) {
		try {
			final DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(locale);
			df.setParseBigDecimal(true);
			final BigDecimal bd = (BigDecimal) df.parseObject(numberString);
			return bd;
		} catch (final ParseException e) {
			e.printStackTrace();
			throw new ServicioErrorException(e);
		}
	}

	public static void main(String[] args) {
		final BigDecimal amount = new BigDecimal("1234567.89");
		final Format formatEs = NumberFormat.getCurrencyInstance(new Locale("es"));
		final Format formatFr = NumberFormat.getCurrencyInstance(new Locale("fr"));
		final Format formatIt = NumberFormat.getCurrencyInstance(new Locale("it"));
		final Format formatDe = NumberFormat.getCurrencyInstance(new Locale("de"));
		final Format formatEn = NumberFormat.getCurrencyInstance(new Locale("en"));
		final Format formatPt = NumberFormat.getCurrencyInstance(new Locale("pt"));

		System.out.println(formatEs.format(amount).replace("¤", "").trim());
		System.out.println(formatFr.format(amount).replace("¤", "").trim());
		System.out.println(formatIt.format(amount).replace("¤", "").trim());
		System.out.println(formatDe.format(amount).replace("¤", "").trim());
		System.out.println(formatEn.format(amount).replace("¤", "").trim());
		System.out.println(formatPt.format(amount).replace("¤", "").trim());

		System.out
				.println(formatEs.format(parseMoney(formatEs.format(amount).replace("¤", "").trim(), new Locale("es")))
						.replace("¤", "").trim());
		System.out
				.println(formatFr.format(parseMoney(formatFr.format(amount).replace("¤", "").trim(), new Locale("fr")))
						.replace("¤", "").trim());
		System.out
				.println(formatIt.format(parseMoney(formatIt.format(amount).replace("¤", "").trim(), new Locale("it")))
						.replace("¤", "").trim());
		System.out
				.println(formatDe.format(parseMoney(formatDe.format(amount).replace("¤", "").trim(), new Locale("de")))
						.replace("¤", "").trim());
		System.out
				.println(formatEn.format(parseMoney(formatEn.format(amount).replace("¤", "").trim(), new Locale("en")))
						.replace("¤", "").trim());
		System.out
				.println(formatPt.format(parseMoney(formatPt.format(amount).replace("¤", "").trim(), new Locale("pt")))
						.replace("¤", "").trim());

		System.out.println(parseMoney(formatEs.format(amount).replace("¤", "").trim(), new Locale("es")).toString());
		System.out.println(parseMoney(formatFr.format(amount).replace("¤", "").trim(), new Locale("fr")).toString());
		System.out.println(parseMoney(formatIt.format(amount).replace("¤", "").trim(), new Locale("it")).toString());
		System.out.println(parseMoney(formatDe.format(amount).replace("¤", "").trim(), new Locale("de")).toString());
		System.out.println(parseMoney(formatEn.format(amount).replace("¤", "").trim(), new Locale("en")).toString());
		System.out.println(parseMoney(formatPt.format(amount).replace("¤", "").trim(), new Locale("pt")).toString());
	}
}