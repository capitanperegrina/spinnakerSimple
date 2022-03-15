<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
	<spring:message code="global.max" var="maxPlaceholder"/>
	<spring:message code="global.min" var="minPlaceholder"/>
    <spring:message code="vela.comprar.busquedaportitulo" var="buscarPlaceholder"/>
    
    <div class="container-fluid">
        <form:form action="comprarVelaBuscarAjax.action" method="post" class="form-inline" enctype="multipart/form-data" modelAttribute="comprarVelaForm" id="velaForm">
		    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		        <div class="separa"><label><spring:message code="vela.tipo"/></label></div>
		        <div>
		            <form:select path="tipoVela" class="form-control ancho100" id="comprarVelaForm_tipoVela">
		                <form:option value="-1"><spring:message code="global.todos"/></label></form:option>
		                <form:options items="${tiposVela}"  />
		            </form:select>
		            <form:hidden path="regInicial" id="regInicial"/>
		            <form:hidden path="tipoOrden" id="tipoOrden"/>
		            <form:hidden path="orden" id="orden"/>
		        </div>
			</div>

			<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 separa" id="div-gratil">
				<div><label><spring:message code="vela.gratil"/></label></div> 
			  	<div class="form-group input-group">
			  		<form:input path="gratilmin" class="form-control ancho100" type="text" id="gratilmin" placeholder="${minPlaceholder}"/><form:errors path="gratilmin" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			  	<div class="form-group input-group">
			  		<form:input path="gratilmax" class="form-control ancho100" type="text" id="gratilmax" placeholder="${maxPlaceholder}"/><form:errors path="gratilmax" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			</div>

			<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 separa" id="div-baluma">
				<div><label><spring:message code="vela.baluma"/></label></div>
			  	<div class="form-group input-group">
			  		<form:input path="balumamin" class="form-control ancho100" type="text" id="balumamin" placeholder="${minPlaceholder}"/><form:errors path="balumamin" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			  	<div class="form-group input-group">
			  		<form:input path="balumamax" class="form-control ancho100" type="text" id="balumamax" placeholder="${maxPlaceholder}"/><form:errors path="balumamax" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			</div>

			<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 separa" id="div-pujamen">
				<div><label><spring:message code="vela.pujamen"/></label></div>
			  	<div class="form-group input-group">
			  		<form:input path="pujamenmin" class="form-control ancho100" type="text" id="pujamenmin" placeholder="${minPlaceholder}"/><form:errors path="pujamenmin" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			  	<div class="form-group input-group">
			  		<form:input path="pujamenmax" class="form-control ancho100" type="text" id="pujamenmax" placeholder="${maxPlaceholder}"/><form:errors path="pujamenmax" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			</div>

			<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 separa" id="div-caidaProa">
				<div><label><spring:message code="vela.caidaProa"/></label></div>
			  	<div class="form-group input-group">
			  		<form:input path="caidaProaMin" class="form-control ancho100" type="text" id="caidaProaMin" placeholder="${minPlaceholder}"/><form:errors path="caidaProaMin" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			  	<div class="form-group input-group">
			  		<form:input path="caidaProaMax" class="form-control ancho100" type="text" id="caidaProaMax" placeholder="${maxPlaceholder}"/><form:errors path="caidaProaMax" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			</div>
			
			<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 separa" id="div-caidaPopa">
				<div><label><spring:message code="vela.caidaPopa"/></label></div>
			  	<div class="form-group input-group">
			  		<form:input path="caidaPopaMin" class="form-control ancho100" type="text" id="caidaPopaMin" placeholder="${minPlaceholder}"/><form:errors path="caidaPopaMin" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			  	<div class="form-group input-group">
			  		<form:input path="caidaPopaMax" class="form-control ancho100" type="text" id="caidaPopaMax" placeholder="${maxPlaceholder}"/><form:errors path="caidaPopaMax" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.longitud.metros" /></span>
			  	</div>
			</div>
			
			<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 separa" id="div-tipoCometa">
				<div><label><spring:message code="vela.tipoCometa"/></label></div>
			  	<div>
					<form:select path="tipoCometa" class="form-control ancho100" id="comprarVelaForm_tipoCometa">
						<form:option value="-1"><spring:message code="global.elija"/></form:option>
						<form:option value="c"><spring:message code="vela.tipoCometa.c"/></form:option>
						<form:option value="b"><spring:message code="vela.tipoCometa.b"/></form:option>
						<form:option value="h"><spring:message code="vela.tipoCometa.h"/></form:option>
						<form:option value="f"><spring:message code="vela.tipoCometa.f"/></form:option>
					</form:select>
				</div>				
			</div>
			
			<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4 separa" id="div-superficie">
				<div><label><spring:message code="vela.superficie"/></label></div>
			  	<div class="form-group input-group">
			  		<form:input path="superficieMin" class="form-control ancho100" type="text" id="superficieMin" placeholder="${minPlaceholder}"/><form:errors path="superficieMin" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.superficie.metrosCuadrados" /></span>
		  		</div>
			  	<div class="form-group input-group">
			  		<form:input path="superficieMax" class="form-control ancho100" type="text" id="superficieMax" placeholder="${maxPlaceholder}"/><form:errors path="superficieMax" cssClass="errorFormulario" />
			  		<span class="input-group-addon"><spring:message code="unidades.superficie.metrosCuadrados" /></span>
		  		</div>
			</div>
			
			<div class="clearfix"></div>

			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">			
		        <div class="derecha ancho100">
                    <a href="comprarVela.action"><spring:message code="global.busquedaAvanzada"/></a>
		        </div>
			</div>
			
			<div class="clearfix"></div>
		</form:form>
	</div>
    
    <div id="resultadosBusqueda"></div>
    
    <div id="principalCargando" class="fondogris">
        <div class="separador fondogris"></div>
        <div class="centrado fondogris">
            <img src="imagenes/gear.gif" alt="<spring:message code='global.cargando' />"/><br/><spring:message code='global.cargando' />
        </div>
        <div class="separador fondogris"></div>
    </div>
    
    <div class="separador fondogris"></div>

