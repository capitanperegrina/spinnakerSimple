<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

<c:if test="${empty mensajes}">
							<div class="alert alert-danger">
								<spring:message code="global.nadaEncontrado"/>								
							</div>
</c:if>
<c:if test="${not empty mensajes}">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable-mensajes">
                                <thead>
                                    <tr>
                                    	<th><spring:message code="global.fecha"/></th>
                                    	<th><spring:message code="global.nombre"/></th>
                                        <th><spring:message code="global.mensaje"/></th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
	<c:forEach items="${mensajes}" var="mensaje" varStatus="status">
		<c:if test="${status.index % 2 == 0}">
									<tr class="odd">
		</c:if>	
		<c:if test="${status.index % 2 == 1}">
									<tr class="even">
		</c:if>
										<td><span style="display: none;">${mensaje.fechaOrdenacion}</span>${mensaje.fecha}</span></td>
                                        <td>${mensaje.nombre}</td>
                                        <td>${mensaje.extractoTitulo}</td>
                                        <td class="centrado">${mensaje.botonera}</td>
                                    </tr>
	</c:forEach>
                                </tbody>
                            </table>
</c:if>

    <script>
	    $(document).ready(function() {
	    	$('.btnVerMensaje').each( function() {
	    		$(this).unbind('click').bind('click', function() {
	    			creaVentanaModal("<spring:message code="global.mensaje"/>", $(this).attr('msg'));
	    			// document.location='adminVerMensaje.action?idm=' + $(this).attr('cod');
	    		});
	    	});

	        $('#dataTable-mensajes').DataTable({
	            responsive: true,
	            stateSave: true
	        });
	    });
    </script>