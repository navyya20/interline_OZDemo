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
<script src="<c:url value = '../resources/js/agreement.js?ver=6'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
<title>writeAgreementMobile</title>

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
<div id="menuBar" class="mobile_body" style="position:relative; left: 0px; z-index: 1000; text-align: center; width:100%;">
		<table style="text-align: center; margin: auto;">
			<tr>
				<td class="mainMenuTd">
					<span id="updateMyProfile" class="pc_font_button1 mainMenuButton move_btn" onclick="saveButton()">作成</span>
				</td>
				<td class="mainMenuTd"></td>
				<td class="mainMenuTd">
					<span id="writeNewEstimateSheet" class="pc_font_button1 mainMenuButton" onclick="cancelButton()">戻る</span>
				</td>
			</tr>
		</table>
	</div>
<div id="OZViewer" style="width:100%;height:100%;overflow:hidden"></div>
<script type="text/javascript" >
function getUserInform(){
	//var userInformJsonString = $('#userInformJsonString').html();
	var userInformJsonString = '${userInformJsonString}';
	var userInformJson=JSON.parse(userInformJsonString);
	userInformJsonString = JSON.stringify(userInformJson);
	console.log("userInformJsonString:"+userInformJsonString);
	$('#userInform').html(userInformJsonString);
	return userInformJsonString;
}
function SetOZParamters_OZViewer(){
var oz;
oz = document.getElementById("OZViewer");
oz.sendToActionScript("viewer.ignore_disable_color_inputcomponent","true");
oz.sendToActionScript("viewer.external_functions_path","ozp://OZDemo/JS/estimateSheet.js");
oz.sendToActionScript("connection.servlet","http://<%out.print(properties.getOzIP());%>/oz80/server");
oz.sendToActionScript("connection.reportname","OZDemo_Agreement/Agreement/writeAgreement.ozr");
oz.sendToActionScript("connection.inputjson", getUserInform());
oz.sendToActionScript("eform.signpad_type", "dialog");
oz.sendToActionScript("pdf.fontembedding","true");
return true;
}
var opt = [];
opt["print_exportfrom"] = "server"; //인쇄 PDF 익스포트 작업을 서버와 통신하여 동작
opt["save_exportfrom"] = { "pdf" : "server" }; //PDF 익스포트 작업을 서버와 통신하여 동작 
start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/",true,opt);


function saveButton(){
	var inputJsonString = OZViewer.GetInformation("INPUT_JSON_ALL");
	console.log("제이슨:"+inputJsonString);
	var inputJson=JSON.parse(inputJsonString);
	var processedInputJson = getJsonToSend(inputJson);
	var address="saveAgreement";
	$('#wrapper').css('z-index',2);
	$('#wrapper').css('visibility','visible');
	$('body').css('cursor','progress');
	sendInputJson(processedInputJson,address);
}

//모도루 버튼 클릭시 작동. 메인메뉴로 돌아간다.
function cancelButton(){
	//var userInformJsonString = $('#userInformJsonString').html();
	//alert("userInformJsonString:"+userInformJsonString);
	location.href="agreementMainMenu";
}
</script>
<div id="wrapper" style="z-index: -1; display: table; background-color:rgb(225,225,225); position: absolute; left: 0%; top: 0%; visibility: hidden; width: 100%; height:170%; opacity: 0.5;"></div>

</body>
</html>