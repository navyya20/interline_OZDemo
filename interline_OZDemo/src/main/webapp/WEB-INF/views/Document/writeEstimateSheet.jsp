<%@page import="jp.co.interlineOZDemo.util.GetProperties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% GetProperties properties= new GetProperties(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="format-detection" content="telephone=no, address=no, email=no"/><!-- IOS에서 일정 형식의 text에 자동링크를 걸어서 JSON.stringify가 되지 않는다. 이를 해결하기위한 코드 -->
<title>estimateSheet</title>
</head>

<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.css" type="text/css"/>
<script src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/ui.dynatree.css" type="text/css"/>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/jquery.dynatree.js" charset="utf-8"></script>
<script type="text/javascript" src="http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/OZJSViewer.js" charset="utf-8"></script>
<!-- If you want to run the HTML5SVG viewer please change the OZJSViewer.js to OZJSSVGViewer.js.
<script type="text/javascript" src="http://192.168.0.103:8888/oz80/ozhviewer/OZJSSVGViewer.js" charset="utf-8"></script>   OZJSViewer.js   OZJSSVGViewer.js
-->


<script src="<c:url value = '../resources/js/estimateSheet.js?ver=6'/>"></script>
<link href="../resources/css/Font-Style.css" rel="stylesheet">
<style>
	.mainMenuButton{	
		display:inline-block;
		width:100px;
	}
	.mainMenuTd{
		width: 100px;
	}
</style>
<body class="pc_body">
	<div id="menuBar" style="position:relative; left: 0px; z-index: 1000; text-align: center; width:100%;">
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
	<div id="OZViewer" style="width:98%;height:98%"></div>
	<script type="text/javascript" >
		//세션으로부터 유저인폼 jsonString을 받는다.
		var stamp = "http://<%out.print(properties.getWebIP());%>/OZDemo/resources/stamp/";
		function getUserInform(){
			//var userInformJsonString = $('#userInformJsonString').html();
			var userInformJsonString = '${userInformJsonString}';
			var userInformJson=JSON.parse(userInformJsonString);
			userInformJson["supplier"]=userInformJson.companyName;
			stamp = stamp + userInformJson.stampFileName;
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
			oz.sendToActionScript("connection.reportname","OZDemo/writeEstimateSheet.ozr");
			oz.sendToActionScript("connection.inputjson", getUserInform());
			oz.sendToActionScript("connection.pcount","3");
			oz.sendToActionScript("connection.args1","repeat=10");
			oz.sendToActionScript("connection.args2","itemJson="+itemJsonString);
			oz.sendToActionScript("connection.args3","stampFileName="+stamp);
			oz.sendToActionScript("pdf.fontembedding","true");
			return true;
		}
		
		var opt = [];
		opt["print_exportfrom"] = "server"; //인쇄 PDF 익스포트 작업을 서버와 통신하여 동작
		opt["save_exportfrom"] = { "pdf" : "server" }; //PDF 익스포트 작업을 서버와 통신하여 동작 
		start_ozjs("OZViewer","http://<%out.print(properties.getOzIP());%>/oz80/ozhviewer/", opt);


		/* function OZUserEvent_OZViewer(inputJsonString, param2, param3) {
			var inputJson=JSON.parse(inputJsonString);
			var processedInputJson = getJsonToSend(inputJson);
			var address="saveEstimate";
			$('#wrapper').css('z-index',2);
			$('#wrapper').css('visibility','visible');
			$('body').css('cursor','progress');
			sendInputJson(processedInputJson,address);
		} */

		//보존버튼을 누루면 작동. 
		//뷰어의 모든 값을 제이슨스트링으로 가져옴.
		//parse/직렬화되어있는 아이템을 병렬화하여 jsonString으로/ 병렬화된 item의 JsonString과 견적서객체 ajax로 보냄.
		function saveButton(){
			var inputJsonString = OZViewer.GetInformation("INPUT_JSON_ALL");
			console.log("제이슨:"+inputJsonString);
			var inputJson=JSON.parse(inputJsonString);
			var processedInputJson = getJsonToSend(inputJson);
			var address="saveEstimate";
			$('#wrapper').css('z-index',2);
			$('#wrapper').css('visibility','visible');
			$('body').css('cursor','progress');
			sendInputJson(processedInputJson,address);
		}

		//모도루 버튼 클릭시 작동. 메인메뉴로 돌아간다.
		function cancelButton(){
			var userInformJsonString = $('#userInformJsonString').html();
			//alert("userInformJsonString:"+userInformJsonString);
			location.href="memberMain";
		}
	</script>
	<div id="wrapper" style="z-index: -1; display: table; background-color:rgb(225,225,225); position: absolute; left: 0%; top: 0%; visibility: hidden; width: 100%; height:170%; opacity: 0.5;"></div>
	
</body>
</html>