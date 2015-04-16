<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Skrzynka odbiorcza</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body class="mailsBody">
		Zalogowany jako: <%=session.getAttribute("userLogin")%>
       	<br>
      	<br> Odebrane wiadomości
	       <table class="mailsTable" border="1">
			   <%int size = Integer.parseInt( session.getAttribute("listOfMailsSize").toString());
		       for(int i = 0; i < size; i++){
		       %>
		       	   <tr>
			       <td class="mailsCell">Nadawca: <%=session.getAttribute("senderId"+i)%><br>Data: <%=session.getAttribute("date"+i)%><br><%=session.getAttribute("message"+i)%>
			       	   <br>
			       </td>
 
		       	   </tr>
		       <%}%>
	       </table>
</body>
</html>