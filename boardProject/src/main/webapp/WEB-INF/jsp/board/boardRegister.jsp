<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- wrapper -->


	<!-- header -->
	
	<!-- Container -->
	<div class="Container">
	<form id="listform" name="listform" method="get">
	<input type="hidden" id="idx" name="" value=""/>
	<table width="700px" class="listTable" id="" name="" method="get" summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
		<tbody>
		<tr>
			<td width="100">제목</td>
			<td><input type="text" id="title" name="title" maxlength="100" size="50"/></td>
			<td>작성자</td>
			<td><input type="text" id="rgtId" name="rgtId"  value="" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3"><textarea cols="80" rows="20"></textarea></td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td colspan="3"><input type="file" id="attachFile" name="attachFile"  value="" /></td>
		</tr>
		
		
		<tr>
			<td colspan="4" style="text-align: right">
				<input type="button" id="" name="" value="등록"  onclick="fn_Register()"/>
			</td>
		</tr>
		<!-- Paging -->
	</tbody>
	
	</table>
	</form>
	</div>
	<!-- //Container -->
	
	<!-- // header -->
	
	
<!-- //wrapper -->
</body>
</html>