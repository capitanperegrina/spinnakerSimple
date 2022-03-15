<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

            <div class="row">
                <div class="col-lg-12">

					<div class="float-izda">
						<h1><spring:message code="anuncio.consulta.titulo"/></h1>
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
                        <div class="panel-body">
							<c:out value="${anuncio.anuncio.divEstado}" escapeXml="false"/>

							<div class="col-md-8 col-sm-8">
					
								<img src="<c:out value="${anuncio.foto1}"/>" alt="<c:out value="${anuncio.anuncio.tituloAnuncio}"/>" class="img-responsive anchototal" id="fotoPrincipal">
								<div class="col-md-12 col-sm-12" style="margin-top:15px;">
				
<c:if test="${empty anuncio.fotos}">
									<div class="col-md-3 col-sm-3 col-xs-3 alto250">
					            		<a onclick="ampliafoto(1);" class="thumbnail" id="thumb" href="">
					              			<img src="<c:out value="${anuncio.foto1}"/>" alt="<spring:message code="vela.foto"/> 1" id="miniaturaFoto1"/>
					            		</a>
					          		</div>
</c:if>
<c:if test="${not empty anuncio.fotos}">
	<c:forEach items="${anuncio.fotos}" var="thumb" varStatus="status">
									<div class="col-md-3 col-sm-3 col-xs-3 alto250">
					            		<a onclick="ampliafoto(<c:out value="${status.index}"/>);" class="thumbnail" id="thumb">
					              			<img src="<c:out value="${thumb.imagen}"/>" alt="<spring:message code="vela.foto"/> <c:out value="${status.index}"/>" id="miniaturaFoto<c:out value="${status.index}"/>"/>
					            		</a>
					          		</div>
	</c:forEach>
</c:if>
									<div class="clearfix"></div>
								</div>
							</div>
							
							
							
							<div class="col-md-4 col-sm-4">
							
								<div class="titulovela lnkAnuncio"><c:out value="${anuncio.anuncio.tituloAnuncio}"/></div>
							<c:if test="${!empty anuncio.anuncio.gratil}">
							    <div class="medidasLabel"><spring:message code="vela.gratil"/></div>
							    <div class="medidas"><c:out value="${anuncio.anuncio.gratil}"/> <spring:message code="unidades.longitud.metros"/></div>
							    <div class="clearfix"></div>
							</c:if>
							<c:if test="${!empty anuncio.anuncio.baluma}">
							    <div class="medidasLabel"><spring:message code="vela.baluma"/></div>
							    <div class="medidas"><c:out value="${anuncio.anuncio.baluma}"/> <spring:message code="unidades.longitud.metros"/></div>
							    <div class="clearfix"></div>
							</c:if>
							<c:if test="${!empty anuncio.anuncio.pujamen}">
							    <div class="medidasLabel"><spring:message code="vela.pujamen"/></div>
							    <div class="medidas"><c:out value="${anuncio.anuncio.pujamen}"/> <spring:message code="unidades.longitud.metros"/></div>
							    <div class="clearfix"></div>
							</c:if>
							<c:if test="${!empty anuncio.anuncio.caidaProa}">
							    <div class="medidasLabel"><spring:message code="vela.caidaProa"/></div>
							    <div class="medidas"><c:out value="${anuncio.anuncio.caidaProa}"/> <spring:message code="unidades.longitud.metros"/></div>
							    <div class="clearfix"></div>
							</c:if>
							<c:if test="${!empty anuncio.anuncio.caidaPopa}">
							    <div class="medidasLabel"><spring:message code="vela.caidaPopa"/></div>
							    <div class="medidas"><c:out value="${anuncio.anuncio.caidaPopa}"/> <spring:message code="unidades.longitud.metros"/></div>
							    <div class="clearfix"></div>
							</c:if>
							<c:if test="${!empty anuncio.anuncio.tipoCometaDescripcion}">
							    <div class="medidasLabel"><spring:message code="vela.tipoCometa"/></div>
							    <div class="medidas"><c:out value="${anuncio.anuncio.tipoCometaDescripcion}"/></div>
							    <div class="clearfix"></div>
							</c:if>
							<c:if test="${!empty anuncio.anuncio.superficie}">
							    <div class="medidasLabel"><spring:message code="vela.superficie"/></div>
							    <div class="medidas"><c:out value="${anuncio.anuncio.superficie}"/> <spring:message code="unidades.superficie.metrosCuadrados"/></div>
							    <div class="clearfix"></div>
							</c:if>
					        <c:if test="${!empty anuncio.anuncio.gramaje}">
					            <div class="medidasLabel"><spring:message code="vela.gramaje"/></div>
					            <div class="medidas"><c:out value="${anuncio.anuncio.gramaje}"/> <spring:message code="unidades.densidad.gramosPorMetroCuadrado"/></div>
					            <div class="clearfix"></div>        
					        </c:if>
					        	
								<div class="medidasLabel" style="margin-top: 1em"><spring:message code="global.descripcion"/></div>
								<div class="clearfix"></div>
					        	<div><c:out value="${anuncio.anuncio.descripcion}"/></div>
					        	<div class="clearfix"></div>
					
					        	<p>&nbsp;</p>
					        	<div class="precio" style="margin-right: .2em; border-top: 1px solid #000000; border-bottom: 1px solid #000000; width: 100%; text-align: right !important;"><c:out value="${anuncio.anuncio.precio}"/> <c:out value="${simboloDivisa}" escapeXml="false"/></div>
					        	<p>&nbsp;</p>
					        	
                        	</div>
                    	</div>
                	</div>
            	</div>
            </div>

    <script>
	    $(document).ready(function() {
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
	        

	    	
	    	$('.btnEditaAnuncio').each( function() {
	    		$(this).unbind('click').bind('click', function() {
	    			document.location='adminEditarVela.action?ida=' + $(this).attr('cod');
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
	        
	        $('#btnVolver').unbind('click').bind('click', function() {
	        	history.back();
	        });
	    });
    </script>
