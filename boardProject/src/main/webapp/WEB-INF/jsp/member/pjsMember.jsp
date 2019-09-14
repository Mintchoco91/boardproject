<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/cm/header.jsp" %>

<div class="layer_fixed">
<script type="text/javascript">
function memberModify(){
	var log = document.member;
	log.action="memberModify.do";
	var pw=document.member.pw.value;
	var name = document.member.name.value;
	var email1 = document.member.email1.value;
	if(name=="") alert("아이디를 입력해주세요");
	else if(email1=="") alert("이메일을 입력해주세요");
	else if(pw=="") alert("비밀번호를 입력해주세요");
	else log.submit();
}
function memberDelete(){
	var log=document.member;
	log.action="";
	log.submit();
}
</script>
<style>
.inputClass td{
padding-left: 5px;
padding-right: 5px;
}
</style>

	<br/>
	<br/>
	<form:form name="member" method="post">
	<table align="center" border=1px class="inputClass">
		<tr>
			<td>회원 아이디</td>
			<td>${memberVO.userId }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw" id="pw"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id="name" value="${memberVO.name }"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email1" id="email1" value="${memberVO.email1 }"></td>
		</tr>
	</table>
	<br/>
	<table align="center">
		<tr>
			<td align="center"> <input type="button" value="정보수정" onClick="memberModify()"></td>
			<td align="center"> <input type="button" value="회원탈퇴" onClick="memberDelete()"></td> 
		</tr>
	</table>
	<c:if test="${not empty resultMessage}">
		<table align="center">
			<tr>
				<td colspan="2"><font color=red>${resultMessage }</font></td>
			</tr>
		</table>
	</c:if>
	</form:form>
	
</div>	
<%@include file="/WEB-INF/jsp/cm/footer.jsp" %>