<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>

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
</style>
</head>

<body>
<!-- wrapper -->

	<!-- Container -->
	<div class="Container">
	<!-- contents -->
	<table width="700px;" id="listtable" name="listtable" method="get" summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
		<thead>
		<colgroup>
			<col width="20%">
			<col width="30%">
			<col width="30%">
			<col width="20%">
		</colgroup>
		</thead>
		<tbody>
		<tr>
			<th scope="col" >제목</th>
			<td><input type="text" id="title" name="title" value="${vo.title }" maxlength="100" size="50"/></td>
			<th scope="col">작성자</th>
			<td><input type="text" id="rgtId" name="rgtId"  value="${vo.rgtId }" /></td>
		</tr>
		<tr>
			<th scope="col">내용</th>
			<td colspan="3"><textarea cols="70" rows="20" id="contents" name="contents" style="overflow-y:scroll" >${vo.contents }</textarea></td>
		</tr>
		<tr>
			<th scope="col">첨부파일</th>
			<td colspan="3"><input type="file" id="attachFile" name="attachFile"  value="" /></td>
		</tr>
		<tr>
			<td colspan="4" style="text-align: right">
				<input type="button" id="" name="" value="수정"  onclick="fn_Update()"/>
			</td>
		</tr>
		<!-- Paging -->
	</tbody>
	</table>
	<!-- //contents -->
	</div>
	<!-- //Container -->
	
	<!-- // header -->
	
	
<!-- //wrapper -->
</body>

</html>