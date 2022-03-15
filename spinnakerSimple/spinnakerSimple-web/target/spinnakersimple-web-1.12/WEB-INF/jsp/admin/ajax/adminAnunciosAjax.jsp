<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

<c:if test="${empty anuncios}">
							<div class="alert alert-danger">
								<spring:message code="global.nadaEncontrado"/>								
							</div>
</c:if>
<c:if test="${not empty anuncios}">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable-anuncios">
                                <thead>
                                    <tr>
										<th></th>
										<th><spring:message code="global.titulo"/></th>                                    
                                    	<th class="centrado"><spring:message code="global.fecha"/></th>
                                        <th><spring:message code="vela.tipo"/></th>
                                        <th><spring:message code="global.precio"/></th>
                                        <th class="centrado"><spring:message code="global.paraCaducar"/></th>
                                    </tr>
                                </thead>
                                <tbody>
	<c:forEach items="${anuncios}" var="anuncio" varStatus="status">
		<c:if test="${status.index % 2 == 0}">
									<tr class="odd">
		</c:if>	
		<c:if test="${status.index % 2 == 1}">
									<tr class="even">
		</c:if>
										<td class="centrado">${anuncio.botonesAdministrador}</td>
										<td>${anuncio.extractoTitulo}</td>
										<td class="centrado"><span style="display: none;">${anuncio.fechaOrdenacion}</span>${anuncio.fecAlta}</span></td>
                                        <td>${anuncio.tipoVelaDescripcion}</td>
                                        <td>${anuncio.precio}</td>
                                        <td class="centrado"><span style="display: none;">${anuncio.diasParaCaducarOrdenacion}</span>${anuncio.diasParaCaducar}</span></td>
                                    </tr>
	</c:forEach>
                                </tbody>
                            </table>
</c:if>

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
								} else {
									creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
								}
							});	        				
	        			}
	        		} else if ( $(this).hasClass('btn-default') ) { // Activar
						$.getJSON('adminCambiaEstadoAnuncio.action?w=1&s=' + $(this).attr('cod'), function( res ) {
							if ( res.resultado == 'OK' ) {
								boton.removeClass('btn-default').addClass('btn-success');
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
								} else {
									creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
								}
							});	        				
		    			}	    			
		    		}	    			
	    		});
	    	});
	    	
	    	$('.btnVerAnuncio').each( function() {
	    		$(this).unbind('click').bind('click', function() {
	    			document.location='adminVerVela.action?ida=' + $(this).attr('cod');
	    		});
	    	});
	    	
	    	$('.btnEditaAnuncio').each( function() {
	    		$(this).unbind('click').bind('click', function() {
	    			document.location='adminEditarVela.action?ida=' + $(this).attr('cod');
	    		});
	    	});

	        $('#dataTable-anuncios').DataTable({
	            responsive: true,
	            stateSave: true
	        });
	    });
    </script>