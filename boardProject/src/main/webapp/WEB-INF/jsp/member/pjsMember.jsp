<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
function modify(){
	var log = document.member;
	log.action="memberModify.do";
	var pw=document.member.pw;
	var name = document.member.name;
	var email1 = document.member.email1;
	if(name=="") alert("���̵� �Է����ּ���");
	else if(email1=="") alert("�̸����� �Է����ּ���");
	else if(pw=="") alert("��й�ȣ�� �Է����ּ���");
	else log.submit();
}
function cancel(){
	var log=document.member;
	log.action="index.do";
	log.submit();
}
</script>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<h1>${test }</h1>
<body>
	<form:form name="member" method="post">
	<table>
		<tr>
			<td>ȸ�� ���̵�</td>
			<td>${userId }</td>
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
	<input type="button" value="��������" onClick="modify()"> 
	<input type="button" value="���" onClick="cancel()">
	</form:form>
</body>
</html>