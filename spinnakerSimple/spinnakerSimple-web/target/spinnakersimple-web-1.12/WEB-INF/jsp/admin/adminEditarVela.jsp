<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

    <spring:message code="unidades.densidad.gramosPorMetroCuadrado" var="gramajePlaceholder"/>
	<jsp:include page="../modalSelectorBarco.jsp" />

            <div class="row">
                <div class="col-lg-12">
					<div class="float-izda">
						<h1><spring:message code="global.editarAnuncio"/></h1>
					</div>                
					<div class="float-dcha">
						<h1>
		        			<button id="btnVolver" type="button" class="btn btn-primary">
								<spring:message code="global.volver"/>
							</button>
						</h1>						
					</div>
					<div class="clearfix"></div>
                    
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        
                        
                        <div class="panel-heading">
    						<label><spring:message code="global.propietario"/>:</label> <c:out value="${anuncio.usuario.nombrePropietario}"/>
<c:if test="${not empty anuncio.usuario.mail}">
 - <label>&#9993;</label> <a href="mailto:<c:out value="${anuncio.usuario.mail}"/>"><c:out value="${anuncio.usuario.mail}"/></a>
</c:if>
<c:if test="${not empty anuncio.usuario.movil}">
 - <label>&phone;</label> <c:out value="${anuncio.usuario.movil}"/>