<script>
    var noHayMas = false;
    var regInicial = 0;
    var waypoint;
    var wto;

    $(document).ready(function() {
    	
    	configuraFormularioPorTipoVelaPantallaPrincipal();
    	
    	waypoint = new Waypoint({
        	  element: $('#piePagina'),
        	  handler: function(direction) {
                  waypoint.disable();
                  $('#principalCargando').show();
                  ejecutarBusqueda();
        	  },
        	  offset: '100%'
        });

        $('#comprarVelaForm_tipoVela').change(function() {
			configuraFormularioPorTipoVelaPantallaPrincipal();
			buscar();
        });

        $('#comprarVelaForm_tipoCometa').change(function() {
			buscar();
        });
        
        $('#gratilmin, #gratilmax, #balumamin, #balumamax, #pujamenmin, #pujamenmax, #caidaProaMin, #caidaProaMax, #caidaPopaMin, #caidaPopaMax, #superficieMin, #superficieMax').keypress(function() {
			clearTimeout(wto);
        	wto = setTimeout(function() {
        		buscar();	
        	}, 1000);
        });
        
		buscar();

        window.scrollTo(0, 0);
    });

    function buscar() {
        noHayMas = false;
        regInicial = 0;
        $('#resultadosBusqueda').html('');
        ejecutarBusqueda();
        return false;    	
    }

    function ejecutarBusqueda() {
    	if (!noHayMas) {
            $('#principalCargando').show();
            $('#regInicial').val(regInicial);
            $.ajax({
                url: $('#velaForm').attr('action'),
                dataType: 'html',
                type: 'post',
                data: $('#velaForm').serialize(),
                statusCode: {
                    302: function() {
                        $('#principalCargando').hide();
                        window.location.href = "loginForm.action";
                    }
                },
                success: function(data) {
                    $('#principalCargando').hide();
                    $('#resultadosBusqueda').append(data);
                    regInicial = regInicial + 12;
                    waypoint.enable();
                },
                error: function() {
                    $('#principalCargando').hide();
                    waypoint.enable();
                }
            });    	
        } else {
            $('#principalCargando').hide();
            waypoint.enable();
        }
    }

</script>