<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerMember</title>
<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<script>
$(function(){

	$("#insert_Member_btn").click(userInform_check);

	function userInform_check(){
		
		var user_num = $("#userNum").val();
		var user_id = $("#userId").val();
		var user_pw = $("#password").val();
		
 		if(user_num !="" && user_id != ""&& user_pw != ""){

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

		if(user_num == ""){
			alert("会員番号を入力してください。");
			$("#userNum").focus();
		}else if(user_id == ""){
			alert("会員IDを入力してください。");
			$("#userMail").focus();
		}else if(user_pw == ""){
			alert("パスワードを入力してください。");
			$("#password").focus();
		}
		
		return false;
	}
});

</script>

<style type="text/css">
body{
text-align:center;
}

input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

</style>
</head>
<body>
<h1>会員登録</h1>
<form action="../admin/registerMember" method="post" id="registerMember_Form">
<table>
<tr>
<td><label for="userNum">会員番号</label></td>
<td><input type="number" id="userNum" name="userNum"></td>
</tr>
<tr>
<td><label for="userId">会員ID</label></td>
<td><input type="text" id="userId" name="userId"></td>
</tr>
<tr>
<td><label for="password">会員PW</label></td>
<td><input type="password" id="password" name="password"></td>
</tr>
<tr>
<td colspan="2" id="tr_btn"><button id="insert_Member_btn">会員登録</button>
</td>
</tr>
</table>
</form>
</body>
</html>