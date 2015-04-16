<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title> Czat</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body class="mailsBody">
		Zalogowany jako: <%=session.getAttribute("userLogin")%>
       	<br>
       	<div>
			<form action="addChatMess" method="post" id="mailForm">
	            <br>Wiadomość:
	            <br>
	            <input name="message" type="text"/>
	            <br>
	            <input name="chatId" type="hidden" value="<%=session.getAttribute("chatId")%>"/>
	            <br>
	            <div>
		            <input type="submit" name="submit" class="submit" value="Wyślij" />
	            </div>
	       	</form>
       	</div>
      	<br> Wysłane wiadomości
	       <table class="mailsTable" border="1">
			   <%int size = Integer.parseInt( session.getAttribute("listOfMessSize").toString());
		       for(int i = 0; i < size; i++){
		       %>
		       	   <tr>
			       <td class="mailsCell">Autor: <%=session.getAttribute("userId"+i)%><br><%=session.getAttribute("message"+i)%>
			       	   <br>
			       </td>
 
		       	   </tr>
		       <%}%>
	       </table>
</body>
</html>