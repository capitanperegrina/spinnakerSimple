<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
	<spring:message code="global.max" var="maxPlaceholder"/>
	<spring:message code="global.min" var="minPlaceholder"/>
	<spring:message code="vela.comprar.instruccionesBusqueda" var="velaComprarInstruccionesBusquedaPlaceholder"/>

	<jsp:include page="modalSelectorBarco.jsp" />
	
    <div class="container-fluid">

    	<div class="col-md-12 col-sm-12 text-center">
      		<h2><spring:message code="global.COMPRARVELAS"/></h2>
    	</div>

		<div class="col-md-12" style="margin-bottom:15px;">
		
			<form:form action="comprarVelaExec.action" method="get" class="form-inline" enctype="multipart/form-data" modelAttribute="comprarVelaForm" id="velaForm">
				<sec:csrfInput />
				<form:hidden path="tipoClase" id="comprarVelaForm_tipoClase"/>
				<form:hidden path="tipoBarco" id="comprarVelaForm_tipoBarco"/>

				<div class="separa">
					<div class="col-xs-12 col-md-6 col-sm-6">
						<div><label><spring:message code="tipoBarco.seleccione.tipoBarco.tipo"/></label></div>
						<div><input id="nombreClaseBarco" name="nombreClaseBarco" type="text" class="form-control ancho66" disabled="disabled" style="float: left;" value="${tipoBarcoDescripcion}"/> <a href="#" id="cambiarTipoBarco" style="margin-top: 3px; margin-left: 10px; float: left;"><spring:message code="global.seleccionar"/></a></div>
					</div>
					
					<div class="col-xs-12 col-md-6 col-sm-6">
						<div><label><spring:message code="vela.tipo"/></label></div>
						<div>
							<form:select path="tipoVela" class="form-control" id="comprarVelaForm_tipoVela" style="width:75%">
								<form:options items="${tiposVela}"  />
							</form:select>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				
				
				<div class="separa">
					<div class="col-xs-12 col-md-12 col-sm-12">
						<label><spring:message code="vela.comprar.busquedaportitulo"/></label>
						<form:input path="titulo" class="form-control ancho100" type="text" id="titulo" placeholder="${comprarVelaForm.titulo}" style="margin-top:5px;"/>
						<p>** <spring:message code="vela.comprar.instruccionesBusqueda"/></p>
       					<p>** <spring:message code="vela.comprar.siNoIntroducePalabra"/></p>
					</div>				
				</div>
				<div class="clearfix"></div>
				
				<div class="separa" id="medidasVelas">
					<div class="col-md-2 col-sm-2" id="div-gratil">
						<div><label><spring:message code="vela.gratil"/></label></div> 
					  	<div class="form-group input-group"><form:input path="gratilmin" class="form-control ancho100" type="text" id="gratilmin" placeholder="${minPlaceholder}"/><form:errors path="gratilmin" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					  	<div class="form-group input-group"><form:input path="gratilmax" class="form-control ancho100" type="text" id="gratilmax" placeholder="${maxPlaceholder}"/><form:errors path="gratilmax" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					</div>
	
					<div class="col-md-2 col-sm-2" id="div-baluma">
						<div><label><spring:message code="vela.baluma"/></label></div>
					  	<div class="form-group input-group"><form:input path="balumamin" class="form-control ancho100" type="text" id="balumamin" placeholder="${minPlaceholder}"/><form:errors path="balumamin" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					  	<div class="form-group input-group"><form:input path="balumamax" class="form-control ancho100" type="text" id="balumamax" placeholder="${maxPlaceholder}"/><form:errors path="balumamax" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					</div>
	
					<div class="col-md-2 col-sm-2" id="div-pujamen">
						<div><label><spring:message code="vela.pujamen"/></label></div>
					  	<div class="form-group input-group"><form:input path="pujamenmin" class="form-control ancho100" type="text" id="gratilmin" placeholder="${minPlaceholder}"/><form:errors path="pujamenmin" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					  	<div class="form-group input-group"><form:input path="pujamenmax" class="form-control ancho100" type="text" id="gratilmax" placeholder="${maxPlaceholder}"/><form:errors path="pujamenmax" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					</div>
					
					<div class="col-md-2 col-sm-2" id="div-caidaProa">
						<div><label><spring:message code="vela.caidaProa"/></label></div>
					  	<div class="form-group input-group"><form:input path="caidaProaMin" class="form-control ancho100" type="text" id="caidaProaMin" placeholder="${minPlaceholder}"/><form:errors path="caidaProaMin" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					  	<div class="form-group input-group"><form:input path="caidaProaMax" class="form-control ancho100" type="text" id="caidaProaMax" placeholder="${maxPlaceholder}"/><form:errors path="caidaProaMax" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					</div>
					
					<div class="col-md-2 col-sm-2" id="div-caidaPopa">
						<div><label><spring:message code="vela.caidaPopa"/></label></div>
					  	<div class="form-group input-group"><form:input path="caidaPopaMin" class="form-control ancho100" type="text" id="caidaPopaMin" placeholder="${minPlaceholder}"/><form:errors path="caidaPopaMin" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					  	<div class="form-group input-group"><form:input path="caidaPopaMax" class="form-control ancho100" type="text" id="caidaPopaMax" placeholder="${maxPlaceholder}"/><form:errors path="caidaPopaMax" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span></div>
					</div>
					
					<div class="col-md-2 col-sm-2" id="div-superficie">
						<div><label><spring:message code="vela.superficie"/></label></div>
					  	<div class="form-group input-group"><form:input path="superficieMin" class="form-control ancho100" type="text" id="superficieMin" placeholder="${minPlaceholder}"/><form:errors path="superficieMin" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.superficie.metrosCuadrados" /></span></div>
					  	<div class="form-group input-group"><form:input path="superficieMax" class="form-control ancho100" type="text" id="superficieMax" placeholder="${maxPlaceholder}"/><form:errors path="superficieMax" cssClass="errorFormulario" /><span class="input-group-addon"><spring:message code="unidades.superficie.metrosCuadrados" /></span></div>
					</div>
					
					<div class="col-md-2 col-sm-2">
					  	<div id="div-tipoCometa">
							<div><label><spring:message code="vela.tipoCometa"/></label></div>
						  	<div>
								<form:select path="tipoCometa" class="form-control" id="comprarVelaForm_tipoCometa" style="width: 200px !important">
									<form:option value="-1"><spring:message code="global.elija"/></form:option>
									<form:option value="c"><spring:message code="vela.tipoCometa.c"/></form:option>
									<form:option value="b"><spring:message code="vela.tipoCometa.b"/></form:option>
									<form:option value="h"><spring:message code="vela.tipoCometa.h"/></form:option>
									<form:option value="f"><spring:message code="vela.tipoCometa.f"/></form:option>
								</form:select>
							</div>
					  	</div>
					</div>


					<div class="clearfix"></div>
					<div>
						<p style="float: left;" class="separa">** <spring:message code="vela.comprar.siNoIntroduceMedida"/></p>
					</div>	
				</div>
				<div class="clearfix"></div>
				
				<div class="separa">
					<div id="divOrdenacion" class="col-md-6 col-sm-6">
						<label><spring:message code="label.ordenacionResultados"/></label>
						<div class="clearfix"></div>
						<div id="divPujamen" class="col-md-5 col-sm-5">
							<label><spring:message code="label.ordenarPor"/></label>
							<form:select path="orden" class="form-control ancho100" id="comprarVelaForm_idTipoVela" style="width:75%">
								<form:option value="fec_alta"><spring:message code="label.ordenacion.fec_alta"/></form:option>
								<form:option value="gratil"><spring:message code="label.ordenacion.gratil"/></form:option>
								<form:option value="baluma"><spring:message code="label.ordenacion.baluma"/></form:option>
								<form:option value="pujamen"><spring:message code="label.ordenacion.pujamen"/></form:option>
								<form:option value="precio"><spring:message code="label.ordenacion.precio"/></form:option>
							</form:select>
						</div>
						<div id="divPujamen" class="col-md-5 col-sm-5">
							<label><spring:message code="label.tipoOrdenacion"/></label>
							<form:select path="tipoOrden" class="form-control ancho100" id="comprarVelaForm_idTipoVela" style="width:75%">
								<form:option value="DESC"><spring:message code="label.tipoOrdenacion.DESC"/></form:option>
								<form:option value="ASC"><spring:message code="label.tipoOrdenacion.ASC"/></form:option>
							</form:select>
						</div>
					</div>
					<div class="clearfix"></div>				
				</div>

				<div class="col-md-12 col-sm-12 separa" style="text-align: right;">
				    <button id="velaForm_btnBuscar" class="btn btn-primary">&gt;&gt; <spring:message code="global.BUSCAR"/></button>
			  	</div>
			</form:form>
		</div>
		<div class="clearfix"></div>
		
		<div id="divResultadosBusqueda">
