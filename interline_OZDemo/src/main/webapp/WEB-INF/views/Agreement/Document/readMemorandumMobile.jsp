<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="format-detection" content="telephone=no, address=no, email=no"/><!-- IOS에서 일정 형식의 text에 자동링크를 걸어서 JSON.stringify가 되지 않는다. 이를 해결하기위한 코드 -->
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog.min.css"/>
<script type="text/javascript" src="http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog2.min.js"></script>

<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>
<!-- If you want to run the HTML5SVG viewer please change the OZJSViewer.js to OZJSSVGViewer.js.
<script type="text/javascript" src="http://kimServer:8080/ozrviewer/OZJSSVGViewer.js" charset="utf-8"></script>
-->
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.mouseSwipe.js" type="text/javascript"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
<script type="text/javascript" src="../resources/js/jQuery-FontSpy.js" charset="utf-8"></script>

<title>readMemorandumMobile</title>
<style>
@font-face {
	font-family: "Noto Sans JP";
	src: url('../resources/font/NotoSansJP-Regular.otf') format('opentype');
	font-weight: normal;
	font-style: normal;
}
.mobile_body{
	font-family: -apple-system-subset,Helvetica,Hiragino Kaku Gothic ProN,sans-serif;
	-webkit-text-size-adjust:300%;
}
.memorandumMenuButton{
	display:inline-block;
	width:100px;
}
.memorandumMenuTd{
	width: 100px;
}
</style>

<script>

$(document).ready(function(){
	 isMobile(); 
	function isMobile() {
	    var filter = "win16|win32|win64|mac|macintel";
	    if( navigator.platform  ){
	      if( filter.indexOf(navigator.platform.toLowerCase())<0 ){
	      }else{
	    	  $("#menuBar").addClass('pc_body');
		      $("#title").addClass("pc_font_title");
		      $(".btn").addClass("pc_font_button2");
	      }
	    }
	  }
	
});

function back(){
	location.href="agreementMainMenu";
}
</script>

</head>
<body>
<div id="menuBar" style="position:relative; left:0px; z-index:1000; text-align: center; width:100%;">
<table style="text-align:center; margin:auto;"><tr>
<td class="memorandumMenuTd"><span id="back_Btn"  class="pc_font_button1 memorandumMenuButton" onclick="back()">戻る</span></td>
</tr></table>
</div>
<div id="OZViewer" style="width:100%;height:100%"></div>
<script type="text/javascript">
	var userNum = "${userNum}";
	var reportNum= "${reportNum}";

	function SetOZParamters_OZViewer() {
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("connection.servlet", "http://<%out.print(properties.getOzIP());%>/oz80/server");
		oz.sendToActionScript("connection.reportname", "OZDemo_Agreement/Memorandum/readMemorandum.ozr");

		oz.sendToActionScript("global.language", "ja_JP");
		
		oz.sendToActionScript("odi.odinames", "readMemorandum");
 		oz.sendToActionScript("odi.readMemorandum.pcount", "2");
 		oz.sendToActionScript("odi.readMemorandum.args1", "userNum="+userNum);
		oz.sendToActionScript("odi.readMemorandum.args2", "reportNum="+reportNum);

		oz.sendToActionScript("export.format","pdf");
		oz.sendToActionScript("export.filename",reportNum+"_MemorandumSheet_"+now.getFullYear() + (now.getMonth()+1).toString().padStart(2,'0') + now.getDate().toString().padStart(2,'0') + "_" + now.getHours().toString().padStart(2,'0') + now.getMinutes().toString().padStart(2,'0') + now.getSeconds().toString().padStart(2,'0'));
		oz.sendToActionScript("pdf.fontembedding","true");
		return true;
	}
	var opt = [];
	opt["print_exportfrom"] = "server"; //인쇄 PDF 익스포트 작업을 서버와 통신하여 동작
	opt["save_exportfrom"] = { "pdf" : "server" }; //PDF 익스포트 작업을 서버와 통신하여 동작 

	var isFont = false;		
	function start_viewer() {
	    if (isFont) {
	    	start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/",true,opt);
	    }
	}

	console.log("fontSpy함수를 실행합니다.");
	fontSpy("Noto Sans JP", { //위의 font-face에서 설정한 이름을 여기에 설정해주시기 바랍니다.
	    success: function() {
	    	isFont = true;
	    	console.log("뷰어를 실행합니다.")
	        start_viewer();
	    },
	    failure: function() {
			console.log("isFont is false");
	    }
	});
</script>
</body>
</html>