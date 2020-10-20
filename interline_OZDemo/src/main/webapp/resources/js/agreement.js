

//input: 폼에서 받은 제이슨
/*output: ajax로 보내기위해 체크및 가공을 합니다.
  
 */
 
function getJsonToSend(inputJson){
	var sign = inputJson.sign;
	//DB의 sign칼럼이 15000자 제한이기 때문에 하는 체크
	if(sign.length > 15000){
		alert("사인의 data 길이가 너무 깁니다.");
	}
	return inputJson;
}


//input: 가공된 inputJson , 보낼 주소
//output: 없음.
// 가공된 json을 address에 POST 방식으로 ajax로 보낸다.
function sendInputJson(processedInputJson,address){
	console.log("sendInputJson함수 실행");
	console.log("address:"+address);
	console.log(JSON.stringify(processedInputJson));
	$.ajax(
			{
				url: address,
				type: 'POST',
				data: processedInputJson,
				success: function(data){
					alert("同意書を作成しました。");
					location.href="agreementMainMenu";
				},
				error: function(e){
					console.log(JSON.stringify(e));
					alert('エラー！');
				}
			}		
	);
}
