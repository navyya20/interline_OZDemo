<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberList</title>
</head>
<body>
<h1>会員リスト</h1>

<div>
<table>
<tr>
<th>会員番号</th><th>企業名</th><th>代表者</th><th>入会日時</th>
</tr>
<c:forEach begin="0" var="i" end="${member.size()-1}">
<tr>
<td>${member[i].authority}</td>
<td>${member[i].userid}</td>
<td>${member[i].companyname}</td>
<td>${member[i].post}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>