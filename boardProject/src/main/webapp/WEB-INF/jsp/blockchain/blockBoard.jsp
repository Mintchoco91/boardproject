<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<script type="text/javascript">
function blockVote(num, voidx){
	var log = document.blockBoard;
	document.blockBoard.idx.value=voidx;
	if(num==1){
		log.action="blockUpVote.do";
	}else if(num==-1){
		log.action="blockDownVote.do";
	}
	log.submit();
}
</script>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<input type="button" onclick="location.href='blockBoardRegister.do'" value="등록">
	<table width="700px" class="table" summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
			<tr>
				<th>idx</th>
				<th>registerId</th>
				<th>upVote</th>
				<th>downVote</th>
				<th>coinValue</th>
				<th>day</th>
			</tr>
		<tbody>
			<c:if test="${blockBoardList.size() !=0 }">
				<form name="blockBoard" id="blockBoard" method="post">
				<input type="hidden" id="idx" name="idx" value=""/>
					<c:forEach var="vo" items="${blockBoardList}">
						<tr>
							<td>${vo.idx }</td>
							<td>${vo.registerId }</td>
							<td><input type="button" value="${vo.upVote}" onClick="blockVote(1, ${vo.idx})"></td>
							<td><input type="button" value="${vo.downVote}" onClick="blockVote(-1, ${vo.idx})"></td>
							<td>${vo.coinValue }</td>
							<td>${vo.timestamp }</td>
						</tr>
					</c:forEach>
				</form>
			</c:if>		
			<c:if test="${blockBoardList.size() == 0 }">
				<tr>
					<td colspan="7" align="center">
						조회된 게시물이 없습니다.
					</td>
				</tr>
			</c:if>
		</tbody>
</table>
</body>
</html>