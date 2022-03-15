<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Navegación</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="inicio.action">
	                <img src="imagenes/logo_peque.png" style="height: 25px;"/>
                </a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="adminElPerfil.action?id=<c:out value='${userUi.idUsuarioCodificado}'/>"><i class="fa fa-user fa-fw"></i> <c:out value="${user.nombre}"/></a></li>
                        <li class="divider"></li>
                        <li><a href="logout.action"><i class="fa fa-sign-out fa-fw"></i> <spring:message code="global.logout"/></a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="admin.action"><i class="fa fa-dashboard fa-fw"></i> <spring:message code="dashboard.escritorio"/></a>
                        </li>
                        <li>
                            <a href="adminAnuncios.action"><i class="fa fa-table fa-fw"></i> <spring:message code="global.anuncios"/></a>
                        </li>
                        <li>
                            <a href="adminMensajes.action"><i class="fa fa-table fa-fw"></i> <spring:message code="global.mensajes"/></a>
                        </li>
                        <li>
                            <a href="adminUsuarios.action"><i class="fa fa-table fa-fw"></i>  <spring:message code="vendedores.titulo"/></a>
                        </li>
                        <li>
                            <a href="adminParametrizacion.action"><i class="fa fa-cogs fa-fw"></i>  <spring:message code="global.parametrizacion"/></a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
