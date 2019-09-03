<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 
/*
 * 파일명 : Index.jsp
 * 용도    : 사용자 메인
 * 작성자 : mintchoco91
 * 변경일 : 2019/07/22
 */
 -->
<!DOCTYPE html>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>임시 메인페이지</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<body>
	<h1>임시 메인페이지입니다.</h1><br/>
	<a href="./kwboardInq.do">[기원]게시판이동</a><br/><br/>
	<a href="./chboard/chboardList.do">[채아]게시판이동</a><br/><br/>
	
	<c:if test="${not empty memberVO.userId}">
		<a href="pjsMember.do?userId=${memberVO.userId }">[임시]${memberVO.userId }</a>
		<a href="logout.do">로그아웃</a>
	</c:if>
	<c:if test="${empty memberVO.userId}">
		<a href="login.do">로그인</a>
	</c:if>
  
</body>

<jsp:include page="footer.jsp"></jsp:include>
</html>
