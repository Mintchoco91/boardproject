<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 등록</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>
<script type="text/javascript">

function fn_Register() {
	var f= document.listform;
	
	if($('#title').val() == '') {
	    alert("아이디를 입력하세요");
	    $('#title').focus();
	    return;
	}
		if($('#rgtId').val() == '') {
	    alert("글쓴이를 입력하세요");
	    $('#rgtId').focus();
	    return;
	}
	if($('#contents').val() == '') {
	    alert("내용을 입력하세요");
	    $('#contents').focus();
	    return;
	}
	f.action="chboardInsert.do";
	f.submit();
}



</script>
<!-- wrapper -->


	<!-- header -->
	
	<!-- Container -->
	<div class="Container">
	<!-- contents -->
	<div class="contents">
	
	<form:form commandName="BoardVO" id="listform" name="listform" method="get" >
	<input type="hidden" id="idx" name="" value=""/>
	<input type="hidden" id="boardId" name="boardId" value="free"/>
	<input type="hidden" id="currentPage" name="currentPage" value=""/>
	<div class="" style="width : 650px;" >
	<!-- form:input path= "" id랑 name을 맞춰준다 -->
	
	<table width="100%" id="insertTable" name="insertTable" method="get" summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
		<thead>
		<colgroup>
			<col width="20%">
			<col width="30%">
			<col width="20%">
			<col width="20%">
		</colgroup>
		</thead>
		<tbody>
		<tr>
			<th scope="col" >제목</th>
			<td><input type="text" id="title" name="title" value="" maxlength="100" size="50"/></td>
			<th scope="col">작성자</th>
			<td><input type="text" id="rgtId" name="rgtId"  value="" /></td>
		</tr>
		<tr>
			<th scope="col">내용</th>
			<td colspan="3"><textarea cols="70" rows="20" id="contents" name="contents"></textarea></td>
		</tr>
		<tr>
			<th scope="col">첨부파일</th>
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
	</div>
	</form:form>
	</div>
	<!-- //contents -->
	</div>
	<!-- //Container -->
	
	<!-- // header -->
	
	
<!-- //wrapper -->
</body>
</html>