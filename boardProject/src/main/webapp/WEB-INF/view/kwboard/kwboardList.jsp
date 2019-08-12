<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>kwboardList</title>
</head>
<body>
	<br/>
	<table align="center">
		<tr>
			<td><input type="text"><input type="button" value="검색"></td>
		</tr>
	</table>
	<br/>
	<table align="center" border=1px>
		<tr>
			<td>선택</td>
			<td>글번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>조회수</td>
			<td>첨부파일</td>
			<td>등록시간</td>
		</tr>

		<tr>
			<td>선택</td>
			<td>글번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>조회수</td>
			<td>첨부파일</td>
			<td>등록시간</td>
		</tr>
	</table>
	<br/><br/>
	
	<table align="center">
		<tr>
			<td><input type="button" value="등록" onclick="fn_movePage('kwboardRegister.do');"></td>
			<td><input type="button" value="삭제" onclick="./"></td>
			
		</tr>
	</table>
	
	<br/>
	
	<table align="center">
		<tr>
			<td>[미구현]페이징 구역</td>
		</tr>
	</table>
</body>
</html>

<script>
	function fn_movePage(actionUrl){
		location.replace(actionUrl);
	}
</script>