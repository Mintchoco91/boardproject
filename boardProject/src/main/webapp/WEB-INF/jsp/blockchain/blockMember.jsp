<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<script type="text/javascript">
function blockMemberRegister(){
	var log = document.blockmember;
	log.action="blockMemberRegister.do";
	var userId=document.blockmember.userId.value;
	var pw=document.blockmember.pw.value;
	log.submit();
}
function blockMemberLogin(){
	var log=document.blockmember;
	log.action="blockMemberLoginCheck.do";
	var userId=document.blockmember.userId.value;
	var pw=document.blockmember.pw.value;
	log.submit();
}
</script>

<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form name="blockmember" id="blockmember" method="post">
	<table align="center" border=1px class="inputClass">
		<tr>
			<td>ȸ�� ���̵�</td>
			<td><input type="text" name="userId" id="userId"></td>
		</tr>
		<tr>
			<td>��й�ȣ</td>
			<td><input type="password" name="pw" id="pw"></td>
		</tr>
	</table>
	<br/>
	<table align="center">
		<tr>
			<td align="center"> <input type="button" value="����" onClick="blockMemberRegister()"></td>
			<td align="center"> <input type="button" value="�α���" onClick="blockMemberLogin()"></td> 
		</tr>
	</table>
</body>
</html>