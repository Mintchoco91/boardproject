<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	 --%>      <!-- jstl 제어,대입문 -->
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>
<meta charset="UTF-8">
<title>BOARD</title>
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
tr:hover {
background-color: #fffcde;
cursor :pointer;}

}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<%@ include file="/WEB-INF/js/cm/cm.js" %>
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
	location.href="chboardRegister.do";
/* 	f.action="chboardRegister.do";
	f.submit(); */
}

function fn_Delete() {
	var f=document.frm;
	var data = $('#frm').serialize();
	var msg = confirm("정말 삭제하시겠습니까?");
	if(msg ==true) {
		var checkArr =new Array();
		$("input[type='checkBox']:checked").each(function() {
			checkArr.push($(this).attr("data-cartNum"));
		});
		alert(checkArr);		 
		$.ajax({
			url : "chboardDelete.do",
			type : "post",
			datatype:"text",
			data : {chbox : checkArr },
			success : function(data) {
				if(data ==1) {
				alert("댓글 삭제완료!");
				  location.href = "chboardList.do";
				}
			},
			error : function() {
				alert("삭제 실패");
			}
			
		});
	}else {
		return;
	}
	
}

function ajaxCall(method, url, request) {
	var returnData = new Object;
	$.ajax({
		method : method,
		url : url,
		dataType : 'json',
		data :  JSON.stringify(request),
		processData : true,
		contentType : 'application/json; charset=UTF-8',
		async : false, // default == true  == 비동기 // false == 동기
		success : function(data) {
			if(data ==1) {
				alert("삭제 성공");
			}else {
				alert("삭제 실패");
			}
			
		},
		error :function() {
			alert("실패작");
		}
	});
	return returnData;
};

function fn_selectLine(obj) {
	document.frm.idx.value =obj;
	$.ajax({
		url : 'chboardUpdateReadCnt.do',
		type : "post",
		data : {idx : obj},
		success : function(data) {
			if(data=="1") {
			//	alert("업데이트 성공");
				onSuccess(data);
	//			 location.href = "chboardList.do";
			}
		},
		error : function() {
			alert("접근 실패");
		}
		
	});
	
	/* var str="";
	var tr= $(this);
	var tdArr = new Array();
	var td= tr.children();
	td.each(function(i){
		tdArr.push(td.eq(i).text());		//클릭된 행의 모든값을 한번에 가져오기 : text()
		)}; */
		/* alert(tdArr); */
}
function onSuccess(data) {
	var form = document.frm;
 //	var result = $('#idx').val();
	form.action="Detail.do";
	form.submit();
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
	<form:form commandName="BoardVO" id="frm" name="frm" method="get">
	<table width="700px" class="listTable" id="" name="" method="get" summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
	<input type="hidden" id="idx" name="idx" value=""/>
	<thead>
		<tr >
				<td colspan="7">
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
			<td><input type="checkbox" id="allCheck" name="allCheck" value="" /></td>
			<td>글번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>조회수</td>
			<td>첨부파일</td>
			<td>등록일시</td>
		</tr>
		
		<!-- list.size !=0 -->
		<c:if test="${list.size() == 0 }">
			<tr>
			<td colspan="7" align="center">
				게시물이 없습니다.
			</td>
		</tr>
		</c:if>
		<c:if test="${list.size() !=0 }" >
		<c:forEach var="vo" items="${boardList}">
				<tr>
				<td ><input type="checkbox" id="check" class="chBox" name="check" value="${vo.idx }"   data-cartNum="${vo.idx}" /></td>
				<td  onclick="fn_selectLine(${vo.idx})">${vo.idx }</td>
				<td  onclick="fn_selectLine(${vo.idx})">${vo.title }</td>
				<td  onclick="fn_selectLine(${vo.idx})">${vo.contents }</td>
				<td  onclick="fn_selectLine(${vo.idx})">${vo.readCnt }</td>
				<td></td>
				<td  onclick="fn_selectLine(${vo.idx})">${vo.rgtDtm }</td>
				</tr>
			
		</c:forEach>
		</c:if>
		<tr>
			<td  colspan="7" align="right">
			<input type="button" value="등록" onclick="fn_movePage('chboardInsert.do');">
			<input type="button" value="삭제" onclick="fn_Delete();">
			</td>
		</tr>
	</tbody>
	</table>
	</form:form>
	</div>
	<!-- //Container -->
	    <!-- paging 시작 -->
	<%@ include file="/WEB-INF/jsp/board/boardPaging.jsp" %>
	<!-- paging 끝 -->	
	
	
	<!-- footer -->
	<div class="footer">
	
	
	</div>
	</div>
<!-- //wrapper -->
	
</body>
</html>