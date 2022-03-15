package com.spinnakersimple.util.tablasayuda;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.i18n.Mensajes;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.capitanperegrina.i18n.modelo.common.basebeans.I18nBaseSingleton;
import com.capitanperegrina.i18n.modelo.dao.impl.tabla.IdiomasDao;
import com.capitanperegrina.i18n.modelo.entidad.tabla.IdiomasPOJO;
import com.spinnakersimple.modelo.dao.TipoBarcoDao;
import com.spinnakersimple.modelo.entidad.TipoBarcoPOJO;

@Component("tiposBarco")
public class TiposBarco extends I18nBaseSingleton<Integer, TipoBarcoPOJO> {

	private static final long serialVersionUID = 4884191899163578419L;

	private static Logger log = Logger.getLogger(TiposBarco.class);

	public static final Integer CRUCERO = 1;

	@Autowired
	private transient IdiomasDao idiomasDao;

	@Autowired
	private transient TipoBarcoDao tipoBarcoDao;

	private TiposBarco() {
		super();
	}

	@PostConstruct
	@Override
	protected synchronized void initialize() {
		try {
			final List<IdiomasPOJO> idiomas = this.idiomasDao.buscaTodos();
			this.mapa = new HashMap<String, Map<Integer, TipoBarcoPOJO>>(idiomas.size());
			this.options = new HashMap<String, Map<String, String>>(idiomas.size());
			this.lista = new HashMap<String, List<TipoBarcoPOJO>>(idiomas.size());
			for (final IdiomasPOJO idioma : idiomas) {
				if (this.mapa.get(idioma.getLang()) == null) {
					this.mapa.put(idioma.getLang(), new HashMap<Integer, TipoBarcoPOJO>());
				}
				if (this.options.get(idioma.getLang()) == null) {
					this.options.put(idioma.getLang(), new HashMap<String, String>());
				}
				this.lista.put(idioma.getLang(), this.tipoBarcoDao.buscaTodos());
				for (final TipoBarcoPOJO tipoBarco : this.lista.get(idioma.getLang())) {
					tipoBarco.setTipoBarco(Mensajes.getStringSafe("tipoBarco." + tipoBarco.getTipoBarco(),
							new Locale(idioma.getLang())));
					this.mapa.get(idioma.getLang()).put(tipoBarco.getIdTipoBarco(), tipoBarco);
					this.options.get(idioma.getLang()).put(tipoBarco.getIdTipoBarco().toString(), tipoBarco.getTipoBarco());
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
