<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

    <div class="container-fluid">

		<div class="errorFormulario" style="padding: 6em; text-align: center;"><c:out value="${msg}" escapeXml="false"/></div>

		<div class="separa" style="text-align: center; margin-top: 2em;">
			<button class="btn btn-primary" onclick="document.location.href='inicio.action';"><spring:message code="global.menu.inicio"/></button>
		</div>
		
		<div class="separa">&nbsp;</div>
		
	</div>
<c:if test="${modoDebug eq 'S'}">
	<div class="trazaExcepcion">
	    <h4><spring:message code="global.infoDebug" /></h4>
		<pre><c:out value="${traza}"/></pre>
	</div>
</c:if>