<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

function back(){
	location.href="agreementMainMenu";
}
</script>

</head>
<body style="width:98%;height:98%">
<div id="menuBar" style="position:relative; left:0px; z-index:1000; text-align: center; width:98%;">
<table style="text-align:center; margin:auto;"><tr>
<td class="memorandumMenuTd"><span id="back_Btn"  class="pc_font_button1 memorandumMenuButton" onclick="back()">戻る</span></td>
</tr></table>
</div>
<div id="OZViewer" style="width:98%;height:98%"></div>
<script type="text/javascript">
	var userNum = "${userNum}";
	var reportNum= "${reportNum}";

	function SetOZParamters_OZViewer() {
		var oz;
		oz = document.getElementById("OZViewer");
		oz.sendToActionScript("connection.servlet", "http://127.0.0.1:8888/oz80/server");
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
	start_ozjs("OZViewer", "http://127.0.0.1:8888/oz80/ozhviewer/");
</script>
</body>
</html>
