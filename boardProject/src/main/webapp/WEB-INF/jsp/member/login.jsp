<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
<form:form name="login" method="post" >
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userId" id="userId"></td>
			<!--  form:input type="text" path="userId" / -->
		</tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw" id="pw"></td>
		<tr>
	</table>
	<input type="button" value="로그인" onClick="loginCheck()"> 
	<input type="button" value="회원가입" onClick="memberRegister()">
</form:form>
</body>
</html>

<script type="text/javascript">
function loginCheck(){
	var log=document.login;
	var userId = document.login.userId;
	var pw=document.login.pw;
	log.action="loginCheck.do";
	if(userId=""){
		alert("아이디를 입력하세요");
	} else if(pw=""){
		alert("비밀번호를 입력하세요");
	} else {
		log.submit();
	}
}

function memberRegister(){
	var log = document.login;
	log.action="pjsMemberRegister.do";
	log.submit();
}


</script>
