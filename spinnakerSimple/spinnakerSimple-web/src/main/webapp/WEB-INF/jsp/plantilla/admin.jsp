<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="Spinnaker Simple v.<c:out value="${appVersion}"/>">
	<meta name="robots" content="index, follow">
	<meta name="revisit-after" content="3 month">    

    <title><spring:message code="app.titulo"/></title>

    <link href="admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="admin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
    <link href="admin/dist/css/sb-admin-2.css" rel="stylesheet">
    <link href="admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="admin/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
    <link href="admin/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
    <link href="css/estilosAdmin.css" rel="stylesheet" type="text/css">
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery -->
    <script src="admin/vendor/jquery/jquery.min.js"></script>
    <script src="admin/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="admin/vendor/metisMenu/metisMenu.min.js"></script>
    <script src="admin/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="admin/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="admin/vendor/datatables-responsive/dataTables.responsive.js"></script>    
    <script src="admin/dist/js/sb-admin-2.js"></script>
    <script src="dist/js/spinnakerSimple.js"></script>
  </head>

  <body>

    <div id="wrapper">
<tiles:insertAttribute name="menu" />    
        <div id="page-wrapper">
<tiles:insertAttribute name="contenido" />
		</div>
    </div>
    
	<!-- Modal -->
	<div id="ventanaModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 id="tituloVentanaModal" class="modal-title"></h4>
	      </div>
	      <div id="contenidoVentanaModal" class="modal-body"></div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="global.cerrar"/></button>
	      </div>
	    </div>
	
	  </div>
	</div>    
	<div id="_cargandoDatos" class="oculto">
   		<div class="loadingOverlay">
   			<div class="loadingOverlay-content">
   				<img src="imagenes/gear.gif" alt="<spring:message code='global.cargando' />"/>
   				<p class="aCen"><spring:message code='global.cargando' /></p>
   			</div>
   		</div>
	</div>    
  </body>

</html>