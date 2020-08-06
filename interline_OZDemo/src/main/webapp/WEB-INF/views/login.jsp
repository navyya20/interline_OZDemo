<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<table>
<tr>
<th colspan="2" class="login_th_title">
<span class="title_text"></span></th>
</tr>
<form action="login" method="post">
<tr>
<td class="login_td_id"><label for="id">ID</label></td>
<td class="login_td_id"><input type="text" id="id" name="userid"></td>
</tr>
<tr>
<td class="login_td_pw "><label for="pw">PW</label></td>
<td class="login_td_pw"><input type="password" id="pw" name="password"></td>
</tr>
<tr>
<td colspan="2" class="login_td_Submit"><button id="login">ログイン</button></td>
</tr>
</form>
</table>
</body>
</html>
