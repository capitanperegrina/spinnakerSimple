<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
	<spring:message code="global.newPassword" var="newPasswordPlaceholder"/>
	<spring:message code="global.repeatPassword" var="passRepetidaPlaceholder"/>
	
    <div class="container-fluid">

 		<form:form action="resetPass.action?id=${id}&pw=${pw}&phm=${phm}" method="post" class="fondogris" modelAttribute="loginForm" id="loginForm">
			<form:input type="hidden" path="tipoUso"/>
			<div class="col-md-12 col-sm-12 fondogris">

				<div class="col-md-5 col-sm-5">
					<div class="col-md-12 col-sm-12 separa">
						<span id="imagenvela">
							<img id="imagenvelaImg" src="imagenes/home/home-login.jpg" class="img-responsive anchototal" alt="<spring:message code="loginForm.titulo"/>" />
						</span>
					</div>
				</div>

				<div class="col-md-7 col-sm-7">
				
					<div class="text-center">
			    		<h2><spring:message code="resetPass.titulo"/></h2>
    					<div class="separa"><spring:message code="resetPassRequest.ayuda"/></div>
					</div>

					<div class="text-center separa">
      					<div class="separa errorFormulario"><c:out value="${msg}"/></div>
    				</div>
				
					<div class="separa">
      					<label for="loginForm_emailLogin"><spring:message code="global.newPassword"/></label> <form:errors path="passNew" cssClass="errorFormulario" />
    				</div>
					<div>
						<form:input type="password" path="passNew" class="form-control ancho100" id="loginForm_passNew" placeholder="${newPasswordPlaceholder}"/>
    				</div>
 					<div class="clearfix"></div>
				
					<div class="separa">
      					<label for="loginForm_passRepetida"><spring:message code="global.repeatPassword"/></label> <form:errors path="passRepetida" cssClass="errorFormulario" />
    				</div>
					<div>
						<form:input type="password" path="passRepetida" class="form-control ancho100" id="loginForm_passRepetida" placeholder="${passRepetidaPlaceholder}"/>
    				</div>
 					<div class="clearfix"></div>
 					 					
					<div class="separa">
						<form:errors path="captcha" cssClass="errorFormulario"  />
						<div class="g-recaptcha" data-sitekey="<c:out value="${reCaptchaSiteKey}"/>">/div>
      				</div>
    				<div class="clearfix"></div>
    				
    				<div class="separa" style="float:right;">
    					<button type="submit" class="btn btn-primary dcha"><spring:message code="global.enviar"/></button>
  					</div>
          			
				</div>

			</div>
 
 		</form:form>

 	</div>