<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>

<!DOCTYPE html>
<html style="height:97.6%">
<head>
<meta charset="UTF-8">
<title>Read BillSheet</title>

<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.css" type="text/css"/>
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
<script type="text/javascript" src="../resources/js/jQuery-FontSpy.js" charset="utf-8"></script>
<link rel="preload" href="../resources/font/NotoSansJP-Regular.otf" as="font">
<style>
.billMenuButton{
	display:inline-block;
	width:100px;
}
.billMenuTd{
	width: 100px;
}
</style>

</head>
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
	location.href="memberMain";
}
</script>

<body style="width:99%;height:97.6%">
<div id="menuBar" style="position:relative; left:0px; z-index:1000; text-align: center; width:100%;">
<table style="text-align:center; margin:auto;"><tr>
<td class="billMenuTd"><span id="back_Btn"  class="pc_font_button1 billMenuButton" onclick="back()">戻る</span></td>
</tr></table>
</div>

<div id="OZViewer" style="width:99.5%;height:97.6%"></div>
<script type="text/javascript" >
	function SetOZParamters_OZViewer(){
		var now = new Date();
		var reportNum = "${reportNum}";
		var userNum = "${userNum}";
		var billOzrName = "${billOzrName}";
		var jsName ="${jsName}"
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("connection.servlet","http://<%out.print(properties.getOzIP());%>/oz80/server");
		oz.sendToActionScript("connection.reportname","OZDemo_Bill/read"+billOzrName+".ozr");

		oz.sendToActionScript("global.language", "ja_JP");
		oz.sendToActionScript("odi.odinames", "read"+billOzrName);
 		oz.sendToActionScript("odi.readBill.pcount", "2");
		oz.sendToActionScript("odi.readBill.args1", "reportNum="+reportNum);
		oz.sendToActionScript("odi.readBill.args2", "userNum="+userNum);

		oz.sendToActionScript("export.format","pdf");
		oz.sendToActionScript("export.filename",reportNum+"_BillSheet_"+now.getFullYear() + (now.getMonth()+1).toString().padStart(2,'0') + now.getDate().toString().padStart(2,'0') + "_" + now.getHours().toString().padStart(2,'0') + now.getMinutes().toString().padStart(2,'0') + now.getSeconds().toString().padStart(2,'0'));
		oz.sendToActionScript("pdf.fontembedding","true");
		return true;
	}
	var opt = [];
	opt["print_exportfrom"] = "server"; //인쇄 PDF 익스포트 작업을 서버와 통신하여 동작
	opt["save_exportfrom"] = { "pdf" : "server" }; //PDF 익스포트 작업을 서버와 통신하여 동작 
	var isFont = false;		
	function start_viewer() {
        if (isFont) {
        	start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/", opt);
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
