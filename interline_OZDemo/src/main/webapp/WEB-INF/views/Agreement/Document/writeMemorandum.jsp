<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>

<!DOCTYPE html>
<html style="height:100%">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css"/>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://127.0.0.1:8888/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://127.0.0.1:8888/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://127.0.0.1:8888/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>
<!-- If you want to run the HTML5SVG viewer please change the OZJSViewer.js to OZJSSVGViewer.js.
<script type="text/javascript" src="http://127.0.0.1:8888/oz80/ozhviewer/OZJSSVGViewer.js" charset="utf-8"></script>
-->
<!-- When using a PDF document or attaching a PDF file to a report, please set compatibility.js and pdf.js to be loaded.
<script type="text/javascript" src="http://127.0.0.1:8888/oz80/ozhviewer/pdf_js/web/compatibility.js" charset="utf-8"></script>
<script type="text/javascript" src="http://127.0.0.1:8888/oz80/ozhviewer/pdf_js/build/pdf.js" charset="utf-8"></script>
<script type="text/javascript">
	if (typeof PDFJS != "undefined") {
		//cMapUrl must match the path of pdf.js file. If it is different from the default path, be sure to correct it.
        PDFJS.cMapUrl = "http://127.0.0.1:8888/ozrviewer/pdf_js/web/cmaps/";
        PDFJS.cMapPacked = true;
    }
</script>
-->
<link href="../resources/css/Font-Style.css" rel="stylesheet">

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

function save(reportNum){
	var memorandumData=OZViewer.GetInformation("INPUT_JSON_ALL"); //입력된 값을 전부 받아오기
	console.log(memorandumData);

		$.ajax({
			url: "saveMemorandum",
			type: 'POST',
			data: {jsonStr:memorandumData},
			success: function(data){
								
				alert("覚書を作成しました。");
				location.href="agreementMainMenu";
			},
			error: function(e){
				console.log(JSON.stringify(e));
				alert('エラー！');
			}
		});

	return false;
}

function back(){
	location.href="agreementMainMenu";
}
</script>
</head>
<body style="width:98%;height:98%">
<div id="menuBar" style="position:relative; left:0px; z-index:1000; text-align: center; width:98%;">
<table style="text-align:center; margin:auto;"><tr>
<td class="memorandumMenuTd"><span id="save_Btn"  class="pc_font_button1 memorandumMenuButton" onclick="save(${reportNum})">作成</span></td>
<td class="memorandumMenuTd"></td>
<td class="memorandumMenuTd"><span id="back_Btn"  class="pc_font_button1 memorandumMenuButton" onclick="back()">戻る</span></td>
</tr></table>
</div>
<div id="OZViewer" style="width:98%;height:98%"></div>
<script type="text/javascript">

	var userNum = "${userNum}";
	var stamp = "http://<%out.print(properties.getWebIP());%>/files/OZDemoEstimateSheet/stamp/"+"${stampFileName}";

	function SetOZParamters_OZViewer() {
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("connection.servlet", "http://127.0.0.1:8888/oz80/server");
		oz.sendToActionScript("connection.reportname", "OZDemo_Agreement/Memorandum/writeMemorandum.ozr");
		oz.sendToActionScript("connection.pcount","1");
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
	start_ozjs("OZViewer", "http://127.0.0.1:8888/oz80/ozhviewer/");
</script>
</body>
</html>
