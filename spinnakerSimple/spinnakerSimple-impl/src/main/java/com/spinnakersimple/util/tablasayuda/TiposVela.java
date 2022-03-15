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
import com.spinnakersimple.modelo.dao.TipoVelaDao;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;

/**
 * Clase tabla de ayuda para tipos de vela.
 */
@Component("tiposVela")
public class TiposVela extends I18nBaseSingleton<Integer, TipoVelaPOJO> {

	private static final long serialVersionUID = 4884191899163578419L;

	private static Logger log = Logger.getLogger(TiposVela.class);

	public static final Integer GENOVA = 3;

	@Autowired
	private transient IdiomasDao idiomasDao;

	@Autowired
	private transient TipoVelaDao tipovelaDao;

	private TiposVela() {
		super();
	}

	@PostConstruct
	@Override
	protected synchronized void initialize() {
		log.debug("Iniciando " + StackTraceUtil.getCallerInfo());

		try {
			final List<IdiomasPOJO> idiomas = this.idiomasDao.buscaTodos();
			this.mapa = new HashMap<String, Map<Integer, TipoVelaPOJO>>(idiomas.size());
			this.options = new HashMap<String, Map<String, String>>(idiomas.size());
			this.lista = new HashMap<String, List<TipoVelaPOJO>>(idiomas.size());
			for (final IdiomasPOJO idioma : idiomas) {
				if (this.mapa.get(idioma.getLang()) == null) {
					this.mapa.put(idioma.getLang(), new HashMap<Integer, TipoVelaPOJO>());
				}
				if (this.options.get(idioma.getLang()) == null) {
					this.options.put(idioma.getLang(), new HashMap<String, String>());
				}
				this.lista.put(idioma.getLang(), this.tipovelaDao.buscaTodos());
				for (final TipoVelaPOJO tipoVela : this.lista.get(idioma.getLang())) {
					tipoVela.setTipoVela(Mensajes.getStringSafe("tipoVela." + tipoVela.getIdTipoVela(),
							new Locale(idioma.getLang())));
					this.mapa.get(idioma.getLang()).put(tipoVela.getIdTipoVela(), tipoVela);
					this.options.get(idioma.getLang()).put(tipoVela.getIdTipoVela().toString(), tipoVela.getTipoVela());
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
