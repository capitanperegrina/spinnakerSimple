<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
	<spring:message code="global.max" var="maxPlaceholder"/>
	<spring:message code="global.min" var="minPlaceholder"/>
	<spring:message code="global.tituloAnuncio" var="tituloAnuncioPlaceholder"/>
	<spring:message code="global.metros" var="metrosPlaceholder"/>
	<spring:message code="unidades.densidad.gramosPorMetroCuadrado" var="gramajePlaceholder"/>
	<spring:message code="global.precio" var="precioPlaceholder"/>
	<spring:message code="global.nombre" var="nombrePlaceholder"/>
	<spring:message code="global.apellidos" var="apellidosPlaceholder"/>
	<spring:message code="global.direccion" var="direccion1Placeholder"/>
	<spring:message code="global.localidad" var="direccion2Placeholder"/>
	<spring:message code="global.provincia" var="provinciaPlaceholder"/>
	<spring:message code="global.email" var="emailPlaceholder"/>

	<jsp:include page="modalSelectorBarco.jsp" />

    <div class="container-fluid">
    
    	<div class="col-md-12 col-sm-12 text-center fondogris">
    		<h2><spring:message code="vela.fichaVenta"/></h2>
    	</div>

		<form:form action="venderVela.action" method="post" enctype="multipart/form-data" modelAttribute="venderVelaForm" id="venderVelaForm">
    		<form:hidden path="tipoAlta"  id="venderVelaForm_tipoAlta"/>
			<form:hidden path="tipoClase" id="venderVelaForm_tipoClase"/>
			<form:hidden path="tipoBarco" id="venderVelaForm_tipoBarco"/>

			<div class="col-md-12 col-sm-12 fondogris">

				<div class="col-md-5 col-sm-5">
					<div class="col-md-12 col-sm-12 separa">
						<span id="imagenvela">
							<img id="imagenVelaImg" src="imagenes/velas/tipovela/tipovela.png" class="img-responsive anchototal"/>
						</span>
					</div>
				</div>

				<div class="col-md-7 col-sm-7">

					<div class="separa">
						<label for="nombreClaseBarco"><spring:message code="tipoBarco.seleccione.tipoBarco.tipo"/></label> <form:errors path="tipoBarco" cssClass="errorFormulario" /> <form:errors path="tipoClase" cssClass="errorFormulario" />
					</div>
					<div>
						<input id="nombreClaseBarco" name="nombreClaseBarco" type="text" class="form-control ancho66" disabled="disabled" style="float: left;"/> <a href="#" id="cambiarTipoBarco" style="margin-top: 3px; margin-left: 10px; float: left;"><spring:message code="global.cambiar"/></a>
					</div>
					<div class="clearfix"></div>

					<div class="separa">
						<label for="venderVelaForm_tipoVela"><spring:message code="vela.tipo"/></label> <form:errors path="tipoVela" cssClass="errorFormulario" />
					</div>
					<div>
						<form:select path="tipoVela" class="form-control" id="venderVelaForm_tipoVela">
						</form:select>
					</div>
					<div class="clearfix"></div>


					<div class="separa">
						<label for="venderVelaForm_tituloAnuncio" class="izda"><spring:message code="global.titulo"/> * </label>&nbsp;<form:errors path="tituloAnuncio" cssClass="errorFormulario" />
      				</div>
					<div>
						<form:input type="text" path="tituloAnuncio" class="form-control dcha" id="venderVelaForm_titulo" placeholder="${tituloAnuncioPlaceholder}"/>
      				</div>
     				
					<div>
						<label class="fondoblanco"><spring:message code="vela.vender.ayuda.1"/></label>  
					</div>
      				<div class="clearfix"></div>
 				
 				
 					
					<div class="separa">
						<label for="venderVelaForm_descripcion"><spring:message code="global.descripcion"/></label> <form:errors path="descripcion" cssClass="errorFormulario" />
      				</div>
      				<div>
						<form:textarea path="descripcion" class="form-control" rows="5" id="venderVelaForm_descripcion"/>
      				</div>
 					<div class="clearfix"></div>

					<div class="separa">
						<label for="venderVelaForm_precio"><spring:message code="global.precio"/> (<span id="divisa_importe"><c:out value="${literalDivisa}" escapeXml="false"/></span>):</label> <form:errors path="precio" cssClass="errorFormulario" />
					</div>
      				<div>
						<form:input type="text" path="precio" class="form-control" style="width: 200px !important" id="venderVelaForm_precio" placeholder="${precioPlaceholder}"/>
