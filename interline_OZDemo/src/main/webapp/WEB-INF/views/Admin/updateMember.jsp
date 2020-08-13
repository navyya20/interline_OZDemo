<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMember</title>
</head>
<body>
<form action="../admin/updateMember" method="post" id="">
<table>
<tr>
<td><label for="userid">会員ID</label></td>
<td><input type="text" id="userid" name="userid" value="${user.userId}" readonly></td>
</tr>
<tr>
<td><label for="password">会員PW</label></td>
<td><input type="password" id="password" name="password"></td>
</tr>
<tr>
<td colspan="2" id="tr_btn"><button id="update_Member_btn">修正</button>
</td>
</tr>
</table>
</form>
</body>
</html>