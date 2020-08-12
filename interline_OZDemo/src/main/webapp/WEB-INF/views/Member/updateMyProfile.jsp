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
</script>

<body>
<div id="title">会員情報修正</div>

<div id = "">
<form action="" method="post">
<table class="informTable">
	<tr>
		<td class="categoryTd"></td>
		<td style="text-align: left;">会員情報</td>
	</tr>
	<tr>
		<td>ID</td>
		<td></td>
	</tr>
	<tr>
		<td>PW</td>
		<td class="inputTd"><input type="password" id="password" name="password" class="inputBox" ></td>
	</tr>
	<tr>
		<td>企業名</td>
		<td class="inputTd"><input type="text" id="companyName" name="companyName" class="inputBox" ></td>
	</tr>
	<tr>
		<td>代表者</td>
		<td class="inputTd"><input type="text" id="representative" name="representative" class="inputBox" ></td>
	</tr>
	<tr>
		<td>電話番号</td>
		<td class="inputTd"><input type="text" id="phoneNumber" name="phoneNumber" class="inputBox" ></td>
	</tr>
	<tr>
		<td>住所</td>
		<td class="inputTd"><input type="text" id="address" name="address" class="inputBox" ></td>
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
		<td class="inputTd"><input type="text" id="address" name="address" class="inputBox" ></td>
	</tr>
	<tr>
		<td>預金区分</td>
		<td class="inputTd"><input type="text" id="address" name="address" class="inputBox" ></td>
	</tr>
	<tr>
		<td>口座番号</td>
		<td class="inputTd"><input type="text" id="address" name="address" class="inputBox" ></td>
	</tr>
	<tr>
		<td>口座名義人</td>
		<td class="inputTd"><input type="text" id="address" name="address" class="inputBox" ></td>
	</tr>
	<tr>
		<td>ふりがな</td>
		<td class="inputTd"><input type="text" id="address" name="address" class="inputBox" ></td>
	</tr>
	
	<tr id="tr_btn">
		<td colspan="2" style="text-align: right;">
			<button id="update_Member_btn" class="pc_font_button2">修正</button>
			<button id="delete_Member_btn" class="pc_font_button2">退会</button>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>