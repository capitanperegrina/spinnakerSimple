package com.spinnakersimple.web.ui.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.i18n.Mensajes;
import com.capitanperegrina.common.seguridad.Criptografia;
import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.utils.Fecha;
import com.capitanperegrina.common.utils.Formateador;
import com.capitanperegrina.common.utils.Imagenes;
import com.capitanperegrina.common.web.FormUtils;
import com.capitanperegrina.i18n.modelo.util.Divisas;
import com.capitanperegrina.i18n.modelo.util.Paises;
import com.spinnakersimple.modelo.bean.AnuncioCompleto;
import com.spinnakersimple.modelo.bean.MisAnunciosBean;
import com.spinnakersimple.modelo.entidad.AnuncioPOJO;
import com.spinnakersimple.modelo.entidad.CompradoresPOJO;
import com.spinnakersimple.modelo.entidad.FotografiaPOJO;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;
import com.spinnakersimple.modelo.entidad.UsuarioPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.modelo.vista.UsuarioCompletoView;
import com.spinnakersimple.util.tablasayuda.TiposBarco;
import com.spinnakersimple.web.ui.bean.AnuncioCompletoUI;
import com.spinnakersimple.web.ui.bean.AnuncioUI;
import com.spinnakersimple.web.ui.bean.CompradoresUI;
import com.spinnakersimple.web.ui.bean.FotografiaUI;
import com.spinnakersimple.web.ui.bean.MisAnunciosUI;
import com.spinnakersimple.web.ui.bean.TipoVelaUI;
import com.spinnakersimple.web.ui.bean.UsuarioUI;
import com.spinnakersimple.web.ui.bean.VenderVelaUI;

/**
 * The Class AdaptToUI.
 *
 * @author <a href="mailto:yo@capitanperegrina.com">Capitán Peregrina</a>
 */
@Component
public class AdaptToUI {

    /** The t criptografia. */
    @Autowired
    Criptografia tCriptografia;

    @Autowired
    MessageSource tMessageSource;

    @Autowired
    Paises tPaises;

    @Autowired
    TiposBarco tTiposBarco;

    @Autowired
    Divisas tDivisas;

    /** The criptografia. */
    private static Criptografia criptografia;

    private static MessageSource messageSource;

    private static Paises paises;

    private static Divisas divisas;

    private static TiposBarco tiposBarco;

    private static final SimpleDateFormat formatoFechaOrdenacion = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Inicializa la propiedad estática criptografia.
     */
    @PostConstruct
    public void init() {
        AdaptToUI.criptografia = this.tCriptografia;
        AdaptToUI.paises = this.tPaises;
        AdaptToUI.divisas = this.tDivisas;
        AdaptToUI.tiposBarco = this.tTiposBarco;
        AdaptToUI.messageSource = this.tMessageSource;
    }

    /**
     * To mis anuncios bean.
     *
     * @param in the bean
     * @return the mis anuncios UI
     */
    public static MisAnunciosUI toMisAnunciosBean(final MisAnunciosBean in) {
        final MisAnunciosUI out = new MisAnunciosUI();
        out.setTipoAnuncio(in.getTipoAnuncio());
        out.setTipoVela(in.getTipoVela().toString());
        return out;
    }

