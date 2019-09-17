<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
</head>
<body>
<!-- 네이버아이디로로그인 버튼 노출 영역 -->
<div id="naverIdLogin"></div>
<!-- //네이버아이디로로그인 버튼 노출 영역 -->

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "9iK41XPuMNKiA_HdjHl_",
			callbackUrl: "http://localhost:8808/member/callback.do",
			isPopup: false, /* 팝업을 통한 연동처리 여부 */
			loginButton: {color: "green", type: 3, height: 60} /* 로그인 버튼의 타입을 지정 */
		}
	);
	
	/* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
	naverLogin.getLoginStatus(function (status) {
		if (status) {
			var email = naverLogin.user.getEmail();
			var name = naverLogin.user.getNickName();
			var profileImage = naverLogin.user.getProfileImage();
			var birthday = naverLogin.user.getBirthday();			
			var uniqId = naverLogin.user.getId();
			var age = naverLogin.user.getAge();
			console.log(email, name, uniqId);
		} else {
			console.log("AccessToken이 올바르지 않습니다.");
		}
	});
	
</script>
<!-- // 네이버아이디로로그인 초기화 Script -->
</body>
</html>