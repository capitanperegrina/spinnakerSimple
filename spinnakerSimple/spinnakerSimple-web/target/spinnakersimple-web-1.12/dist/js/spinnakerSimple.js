//  $(function () {         
//      var timer;
//      $(document).ajaxStart( function () {
//          timer && clearTimeout(timer);
//          timer = setTimeout(function () {
//              $('#_cargandoDatos').show();
//          }, 500);
//      }).ajaxStop( function () {
//          clearTimeout(timer);
//          $('#_cargandoDatos').hide();       
//      });
//  });

    function creaVentanaModal(titulo, contenido, ms) {
        $('#tituloVentanaModal').html(titulo);
        $('#contenidoVentanaModal').html(contenido);
        $('#ventanaModal').modal('show');
        if ( ms > 0 ) {
            setTimeout( function() {
                $('#ventanaModal').modal('hide')
            }, ms);
        }
    }
        
    function cargaImagenVela( idVela ) {
        $.ajax({
            type: "GET",
            url: 'tipoVelaInfoAjax.action?idTipoVela=' + idVela,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function(json) {
                $('#imagenVelaImg').attr( 'src', json.img );
                $('#imagenVelaImg').attr( 'alt', json.nom );                
            },
            error: function (xhr, desc, err) {
                // procesaError( xhr.status );
            }
        });
    }

    function ampliafoto( idFoto ) {
        if ($('#fotoPrincipal').length > 0) {
            $('#fotoPrincipal').attr('src',$('#miniaturaFoto'+idFoto).attr('src'));
        }
        return false;
    }
    
    function generaFormQueryString() {
        var s = '&s=' +
                $('#filtroMisAnunciosForm_idTipoVela').val() + ';' + 
                $('#filtroMisAnunciosForm_estado_0').is(':checked') + ';' + 
                $('#filtroMisAnunciosForm_estado_1').is(':checked') + ';' + 
                $('#filtroMisAnunciosForm_estado_2').is(':checked');
        return s;
    }
    
    function openModalTipoBarco() {
        $('#ventanaModalSelectorTipoBarco').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
    }
    
    function openModalSelectorClaseVelaLigera() {
        $('#ventanaModalSelectorClaseVelaLigera').modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
    }

    function buscaTiposDeVela(prefijo, tipoBarco, postFunction) {
        if (tipoBarco) {
            $.getJSON( "tiposDeVela.action?tb=" + tipoBarco, function( res ) {
                $(prefijo + 'tipoVela').find('option').remove().end();
                for (var key in res) {
                    if (res.hasOwnProperty(key)) {
                        $(prefijo + 'tipoVela').append('<option value="' + key + '">' + res[key] + '</option>');
                    }
                }
                var valor = $('#_valorTemporal').val();
                if (valor!="") {
                    var existe = 0 != $(prefijo + 'tipoVela option[value='+valor+']').length;
                    if (existe) {
                        $(prefijo + 'tipoVela').val(valor);
                    }
                }
                $(prefijo + 'tipoVela').change();
                if ( postFunction ) {
                    postFunction();
                }
            });
        }       
    }

    function configuraTodosBotonesActivosPorTiposClase(msgNoHay) {
        $('.btn-sel-clase-vela-ligera').each( function() {
            $(this).removeClass("disabled");
            $(this).prop('title','');
            $(this).prop("disabled", false);
        });
    }
    
    function configuraTodosBotonesActivosPorTiposBarco() {
        $('.btn-sel-tipo-barco').each( function() {     
            $(this).removeClass("disabled");
            $(this).prop('title','');
            $(this).prop("disabled", false);
        });
    }
    
    function configuraBotonesActivosPorTiposClase(prefijo, mensajeNoDisponible) {
        $.getJSON( "buscaBotonesActivosPorTiposClase.action", function( res ) {
            for (var elemento in res) {
                $('.btn-sel-tipo-barco').each( function() {
                    if ( $(this).attr('code') == res[elemento].val1 ) {
                        $(this).removeClass("disabled");
                        $(this).prop("disabled", false);
                    }
                });
            }
        });
    }

    function configuraBotonesActivosPorTiposBarco(prefijo) {
        $.getJSON( "buscaBotonesActivosPorTiposBarco.action", function( res ) {
            for (var elemento in res) {
                var selector = '#sel' + res[elemento].val1.charAt(0).toUpperCase() + res[elemento].val1.slice(1); 
                 $(selector).removeClass("disabled");
                 $(selector).prop('title','');
                 $(selector).prop("disabled", false);
            }           
        });
    }

    function desactivar(divId) {
        $('#'+divId).hide();
        $('#'+divId).attr('disabled','disabled').val('');
    }

    function activar(divId) {
        $('#'+divId).removeAttr('disabled');
        $('#'+divId).show();
    }
    
    function configuraFormularioPorTipoVelaPantallaPrincipal() {
        var idTipoVela = $('#comprarVelaForm_tipoVela').find('option:selected').val();
        switch(idTipoVela) {
          case "1":
            activar('div-gratil');
            activar('div-baluma');
            activar('div-pujamen');           
            desactivar('div-caidaProa');
            desactivar('div-caidaPopa');
            desactivar('div-tipoCometa');
            desactivar('div-superficie');
            break;
          case "2":
            activar('div-gratil');
            activar('div-baluma');
            activar('div-pujamen');           
            desactivar('div-caidaProa');
            desactivar('div-caidaPopa');
            desactivar('div-tipoCometa');
            desactivar('div-superficie');
            break;
          case "3":
            activar('div-gratil');
            activar('div-baluma');
            activar('div-pujamen');           
            desactivar('div-caidaProa');
            desactivar('div-caidaPopa');
            desactivar('div-tipoCometa');
            desactivar('div-superficie');
            break;
          case "4":
            activar('div-gratil');
            desactivar('div-baluma');
            activar('div-pujamen');           
            desactivar('div-caidaProa');
            desactivar('div-caidaPopa');
            desactivar('div-tipoCometa');
            desactivar('div-superficie');
            break;
          case "5":
            activar('div-gratil');
            activar('div-baluma');
            activar('div-pujamen');           
            activar('div-caidaProa');
            activar('div-caidaPopa');
            desactivar('div-tipoCometa');
            desactivar('div-superficie');
            break;
          case "6":
            desactivar('div-gratil');
            desactivar('div-baluma');
            desactivar('div-pujamen');            
            desactivar('div-caidaProa');
            desactivar('div-caidaPopa');
            activar('div-tipoCometa');
            activar('div-superficie');
            break;
          default:
            desactivar('div-gratil');
            desactivar('div-baluma');
            desactivar('div-pujamen');            
            desactivar('div-caidaProa');
            desactivar('div-caidaPopa');
            desactivar('div-tipoCometa');
            desactivar('div-superficie');
        }       
    }   

    function configuraFormularioPorTipoVela(prefijo, idTipoVela) {
        var tipoClase = $(prefijo + "tipoClase").val().toUpperCase();
        var tipoBarco = $(prefijo + "tipoBarco").val().toUpperCase();
        $.getJSON( "buscaConfiguracionTipoVela.action?tv=" + idTipoVela, function( res ) {
            if ( tipoClase == 'L' && tipoBarco != '99' ) {
                $('#medidasVelas').hide();              
                $(prefijo + 'gratil').attr('disabled','disabled').val('');
                $(prefijo + 'baluma').attr('disabled','disabled').val('');
                $(prefijo + 'caidaProa').attr('disabled','disabled').val('');
                $(prefijo + 'pujamen').attr('disabled','disabled').val('');
                $(prefijo + 'caidaPopa').attr('disabled','disabled').val('');
                $(prefijo + 'superficie').attr('disabled','disabled').val('');
                $(prefijo + 'tipoCometa').attr('disabled','disabled').val('');              
            } else {
                $('#medidasVelas').show();
                if ( res.gratil == 0 ) {
                    $('#div-gratil').hide();                
                    $(prefijo + 'gratil').attr('disabled','disabled');
                    $(prefijo + 'gratil').val('');
                } else {
                    $(prefijo + 'gratil').removeAttr('disabled');
                    $('#div-gratil').show();
                }
                
                if ( res.baluma == 0 ) {
                    $('#div-baluma').hide();
                    $(prefijo + 'baluma').attr('disabled','disabled');
                    $(prefijo + 'baluma').val('');
                } else {
                    $(prefijo + 'baluma').removeAttr('disabled');
                    $('#div-baluma').show();
                }
                
                if ( res.pujamen == 0 ) {
                    $('#div-pujamen').hide();
                    $(prefijo + 'pujamen').attr('disabled','disabled');
                    $(prefijo + 'pujamen').val('');
                } else {
                    $(prefijo + 'pujamen').removeAttr('disabled');
                    $('#div-pujamen').show();
                }
                
                if ( res.caidaProa == 0 ) {
                    $('#div-caidaProa').hide();
                    $(prefijo + 'caidaProa').attr('disabled','disabled');
                    $(prefijo + 'caidaProa').val('');
                } else {
                    $(prefijo + 'caidaProa').removeAttr('disabled');
                    $('#div-caidaProa').show();
                }
                
                if ( res.caidaPopa == 0 ) {
                    $('#div-caidaPopa').hide();
                    $(prefijo + 'caidaPopa').attr('disabled','disabled');
                    $(prefijo + 'caidaPopa').val('');
                } else {
                    $(prefijo + 'caidaPopa').removeAttr('disabled');
                    $('#div-caidaPopa').show();
                }

                if ( res.superficie == 0 ) {
                    $('#div-superficie').hide();
                    $(prefijo + 'superficie').attr('disabled','disabled');
                    $(prefijo + 'superficie').val('');
                } else {
                    $(prefijo + 'superficie').removeAttr('disabled');
                    $('#div-superficie').show();
                }

                if ( res.tipoCometa == 0 ) {
                    $('#div-tipoCometa').hide();
                    $(prefijo + 'tipoCometa').attr('disabled','disabled');
                    $(prefijo + 'tipoCometa').val('');
                } else {
                    $(prefijo + 'tipoCometa').removeAttr('disabled');
                    $('#div-tipoCometa').show();
                }               
            }
        });
        cargaImagenVela( idTipoVela );
    }
    
    
    function bugfixBootstrap377() {
        
        $('#ventanaModalSelectorTipoBarco').on('hidden.bs.modal', function (e) {
              $('body').css("padding-right","");
        });
        
        $('#ventanaModalSelectorClaseVelaLigera').on('hidden.bs.modal', function (e) {
              $('body').css("padding-right","");
        });     
    }
