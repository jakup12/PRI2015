<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Kalendarz : TeacherScheduler</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body class="calendarBody">
		<div>
			<form action="addNewTerm" method="post" id="termForm">
				<br>Data: 
	            <input name="date" type="date"/>
	            <br>Godzina: 
		        <select name="hour" form="termForm">
				  <option value="10:00">10:00</option>
				  <option value="11:00">11:00</option>
				  <option value="12:00">12:00</option>
				  <option value="13:00">13:00</option>
				</select>
	            <br>
	            <input name="userId" type="hidden" value="<%=session.getAttribute("userLogin")%>"/>
	            <br>
	            <div>
		            <input type="submit" name="submit" class="submit" value="Udostępnij temin" />
	            </div>
	       	</form>
       	</div>
       	<br>
      	<br> Twój kalendarz udostępnionych terminów
	       <table class="calendarTable">
			   <%int size = Integer.parseInt( session.getAttribute("listOfTermsSize").toString());
		       for(int i = 0; i < size; i++){
		           if((i == 0) | (i % 5 == 0)){
		       %>
		       	   <tr>
			       <%}%>
			       <td class="calendarCell"><br><br><%=session.getAttribute("termDate"+i)%><br><%=session.getAttribute("termHour"+i)%><br><%=session.getAttribute("assignation"+i)%>
			       	   <br>
				       <form action="removeTerm" method="post">
					       <input name="termId" type="hidden" value="<%=session.getAttribute("termId"+i)%>"/>
					       <div class="centerBtn"><input type="submit" name="submit" value="Usuń"/></p>
				       </form>
  
			       </td>
			       
		       	   <%if((((i+1) % 5 == 0) & i != 0) | (i == (size-1))){%>  
		       	   </tr>
		       	   <%} %>
		       <%}%>
	       </table>
</body>
</html>