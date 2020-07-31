<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<table>
<tr>
<th colspan="2" class="login_th_title pc_font_title">
<span class="title_text"></span></th>
</tr>
<form action="login" method="post">
<tr>
<td class="login_td_id pc_login_label pc_font_content1"><label for="pc_login_id">メールアドレス</label></td>
<td class="login_td_id"><input type="text" id="pc_login_id" name="userid"></td>
</tr>
<tr>
<td class="login_td_pw pc_login_label pc_font_content1"><label for="pc_login_pw">パスワード</label></td>
<td class="login_td_pw"><input type="password" id="pc_login_pw" name="password"></td>
</tr>
<tr>
<td colspan="2" class="login_td_Submit"><button id="pc_btn_login">ログイン</button></td>
</tr>
</form>
</table>
</body>
</html>
