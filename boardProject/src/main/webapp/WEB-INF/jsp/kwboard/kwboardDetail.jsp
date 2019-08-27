<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>boardproject</title>
</head>
<script src="//code.jquery.com/jquery.min.js"></script>
<%@ include file="/WEB-INF/js/cm/cm.js" %>

<!-- 이 위로 header -->

<body>
	<h1 align="center">상세페이지</h1>
	<br />
	<form:form id="frm" action="#" method="post" modelAttribute="boardVO">
		<table align="center" border=1px style="width:700px">
		<form:hidden path="idx" readonly = "true"/>		
		<form:hidden path="boardId" readonly = "true"/>		
		<form:hidden path="readCnt" readonly = "true"/>
		<form:hidden path="title" readonly = "true"/>		
		<form:hidden path="rgtId" readonly = "true"/>
		<form:hidden path="rgtDtm" readonly = "true"/>
		<form:hidden path="contents" readonly = "true"/>
		
			<tr>
				<td style="background-color:#E0E6F8">작성자</td>
				<td style="width:420px">${boardVO.rgtId}</td>
				<td style="background-color:#E0E6F8">조회</td>
				<td style="width:140px">${boardVO.readCnt}</td>
			</tr>		
			<tr>
				<td style="background-color:#E0E6F8">제목</td>
				<td style="width:420px">${boardVO.title}</td>
				<td style="background-color:#E0E6F8">등록시간</td>
				<td style="width:140px">${boardVO.rgtDtm}</td>
			</tr>	
			<tr>
				<td style="background-color:#E0E6F8">내용</td>
				<td colspan="3" valign=top style="width:640px;height:300px;">${boardVO.contents}</td>
			</tr>
			<!-- 
			<tr>
				<td>첨부파일</td>
				<td><input type="file"></td>
			</tr>
			-->
		</table>
	</form:form>
	<br />
	<br />

	<table align="center">
		<tr>
			<td><input type="button" value="수정"
				onclick="fn_movePage('kwboardModifyPage.do','Y');"></td>
			<td><input type="button" value="취소"
				onclick="fn_movePage('kwboardInq.do');"></td>
		</tr>
	</table>
	
</body>
</html>
