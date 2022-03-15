package com.capitanperegrina.i18n.modelo.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.i18n.modelo.common.basebeans.I18nBaseSingleton;
import com.capitanperegrina.i18n.modelo.dao.impl.tabla.IdiomasDao;
import com.capitanperegrina.i18n.modelo.dao.impl.tabla.PaisesDao;
import com.capitanperegrina.i18n.modelo.entidad.tabla.IdiomasPOJO;
import com.capitanperegrina.i18n.modelo.entidad.tabla.PaisesPOJO;

/**
 * Clase tabla de ayuda para paises.
 */
@Component
public class Paises extends I18nBaseSingleton<String, PaisesPOJO> {

	private static final long serialVersionUID = 4884191899163578419L;

	private static Logger log = Logger.getLogger(Paises.class);

	@Autowired
	private transient IdiomasDao idiomasDao;

	@Autowired
	private transient PaisesDao paisesDao;

	private Paises() {
		super();
	}

	@PostConstruct
	@Override
	protected synchronized void initialize() {
		log.debug("Iniciando " + StackTraceUtil.getCallerInfo());

		try {
			final List<IdiomasPOJO> idiomas = this.idiomasDao.buscaTodosOrdenado();
			this.mapa = new HashMap<String, Map<String, PaisesPOJO>>(idiomas.size());
			this.options = new HashMap<String, Map<String, String>>(idiomas.size());
			this.lista = new HashMap<String, List<PaisesPOJO>>(idiomas.size());
			for (final IdiomasPOJO idioma : idiomas) {
				if (this.mapa.get(idioma.getLang()) == null) {
					this.mapa.put(idioma.getLang(), new HashMap<String, PaisesPOJO>());
				}
				if (this.options.get(idioma.getLang()) == null) {
					this.options.put(idioma.getLang(), new HashMap<String, String>());
				}
				final PaisesPOJO paisFiltro = new PaisesPOJO();
				paisFiltro.setLang(idioma.getLang());
				this.lista.put(idioma.getLang(), this.paisesDao.buscaVarios(paisFiltro, true));
				for (final PaisesPOJO pais : this.lista.get(idioma.getLang())) {
					this.mapa.get(idioma.getLang()).put(pais.getCodigo().toString(), pais);
					this.options.get(idioma.getLang()).put(pais.getCodigo().toString(), pais.getNombrePais());
				}
			}
			this.mapa = Collections.unmodifiableMap(this.mapa);
			this.options = Collections.unmodifiableMap(this.options);
			this.lista = Collections.unmodifiableMap(this.lista);
		} finally {
			log.debug("Finalizando " + StackTraceUtil.getCallerInfo());
		}
	}
}
