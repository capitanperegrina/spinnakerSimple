<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

		<img src="<c:out value="${anuncio.foto1}"/>" alt="<c:out value="${anuncio.anuncio.tituloAnuncio}"/>" class="img-responsive anchototal" id="fotoPrincipal">
		<div class="col-md-12 col-sm-12" style="margin-top:15px;">

<c:if test="${empty fotos}">
			<div class="col-md-3 col-sm-3 col-xs-3 alto250">
           		<a onclick="ampliafoto(1);" class="thumbnail" id="thumb">
             			<img src="" alt="<spring:message code="vela.foto"/> 1" id="miniaturaFoto1"/>
           		</a>
         	</div>
</c:if>
<c:if test="${not empty fotos}">
	<c:forEach items="${fotos}" var="thumb" varStatus="status">
			<div class="col-md-3 col-sm-3 col-xs-3 alto250" id="foto_<c:out value="${thumb.idFotografiaCodificada}"/>">
				<div style="text-align: right;">
					<button ida="<c:out value='${thumb.idAnuncioCodificado}'/>" idf="<c:out value='${thumb.idFotografiaCodificada}'/>" type="button" class="btnFotoDel btn btn-danger btn-circle" title="<spring:message code="global.eliminar"/>">
						<i class="fa fa-trash"></i>
					</button>
				</div>
           		<a onclick="ampliafoto(<c:out value="${status.index}"/>);" class="thumbnail" id="thumb">
   	         		<img src="<c:out value="${thumb.imagen}"/>" alt="<spring:message code="vela.foto"/> <c:out value="${status.index}"/>" id="miniaturaFoto<c:out value="${status.index}"/>"/>
       	   		</a>
      		</div>
	</c:forEach>
</c:if>
    	</div>
    	
		<form:form action="adminMiAnuncioModFoto.action" method="post" enctype="multipart/form-data" modelAttribute="editaFotosVelaForm" id="editaFotosVelaForm">
			<form:input type="hidden" path="idAnuncioCodificado" id="editaFotosVelaForm_idAnuncioCodificado"/>
			<form:input type="hidden" path="tipoAlta" id="editaFotosVelaForm_tipoAlta"/>
			<div class="separa">
				<div>
					<label for="editaFotosVelaForm_files"><spring:message code="global.fotos"/>:</label> <form:errors path="files" cssClass="errorFormulario" />
				</div>
				<div style="float: left; text-align: left;">
					<form:input path="files" type="file" class="ancho100" id="editaFotosVelaForm_files"  multiple="multiple"/>
				</div>	
				<div class="clearfix"></div>		
				<div class="separa" style="float: right; text-align: right;">
					<button id="btnSubmitFotosVela" type="submit" class="btn btn-primary dcha"><spring:message code="global.enviar"/></button>
				</div>
				<div class="clearfix"></div>
			</div>
		</form:form>

		
	<script>
		$(document).ready(function(){
			
			ampliafoto(0);
			
		    $('.btnFotoDel').each( function() {
				$(this).unbind('click').click( function() {
					if ( confirm("<spring:message code="anuncio.del.confirme.foto"/>") ) {
						$.getJSON( 'miAnuncioDelFoto.action?ida=' + $(this).attr('ida') + '&idb=' + $(this).attr('idf'), function( res ) {
							if ( res.resultado == 'OK' ) {
								$(res.id).remove();
								$('#fotoPrincipal').attr('src', res.foto);
							}
						});
					}
				});	
			});

		});
	</script>