<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>
<script>

$(document).ready(function() {
	$('#allCheck').click(function() {
		if($('#allCheck').prop("checked")){
			$("input[type=checkbox]").prop("checked", true);
		}else {
			$("input[type=checkbox]").prop("checked", false);
		}
	});
});


function fn_Register() {
	var f = document.frm;
	location.href="boardRegister.do";
/* 	f.action="boardRegister.do";
	f.submit(); */
}

function fn_Delete() {
	var f=document.frm;
	var data = $('#frm').serialize();
	var arrCnt = 0;
	var checkArr =new Array();
	$("input[type='checkBox']:checked").each(function() {
		checkArr.push($(this).attr("data-cartNum"));
		arrCnt++;
	});

	//글선택 안했을경우
	if(arrCnt == 0) {
		alert("삭제 할 항목을 선택 해 주세요."); 
		return false;
	}

	var msg = confirm("정말 삭제하시겠습니까?");
	if(msg == false) return false;
	
	$.ajax({
		url : "boardDelete.do",
		type : "post",
		datatype:"text",
		data : {chbox : checkArr },
		success : function(data) {
			if(data ==1) {
			alert("글 삭제완료!");
			  location.href = "boardList.do";
			}
		},
		error : function() {
			alert("삭제 실패");
		}
	});
}

function fn_selectLine(obj) {
	document.frm.idx.value =obj;
	$.ajax({
		url : 'boardUpdateReadCnt.do',
		type : "post",
		data : {idx : obj},
		success : function(data) {
			console.log("!!!"+data);
			if(data=="1") {
			//	alert("업데이트 성공");
				onSuccess(data);
	//			 location.href = "boardList.do";
			}
		},
		error : function() {
			alert("접근 실패");
		}
		
	});
}
function onSuccess(data) {
	var form = document.frm;
	form.action="Detail.do";
	form.submit();
}

function chkword(obj, maxlength){
	 var strValue = obj.value;
     var strLen = strValue.length;
     var totalByte = 0;
     var len = 0;
     var oneChar = "";
     var str2 = "";

     for (var i = 0; i < strLen; i++) {
         oneChar = strValue.charAt(i);
         if (escape(oneChar).length > 4) {
             totalByte += 2;
         } else {
             totalByte++;
         }

         // 입력한 문자 길이보다 넘치면 잘라내기 위해 저장
         if (totalByte <= maxlength) {
             len = i + 1;
         }
     }

     // 넘어가는 글자는 자른다.
     if (totalByte > maxlength) {
         alert(maxlength + "자를 초과 입력 할 수 없습니다.");
         str2 = strValue.substr(0, len);
         obj.value = str2;
         chkword(obj, 4000);
     }

}

function fn_enter() {
	if(event.keyCode == 13) {
		fn_movePage('boardList.do','frm','Y');
	}
}

function fn_search() {
	var srchtrg = $("#srchtrg option:selected").val();
	fn_movePage('boardList.do','schfrm','Y');
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
	게시판 > 게시판 리스트
	
	<form:form commandName="BoardVO" id="frm" name="frm"> 
	<table width="700px" class="table" summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
	<input type="hidden" id="idx" name="idx" value=""/>
		<thead class="table-warning">
			<tr>
				<th><input type="checkbox" id="allCheck" name="allCheck" value="" /></th>
				<th>글번호</th>
				<th width="200px">제목</th>
				<th>조회수</th>
				<th width="200px">등록일시</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${list.size() !=0 }">
				<c:forEach var="vo" items="${boardList}">
						<tr class="selectline">
						<td><input type="checkbox" id="check" class="chBox" name="check" value="${vo.idx }"   data-cartNum="${vo.idx}" /></td>
							<td onclick="fn_selectLine(${vo.idx})">${vo.idx }</td>
							<td onclick="fn_selectLine(${vo.idx})">${vo.title }</td>
							<td onclick="fn_selectLine(${vo.idx})">${vo.readCnt }</td>
							<td onclick="fn_selectLine(${vo.idx})">${vo.rgtDtm }</td>
						</tr>
				</c:forEach>
			</c:if>		
			<c:if test="${list.size() == 0 }">
				<tr>
					<td colspan="7" align="center">
						조회된 게시물이 없습니다.
					</td>
				</tr>
			</c:if>
		</tbody>

		<tr>
			<td  colspan="7" align="right">
			<input type="button" class="btn btn-default" value="등록" onclick="fn_movePage('boardRegister.do');">
			<input type="button" class="btn btn-default" value="삭제" onclick="fn_Delete();">
			</td>
		</tr>
	
	</tbody>
	</table>
	</form:form>
	
	<!-- paging 시작 -->
	<%@ include file="/WEB-INF/jsp/board/boardPaging.jsp" %>
	<!-- paging 끝 -->	
		
 	<form class="form-inline" id="schfrm" name="schfrm" action="#" method="get">
 	<table align="center">
			<tr>
				<td>
					<input type="hidden" name="curPage" value="${pagination.curPage}">
					<select class="form-control" id="srchtrg" name="srchtrg" onchange="" >
						<option value="title" <c:if test="${ BoardVO.srchtrg eq 'title'}"> selected</c:if>>제목</option>
						<option value="contents" <c:if test="${BoardVO.srchtrg eq 'contents'}">selected</c:if>>내용</option>
					</select>
					<input type="text" class="form-control" id="srchKeyword" name="srchKeyword" value="${BoardVO.srchKeyword}">
					<button class="btn btn-default" onclick="fn_search(this)" onkeypress="fn_enter()">검색</button>
				</td>
			</tr>
		</table>
	</form>
	
	</div>
	<!-- //Container -->
	
	<!-- footer -->
	<%@include file="/WEB-INF/jsp/cm/footer.jsp" %>
	<!-- footer 끝 -->
	
	</div>
<!-- //wrapper -->

</body>
</html>