<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>
<script type="text/javascript">

	$(document).ready(function() { 
		var msg = '${msg}';
		if(msg!=""){
			alert(msg);
		}
	});

  function replyUpdateBtn_click(idx) {
		if($("#btnReplyUpdate_"+idx).html() == '수정'){//수정하기 버튼 클릭 시 -> 읽기전용모드 해제, 포커싱, 버튼 텍스트 변경
			$("#btnReplyUpdate_"+idx).html('수정완료');
			$("#replyContent_"+idx).attr("readonly", false);
			$("#replyContent_"+idx).focus();
		} else {//댓글 수정 후 수정완료 버튼 클릭 시 -> 폼 전송
			$("#replyupdate_idx").val(idx);
			$("#replyupdate_contents").val($("#replyContent_"+idx).val());
			$("#replyupdate_rgtId").val("");
			$("#replyForm").attr("action", "./replyUpdate.do");
			$("#replyForm").submit();
		}
	}

  	
  function replyDeleteBtn_click(idx) {
		var del_confirmMsg = '<spring:message code="board.confirm" arguments="삭제"/>';
		var check = confirm(del_confirmMsg);
	
		if(check){//댓글 삭제 시 필요한 전송값: 게시글 번호, 댓글 인덱스번호만 있으면 됨 
			$("#replyupdate_idx").val(idx);
			$("#replyForm").attr("action", "./replyDelete.do");
			$("#replyForm").submit();
		}
	}
  
</script>

<body>
	<div class="Container">
	게시판 > 상세 페이지
	<!-- contents -->
	<form:form commandName="BoardVO" id="frm" name="frm" >
		<input type="hidden" id="idx" name="idx" value="${vo.idx}"/>
		<table class="table" width="700px;" id="listtable" name="listtable"  summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
			<tbody>
				<tr>
					<th style="width:100;text-align:center" scope="col">제목</th>
					<td colspan="2">${vo.title }</td>
				</tr>
				<tr>
					<th style="width:100;text-align:center" scope="col">작성자</th>
					<td colspan="2">${vo.rgtId }</td>
				</tr>
				<tr>
					<th style="width:100;text-align:center" scope="col">내용</th>
					<td colspan="3"><pre style="height:300px">${vo.contents }</pre></td>
				</tr>
				<tr>
					<th style="width:100;text-align:center" scope="col">첨부파일</th>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="2" align= "right">
						<input type="button" class="btn btn-default" id="" name="" value="수정"  onclick="fn_movePage('boardUpdList.do','frm','Y');"/>
						<input type="button" class="btn btn-default"  id="" name="" value="목록으로"  onclick="fn_movePage('boardList.do')"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
	</div>
	
	<!-- 댓글작성 입력폼 -->
	<form action="./replyInsert.do" method="post">
		<div style="width:650px; text-align: center; margin: auto; position: relative;">
			<br>
			<input type="hidden" name="bno" value="${vo.idx}">
			<textarea rows="5" cols="80" name="contents" id="contents" placeholder="댓글을 작성해주세요"></textarea>
			<br>
      <input type="submit" class="btn btn-default" value ="댓글 작성"><br/><br/>
		</div>
	</form>
		
<table class="table" summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
		<tr>
		   <td>댓글</td>
		</tr>
		<c:forEach var="vo" items="${replyList}">
		      <tr>
		         <td><textarea id="replyContent_${vo.idx}" rows="2" cols="100" readonly>${vo.contents}</textarea><button id="btnReplyUpdate_${vo.idx}" onclick="replyUpdateBtn_click(${vo.idx})">수정</button><button id="btnReplyDelete_${vo.idx}" onclick="replyDeleteBtn_click(${vo.idx})">삭제</button></td>
		      </tr>
		</c:forEach>
</table>	
	
<form id="replyForm" method="post">
	<!-- 댓글 수정 삭제 시 전송폼 (글내용, 게시물 인덱스, 댓글 인덱스, 작성자) -->
	<input type="hidden" id="replyupdate_idx" name="idx">
	<input type="hidden" value="${vo.idx}" id="bno" name="bno">
	<input type="hidden" id="replyupdate_contents" name="contents">
	<input type="hidden" id="replyupdate_rgtId" name="rgtId">
</form>

	<!-- footer -->
	<%@include file="/WEB-INF/jsp/cm/footer.jsp" %>
	<!-- footer 끝 -->
</body>
</html>