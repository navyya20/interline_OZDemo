<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMyProfile</title>
</head>
<body>
<h1>会員情報修正</h1>

<div id = "">
<form action="" method="post">
<table>
<tr>
<td>ID</td>
<td><input type="text" id="userid" name="userid" readonly></td>
</tr>
<tr>
<td>PW</td>
<td><input type="password" id="password" name="password"></td>
</tr>
<tr>
<td>企業名</td>
<td><input type="text" id="conpanyname" name="conpanyname" ></td>
</tr>
<tr>
<td>住所</td>
<td><input type="text" id="address" name="address"></td>
</tr>
<tr id="tr_btn"><td colspan="2">
<button id="update_Member_btn">修正</button>
<button id="delete_Member_btn">退会</button>
</td></tr>
</table>
</form>
</div>
</body>
</html>