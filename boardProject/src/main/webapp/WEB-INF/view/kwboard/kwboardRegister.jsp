<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>kwboardRegister</title>
</head>
<script src="//code.jquery.com/jquery.min.js"></script>

<body>
	<br />
	<form:form id="frm" action="#" method="post" modelAttribute="boardVO">
		<table align="center" border=1px>
			<tr>
				<td>작성자</td>
				<td><form:input path="rgtId" style="width:300px" /></td>
			</tr>		
			<tr>
				<td>제목</td>
				<td><form:input path="title" style="width:300px" /></td>
			</tr>	
			<tr>
				<td>내용</td>
				<td><form:textarea path="contents"
						style="width: 300px; height:300px" />
					</textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file"></td>
			</tr>
		</table>
	</form:form>
	<br />
	<br />

	<table align="center">
		<tr>
			<td><input type="button" value="등록"
				onclick="fn_movePage('kwboardWrite.do','Y');"></td>
			<td><input type="button" value="취소"
				onclick="fn_movePage('kwboardList.do');"></td>
		</tr>
	</table>
	
</body>
</html>

<script>
	function fn_movePage(actionUrl,submitYn ="N") {
		if(submitYn == "N"){
			location.replace(actionUrl);
		}else{
			$("#frm").attr("action", actionUrl);
			$("#frm").submit();
		}
	}
</script>