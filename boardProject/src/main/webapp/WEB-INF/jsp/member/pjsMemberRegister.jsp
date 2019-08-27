<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
	function memberSave(){
		var userId =document.getElementById("userId").value;
		var pw=document.getElementById("pw").value;
		var name = document.getElementById("name").value;
		var email1 = document.getElementById("email1").value;
		if(userId==""){
			alert("아이디를 입력해주세요");
		} else if(pw==""){
			alert("패스워드를 입력해주세요");
		} else if(name==""){
			alert("이름을 입력해 주세요");
		} else {
			document.register.submit();
		} 
	}
</script>

<head>
<meta charset="UTF-8">
<title>pjsMemberRegister</title>
</head>
<body>
	<form:form name="register" action="register.do" method="post">
		<table>
			<tr>
				<tr>
					<td>아이디</td>
					<td><form:input path="userId" placeholder="아이디 입력" /></td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><form:password path="pw" placeholder="비밀번호 입력"/></td>
				</tr>
				<tr>	
					<td>이름</td>
					<td><form:input path="name" placeholder="이름 입력"/></td>
				</tr>
				<tr>
					<td>email 주소</td>
					<td><form:input path="email1"/></td>
				</tr>
		</table>
		<div>
			<input type="button" value="저장" onClick = "memberSave()">
		</div>
	</form:form>

</body>
</html>