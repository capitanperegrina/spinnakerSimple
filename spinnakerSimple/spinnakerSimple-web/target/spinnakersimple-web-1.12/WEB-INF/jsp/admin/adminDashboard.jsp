<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><spring:message code="dashboard.escritorio"/></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
                
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-file fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${numAnunciosPendientes}</div>
                                    <div><spring:message code="dashboard.anuncios.pendientes" htmlEscape="false"/></div>
                                </div>
                            </div>
                        </div>
                        <a href="adminAnuncios.action?v=0">
                            <div class="panel-footer">
                                <span class="pull-left"><spring:message code="global.verDetalles"/></span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-list fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${numAnunciosPublicados}</div>
                                    <div><spring:message code="dashboard.anuncios.vigentes" htmlEscape="false"/></div>
                                </div>
                            </div>
                        </div>
                        <a href="adminAnuncios.action?v=1">
                            <div class="panel-footer">
                                <span class="pull-left"><spring:message code="global.verDetalles"/></span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-calendar fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${numAnunciosACaducar}</div>
                                    <div><spring:message code="dashboard.anuncios.aCaducar" htmlEscape="false"/></div>
                                </div>
                            </div>
                        </div>
                        <a href="adminAnuncios.action?v=30">
                            <div class="panel-footer">
                                <span class="pull-left"><spring:message code="global.verDetalles"/></span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-comments fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${numMensajesPendientes}</div>
                                    <div><spring:message code="dashboard.mensajes.pendientes" htmlEscape="false"/></div>
                                </div>
                            </div>
                        </div>
                        <a href="adminMensajes.action?e=0">
                            <div class="panel-footer">
                                <span class="pull-left"><spring:message code="global.verDetalles"/></span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
            </div>                        