<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
	<spring:message code="global.nombre" var="nombrePlaceholder"/>
	<spring:message code="global.apellidos" var="apellidosPlaceholder"/>
	<spring:message code="global.direccion" var="direccion1Placeholder"/>
	<spring:message code="global.localidad" var="direccion2Placeholder"/>
	<spring:message code="global.provincia" var="provinciaPlaceholder"/>
	<spring:message code="global.email" var="emailPlaceholder"/>
	
				
    <div class="container-fluid">
    
		<form:form action="signUp.action" method="post" class="fondogris" enctype="multipart/form-data" modelAttribute="signUpForm" id="signUpForm">
			<div class="col-md-12 col-sm-12 fondogris">

				<div class="col-md-5 col-sm-5">
					<div class="col-md-12 col-sm-12 separa">
						<span id="imagenvela">
							<img id="imagenvelaImg" src="imagenes/home/home-login.jpg" class="img-responsive anchototal" alt="<spring:message code="loginForm.titulo"/>" />
						</span>
					</div>
				</div>

				<div class="col-md-7 col-sm-7">

			    	<div class="col-md-12 col-sm-12 text-center fondogris">
			    		<h2><spring:message code="signUpForm.titulo"/></h2>
			    	</div>
					
					<div class="separa">
						<label for="signUpForm_nombre"><spring:message code="global.nombre"/></label> * <form:errors path="nombre" cssClass="errorFormulario" />
      				</div>
					<div>
						<form:input type="text" path="nombre" class="form-control ancho100" id="signUpForm_nombre" placeholder="${nombrePlaceholder}"/>
      				</div>
 					<div class="clearfix"></div>
 					
 					
					<div class="separa">
						<label for="signUpForm_apellidos"><spring:message code="global.apellidos"/></label> <form:errors path="apellidos" cssClass="errorFormulario" />
      				</div>
					<div>
						<form:input type="text" path="apellidos" class="form-control ancho100" id="signUpForm_apellidos" placeholder="${apellidosPlaceholder}"/>
      				</div>
 					<div class="clearfix"></div>
 					
 					
					<div class="separa">
      					<label for="signUpForm_mail"><spring:message code="global.email"/></label> * <form:errors path="mail" cssClass="errorFormulario" />
    				</div>
					<div>
						<form:input type="mail" path="mail" class="form-control ancho100" id="signUpForm_mail" placeholder="${emailPlaceholder}"/>
    				</div>
 					<div class="clearfix"></div>
    				
					<div class="separa">
      					<label for="signUpForm_movil"><spring:message code="global.movil"/></label> <form:errors path="movil" cssClass="errorFormulario" />
    				</div>
					<div>
      					<form:input type="tel" path="movil" class="form-control" style="width: 200px !important" id="signUpForm_movil" placeholder="+00999999999"/>
    				</div>
    				<div class="clearfix"></div>

    				
					<div class="separa">
						<label for="signUpForm_direccion1"><spring:message code="global.direccion"/></label> <form:errors path="direccion1" cssClass="errorFormulario" />
      				</div>
					<div>
						<form:input type="text" path="direccion1" class="form-control ancho100" id="signUpForm_direccion1" placeholder="${direccion1Placeholder}"/>
      				</div>
    				<div class="clearfix"></div>
    				
 					
					<div class="separa">
						<label for="signUpForm_direccion2"><spring:message code="global.localidad"/></label> <form:errors path="direccion2" cssClass="errorFormulario" />
      				</div>
					<div>
						<form:input type="text" path="direccion2" class="form-control ancho100" id="signUpForm_direccion2" placeholder="${direccion2Placeholder}"/>
      				</div>
    				<div class="clearfix"></div>
 					
 					
					<div class="separa">
      					<label for="signUpForm_codPostal"><spring:message code="global.codPostal"/></label> <form:errors path="codPostal" cssClass="errorFormulario" />
    				</div>
					<div>
						<form:input type="text" path="codPostal" class="form-control" style="width: 150px !important" id="signUpForm_codPostal" placeholder="99999"/>
    				</div>
 					<div class="clearfix"></div>
 					
 					
					<div class="separa">
						<label for="signUpForm_provincia"><spring:message code="global.provincia"/></label> <form:errors path="provincia" cssClass="errorFormulario" />
      				</div>
					<div>
						<form:input type="text" path="provincia" class="form-control" id="signUpForm_provincia" placeholder="${provinciaPlaceholder}"/>
      				</div>
 					<div class="clearfix"></div>
 					
 
					<div class="separa">
						<label for="signUpForm_pais"><spring:message code="global.pais"/></label> * <form:errors path="pais" cssClass="errorFormulario" />
					</div>
					<div>
						<form:select path="pais" class="form-control" id="signUpForm_pais">
							<option value="-1"><spring:message code="global.seleccionePais"/></option>
      						<form:options items="${paises}" itemValue="codigo" itemLabel="nombrePais"/>
						</form:select>
					</div>
					<div class="clearfix"></div>
						
					<div class="separa">
						<label for="signUpForm_divisa"><spring:message code="global.divisaPreferida"/></label> <form:errors path="divisa" cssClass="errorFormulario" />
					</div>
					<div>
						<form:select path="divisa" class="form-control" id="signUpForm_divisa">
      						<form:options items="${divisas}" itemValue="codigoDivisa" itemLabel="descripcionCompleta" htmlEscape="false"/>
						</form:select>
					</div>
					<div class="clearfix"></div>
					
					<div class="separa">
						<form:checkbox id="signUpForm_privacidad" path="privacidad" value="S" /> <label for="signUpForm_privacidad"><spring:message code="privacidad.acepto"/></label> * <form:errors path="privacidad" cssClass="errorFormulario" /><br/>
						<form:checkbox id="signUpForm_terminos" path="terminos" value="S" /> <label for="signUpForm_terminos"><spring:message code="terminos.acepto"/></label> * <form:errors path="terminos" cssClass="errorFormulario" />
					</div>
          			
					<div class="separa">
						<form:errors path="captcha" cssClass="errorFormulario"  />
						<div class="g-recaptcha" data-sitekey="<c:out value="${reCaptchaSiteKey}"/>"></div>
      				</div>
    				<div class="clearfix"></div>
          			
				</div>
      			<div class="form-group col-md-12 col-sm-12" style="float:right;">
    				<button type="submit" class="btn btn-primary dcha"><spring:message code="global.enviar"/></button>
  				</div>
			</div>

		</form:form>

	</div> <!-- /container-fluid -->
	

    <script>
	    $(document).ready(function() {
	    	$('#signUpForm_privacidad').bind('click', function() {
	    		if ( $('#signUpForm_privacidad').prop('checked') ) {
	    			$('#tituloVentanaModal').html("<spring:message code="global.mensaje"/>");
	    			$('#contenidoVentanaModal').load('legalStuffAjax.action?f=U');
	    			$('#ventanaModal').modal('show');
	    		}
	    	});
	    });
    </script>