</c:if>
						</div>
                        <!-- /.panel-heading -->
                        
                        
                        <div class="panel-body">

							<c:out value="${anuncio.anuncio.divEstado}" escapeXml="false"/>

							<div class="col-md-6 col-sm-6">
							
								<form:form action="miAnuncioModAjax.action" method="post" enctype="multipart/form-data" modelAttribute="editaVelaForm" id="editaVelaForm">
									<form:hidden path="idAnuncio" id="editaFotosVelaForm_idAnuncio"/>
						    		<form:hidden path="tipoAlta"  id="editaVelaForm_tipoAlta"/>
						    		<form:hidden path="caduca"    id="editaVelaForm_caduca"/>
									<form:hidden path="tipoClase" id="editaVelaForm_tipoClase"/>
									<form:hidden path="tipoBarco" id="editaVelaForm_tipoBarco"/>
						    		
									<div class="separa">
										<h3><label><spring:message code="global.datosAnuncio"/></label></h3>
									</div>
									<div class="clearfix"></div>
									
									<div class="separa">
										<label for="nombreClaseBarco"><spring:message code="tipoBarco.seleccione.tipoBarco.tipo"/></label> 
										<form:errors path="tipoBarco" cssClass="errorFormulario" /> <form:errors path="tipoClase" cssClass="errorFormulario" />
									</div>
									<div>
										<input value="${nombreClaseBarco}" id="nombreClaseBarco" name="nombreClaseBarco" type="text" class="form-control ancho66" disabled="disabled" style="float: left; width: 400px;"/> <a href="#" id="cambiarTipoBarco" style="margin-top: 3px; margin-left: 10px; float: left;"><spring:message code="global.cambiar"/></a>
									</div>
									<div class="clearfix"></div>
					
									<div class="separa">
										<label for="editaVelaForm_tipoVela"><spring:message code="vela.tipo"/></label> 
										<span id="errorFormulario_tipoVela" class="errorFormulario"></span>
									</div>
									<div>
										<form:select path="tipoVela" class="form-control" id="editaVelaForm_tipoVela">
											<form:options items="${tiposVela}"  />
										</form:select>
									</div>
									<div class="clearfix"></div>
									
									<div class="separa">
										<label for="editaVelaForm_tituloAnuncio" class="izda"><spring:message code="global.titulo"/> * </label> 
										<span id="errorFormulario_tituloAnuncio" class="errorFormulario"></span>
					     				</div>
									<div>
										<form:input type="text" path="tituloAnuncio" class="form-control dcha" id="editaVelaForm_titulo" placeholder="${tituloAnuncioPlaceholder}"/>
					     				</div>
					    				
									<div>
										<label class="fondoblanco"><spring:message code="vela.vender.ayuda.1"/></label>  
									</div>
					   				<div class="clearfix"></div>
										
									<div class="separa">
										<label for="editaVelaForm_descripcion"><spring:message code="global.descripcion"/></label> 
										<span id="errorFormulario_descripcion" class="errorFormulario"></span>
					     				</div>
					     				<div>
										<form:textarea path="descripcion" class="form-control" rows="5" id="editaVelaForm_descripcion"/>
					     				</div>
										<div class="clearfix"></div>
					
									<div class="separa">
										<label for="editaVelaForm_precio"><spring:message code="global.precio"/> (<span id="divisa_importe"><c:out value="${literalDivisa}" escapeXml="false"/></span>):</label> 
										<span id="errorFormulario_precio" class="errorFormulario"></span>
									</div>
					     				<div>
										<form:input type="text" path="precio" class="form-control" style="width: 200px !important" id="editaVelaForm_precio" placeholder="${precioPlaceholder}"/>
										<form:input type="hidden" path="divisa" id="editaVelaForm_divisa"/>
									</div>
									<div id="infoTiposCambio">* - <spring:message code="global.divisa.tiposCambio.aviso"/></div>
									<div class="clearfix"></div>
                
					                <div class="separa">
					                    <label for="venderVelaForm_paisVela" class="izda"><spring:message code="global.ubicacion"/> * </label>
					                    <span id="errorFormulario_paisVela" class="errorFormulario"></span>
					                </div>
					                <div>
					                    <form:select path="paisVela" class="form-control" id="venderVelaForm_paisVela">
					                        <form:option value="-1"><spring:message code="global.seleccionePais"/></form:option>
					                        <form:options items="${paises}" itemValue="codigo" itemLabel="nombrePais"/>
					                    </form:select>                    
					                </div>					
									
									<div id="medidasVelas">
									
										<div class="separa">
											<h3><label><spring:message code="global.medidas"/></label></h3>
										</div>
										<div class="clearfix"></div>
						
										<div><spring:message code="vela.vender.ayuda.2"/></div>
						
										<div id="div-gratil">
											<div class="separa">
												<label for="editaVelaForm_gratil"><spring:message code="vela.gratil"/>:</label> 
												<span id="errorFormulario_gratil" class="errorFormulario"></span>
											</div>
											<div>
												<form:input type="text" path="gratil" class="form-control" style="width: 200px !important" id="editaVelaForm_gratil" placeholder="${metrosPlaceholder}"/>
											</div>
											<div class="clearfix"></div>
										</div>
						
										<div id="div-baluma">
											<div class="separa">
												<label for="editaVelaForm_baluma"><spring:message code="vela.baluma"/>:</label> 
												<span id="errorFormulario_baluma" class="errorFormulario"></span>
											</div>
											<div>
												<form:input type="text" path="baluma" class="form-control" style="width: 200px !important" id="editaVelaForm_baluma" placeholder="${metrosPlaceholder}"/>
											</div>
											<div class="clearfix"></div>
										</div>
						
										<div id="div-pujamen">						
											<div class="separa">
												<label for="editaVelaForm_pujamen"><spring:message code="vela.pujamen"/>:</label> 
												<span id="errorFormulario_pujamen" class="errorFormulario"></span>
											</div>  
											<div>
												<form:input type="text" path="pujamen" class="form-control" style="width: 200px !important" id="editaVelaForm_pujamen" placeholder="${metrosPlaceholder}"/>
											</div>
											<div class="clearfix"></div>
										</div>
						
										<div id="div-caidaProa">						
											<div class="separa">
												<label for="editaVelaForm_caidaProa"><spring:message code="vela.caidaProa"/>:</label> 
												<span id="errorFormulario_caidaProa" class="errorFormulario"></span>
											</div>  
											<div>
												<form:input type="text" path="caidaProa" class=" form-control" style="width: 200px !important" id="editaVelaForm_caidaProa" placeholder="${metrosPlaceholder}"/>
											</div>
											<div class="clearfix"></div>
										</div>
						
										<div id="div-caidaPopa">						
											<div class="separa">
												<label for="editaVelaForm_caidaPopa"><spring:message code="vela.caidaPopa"/>:</label>
												<span id="errorFormulario_caidaPopa" class="errorFormulario"></span>
											</div>  
											<div>
												<form:input type="text" path="caidaPopa" class=" form-control" style="width: 200px !important" id="editaVelaForm_caidaPopa" placeholder="${metrosPlaceholder}"/>
											</div>
											<div class="clearfix"></div>
										</div>
						
										<div id="div-superficie">						
											<div class="separa">
												<label for="editaVelaForm_superficie"><spring:message code="vela.superficie"/>:</label>
												<span id="errorFormulario_superficie" class="errorFormulario"></span>
											</div>  
											<div>
												<form:input type="text" path="superficie" class=" form-control" style="width: 200px !important" id="editaVelaForm_superficie" placeholder="${metrosPlaceholder}"/>
											</div>
											<div class="clearfix"></div>
										</div>
						
										<div id="div-tipoCometa">						
											<div class="separa">
												<label for="editaVelaForm_tipoCometa"><spring:message code="vela.tipoCometa"/>:</label>
												<span id="errorFormulario_tipoCometa" class="errorFormulario"></span>
											</div>  
											<div>
												<form:select path="tipoCometa" class="form-control" id="editaVelaForm_tipoCometa" style="width: 200px !important">
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
					                            <label for="editaVelaForm_gramaje"><spring:message code="vela.gramaje"/> (<spring:message code="unidades.densidad.gramosPorMetroCuadrado"/>):</label> 
					                            <span id="errorFormulario_gramaje" class="errorFormulario"></span>
					                        </div>
					                        <div>
					                            <form:input type="text" path="gramaje" class="form-control" style="width: 200px !important" id="editaVelaForm_gramaje" placeholder="${gramajePlaceholder}"/>
					                        </div>
					                        <div class="clearfix"></div>
					                    </div>  
									</div>	
					
					     			<div class="clearfix"></div>
					
						   			<div class="separa form-group col-md-12 col-sm-12">
						 				<button id="btnSubmitDatosVela" type="submit" class="btn btn-primary dcha"><spring:message code="global.enviar"/></button>
									</div>
								
						    	</form:form>
					
						    </div>
						    
					
							<div class="col-md-6 col-sm-6">
								<div class="separa">
									<h3><label><spring:message code="global.fotos"/></label></h3>
								</div>
								<div class="clearfix"></div>			
					
							    <div class="separa" id="divFotografias"></div>					
								
						    </div>
						    		
					    </div><!-- /container-fluid -->



                        
                        </div>
                    </div>
                </div>
            </div>

    <script>
	    $(document).ready(function() {
	    	$('.btnEditaAnuncio').hide();
	    	
	    	$("#divFotografias").load("adminEdicionFotografias.action?ida=<c:out value="${anuncio.anuncio.idAnuncioCodificado}"/>");
	    	
	        $('.btnValidaAnuncio').each( function() {
	        	$(this).unbind('click').bind('click', function() {
	        		var boton = $(this);
	        		if ( $(this).hasClass('btn-success') ) {
	        			if ( confirm("<spring:message code="anuncio.desactiva.confirme"/>") ) {
							$.getJSON( 'adminCambiaEstadoAnuncio.action?w=0&s=' + $(this).attr('cod'), function( res ) {
								if ( res.resultado == 'OK' ) {
									boton.removeClass('btn-success').addClass('btn-default');
									$('#divEstadoAnuncio').removeClass('alert-success').addClass('alert-warning');
									$('#textoEstadoAnuncio').html("<spring:message code="anuncio.estado.0"/>");
								} else {
									creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
								}
							});	        				
	        			}
	        		} else if ( $(this).hasClass('btn-default') ) { // Activar
						$.getJSON('adminCambiaEstadoAnuncio.action?w=1&s=' + $(this).attr('cod'), function( res ) {
							if ( res.resultado == 'OK' ) {
								boton.removeClass('btn-default').addClass('btn-success');
								$('#divEstadoAnuncio').removeClass('alert-warning').addClass('alert-success');
								$('#textoEstadoAnuncio').html("<spring:message code="anuncio.estado.1"/>");
							} else {
								creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
							}
						});
	        		} else if ( $(this).hasClass('btn-primary') ) { // Reciclar
	        			if ( confirm("<spring:message code="anuncio.recupera.confirme"/>") ) {
							$.getJSON('adminCambiaEstadoAnuncio.action?w=1&s=' + $(this).attr('cod'), function( res ) {
								if ( res.resultado == 'OK' ) {
									boton.removeClass('btn-primary').addClass('btn-success');
									boton.find('i').removeClass('fa-undo').addClass('fa-check');
									$('#btnEliminaAnuncio_'+boton.attr('cod')).removeClass('disabled')
									$('#divEstadoAnuncio').removeClass('alert-danger').addClass('alert-success');									
									$('#textoEstadoAnuncio').html("<spring:message code="anuncio.estado.1"/>");
								} else {
									creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
								}
							});
	        			}
	        		}
	        	});
	    	});

	        $('.btnEliminaAnuncio').each( function() {
	    		var boton = $(this);
	    		$(this).unbind('click').bind('click', function() {
	    			if ( !boton.hasClass('disabled') ) {
		    			if ( confirm("<spring:message code="anuncio.del.confirme"/>") ) {
							$.getJSON( 'adminCambiaEstadoAnuncio.action?w=2&s=' + $(this).attr('cod'), function( res ) {
								if ( res.resultado == 'OK' ) {
									$('#btnValidaAnuncio_'+boton.attr('cod')).find('i').removeClass('fa-check').addClass('fa-undo');
									$('#btnValidaAnuncio_'+boton.attr('cod')).removeClass('btn-success').removeClass('btn-default').addClass('btn-primary');
									boton.addClass('disabled');
									$('#divEstadoAnuncio').removeClass('alert-success').removeClass('alert-warning').addClass('alert-danger');									
									$('#textoEstadoAnuncio').html("<spring:message code="anuncio.estado.2"/>");
								} else {
									creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
								}
							});	        				
		    			}	    			
		    		}	    			
	    		});
	    	});	    	
	    	
			$("#btnSubmitDatosVela").unbind('click').click( function() {
			    $.ajax({
			        url: $('#editaVelaForm').attr('action'),
			        dataType: "json",
			        type: 'post',
			        data: $('#editaVelaForm').serialize(),
			        success: function(json) {
			            if (json.redirect) {
			                window.location.href = data.redirect;
			            }
			            else {
				        	if ( json.errores != undefined ) {
				        		 $.each(json.errores, function(i, item) {
				        			 var id = Object.keys(item)[0];
				        			 var mensaje = item[id];
				        			 $('#errorFormulario_'+id).html(mensaje);
				                 });
				        	} else if ( json.resultado != undefined ) {
				        		creaVentanaModal("<spring:message code="global.mensaje"/>",json.resultado.mensaje);
				        	} else {
				        		alert( 'ERR' );
				        	} 
			            }
			        }
			    });
			    return false;
			});
	    	
	        $('#btnVolver').unbind('click').bind('click', function() {
	        	history.back();
	        });

			$('#editaVelaForm_tipoVela').change(function() {
				var idTipoVela = $(this).find('option:selected').val();
				configuraFormularioPorTipoVela("#editaVelaForm_", idTipoVela);
			});
			
			$('#selCrucero').click(function() {
				$('#editaVelaForm_tipoClase').val('C');
				$('#editaVelaForm_tipoBarco').val("1");
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.crucero"/>");
				buscaTiposDeVela("#editaVelaForm_", "1");
			});

			$('#serRC').click(function() {
				$('#editaVelaForm_tipoClase').val('R');
				$('#editaVelaForm_tipoBarco').val("2");
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.velaRc"/>");
				buscaTiposDeVela("#editaVelaForm_", "2");
			});

			$('#selWindsurf').click(function() {
				$('#editaVelaForm_tipoClase').val('W');
				$('#editaVelaForm_tipoBarco').val("3");
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.windsurf"/>");
				buscaTiposDeVela("#editaVelaForm_", "3");
			});

			$('#selKiteboarding').click(function() {
				$('#editaVelaForm_tipoClase').val('K');
				$('#editaVelaForm_tipoBarco').val("4");
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.kiteboarding"/>");
				buscaTiposDeVela("#editaVelaForm_", "4", function() {
					if ( !$("#editaVelaForm_tipoCometa").val() ) {
						$("#editaVelaForm_tipoCometa").val("-1");
					}
				});
			});

			$('#seltipoClase').click(function() {
				$('#editaVelaForm_tipoClase').val('L');
				openModalSelectorClaseVelaLigera();
				configuraTodosBotonesActivosPorTiposClase();
			});

			$('.btn-sel-clase-vela-ligera').each( function() {
				var boton = $(this);
				boton.unbind('click').bind('click', function() {
					$('#editaVelaForm_tipoBarco').val( boton.attr('idClase') );
					$('#nombreClaseBarco').val( boton.attr('titulo') );
					buscaTiposDeVela( "#editaVelaForm_", boton.attr('idClase') );
		        });
			});

			$("#btnSubmitFotosVela").unbind('click').click( function() {
				$('#editaFotosVelaForm').submit();
			});
			
			$('#cambiarTipoBarco').click(function() {
	        	$('#editaVelaForm_tipoBarco').val('');
				$('#nombreClaseBarco').val('');
				openModalTipoBarco();
				configuraTodosBotonesActivosPorTiposBarco();
			});
			
			inicializaForm();
		});
		
		
		
		function inicializaForm() {
			$('#tipoBarcoLeyenda').hide();
			if ( $('#editaVelaForm_tipoClase').val() == "C" ) {
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.crucero"/>");
			} else if ( $('#editaVelaForm_tipoClase').val() == "R" ) {
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.velaRc"/>");
			} else if ( $('#editaVelaForm_tipoClase').val() == "W" ) {
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.windsurf"/>");
			} else if ( $('#editaVelaForm_tipoClase').val() == "K" ) {
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.kiteboarding"/>");
			} else if ( $('#editaVelaForm_tipoClase').val() == "L" ) {
				$('#nombreClaseBarco').val("${nombreClaseBarco}");
			} else {
				openModalTipoBarco();
				configuraTodosBotonesActivosPorTiposBarco();
			}
			var tipoVela = $('#editaVelaForm_tipoVela option:selected').val();
			if ( tipoVela ) {
				configuraFormularioPorTipoVela("#editaVelaForm_", tipoVela);
			}			
			buscaTiposDeVela("#editaVelaForm_", $('#editaVelaForm_tipoBarco').val());
		}
    </script>