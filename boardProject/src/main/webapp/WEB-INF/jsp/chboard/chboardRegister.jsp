<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>

<body>
<style>

	.Container {
		margin: 0 auto;
		padding :0;
		overflow: hidden;
		
	}

	.listTable {
	text-align: center;
	}
	.wrapper {
	 width: 800px;
  margin: 0 auto;
  border: 1px solid #aaa;
  }
.button {
    background-color: white;
    border:  none;
    font-family: a타이틀고딕3;
    text-decoration: none;
    padding: 10px 10px;
    margin: 1px;
     border-top-left-radius:20px;
 border-top-right-radius:20px;
 border-bottom-right-radius:20px;
 border-bottom-left-radius:20px;
 background: #b7c7e5;
 }
.button:hover {
   background: #93A9D1;
   color : white;
}
}
</style>
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

	<!-- Container -->
	<div class="Container">
	<!-- contents -->
	<c:choose>
	    <c:when test="${flag == '등록'}">
	    <h1>등록 페이지</h1>
		</c:when>
		<c:otherwise>
	    <h1>수정 페이지</h1>
		</c:otherwise>
	</c:choose>
	<form:form commandName="BoardVO" id="frm" name="frm" method="post" modelAttribute="BoardVO" >
	<input type="hidden" id="idx" name="idx" value=""/>
	<input type="hidden" id="boardId" name="boardId" value="free"/>
	<input type="hidden" id="currentPage" name="currentPage" value=""/>
	<!-- form:input path= "" id랑 name을 맞춰준다 -->
	<table width="700px;" id="insertTable" name="insertTable"  summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
		<thead>
		<colgroup>
			<col width="30%">
			<col width="30%">
			<col width="30%">
			<col width="20%">
		</colgroup>
		</thead>
		<tbody>
		<tr>
			<th scope="col" >제목</th>
			<td><form:input path="title" maxlength="100" size="50"/></td>
			<th scope="col">작성자</th>
			<td><form:input path="rgtId"   /></td>
		</tr>
		<tr>
			<th scope="col">내용</th>
			<td colspan="3"><textarea cols="70" rows="20" id="contents" name="contents" style="overflow-y:scroll"><%-- ${boardVO.contents } --%></textarea></td>
		</tr>
		<tr>
			<th scope="col">첨부파일</th>
			<td colspan="3"><input type="file" id="attachFile" name="attachFile"  value="" /></td>
		</tr>
		<tr>
			<td colspan="4" style="text-align: right">
				<input type="button" class="button" id="" name="" value="등록"  onclick="fn_Register()"/>
				<input type="button" class="button" id="" name="" value="취소"  onclick="history.back()"/>
			</td>
		</tr>
		<!-- Paging -->
	</tbody>
	
	</table>
	</form:form>
	</div>
	<!-- //Container -->
	
	<!-- // header -->
	
	
<!-- //wrapper -->
</body>
</html>