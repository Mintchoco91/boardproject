<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>
<%--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	 --%>      <!-- jstl 제어,대입문 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
  .header {
  padding:40px 10px;
  text-align: center;
  background: #eee;
  margin-bottom: 20px;
}
.footer {
  text-align: center;
  border-top: 1px solid #aaa;
  margin: 20px 20px 0;
  font-size: 12px;
}

}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>

function fn_Register() {
	var f = document.listform;
	location.href="chboardRegister.do";
/* 	f.action="chboardRegister.do";
	f.submit(); */
}

</script>
<body>


<!-- wrapper -->
	<div class="wrapper">

	<!-- header -->
	<div class="header">
	
	</div>
	<!-- Container -->
	<div class="Container">
	<form:form commandName="BoardVO" id="listform" name="listform" method="get">
	<table width="700px" class="listTable" id="" name="" method="get" summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
	<thead>
		<tr >
				<td colspan="7">
				${totalCount }
				<fieldset style="border:none; text-align: right;">
				<select id="" name="" onchange="">
					<option value="제목">제목</option>
					<option value="내용">내용</option>
				</select>
					<input type="text" id="" name="" value=""/>
					<input type="button" id="" name="" value="검색" onclick=""/>
					</fieldset>
				</td>		
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><input type="checkbox" id="all" name="" value=""/></td>
			<td>글번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>조회수</td>
			<td>첨부파일</td>
			<td>등록일시</td>
		</tr>
		
		<c:set var="list" value="${boardList.boardList }"/>
		<!-- list.size !=0 -->
		<c:if test="${list.size() !=0 }" >
		<c:forEach var="vo" items="${list}">
				<tr>
				<td><input type="checkbox" id="all" name="" value=""/></td>
				<td>${vo.idx }</td>
				<td>${vo.title }</td>
				<td>${vo.contents }</td>
				<td>${vo.readCnt }</td>
				<td></td>
				<td>${vo.rgtDtm }</td>
				</tr>
		</c:forEach>
		</c:if>
		<c:if test="${list.size() == 0 }">
			<tr>
			<td colspan="7">
				게시물이 없습니다.
			</td>
		</tr>
			
		</c:if>
		<tr>
			<td colspan="7" style="text-align: right">
				<input type="button" id="" name="" value="등록"  onclick="fn_Register()"/>
				<input type="button" id="" name="" value="삭제" onclick="fn_Delete()"/>
			</td>
		</tr>
		<!-- Paging -->
		<tr>
			<td colspan="7"></td>
		</tr>
	</tbody>
	
	</table>
	</form:form>
	</div>
	<!-- //Container -->
	
	<!-- // header -->
	
	
	<!-- footer -->
	<div class="footer">
	
	
	</div>
	</div>
<!-- //wrapper -->
	
</body>
</html>