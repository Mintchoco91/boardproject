<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>
<script type="text/javascript">

/*
		$("#btnReply").click(function(){
			var contents = $("#contents").val();
			var bno = "${dto.bno}";
			var param = "contents="+replytext+"&bno="+bno;
			$.ajax({
				type: "post",
				dataType: "json",
				url: "${path}/reply/insert.do",
				data: param,
				success: function(){
					alert("댓글이 등록되었습니다.");
					//listReply2();
				}
			});
		});
*/
</script>



<body>
<!-- wrapper -->
	<!-- Container -->
	<div class="Container">
	<!-- contents -->
	<form:form commandName="BoardVO" id="frm" name="frm" >
	<input type="hidden" id="title" name="title" value="${vo.title}"/>
	<input type="hidden" id="rgtId" name="rgtId" value="${vo.rgtId}"/>
	<input type="hidden" id="rgtDtm" name="rgtDtm" value="${vo.rgtDtm}"/>
	<input type="hidden" id="contents" name="contents" value="${vo.contents}"/>
	<input type="hidden" id="boardId" name="boardId" value="${vo.boardId}"/>
	<input type="hidden" id="scrYn" name="scrYn" value="${vo.scrYn}"/>
	<input type="hidden" id="scrPw" name="scrPw" value="${vo.scrPw}"/>
	<input type="hidden" id="idx" name="idx" value="${vo.idx}"/>
	<input type="hidden" id="readCnt" name="readCnt" value="${vo.readCnt}"/><%-- 
	<input type="text" id="srchKeyword" name="srchKeyword" value="${vo.srchKeyword }"/>
	<input type="text" id="srchtrg" name="srchtrg" value="${vo.srchtrg }"/> --%>
	<table width="700px;" id="listtable" name="listtable"  summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
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
			<td>${vo.title }</td>
			<th scope="col">작성자</th>
			<td>${vo.rgtId }</td>
		</tr>
		<tr>
			<th scope="col">내용</th>
			<td colspan="3"><pre>${vo.contents }</pre></td>
		</tr>
		<tr>
			<th scope="col">첨부파일</th>
			<td colspan="3"></td>
		</tr>
		<tr>
			<td colspan="2" align="left">
				<input type="button"class="button"  id="" name="" value="목록으로"  onclick="fn_movePage('boardList.do')"/>
			</td>
			<td colspan="2" align= "right">
				<input type="button" class="button" id="" name="" value="수정"  onclick="fn_movePage('boardUpdList.do','frm','Y');"/>
			</td>
		</tr>
		<!-- Paging -->
	</tbody>
	</table>
	</form:form>
	<!-- //contents -->
	</div>
	<!-- //Container -->
	
	<!-- // header -->
	
	<!-- 댓글작성 입력폼 -->
	<form action="./replyInsert.do" method="post">
		<div style="width:650px; text-align: center;">
			<br>
			<input type="text" name="idx" value="${vo.idx}">
			<textarea rows="5" cols="80" name="contents" id="contents" placeholder="댓글을 작성해주세요"></textarea>
			<br>
			<input type="submit">댓글 작성</button>
			
		
		</div>
	</form>
	
<!-- //wrapper -->
</body>

</html>