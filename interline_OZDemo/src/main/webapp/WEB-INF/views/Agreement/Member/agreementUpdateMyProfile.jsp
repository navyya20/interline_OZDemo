<%@ page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMyProfile</title>
</head>
<script src="<c:url value = '../resources/js/jquery-3.4.1.min.js'/>"></script>
<script src="<c:url value = '../resources/js/inputController.js'/>"></script>
<link href="../resources/css/Font-Style.css?ver=1" rel="stylesheet">

<style>
	body{
		text-align: center;
	}
	#title{
		margin-left: auto;
		margin-right: auto;
	}
	.informTable{
		margin-left: auto;
		margin-right: auto;
	}
	.categoryTd{
		width: 17%;
	}
	.inputTd{
		width: 65%;
		text-align:left;
	}
	.inputBox{
		width: 98%; 
	}
	.mini_title{
		font-weight: 700;
	}
	#update_Member_btn{
		padding: 1px 20px 1px 20px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("#companyName").on("propertychange input", function() { limitString("companyName",20); });
		$("#representative").on("propertychange input", function() { limitString("representative",26); });
		$("#address").on("propertychange input", function() { limitString("address",70); });
		$("#incharge").on("propertychange input", function() { limitString("incharge",20); });
		$("#email").on("propertychange input", function() { limitString("email",30); });
	});

	
 
	//정규식 확인작업 필요함. [一-龥] [가-힇]   [ぁ-ゔ]+|[ァ-ヴー]+[々〆〤]+/u 
function submitMyProfile(){
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("password2").value;
	var companyName = document.getElementById("companyName").value;
	var representative = document.getElementById("representative").value;
	var phoneNumber = document.getElementById("phoneNumber").value;
	var address = document.getElementById("address").value;
	var post = document.getElementById("post").value;
	var incharge = document.getElementById("incharge").value;
	var email = document.getElementById("email").value;
	//비밀번호 체크 정규식
	var pwCheck = /^[A-Za-z0-9]{3,20}$/ ;
	//이메일 체크 정규식
	var emailCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	//일반 전화번호 정규식
	var phoneCheck = /^\d{2,3}-\d{3,4}-\d{4}$/;
	var postCheck = /^\d{2,3}-\d{3,4}$/;
	//#&@ 검사
	var characterCheck = /[~!$%^*_+|<>?:{}]/;
	
	if(!pwCheck.test(password)){alert("PASSWORDは3~20字の英数字です。");$("#login_pw").focus();return false;}
	if(password != password2){alert("パスワードを確認してください");return false;}
	if(characterCheck.test(companyName)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#companyName").focus();return false;}
	if(characterCheck.test(representative)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#representative").focus();return false;}
	if(characterCheck.test(address)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#address").focus();return false;}
	if(characterCheck.test(incharge)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#incharge").focus();return false;}
	if(!phoneCheck.test(phoneNumber)){alert("123-1234-1234形式でお願いします。");$("#phoneNumber").focus();return false;}
	if(!postCheck.test(post)){alert("123-1234形式でお願いします。");$("#post").focus();return false;}
	if(!emailCheck.test(email)){alert("メール形式と間違っています。");$("#email").focus();return false;}
	
	var formData = $("#updateMyProfile").serialize();
	//var formData = document.getElementById("updateMyProfile").serialize();
	console.log(JSON.stringify(formData));
    $.ajax({
        type : "post",
        url : "agreementUpdateMyProfile",
        data : formData,
        dataType : "text",
        error: function(xhr, status, error){
            console.log(error);
        },
        success : function(s){
            alert("自社情報を修正しました。");
            location.reload(true);
        }
    });
}


</script>

<body class="pc_body">
<div id="title">自社情報修正</div>

<form id="updateMyProfile" method="post" >
<table class="informTable">
	<tr>
		<td class="categoryTd"></td>
		<td style="text-align: left;" class="mini_title">会員情報</td>
	</tr>
	<tr>
		<td>ID</td>
		<td id="userId" style="text-align: left;">${userInform.userId}<input type="hidden" id="userIdHidden" name="userId" class="inputBox" value="${userInform.userId}"></td>
	</tr>
	<tr>
		<td>PW</td>
		<td class="inputTd"><input type="password" id="password" name="password" class="inputBox" title="形式:3~20字の英数字" maxlength="20" value="${userInform.password}"></td>
	</tr>
	<tr>
		<td>PW確認</td>
		<td class="inputTd"><input type="password" id="password2" name="password2" class="inputBox" title="形式:3~20字の英数字" maxlength="20"  value="${userInform.password}"></td>
	</tr>
	<tr>
		<td>企業名</td>
		<td class="inputTd"><input type="text" id="companyName" name="company" class="inputBox" value="${agreementUserInform.company}"></td>
	</tr>
	<tr>
		<td>代表者</td>
		<td class="inputTd"><input type="text" id="representative" name="representative" class="inputBox" value="${agreementUserInform.representative}"></td>
	</tr>
	<tr>
		<td>電話番号</td>
		<td class="inputTd"><input type="text" id="phoneNumber" name="phoneNumber" class="inputBox" title="形式:000-0000-0000" maxlength="13" value="${agreementUserInform.phoneNumber}"></td>
	</tr>
	<tr>
		<td>住所</td>
		<td class="inputTd"><input type="text" id="address" name="address" class="inputBox" value="${agreementUserInform.address}"></td>
	</tr>
	<tr>
		<td>POST</td>
		<td class="inputTd"><input type="text" id="post" name="post" class="inputBox" maxlength="8" title="形式:000-0000" value="${agreementUserInform.post}"></td>
	</tr>
	<tr>
		<td>窓口責任者</td>
		<td class="inputTd"><input type="text" id="incharge" name="incharge" class="inputBox" maxlength="20" value="${agreementUserInform.incharge}"></td>
	</tr>
	<tr>
		<td>email</td>
		<td class="inputTd"><input type="text" id="email" name="email" class="inputBox" maxlength="40" value="${agreementUserInform.email}"></td>
	</tr>
	
	<tr id="tr_btn">
		<td colspan="2" style="text-align: right;">
			<span id="update_Member_btn" class="pc_font_button2" onclick="submitMyProfile()">修正</span>
		</td>
	</tr>
</table>
</form>
</body>
</html>