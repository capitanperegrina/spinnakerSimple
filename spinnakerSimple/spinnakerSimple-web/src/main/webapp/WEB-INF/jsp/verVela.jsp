<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

	<div class="container-fluid" style="position:relative;">
    
		<div class="col-md-8 col-sm-8">

<c:if test="${modoPagina == 'misAnuncios'}">
					<div style="float: left; text-align: left; margin-top: 2px; color: #c7630e; font-weight: bold; font-size: 1.1em;">
						<c:out value="${anuncio.anuncio.estadoAnuncioDescripcion}"/>
					</div>
					<div style="float: right; text-align: right; margin-bottom: 4px;">
						<c:out value="${anuncio.anuncio.botones}" escapeXml="false"/>
					</div>
					<div class="clearfix"></div>			
</c:if>
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
			<h2 class="titulovela lnkAnuncio">
				<c:out value="${anuncio.anuncio.tituloAnuncio}"/>
			</h2>
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
<c:if test="${modoPagina != 'misAnuncios'}">
	<c:if test="${socialButtons eq 'S'}">
			<div class="divSocialButtons">
        		<a href="http://facebook.com/sharer.php?t=<c:out value="${socialBean.texto}"/>&u=<c:out value="${socialBean.url}"/>" target="_blank" class=""><i class="btnSocial btnSocial-fa-facebook fab fa-facebook"></i></a>
        		<a href="https://twitter.com/intent/tweet?text=<c:out value="${socialBean.texto}"/>&url=<c:out value="${socialBean.url}"/>&via=<c:out value="${socialBean.via}"/>"><i class="btnSocial btnSocial-fa-twitter fab fa-twitter"></i></a>
        		<a href="whatsapp://send?text=<c:out value="${socialBean.texto}"/> https%3A%2F%2Fspinnakersimple.com" data-action="share/whatsapp/share"><i class="btnSocial btnSocial-fa-whatsapp fab fa-whatsapp"></i></a>			
			</div>
	</c:if>
			<div style="padding: 1em; background-color: #bfcfff; -moz-border-radius: 5px; -webkit-border-radius: 5px; -khtml-border-radius: 5px; border-radius: 5px;">
				<h3 style="margin-bottom: 1em; width: 100%; text-align:center; font-weight: bold;"><spring:message code="vela.formulario.contacto"/></h3>
				<form:form id="formularioMovil" action="consultaVela.action" modelAttribute="consultarVelaForm" method="post">
					<div style="margin-top: .6em"><label><spring:message code="vela.formulario.contacto.nombre"/> *</label> <form:errors path="nombre" cssClass="errorFormulario" /></div>
					<div><form:input path="nombre" type="text" style="width: 100%;"/></div>
          			<div style="margin-top: .6em"><label><spring:message code="vela.formulario.contacto.correo"/> *</label> <form:errors path="mail" cssClass="errorFormulario"  /></div>
          			<div><form:input path="mail" type="mail" style="width: 100%;"/></div>
          			<div style="margin-top: .6em"><label><spring:message code="vela.formulario.contacto.telefono"/> *</label> <form:errors path="telefono" cssClass="errorFormulario" /></div>
          			<div><form:input path="telefono" type="telefono" style="width: 100%;"/></div>
          			<div style="margin-top: .6em"><label><spring:message code="vela.formulario.contacto.observaciones"/></label> <form:errors path="observaciones" cssClass="errorFormulario" /></div>
          			<div><form:textarea path="observaciones" style="width: 100%;"/></div>
          			<div style="margin-top: .6em">
          				<form:checkbox id="formularioMovil_privacidad" path="privacidad" value="S" /> 
          				<label for="formularioMovil_privacidad"><spring:message code="privacidad.acepto"/></label> * <form:errors path="privacidad" cssClass="errorFormulario" /></div>
          			<div style="margin-top: 1em;">
						<form:errors path="captcha" cssClass="errorFormulario"  />
						<div style="transform:scale(0.86); transform-origin:0 0; }" class="g-recaptcha" data-sitekey="<c:out value="${reCaptchaSiteKey}"/>"></div>
          			</div>
          			<br/>
          			<form:hidden path="ida"/>
          			<input id="btSubmitVerVela1" class="btn btn-primary" style="margin-bottom:5px;" value="Solicitar información" type="button"/>
				</form:form>
			</div>
        	<div class="clearfix"></div>
</c:if>
        </div>
        
    </div><!-- /container-fluid -->
    
<script>
	$(document).ready(function(){

<c:if test="${modoPagina != 'misAnuncios'}">		
		$("#btSubmitVerVela1").unbind('click').click( function() {
			$('#formularioMovil').submit();
		});
</c:if>
		
<c:if test="${modoPagina == 'misAnuncios'}">

		$('.btnAnuncioMod').each( function() {
			$(this).unbind('click').click( function() {
				document.location='miAnuncioMod.action?ida=' + $(this).attr('ida') + generaFormQueryString();
			});	
		});
		
	    $('.btnAnuncioDel').each( function() {
			$(this).unbind('click').click( function() {
				if ( confirm("<spring:message code="anuncio.del.confirme"/>") ) {
					document.location='miAnuncioCambiaEstado.action?ne=2&ida=' + $(this).attr('ida') + generaFormQueryString();					
				}
			});	
		});
	    
	    $('.btnAnuncioRecupera').each( function() {
			$(this).unbind('click').click( function() {
				if ( confirm("<spring:message code="anuncio.recupera.confirme"/>") ) {
					document.location='miAnuncioCambiaEstado.action?ne=1&ida=' + $(this).attr('ida') + generaFormQueryString();					
				}
			});	
		});
	    
	    $('.btnAnuncioOk').each( function() {
			$(this).unbind('click').click( function() {
				document.location='miAnuncioCambiaEstado.action?ne=1&ida=' + $(this).attr('ida') + generaFormQueryString();
			});	
		});
</c:if>

		$('#formularioMovil_privacidad').bind('click', function() {
			if ( $('#formularioMovil_privacidad').prop('checked') ) {
				$('#tituloVentanaModal').html("<spring:message code="global.mensaje"/>");
				$('#contenidoVentanaModal').load('legalStuffAjax.action?f=C');
				$('#ventanaModal').modal('show');
			}
		});
	});
</script>
