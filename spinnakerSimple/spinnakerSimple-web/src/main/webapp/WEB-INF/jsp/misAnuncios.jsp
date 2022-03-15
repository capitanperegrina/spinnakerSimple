<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

    <div class="container-fluid">

    	<div class="col-md-12 col-sm-12 text-center fondogris">
      		<h2><spring:message code="global.misAnuncios"/></h2>
    	</div>

		<div class="col-md-12 col-sm-12 text-center fondogris">
			<div class="col-md-12 col-sm-12 separa">
				<form:form action="misAnuncios.action" method="post" class="form-inline" enctype="multipart/form-data" modelAttribute="filtroMisAnunciosForm" id="filtroMisAnunciosForm">
					<sec:csrfInput />
					<div>
						<label for="filtroMisAnunciosForm_tipoVela"><spring:message code="vela.tipo" /></label>
						<form:select path="tipoVela" class="form-control" id="filtroMisAnunciosForm_idTipoVela" style="width:33%">
							<form:option value="-1"><spring:message code="global.todos" /></form:option>
							<form:options items="${tiposVela}" />
						</form:select>
						<span style="margin-left: 20px;"><form:checkbox path="tipoAnuncio" id="filtroMisAnunciosForm_estado_0" value="0"/> <label for="filtroMisAnunciosForm_estado_0"><spring:message code="anuncio.estado.0"/></label></span>
						<span style="margin-left: 10px;"><form:checkbox path="tipoAnuncio" id="filtroMisAnunciosForm_estado_1" value="1"/> <label for="filtroMisAnunciosForm_estado_1"><spring:message code="anuncio.estado.1"/></label></span>
						<span style="margin-left: 10px;"><form:checkbox path="tipoAnuncio" id="filtroMisAnunciosForm_estado_2" value="2"/> <label for="filtroMisAnunciosForm_estado_2"><spring:message code="anuncio.estado.2"/></label></span> 
						<span style="margin-left: 20px;">
							<button type="submit" class="btn btn-primary">
								<spring:message code="global.BUSCAR" />
							</button>
						</span>
					</div>
					<div class="clearfix"></div>					
				</form:form>
			</div>
		</div>
		
		<div class="row separa">
		
	<c:if test="${empty resultadosBusqueda}">
    	<div class="col-md-12 col-sm-12 text-center fondogris">
    		<div style="margin-top: 10%; margin-bottom: 10%;">
    			<spring:message code="global.comprarVelas.nadaEncontrado" />
    		</div>
    	</div>
	</c:if>		
	<c:if test="${!empty resultadosBusqueda}">

			<div class="col-md-12 col-sm-12 fondogris separa">
<c:forEach items="${resultadosBusqueda}" var="anuncio" varStatus="status">
				<div class="col-md-4 col-sm-4 separa">
					<div style="float: left; text-align: left; margin-top: 2px; color: #c7630e; font-weight: bold; font-size: 1.1em;">
						<span id="textoEstadoAnuncio_<c:out value="${anuncio.anuncio.idAnuncioCodificado}"/>" class="estadoAnuncio<c:out value="${anuncio.anuncio.visible}"/>"><c:out value="${anuncio.anuncio.estadoAnuncioDescripcion}"/></span>
					</div>
					<div class="clearfix"></div>
					<div style="float: right; text-align: right; margin-bottom: 4px;">
	<c:if test="${anuncio.usuario.admin == '1'}">
						<c:out value="${anuncio.anuncio.botonesAdministrador}" escapeXml="false"/>
	</c:if>
	<c:if test="${anuncio.usuario.admin == '0'}">
						<c:out value="${anuncio.anuncio.botones}" escapeXml="false"/>
	</c:if>					
					</div>
					<div class="clearfix"></div>

	       			<div class="crop">
          				<p>
          					<a href="verVela.action?ida=<c:out value="${anuncio.anuncio.idAnuncioCodificado}"/>">
          						<img src="${anuncio.foto1}" alt="<c:out value="${anuncio.anuncio.tituloAnuncio}"/>" class="img-responsive" />
	          				</a>	
    	      			</p>
        			</div>
        			<h3>
        				<a href="verVela.action?ida=<c:out value="${anuncio.anuncio.idAnuncioCodificado}"/>">
        					<c:out value="${anuncio.anuncio.tituloAnuncio}"/>
	        			</a>
    	    		</h3>
		<c:if test="${(anuncio.anuncio.tipoBarco < 5 || anuncio.anuncio.tipoBarco > 98 )}"> 
			<c:if test="${anuncio.tipoVela.gratil eq '1'}">
				<c:if test="${!empty anuncio.anuncio.gratil}">
					<div class="miniMedidasLabel"><spring:message code="vela.gratil"/></div>
					<div class="miniMedidas"><c:out value="${anuncio.anuncio.gratil}"/> <spring:message code="unidades.longitud.metros"/></div>
				</c:if>
			</c:if>
			<c:if test="${anuncio.tipoVela.baluma eq '1'}">
				<c:if test="${!empty anuncio.anuncio.baluma}">
				    <div class="miniMedidasLabel"><spring:message code="vela.baluma"/></div>
				    <div class="miniMedidas"><c:out value="${anuncio.anuncio.baluma}"/> <spring:message code="unidades.longitud.metros"/></div>
				</c:if>
			</c:if>
			<c:if test="${anuncio.tipoVela.pujamen eq '1'}">
				<c:if test="${!empty anuncio.anuncio.pujamen}">
				    <div class="miniMedidasLabel"><spring:message code="vela.pujamen"/></div>
				    <div class="miniMedidas"><c:out value="${anuncio.anuncio.pujamen}"/> <spring:message code="unidades.longitud.metros"/></div>
				</c:if>
			</c:if>
			<c:if test="${anuncio.tipoVela.caidaProa eq '1'}">
				<c:if test="${!empty anuncio.anuncio.caidaProa}">
				    <div class="miniMedidasLabel"><spring:message code="vela.caidaProa"/></div>
				    <div class="miniMedidas"><c:out value="${anuncio.anuncio.caidaProa}"/> <spring:message code="unidades.longitud.metros"/></div>
				</c:if>
			</c:if>
			<c:if test="${anuncio.tipoVela.caidaPopa eq '1'}">
				<c:if test="${!empty anuncio.anuncio.caidaPopa}">
				    <div class="miniMedidasLabel"><spring:message code="vela.caidaPopa"/></div>
				    <div class="miniMedidas"><c:out value="${anuncio.anuncio.caidaPopa}"/> <spring:message code="unidades.longitud.metros"/></div>
				</c:if>
			</c:if>
			<c:if test="${anuncio.tipoVela.tipoCometa eq '1'}">
				<c:if test="${!empty anuncio.anuncio.tipoCometaDescripcion}">
				    <div class="miniMedidasLabel"><spring:message code="vela.tipoCometa"/></div>
				    <div class="miniMedidas"><c:out value="${anuncio.anuncio.tipoCometaDescripcion}"/></div>
				</c:if>
			</c:if>
			<c:if test="${anuncio.tipoVela.superficie eq '1'}">
				<c:if test="${!empty anuncio.anuncio.superficie}">
				    <div class="miniMedidasLabel"><spring:message code="vela.superficie"/></div>
				    <div class="miniMedidas"><c:out value="${anuncio.anuncio.superficie}"/> <spring:message code="unidades.superficie.metrosCuadrados"/></div>
				</c:if>
			</c:if>
		</c:if>
					<div class="clearfix"></div>
					<div>
		        		<c:out value="${anuncio.anuncio.extractoAnuncio}"/>
	        		</div>

					<div class="anuncioEstadisticas">
						<spam class="miniMedidasLabel"><spring:message code="anuncio.veces.listado"/></spam>
						<spam class="miniMedidas"><c:out value="${anuncio.anuncio.listado}"/></spam>
						<spam class="miniMedidasLabel"><spring:message code="anuncio.veces.visto"/></spam>
						<spam class="miniMedidas"><c:out value="${anuncio.anuncio.visto}"/></spam>
						<spam class="miniMedidasLabel"><spring:message code="anuncio.veces.comentado"/></spam>
						<spam class="miniMedidas"><c:out value="${anuncio.anuncio.comentado}"/></spam>
					</div>
					<div class="clearfix"></div> 
        		
    	  		</div>
				<c:if test="${status.index % 3 == 2}">
					<div class="clearfix"></div><!--  cada 4 -->
				</c:if>
