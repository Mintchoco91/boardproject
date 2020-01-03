<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>

<html>
	<body>
		<div class="Container">
		고객센터 > 메일 전송
			<form:form id="frm" name="frm"> 
				<table class="table" width="700px;" summary="고객센터" border="1" cellspacing="0" cellpadding="5" align="center">
					<tr>
						<td>메일주소</td>
						<td><input type="text" size="130" name="email"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" size="130" name="subject"></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea cols="132" rows="20" id="contents" name="contents" style="overflow-y:scroll"></textarea></td>
					</tr>
					
					<tr>
						<td colspan="2" align="right">
						<input type="button" class="btn btn-default" value="등록" onclick="fn_movePage('csSendMail.do','frm','Y')">
					</td>
				</table>
			</form:form>
		</div>
			<!-- footer -->
			<%@include file="/WEB-INF/jsp/cm/footer.jsp" %>
			<!-- footer 끝 -->
	</body>
</html>