package com.capitanperegrina.common.ui.adapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;

import com.capitanperegrina.common.i18n.Mensajes;
import com.capitanperegrina.common.modelo.entidad.tabla.ParamsPOJO;
import com.capitanperegrina.common.ui.bean.ParamsUI;

public class ParamsAdapter {

	public static ParamsUI toParamsUI( ParamsPOJO in ) {
		ParamsUI out = new ParamsUI();
		final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, LocaleContextHolder.getLocale());
		out.setIdParam(in.getPk().getIdParam());
		out.setVisible(in.getVisible());
		out.setValor(in.getValor());
		out.setTipoJava(in.getTipoJava());
		out.setTipoJs(in.getTipoJs());
		out.setIdUsuarioAlta(in.getIdUsuarioAlta().toString());
		out.setFecAlta(df.format(in.getFecModif().getTime()));
		out.setIdUsuarioModif(in.getIdUsuarioModif().toString());
		out.setFecModif(df.format(in.getFecModif().getTime()));
		out.setLabel( Mensajes.getStringSafe( "label." + in.getPk().getIdParam(), LocaleContextHolder.getLocale() ) );
		out.setIdParamWeb(in.getPk().getIdParam().replace('.', '-'));
		return out;
	}
	
	public static List<ParamsUI> toParamsUIList(List<ParamsPOJO> lista) {
		if ( lista == null ) {
			return null;
		}
		List<ParamsUI> out = new ArrayList<>();
		for ( ParamsPOJO p : lista ) {
			out.add( toParamsUI( p ) );
		}
		return out;
	}
	
	private ParamsAdapter() {
		super();
	}
}
