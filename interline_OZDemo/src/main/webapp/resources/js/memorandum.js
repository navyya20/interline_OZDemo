function save(){
	var memorandumData=JSON.parse(OZViewer.GetInformation("INPUT_JSON_ALL")); //입력된 값을 전부 받아오기
	var customer_Len = memorandumData.customer.length;
	var sign_Len = memorandumData.sign.length;
	
	if( 0<customer_Len && customer_Len<20 && 0<sign_Len && sign_Len<15000 ){
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
	}

	if(0>=customer_Len || customer_Len>20){
		alert("名前は1~20文字です。");
	}else if(0>=sign_Len){
		alert("サインを入力してください。");
	}else if(sign_Len>15000){
		alert("サインが長いです。");
	}
	
	return false;
}

function back(){
	location.href="agreementMainMenu";
}