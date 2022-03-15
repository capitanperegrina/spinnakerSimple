<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>


	<!-- Modal content -->
	<div id="ventanaModalSelectorTipoBarco" class="modal fade" tabindex="-2" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<h4 id="tituloVentanaModal" style="margin-top: 3em; text-align: center;" class="modal-title"><spring:message code="tipoBarco.seleccione.tipoBarco"/></h4>
					<p id="tipoBarcoLeyenda" style="margin-top: 3em; text-align: center;"><spring:message code="tipoBarco.leyenda"/></p>
					<p style="margin-top: 2em; margin-bottom: 3em; text-align: center;">
						<button id="selCrucero" type="button" code="C" disabled="disabled" class="btn disabled btn-default btn-sel-tipo-barco" data-dismiss="modal"><spring:message code="tipoBarco.tipo.crucero"/></button>
		      			<button id="seltipoClase" type="button" code="L" disabled="disabled" class="btn disabled btn-default btn-sel-tipo-barco" data-dismiss="modal"><spring:message code="tipoBarco.tipo.velaLigera"/></button>
		      			<button id="selWindsurf" type="button" code="W" disabled="disabled" class="btn disabled btn-default btn-sel-tipo-barco" data-dismiss="modal"><spring:message code="tipoBarco.tipo.windsurf"/></button>
		      			<button id="selKiteboarding" type="button" code="K" disabled="disabled" class="btn disabled btn-default btn-sel-tipo-barco" data-dismiss="modal"><spring:message code="tipoBarco.tipo.kitesurf"/></button>
		      		</p>
				</div>
	    	</div>
	  	</div>
	</div>

	<!-- Modal content -->
	<div id="ventanaModalSelectorClaseVelaLigera" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<h4 id="tituloVentanaModal" style="margin-top: 3em; text-align: center;" class="modal-title"><spring:message code="tipoBarco.seleccione.clase"/></h4>
					<p style="margin-top: 3em; text-align: center;"><spring:message code="tipoBarco.leyenda"/></p>
					<p style="margin-top: 2em; margin-bottom: 3em; text-align: center;">
						<button id="sel29er"        idClase="5"  disabled="disabled" titulo="<spring:message code="tipoBarco.29er"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-29er" data-dismiss="modal"></button>
						<button id="sel420"         idClase="6"  disabled="disabled" titulo="<spring:message code="tipoBarco.420"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-420" data-dismiss="modal"></button>
						<button id="sel470"         idClase="7"  disabled="disabled" titulo="<spring:message code="tipoBarco.470"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-470" data-dismiss="modal"></button>
						<button id="sel49er"        idClase="8"  disabled="disabled" titulo="<spring:message code="tipoBarco.49er"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-49er" data-dismiss="modal"></button>
						<button id="selCadete"      idClase="20" disabled="disabled" titulo="<spring:message code="tipoBarco.cadete"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-cadete" data-dismiss="modal"></button>
						<button id="selCatamaran"   idClase="9"  disabled="disabled" titulo="<spring:message code="tipoBarco.catamaran"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-catamaran" data-dismiss="modal"></button>
						<button id="selEurope"      idClase="10" disabled="disabled" titulo="<spring:message code="tipoBarco.europe"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-europe" data-dismiss="modal"></button>
						<button id="selFinn"        idClase="11" disabled="disabled" titulo="<spring:message code="tipoBarco.finn"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-finn" data-dismiss="modal"></button>
						<button id="selLaser"       idClase="12" disabled="disabled" titulo="<spring:message code="tipoBarco.laser"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-laser" data-dismiss="modal"></button>
						<button id="selLaser47"     idClase="13" disabled="disabled" titulo="<spring:message code="tipoBarco.laser47"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-laser47" data-dismiss="modal"></button>
						<button id="selLaserRadial" idClase="14" disabled="disabled" titulo="<spring:message code="tipoBarco.laserRadial"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-laserRadial" data-dismiss="modal"></button>
						<button id="selOptimist"    idClase="15" disabled="disabled" titulo="<spring:message code="tipoBarco.optimist"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-optimist" data-dismiss="modal"></button>
						<button id="selPatinAVela"  idClase="16" disabled="disabled" titulo="<spring:message code="tipoBarco.patinAVela"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-patinAVela" data-dismiss="modal"></button>
						<button id="selSnipe"       idClase="17" disabled="disabled" titulo="<spring:message code="tipoBarco.snipe"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-snipe" data-dismiss="modal"></button>
						<button id="selSoling"      idClase="18" disabled="disabled" titulo="<spring:message code="tipoBarco.soling"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-soling" data-dismiss="modal"></button>
						<button id="selOtros"       idClase="99" disabled="disabled" titulo="<spring:message code="tipoBarco.otros"/>" type="button" class="btn disabled btn-default btn-sel-clase-vela-ligera btn-otros" data-dismiss="modal"></button>
		      		</p>
		      		<p class="centrado">
			      		<button id="selVolverVelaLigera"  title="<spring:message code="global.volver"/>" type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="global.volver"/></button>
		      		</p>
				</div>
	    	</div>
	  	</div>
	</div>

<script>
	$(document).ready(function(){
		
		$('#selVolverVelaLigera').click(function() {
			openModalTipoBarco();
			configuraTodosBotonesActivosPorTiposBarco();
		});
		
		bugfixBootstrap377();
	});
</script>	