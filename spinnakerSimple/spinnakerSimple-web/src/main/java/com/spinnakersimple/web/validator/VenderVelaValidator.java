package com.spinnakersimple.web.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.capitanperegrina.common.config.DefaultGlobalNames;
import com.capitanperegrina.common.utils.Cadenas;
import com.capitanperegrina.common.validators.Validadores;
import com.capitanperegrina.common.web.FormUtils;
import com.spinnakersimple.modelo.entidad.TipoVelaPOJO;
import com.spinnakersimple.modelo.util.SpinnakerSimpleGlobals;
import com.spinnakersimple.util.tablasayuda.TiposVela;
import com.spinnakersimple.web.ui.bean.VenderVelaUI;

@Component
public class VenderVelaValidator implements Validator {

    public static final String PNG_MIME_TYPE = "image/png";
    public static final String JPEG_MIME_TYPE = "image/jpeg";
    public static final String GIF_MIME_TYPE = "image/gif";

    protected static final String FIELD_IDANUNCIO = "idAnuncio";
    protected static final String FIELD_TITULOANUNCIO = "tituloAnuncio";
    protected static final String FIELD_TIPOBARCO = "tipoBarco";
    protected static final String FIELD_TIPOVELA = "tipoVela";
    protected static final String FIELD_GRAMAJE = "gramaje";
    protected static final String FIELD_GRATIL = "gratil";
    protected static final String FIELD_BALUMA = "baluma";
    protected static final String FIELD_PUJAMEN = "pujamen";
    protected static final String FIELD_CAIDA_PROA = "caidaProa";
    protected static final String FIELD_CAIDA_POPA = "caidaPopa";
    protected static final String FIELD_SUPERFICIE = "superficie";
    protected static final String FIELD_TIPO_COMETA = "tipoCometa";
    protected static final String FIELD_NUEVA = "nueva";
    protected static final String FIELD_ROCES = "roces";
    protected static final String FIELD_DESPERFECTOS = "desperfectos";
    protected static final String FIELD_REPARADA = "reparada";
    protected static final String FIELD_DEFORMADA = "deformada";
    protected static final String FIELD_REPARAR = "reparar";
    protected static final String FIELD_DESCRIPCION = "descripcion";
    protected static final String FIELD_PRECIO = "precio";
    protected static final String FIELD_NOMBRE = "nombre";
    protected static final String FIELD_APELLIDOS = "apellidos";
    protected static final String FIELD_DIRECCION = "direccion";
    protected static final String FIELD_LOCALIDAD = "localidad";
    protected static final String FIELD_CODPOSTAL = "codPostal";
    protected static final String FIELD_PROVINCIA = "provincia";
    protected static final String FIELD_PAIS = "pais";
    protected static final String FIELD_EMAIL = "email";
    protected static final String FIELD_MOVIL = "movil";
    protected static final String FIELD_FILES = "files";
    protected static final String FIELD_PASS = "pass";
    protected static final String FIELD_LANG = "lang";

    protected static final String FIELD_EMAIL_LOGIN = "emailLogin";
    protected static final String FIELD_PASS_LOGIN = "passLogin";
    protected static final String FIELD_TIPO_ALTA = "tipoAlta";

    protected static final String FIELD_PRIVACIDAD = "privacidad";
    protected static final String FIELD_TERMINOS = "terminos";

    @Autowired
    MessageSource messageSource;

    @Autowired
    TiposVela tiposVela;

    @Override
    public boolean supports(final Class<?> clazz) {
        return VenderVelaUI.class.equals(clazz);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {

        final VenderVelaUI f = (VenderVelaUI) obj;

        if (!f.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_REGISTER_USER)
                && !f.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_EDITAR_DATOS)) {
            validaDatosAnuncio(obj, errors, f);
            validaImagenes(obj, errors, f);
        }

