//입력한 청구서 정보를 데이터베이스에 저장
function save(reportNum){
	var billData=JSON.parse(OZViewer.GetInformation("INPUT_JSON_ALL")); //입력된 값을 전부 받아오기
	var bankName_Len = billData.bankName.length;
	var depositeClassification_Len = billData.depositeClassification.length;
	var accountNumber = billData.accountNumber;
	var accountOwner_Len = billData.accountOwner.length;
	var hurigana_Len = billData.hurigana.length;
	var accountcheck=/^\d{1,4}-\d{1,8}$/; //어카운트 체크를 위한 값(123-1234567)방식-최대값(1234-12345678)
	
	if(0< bankName_Len && bankName_Len <= 30 && 0< depositeClassification_Len && depositeClassification_Len <= 10 && accountcheck.test(accountNumber) && 0< accountOwner_Len && accountOwner_Len <= 30 && 0< hurigana_Len && hurigana_Len <= 60){

		if(confirm("請求書を保存しますか?")){
		
			billData["reportNum"] =reportNum; //견적서번호
			billData["billNum"] =reportNum;
			billData["sum"] = billData["sum"].replace(/[^0-9]/g,""); //금액의 ￥표시 삭제, 숫자만 받아오기
			billData["sumWithTax"] = billData["sumWithTax"].replace(/[^0-9]/g,"");
			billData["sumWithTax2"] = billData["sumWithTax2"].replace(/[^0-9]/g,"");

			$.ajax({
				url: "saveBill",
				type: 'POST',
				data: {jsonStr:JSON.stringify(billData)},
				dataType:'text',
				success: function(data){
					alert("保存成功");
					location.href="memberMain";
				},
				error: function(e){
					console.log(JSON.stringify(e));
					alert('エラー！');
				}
			});
		}
	}


	if(0>=bankName_Len || bankName_Len>30){
		alert("銀行名は1~30文字です。");
	}else if(0>=depositeClassification_Len || depositeClassification_Len>10){
		alert("預金区分は1~10文字です。");
	}else if(!accountcheck.test(accountNumber)){
		alert("口座番号は123-1234567形式でお願いします。");
	}else if(0>=accountOwner_Len || accountOwner_Len>30){
		alert("口座名義人は1~30文字です。");
	}else if(0>=hurigana_Len || hurigana_Len>60){
		alert("口座名義人ふりがなは1~60文字です。");
	}		

	return false;
}

function back(){
	location.href="memberMain";
}
