<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <title><spring:message code="app.titulo"/></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="title" content="Spinnaker Simple">
    <meta name="description" content="<spring:message code="metaTag.descripttion"/>">
    <meta name="keywords" content="<spring:message code="metaTag.keywords"/>">
    <meta name="robots" content="index, follow">
    <meta name="revisit-after" content="15 days">
    <meta name="author" content="Spinnaker Simple v.<c:out value="${appVersion}"/>">    
    <meta name="viewport" content="width=device-width, initial-scale=1">
<c:if test="${socialButtons eq 'S'}">
    <meta property="og:url" content="<c:out value="${socialBean.ogUrl}"/>"/>
    <meta property="og:type" content="<c:out value="${socialBean.ogType}"/>"/>
    <meta property="og:title" content="<c:out value="${socialBean.ogTittle}"/>"/>
    <meta property="og:description" content="<c:out value="${socialBean.ogDescription}"/>"/>
    <meta property="og:image" content="<c:out value="${socialBean.ogImage}"/>"/>
</c:if>	
    <sec:csrfMetaTags/>
<c:if test="${socialButtons eq 'S'}">
	<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
</c:if>
<c:if test="${(estadisticas eq 'S') && ( empty logged || ( not empty logged && user.admin ne '1' ))}">
	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-131739891-1"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag(){dataLayer.push(arguments);}
		gtag('js', new Date());
		gtag('config', 'UA-131739891-1');		
	</script>
</c:if>
<c:if test="${(publicidad eq 'S') && (user.admin ne '1')}">
    <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
    <script>
      (adsbygoogle = window.adsbygoogle || []).push({
        google_ad_client: "ca-pub-7791939720825840",
        enable_page_level_ads: true
      });
</script>
</c:if>
<c:if test="${(googleAds eq 'S') && (user.admin ne '1')}">
	<script async src="https://www.googletagmanager.com/gtag/js?id=AW-769701470"></script>
	<script>
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments);}
        gtag('js', new Date());
        gtag('config', 'AW-769701470');
	</script>
</c:if>
    <link rel="icon" href="favicon.ico">
    <link href="css/spinnakerSimple.css" rel="stylesheet">
    <script src="fontawesome/js/fontawesome.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src='https://www.google.com/recaptcha/api.js?hl=${pageContext.response.locale}'></script>
    
    <!-- Bootstrap core JavaScript -->
    <script src="dist/js/jquery.min.js" type="text/javascript"></script>
    <script>window.jQuery || document.write('<script src="assets/js/vendor/jquery.min.js" type="text/javascript"><\/script>')</script>
    <script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="dist/js/spinnakerSimple.js" type="text/javascript"></script>
    <script src="dist/js/bootstrap-select.js" type="text/javascript"></script>
    <script src="dist/js/jquery.waypoints.min.js" type="text/javascript"></script>
    <script src="js/scripts.js" type="text/javascript"></script>
    <script src="politica-cookies/js/cookies.js" type="text/javascript"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="assets/js/ie10-viewport-bug-workaround.js" type="text/javascript"></script>
  </head>
  <body>
<input type="hidden" id="_valorTemporal" value=""/>
<tiles:insertAttribute name="menu" />
<tiles:insertAttribute name="contenido" />
<tiles:insertAttribute name="pie" />
<tiles:insertAttribute name="javascript" />
	<div id="overbox3">
	    <div id="infobox3">
	        <p><spring:message code="cookies.principal.1"/>
	        <a href="politicaCookies.action"><spring:message code="cookies.principal.2"/></a>
	        <a onclick="aceptar_cookies();" style="cursor:pointer;"><spring:message code="cookies.principal.3"/></a></p>
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
</html><!-- Spinnaker Simple v.<c:out value="${appVersion}"/> -->
  