<c:if test="${busquedaRealizada eq 'S'}">		
	<c:if test="${empty resultadosBusqueda}">
							<div class="alert alert-danger">
								<spring:message code="global.nadaEncontrado"/>								
							</div>	
	</c:if>
</c:if>
<c:if test="${not empty resultadosBusqueda}">
		<div class="row separa">
	<c:forEach items="${resultadosBusqueda}" var="anuncio" varStatus="status">
			<div class="col-md-6 col-md-offset-3 separa">
	       		<div class="crop">
          			<p>
          				<img class="img-responsive lnkAnuncio punteroEnlace" id="lnkAnuncio_<c:out value="${anuncio.anuncio.idAnuncioCodificado}"/>" src="${anuncio.foto1}" alt="<c:out value="${anuncio.anuncio.tituloAnuncio}"/>"/>
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
				
				<div class="anuncioExtracto">
	        		<c:out value="${anuncio.anuncio.extractoAnuncio}"/>
        		</div>
				<div class="clearfix"></div>
				
      		</div>
	</c:forEach>
		</div>
</c:if>
		
		</div>

	</div> <!-- /container-fluid -->

	<script>
		$(document).ready(function(){
			
			$('#_valorTemporal').val($('#comprarVelaForm_tipoVela').val());
			
			$('#comprarVelaForm_tipoVela').change(function() {
				var idTipoVela = $(this).find('option:selected').val();
				configuraFormularioPorTipoVela("#comprarVelaForm_", idTipoVela);
			});			
			
			$('#velaForm_btnBuscar').unbind('click').click( function() {
				$('#velaForm').submit();
			    return false;
			});
			
			$('.lnkAnuncio').each( function() {
	    		$(this).unbind('click').click( function() {
					document.location='verVela.action?ida=' + $(this).attr('id').substring(11);
				});
			});
			
			$('#cambiarTipoBarco').click(function() {
	        	$('#comprarVelaForm_tipoBarco').val('');
				$('#nombreClaseBarco').val('');
				openModalTipoBarco();
				configuraBotonesActivosPorTiposClase("#comprarVelaForm_");
			});
			
			$('#selCrucero').click(function() {
				$('#comprarVelaForm_tipoClase').val('C');
				$('#comprarVelaForm_tipoBarco').val("1");
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.crucero"/>");
				buscaTiposDeVela("#comprarVelaForm_", "1");
			});

			$('#serRC').click(function() {
				$('#comprarVelaForm_tipoClase').val('R');
				$('#comprarVelaForm_tipoBarco').val("2");
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.velaRc"/>");
				buscaTiposDeVela("#comprarVelaForm_", "2");
			});

			$('#selWindsurf').click(function() {
				$('#comprarVelaForm_tipoClase').val('W');
				$('#comprarVelaForm_tipoBarco').val("3");
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.windsurf"/>");
				buscaTiposDeVela("#comprarVelaForm_", "3");
			});

			$('#selKiteboarding').click(function() {
				$('#comprarVelaForm_tipoClase').val('K');
				$('#comprarVelaForm_tipoBarco').val("4");
				$('#nombreClaseBarco').val("<spring:message code="tipoBarco.kiteboarding"/>");
				buscaTiposDeVela("#comprarVelaForm_", "4");
				$('#comprarVelaForm_tipoCometa').val(-1);
			});

			$('#seltipoClase').click(function() {
				$('#comprarVelaForm_tipoClase').val('L');
				openModalSelectorClaseVelaLigera();
				configuraBotonesActivosPorTiposBarco("#comprarVelaForm_");
			});

			$('.btn-sel-clase-vela-ligera').each( function() {
				var boton = $(this);
				boton.unbind('click').bind('click', function() {
					$('#comprarVelaForm_tipoBarco').val( boton.attr('idClase') );
					$('#nombreClaseBarco').val( boton.attr('titulo') );
					buscaTiposDeVela( "#comprarVelaForm_", boton.attr('idClase') );
		        });
			});
			
			$('#comprarVelaForm_tipoVela').change();
			
			buscaTiposDeVela("#comprarVelaForm_", $("#comprarVelaForm_tipoBarco").val());
			
<c:if test="${busquedaRealizada ne 'S'}">
			configuraBotonesActivosPorTiposClase("#comprarVelaForm_");
			openModalTipoBarco();
</c:if>			
		});
	</script>
	
	<nav aria-label="Page navigation" class="col-md-12 col-sm-12 text-center"></nav>	