<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerMember</title>
<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
<script>
$(function(){

	$("#insert_Member_btn").click(userInform_check);

	function userInform_check(){
		
		var user_num = $("#userNum").val();
		var user_id = $("#userId").val();
		var user_pw = $("#password").val();
		var check = /^[A-Za-z0-9]{3,20}$/ ;

		if(user_num.length <= 0 || user_num.length >9){
			alert("会員番号は1~9の数字です。");
			$("#userNum").focus();
		}else if(!check.test(user_id)){
			alert("IDは3~20字の英数字です。");
			$("#userId").focus();
		}else if(!check.test(user_pw)){
			alert("PASSWORDは3~20字の英数字です。");
			$("#password").focus();
		}

 		if(user_num.length > 0 && user_num.length <= 9 && check.test(user_id) && check.test(user_pw)){

 			$.ajax({
				type:"post",
				url:"check_multiple",
				data:{userNum:user_num,userId:user_id},
				dataType:"text",
				success:function(result){
					
					if(result == "存在する会員番号です。"){
						alert(result);
						$("#userNum").focus();
						
					}else if(result == "存在する会員IDです。"){
						alert(result);
						$("#userMail").focus();	
																				
					}else if(confirm("登録しましか？")){
						$('#registerMember_Form').submit();
					} 
				}
			}); 
		} 

 		return false;
	}
});

</script>

<style type="text/css">
body{
text-align:center;
}

td{
height: 28px;
}

input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

input[type="number"],input[type="text"],input[type="password"]{
height: 18px;
width: 175px;
}

#registerMember_contents{
margin: 70px auto;
width: 30%;
}

</style>
</head>
<body class="pc_body">
<h1>会員登録</h1>
<div id="registerMember_contents">
<form action="../admin/registerMember" method="post" id="registerMember_Form">
<table>
<tr>
<td><label for="userNum" class="pc_font_content1">会員番号</label></td>
<td><input type="number" id="userNum" name="userNum" class="pc_font_content1" style="-moz-appearance: textfield;"></td>
</tr>
<tr>
<td><label for="userId" class="pc_font_content1">会員ID</label></td>
<td><input type="text" id="userId" name="userId" class="pc_font_content1"></td>
</tr>
<tr>
<td><label for="password" class="pc_font_content1">会員PW</label></td>
<td><input type="password" id="password" name="password" class="pc_font_content1"></td>
</tr>
<tr>
<td colspan="2" id="tr_btn"><button id="insert_Member_btn" class="pc_font_button2">会員登録</button>
</td>
</tr>
</table>
</form>
</div>


</body>
</html>