<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<script type="text/javascript">
function sendCoin(){
	var log=document.blockSend;
	log.action="sendCoin.do";
	var receiver = document.blockSend.receiver.value;
	var amount=document.blockSend.amount.value;
	var privateKeyString= document.blockSend.privateKeyString.value;
	log.submit();
}
</script>

<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form name="blockSend" id="blockSend" method="post">
	<table align="center" border=1px class="inputClass">
		<tr>
			<td>받는 아이디</td>
			<td><input type="text" name="receiver" id="receiver"></td>
		</tr>
		<tr>
			<td>amount</td>
			<td><input type="text" name="amount" id="amount"></td>
		</tr>
		<tr>
			<td>privateKey</td>
			<td><input type="password" name="prviateKeyString" id="privateKeyString"></td>
		</tr>
	</table>
	<br/>
	<table align="center">
		<tr>
			<td align="center"> <input type="button" value="전달" onClick="sendCoin()"></td> 
		</tr>
	</table>
	</form>
</body>
</html>