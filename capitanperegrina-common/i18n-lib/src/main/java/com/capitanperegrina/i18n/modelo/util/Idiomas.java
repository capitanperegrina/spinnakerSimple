package com.capitanperegrina.i18n.modelo.util;

import java.util.Collections;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.basebeans.BaseSingleton;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.i18n.modelo.dao.impl.tabla.IdiomasDao;
import com.capitanperegrina.i18n.modelo.entidad.tabla.IdiomasPOJO;

/**
 * Clase tabla de ayuda para idiomas.
 */
@Component("idiomas")
public class Idiomas extends BaseSingleton<String, IdiomasPOJO> {

	private static final long serialVersionUID = 4884191899163578419L;

	private static Logger log = Logger.getLogger(Idiomas.class);

	@Autowired
	private transient IdiomasDao idiomasDao;

	private Idiomas() {
		super();
	}

	@PostConstruct
	@Override
	protected synchronized void initialize() {
		log.debug("Iniciando " + StackTraceUtil.getCallerInfo());

		try {
			this.lista = this.idiomasDao.buscaTodos();

	        this.mapa = new HashMap<String,IdiomasPOJO>( this.lista.size() );
			for (final IdiomasPOJO idioma : this.lista) {
				this.mapa.put(idioma.getLang(), idioma);
			}
			this.mapa = Collections.unmodifiableMap(this.mapa);
		} finally {
			log.debug("Finalizando " + StackTraceUtil.getCallerInfo());
		}
	}
}
