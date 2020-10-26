<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>

<!DOCTYPE html>
<html style="height:100%">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.css" type="text/css"/>
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
<script src="<c:url value = '../resources/js/memorandum.js'/>" charset="utf-8"></script>

<title>writeMemorandum</title>
<style>
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
	    	  $("body").addClass('pc_body');
		      $("#title").addClass("pc_font_title");
		      $(".btn").addClass("pc_font_button2");
	      }
	    }
	  }
	
});

</script>
</head>
<body>
<div id="menuBar" style="position:relative; left:0px; z-index:1000; text-align: center; width:100%;">
<table style="text-align:center; margin:auto;"><tr>
<td class="memorandumMenuTd"><span id="save_Btn"  class="pc_font_button1 memorandumMenuButton" onclick="save(${reportNum})">作成</span></td>
<td class="memorandumMenuTd"></td>
<td class="memorandumMenuTd"><span id="back_Btn"  class="pc_font_button1 memorandumMenuButton" onclick="back()">戻る</span></td>
</tr></table>
</div>
<div id="OZViewer" style="width:100%;height:100%"></div>
<script type="text/javascript">

	var userNum = "${userNum}";
	var stamp = "http://<%out.print(properties.getWebIP());%>/files/OZDemoEstimateSheet/stamp/"+"${stampFileName}";

	function SetOZParamters_OZViewer() {
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("connection.servlet", "http://<%out.print(properties.getOzIP());%>/oz80/server");
		oz.sendToActionScript("connection.reportname", "OZDemo_Agreement/Memorandum/writeMemorandum.ozr");
		oz.sendToActionScript("connection.pcount","1");
		oz.sendToActionScript("viewer.pagedisplay", "singlepagecontinuous");
		
		oz.sendToActionScript("connection.args1","stampFileName="+stamp);

		oz.sendToActionScript("global.language", "ja_JP");
		oz.sendToActionScript("odi.odinames", "writeMemorandum");
 		oz.sendToActionScript("odi.writeMemorandum.pcount", "1");
		oz.sendToActionScript("odi.writeMemorandum.args1", "userNum="+userNum);

		oz.sendToActionScript("eform.signpad_type","dialog");

		oz.sendToActionScript("export.format","pdf");
		oz.sendToActionScript("pdf.filename","Memorandum");
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