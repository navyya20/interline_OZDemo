<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% GetProperties properties= new GetProperties(); %>
<% String device = (String)request.getSession().getAttribute("device"); %>
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
<title>writeMemorandumMobile</title>

<style>
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
	    	  $("body").addClass('pc_body');
		      $("#title").addClass("pc_font_title");
		      $(".btn").addClass("pc_font_button2");
	      }
	    }
	  }
	
});

function save(reportNum){
	var memorandumData=JSON.parse(OZViewer.GetInformation("INPUT_JSON_ALL")); //입력된 값을 전부 받아오기
	var name = memorandumData.customer;
	console.log(memorandumData);

		$.ajax({
			url: "saveMemorandum",
			type: 'POST',
			data: memorandumData,
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
		oz.sendToActionScript("connection.servlet", "http://<%out.print(properties.getOzIP());%>/oz80/server");
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
	
	var opt = [];
	opt["print_exportfrom"] = "server"; //인쇄 PDF 익스포트 작업을 서버와 통신하여 동작
	opt["save_exportfrom"] = { "pdf" : "server" }; //PDF 익스포트 작업을 서버와 통신하여 동작 
	start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/",true,opt);
	
</script>
</body>
</html>