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
	if(name=="") alert("아이디를 입력해주세요");
	else if(email1=="") alert("이메일을 입력해주세요");
	else if(pw=="") alert("비밀번호를 입력해주세요");
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
			<td>회원 아이디</td>
			<td>${userId }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw" id="pw"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id="name" value="${memberVO.name }"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email1" id="email1" value="${memberVO.email1 }"></td>
		</tr>
	</table>
	<input type="button" value="정보수정" onClick="modify()"> 
	<input type="button" value="취소" onClick="cancel()">
	</form:form>
</body>
</html>