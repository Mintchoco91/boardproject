<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<script type="text/javascript">
function memberModify(){
	var log = document.member;
	log.action="memberModify.do";
	var pw=document.member.pw.value;
	var name = document.member.name.value;
	var email1 = document.member.email1.value;
	if(name=="") alert("���̵� �Է����ּ���");
	else if(email1=="") alert("�̸����� �Է����ּ���");
	else if(pw=="") alert("��й�ȣ�� �Է����ּ���");
	else log.submit();
}
function memberDelete(){
	var log=document.member;
	log.action="";
	log.submit();
}
</script>
<style>
.inputClass td{
padding-left: 5px;
padding-right: 5px;
}
</style>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<br/>
	<br/>
	<form:form name="member" method="post">
	<table align="center" border=1px class="inputClass">
		<tr>
			<td>ȸ�� ���̵�</td>
			<td>${memberVO.userId }</td>
		</tr>
		<tr>
			<td>��й�ȣ</td>
			<td><input type="password" name="pw" id="pw"></td>
		</tr>
		<tr>
			<td>�̸�</td>
			<td><input type="text" name="name" id="name" value="${memberVO.name }"></td>
		</tr>
		<tr>
			<td>�̸���</td>
			<td><input type="text" name="email1" id="email1" value="${memberVO.email1 }"></td>
		</tr>
	</table>
	<br/>
	<table align="center">
		<tr>
			<td align="center"> <input type="button" value="��������" onClick="memberModify()"></td>
			<td align="center"> <input type="button" value="ȸ��Ż��" onClick="memberDelete()"></td> 
		</tr>
	</table>
	<c:if test="${not empty resultMessage}">
		<table align="center">
			<tr>
				<td colspan="2"><font color=red>${resultMessage }</font></td>
			</tr>
		</table>
	</c:if>
	</form:form>
</body>
</html>