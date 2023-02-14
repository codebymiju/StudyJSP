<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>requestForm1.jsp - request 객체</h1>
<!-- 	<form action="requestPro1.jsp" method="get"></form> -->
	<form action="requestPro1.jsp" method="post">
		<table border = "1">
	  		<tr>
		  		 <th>이름</th>
		  		 <td><input type = "text" name = "name" required="required"></td>
	  		</tr>
	  		<tr>
		  		 <th>나이</th>
		  		 <td><input type = "text" name = "age" ></td>
	  		</tr>
	  		<tr>
		  		 <th>성별</th>
		  		 <td>
			  		 <input type = "radio" name = "gender" value="male">남
			  		 <input type = "radio" name = "gender" value="female">여
		  		 </td>
	  		</tr>	
	  		<tr>
		  		 <th>취미</th>
		  		 <td>
			  		 <input type = "checkbox" name = "hobby" value="독서">독서
			  		 <input type = "checkbox" name = "hobby" value="게임">게임
			  		 <input type = "checkbox" name = "hobby" value="등산">등산
		  		 </td>
	  		</tr>	
	  		<tr>
		  		 <td colspan = "2" align="center"><input type = "submit" value = "전송"></td>
	  		</tr>	
		</table>
	</form>
</body>
</html>