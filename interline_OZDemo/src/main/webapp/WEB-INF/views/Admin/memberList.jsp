<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberList</title>
<script>

function update_member(val){
	location.href="updateMember?userNum="+val;
}

function del_member(val){
	var userNum = document.getElementById('userNum');
	var form = document.getElementById('delForm_member');
	userNum.value = val;
	
	form.submit();
}
</script>
</head>
<body>
<h1>会員リスト</h1>

<div>
<table>
<tr>
<th>会員番号</th><th>会員ID</th><th>企業名</th><th>代表者</th><th>入会日時</th>
</tr>
<c:forEach begin="0" var="i" end="${member.size()-1}">
<tr>
<td>${member[i].userNum}</td>
<td>${member[i].userId}</td>
<td>${member[i].companyName}</td>
<td>${member[i].representative}</td>
<td>${member[i].startDate}</td>
<td><button id="upBtn_member" onclick="update_member(${member[i].userNum})">修正</button></td>
<td><button id="delBtn_member" onclick="del_member(${member[i].userNum})">削除</button></td>
</tr>
</c:forEach>
</table>
</div>

<form action="deleteMember" method="post" id="delForm_member">
<input type="hidden" name="userNum" id="userNum">
</form>
</body>
</html>