<%@ page language="java"  contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"
		 import="java.util.List"
		 import="sec01.ex01.MemberDAO"
		 import="sec01.ex01.MemberBean" %>
			 
<%	request.setCharacterEncoding("UTF-8"); %>    

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 출력창</title>
</head>
<body>
	<table style="margin:auto; width:100%;">
		<tr align="center" bgcolor="#99ccff">
			<td width="7%" >아이디</td>
			<td width="7%">비밀번호</td>
			<td width="5%" >이름</td>
			<td width="11%" >이메일</td>
		</tr>
<!-- form에서 입력된 것을 가져올 때. 밑에 5줄은 33줄부터 43줄까지를 대체한다.  -->
		<tr align="center">
			<td>${param.id}</td>
			<td>${param.password}</td>
			<td>${param.name}</td>
			<td>${param.email}</td>
		</tr>

<%-- 
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
%> 
		<tr align="center">
			<td><%=id %></td>
			<td><%=password %></td>
			<td><%=name %></td>
			<td><%=email %></td>
		</tr>
		
		<tr align="center">
			<td><%=request.getParameter("id") %></td>
			<td><%=request.getParameter("password") %></td>
			<td><%=request.getParameter("name") %></td>
			<td><%=request.getParameter("email") %></td>
		</tr>
--%>
		

		<tr height="1" bgcolor="#99ccff">
			<td colspan="4"></td>
		</tr>
	</table>
</body>
</html>
