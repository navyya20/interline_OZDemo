<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Login</title>
</head>

<script src="<c:url value = 'resources/js/jquery-2.0.3.min.js'/>"></script>

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

<body>
<div id="login_div">
<table>
<tr>
<th colspan="2" class="login_th_title">
<span class="title_text">見積書システム</span></th>
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
<td colspan="2" class="login_td_Submit"><button id="btn_login">ログイン</button></td>
</tr>
</table>
</div>
</body>
</html>
