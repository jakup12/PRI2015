<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Strona główna : TeacherScheduler</title>
<!-- UŻYTKOWNIK NIEZALOGOWANY -->
<% if(session.getAttribute("userLogin") == null){ %>
<meta http-equiv="Refresh" content="0; url=loginPage.jsp" />
</head><body></body></html>
<% } else { %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/style.css">  
<!-- <title>Insert title here</title>-->
</head>
<body class="mainPage">
	<% if(session.getAttribute("isTeacher").toString().compareTo("true") == 0){%>
		<h3>Zalogowano pomyślnie jako wykładowca!</h3>
		<table>
		<tr>
			<td class="rTab">IMIĘ:</td>
			<td><%=session.getAttribute("userName") %></td>
		</tr> 
		<tr>
			<td class="rTab">NAZWISKO:</td>
			<td><%=session.getAttribute("userSurname") %></td>
		</tr>   
		</table>
		<br>
		<form action="getTeacherCalendar" method="post">
			<input type="hidden" name="userId" value="<%=session.getAttribute("userLogin")%>"/>
			<br>
			<input type="submit" value="Mój kalendarz" name=""/>
		</form>
		
	<% } else { %>
		<h3>Zalogowano pomyślnie jako student!</h3>
		<table>
		<tr>
			<td class="rTab">IMIĘ:</td>
			<td><%=session.getAttribute("userName") %></td>
		</tr> 
		<tr>
			<td class="rTab">NAZWISKO:</td>
			<td><%=session.getAttribute("userSurname") %></td>
		</tr>  
		</table>
		<br> 	
		<form action="selectTeacher" method="post" id="teacherForm">
			<h4>*** Zapis na dyżury ***</h4>Wybierz wykładowcę:	
            <br>
	        <select name="teacherId" form="teacherForm">
		      <%int size = Integer.parseInt( session.getAttribute("listOfTeachersSize").toString());
	       	  for(int i = 0; i < size; i++){
	          %>
			  <option value="<%=session.getAttribute("teacherLogin"+i)%>"><%=session.getAttribute("teacherFullName"+i)%></option>
			  <%} %>
			</select>
            <div>
	            <input type="submit" name="submit" class="submit" value="Sprawdź terminy" />
            </div>
       	</form>
	<%}%>
	
	<h3>Wysłane wiadomości</h3>
		<br>
		<form action="getSendersMails" method="post">
			<input type="hidden" name="senderId" value="<%=session.getAttribute("userLogin")%>"/>
			<br>
			<input type="submit" value="Wysłane" name=""/>
		</form>
	<h3>Odebrane wiadomości</h3>
		<br>
		<form action="getReceiversMails" method="post">
			<input type="hidden" name="receiverId" value="<%=session.getAttribute("userLogin")%>"/>
			<br>
			<input type="submit" value="Odebrane" name=""/>
		</form>
</body>
</html>
<%}%>