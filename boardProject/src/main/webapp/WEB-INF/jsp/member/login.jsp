<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	function memberLogin(){
		var log = document.loginform;
		log.action="loginCheck.do";
		var userId = document.getElementById("userId").value;
		var pw=document.getElementById("pw").value;
		if(userId==""){
			alert("아이디를 입력하세요");
		} else if(pw==""){
			alert("비밀번호를 입력하세요");
		} else {
			log.submit();
		}
	}
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<jsp:include page="../header.jsp"></jsp:include>

<br/>
<br/>
<form:form name="loginform" method="post" onKeyPress="if(event.keyCode==13){memberLogin();}">
	<table align="center" border=1px>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userId" id="userId"></td>
			<!--  form:input type="text" path="userId" / -->
		</tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw" id="pw"></td>
		<tr>
	</table>
	<br/>
	<table align="center">
		<tr>
			<td align="center"> <input type="button" value="로그인" onClick="memberLogin()" onKeyPress="if(event.keyCode==13){memberLogin();}"> </td> 
		</tr>
		<c:if test="${not empty resultMessage}">
			<tr>
				<td ><font color=red>${resultMessage }</font></td>
			</tr>
		</c:if>
	</table>
</form:form>

</body>
</html>