<c:if test="${logged != null}">
						<form:input type="hidden" path="divisa" id="venderVelaForm_divisa"/>
</c:if>
					</div>
					<div id="infoTiposCambio"> ** - <spring:message code="global.divisa.tiposCambio.aviso"/></div>
					<div class="clearfix"></div>
					
                    <div class="separa">
                        <label for="venderVelaForm_paisVela" class="izda"><spring:message code="global.ubicacion"/> * </label>&nbsp;<form:errors path="paisVela" cssClass="errorFormulario" />
                    </div>
                    <div>
                        <form:select path="paisVela" class="form-control" id="venderVelaForm_paisVela">
                            <form:option value="-1"><spring:message code="global.seleccionePais"/></form:option>
                            <form:options items="${paises}" itemValue="codigo" itemLabel="nombrePais"/>
                        </form:select>                    
                    </div>					

					<div id="medidasVelas">

						<div class="separa">
							<label><h3><spring:message code="global.medidas"/></h3></label>
						</div>
						<div class="clearfix"></div>
	
						<div><spring:message code="vela.vender.ayuda.2"/></div>
	
						<div id="div-gratil">
							<div class="separa">
								<label for="venderVelaForm_gratil"><spring:message code="vela.gratil"/>:</label> <form:errors path="gratil" cssClass="errorFormulario" />
							</div>
							<div>
								<form:input type="text" path="gratil" class="form-control" style="width: 200px !important" id="venderVelaForm_gratil" placeholder="${metrosPlaceholder}"/>
							</div>
							<div class="clearfix"></div>
						</div>
	
						<div id="div-baluma">
							<div class="separa">
								<label for="venderVelaForm_baluma"><spring:message code="vela.baluma"/>:</label> <form:errors path="baluma" cssClass="errorFormulario" />
							</div>
							<div>
								<form:input type="text" path="baluma" class="form-control" style="width: 200px !important" id="venderVelaForm_baluma" placeholder="${metrosPlaceholder}"/>
							</div>
							<div class="clearfix"></div>
						</div>
	
						<div id="div-pujamen">						
							<div class="separa">
								<label for="venderVelaForm_pujamen"><spring:message code="vela.pujamen"/>:</label> <form:errors path="pujamen" cssClass="errorFormulario" />
							</div>  
							<div>
								<form:input type="text" path="pujamen" class=" form-control" style="width: 200px !important" id="venderVelaForm_pujamen" placeholder="${metrosPlaceholder}"/>
							</div>
							<div class="clearfix"></div>
						</div>
	
						<div id="div-caidaProa">						
							<div class="separa">
								<label for="venderVelaForm_caidaProa"><spring:message code="vela.caidaProa"/>:</label> <form:errors path="caidaProa" cssClass="errorFormulario" />
							</div>  
							<div>
								<form:input type="text" path="caidaProa" class=" form-control" style="width: 200px !important" id="venderVelaForm_caidaProa" placeholder="${metrosPlaceholder}"/>
							</div>
							<div class="clearfix"></div>
						</div>
	
						<div id="div-caidaPopa">						
							<div class="separa">
								<label for="venderVelaForm_caidaPopa"><spring:message code="vela.caidaPopa"/>:</label> <form:errors path="caidaPopa" cssClass="errorFormulario" />
							</div>  
							<div>
								<form:input type="text" path="caidaPopa" class=" form-control" style="width: 200px !important" id="venderVelaForm_caidaPopa" placeholder="${metrosPlaceholder}"/>
							</div>
							<div class="clearfix"></div>
						</div>
	
						<div id="div-superficie">						
							<div class="separa">
								<label for="venderVelaForm_superficie"><spring:message code="vela.superficie"/>:</label> <form:errors path="superficie" cssClass="errorFormulario" />
							</div>  
							<div>
								<form:input type="text" path="superficie" class=" form-control" style="width: 200px !important" id="venderVelaForm_superficie" placeholder="${metrosPlaceholder}"/>
							</div>
							<div class="clearfix"></div>
						</div>
	
						<div id="div-tipoCometa">						
							<div class="separa">
								<label for="venderVelaForm_tipoCometa"><spring:message code="vela.tipoCometa"/>:</label> <form:errors path="tipoCometa" cssClass="errorFormulario" />
							</div>  
							<div>
								<form:select path="tipoCometa" class="form-control" id="venderVelaForm_tipoCometa" style="width: 200px !important">
									<form:option value="-1"><spring:message code="global.elija"/></form:option>
									<form:option value="c"><spring:message code="vela.tipoCometa.c"/></form:option>
									<form:option value="b"><spring:message code="vela.tipoCometa.b"/></form:option>
									<form:option value="h"><spring:message code="vela.tipoCometa.h"/></form:option>
									<form:option value="f"><spring:message code="vela.tipoCometa.f"/></form:option>
								</form:select>
							</div>
							<div class="clearfix"></div>
						</div>

	                    <div id="div-gramaje">
	                        <div class="separa">
	                            <label for="editaVelaForm_gramaje"><spring:message code="vela.gramaje"/> (<spring:message code="unidades.densidad.gramosPorMetroCuadrado"/>):</label> <form:errors path="gramaje" cssClass="errorFormulario" />
	                        </div>
	                        <div>
	                            <form:input type="text" path="gramaje" class="form-control" style="width: 100px !important" id="editaVelaForm_gramaje" placeholder="${gramajePlaceholder}"/> 
	                        </div>
	                        <div class="clearfix"></div>
	                    </div>
						
					</div>						

					<div class="separa">
						<h3><label><spring:message code="global.fotos"/></label></h3>
					</div>
					<div class="clearfix"></div>
    
					<div class="separa">
						<label for="venderVelaForm_files"><spring:message code="global.fotos"/>:</label> <form:errors path="files" cssClass="errorFormulario" />
						<form:input path="files" type="file" class="ancho100" id="venderVelaForm_files"  multiple="multiple"/>
					</div>
					<div class="clearfix"></div>

