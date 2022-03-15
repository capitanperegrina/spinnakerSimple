package com.capitanperegrina.i18n.modelo.util;

import java.util.Collections;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.basebeans.BaseSingleton;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.i18n.modelo.dao.impl.tabla.DivisasDao;
import com.capitanperegrina.i18n.modelo.entidad.tabla.DivisasPOJO;

/**
 * The Class Divisas.
 */
@Component("divisas")
public class Divisas extends BaseSingleton<String, DivisasPOJO> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 926272413230791708L;

	/** The log. */
	private static Logger log = Logger.getLogger(Idiomas.class);

	/** The divisas dao. */
	@Autowired
	private transient DivisasDao divisasDao;

	/**
	 * Instantiates a new divisas.
	 */
	private Divisas() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.capitanperegrina.common.basebeans.BaseSingleton#initialize()
	 */
	@PostConstruct
	@Override
	protected synchronized void initialize() {
		log.debug("Iniciando " + StackTraceUtil.getCallerInfo());

		try {
			this.lista = this.divisasDao.buscaTodos();
			this.options = new HashMap<String, String>(this.lista.size());
			this.mapa = new HashMap<String, DivisasPOJO>(this.lista.size());
			for (final DivisasPOJO divisa : this.lista) {
				this.mapa.put(divisa.getCodigoDivisa(), divisa);
				this.options.put(divisa.getCodigoDivisa(), divisa.getCodigoDivisa() + " - "
						+ divisa.getDescripcionDivisa() + " (" + divisa.getSimboloDivisa() + ")");
			}
			this.mapa = Collections.unmodifiableMap(this.mapa);
		} finally {
			log.debug("Finalizando " + StackTraceUtil.getCallerInfo());
		}
	}
}
