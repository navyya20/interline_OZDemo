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
<script src="<c:url value = '../resources/js/inputController.js'/>"></script>
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
		$("#companyName").on("propertychange input", function() { limitString("companyName",40); });
		$("#representative").on("propertychange input", function() { limitString("representative",26); });
		$("#address").on("propertychange input", function() { limitString("address",120); });
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

 
	//정규식 확인작업 필요함. [一-龥] [가-힇]   [ぁ-ゔ]+|[ァ-ヴー]+[々〆〤]+/u 
function submitMyProfile(){
	var password = document.getElementById("password").value;
	var password2 = document.getElementById("password2").value;
	var companyName = document.getElementById("companyName").value;
	var representative = document.getElementById("representative").value;
	var phoneNumber = document.getElementById("phoneNumber").value;
	var address = document.getElementById("address").value;
	var post = document.getElementById("post").value;
	var bankName = document.getElementById("bankName").value;
	var depositeClassification = document.getElementById("depositeClassification").value;
	var accountNumber = document.getElementById("accountNumber").value;
	var accountOwner = document.getElementById("accountOwner").value;
	var hurigana = document.getElementById("hurigana").value;
	//비밀번호 체크 정규식
	var pwCheck = /^[A-Za-z0-9]{3,20}$/ ;
	//이메일 체크 정규식
	var emailCheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	//일반 전화번호 정규식
	var phoneCheck = /^\d{2,3}-\d{3,4}-\d{4}$/;
	var postCheck = /^\d{2,3}-\d{3,4}$/;
	//#&@ 검사
	var characterCheck = /[~!$%^*_+|<>?:{}]/;
	//계좌번호 검사
	var accountcheck=/^\d{1,4}-\d{1,8}$/;
	
	if(!pwCheck.test(password)){alert("PASSWORDは3~20字の英数字です。");$("#login_pw").focus();return;}
	if(password != password2){alert("パスワードを確認してください");return;}
	if(characterCheck.test(companyName)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#companyName").focus();return;}
	if(characterCheck.test(representative)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#representative").focus();return;}
	if(characterCheck.test(phoneNumber)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#phoneNumber").focus();return;}
	if(characterCheck.test(address)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#address").focus();return;}
	if(characterCheck.test(post)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#post").focus();return;}
	if(characterCheck.test(bankName)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#bankName").focus();return;}
	if(characterCheck.test(depositeClassification)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#depositeClassification").focus();return;}
	if(characterCheck.test(accountNumber)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#accountNumber").focus();return;}
	if(characterCheck.test(accountOwner)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#accountOwner").focus();return;}
	if(characterCheck.test(hurigana)){alert("特殊文字は＠,＃,＆,(,)だけ使えます。");$("#hurigana").focus();return;}

	//if(companyName.length >=21){"会社名は20文字以内にお願いします。";$("#companyName").focus();return;}
	//if(representative.length >=14){"代表者名は20文字以内にお願いします。";$("#representative").focus();return;}
	//if(address.length >=51){"住所は50文字以内にお願いします。";$("#address").focus();return;}
	if(bankName.length >=31){alert("銀行名は30文字以内にお願いします。");$("#bankName").focus();return;}
	if(depositeClassification.length >=11){alert("代表者名は10文字以内にお願いします。");$("#depositeClassification").focus();return;}
	if(accountNumber.length >0 && !accountcheck.test(accountNumber)){alert("口座番号は123-1234567形式でお願いします。");$("#accountNumber").focus();return;}
	if(accountOwner.length >=31){alert("代表者名は30文字以内にお願いします。");$("#accountOwner").focus();return;}
	if(hurigana.length >=61){alert("口座名義人名は60文字以内にお願いします。");$("#hurigana").focus();return;}
	if(!phoneCheck.test(phoneNumber)){alert("123-1234-1234形式でお願いします。");$("#phoneNumber").focus();return;}
	if(!postCheck.test(post)){alert("123-1234形式でお願いします。");$("#post").focus();return;}

	var form = document.getElementById("updateMyProfile");
	var form = $("#updateMyProfile").serialize() ;
	
    $.ajax({
        type : "post",
        url : "updateMyProfile",
        data : form,
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

<body>
<div id="title">自社情報修正</div>

<form id="updateMyProfile" action="updateMyProfile" method="post">
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
		<td class="inputTd"><input type="text" id="companyName" name="companyName" class="inputBox" value="${userInform.companyName}"></td>
	</tr>
	<tr>
		<td>代表者</td>
		<td class="inputTd"><input type="text" id="representative" name="representative" class="inputBox" value="${userInform.representative}"></td>
	</tr>
	<tr>
		<td>電話番号</td>
		<td class="inputTd"><input type="text" id="phoneNumber" name="phoneNumber" class="inputBox" title="形式:000-0000-0000" maxlength="13" value="${userInform.phoneNumber}"></td>
	</tr>
	<tr>
		<td>住所</td>
		<td class="inputTd"><input type="text" id="address" name="address" class="inputBox" value="${userInform.address}"></td>
	</tr>
	<tr>
		<td>POST</td>
		<td class="inputTd"><input type="text" id="post" name="post" class="inputBox" title="形式:000-0000" maxlength="8" value="${userInform.post}"></td>
	</tr>
	
	
	<tr>
		<td class="categoryTd"></td>
		<td style="text-align: left;"><br></br></td>
	</tr>
	<tr>
		<td class="categoryTd"></td>
		<td style="text-align: left;" class="mini_title">口座情報</td>
	</tr>
	<tr>
		<td>銀行名</td>
		<td class="inputTd"><input type="text" id="bankName" name="bankName" class="inputBox" maxlength="30" value="${userInform.bankName}"></td>
	</tr>
	<tr>
		<td>預金区分</td>
		<td class="inputTd"><input type="text" id="depositeClassification" name="depositeClassification" class="inputBox" maxlength="10" value="${userInform.depositeClassification}"></td>
	</tr>
	<tr>
		<td>口座番号</td>
		<td class="inputTd"><input type="text" id="accountNumber" name="accountNumber" class="inputBox" maxlength="13" value="${userInform.accountNumber}"></td>
	</tr>
	<tr>
		<td>口座名義人</td>
		<td class="inputTd"><input type="text" id="accountOwner" name="accountOwner" class="inputBox" maxlength="20" value="${userInform.accountOwner}"></td>
	</tr>
	<tr>
		<td>ふりがな</td>
		<td class="inputTd"><input type="text" id="hurigana" name="hurigana" class="inputBox" maxlength="20" value="${userInform.hurigana}"></td>
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