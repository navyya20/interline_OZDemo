<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404Error</title>
<script src="<c:url value = '/resources/js/jquery-2.0.3.min.js'/>"></script>
<link href="<c:url value = '/resources/css/Font-Style.css'/>" rel="stylesheet">
<script>
$(document).ready(function(){
	Error_Page();
	 isMobile(); 
	function isMobile() {
	    var filter = "win16|win32|win64|mac|macintel";
	    if( navigator.platform  ){
	    	  $("body").addClass('mobile_body');
	      if( filter.indexOf(navigator.platform.toLowerCase())<0 ){
	      }else{
	    	  $("body").addClass('pc_body');
		      $("#title").addClass("pc_font_title");
		      $(".btn").addClass("pc_font_button2");
	      }
	    }
	  }
	  
	function Error_Page(){
		//페이지호출이 iframe일 경우 부모페이지를 에러페이지로
		if(self!=top){
			window.parent.location.href="/OZDemo/400Error";
		}
	}
});

function main_Menu(){
	location.replace("<c:url value = '/member/memberMain'/>");
}
</script>
<style>
#error_div{    
margin-top: 15%;
text-align: center;
}
</style>
</head>
<body>
<div id="error_div">
<h1>このページは,存在しません。</h1>
<button id="delBtn_member" class="pc_font_button1" onclick="main_Menu()">メイン画面へ</button>
</div>
</body>
</html>