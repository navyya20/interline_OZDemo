<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerMember</title>
</head>
<body>
<h1>会員登録</h1>
<form action="../admin/registerMember" method="post" id="">
<table>
<tr>
<td><label for="userid">会員ID</label></td>
<td><input type="text" id="userid" name="userid"></td>
</tr>
<tr>
<td><label for="password">会員PW</label></td>
<td><input type="password" id="password" name="password"></td>
</tr>
<tr>
<tr>
<td><label for="companyname">企業名</label></td>
<td><input type="text" id="companyname" name="companyname"></td>
</tr>
<tr>
<td colspan="2" id="tr_btn"><button id="insert_Member_btn">会員登録</button>
</td>
</tr>
</table>
</form>
</body>
</html>