        if (f.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_ALTA)) {
            if (Validadores.estaVacia(f.getPrivacidad()) || !f.getPrivacidad().equals(DefaultGlobalNames.SI)) {
                errors.rejectValue(FIELD_PRIVACIDAD, DefaultGlobalNames.FORM_VALIDATION_PRIVACIDAD_FIELD);
            }

            if (Validadores.estaVacia(f.getTerminos()) || !f.getTerminos().equals(DefaultGlobalNames.SI)) {
                errors.rejectValue(FIELD_TERMINOS, DefaultGlobalNames.FORM_VALIDATION_TERMINOS_FIELD);
            }
        }

        if (f.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_ALTA)
                || f.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_REGISTER_USER)) {

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_NOMBRE, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_EMAIL, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

            if (!Validadores.estaVacia(f.getEmail()) && !Validadores.esCorreoElectronico(f.getEmail())) {
                errors.rejectValue(VenderVelaValidator.FIELD_EMAIL, DefaultGlobalNames.FORM_VALIDATION_MAIL_FIELD);
            }

            if (!Validadores.estaVacia(f.getMovil()) && !Validadores.esTelefono(f.getMovil())) {
                errors.rejectValue(VenderVelaValidator.FIELD_MOVIL, DefaultGlobalNames.FORM_VALIDATION_TELEPHONE_FIELD);
            }

            if (f.getPais().equals(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA)) {
                errors.rejectValue(VenderVelaValidator.FIELD_PAIS, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
            }

            if (!Validadores.estaVacia(f.getCodPostal()) && !Validadores.esCodigoPostal(f.getCodPostal())) {
                errors.rejectValue(VenderVelaValidator.FIELD_CODPOSTAL, DefaultGlobalNames.FORM_VALIDATION_CODIGOPOSTAL_FIELD);
            }
        } else if (f.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_LOGIN)) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_EMAIL_LOGIN, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_PASS_LOGIN, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
            if (!Validadores.estaVacia(f.getEmailLogin()) && !Validadores.esCorreoElectronico(f.getEmailLogin())) {
                errors.rejectValue(VenderVelaValidator.FIELD_EMAIL_LOGIN, DefaultGlobalNames.FORM_VALIDATION_MAIL_FIELD);
            }
        } else if (f.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_EDITAR_DATOS)) {
            validaDatosAnuncio(obj, errors, f);
        } else if (f.getTipoAlta().equals(SpinnakerSimpleGlobals.VENDER_TIPO_EDITAR_FOTOS)) {
            validaImagenes(obj, errors, f);
        }
    }

    private void validaDatosAnuncio(final Object obj, final Errors errors, final VenderVelaUI f) {
        // NO ES REGISTRO DE USUARIO => VELA NUEVA

        if (f.getTipoVela().equals(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA)) {
            errors.rejectValue(VenderVelaValidator.FIELD_TIPOVELA, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_TITULOANUNCIO, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_TIPOBARCO, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_TIPOVELA, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);

        TipoVelaPOJO tv = null;
        if (FormUtils.isNatural(f.getTipoVela())) {
            tv = this.tiposVela.getPojo(LocaleContextHolder.getLocale().getLanguage(), Integer.parseInt(f.getTipoVela()));
        }

        if (tv != null) {

            if (!StringUtils.isEmpty(f.getGramaje()) && !FormUtils.isBigDecimal(f.getGramaje())) {
                errors.rejectValue(VenderVelaValidator.FIELD_GRAMAJE,
                        DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
            }

            if (tv.getGratil().equals("1")) {
                if (!StringUtils.isEmpty(f.getGratil()) && !FormUtils.isBigDecimal(f.getGratil())) {
                    errors.rejectValue(VenderVelaValidator.FIELD_GRATIL,
                            DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
                }
            }

            if (tv.getBaluma().equals("1")) {
                if (!StringUtils.isEmpty(f.getBaluma()) && !FormUtils.isBigDecimal(f.getBaluma())) {
                    errors.rejectValue(VenderVelaValidator.FIELD_BALUMA,
                            DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
                }
            }

            if (tv.getPujamen().equals("1")) {
                if (!StringUtils.isEmpty(f.getPujamen()) && !FormUtils.isBigDecimal(f.getPujamen())) {
                    errors.rejectValue(VenderVelaValidator.FIELD_PUJAMEN,
                            DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
                }
            }

            if (tv.getCaidaProa().equals("1")) {
                if (!StringUtils.isEmpty(f.getCaidaProa()) && !FormUtils.isBigDecimal(f.getCaidaProa())) {
                    errors.rejectValue(VenderVelaValidator.FIELD_CAIDA_PROA,
                            DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
                }
            }

            if (tv.getCaidaPopa().equals("1")) {
                if (!StringUtils.isEmpty(f.getCaidaPopa()) && !FormUtils.isBigDecimal(f.getCaidaPopa())) {
                    errors.rejectValue(VenderVelaValidator.FIELD_CAIDA_POPA,
                            DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
                }
            }

            if (tv.getSuperficie().equals("1")) {
                if (!StringUtils.isEmpty(f.getSuperficie()) && !FormUtils.isBigDecimal(f.getSuperficie())) {
                    errors.rejectValue(VenderVelaValidator.FIELD_SUPERFICIE,
                            DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
                }
            }

            if (tv.getTipoCometa().equals("1")) {
                if (f.getTipoCometa() != null && f.getTipoCometa().equals(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA)) {
                    errors.rejectValue(VenderVelaValidator.FIELD_TIPO_COMETA, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
                }
            }

        } else {
            if (!StringUtils.isEmpty(f.getGratil()) && !FormUtils.isBigDecimal(f.getGratil())) {
                errors.rejectValue(VenderVelaValidator.FIELD_GRATIL,
                        DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
            }

            if (!StringUtils.isEmpty(f.getBaluma()) && !FormUtils.isBigDecimal(f.getBaluma())) {
                errors.rejectValue(VenderVelaValidator.FIELD_BALUMA,
                        DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
            }

            if (!StringUtils.isEmpty(f.getPujamen()) && !FormUtils.isBigDecimal(f.getPujamen())) {
                errors.rejectValue(VenderVelaValidator.FIELD_PUJAMEN,
                        DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
            }

            if (!StringUtils.isEmpty(f.getCaidaProa()) && !FormUtils.isBigDecimal(f.getCaidaProa())) {
                errors.rejectValue(VenderVelaValidator.FIELD_CAIDA_PROA,
                        DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
            }

            if (!StringUtils.isEmpty(f.getCaidaPopa()) && !FormUtils.isBigDecimal(f.getCaidaPopa())) {
                errors.rejectValue(VenderVelaValidator.FIELD_CAIDA_POPA,
                        DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
            }

            if (!StringUtils.isEmpty(f.getSuperficie()) && !FormUtils.isBigDecimal(f.getSuperficie())) {
                errors.rejectValue(VenderVelaValidator.FIELD_SUPERFICIE,
                        DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
            }

            if (f.getTipoCometa().equals(DefaultGlobalNames.NADA_SELECCIONADO_EN_LISTA)) {
                errors.rejectValue(VenderVelaValidator.FIELD_TIPO_COMETA, DefaultGlobalNames.FORM_VALIDATION_MADATORY_FIELD);
            }
        }

        if (!StringUtils.isEmpty(f.getPrecio()) && !FormUtils.isBigDecimal(f.getPrecio())) {
            errors.rejectValue(VenderVelaValidator.FIELD_PRECIO,
                    DefaultGlobalNames.FORM_VALIDATION_NUMERIC_BIGDECIMAL_FORMAT_FIELD);
        }
    }

    private void validaImagenes(final Object obj, final Errors errors, final VenderVelaUI f) {

        // Validación de las imágenes.
        final List<String> ficherosIncorrectos = new ArrayList<>();
        for (final MultipartFile file : f.getFiles()) {
            if (!file.isEmpty() && !PNG_MIME_TYPE.equalsIgnoreCase(file.getContentType())
                    && !JPEG_MIME_TYPE.equalsIgnoreCase(file.getContentType())
                    && !GIF_MIME_TYPE.equalsIgnoreCase(file.getContentType())) {
                ficherosIncorrectos.add(file.getOriginalFilename());
            }
        }
        if (!ficherosIncorrectos.isEmpty()) {
            if (ficherosIncorrectos.size() == 1) {
                errors.rejectValue(VenderVelaValidator.FIELD_FILES, DefaultGlobalNames.FORM_VALIDATION_IMAGEN_VALIDA_UNO,
                        new Object[] { ficherosIncorrectos.get(0) }, null);
            } else {
                errors.rejectValue(VenderVelaValidator.FIELD_FILES, DefaultGlobalNames.FORM_VALIDATION_IMAGEN_VALIDA_VARIOS,
                        new Object[] { Cadenas.replaceLast(ficherosIncorrectos.toString().replace("[", "").replace("]", ""), ",",
                                " " + this.messageSource.getMessage("global.conjuncion", null, LocaleContextHolder.getLocale())
                                        + " ") },
                        null);
            }
        }
    }
}
