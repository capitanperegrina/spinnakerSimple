<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

            <div class="row">
                <div class="col-lg-12">

					<div class="float-izda">
						<h1><c:out value="${usuario.nombrePropietario}"/></h1>
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
						<div class="panel-heading"><spring:message code="admin.usuarios.datos"/></div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        
                        
                        <div class="row col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<div class="separa">
								<label><spring:message code="global.nombre"/></label>
		      				</div>
							<div>
								${usuario.nombre}
		      				</div>
		 					<div class="clearfix"></div>
		 					
		 					
							<div class="separa">
								<label><spring:message code="global.apellidos"/></label>
		      				</div>
							<div>
								${usuario.apellidos}
		      				</div>
		 					<div class="clearfix"></div>
		 					
							<div class="separa">
								<label><spring:message code="global.divisaPreferida"/></label>
							</div>
							<div>
								${usuario.divisa}
							</div>
							<div class="clearfix"></div>
                        </div>
						<div class="row col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<div class="separa">
		      					<label><spring:message code="global.email"/></label>
		    				</div>
							<div>
								${usuario.mail}
		    				</div>
		 					<div class="clearfix"></div>

							<div class="separa">
		      					<label><spring:message code="global.movil"/></label>
		    				</div>
							<div>
								${usuario.movil}
		    				</div>
		    				<div class="clearfix"></div>
		
		    				
							<div class="separa">
								<label><spring:message code="global.dir"/></label>
		      				</div>
							<div>
								${usuario.direccionCompleta}
							</div>
							<div class="clearfix"></div>
                        </div>

                        </div>						
                    </div>
                    
                    <div class="panel panel-default">
						<div class="panel-heading"><spring:message code="admin.usuarios.anuncios"/></div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<div id="adminAnunciosAjax"></div>
                        </div>
                        <!-- /.panel-body -->
                        						
                    </div>                    
				</div>
			</div>
			
			
			
			
    <script>
	    $(document).ready(function() {
	    	$("#adminAnunciosAjax").load("adminAnunciosAjax.action?idu=<c:out value="${usuario.idUsuarioCodificado}"/>");
	    	
	        $('#btnVolver').unbind('click').bind('click', function() {
	        	history.back();
	        });
	    });
    </script>			