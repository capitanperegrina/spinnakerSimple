<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp"%>

<spring:message code="contacto.nombre" var="nombrePlaceholder"/>
<spring:message code="contacto.email" var="emailPlaceholder"/>

		<div class="container-fluid">
		
			<form:form id="contactoForm" action="contacto.action" class="col-md-6 col-sm-6" modelAttribute="contactoForm" method="post">

				<div class="form-group">
					<label for="contactoForm_nombre">
						<spring:message code="contacto.nombre" />
					</label> * <form:errors path="nombre" cssClass="errorFormulario"  />
					<form:input path="nombre" type="text" class="form-control" id="contactoForm_nombre" placeholder="${contacto.nombre}"/>
				</div>
				
				<div class="form-group">
					<label for="contactoForm_email">
						<spring:message code="contacto.email" />
					</label> * <form:errors path="email" cssClass="errorFormulario"  />
					<form:input path="email" type="email" class="form-control" id="contactoForm_email"  placeholder="${contacto.email}"/>
				</div>
				
				<div class="form-group">
					<label for="contactoForm_consulta">
						<spring:message code="contacto.consultar" />
					</label> * <form:errors path="consulta" cssClass="errorFormulario"  />
					<form:textarea path="consulta" class="form-control" rows="3" id="contactoForm_consulta"/>
				</div>
				
				<div class="separa">
					<form:checkbox id="contactoForm_privacidad" path="privacidad" value="S" /> <label for="contactoForm_privacidad"><spring:message code="privacidad.acepto"/></label> * <form:errors path="privacidad" cssClass="errorFormulario" /><br/>
				</div>
				
				<div class="form-group">
					<form:errors path="captcha" cssClass="errorFormulario"  />
					<div class="g-recaptcha" data-sitekey="<c:out value="${reCaptchaSiteKey}"/>"></div>
				</div>
				
				<button class="btn btn-app btn-primary" onclick="javascript:enviamail();">
					<i class="fa fa-envelope"></i> <spring:message code="contacto.enviar" />
				</button>

			</form:form>
		
			<div class="col-md-6 col-sm-6">
<c:if test="${not empty msg}">
				<c:out value="${msg}"/>
</c:if>
<c:if test="${empty msg}">
				<spring:message code="contacto.texto.base" />
</c:if>
			</div>

		</div>
		<!-- /container-fluid -->
		
    <script>
	    $(document).ready(function() {
	    	$('#contactoForm_privacidad').bind('click', function() {
	    		if ( $('#contactoForm_privacidad').prop('checked') ) {
	    			$('#tituloVentanaModal').html("<spring:message code="global.mensaje"/>");
	    			$('#contenidoVentanaModal').load('legalStuffAjax.action?f=M');
	    			$('#ventanaModal').modal('show');
	    		}
	    	});
	    });
    </script>		