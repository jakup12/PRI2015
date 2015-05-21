<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Kalendarz : TeacherScheduler</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body class="calendarBody">
		<!-- Formularz dodawania grupy -->
		<h3>Dodawanie grup</h3>
		<form action="addGroup" method="post">
			Nazwa grupy: 
			<input type="text" name="groupName"/>
			<input type="hidden" name="userId" value="<%=session.getAttribute("userLogin")%>"/>
			<input type="submit" value="Dodaj grupę" name=""/>
		</form>
		
		<br> <h3>Lista grup</h3>
		<!-- Lista grup -->	
		<%int size = Integer.parseInt( session.getAttribute("listOfGroupsSize").toString());%>	
		<table border="1">
			
		<tr>
		<%for(int i = 0; i < size; i++){%>
			<td><%=session.getAttribute("groupName"+i)%></td>
		<%} %>
		</tr>
		
		<tr>
		<%for(int i = 0; i < size; i++){%>
			<td>
				<form action="addChat" method="post">
					<input type="text" name="chatName"/>
					<input type="hidden" name="groupId" value="<%=session.getAttribute("groupId"+i)%>"/>
					<input type="hidden" name="teacherName" value="<%=session.getAttribute("userLogin")%>"/>
					<input type="submit" value="Dodaj czat" name=""/>
				</form>
			</td>
		<%} %>
		</tr>
		
		<tr>
		<%for(int i = 0; i < size; i++){%>
			<td><%=session.getAttribute("groupStudents"+i)%></td>
		<%} %>
		</tr>
		
		<tr>
		<%for(int i = 0; i < size; i++){%>
			<td>
				<form action="addStudentToGroup" method="post" id="addStudentToGroupForm<%=i%>">
					<select name="studentUserId" form="addStudentToGroupForm<%=i%>">
			      		<%int studentsSize = Integer.parseInt( session.getAttribute("listOfStudentsSize").toString());
		       	  		for(int j = 0; j < studentsSize; j++){
		          		%>
				  			<option value="<%=session.getAttribute("studentUserId"+j)%>"><%=session.getAttribute("studentFullName"+j)%></option>
				  		<%} %>
					</select>
					<input type="hidden" name="groupId" value="<%=session.getAttribute("groupId"+i)%>"/>
					<br><input type="submit" value="Dodaj do grupy" name=""/>
				</form>
			</td>
		<%} %>	
		</tr>
		
		</table>
</body>
</html>