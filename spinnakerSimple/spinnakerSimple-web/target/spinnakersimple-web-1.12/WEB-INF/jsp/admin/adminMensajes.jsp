<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

            <div class="row">
                <div class="col-lg-12">
					<div class="float-izda">
						<h1><spring:message code="global.mensajes"/></h1>
					</div>                
					<div class="float-dcha">
						<h1 class="page-header">
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
                    	<div class="panel-heading"><spring:message code="global.filtros"/></div>
                        <!-- /.panel-heading -->

						<div class="panel-body">
							<form:form action="adminMensajesAjax.action" method="get" class="form-inline" modelAttribute="consultarVelaForm" id="consultarVelaForm">
								<div class="form-group">
								    <label for="consultarVelaForm_revisado"><spring:message code="global.estado"/></label>
									<form:select path="revisado" class="form-control" id="consultarVelaForm_revisado">
										<form:options items="${opcionesVisibilidad}"  />
									</form:select>
								</div>
							</form:form>
						</div>
					</div>

                    <div class="panel panel-default">
						<div class="panel-heading"><spring:message code="globla.resultados"/></div>
						<!-- /.panel-heading -->
                        <div class="panel-body">
                        	<div id="adminMensajesAjax"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            


    <script>
	    $(document).ready(function() {
	    	$('#consultarVelaForm_revisado').unbind('change').change( function() {
	    		cargaMensajes( $('#consultarVelaForm_revisado').val() );
	    	});
	    	
	        $('#btnVolver').unbind('click').bind('click', function() {
	        	history.back();
	        });
	        
	        cargaMensajes( $('#consultarVelaForm_revisado').val() );
	    });
	    
	    function cargaMensajes(r) {
	    	$('#adminMensajesAjax').load('adminMensajesAjax.action?r=' + r);
	    }
    </script>