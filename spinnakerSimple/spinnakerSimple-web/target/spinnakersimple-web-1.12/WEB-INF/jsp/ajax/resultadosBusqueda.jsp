<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

<c:if test="${not empty resultadosBusqueda}">
        <div class="row">
            <div class="col-md-12 col-sm-12 fondogris separa">
                <div class="container-fluid">
                    <div class="separa">
                        <div class="col-md-12 col-sm-12 fondogris separa">
                
    <c:forEach items="${resultadosBusqueda}" var="anuncio" varStatus="status">
        <c:if test="${status.index % 3 == 0}">
                            <div class="filaTriple">
        </c:if>      
    
                                <div class="col-md-4 col-sm-4 separa">
                                    <div class="crop">
                                        <p>
                                        <a href="verVela.action?ida=<c:out value="${anuncio.anuncio.idAnuncioCodificado}"/>">
                                            <img class="img-responsive lnkAnuncio punteroEnlace" id="lnkAnuncio_<c:out value="${anuncio.anuncio.idAnuncioCodificado}"/>" src="${anuncio.foto1}" alt="<c:out value="${anuncio.anuncio.tituloAnuncio}"/>"/>
                                        </a>    
                                        </p>
                                    </div>
                    
                                    <h3>
                                        <a href="verVela.action?ida=<c:out value="${anuncio.anuncio.idAnuncioCodificado}"/>">
                                            <c:out value="${anuncio.tipoVela.tipoVelaLocalizado}"/>
                                        </a>
                                    </h3>
                    
        <c:if test="${(anuncio.anuncio.tipoBarco < 5 || anuncio.anuncio.tipoBarco > 98 )}"> 
            <c:if test="${anuncio.tipoVela.gratil eq '1'}">
                <c:if test="${!empty anuncio.anuncio.gratil}">
                                    <div class="miniMedidasLabel"><spring:message code="vela.gratil"/></div>
                                    <div class="miniMedidas"><c:out value="${anuncio.anuncio.gratil}"/> <spring:message code="unidades.longitud.metros"/></div>
                </c:if>
            </c:if>
            <c:if test="${anuncio.tipoVela.baluma eq '1'}">     
                <c:if test="${!empty anuncio.anuncio.baluma}">
                                    <div class="miniMedidasLabel"><spring:message code="vela.baluma"/></div>
                                    <div class="miniMedidas"><c:out value="${anuncio.anuncio.baluma}"/> <spring:message code="unidades.longitud.metros"/></div>
                </c:if>
            </c:if>
            <c:if test="${anuncio.tipoVela.pujamen eq '1'}">
                <c:if test="${!empty anuncio.anuncio.pujamen}">
                                    <div class="miniMedidasLabel"><spring:message code="vela.pujamen"/></div>
                                    <div class="miniMedidas"><c:out value="${anuncio.anuncio.pujamen}"/> <spring:message code="unidades.longitud.metros"/></div>
                </c:if>
            </c:if>
            <c:if test="${anuncio.tipoVela.caidaProa eq '1'}">
                <c:if test="${!empty anuncio.anuncio.caidaProa}">
                                    <div class="miniMedidasLabel"><spring:message code="vela.caidaProa"/></div>
                                    <div class="miniMedidas"><c:out value="${anuncio.anuncio.caidaProa}"/> <spring:message code="unidades.longitud.metros"/></div>
                </c:if>
            </c:if>
            <c:if test="${anuncio.tipoVela.caidaPopa eq '1'}">
                <c:if test="${!empty anuncio.anuncio.caidaPopa}">
                                    <div class="miniMedidasLabel"><spring:message code="vela.caidaPopa"/></div>
                                    <div class="miniMedidas"><c:out value="${anuncio.anuncio.caidaPopa}"/> <spring:message code="unidades.longitud.metros"/></div>
                </c:if>
            </c:if>
            <c:if test="${anuncio.tipoVela.tipoCometa eq '1'}">
                <c:if test="${!empty anuncio.anuncio.tipoCometaDescripcion}">
                                    <div class="miniMedidasLabel"><spring:message code="vela.tipoCometa"/></div>
                                    <div class="miniMedidas"><c:out value="${anuncio.anuncio.tipoCometaDescripcion}"/></div>
                </c:if>
            </c:if>
            <c:if test="${anuncio.tipoVela.superficie eq '1'}">
                <c:if test="${!empty anuncio.anuncio.superficie}">
                                    <div class="miniMedidasLabel"><spring:message code="vela.superficie"/></div>
                                    <div class="miniMedidas"><c:out value="${anuncio.anuncio.superficie}"/> <spring:message code="unidades.superficie.metrosCuadrados"/></div>
                </c:if>
            </c:if>
        </c:if>     
                                    <div class="clearfix"></div>
                    
                                </div>    
        <c:if test="${status.index % 3 == 2}">
                            </div>
                            <div class="clearfix"></div>
        </c:if>  
    </c:forEach>
        <c:if test="${status.index % 3 != 2}">
                            </div>
                            <div class="clearfix"></div>
        </c:if>    
                        </div>
                    </div>        
                </div>
            </div>
        </div>
</c:if>

<script>        
    $(function () {
    	noHayMas = "${noHayMas}" == "T";
    });
</script>


