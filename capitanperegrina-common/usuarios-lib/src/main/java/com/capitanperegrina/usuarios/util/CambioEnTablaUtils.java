package com.capitanperegrina.usuarios.util;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;

import com.capitanperegrina.common.basebeans.CambioEnTabla;
import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.usuarios.modelo.entidad.tabla.UsuarioPOJO;

public class CambioEnTablaUtils {

    public static CambioEnTabla creaCambioEnTabla(final HttpServletRequest request) {
        final CambioEnTabla ct = new CambioEnTabla();
        ct.setUsu(((UsuarioPOJO) request.getSession().getAttribute(DefaultGlobalNames.USERSESSION)).getPk().getIdUsuario());
        ct.setFec(Calendar.getInstance());
        ct.setIp(request.getRemoteAddr());
        ct.setLocale(LocaleContextHolder.getLocale());
        return ct;
    }
}
