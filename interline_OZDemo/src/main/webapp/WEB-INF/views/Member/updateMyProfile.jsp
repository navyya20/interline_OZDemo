<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMyProfile</title>
</head>
<script src="<c:url value = '../resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">

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
		width: 10%;
	}
	.inputTd{
		width: 30%;
	}
	.inputBox{
		width: 100%; 
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		 isMobile(); 
		function isMobile() {
		    var filter = "win16|win32|win64|mac|macintel";
		    if( navigator.platform  ){
		      if( filter.indexOf(navigator.platform.toLowerCase())<0 ){
			      $("body").addClass('mobile_body');
		      }else{
		    	  $("body").addClass('pc_body');
		    	  $("body").addClass("pc_body");
			      $("#title").addClass("pc_font_title");
			      $(".btn").addClass("pc_font_button2");
		      }
		    }
		  }
		
	});


	//정규식 확인작업 필요함.
function submitMyProfile(){
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("password2").value;
	if (password != password2){
		alert("パスワードを確認してください");
		return;
	}
	
	var form = document.getElementById("updateMyProfile");
	form.submit();
}
</script>

<body>
<div id="title">会員情報修正</div>

<div id = "">
<form id="updateMyProfile" action="updateMyProfile" method="post">
<table class="informTable">
	<tr>
		<td class="categoryTd"></td>
		<td style="text-align: left;">会員情報</td>
	</tr>
	<tr>
		<td>ID</td>
		<td id="userId" style="text-align: left;">${userInform.userId}<input type="hidden" id="userIdHidden" name="userId" class="inputBox" value="${userInform.userId}"></td>
	</tr>
	<tr>
		<td>PW</td>
		<td class="inputTd"><input type="password" id="password" name="password" class="inputBox" value="${userInform.password}"></td>
	</tr>
	<tr>
		<td>PW確認</td>
		<td class="inputTd"><input type="password" id="password2" name="password2" class="inputBox" value="${userInform.password}"></td>
	</tr>
	<tr>
		<td>企業名</td>
		<td class="inputTd"><input type="text" id="companyName" name="companyName" class="inputBox" value="${userInform.companyName}"></td>
	</tr>
	<tr>
		<td>代表者</td>
		<td class="inputTd"><input type="text" id="representative" name="representative" class="inputBox" value="${userInform.representative}"></td>
	</tr>
	<tr>
		<td>電話番号</td>
		<td class="inputTd"><input type="text" id="phoneNumber" name="phoneNumber" class="inputBox" value="${userInform.phoneNumber}"></td>
	</tr>
	<tr>
		<td>住所</td>
		<td class="inputTd"><input type="text" id="address" name="address" class="inputBox" value="${userInform.address}"></td>
	</tr>
	<tr>
		<td>POST</td>
		<td class="inputTd"><input type="text" id="post" name="post" class="inputBox" value="${userInform.post}"></td>
	</tr>
	
	
	<tr>
		<td class="categoryTd"></td>
		<td style="text-align: left;"><br></br></td>
	</tr>
	<tr>
		<td class="categoryTd"></td>
		<td style="text-align: left;">口座情報</td>
	</tr>
	<tr>
		<td>銀行名</td>
		<td class="inputTd"><input type="text" id="bankName" name="bankName" class="inputBox" value="${userInform.bankName}"></td>
	</tr>
	<tr>
		<td>預金区分</td>
		<td class="inputTd"><input type="text" id="depositeClassification" name="depositeClassification" class="inputBox" value="${userInform.depositeClassification}"></td>
	</tr>
	<tr>
		<td>口座番号</td>
		<td class="inputTd"><input type="text" id="accountNumber" name="accountNumber" class="inputBox" value="${userInform.accountNumber}"></td>
	</tr>
	<tr>
		<td>口座名義人</td>
		<td class="inputTd"><input type="text" id="accountOwner" name="accountOwner" class="inputBox" value="${userInform.accountOwner}"></td>
	</tr>
	<tr>
		<td>ふりがな</td>
		<td class="inputTd"><input type="text" id="hurigana" name="hurigana" class="inputBox" value="${userInform.hurigana}"></td>
	</tr>
	
	<tr id="tr_btn">
		<td colspan="2" style="text-align: right;">
			<span id="update_Member_btn" class="pc_font_button2" onclick="submitMyProfile()">修正</span>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>