<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

<c:if test="${empty usuarios}">
							<div class="alert alert-danger">
								<spring:message code="global.nadaEncontrado"/>								
							</div>
</c:if>
<c:if test="${not empty usuarios}">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable-anuncios">
                                <thead>
                                    <tr>
                                    	<th><spring:message code="global.fecha"/></th>
                                        <th><spring:message code="global.nombre"/></th>
                                        <th><spring:message code="anuncios.numero"/></th>
                                        <th><spring:message code="global.email"/></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
	<c:forEach items="${usuarios}" var="usuario" varStatus="status">
		<c:if test="${status.index % 2 == 0}">
									<tr class="odd">
		</c:if>	
		<c:if test="${status.index % 2 == 1}">
									<tr class="even">
		</c:if>
										<td><span style="display: none;">${usuario.fechaOrdenacion}</span>${usuario.fechaAlta}</span></td>
                                        <td>${usuario.nombreCompleto}</td>
                                        <td>${usuario.numAnuncios}</td>
                                        <td>${usuario.mail}</td>
                                        <td class="centrado">${usuario.botonera}</td>
                                    </tr>
	</c:forEach>
                                </tbody>
                            </table>
</c:if>

    <script>
	    $(document).ready(function() {

	    	$('.btnVerUsuario').each( function() {
	    		$(this).unbind('click').bind('click', function() {
	    			document.location='adminVerUsuario.action?idu=' + $(this).attr('cod');
	    		});
	    	});
	    	
	    	$('.btnModUsuario').each( function() {
	    		$(this).unbind('click').bind('click', function() {
	    			document.location='adminElPerfil.action?id=' + $(this).attr('cod');
	    		});
	    	});

	    	$('.btnDelUsuario').each( function() {
	    		var boton = $(this);
	    		$(this).unbind('click').bind('click', function() {
	    			if ( !boton.hasClass('disabled') ) {
		    			if ( confirm("<spring:message code="usuario.del.confirme"/>") ) {
							$.getJSON( 'adminEliminaUsuariosCompletamente.action?w=' + $(this).attr('cod'), function( res ) {
								if ( res.resultado == 'OK' ) {
									boton.addClass('disabled');
									creaVentanaModal("<spring:message code="global.mensaje"/>",res.mensaje);
									$('#adminUsuariosAjax').load('adminUsuariosAjax.action');
								} else {
									creaVentanaModal("<spring:message code="global.error"/>",res.mensaje);
								}
							});	        				
		    			}	    			
		    		}	    			
	    		});	
	    	});
	    	
	        $('#dataTable-anuncios').DataTable({
	            responsive: true,
	            stateSave: true
	        });
	    });
    </script>