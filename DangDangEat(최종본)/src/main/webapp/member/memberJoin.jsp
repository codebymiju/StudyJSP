<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/styles.css" rel="stylesheet" /> 
<title></title>
<style type="text/css">
@font-face {
    font-family: 'GmarketSans';
    font-weight: 300;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.eot?#iefix') format('embedded-opentype'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.woff2') format('woff2'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.woff') format('woff'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansLight.ttf') format("truetype");
    font-display: swap;
} 
*{
    box-sizing: border-box; /*전체에 박스사이징*/
    outline: none; /*focus 했을때 테두리 나오게 */
}

   @font-face { /*지마켓 산스*/
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}    

body {

}

input::placeholder  {
	font-family: "GmarketSans";
}

body{
    font-family: 'GmarketSans';
    font-size:14px;
    background-color: #f5f6f7;
    line-height: 1.5em;
    color : #222;
    margin: 0;
}

a{
    text-decoration: none;
    color: #222;
}

/*member sign in*/
.member{
    width: 400px;
    /* border: 1px solid #000; */
    margin: auto; /*중앙 정렬*/
    padding: 0 20px;
    margin-bottom: 20px;
}

.member .logo{
    /*로고는 이미지라 인라인 블록이니까 마진 오토 안됨 블록요소만 됨 */
    display: block;
    margin :50px auto;
    width: 300px;
}

.member .field{
    margin :5px 0; /*상하로 좀 띄워주기*/
}

.member b{
    /* border: 1px solid #000; */
    display: block; /*수직 정렬하기 */
    margin-bottom: 5px;
}

/*input 중 radio 는 width 가 100%면 안되니까 */
.member input:not(input[type=radio]),.member select{
    border: 1px solid #dadada;
    padding: 15px;
    width: 100%;
    margin-bottom: 5px;
}

.member input[type=button],
.member input[type=submit]{
 	background-color: #cce4a8;
 	color:#999999;
	font-family: "GmarketSans";
	font-weight: bold;
}

.member input:focus, .member select:focus{
    border: 1px solid #2db400;
}

.field.birth div{ /*field 이면서 birth*/
    display: flex;
    gap:10px; /*간격 벌려줄때 공식처럼 사용핟나 */
}

/* .field.birth div > * {  gap 사용한거랑 같은 효과를 줌 
    flex:1;
} */

.field.tel-number div {
    display: flex;
}

.field.tel-number div input:nth-child(1){
    flex:2;
}

.field.tel-number div input:nth-child(2){
    flex:1;
}
input::placeholder  {
   font-family: "GmarketSans";
   font-weight: bold;
}
.field.gender div{
    border: 1px solid #dadada;
    padding: 15px 5px;
    background-color: #fff;
}


.userpw{
    background:url(./images/images2/icon-01.png) no-repeat center right 15px;
    background-size: 20px;
    background-color: #fff;
}

.userpw-confirm{
    background:url(./images/images2/icon-02.png) no-repeat center right 15px;
    background-size: 20px;
    background-color: #fff;
}

.member-footer {
	font-family: "GmarketSans";
    text-align: center;
    font-size: 12px;
    margin-top: 20px;
}

.member-footer div a:hover{
    text-decoration: underline;
    color:#2db400
}

.member-footer div a:after{
    content:'|';
    font-size: 10px;
    color:#bbb;
    margin-right: 5px;
    margin-left: 7px;
    /*살짝 내려가 있기 때문에 위로 올려주기 위해 transform 사용하기*/
    display: inline-block;
    transform: translateY(-1px);

}

.member-footer div a:last-child:after{
    display: none;
}

