<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

    <div class="container-fluid">

		<div style="padding: 6em; text-align: center;"><c:out value="${msg}" escapeXml="false"/></div>

		<div class="separa" style="text-align: center; margin-top: 2em;">
			<button class="btn btn-primary" onclick="document.location.href='inicio.action';"><spring:message code="global.menu.inicio"/></button>
		</div>
		
		<div class="separa">&nbsp;</div>

	</div>

<c:if test="${(googleAds eq 'S') && (user.admin ne '1')}">
    <c:if test="${(conversionAltaVela eq 'S')}">
    <script>
        gtag('event', 'conversion', {'send_to': 'AW-769701470/Kl-fCO2oxagBEN7sgu8C'});
    </script>
    </c:if>	
    <c:if test="${(conversionComentarioVela eq 'S')}">
    <script>
        gtag('event', 'conversion', {'send_to': 'AW-769701470/yn4GCJjWpqkBEN7sgu8C'});
    </script>
    </c:if>
</c:if>