    /**
     * To anuncio UI.
     *
     * @param in the obj
     * @return the anuncio UI
     */
    public static AnuncioUI toAnuncioUI(final AnuncioPOJO in) {
        if (in == null) {
            return null;
        }
        final AnuncioUI out = new AnuncioUI();
        final Locale locale = LocaleContextHolder.getLocale();
        final NumberFormat fLon = NumberFormat.getNumberInstance(locale);
        fLon.setMinimumFractionDigits(SpinnakerSimpleGlobals.DECIMALES_LONGITUDES);
        fLon.setMaximumFractionDigits(SpinnakerSimpleGlobals.DECIMALES_LONGITUDES);
        final DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, LocaleContextHolder.getLocale());
        out.setIdAnuncio(in.getIdAnuncio().toString());
        out.setTipoBarco(in.getTipoBarco().toString());
        out.setTipoVela(in.getTipoVela().toString());
        out.setTipoVelaDescripcion(Mensajes.getStringSafe("tipoVela." + in.getTipoVela().toString()));
        out.setTituloAnuncio(in.getTituloAnuncio());
        out.setGramaje(in.getGramaje() != null ? fLon.format(in.getGramaje()) : SpinnakerSimpleGlobals.NADA);
        out.setGratil(in.getGratil() != null ? fLon.format(in.getGratil()) : SpinnakerSimpleGlobals.NADA);
        out.setBaluma(in.getBaluma() != null ? fLon.format(in.getBaluma()) : SpinnakerSimpleGlobals.NADA);
        out.setPujamen(in.getPujamen() != null ? fLon.format(in.getPujamen()) : SpinnakerSimpleGlobals.NADA);
        out.setCaidaProa(in.getCaidaProa() != null ? fLon.format(in.getCaidaProa()) : SpinnakerSimpleGlobals.NADA);
        out.setCaidaPopa(in.getCaidaPopa() != null ? fLon.format(in.getCaidaPopa()) : SpinnakerSimpleGlobals.NADA);
        out.setSuperficie(in.getSuperficie() != null ? fLon.format(in.getSuperficie()) : SpinnakerSimpleGlobals.NADA);
        out.setTipoCometa(in.getTipoCometa());
        if (StringUtils.isNotEmpty(in.getTipoCometa())) {
            out.setTipoCometaDescripcion(
                    Mensajes.getStringSafe("vela.tipoCometa." + in.getTipoCometa(), LocaleContextHolder.getLocale()));
        } else {
            out.setTipoCometaDescripcion("");
        }
        out.setDescripcion(in.getDescripcion());
        out.setPrecio(in.getPrecio() != null ? fLon.format(in.getPrecio()) : SpinnakerSimpleGlobals.NADA);
        out.setPais(in.getPais());
        out.setCaduca(df.format(in.getCaduca().getTime()));
        out.setVisible(in.getVisible().toString());
        out.setListado(in.getListado().toString());
        out.setVisto(in.getVisto().toString());
        out.setComentado(in.getComentado().toString());
        out.setIdUsuarioAlta(in.getIdUsuarioAlta().toString());
        out.setFecAlta(df.format(in.getFecAlta().getTime()));
        out.setIdUsuarioModif(in.getIdUsuarioModif().toString());
        out.setFecModif(df.format(in.getFecModif().getTime()));
        if (Cadenas.trimNoNulo(in.getDescripcion()).length() > 120) {
            out.setExtractoAnuncio(in.getDescripcion().substring(0, 120));
        } else {
            out.setExtractoAnuncio(in.getDescripcion());
        }
        if (in.getIdAnuncio() != null) {
            out.setIdAnuncioCodificado(AdaptToUI.criptografia.codificaParaUrl(in.getIdAnuncio().toString()));
        } else {
            out.setIdAnuncioCodificado(null);
        }
        out.setFechaOrdenacion(formatoFechaOrdenacion.format(in.getFecAlta().getTime()));
        out.setDiasParaCaducar(
                Long.toString(ChronoUnit.DAYS.between(Calendar.getInstance().toInstant(), in.getCaduca().toInstant())));
        out.setDiasParaCaducarOrdenacion(Formateador.fill(out.getDiasParaCaducar(), '0', 5, Formateador.IZQUIERDA));
        return out;
    }

    /**
     * To anuncio UI list.
     *
     * @param in the lista
     * @return the list
     */
    public static List<AnuncioUI> toAnuncioUIList(final List<AnuncioPOJO> in) {
        final List<AnuncioUI> out = new ArrayList<>();
        if (!CollectionUtils.isEmpty(in)) {
            for (final AnuncioPOJO bean : in) {
                out.add(toAnuncioUI(bean));
            }
        }
        return out;
    }

    public static UsuarioUI toUsuarioUI(final UsuarioCompletoView in) {
        final UsuarioUI ui = toUsuarioUI((UsuarioPOJO) in);
        ui.setNumAnuncios(in.getNumAnuncios());
        return ui;
    }

    /**
     * To usuario UI.
     *
     * @param in the dto
     * @return the usuario UI
     */
    public static UsuarioUI toUsuarioUI(final UsuarioPOJO in) {
        final UsuarioUI out = new UsuarioUI();
        out.setIdUsuario(in.getIdUsuario().toString());
        out.setNombre(in.getNombre());
        out.setApellidos(in.getApellidos());
        out.setMail(in.getMail());
        out.setMovil(in.getMovil());
        out.setDireccion1(in.getDireccion1());
        out.setDireccion2(in.getDireccion2());
        out.setProvincia(in.getProvincia());
        out.setPais(in.getPais());
        out.setDivisa(in.getDivisa());
        out.setCodPostal(in.getCodPostal());
        out.setAdmin(in.getAdmin());
        out.setPass(in.getPass());
        out.setLang(in.getLang());
        out.setIp(in.getIp());
        out.setFallosLogin(in.getFallosLogin().toString());
        out.setIdUsuarioAlta(in.getIdUsuarioAlta().toString());
        out.setFecAlta(Fecha.calendar2DateHourString(in.getFecAlta()));
        out.setIdUsuarioModif(in.getIdUsuarioModif().toString());
        out.setFecModif(Fecha.calendar2DateHourString(in.getFecModif()));
        out.setFechaOrdenacion(formatoFechaOrdenacion.format(in.getFecAlta().getTime()));
        out.setFechaAlta(formatoFechaOrdenacion.format(in.getFecAlta().getTime()));
        out.setNombrePropietario(StringUtils
                .trimToEmpty(StringUtils.trimToEmpty(in.getNombre()) + " " + StringUtils.trimToEmpty(in.getApellidos())));
        final StringBuilder nombreCompleto = new StringBuilder();
        if (!StringUtils.isEmpty(in.getApellidos())) {
            nombreCompleto.append(in.getApellidos()).append(", ");
        }
        nombreCompleto.append(in.getNombre());
        out.setNombreCompleto(nombreCompleto.toString());

        if (in.getIdUsuario() != null) {
            out.setIdUsuarioCodificado(AdaptToUI.criptografia.codificaParaUrl(in.getIdUsuario().toString()));
        } else {
            out.setIdUsuarioCodificado(null);
        }

        final StringBuilder dc = new StringBuilder();
        if (!StringUtils.isEmpty(in.getDireccion1())) {
            dc.append(in.getDireccion1());
            if (!StringUtils.isEmpty(in.getDireccion2()) || !StringUtils.isEmpty(in.getCodPostal())
                    || !StringUtils.isEmpty(in.getProvincia()) || !StringUtils.isEmpty(in.getPais())) {
                dc.append(DefaultGlobalNames.HTML_BR);
            }
        }

        if (!StringUtils.isEmpty(in.getDireccion2())) {
            dc.append(in.getDireccion2());
            if (!StringUtils.isEmpty(in.getCodPostal()) || !StringUtils.isEmpty(in.getProvincia())
                    || !StringUtils.isEmpty(in.getPais())) {
                dc.append(DefaultGlobalNames.HTML_BR);
            }
        }

        if (!StringUtils.isEmpty(in.getCodPostal())) {
            dc.append(in.getCodPostal());
            if (!StringUtils.isEmpty(in.getProvincia())) {
                dc.append(" ");
            }
            if (!StringUtils.isEmpty(in.getPais())) {
                dc.append(DefaultGlobalNames.HTML_BR);
            }
        }

        if (!StringUtils.isEmpty(in.getPais())) {
            dc.append(paises.getPojo(LocaleContextHolder.getLocale().getLanguage().toUpperCase(), in.getPais()).getNombrePais()
                    .toUpperCase());
        }
        out.setDireccionCompleta(dc.toString());
        return out;
    }

    /**
     * To usuario UI list.
     *
     * @param in the lista
     * @return the list
     */
    public static List<UsuarioUI> toUsuarioUIList(final List<UsuarioPOJO> in) {
        final List<UsuarioUI> out = new ArrayList<>();
        if (!CollectionUtils.isEmpty(in)) {
            for (final UsuarioPOJO pojo : in) {
                out.add(toUsuarioUI(pojo));
            }
        }
        return out;
    }

    /**
     * To usuario UI list.
     *
     * @param in the lista
     * @return the list
     */
    public static List<UsuarioUI> toUsuarioUIListFromUsuarioCompletoView(final List<UsuarioCompletoView> in) {
        final List<UsuarioUI> out = new ArrayList<>();
        if (!CollectionUtils.isEmpty(in)) {
            for (final UsuarioCompletoView pojo : in) {
                out.add(toUsuarioUI(pojo));
            }
        }
        return out;
    }

    /**
     * To fotografia UI.
     *
     * @param in the f
     * @return the fotografia UI
     */
    public static FotografiaUI toFotografiaUI(final FotografiaPOJO in) {
        final FotografiaUI out = new FotografiaUI();
        out.setIdFotografia(in.getIdFotografia() != null ? in.getIdFotografia().toString() : "");
        out.setIdAnuncio(in.getIdAnuncio() != null ? in.getIdAnuncio().toString() : "");
        out.setImagen(Imagenes.imgToBase64(in.getTipo(), in.getImagen()));
        out.setIdAnuncioCodificado(criptografia.codificaParaUrl(out.getIdAnuncio()));
        out.setIdFotografiaCodificada(criptografia.codificaParaUrl(out.getIdFotografia()));
        return out;
    }

    /**
     * To fotografia UI.
     *
     * @param in the l
     * @return the list
     */
    public static List<FotografiaUI> toFotografiaUIList(final List<FotografiaPOJO> in) {
        final List<FotografiaUI> out = new ArrayList<>();
        if (!CollectionUtils.isEmpty(in)) {
            for (final FotografiaPOJO pojo : in) {
                out.add(toFotografiaUI(pojo));
            }
        }
        return out;
    }

    /**
     * To anuncio completo UI.
     *
     * @param in the ac
     * @return the anuncio completo UI
     */
    public static AnuncioCompletoUI toAnuncioCompletoUI(final AnuncioCompleto in) {
        final AnuncioCompletoUI out = new AnuncioCompletoUI();
        out.setAnuncio(toAnuncioUI(in.getAnuncio()));
        out.setTipoVela(toTipoVelaUI(in.getTipoVela()));
        out.setUsuario(toUsuarioUI(in.getUsuario()));
        out.setFotos(toFotografiaUIList(in.getFotos()));
        // TODO - ui.setMensajes( ac.toMensajesUIList( ac.getMensajes() ) );
        return out;
    }

    public static TipoVelaUI toTipoVelaUI(final TipoVelaPOJO in) {
        if (in == null) {
            return null;
        }
        final TipoVelaUI out = new TipoVelaUI();
        out.setIdTipoVela(in.getIdTipoVela().toString());
        out.setTipoVela(in.getTipoVela());
        out.setUrlTipoVela(in.getUrlTipoVela());
        out.setImagenTipoVela(in.getImagenTipoVela());
        out.setGratil(in.getGratil());
        out.setBaluma(in.getBaluma());
        out.setPujamen(in.getPujamen());
        out.setCaidaProa(in.getCaidaProa());
        out.setCaidaPopa(in.getCaidaPopa());
        out.setSuperficie(in.getSuperficie());
        out.setTipoCometa(in.getTipoCometa());
        out.setTipoVelaLocalizado(AdaptToUI.messageSource.getMessage("tipoVela." + in.getIdTipoVela().toString(), null,
                LocaleContextHolder.getLocale()));
        return out;
    }

    /**
     * To anuncio completo UI list.
     *
     * @param in the l
     * @return the list
     */
    public static List<AnuncioCompletoUI> toAnuncioCompletoUIList(final List<AnuncioCompleto> in) {
        final List<AnuncioCompletoUI> out = new ArrayList<>();
        for (final AnuncioCompleto pojo : in) {
            out.add(toAnuncioCompletoUI(pojo));
        }
        return out;
    }

    /**
     * To vender vela UI.
     *
     * @param in the ac
     * @return the vender vela UI
     */
    public static VenderVelaUI toVenderVelaUI(final AnuncioCompleto in) {
        final VenderVelaUI out = new VenderVelaUI();
        out.setIdAnuncio(in.getAnuncio().getIdAnuncio().toString());
        out.setTituloAnuncio(in.getAnuncio().getTituloAnuncio());
        out.setTipoBarco(in.getAnuncio().getTipoBarco().toString());
        out.setTipoClase(
                tiposBarco.getPojo(LocaleContextHolder.getLocale().getLanguage(), in.getAnuncio().getTipoBarco()).getTipoClase());
        out.setTipoBarcoDescripcion(Mensajes.getStringSafe("tipoBarco."
                + tiposBarco.getPojo(LocaleContextHolder.getLocale().getLanguage(), in.getAnuncio().getTipoBarco()).getTipoBarco(),
                LocaleContextHolder.getLocale()));
        out.setTipoVela(in.getAnuncio().getTipoVela().toString());
        out.setGramaje(FormUtils.toStringForm(in.getAnuncio().getGramaje()));
        out.setGratil(FormUtils.toStringForm(in.getAnuncio().getGratil()));
        out.setBaluma(FormUtils.toStringForm(in.getAnuncio().getBaluma()));
        out.setPujamen(FormUtils.toStringForm(in.getAnuncio().getPujamen()));
        out.setCaidaProa(FormUtils.toStringForm(in.getAnuncio().getCaidaProa()));
        out.setCaidaPopa(FormUtils.toStringForm(in.getAnuncio().getCaidaPopa()));
        out.setSuperficie(FormUtils.toStringForm(in.getAnuncio().getSuperficie()));
        out.setTipoCometa(in.getAnuncio().getTipoCometa());
        out.setDescripcion(in.getAnuncio().getDescripcion());
        out.setPrecio(FormUtils.toStringForm(in.getAnuncio().getPrecio()));
        out.setPaisVela(FormUtils.toStringForm(in.getAnuncio().getPais()));
        out.setDivisa(in.getUsuario().getDivisa());
        out.setCaduca(FormUtils.toStringForm(in.getAnuncio().getCaduca()));
        return out;
    }

    public static CompradoresUI toCompradoresUI(final CompradoresPOJO in) {
        final CompradoresUI out = new CompradoresUI();
        out.setIdAnuncio(in.getIdAnuncio().toString());
        out.setNombre(in.getNombre());
        out.setIdComprador(in.getIdComprador().toString());
        out.setMail(in.getMail());
        out.setObservaciones(in.getObservaciones());
        out.setFecha(Fecha.calendar2DateStringDDMMYYYY(in.getFecha(), '/'));
        out.setTelefono(in.getTelefono());
        out.setRevisado(in.getRevisado());
        out.setIdCompradorCodificado(AdaptToUI.criptografia.codificaParaUrl(in.getIdComprador().toString()));
        out.setFechaOrdenacion(formatoFechaOrdenacion.format(in.getFecha().getTime()));
        return out;
    }

    public static List<CompradoresUI> toCompradoresUIList(final List<CompradoresPOJO> in) {
        final List<CompradoresUI> out = new ArrayList<>();
        for (final CompradoresPOJO pojo : in) {
            out.add(toCompradoresUI(pojo));
        }
        return out;
    }

}
