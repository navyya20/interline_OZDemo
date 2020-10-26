<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% GetProperties properties= new GetProperties(); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="format-detection" content="telephone=no, address=no, email=no"/><!-- IOS에서 일정 형식의 text에 자동링크를 걸어서 JSON.stringify가 되지 않는다. 이를 해결하기위한 코드 -->
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.css"/>
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog.min.css"/>
<script type="text/javascript" src="http://dev.jtsage.com/cdn/simpledialog/latest/jquery.mobile.simpledialog2.min.js"></script>

<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/OZJSSVGViewer.js" charset="utf-8"></script>
<!-- If you want to run the HTML5SVG viewer please change the OZJSViewer.js to OZJSSVGViewer.js.
<script type="text/javascript" src="http://kimServer:8080/ozrviewer/OZJSSVGViewer.js" charset="utf-8"></script>
-->
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.mouseSwipe.js" type="text/javascript"></script>
<script src="<c:url value = '../resources/js/agreement.js?ver=6'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
<title>readAgreementMobile</title>

<style>
.mobile_body{
	font-family: -apple-system-subset,Helvetica,Hiragino Kaku Gothic ProN,sans-serif;
	-webkit-text-size-adjust:300%;
}
.mainMenuButton{	
		display:inline-block;
		width:100px;
	}
	.mainMenuTd{
		width: 200px;
	}
</style>

</head>
<body id="body_Mobile" style="height:100%; overscroll-behavior:none;">
<div id="menuBar" style="position:relative; left: 0px; z-index: 1000; text-align: center; width:100%;">
	<table style="text-align: center; margin: auto;">
		<tr>
			<td class="mainMenuTd">
				<span id="writeNewEstimateSheet" class="pc_font_button1 mainMenuButton" onclick="cancelButton()">戻る</span>
			</td>
		</tr>
	</table>
</div>
<div id="OZViewer" style="width:100%;height:100%;overflow:hidden"></div>
<script type="text/javascript" >
//세션으로부터 유저인폼 jsonString을 받는다.
var userNum = "${userNum}";
var reportNum = "${reportNum}";
console.log("userNum:"+userNum);
console.log("reportNum:"+reportNum);
var now = new Date();
var fileName=reportNum+"_OZDemo_"+now.getFullYear() + numFormat((now.getMonth()+1)) + numFormat(now.getDate()) + "_" + numFormat(now.getHours()) + numFormat(now.getMinutes()) + numFormat(now.getSeconds());
console.log("filename:"+fileName);
function SetOZParamters_OZViewer(){
	var oz;
	oz = document.getElementById("OZViewer");
	oz.sendToActionScript("viewer.ignore_disable_color_inputcomponent","true");
	oz.sendToActionScript("viewer.external_functions_path","ozp://OZDemo/JS/estimateSheet.js");
	oz.sendToActionScript("font.fontnames","font1");
	oz.sendToActionScript("font.font1.name","ＭＳ ゴシック");
	oz.sendToActionScript("font.font1.replacefont","meiryo");
	oz.sendToActionScript("connection.servlet","http://<%out.print(properties.getOzIP());%>/oz80/server");
	oz.sendToActionScript("connection.reportname","OZDemo_Agreement/Agreement/readAgreement.ozr");
	oz.sendToActionScript("global.language", "ja");
	oz.sendToActionScript("odi.odinames","readAgreement");
	oz.sendToActionScript("odi.readAgreement.pcount","2");
	oz.sendToActionScript("odi.readAgreement.args1","userNum="+userNum);
	oz.sendToActionScript("odi.readAgreement.args2","reportNum="+reportNum);
	oz.sendToActionScript("export.filename",reportNum+"_OZDemo_"+now.getFullYear() + (now.getMonth()+1).toString().padStart(2,'0') + now.getDate().toString().padStart(2,'0') + "_" + now.getHours().toString().padStart(2,'0') + now.getMinutes().toString().padStart(2,'0') + now.getSeconds().toString().padStart(2,'0'));
	oz.sendToActionScript("pdf.fontembedding","true");
	return true;
}
var opt = [];
opt["print_exportfrom"] = "server"; //인쇄 PDF 익스포트 작업을 서버와 통신하여 동작
opt["save_exportfrom"] = { "pdf" : "server" }; //PDF 익스포트 작업을 서버와 통신하여 동작 
start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/",true,opt);

function OZUserEvent_OZViewer(inputJsonString, param2, param3) {
}
//pdf저장시 파일이름에  6월 을  06월로 바꿔주기위함
function numFormat(variable) { 
	variable = Number(variable).toString(); 
	if(Number(variable) < 10 && variable.length == 1) variable = "0" + variable; 
	return variable; 
}
function cancelButton(){
	var userInformJsonString = $('#userInformJsonString').html();
	//alert("userInformJsonString:"+userInformJsonString);
	location.href="agreementMainMenu";
}
</script>

</body>
</html>