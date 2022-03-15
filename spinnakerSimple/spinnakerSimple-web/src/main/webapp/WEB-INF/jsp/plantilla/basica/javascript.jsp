<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

  <!-- Modal -->
  <div class="modal fade" id="_msgModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" id="_msgModal_title"><spring:message code="global.mensaje"/></h4>
        </div>
        <div class="modal-body">
          <p id="_msgModal_text"></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="global.cerrar"/></button>
        </div>
      </div>
    </div>
  </div>

<script>
    $('#_msgModal').modal({ show: false})
	$(document).ready(function(){
		$('#li_<c:out value="${menuSeleccionado}"/>').addClass('active');
	});
</script>