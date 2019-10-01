<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/cm/common.jsp" %>
<style>

	.Container {
	width : 1400px;
height : 500px; 
padding : 30px;
	}

	.listTable {
	
	text-align: center;
	}
	.wrapper {
	 width: 1400px;
  margin: 0 auto;
  
}
 
.selectline:hover {
background-color: #fffcde;
cursor :pointer;}



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

.searchBox {
	float : right;
	width : 800px;
	margin-bottom:  30px;
	text-align: center;
}

form {

margin :0 auto;
padding  0;}
</style>

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
		async : false, // default == true  == 동기 // false == 비동기
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
			console.log("!!!"+data);
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


function fn_search() {
	var f= document.schfrm;
	var obj= f.serialize();
	
	if(schtext !=null) {
		$.ajax({
			url : 'chboardSchBoard.do',
			data : {searchList: obj },
			success : function(data) {
				
			},
			error : function() {
				alert("접근 실패");
			}
			
		});
	}
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
</script>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<!-- wrapper -->
	<div class="wrapper">

	<!-- header -->
	<div class="header">
	
	</div>
	<!-- Container -->
	<div class="Container">
	<form:form commandName="BoardVO" id="schfrm" name="schfrm" >
	<div class="searchBox">
	<select id="schsel" name="schsel" onchange="fn_initValue()">
		<option value="title">제목</option>
		<option value="contents">내용</option>
	</select>
		<input type="text" id="schtext" name="schtext" value="${schtext }"/>
		<input type="button" id="sch" name="sch" value="검색" onclick="fn_search()" onkeydown=""/>
		</div>
	</form:form>
	
	<form:form commandName="BoardVO" id="frm" name="frm">
	<table width="700px" class="listTable" id="" name=""  summary="게시물입니다" border="1" cellspacing="0" cellpadding="5" align="center">
	<input type="hidden" id="idx" name="idx" value=""/>
	<thead>
	</thead>
	<tbody>
		<tr>
			<td><input type="checkbox" id="allCheck" name="allCheck" value="" /></td>
			<td>글번호</td>
			<td>제목</td>
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
				<tr class="selectline">
				<td ><input type="checkbox" id="check" class="chBox" name="check" value="${vo.idx }"   data-cartNum="${vo.idx}" /></td>
				<td  onclick="fn_selectLine(${vo.idx})">${vo.idx }</td>
				<td onclick="fn_selectLine(${vo.idx})">${vo.title }</td>
				<td  onclick="fn_selectLine(${vo.idx})">${vo.readCnt }</td>
				<td ></td>
				<td  onclick="fn_selectLine(${vo.idx})">${vo.rgtDtm }</td>
				</tr>
			
		</c:forEach>
		</c:if>
		<tr>
			<td  colspan="7" align="right">
			<input type="button" class="button" value="등록" onclick="fn_movePage('chboardRegister.do');">
			<input type="button" class="button" value="삭제" onclick="fn_Delete();">
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
	</div>
<!-- //wrapper -->
	<jsp:include page="../footer.jsp"/>
</body>
</html>