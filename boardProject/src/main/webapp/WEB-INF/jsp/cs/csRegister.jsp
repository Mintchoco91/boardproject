<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>


<body>

<form:form id="frm" name="frm"> 
	<table width="700px" class="listTable" id="" name=""  summary="고객센터" border="1" cellspacing="0" cellpadding="5" align="center">

		<tr>
			<td>메일주소</td>
			<td><input type="text" size="95" name="email"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" size="95" name="subject"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea  cols="95" rows="20" id="contents" name="contents" style="overflow-y:scroll"></textarea></td>
		</tr>
		
		<tr>
			<td colspan="2" align="right">
			<input type="button" class="button" value="등록" onclick="fn_movePage('csSendMail.do','frm','Y')">
		</td>
	</table>
	</form:form>

</body>
</html>