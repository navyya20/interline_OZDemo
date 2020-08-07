<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Login</title>
	
<script src="<c:url value = 'resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="resources/css/Font-Style.css" rel="stylesheet">
<style type="text/css">
	#login_div{
		margin-top:10%;
		text-align: center;
	}
	#login_table{
		display: inline-block;
		
	}
	.login_title{
		font-weight: bold;
		color: rgb(0, 112, 192);
	}
</style>
<script>
$(document).ready(function(){
	 $('#btn_login').click(input_Check);
	 
	function input_Check(){
		var id=$("#login_id").val();
		var pw=$("#login_pw").val();

		if(id !="" && pw != ""){
			$.ajax({
				type:"post",
				url:"login",
				traditional: true,
				data:{userid:id,password:pw},
				dataType:"json",
				success:function(result){
					if(result.error!=null){
						alert(result.error);
					}else if(result.error==null){
						location.href=result.url;
					}
				}	
			});
			$("#login_id").val("");
			$("#login_pw").val("");
		}
		
		if(id == ""){
			alert("IDを入力してください。");
			$("#login_id").focus();
		}else if(pw == ""){
			alert("PASSWORDを入力してください。");
			$("#login_pw").focus();
		}
		return false;
	}
});

</script>
</head>

<body class="body">
<div id="login_div">
	<table id="login_table">
	<tr>
	<td colspan="2" class="login_logo" style="text-align: left;">
		<img src="<c:url value = 'resources/image/interline_login.png'/>" id="login_logo">
	</td>
	</tr>
	<tr>
	<td colspan="2" class="login_title" style="text-align: left;">
		<span>OZ-DEMO 見積書編</span>
	</td>
	</tr>
	<tr>
	<td class="login_td_id"><label for="login_id">ID</label></td>
	<td class="login_td_id"><input type="text" id="login_id" name="login_id"></td>
	</tr>
	<tr>
	<td class="login_td_pw"><label for="login_pw">PW</label></td>
	<td class="login_td_pw"><input type="password" id="login_pw" name="login_pw"></td>
	</tr>
	<tr>
	<td colspan="2" class="login_td_Submit" style="text-align: right;"><button id="btn_login">ログイン</button></td>
	</tr>
	</table>
</div>
</body>
</html>
