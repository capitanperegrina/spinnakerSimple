<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

            <div class="row">
                <div class="col-lg-12">
					<div class="float-izda">
						<h1><spring:message code="global.parametrizacion"/></h1>
					</div>                
					<div class="float-dcha">
						<h1>
		        			<button id="btnVolver" type="button" class="btn btn-primary">
								<spring:message code="global.volver"/>
							</button>
						</h1>						
					</div>
					<div class="clearfix"></div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable-parametros">
                                <thead>
                                    <tr>                                    
                                        <th class="col-xs-6 col-md-4"><spring:message code="global.parametro"/></th>
                                        <th class="col-xs-12 col-md-8"><spring:message code="global.valor"/></th>
                                    </tr>
                                </thead>
<c:forEach items="${listaParametros}" var="parametro" varStatus="status">
	<c:if test="${status.index % 2 == 0}">
								<tr class="odd">
	</c:if>	
	<c:if test="${status.index % 2 == 1}">
								<tr class="even">
	</c:if>
									<td><label for="parametrizacionForm_${parametro.idParamWeb}">${parametro.label}:</label> <span class="errorFormulario" id="errorFormulario_${parametro.idParamWeb}"></span></td>
									<td>
	<c:if test="${parametro.tipoJs == 'S'}">
										<input id="parametrizacionForm_${parametro.idParamWeb}" name="${parametro.idParamWeb}" type="text" class="form-control ancho100" value="${parametro.valor}"/>
	</c:if>									
	<c:if test="${parametro.tipoJs == 'N'}">
										<input id="parametrizacionForm_${parametro.idParamWeb}" name="${parametro.idParamWeb}" type="text" class="form-control ancho100" value="${parametro.valor}"/>
	</c:if>									
	<c:if test="${parametro.tipoJs == 'B'}">
										<select id="parametrizacionForm_${parametro.idParamWeb}" name="${parametro.idParamWeb}" style="width:75%" class="form-control">
											<option value="S" <c:if test="${parametro.valor == 'S'}">selected="selected"</c:if>><spring:message code="global.si"/></option>
											<option value="N" <c:if test="${parametro.valor == 'N'}">selected="selected"</c:if>><spring:message code="global.no"/></option>
										</select>
	</c:if>
										<input id="original_parametrizacionForm_${parametro.idParamWeb}" type="hidden" value="${parametro.valor}"/>
									</td>
								</tr>
</c:forEach>
							</table>
						</div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            
            <div class="row">
                <div class="col-lg-12">
					<div class="float-izda">
						<h1>Log</h1>
					</div>                
					<div class="clearfix"></div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">

							<div class="col-md-6 col-sm-6">
								<div class="separa">
								    <label for="logger_package">Logger Java Package</label>
			     				</div>
								<div>
									<input type="text" name="logger_package" class="form-control dcha" id="logger_package" placeholder="Java Package"/>
						     	</div>
								<div class="clearfix"></div>
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="separa">
								    <label for="logger_level">Logger Level</label>
			     				</div>
								<div>
									<select id="logger_level" name="logger_level" style="width:75%" class="form-control">
										<option value="">Level</option>
										<option value="OFF">OFF</option>
										<option value="FATAL">FATAL</option>
										<option value="ERROR">ERROR</option>
										<option value="WARN">WARN</option>
										<option value="INFO">INFO</option>
										<option value="DEBUG">DEBUG</option>
										<option value="TRACE">TRACE</option>
										<option value="ALL">ALL</option>
									</select>
			     				</div>
				   				<div class="clearfix"></div>					
							</div>


			   				<div class="float-dcha">
								<h1>
				        			<button id="btnLoggerSave" type="button" class="btn btn-primary">
										<spring:message code="global.enviar"/>
									</button>
								</h1>						
							</div>
							<div class="clearfix"></div>


						</div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

    <script>
	    $(document).ready(function() {

	    	creaVentanaModal("<spring:message code="global.mensaje"/>","<spring:message code="param.notocar"/>",4000);
	    	
	        $('#btnVolver').unbind('click').bind('click', function() {
	        	history.back();
	        });

	        $('#dataTable-parametros').DataTable({
	            responsive: true,
	            stateSave: true,
	            paging: false
	        });
	        
        	$('#dataTable-parametros .form-control').each(function() {
        		$(this).unbind('change').change(function() {
        			var id = $(this).attr('id');
        			var valorOriginal = $('#original_' + id ).val();
        			var valorNuevo = $(this).val();
        			if ( valorOriginal != valorNuevo ) {
            			var url= 'adminParametrizacionSave.action?c=' + id + '&v=' + $(this).val();
    					$.getJSON( url, function( res ) {
    						if ( res.resultado == 'OK' ) {
    							$('#original_' + id ).val( valorNuevo );
    							creaVentanaModal("<spring:message code="global.mensaje"/>","<spring:message code="parametros.guardar.ok"/>",3000);
    						} else {
    							creaVentanaModal("<spring:message code="global.error"/>",res.resultado);
    							$('#' + id ).val( valorOriginal );
    						}
    					});        				
        			}
        		});        		
        	});
        	
        	$('#btnLoggerSave').unbind('click').click( function() {
        		var paquete = $('#logger_package').val();
        		var nivel = $('#logger_level').val();
        		if ( paquete != '' && nivel != '' ) {
        			var url= 'adminLogLevel.action?p=' + paquete + '&l=' + nivel;
					$.getJSON( url, function( res ) {
						if ( res.resultado == 'OK' ) {
							creaVentanaModal("<spring:message code="global.mensaje"/>",res.mensaje,3000);
						} else {
							creaVentanaModal("<spring:message code="global.error"/>",res.mensaje);
						}
					});
        		}
        	});
	    });
    </script>