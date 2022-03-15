package com.spinnakersimple.web.cron;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.utils.StackTraceUtil;
import com.spinnakersimple.servicios.AnunciosService;

@Component
public class SpinnakerSimpleCron {

	static Logger log = Logger.getLogger(SpinnakerSimpleCron.class);

	@Autowired
	AnunciosService anuncioService;

	@Scheduled(cron = "0 0 9/12 * * ?")
	public void publicaEnRedesSociales() {
		if (log.isDebugEnabled()) {
			log.debug(">" + StackTraceUtil.getCallerInfo());
		}
		try {
			final CambioEnTabla ct = new CambioEnTabla();
			ct.setUsu(1);
			ct.setFec(Calendar.getInstance());
			log.info( this.anuncioService.publicaEnRedesSociales(ct) );
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("<" + StackTraceUtil.getCallerInfo());
			}
		}
	}

	@Scheduled(cron = "0 0 10 * * ?")
	public void adExpirarionNotify() {
		if (log.isDebugEnabled()) {
			log.debug(">" + StackTraceUtil.getCallerInfo());
		}
		try {
			this.anuncioService.notificaAnunciosQueCaducanEnNDias(14);
			this.anuncioService.notificaAnunciosQueCaducanEnNDias(7);
			this.anuncioService.notificaAnunciosQueCaducanEnNDias(1);
		} finally {
			if (log.isDebugEnabled()) {
				log.debug("<" + StackTraceUtil.getCallerInfo());
			}
		}
	}
}
