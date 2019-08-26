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
		<table align="center" border=1px>
		<form:hidden path="idx" style="width:300px" readonly = "true"/>
		<form:hidden path="boardId" style="width:300px" readonly = "true"/>
			<tr>
				<td>작성자</td>
				<td><form:input path="rgtId" style="width:300px" readonly = "true"/></td>
			</tr>		
			<tr>
				<td>제목</td>
				<td><form:input path="title" style="width:300px" readonly = "true"/></td>
			</tr>	
			<tr>
				<td>내용</td>
				<td><form:textarea path="contents"
						style="width: 300px; height:300px" readonly = "true"/>
					</textarea></td>
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
