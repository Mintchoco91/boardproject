<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div#topbar{
width:100%;
margin:5px;
}
div#loginbar{
width:90%;
padding-left:15px;
padding-right:15px;
margin-left: 5px;
margin-right: 5px;
}
div#navibar{
width:700px;
height:100px;
padding-left:30px;
padding-right:30px;
margin-left:auto;
margin-right:auto;
}
.logintab li{float: right; list-style: none; margin-right:10px}
.logintab li::after{padding-left:10px;content:"|"}
.logintab li:first-child::after{content:""}
div#navilogo{
margin-bottom:-30px;
margin-left : 15px;
margin-right:15px;
float:left;
}
div#navicategory {
float:right;
}
.categorytab li{float:left; list-style:none}
</style>
</head>
<body>
<div id="topbar">
	<div id="loginbar">
		<ul class=logintab>
			<c:if test="${not empty sessionScope.userid}">
				<li><a href="logout.do">로그아웃</a></li>
				<li><a href="pjsMember.do">${sessionScope.userid }</a></li>
			</c:if>
			<c:if test="${empty sessionScope.userid}">
				<li><a href="login.do">로그인</a></li>
				<li><a href="pjsMemberRegister.do">회원가입</a></li>
			</c:if>
		</ul>
	</div>
</div>
<div id="navibar">
	<div id="navilogi">
		<a href="index.do">
			<img id="studylogo" src="${pageContext.request.contextPath}/resources/images/studylogo.png" style="height:60px;border:2px;">
		</a>
	</div>
	<div id="navicategory">
		<ul class="categorytab">
			<li><a href="" style="padding-left:10px;padding-right:10px;">카테고리1</a></li>
			<li><a href="" style="padding-left:10px;padding-right:10px;">카테고리2</a></li>
			<li><a href="" style="padding-left:10px;padding-right:10px;">카테고리3</a></li>
			<li><a href="" style="padding-left:10px;padding-right:10px;">카테고리4</a></li>
			<li><a href="" style="padding-left:10px;padding-right:10px;">카테고리5</a></li>
			<li><a href="" style="padding-left:10px;padding-right:10px;">카테고리6</a></li>
		</ul>
	</div>
</div>
</body>
</html>