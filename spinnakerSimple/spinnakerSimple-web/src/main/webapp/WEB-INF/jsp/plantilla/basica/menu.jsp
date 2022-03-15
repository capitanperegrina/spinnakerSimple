<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

    <div class="col-md-12" style="padding: 5px;">
        <div style="text-align: right;">
            <a href="?lang=es${queryString}"><img src="imagenes/flags/es.png" alt="Espa&ntilde;ol" /></a>
            <a href="?lang=en${queryString}"><img src="imagenes/flags/gb.png" alt="English" /></a>
            <a href="?lang=it${queryString}"><img src="imagenes/flags/it.png" alt="Italiano" /></a>
            <a href="?lang=gl${queryString}"><img src="imagenes/flags/gl.png" alt="Galego" /></a>
            <a href="?lang=ca${queryString}"><img src="imagenes/flags/ct.png" alt="Catal&agrave;" /></a>
        </div>
    </div>

	<div class="col-md-12 logo cabeceraLogo">
        <a href="./"><img src="imagenes/logo.png" class="img-responsive img-centrada" alt="Logotipo Spinnaker"></a>
		<div class="cabeceraLogoBarraMenu">
		</div>
    </div>
    <!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only"><spring:message code="global.toggleNavigation"/></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
<!--  
<c:if test="${logged == null}">
			<li id="li_empresa"><a href="empresa.action"><spring:message code="global.menu.empresa"/></a></li>
</c:if>
<c:if test="${logged != null}">
	<c:if test="${user.admin ne '1'}">
			<li id="li_empresa"><a href="empresa.action"><spring:message code="global.menu.empresa"/></a></li>
	</c:if>
</c:if>

<c:if test="${logged == null}">
			<li id="li_contacto"><a href="contacto.action"><spring:message code="global.menu.contacto"/></a></li>
</c:if>
<c:if test="${logged != null}">
	<c:if test="${user.admin ne '1'}">
			<li id="li_contacto"><a href="contacto.action"><spring:message code="global.menu.contacto"/></a></li>
	</c:if>
</c:if>
-->
            <li id="li_inicio"><a href="inicio.action"><spring:message code="global.menu.inicio"/></a></li>
            <li id="li_empresa"><a href="empresa.action"><spring:message code="global.menu.empresa"/></a></li>
            <li id="li_comprar"><a href="comprarVela.action"><spring:message code="global.menu.comprar"/></a></li>
            <li id="li_vender"><a href="venderVela.action"><spring:message code="global.menu.vender"/></a></li>
<c:if test="${logged != null}">
	<c:if test="${user.admin eq '1'}">
			<li id="li_admin"><a href="admin.action"><spring:message code="global.admin.corto"/></a></li>
	</c:if>
</c:if>
	
          </ul>

		  <ul class="nav navbar-nav navbar-right">
<c:if test="${logged != null}">
			<li id="li_misAnuncios"><a href="misAnuncios.action"><spring:message code="global.menu.misAnuncios"/></a></li>
			<li id="li_usuarioConectado"><a href="elPerfil.action"><img src="https://www.gravatar.com/avatar/<c:out value="${gravatarMd5}"/>?s=18" alt="<c:out value="${user.nombre}"/>"/> <c:out value="${user.nombre}"/></a></li>
			<li id="li_logout"><a href="logout.action"><spring:message code="global.logout"/></a></li>
</c:if>
<c:if test="${logged == null}">
			<li><a id="li_login" href="login.action"><spring:message code="global.login"/></a></li>
			<li><a id="li_registro" href="signUp.action"><spring:message code="global.reistro"/></a></li>
</c:if>
                <!-- /.dropdown -->
                <li class="dropdown hidden-xs hidden-sm hidden-md hidden-lg hidden-xl">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fas fa-globe-europe"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
						<li><a href="?lang=es${queryString}"><img src="imagenes/flags/es.png" alt="Espa&ntilde;ol" /> Espa&ntilde;ol</a></li>
						<li><a href="?lang=en${queryString}"><img src="imagenes/flags/gb.png" alt="English" /> English</a></li>
                        <li><a href="?lang=it${queryString}"><img src="imagenes/flags/it.png" alt="Italiano" /> Italiano</a></li>
<!--
						<li><a href="?lang=en${queryString}"><img src="imagenes/flags/gb.png" alt="English" /> English</a></li>
						<li><a href="?lang=pt${queryString}"><img src="imagenes/flags/pt.png" alt="Portugu&eacute;s" /> Portugu&eacute;s</a></li>
						<li><a href="?lang=fr${queryString}"><img src="imagenes/flags/fr.png" alt="Fran&ccedil;ais" /> Fran&ccedil;ais</a></li>
						<li><a href="?lang=de${queryString}"><img src="imagenes/flags/de.png" alt="Deutsch" /> Deutsch</a></li>						
						<li><a href="?lang=it${queryString}"><img src="imagenes/flags/it.png" alt="Italiano" /> Italiano</a></li>
-->
						<li><a href="?lang=gl${queryString}"><img src="imagenes/flags/gl.png" alt="Galego" /> Galego</a></li>
						<li><a href="?lang=ca${queryString}"><img src="imagenes/flags/ct.png" alt="Catal&agrave;" /> Catal&agrave;</a></li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->

		  </ul>          
          
<!--          
          <ul class="nav navbar-nav navbar-right">
			<li><a href="?lang=es&${queryString}" class="enlaceIdioma">ES</a></li>
			<li><a href="?lang=pt&${queryString}" class="enlaceIdioma">PT</a></li>
			<li><a href="?lang=en&${queryString}" class="enlaceIdioma">EN</a></li>
			<li><a href="?lang=fr&${queryString}" class="enlaceIdioma">FR</a></li>
			<li><a href="?lang=de&${queryString}" class="enlaceIdioma">DE</a></li>
			<li><a href="?lang=it&${queryString}" class="enlaceIdioma">IT</a></li>
          </ul>
-->
        </div><!--/.nav-collapse -->
      </div>
    </nav>
	<div class="col-md-12 text-center separador separadorCabecera">
		<a href="https://www.facebook.com/Spinnakersimplecom-1746652158993713/" target="_blank"><i style="font-size: 1.5em; margin: 10px;" class="fab fa-facebook"></i></a>
		<a href="https://twitter.com/spinnakersimple" target="_blank"><i style="font-size: 1.5em; margin: 10px;" class="fab fa-twitter"></i></a>
		<a href="https://www.instagram.com/spinnaker_simple/" target="_blank"><i style="font-size: 1.5em; margin: 10px;" class="fab fa-instagram"></i></a>
	</div>
    
 <script>
	$(document).ready(function(){
		$('#li_<c:out value="${menuSeleccionado}"/>').addClass('active');
 	});
 </script>