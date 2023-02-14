<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DangDangShop - top</title>

<!--영어폰트  font-family: 'Abril Fatface', cursive;  -->
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Abril+Fatface&display=swap" rel="stylesheet">

<!-- TOP.jsp상단 메뉴 font-family: 'Rubik Spray Paint', cursive; -->
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Rubik+Spray+Paint&display=swap" rel="stylesheet">

<!--font-family: 'DM Serif Display', serif;-->
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Yeseva+One&display=swap" rel="stylesheet">

<!--font-family: 'Bigshot One', cursive; 영어-->
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Bigshot+One&display=swap" rel="stylesheet">

<!-- font-family: 'Gowun Dodum', sans-serif; 고운 돋움체 -->
<link rel="preconnect" href="https://fonts.googleapis.com"><link rel="preconnect" href="https://fonts.gstatic.com" crossorigin><link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">
</head>
<style>

/* <!-- 한글 범위:  U+AC00-D7A3 --> */
/* <!-- 영어 대문자 범위 :  U+0041-005A --> */
/* <!-- 영어 소문자 범위 :   U+0061-007A --> */
/* 숫자 범위 : U+0030-0039 */

/*default*/
/* @font-face{ */
/*     /*고운 돋움체 -  한글, 숫자 적용인데 숫자는 적용 안돼요ㅠ*/ */
/*     font-family: 'Gowun Dodum' ; */
/*     src: url('https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap') ; */
/*     unicode-range:U+AC00-D7A3, U+0030-0039 ; */
/* } */
  
/* @font-face{ */
/*     /* 영어(소문자,대문자)적용 */ */
/*     font-family: 'Abril Fatface' ; */
/*     src: url('https://fonts.googleapis.com/css2?family=Bigshot+One&display=swap') ; */
/* 	unicode-range:U+0041-005A,U+0061-007A ; */
/* } */
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
  
body {
    font-family:"GmarketSansMedium" ;
}

ul{
	list-style: none;
}
  
a{
	text-decoration: none;
	color: black;
}
  
a:hover {
	color: 		#FF7F50;    
	text-decoration: underline;
	transition: 0.5s;
}
/*TOP*/
header {
	height: 150px;
	margin-top: 10px;
}

header > div {
	font-size: 3rem;
	font-family: 'GmarketSansMedium';
	text-align: center;
}
  
/*TOP - menu*/
.top-menu > ul{
	display: flex;
	font-family: 'GmarketSansMedium', 'culsive';
	font-weight: bold;
	top: 30px
}

.top-menu li {
	padding: 1%;
	font-size: 1.3rem;
}

.top-menu a{
	position: relative;
}

.top-menu a:hover {
	top: 2px;
  
}  

.div-top-icon > ul {
    display: flex;
    flex-direction: row-reverse;
}
.div-top-icon > ul > li > a >  img {
    padding: 1%;
    height: 40px;
    width: auto;
	margin-right: 3%;
	padding: 5px;
}

.login {
	position: absolute;
	font-family: 'GmarketSansMedium';
	top: 15px;
	right: 30px;
	font-size: 1em;
	font-weight: bold;
}

.myDiv {
	padding-top: 1em;
}

</style>

<!-- 로그아웃 확인 -->
<script>
	function logout() {
		let isLogout = confirm("로그아웃 하시겠습니까?");
		
		if(isLogout) {
			location.href = "MemberLogout.me";
		}
	}
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
  <!--TOP-->
  <div class="container">
  <header>
    <div class="myDiv">
      <a href="./">
      <img src = "img/logo3.png" width="300px">
      </a>
    </div>
  </header>
  

  <div class="login">
	  <c:choose>
			<%-- 로그인 상태일 경우 아이디 표시, Logout 링크 표시 --%>
			<%-- EL 을 사용하여 세션 객체 접근 시 sessionScope.속성명 으로 접근 --%>
			<c:when test="${not empty sessionScope.sId }">
				<a href="CheckPassForm.me">${sessionScope.sId } 님</a> |	
				<%-- 만약, 로그인 된 세션 아이디가 "admin" 일 경우 관리자페이지 링크(MemberList.me) 표시 --%>	
				<c:if test='${sessionScope.sId eq "admin" }'>
					<span class="admin_page"><a href="AdminMain.ad">Admin</a> |</span> 		
				</c:if>
					<span class="logout"><a href="javascript:logout()">Logout</a></span>	
			</c:when>
			<c:otherwise>
			<%-- 로그인 상태가 아닐 경우 Login, Join 링크 표시 --%>
				<a href="MemberLoginForm.me">Login</a>		
				| <a href="MemberJoinForm.me">Join</a>
			</c:otherwise>
		</c:choose>
  </div>

  <div class="div-top-icon">
    <ul class="ul-top-icon">
        <li><a href="WishlistDetail.ct"><img class="top-icon" src="img/heart.png"></a></li>
        <li><a href="CartList.ct"><img class="top-icon" src="img/shopping-cart.png"></a></li>
        <li><a href="MyPage.me"><img class="top-icon" src="img/dog.png"></a></li>
    </ul>
</div>

  <div class="top-menu">
    <ul>
      <li><a href="About.ad">About</a></li>
      <li>/</li>
      <li><a href="ProductList.pd">Shop</a></li>
      <li>/</li>
      <li><a href="NoticeList.bo">Notice</a></li>
      <li>/</li>
      <li><a href="QnaList.bo">Q&A</a></li>
    </ul>
  </div>
  </div>
</html>
