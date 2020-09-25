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
	 $("#login_id").keyup(function(e){if(e.keyCode == 13)  input_Check(); });
	 $("#login_pw").keyup(function(e){if(e.keyCode == 13)  input_Check(); });
	function input_Check(){
		var id=$("#login_id").val();
		var pw=$("#login_pw").val();
		
		var check = /^[A-Za-z0-9]{3,20}$/ ;
		if(id.length == 0){alert("IDを入力してください。");$("#login_id").focus();return false;}
		if(pw.length == 0){alert("PWを入力してください。");$("#login_pw").focus();return false;}

		console.log("ID/PW를 확인하러 갑니다.");
		$.ajax({
			type:"post",
			url:"login",
			traditional: true,
			data:{userId:id,password:pw},
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

		return false;
	}
});


//인터셉터로 로그인 들어왔는데 현제페이지가 아이프레임 내부일경우 부모요소를 로그인 페이지로 페이지이동
if ( self !== top ) {
	  // you're in an iframe
	window.parent.location.href="login";
}

</script>
</head>

<body class="body">
<div style="position: absolute; top: 0; left: 0">
	version1 2020 08 27 18:12<br>
	version1.1 2020 08 27 20:53<br>
	version1.2 2020 08 28 16:26<br>
	version1.4 2020 09 03 13:32<br>
	version1.4 2020 09 06 16:29<br>
	version1.6 2020 09 15 16:29<br>
	version1.7 2020 09 17 15:02<br>
	version1.8 2020 09 23 16:37<br>
	version1.9 2020 09 25 13:50<br>
	version1.9 2020 09 25 14:27<br>
</div>
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
	<td colspan="2" class="login_td_Submit" style="text-align: center; padding-top: 5px;"><span id="btn_login" class="pc_font_button1">ログイン</span></td>
	</tr>
	</table>
</div>
</body>
</html>
