<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btnOk").on("click", function() {
			// 캘린더에서 입력(선택)한 날짜 가져오기
			let selectedDate = $("#date").val();
// 			alert("선택된 날짜 : " + selectedDate);
			
			// 날짜 미선택 시 "날짜 선택 필수!" 메세지 출력
			if(selectedDate == "") {
				alert("날짜 선택 필수!");
				$("#date").focus();
				return; // 함수 실행 중단
			}
			
			// 영화 박스오피스 목록 조회를 위한 REST API 요청 형식에 맞게
			// 날짜 형식을 "YYYY-MM-DD" -> "YYYYMMDD" 형태로 변환해야함
			// 1) split() 함수를 통해 기준 문자열 "-" 기준으로 문자열 분리 후 결합
// 			let splitDateArr = selectedDate.split("-"); // "-" 기호 기준 분리 후 배열 리턴
// 			let targetDt = splitDateArr[0] + splitDateArr[1] + splitDateArr[2];
// 			alert(targetDt); // YYYYMMDD 형식으로 출력됨

			// 2) replace() 함수를 통해 "-" 기호를 ""(널스트링) 으로 치환
// 			let targetDt = selectedDate.replace("-", "");
// 			alert(targetDt);
			// => 2022-12-01 에서 첫번째 "-" 기호만 치환되므로 202212-01 문자열이 출력됨
			// => 이 때, 자바스크립트는 replaceAll() 함수가 지원되지 않으므로
			//    전체 문자열을 대상으로 탐색하여 여러 개를 한꺼번에 변환하려면
			//    정규표현식의 플래그를 활용해야한다!
			// 정규표현식에 사용되는 플래그 종류
			// 1) /g(global) : 대상 문자열 내의 모든 패턴 검색
			// 2) /i(ignore case) : 대상 문자열을 대소문자 무시하고 검색
			// 3) /m(multi line) : 대상 문자열이 복수개의 라인일 경우 전체 라인에 걸쳐 검색
			// => 만약, 복수개의 라인에서 각 라인별로 전체 패턴 검색 시 : /g/m 지정
			let targetDt = selectedDate.replace(/-/g, "");
// 			alert(targetDt);
			
			// =========================================================================
			// 영화진흥위원회에서 제공하는 오픈API 의 박스오피스 정보 JSON 데이터로 처리
			// => 파라미터 중 key 는 샘플 키 활용하고
			//    조회 대상 일자(targetDt)는 선택된 날짜를 가공한 데이터 활용
			$.ajax({
				type: "get",
				url: "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=" + targetDt,
				dataType: "json", // 응답 데이터 형식을 JSON 타입(객체)으로 지정
			})
			.done(function(data) { // 요청 성공 시
				// 전달받은 응답 데이터(요청 결과)를 JSON -> 문자열로 변환하여 출력
// 				$("#resultArea").html(JSON.stringify(data));
				
				// 박스오피스 목록이 저장된 JSON 객체(data)로부터 박스오피스 목록 정보 추출
				// 1. 일별 박스오피스 목록(기본 10개)가 저장된 "boxOfficeResult" 객체 추출
				let boxOfficeResult = data.boxOfficeResult;
// 				$("#resultArea").html(JSON.stringify(boxOfficeResult));
				
				// 2. 박스오피스 타입(boxofficeType)과 조회날짜(showRange) 추출
				// => 1번에서 추출한 boxOfficeResult 객체를 통해 접근
				let boxOfficeType = boxOfficeResult.boxofficeType;
				let showRange = boxOfficeResult.showRange;
// 				$("#resultArea").html(JSON.stringify(boxOfficeType) + " : " + JSON.stringify(showRange));

				// 3. 일별 박스오피스 목록(dailyBoxOfficeList) 추출
				// => 복수개의 영화 정보 객체{}가 dailyBoxOfficeList 라는 배열[]로 관리됨
				let dailyBoxOfficeList = boxOfficeResult.dailyBoxOfficeList;
// 				$("#resultArea").html(JSON.stringify(dailyBoxOfficeList));

				// 4. 추출된 박스오피스 목록(배열)을 반복문을 통해 반복하면서
				//    순위(rank), 제목(movieNm), 개봉일(openDt), 누적관객수(audiAcc) 추출
				for(let movie of dailyBoxOfficeList) {
					$("#resultArea > table").append(
							"<tr><td>" + movie.rank + "</td>"
							+ "<td>" + movie.movieNm + "</td>"
							+ "<td>" + movie.openDt + "</td>"
							+ "<td>" + movie.audiAcc + "</td>"
					);
				}
				
			})
			.fail(function() {
				$("#resultArea").html("요청 실패!");
			});
			
			
		});
	});
</script>
</head>
<body>
	<h1>AJAX - test5_json_movie_list.jsp</h1>
	<input type="date" id="date">
	<input type="button" value="일자별 박스오피스 검색" id="btnOk">
	<hr>
	<div id="resultArea">
		<table border="1">
			<tr>
				<th width="80">현재순위</th>
				<th width="400">영화명</th>
				<th width="150">개봉일</th>
				<th width="100">누적관객수</th>
			</tr>
		</table>
	</div>	
</body>
</html>











