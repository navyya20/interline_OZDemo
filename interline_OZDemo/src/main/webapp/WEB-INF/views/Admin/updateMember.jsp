<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMember</title>
<script src="<c:url value='../resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
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

td{
height: 28px;
}

#updateMember_contents{
margin: 70px auto;
width: fit-content;
}

input[type="number"],input[type="text"],input[type="password"]{
height: 18px;
width: 175px;
}

</style>

</head>
<body class="pc_body">
<h1>会員修正</h1>
<div id="updateMember_contents">
<form action="../admin/updateMember" method="post" id="">
<table>
<tr>
<td><label for="userNum" class="pc_font_content1">会員ID</label></td>
<td><input type="text" id="userNum" name="userNum" value="${member.userNum}" class="pc_font_content1" readonly></td>
</tr>
<tr>
<td><label for="userId" class="pc_font_content1">会員ID</label></td>
<td><input type="text" id="userId" name="userId" value="${member.userId}" class="pc_font_content1" readonly></td>
</tr>
<tr>
<td><label for="password" class="pc_font_content1">会員PW</label></td>
<td><input type="password" id="password" name="password" class="pc_font_content1"></td>
</tr>
<tr>
<td colspan="2" id="tr_btn"><button id="update_Member_btn" class="pc_font_button2">修正</button>
</td>
</tr>
</table>
</form>
</div>


</body>
</html>