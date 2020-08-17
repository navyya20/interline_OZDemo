<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMember</title>
<script src="<c:url value='../resources/js/jquery-2.0.3.min.js'/>"></script>
<script>
$(function(){
	$("#update_Member_btn").click(userInform_update);

	function userInform_update(){
		var pw = $("#password").val();

		if(pw != null && pw != ""){
			if(confirm("修正しましか？")){
				return true;
			}
		}else{
			alert("パスワードを入力してください。");
			$("#password").focus();
		}

		return false;
	}
});
</script>

<style>
body{
text-align:center;
}
</style>

</head>
<body>
<h1>会員修正</h1>
<form action="../admin/updateMember" method="post" id="">
<table>
<tr>
<td><label for="userNum">会員ID</label></td>
<td><input type="text" id="userNum" name="userNum" value="${member.userNum}" readonly></td>
</tr>
<tr>
<td><label for="userId">会員ID</label></td>
<td><input type="text" id="userId" name="userId" value="${member.userId}" readonly></td>
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