/*  @media (max-width:768px) {  */
/*      .member{  */
/*          width: 100%;  */
/*     }  */
/*  }  */
</style>
<script src="js/jquery-3.6.3.js"></script>
<script type="text/javascript">
//확인용 전역변수
var idResult = false;
var passwdResult = false;
var passwdCheckResult = false;
var nameResult = false;
var emailResult = false;
// jquery 시작 부분 페이지 동작시 적용
	$(function() {
		//// ID 미입력시 체크 ////
		$("#id").on("focusout", function() {
			let id = $("#id").val();
			let regex = /^[A-Za-z0-9]{5,14}$/;
			if(id == "") {
				$("#checkId").html("필수 입력").css("color","red");
				idResult = false;
				//// 정규표현식을 이용한 ID 유효성 검사 ////
			} else if(!regex.exec(id)) {
				$("#checkId").html("영문자, 숫자 조합 5~14자 입력해주세요").css("color","orange");
				idResult = false;
			} else {
				//// ID 중복체크 ////
				$.ajax({
					url: "MemberIdCheck.me",
					data: { id: $("#id").val()},
					success: function(result) {
						$("#checkId").html(result);
						
						if(result == "true") {
							$("#checkId").html("아이디 중복이다멍").css("color","red");
							idResult = false;
						} else {
							$("#checkId").html("아이디 사용 가능 하개~").css("color","#00ff00");
							idResult = true;
						}
					},
						
				});
				
			}
		});
		//// 패스워드 정규식 체크 ////
		$("#passwd").on("change", function() {
			let passwd = $("#passwd").val(); // 패스워드 값 가져오기
			let lengthRegex = /^[A-Za-z0-9!@#$%]{8,16}$/; // 패스워드 길이 체크 8~16자 대,소문자 숫자 특수문자
			let engUpperRegex = /[A-Z]/;
			let engLowerRegex = /[a-z]/; 
			let numRegex = /[0-9]/;
			let specRegex = /[!@#$%]/;
			if(!lengthRegex.exec(passwd)) { // 입력한 패스워드가 정규식에 포함 X
				$("#checkPw").html("8~16자 대,소문자 숫자 특수문자(!@#$%) 조합하여 작성해주개").css("color","#404040");
				passwdResult = false; 
			} else { // 입력한 패스워드가 정규식에 포함 O
				// 복잡도 검사 (전체 규칙 검사 통과 시)
				let count = 0;
				// 항목별 검사 후 포함되어 있으면 카운트 증가
				if(engUpperRegex.exec(passwd)) { count++ };
				if(engLowerRegex.exec(passwd)) { count++ };
				if(numRegex.exec(passwd)) { count++ };
				if(specRegex.exec(passwd)) { count++ };
				
				// 카운트 값 판별
				switch(count) {
					case 4 : $("#checkPw").html("만족스럽개!").css("color","#00ff00"),
							 $("#passwd").css("color","#00ff00"),
							 passwdResult = true; break;
					case 3 : $("#checkPw").html("뭔가 부족하지만 쓸 수 있개").css("color","orange"),
							 $("#passwd").css("color","orange"),
							 passwdResult = true; break;
					case 2 : $("#checkPw").html("패스워드 너무 쉽개!").css("color","red"),
							 $("#passwd").css("color","red"),
							 passwdResult = true; break;
					case 1 : $("#checkPw").html("사용불가").css("color","#999999"),
							 passwdResult = false;
				}
				count = 0;
			}
		});	
		//// 패스워드 재확인 ////
		$("#repasswd").on("change", function() {
			if($("#passwd").val() != $("#repasswd").val()) {
				$("#checkRePw").html("패스워드 같지 않개!").css("color", "red");
				passwdCheckResult = false;
			} else {
				$("#checkRePw").html("패스워드 일치하개!").css("color", "#00ff00");
				passwdCheckResult = true;
			}
		});
		//// 이름 확인 ////
		$("#name").on("change", function() {
			let name = $("#name").val();
			let regex = /^[가-힣]{2,10}$/;
			if(name == "") {
				$("#checkName").html("입력해주개..").css("color","red");
				nameResult = false;
				//// 정규표현식을 이용한 이름 유효성 검사 ////
			} else if(!regex.exec(name)) {
				$("#checkName").html("조건에 맞지않개..").css("color","red");
				nameResult = false;
			} else {
				$("#checkName").html(name + " 좋은 이름이개!").css("color","#00ff00");
				nameResult = true;
			}
		});
		
		$("#email").on("focusout", function() {
			let email = $("#email").val();
			if(email == "") {
				$("#checkEmail").html("필수 입력").css("color","red");
				emailResult = false;
				
			} else {
				//// Email 중복체크 ////
				$.ajax({
					url: "MemberEmailCheck.me",
					data: { email: $("#email").val()},
					success: function(result) {
						$("#checkEmail").html(result);
						
						if(result == "true") {
							$("#checkEmail").html("이미 사용중인 이메일 이다멍!").css("color","red");
							emailResult = false;
						} else {
							$("#checkEmail").html("사용가능한 이메일 이개~").css("color","#00ff00");
							emailResult = true;
						}
					},
						
				});
				
			}
		});
		//// 가입하기 버튼 누를 시 확인 안된 사항 알림 ////
		$("form").submit(function() {
			if(!idResult) {
				alert("ID 확인해주세요");
				return false;
			}
			
			if(!passwdResult) {
				alert("패스워드 확인해주세요");
				return false;
			}
			
			if(!passwdCheckResult) {
				alert("패스워드가 동일한지 확인해주세요");
				return false;
			}
			
			if(!nameResult) {
				alert("이름 확인해주세요");
				return false;
			}
			
			if(!emailResult) {
				alert("이메일 확인해주세요");
				return false;
			}
		});
	});
</script>
<!-- 챗봇 관련 코드 -->
<script>
  (function() {
    var w = window;
    if (w.ChannelIO) {
      return (window.console.error || window.console.log || function(){})('ChannelIO script included twice.');
    }
    var ch = function() {
      ch.c(arguments);
    };
    ch.q = [];
    ch.c = function(args) {
      ch.q.push(args);
    };
    w.ChannelIO = ch;
    function l() {
      if (w.ChannelIOInitialized) {
        return;
      }
      w.ChannelIOInitialized = true;
      var s = document.createElement('script');
      s.type = 'text/javascript';
      s.async = true;
      s.src = 'https://cdn.channel.io/plugin/ch-plugin-web.js';
      s.charset = 'UTF-8';
      var x = document.getElementsByTagName('script')[0];
      x.parentNode.insertBefore(s, x);
    }
    if (document.readyState === 'complete') {
      l();
    } else if (window.attachEvent) {
      window.attachEvent('onload', l);
    } else {
      window.addEventListener('DOMContentLoaded', l, false);
      window.addEventListener('load', l, false);
    }
  })();
//   ChannelIO('boot', {
//     pluginKey: "1340eb3c-ddf6-43c5-a497-6a91281156bc", //please fill with your plugin key
//     memberId: "YOUR_USER_ID", //fill with user id
//     profile: {
	
//       "name": "YOUR_USER_NAME", //fill with user name
//       "mobileNumber": "YOUR_USER_MOBILE_NUMBER", //fill with user phone number
//       "CUSTOM_VALUE_1": "VALUE_1", //any other custom meta data
//       "CUSTOM_VALUE_2": "VALUE_2"
//     }
//   });
  ChannelIO('boot', {
	  pluginKey: '1340eb3c-ddf6-43c5-a497-6a91281156bc'
		}, function onBoot(error, user) {
	  		if (error) {
	   			 console.error(error);
	  		} else {
	    		console.log('boot success', user)
	  		}
	});
  
</script>
<!-- 챗봇 관련 코드 -->
</head>

<body>
<form action="MemberJoin.me" method="post">
	<div class="member">
        <!--  로고 -->
        <a href="http://localhost:8080/DangDangEat/"><img class="logo" src="img/logo3.png"></a>

        <!--  필드 -->
        <div class="field">
            <b>아이디</b>
            <span class="placehold-text"><input type="text" name="id" id="id" required="required" placeholder="5~14자 대,소문자 숫자 조합"></span>
            <span id="checkId"></span>
        </div>
        <div class="field">
            <b>비밀번호</b>
            <input class="userpw" type="password" name="passwd" id="passwd" placeholder="8~16자 대,소문자 숫자 특수문자(!@#$%) 조합">
            <span id="checkPw"></span>
        </div>
        <div class="field">
            <b>비밀번호 재확인</b>
            <input class="userpw-confirm" type="password" name="repasswd" id="repasswd" placeholder="패스워드 다시 입력해주세요.">
            <span id="checkRePw"></span>
        </div>
        <div class="field">
            <b>이름</b>
            <input type="text" name="name" id="name" required="required" placeholder="2~10자 한글 입력해주세요">
            <span id="checkName"></span>
        </div>


        <!--  이메일_전화번호 -->
        <div class="field">
            <b>이메일</b>
            <input type="email" placeholder="인증메일이 발송될 이메일입니다." name="email" id="email" required="required">
            <span id="checkEmail"></span>
        </div>
        
        <div class="field tel-number">
            <b>휴대전화</b>
            <div>
                <input type="text" placeholder="숫자만 입력해주세요." name="mobile">
            </div>
        </div>
        <!-- 주소 -->
        <div class="field tel-number">
            <b>주소</b>
            <div>
                <input type="text" placeholder="우편 번호" name="postcode" id="postcode">
                <input type="button" value="주소 검색" onclick="kakaoAddr()">
            </div>
            <input type="text" placeholder="주소 입력" name="addr1" id="addr1">
            <input type="text" placeholder="상세주소 입력" name="addr2">
            
        </div>
        
        <!--  필드(생년월일) -->
        <div class="field birth">
            <b>댕생일<small>(선택)</small></b>
            <div>
                <input type="text" placeholder="연(4자)" name="year">                
                <input type="text" placeholder="월" name="month">                
                <input type="text" placeholder="일" name="day">
            </div>
        </div>

        <!--  가입하기 버튼 -->
        <input type="submit" value="가입하기">

        <!--  푸터 -->
        <div class="member-footer">
            <div>
                <a href="#none">이용약관</a>
                <a href="#none">개인정보처리방침</a>
                <a href="#none">책임의 한계와 법적고지</a>
            </div>
            <span><a href="#none"></a></span>
        </div>
    </div>
</form>
</body>
<%-- 카카오 주소 API 적용하기 --%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function kakaoAddr() { // onclick 시 작동할 함수 선언
		new daum.Postcode({
			oncomplete: function(data) {
				var roadAddr = data.roadAddress;
				// 회원이 검색해서 찾은 주소 클릭 시 폼에 뿌리기
				document.getElementById("postcode").value = data.zonecode;
				document.getElementById("addr1").value = data.roadAddress;
				
			}
		}).open();
	}
</script>
</html>