<c:if test="${logged == null}">					
					<div class="separa">
						<h3><spring:message code="vela.vender.datosVendedor"/></h3>
					</div>

					<div class="tabUsuario">
					  <button id="btnAltaDiv" class="tabUsuarioLinks" onclick="openTabUsuario('btnAltaDiv', 'altaDiv','A'); return false;"><spring:message code="global.usuario.noTengo"/></button>
					  <button id="btnLoginDiv" class="tabUsuarioLinks" onclick="openTabUsuario('btnLoginDiv', 'loginDiv','L'); return false;"><spring:message code="global.usuario.siTengo"/></button>
					</div>
					
					<!-- Tab content -->
					<div id="altaDiv" class="tabUsuarioContent">

						<div class="separa">
							<label for="venderVelaForm_nombre"><spring:message code="global.nombre"/></label> * <form:errors path="nombre" cssClass="errorFormulario" />
	      				</div>
						<div>
							<form:input type="text" path="nombre" class="form-control ancho100" id="venderVelaForm_nombre" placeholder="${nombrePlaceholder}"/>
	      				</div>
	 					<div class="clearfix"></div>
	 					
	 					
						<div class="separa">
							<label for="venderVelaForm_apellidos"><spring:message code="global.apellidos"/></label> <form:errors path="apellidos" cssClass="errorFormulario" />
	      				</div>
						<div>
							<form:input type="text" path="apellidos" class="form-control ancho100" id="venderVelaForm_apellidos" placeholder="${apellidosPlaceholder}"/>
	      				</div>
	 					<div class="clearfix"></div>
	 					
	 					
						<div class="separa">
	      					<label for="venderVelaForm_email"><spring:message code="global.email"/></label> * <form:errors path="email" cssClass="errorFormulario" />
	    				</div>
						<div>
							<form:input type="email" path="email" class="form-control ancho100" id="venderVelaForm_email" placeholder="${emailPlaceholder}"/>
	    				</div>
	 					<div class="clearfix"></div>
	    				
						<div class="separa">
	      					<label for="venderVelaForm_movil"><spring:message code="global.movil"/></label> <form:errors path="movil" cssClass="errorFormulario" />
	    				</div>
						<div>
	      					<form:input type="tel" path="movil" class="form-control" style="width: 200px !important" id="venderVelaForm_movil" placeholder="+00999999999"/>
	    				</div>
	    				<div class="clearfix"></div>
	    				
						<div class="separa">
							<label for="venderVelaForm_direccion1"><spring:message code="global.direccion"/></label> <form:errors path="direccion1" cssClass="errorFormulario" />
	      				</div>
						<div>
							<form:input type="text" path="direccion1" class="form-control ancho100" id="venderVelaForm_direccion1" placeholder="${direccion1Placeholder}"/>
	      				</div>
	    				<div class="clearfix"></div>
	    				
	 					
						<div class="separa">
							<label for="venderVelaForm_direccion2"><spring:message code="global.localidad"/></label> <form:errors path="direccion2" cssClass="errorFormulario" />
	      				</div>
						<div>
							<form:input type="text" path="direccion2" class="form-control ancho100" id="venderVelaForm_direccion2" placeholder="${direccion2Placeholder}"/>
	      				</div>
	    				<div class="clearfix"></div>
	 					
	 					
						<div class="separa">
	      					<label for="venderVelaForm_codPostal"><spring:message code="global.codPostal"/></label> <form:errors path="codPostal" cssClass="errorFormulario" />
	    				</div>
						<div>
							<form:input type="text" path="codPostal" class="form-control" style="width: 150px !important" id="venderVelaForm_codPostal" placeholder="99999"/>
	    				</div>
	 					<div class="clearfix"></div>
	 					
	 					
						<div class="separa">
							<label for="venderVelaForm_provincia"><spring:message code="global.provincia"/></label> <form:errors path="provincia" cssClass="errorFormulario" />
	      				</div>
						<div>
							<form:input type="text" path="provincia" class="form-control" id="venderVelaForm_provincia" placeholder="${provinciaPlaceholder}"/>
	      				</div>
	 					<div class="clearfix"></div>
	 					
	 
						<div class="separa">
							<label for="venderVelaForm_pais"><spring:message code="global.pais"/></label> * <form:errors path="pais" cssClass="errorFormulario" />
						</div>
						<div>
							<form:select path="pais" class="form-control" id="venderVelaForm_pais">
								<form:option value="-1"><spring:message code="global.seleccionePais"/></form:option>
	      						<form:options items="${paises}" itemValue="codigo" itemLabel="nombrePais"/>
							</form:select>
						</div>
						<div class="clearfix"></div>
						
						<div class="separa">
							<label for="venderVelaForm_divisa"><spring:message code="global.divisaPreferida"/></label> <form:errors path="divisa" cssClass="errorFormulario" />
						</div>
						<div>
							<form:select path="divisa" class="form-control" id="venderVelaForm_divisa">
	      						<form:options items="${divisas}" itemValue="codigoDivisa" itemLabel="descripcionCompleta" htmlEscape="false"/>
							</form:select>
						</div>
						<div class="clearfix"></div>
						
						<div class="separa">
							<form:checkbox id="venderVelaForm_privacidad" path="privacidad" value="S" /> <label for="venderVelaForm_privacidad"><spring:message code="privacidad.acepto"/></label> * <form:errors path="privacidad" cssClass="errorFormulario" /><br/>
							<form:checkbox id="venderVelaForm_terminos" path="terminos" value="S" /> <label for="venderVelaForm_terminos"><spring:message code="terminos.acepto"/></label> * <form:errors path="terminos" cssClass="errorFormulario" />
						</div>
          			
          				<div class="separa">&nbsp;</div>
					
					</div>
					
					<div id="loginDiv" class="tabUsuarioContent">

						<div class="separa">
	      					<label for="venderVelaForm_emailLogin"><spring:message code="global.email"/></label> <form:errors path="emailLogin" cssClass="errorFormulario" />
	    				</div>
						<div>
							<form:input type="email" path="emailLogin" class="form-control ancho100" id="venderVelaForm_emailLogin" placeholder="${emailPlaceholder}"/>
	    				</div>
	 					<div class="clearfix"></div>

						<div class="separa">
	      					<label for="venderVelaForm_passLogin">Password</label> <form:errors path="passLogin" cssClass="errorFormulario" />
	    				</div>
						<div>
							<form:input type="password" path="passLogin" class="form-control ancho100" id="venderVelaForm_passLogin" placeholder="Password"/>
	    				</div>
	 					<div class="clearfix"></div>
          			
          				<div class="separa">&nbsp;</div>
					
					</div>

					<div class="separa">
						<form:errors path="captcha" cssClass="errorFormulario"  />
						<div class="g-recaptcha" data-sitekey="<c:out value="${reCaptchaSiteKey}"/>"></div>
      				</div>
    				<div class="clearfix"></div>
