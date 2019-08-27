<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>kwboardList</title>
</head>
<script src="//code.jquery.com/jquery.min.js"></script>
<%@ include file="/WEB-INF/js/cm/cm.js" %>

<body>
	<h1 align="center">리스트페이지</h1>
	<br/>
	<table align="center">
		<tr>
			<td><input type="text"><input type="button" value="검색"></td>
		</tr>
	</table>
	<br/>
	<table align="center" border=1px>
		<tr style="background-color:#E0E6F8;text-align:center">
			<td style="width:40px;">선택</td>
			<td style="width:60px;">글번호</td>
			<td style="width:600px;">제목</td>
			<td style="width:60px;">글쓴이</td>
			<td style="width:60px;">작성일</td>
		 	<td style="width:40px;">조회</td>
			<!-- <td>첨부파일</td>-->
		</tr>
		<c:forEach var="boardVOArr" items="${boardVOArr}">
			<tr onmouseover="this.style.background='#E0ECF8'" onmouseout="this.style.background='white'">
				<td style="width:40px;text-align:center"><input type="checkbox" name="idx" value="${boardVOArr.idx}"></td>
				<td style="width:60px;text-align:center;cursor:hand;" onclick="fn_movePage('kwboardDetail.do?idx=${boardVOArr.idx}')">${boardVOArr.idx}</td>
				<td style="width:600px;cursor:hand" onclick="fn_movePage('kwboardDetail.do?idx=${boardVOArr.idx}')">${boardVOArr.title}</td>
				<td style="width:60px;text-align:center";cursor:hand" onclick="fn_movePage('kwboardDetail.do?idx=${boardVOArr.idx}')">${boardVOArr.rgtId}</td>
				<td style="width:60px;text-align:center";cursor:hand" onclick="fn_movePage('kwboardDetail.do?idx=${boardVOArr.idx}')">${boardVOArr.rgtDtm}</td>
				<td style="width:40px;text-align:center;cursor:hand" onclick="fn_movePage('kwboardDetail.do?idx=${boardVOArr.idx}')">${boardVOArr.readCnt}</td>
				<!-- <td>첨부파일</td> -->
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	
	<table align="center">
		<tr>
			<td><input type="button" value="등록" onclick="fn_movePage('kwboardWritePage.do');"></td>
			<td><input type="button" value="삭제" onclick="fn_delboard();"></td>
			
		</tr>
	</table>
	
	<br/>
	
	<table align="center">
		<tr>
			<td>[미구현]페이징 구역</td>
		</tr>
	</table>
</body>
</html>

<script>
	function fn_delboard(){
		var idx = "";
		var idxArray = new Array();
		$("input[name=idx]:checked").each(function() { 
			idx = $(this).val();
			idxArray.push(idx);
		});

		if(idxArray.length == 0){
			alert("삭제 할 게시물을 선택 해 주세요");
			return false;
		}else{			
	        if(confirm("해당 게시물을 삭제 하시겠습니까?")) {}else{
	            return false;
	        }
		}
		
		$.ajax({
		    type: "POST",
		    url: "./kwboardDelete.do",
            dataType : "TEXT",            
		    data: "idxArray=" + idxArray,
		    success: function(data){
			    if(data == "success"){
				    alert("게시물이 삭제 되었습니다.");
			    	location.reload();
			    }else{
			    	alert("삭제 실패하였습니다. 로그를 확인해보세요.");
				}
		    },

		    error: function (request, status, error){    
			    alert("ajax 실패");
		    }
		  });
	}

</script>