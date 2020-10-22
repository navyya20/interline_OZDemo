<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BillSheet</title>
</head>

<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.css" type="text/css"/>
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>
<script src="<c:url value = '../resources/js/billSheet.js'/>" charset="utf-8"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">

<style>
.billMenuButton{
	display:inline-block;
	width:100px;
}
.billMenuTd{
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

</script>
<body>

<div id="menuBar" style="position:relative; left:0px; z-index:1000; text-align: center; width:100%;">
<table style="text-align:center; margin:auto;"><tr>
<td class="billMenuTd"><span id="save_Btn"  class="pc_font_button1 billMenuButton" onclick="save(${reportNum})">作成</span></td>
<td class="billMenuTd"></td>
<td class="billMenuTd"><span id="back_Btn"  class="pc_font_button1 billMenuButton" onclick="back()">戻る</span></td>
</tr></table>
</div>

<div id="OZViewer" style="width:100%;height:98%"></div>
<script type="text/javascript" >
	function SetOZParamters_OZViewer(){
		var reportNum = "${reportNum}";
		var userNum = "${userNum}";

		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("connection.servlet","http://<%out.print(properties.getOzIP());%>/oz80/server");
		oz.sendToActionScript("connection.reportname","OZDemo_Bill/writeBill.ozr");
		
		oz.sendToActionScript("global.language", "ja_JP");
		oz.sendToActionScript("odi.odinames", "writeBill");
 		oz.sendToActionScript("odi.writeBill.pcount", "2");
		oz.sendToActionScript("odi.writeBill.args1", "reportNum="+reportNum);
		oz.sendToActionScript("odi.writeBill.args2", "userNum="+userNum);

		oz.sendToActionScript("export.format","pdf");
		oz.sendToActionScript("pdf.filename","writeBillSheet");
		oz.sendToActionScript("pdf.fontembedding","true");
		return true;
	}
	
	var opt = [];
	opt["print_exportfrom"] = "server"; //인쇄 PDF 익스포트 작업을 서버와 통신하여 동작
	opt["save_exportfrom"] = { "pdf" : "server" }; //PDF 익스포트 작업을 서버와 통신하여 동작 
	start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/", opt);
	
</script>
</body>
</html>
