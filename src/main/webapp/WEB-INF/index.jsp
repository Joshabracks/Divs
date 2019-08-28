<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="icon" href="images/divs.png">
<meta charset="ISO-8859-1">
<title>DIVS |</title>
</head>
<body>
<div class="wrapper">
<div class="header">
<h1>DIVS |</h1>
<div class="things">
<form id="updateForm">
<input type="text" name="name">
<input type="submit" value="Add a thing!" class="button">
</form>
</div>
</div>
	
<div class="sidebar" id="side">
<div class="block">
<div class="health" id="spawner">
<h2>50</h2>
</div>
<button class="div" id="display"></button>
</div>
<div class="block" id="infobox"></div>
<div class="block" id="spotlight"></div>
</div>

<div class="bottom" id="marquis">

</div>
<div class="game" id="gamer">

</div>
</div>
<script src="/js/script.js"></script>
</body>
</html>