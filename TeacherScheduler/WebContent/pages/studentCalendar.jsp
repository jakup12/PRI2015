<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Kalendarz : TeacherScheduler</title>
</head>
<body>
	   <br> Lista wolnych terminów
       <table border="1">
	       <tr>
		       <th>Id</th>
		       <th>Data</th>
		       <th>Godzina</th>
		       <th>Akcja</th>
	       </tr>
		   <%int size = Integer.parseInt( session.getAttribute("listOfTermsSize").toString());
	       for(int i = 0; i < size; i++){
	       %>
	       <tr>
		       <td><%=session.getAttribute("termId"+i)%></td>
		       <td><%=session.getAttribute("termDate"+i)%></td>
		       <td><%=session.getAttribute("termHour"+i)%></td>
		       <td>
			       <form action="signIn" method="post">
				       <input name="termId" type="hidden" value="<%=session.getAttribute("termId"+i)%>"/>
				       <input type="submit" name="submit" class="submit" value="Zapisz" />
			       </form>
		       </td>
	       </tr>
	       <%}%>
       </table>
</body>
</html>