<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

            <div class="row">
                <div class="col-lg-12">
					<div class="float-izda">
						<h1><spring:message code="global.anuncios"/></h1>
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
                    	<div class="panel-heading"><spring:message code="global.filtros"/></div>
                        <!-- /.panel-heading -->

						<div class="panel-body">
							<form:form action="adminAnunciosAjax.action" method="get" class="form-inline" modelAttribute="anuncioForm" id="anuncioForm">
								<div class="form-group">
								    <label for="anuncioForm_visible"><spring:message code="global.estado"/></label>
									<form:select path="visible" class="form-control" id="anuncioForm_visible">
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
                        	<div id="adminAnunciosAjax"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>

    <script>
	    $(document).ready(function() {
	    	$('#anuncioForm_visible').unbind('click').click( function() {
	    		var v = $('#anuncioForm_visible').val();
	    		$('#adminAnunciosAjax').load('adminAnunciosAjax.action?v=' + v);
	    	});
	    	
	        $('#btnVolver').unbind('click').bind('click', function() {
	        	history.back();
	        });
	        
	        $('#anuncioForm_visible').click();
	    });
    </script>