</c:forEach>
			</div>
	</c:if>	
		</div>
    
    </div>

<script type="text/javascript">
	$(document).ready(function(){

<c:if test="${usuario.admin == '1'}">
        $('.btnValidaAnuncio').each( function() {
        	$(this).unbind('click').bind('click', function() {
        		var boton = $(this);
        		if ( $(this).hasClass('btn-success') ) {
        			if ( confirm("<spring:message code="anuncio.desactiva.confirme"/>") ) {
						$.getJSON( 'adminCambiaEstadoAnuncio.action?w=0&s=' + $(this).attr('cod'), function( res ) {
							if ( res.resultado == 'OK' ) {
								boton.removeClass('btn-success').addClass('btn-default');
								$('#textoEstadoAnuncio_'+boton.attr('cod')).html("<spring:message code="anuncio.estado.0"/>").removeClass().addClass('estadoAnuncio0');
							} else {
								creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
							}
						});	        				
        			}
        		} else if ( $(this).hasClass('btn-default') ) { // Activar
					$.getJSON('adminCambiaEstadoAnuncio.action?w=1&s=' + $(this).attr('cod'), function( res ) {
						if ( res.resultado == 'OK' ) {
							boton.removeClass('btn-default').addClass('btn-success');
							$('#textoEstadoAnuncio_'+boton.attr('cod')).html("<spring:message code="anuncio.estado.1"/>").removeClass().addClass('estadoAnuncio1');
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
								$('#textoEstadoAnuncio_'+boton.attr('cod')).html("<spring:message code="anuncio.estado.1"/>").removeClass().addClass('estadoAnuncio1');;
							} else {
								creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
							}
						});
        			}
        		}
        	});
    	});
</c:if>

		$('.btnRenuevaAnuncio').each( function() {
			var boton = $(this);
			$(this).unbind('click').bind('click', function() {
				$.getJSON( 'adminRenuevaAnuncio.action?s=' + $(this).attr('cod'), function( res ) {
					if ( res.resultado == 'OK' ) {
						creaVentanaModal("<spring:message code="global.mensaje"/>",res.nuevaCaducidad);
					} else {
						creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
					}					
				});
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
								$('#textoEstadoAnuncio_'+boton.attr('cod')).html("<spring:message code="anuncio.estado.2"/>").removeClass().addClass('estadoAnuncio2');
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
    			document.location='verVela.action?ida=' + $(this).attr('cod');
    		});
    	});
    	
    	$('.btnEditaAnuncio').each( function() {
    		$(this).unbind('click').bind('click', function() {
    			document.location='miAnuncioMod.action?ida=' + $(this).attr('cod');
    		});
    	});    
	    
	});
</script>