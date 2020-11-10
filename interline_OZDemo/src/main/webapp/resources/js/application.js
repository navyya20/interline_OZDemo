function save(reportNum){
	var inpuJson=OZViewer.GetInformation("INPUT_JSON_ALL"); //입력된 값을 전부 받아오기
	var applicationData = JSON.parse(inpuJson);
	
	var ApplicationName = applicationData.name.length;
	var ApplicationMobile = applicationData.mobile;
	var ApplicationPost = applicationData.post;
	var ApplicationAddress = applicationData.address.length;
	var ApplicationFrontIdentification = applicationData.FrontIdentification.length;
	var ApplicationBackIdentification = applicationData.BackIdentification.length;
	var ApplicationSign = applicationData.sign.length;
	
	var phoneCheck = /^\d{2,3}-\d{3,4}-\d{4}$/;
	var postCheck = /^\d{2,3}-\d{3,4}$/;
	
	if(phoneCheck.test(ApplicationMobile) && postCheck.test(ApplicationPost) 
			&& 0<ApplicationName && ApplicationName<=20 && 0<ApplicationAddress 
			&& ApplicationAddress<=75 && 0< ApplicationFrontIdentification 
			&& 0<ApplicationBackIdentification && 0<ApplicationSign && ApplicationSign<15000 ){
		
		applicationData["FrontIdentification"]="";
		applicationData["BackIdentification"]="";
		applicationData["jsonString"]=inpuJson;
		
		$.ajax({
			url: "saveApplication",
			type: 'POST',
			data: applicationData,
			success: function(data){
								
				alert("申込書を作成しました。");
				location.href="applicationMainMenu";
			},
			error: function(e){
				console.log(JSON.stringify(e));
				alert('エラー！');
			}
		});
	}
	
	if(0>=ApplicationName || ApplicationName>20){
		alert("名前は1~20文字です。");
	}else if(!phoneCheck.test(ApplicationMobile)){
		alert("携帯電話は123-1234-1234形式でお願いします。");
	}else if(!postCheck.test(ApplicationPost)){
		alert("ポストは123-1234形式でお願いします。");
	}else if(0>=ApplicationAddress || ApplicationAddress>75){
		alert("住所は1~75文字です。");
	}else if(0>= ApplicationFrontIdentification){
		alert("身分証明書の表面を入力してください。");
	}else if(0>= ApplicationBackIdentification){
		alert("身分証明書の裏面を入力してください。");
	}else if(0>=ApplicationSign){
		alert("サインを入力してください。");
	}else if(ApplicationSign>15000){
		alert("サインが長いです。");
	}
	
	return false;
}

function back(){
	location.href="applicationMainMenu";
}