<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>kwboardList</title>
</head>
<script src="//code.jquery.com/jquery.min.js"></script>

<body>
	<br/>
	<table align="center">
		<tr>
			<td><input type="text"><input type="button" value="검색"></td>
		</tr>
	</table>
	<br/>
	<table align="center" border=1px>
		<tr>
			<td>선택</td>
			<td>글번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>조회수</td>
			<!-- <td>첨부파일</td>-->
			<td>등록자</td>
			<td>등록시간</td>
		</tr>
		<c:forEach var="boardVOArr" items="${boardVOArr}">
			<tr>
				<td><input type="checkbox" name="idx" value="${boardVOArr.idx}"></td>
				<td>${boardVOArr.idx}</td>
				<td>${boardVOArr.title}</td>
				<td>${boardVOArr.contents}</td>
				<td>${boardVOArr.readCnt}</td>
				<!-- <td>첨부파일</td> -->
				<td>${boardVOArr.rgtId}</td>
				<td>${boardVOArr.rgtDtm}</td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	
	<table align="center">
		<tr>
			<td><input type="button" value="등록" onclick="fn_movePage('kwboardRegister.do');"></td>
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
	function fn_movePage(actionUrl){
		location.replace(actionUrl);
	}

	function fn_delboard(){
		var idx = "";
		var idxArray = new Array();
		$("input[name=idx]:checked").each(function() { 
			idx = $(this).val();
			idxArray.push(idx);
		});

		console.log(idxArray);

        var objParams = {
                "idxArray" : idxArray
            };
		
		$.ajax({
		    type: "POST",
		    url: "./kwboardDelete.do",
            dataType : "json",
            //contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    data: "idxArray=" + idxArray,
		    success: function(data){
			    console.log("성공");
		    },

		    error: function (request, status, error){    
			    console.log("실패");
		    }
		  });
	}

</script>