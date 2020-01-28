<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<script type="text/javascript">
function startMine() {
	var log = document.Mining;
	log.action="startMine.do";
	var privateKeyString = document.Mining.privateKeyString.value;
	log.submit();
}
function stopMine(){
	var log = document.Mining;
	log.action="stopMine.do";
	log.submint();
}
</script>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<a href="blockMember.do">blockMember</a><br/>
	<a href="blockSend.do">blockSend</a><br/>
	<a href="blockBoard.do">blockBoard</a><br/>
	<form id="Mining" name="Mining" method="post">
		<input type="password" id="privateKeyString" name="privateKeyString" width="300px">
		<button onclick="startMine()">startMine</button>
		<button onclick="stopMine()">stopMine</button>
	</form>
</body>
</html>