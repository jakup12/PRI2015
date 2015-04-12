<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<title> Logowanie : TeacherScheduler</title>
<link rel="stylesheet" type="text/css" href="style/style.css">    
</head>

<body class="loginPage">

<h3>Kalendarz dyżurów</h3>

<!-- Formularz logowania -->
<form action="login" method="post">
	<br/> <input type="text" name="userName" size="15" placeholder="Login..." />
	<br/> <input type="password" name="password" size="15" placeholder="Hasło..."/>
	<br>
	<div class="reset"><input type="submit" value="Zaloguj się" name=""/></div>
</form>

<br>
<a href="registerPage.jsp"><button type="button">Zarejestruj się</button></a>

</body>
</html>
        