</c:if>
          			
				</div>
      			<div class="form-group col-md-12 col-sm-12" style="float:right;">
    				<button type="submit" class="btn btn-primary dcha"><spring:message code="global.enviar"/></button>
  				</div>
			</div>

		</form:form>

	</div> <!-- /container-fluid -->
    
<script type="text/javascript">
	$(document).ready(function(){
		
		cambiaDivisa( false );
		
		inicializaForm();
		
		$('#venderVelaForm_divisa').bind('change',function() {
			cambiaDivisa( true );
		});
		
    	$('#venderVelaForm_privacidad').bind('click', function() {
    		if ( $('#venderVelaForm_privacidad').prop('checked') ) {
    			$('#tituloVentanaModal').html("<spring:message code="global.mensaje"/>");
    			$('#contenidoVentanaModal').load('legalStuffAjax.action?f=V');
    			$('#ventanaModal').modal('show');
    		}
    	});		

		$('#venderVelaForm_tipoVela').change(function() {
			var idTipoVela = $(this).find('option:selected').val();
			configuraFormularioPorTipoVela("#venderVelaForm_", idTipoVela);
		});
		
		$('#selCrucero').click(function() {
			$('#venderVelaForm_tipoClase').val('C');
			$('#venderVelaForm_tipoBarco').val("1");
			$('#nombreClaseBarco').val("<spring:message code="tipoBarco.crucero"/>");
			buscaTiposDeVela("#venderVelaForm_", "1");
		});

		$('#serRC').click(function() {
			$('#venderVelaForm_tipoClase').val('R');
			$('#venderVelaForm_tipoBarco').val("2");
			$('#nombreClaseBarco').val("<spring:message code="tipoBarco.velaRc"/>");
			buscaTiposDeVela("#venderVelaForm_", "2");
		});

		$('#selWindsurf').click(function() {
			$('#venderVelaForm_tipoClase').val('W');
			$('#venderVelaForm_tipoBarco').val("3");
			$('#nombreClaseBarco').val("<spring:message code="tipoBarco.windsurf"/>");
			buscaTiposDeVela("#venderVelaForm_", "3");
		});

		$('#selKiteboarding').click(function() {
			$('#venderVelaForm_tipoClase').val('K');
			$('#venderVelaForm_tipoBarco').val("4");
			$('#nombreClaseBarco').val("<spring:message code="tipoBarco.kiteboarding"/>");
			buscaTiposDeVela("#venderVelaForm_", "4", function() {
				if ( !$("#venderVelaForm_tipoCometa").val() ) {
					$("#venderVelaForm_tipoCometa").val("-1");
				}
			});
		});
		
		$('#seltipoClase').click(function() {
			$('#venderVelaForm_tipoClase').val('l');
			openModalSelectorClaseVelaLigera();
			configuraTodosBotonesActivosPorTiposClase();
		});

		$(".btn-sel-clase-vela-ligera").each( function() {
			var boton = $(this);
			boton.unbind('click').bind('click', function() {
	        	$('#venderVelaForm_tipoBarco').val( boton.attr('idClase') );
				$('#nombreClaseBarco').val( boton.attr('titulo') );
				buscaTiposDeVela( "#venderVelaForm_", boton.attr('idClase') );
	        });
		});	

		$('#cambiarTipoBarco').click(function() {
        	$('#venderVelaForm_tipoBarco').val('');
			$('#nombreClaseBarco').val('');
			openModalTipoBarco();
			configuraTodosBotonesActivosPorTiposBarco();
		});
		
<c:if test="${venderVelaForm.tipoAlta eq 'A'}">
		openTabUsuario('btnAltaDiv', 'altaDiv','A');
</c:if>
<c:if test="${venderVelaForm.tipoAlta eq 'L'}">
		openTabUsuario('btnLoginDiv', 'loginDiv','L');
</c:if>
		if ($('.tabUsuario').length > 0) {
			return false;
		}		
	});
	
	function cambiaDivisa( avisa ) {
<c:if test="${logged != null}">
		if ( $('#venderVelaForm_divisa').val() != 'EUR' ) {
			$('#infoTiposCambio').show();
		} else {
			$('#infoTiposCambio').hide();
		}
</c:if>
<c:if test="${logged == null}">
		$('#divisa_importe').html( $('#venderVelaForm_divisa').find('option:selected').text() );
		if ( $('#venderVelaForm_divisa').find('option:selected').val() != 'EUR' ) {
			$('#infoTiposCambio').show();
		} else {
			$('#infoTiposCambio').hide();
		}
		if ( avisa ) {
			alert( "<spring:message code='global.cambioDivisa.aviso'/>" );
			$('#venderVelaForm_precio').focus();			
		}
</c:if>
	}

	function inicializaForm() {
		var postBuscaTipoVela = function() {
			var val = $('#venderVelaForm_tipoVela').find('option:selected').val();
			configuraFormularioPorTipoVela("#venderVelaForm_", val );
		};
		$('#tipoBarcoLeyenda').hide();
		if ( $('#venderVelaForm_tipoClase').val() == "C" ) {
			$('#nombreClaseBarco').val("<spring:message code="tipoBarco.crucero"/>");
			buscaTiposDeVela("#venderVelaForm_", "1", postBuscaTipoVela);
		} else if ( $('#venderVelaForm_tipoClase').val() == "R" ) {
			$('#nombreClaseBarco').val("<spring:message code="tipoBarco.velaRc"/>");
			buscaTiposDeVela("#venderVelaForm_", "2", postBuscaTipoVela);
		} else if ( $('#venderVelaForm_tipoClase').val() == "W" ) {
			$('#nombreClaseBarco').val("<spring:message code="tipoBarco.windsurf"/>");
			buscaTiposDeVela("#venderVelaForm_", "3", postBuscaTipoVela);
		} else if ( $('#venderVelaForm_tipoClase').val() == "K" ) {
			$("#_valorTemporal").val( $("#venderVelaForm_tipoCometa").val());
			$('#nombreClaseBarco').val("<spring:message code="tipoBarco.kiteboarding"/>");
			buscaTiposDeVela("#venderVelaForm_", "4", function() {
				$("#venderVelaForm_tipoCometa").val( $("#_valorTemporal").val());
				$("#_valorTemporal").val( "" );
				postBuscaTipoVela();
			});
		} else if ( $('#venderVelaForm_tipoClase').val().toUpperCase() == "L" ) {
			var tipoBarco = $("#venderVelaForm_tipoBarco").val();
			$('#nombreClaseBarco').val("${nombreTipoBarco}");	
			buscaTiposDeVela( "#venderVelaForm_", tipoBarco, postBuscaTipoVela);
		} else {
			openModalTipoBarco();
			configuraTodosBotonesActivosPorTiposBarco();
		}
	}

	function openTabUsuario(target, cityName, tipoAlta) {
	    // Declare all variables
	    var i, tabcontent, tablinks;
	
	    // Get all elements with class="tabcontent" and hide them
	    tabcontent = document.getElementsByClassName("tabUsuarioContent");
	    for (i = 0; i < tabcontent.length; i++) {
	        tabcontent[i].style.display = "none";
	    }
	
	    // Get all elements with class="tablinks" and remove the class "active"
	    tablinks = document.getElementsByClassName("tabUsuarioLinks");
	    for (i = 0; i < tablinks.length; i++) {
	        tablinks[i].className = tablinks[i].className.replace(" active", "");
	    }
	
	    // Show the current tab, and add an "active" class to the button that opened the tab
	    document.getElementById(cityName).style.display = "block";
	    document.getElementById(target).className += " active";
	    $('#venderVelaForm_tipoAlta').val(tipoAlta);
	}
</script>
    