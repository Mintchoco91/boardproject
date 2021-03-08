<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 공통으로 사용되는 taglib 선언 -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <title>멤버 상세조회</title>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.min.css">
<!-- common.css의 경우 추후 디자인 분리 작업이 진행 될 경우 협의 후 참조 여부 결정이 필요합니다. -->
<link rel="stylesheet" type="text/css" href="/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="/resources/css/layout.css">
<link rel="stylesheet" type="text/css" href="/resources/css/ui.css">
<link rel="shortcut icon" href="/images/favicon.png">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

function fnDaumPostCode() {
new daum.Postcode({
	oncomplete : function(data){
	    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

        // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
        var roadAddr = data.roadAddress; // 도로명 주소 변수
        var extraRoadAddr = ''; // 참고 항목 변수

        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
            extraRoadAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if(data.buildingName !== '' && data.apartment === 'Y'){
           extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if(extraRoadAddr !== ''){
            extraRoadAddr = ' (' + extraRoadAddr + ')';
        }

        // 우편번호와 주소 정보를 해당 필드에 넣는다.
        document.getElementById('postCode').value = data.zonecode;
        document.getElementById("roadAdr").value = roadAddr;
        document.getElementById("jibunAdr").value = data.jibunAddress;
        
        // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
        if(roadAddr !== ''){
            document.getElementById("extraAdr").value = extraRoadAddr;
        } else {
            document.getElementById("extraAdr").value = '';
        }

        var guideTextBox = document.getElementById("guide");
        // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
        if(data.autoRoadAddress) {
            var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
            guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
            guideTextBox.style.display = 'block';

        } else if(data.autoJibunAddress) {
            var expJibunAddr = data.autoJibunAddress;
            guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
            guideTextBox.style.display = 'block';
        } else {
            guideTextBox.innerHTML = '';
            guideTextBox.style.display = 'none';
        }
        
        console.log(data.address);
        console.log(data.addressType);
        console.log(data.userSelectedType);
        console.log(data.roadAddress);
        console.log(data.jibunAddress);
        console.log(data.buildingCode);
        console.log(data.sido);
        console.log(data.sigungu);
        console.log(data.sigunguCode);
        console.log(data.roadnameCode);
        console.log(data.bcode);
        console.log(data.bname);
        console.log(data.sido);
      
         document.getElementById("bdMgtSn").value = data.buildingCode;
         document.getElementById("adrType").value = data.addressType;
        document.getElementById("usrSelType").value =data.userSelectedType; 
        document.getElementById("selYn").value =data.noSelected;
      	document.getElementById("bdMgtNm").value = data.buildingName; 
        document.getElementById("sigunguSn").value =data.sigunguCode;
         document.getElementById("sigungu").value = data.sigungu;
        document.getElementById("roadNm").value= data.roadname; 
      	document.getElementById("bdNm").value = data.bname;
        document.getElementById("roadSn").value = data.roadnameCode;
        document.getElementById("bdSn").value = data.bcode;
        document.getElementById("bdNm1").value =data.bname1;
        document.getElementById("bdNm2").value = data.bname2;
        document.getElementById("hNm").value =data.hname; 
        document.getElementById("sido").value = data.sido;  
         
  
    }
}).open();
}

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
 
	/* function fnChkId() {
		alert("ddd");
		var form = document.usrAcntForm;
		alert(form.usrId.value);
		alert($('#usrId').val());
	} */

	function fnRgt() {
		var form= document.usrAcntForm;
		form.submit();
	}
</script>
<body>
<!-- START : HEADER -->
<header id="hd">
    <!-- START : HEADER TOP -->
    <div class="hdTop">
        <!-- START : HEADER TOP CONTAINER -->
        <div class="innerContainer">
            <!-- START : 다국어 버튼 영역 -->
            <ul class="global">
                <li><a href="#" class="en" title="영어페이지로 이동" onclick="javascript:headerMenu('/sm/ovs/SmTopEnglishInformation.do?guideCd=0000010001&amp;guideYn=Y','080000000002','N');">ENGLISH</a></li>
				<li><a href="#" class="cn" title="중국어페이지로 이동" onclick="javascript:headerMenu('/sm/ovs/SmTopChineseInformation.do?guideCd=0000010001&amp;guideYn=Y','080000000009','N');">中國語</a></li>
				<li><a href="#" class="jp" title="일본어페이지로 이동" onclick="javascript:headerMenu ('/sm/ovs/SmTopJapaneseInformation.do?guideCd=0000010001&amp;guideYn=Y','080000000014','N');">日本語</a></li>
            </ul>
            <!-- END : 다국어 버튼 영역 -->

            <!-- START : 사이트 내 전체검색 영역 -->
            <div class="hdSearchWrap">
                <fieldset>
                    <legend>사이트 내 전체검색</legend>
                    <form id="unifSrch" name="unifSrch" action="/cs/CsBltnWrt.do" method="post" onsubmit="return false;">
                        <input type="hidden" name="fg" value="Y">
			            <input type="hidden" name="inqTrgt" value="02">
			            <input type="hidden" name="inqType" value="03">
                        <label for="srchWord" class="hidden">검색어 필수</label>
                        <input type="text" name="srchWord" id="srchWord" class="keyword" placeholder="검색어를 입력하세요." onfocus="javascript:clearSrch(this);">
                        <button type="submit" name="button" class="btnSearch" onclick="javascript:unifSrchView();"><span class="hidden">검색</span></button>
                    </form>
                </fieldset>
            </div>
            <!-- END : 사이트 내 전체검색 영역 -->
        </div>
        <!-- END : HEADER TOP CONTAINER -->
    </div>
    <!-- END : HEADER TOP -->

    <!-- START : HEADER WRAP -->
    <div class="hdWrap" id="top_menu">
		<!-- START : HEADER WRAP CONTAINER -->
        <div class="innerContainer" id="topmenu">
			<h1 id="logo"><a href="#" onclick="javascript:goToMain();"><img src="/images/logo.png" alt=""></a></h1>
            <!-- START : GNB -->
            <nav id="gnb">
				<h2 id="topmenuNm" class="hidden">주메뉴</h2>
				


<!-- 메뉴3단계 E3.ul 체크 변수--> 

<ul>
	<!-- for start : 1단계 메뉴  -->
	 
		<!-- S1.li -->
		<li>

			<!-- 1단계 메뉴 현출 -->
		 	<a id="top1m01" href="#_">증명서발급</a> 
			<!-- S2.div -->
			<div class="gnbSubWrap gnbSub1">
				<!-- S2.ul -->
				<ul>
					<!-- for start : 메뉴2단계 -->
							<!-- S2.li -->
							<li> 
								<a href="#" onclick="javascript:headerMenu('/pt/PtFrrpApplrInfoInqW.do?menuFg=01','010000000002','N');">가족관계등록부</a>
												<!-- S3.ul -->
									<ul>
											<li><a href="#_" onclick="javascript:headerMenu('/pt/PtFrrpApplrInfoInqW.do?menuFg=02','010000000003','N');">가족관계증명서</a></li>
											<li><a href="#_" onclick="javascript:headerMenu('/pt/PtFrrpApplrInfoInqW.do?menuFg=03','010000000004','N');">기본증명서</a></li>
											<li><a href="#_" onclick="javascript:headerMenu('/pt/PtFrrpApplrInfoInqW.do?menuFg=04','010000000005','N');">혼인관계증명서</a></li>
											<li><a href="#_" onclick="javascript:headerMenu('/pt/PtFrrpApplrInfoInqW.do?menuFg=05','010000000006','N');">입양관계증명서</a></li>
											<li><a href="#_" onclick="javascript:headerMenu('/pt/PtFrrpApplrInfoInqW.do?menuFg=06','010000000007','N');">친양자입양관계증명서</a></li>
									</ul>
											<!-- E3.ul -->
							</li>
							<!-- E2.li -->
								<script type="text/javascript">
								<!-- 1단계로 올리기 위한 강제 스크립트 실행 -->
								if(firstMenuChk){
									var trgtId = "top1m01";
									$("#"+trgtId).click(function(){
										var prgUrl = '/pt/PtFrrpApplrInfoInqW.do?menuFg=01';
										prgUrl = prgUrl.replace(/&amp;/g, "&"); 
									    headerMenu(prgUrl,'010000000002','N');
									});
								}
								</script>
							<!-- S2.li -->
							<li> 
								<a href="#" onclick="javascript:headerMenu('/pt/PtEngFrrpApplrInfoInqW.do?menuFg=08','010000000009','N');">영문증명서</a>
							</li>
							<!-- E2.li -->
								<!-- S2.li -->
							<li> 
								<a href="#" onclick="javascript:headerMenu('/pt/PtJjkpApplrInfoInqW.do?menuFg=09','010000000010','N');">제적부</a>
								
										<!-- S3.ul -->
												<ul>
											
											<li><a href="#_" onclick="javascript:headerMenu('/pt/PtJjkpApplrInfoInqW.do?menuFg=10','010000000011','N');">제적등본</a></li>
												
											<li><a href="#_" onclick="javascript:headerMenu('/pt/PtJjkpApplrInfoInqW.do?menuFg=11','010000000012','N');">제적초본</a></li>
										
									  </ul>
	</header>								  
									  
			</ul>
				<!-- E2.ul -->
			</div>
			<!-- E2.div -->
			<!-- 2단계 메뉴 건수 초기화 -->

			<!-- 2단계 all block 시 -->
            
		</li>
		<!-- E1.li -->
	
	<!-- for end : 1단계 메뉴  -->
	<!-- START : 외국인 안내 페이지 메뉴 -->
	
	<!-- END : 외국인 안내 페이지 메뉴 -->
</ul>
<!-- E1.ul -->  

            </nav>            
        </div>
        <!-- END : HEADER WRAP CONTAINER -->
    </div>
    <!-- END : HEADER WRAP -->
	<form id="headerMn" name="headerMn" action="/cs/CsBltnWrt.do" method="post" onsubmit="return false;"></form>      
</header>
<!-- END : HEADER -->
	<!-- END : top호출 -->

    <!-- START : CONATINER -->
    <div id="container">
        <!-- START : side navigation호출 -->

<!-- END : side navigation호출 -->
<%@include file="../cm/sideNav.jsp" %>
	<!-- 	
        <!-- START : CONTENT -->
        <section id="content" tabindex="-1">
        	
        	<!-- START : 이전 파라미터 FORM -->
			<form id="csBltnWrtVO" name="listForm" action="/cs/CsBltnWrt.do" method="post" onsubmit="return false;">
				<input id="bltnbordId" name="bltnbordId" type="hidden" value="0000001">
				<input id="bltnId" name="bltnId" type="hidden" value="000000120210202000001">
				<input id="inqType" name="inqType" type="hidden" value="">
				<input id="srchKeywd" name="srchKeywd" type="hidden" value="">
				<input id="pageIndex" name="pageIndex" type="hidden" value="1">
				<input id="inqOrder" name="inqOrder" type="hidden" value="">
			</form>
			<!-- END : 이전 파라미터 FORM -->
			
			<!-- START : 첨부파일 FORM -->
			<form id="csAtchfileVO" name="atchfileForm" action="/cs/CsBltnWrt.do" method="post">
				<input id="bltnbordId" name="bltnbordId" type="hidden" value="0000001">
				<input id="bltnId" name="bltnId" type="hidden" value="000000120210202000001">
				<input id="atchfileId" name="atchfileId" type="hidden" value="">
				<input id="atchfileNm" name="atchfileNm" type="hidden" value="">
			</form>
			<!-- END : 첨부파일 FORM -->
        	
            <!-- START : 네비게이션 호출 -->
<div class="breadclumbs">
    <ul>
        <li>홈</li>
        <li>고객센터</li>
        <li class="current">공지사항</li>
    </ul>
</div>
			<!-- END : 네비게이션 호출 -->
            <!-- START : PAGE CONTENT -->
            <form id="usrAcntVO" name="usrAcntForm" action="/um/usrAcntRgt.do" method="post" onsubmit="return false;">
            <input type="hidden" id="bdMgtSn" name="bdMgtSn" value=""/>
            <input type="hidden" id="adrType" name="adrType" value=""/>
            <input type="hidden" id="usrSelType" name="usrSelType" value=""/>
            <input type="hidden" id="selYn" name="selYn" value=""/>
            <input type="hidden" id="bdMgtNm" name="bdMgtNm" value=""/>
            <input type="hidden" id="sido" name="sido" value=""/>
            <input type="hidden" id="sigungu" name="sigungu" value=""/>
            <input type="hidden" id="sigunguSn" name="sigunguSn" value=""/>
            <input type="hidden" id="roadSn" name="roadSn" value=""/>
            <input type="hidden" id="bdSn" name="bdSn" value=""/>
            <input type="hidden" id="roadNm" name="roadNm" value=""/>
            <input type="hidden" id="bdNm" name="bdNm" value=""/>
            <input type="hidden" id="bdNm1" name="bdNm1" value=""/>
            <input type="hidden" id="bdNm2" name="bdNm2" value=""/>
            <input type="hidden" id="hNm" name="hNm" value=""/>
            <div class="pageContent">
                <h2 class="pgTitle">기본정보</h2>
				<!-- START : 공지사항 상세조회 -->
				<div class="tblForm">
                    <table>
                        <caption>회원가입 폼</caption>
                        <colgroup>
	                        <col style="width: 15%;">
	                        <col>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row"><label>아이디</label></th>
                                <td class="fwB"><input type="text" name="usrId" id="usrId"/>
                                <a href="#none" title="아이디중복확인(새창으로 열기)" onclick="javascript:fnChkId();" class="cssbtn btn_gray btn_idcheck">아이디중복확인</a>
                                 (영문소문자/숫자, 4~16자)</td>
                            </tr>
                            <tr>
                                <th><label>비밀번호</label></th>
                                    <td class="fwB"><input type="password" name="usrPw" id="usrPw"/></td>
                            </tr>
                            <tr>
                                <th><label>비밀번호 확인</label></th>
                                   <td class="fwB"><input type="password" name="usrPwConfirm" id="usrPwConfirm"/></td>
                            </tr>
                            <tr>
                                <th><label>이름</label></th>
                              <td class="fwB"><input type="text" name="usrNm" id="usrNm"/>
                            </tr>
                             <tr>
                                <th><label>주소</label></th>
                                    <td class="fwB">
                                <p>  <input type="text" name="postCode" id="postCode" placeholder="우편번호"  style="width:68px;"/>
                                    <input type="button" onclick="fnDaumPostCode()" value="우편번호 찾기"/><br></p>  
                                    <input type="text" name="roadAdr" id="roadAdr" placeholder="도로명주소" style="width:280px;"/><br>
                                 	 <input type="text" name="jibunAdr" id="jibunAdr" placeholder="지번주소" style="width:280px;"/>
                                 	 	 <input type="text" name="extraAdr" id="extraAdr" placeholder="" style="width:68px;"/>
                     			       <span id="guide" style="color:#999;display:none"></span>
                                 	 <input type="text" name="dtlAdr" id="dtlAdr" placeholder="상세주소" style="width:120px;"/>
                                 
                                 </tr>
                              <tr>
                                <th><label>휴대전화</label></th>
					             <td class="fwB"><div class="frmDiv">
                                    <select name="usrTel1" id="usrTel1" class="frmInput">
                                        <option value="" selected="">010</option>
                                        <option value="Y">사용</option>
                                        <option value="N">불가</option>
                                    </select> -
                                     							<input type="text" name="usrTel2" id="usrTel2"/> - 
                                     							<input type="text" name="usrTel3" id="usrTel3"/>
                                </div> 
                                 </tr>
                          <tr>
                                <th><label>이메일</label></th>
                                <td class="fwB"><input type="text" name="usrEmail1" id="usrEmail1"/> @ 
                                     						<input type="text" name="usrEmail2" id="usrEmail2"/> 
                                 </tr>
                        </tbody>
                    </table>
                </div>
                <!-- END : 공지사항 상세조회 -->
                </form>
                <!-- START : 메인버튼 -->
                <div class="formBtn">
                    <button type="button" name="button" onclick="javacript:fnRgt();">확인</button>
                    <button type="button" name="button" title="새창" class="nw2" onclick="javacript:fnOpenDtlInqPrint();">출력</button>
                </div>
                <!-- END : 메인버튼 -->
            </div>
            <!-- END : PAGE CONTENT -->
        </section>
        <!-- END : CONTENT -->
    </div>
    <!-- END : CONATINER -->
    
    <!-- START : footer호출 -->
	<script type="text/javaScript" defer="defer">
//하단 팝업 호출
function fn_popUp(url, height, width) {
    windowOpen(url, 'bottomCmP', height, width, 'yes');
}
</script>
<!-- START : FOOTER -->
<!-- <footer id="ft">
    <h2 class="hidden">푸터영역</h2>
	<div class="innerContainer">
		 START : 지원센터 안내
            <div class="csWrap">
                <h3 class="csTitle"><strong>사용자지원센터</strong>
					일반 통화요금 부과 (별도 정보이용료 없음)</h3>
				<p class="csCall mo-hidden">1899-2732, 031-776-7878</p>
                <p class="csCall mo-visible"><a href="tel:1899-2732">1899-2732</a>, <a href="tel:031-776-7878">031-776-7878</a></p>
                
                <span class="csInfo">월~금요일 (09:00~18:00) / 토 · 일요일 및 공휴일은 휴무</span>
            </div>
            END : 지원센터 안내

        <div class="copyWrap">
            <div class="ftLink">
            	<h3 class="hidden">하단 링크</h3>
                <ul>
                    <li><a href="javascript:fn_popUp('/sm/SmBottomAgreement.do','900','600');" class="nw" title="새창">이용약관</a></li>
                    <li><a href="javascript:fn_popUp('/sm/SmBottomIndiInfoProcPolic.do','900','600');" class="nw tBlack" title="새창">개인정보 처리방침</a></li>
                    <li><a href="javascript:fn_popUp('/sm/SmBottomCopyProtecPolic.do','900','600');" class="nw" title="새창">저작권 보호 정책</a></li>
                    <li><a href="javascript:fn_popUp('/sm/SmBottomLinkMatterAttend.do','900','600');" class="nw" title="새창">링크 시 유의사항</a></li>
                    <li class="mo-hidden"><a href="http://efamilyrs.scourt.go.kr" title="새창" target="_blank">원격지원</a></li>
                </ul>
            </div>
            <div class="copy">
                <p>Copyright©Supreme Court of Korea. All Rights reserved.</p>
            </div>
        </div>
    </div>
</footer> -->
<%@include file="../cm/footer.jsp" %>
<!-- END : FOOTER -->
	<!-- END : footer호출 -->
</div>
<!-- END : WRAP -->
<form id="tempF" action="/cs/CsBltnWrtRpt.do" method="post" target="CsNfcMttrListDtlInqRpt"><input type="hidden" name="bltnbordId" value="0000001">
<input type="hidden" name="bltnId" value="000000120210202000001">
<input type="hidden" name="inqType" value=""><input type="hidden" name="srchKeywd" value="">
<input type="hidden" name="pageIndex" value="1"><input type="hidden" name="inqOrder" value="">
</form>
</body>
								
</body>