var itemJson={"item": 
					[{"itemNumber": 1111, "itemName": "item1", "unit": "台", "unitPrice": 500}, 
					{"itemNumber": 1112, "itemName": "item2", "unit": "台", "unitPrice": 1000},
					{"itemNumber": 1113, "itemName": "item3", "unit": "台", "unitPrice": 2000},
					{"itemNumber": 1114, "itemName": "item4", "unit": "台", "unitPrice": 3000},
					{"itemNumber": "", "itemName": "", "unit": "", "unitPrice": 0}]
			};
var itemJsonString=JSON.stringify(itemJson);

//견적된 아이템들의 jsonObject를 받는다. vo의 배열이기 때문에 ozr에서 인덱스를 가진 폼아이디와 파라메터를 일치시켜줘야한다.
//input: 견적된 아이템들의 jsonObject
//output: 병렬의 아이템들을 직렬배열 화한다. 단, 파라메터에 인덱스가 붙는다. 단, 가격의경우 엔표시가 붙고 숫자의경우 3자리마다 컴마가 붙는다. 
function serializeEstimateItemsJsonObject(estimateItemsJsonObject){
	var jsonObject = new Object();
	for(var i = 0 ; i < estimateItemsJsonObject.length ; i++){
		jsonObject["itemName"+(i+1)] = estimateItemsJsonObject[i].itemName;
		jsonObject["itemNum"+(i+1)] = estimateItemsJsonObject[i].itemNum;  //아이템넘버는 지금 사용하지않지만 혹시몰라서 칼럼을 남겨두었다. 일단 포린키이기때문에 지우면 안된다.
		jsonObject["amount"+(i+1)] = comma(estimateItemsJsonObject[i].amount);
		jsonObject["unit"+(i+1)] = estimateItemsJsonObject[i].unit;
		jsonObject["unitPrice"+(i+1)] = "￥" + comma(estimateItemsJsonObject[i].unitPrice);
		jsonObject["price"+(i+1)] = "￥" + comma(estimateItemsJsonObject[i].price);
		jsonObject["note"+(i+1)] = estimateItemsJsonObject[i].note;
	}
	return jsonObject;
}


//input: 폼에서 받은 제이슨
//output: 견적서 VO에 맞는 제이슨과  견적아이템들VO에 맞는 제이슨을 포함한 제이슨
//견적서 VO는 컨트롤러에서 VO로 받을수있게. 견적아이템들VO는 컨트롤러에서 String 으로 받아 제이슨화 하여 VO리스트화 작업을 거쳐야함.
// "￥1,000"형식을 반드시  1000으로 만들어야함. unitPrice1~13,price1~13,sum,sumWithTax,sumWithTax2 해당.
// 또한 reportNum 에 아무 숫자나 넣어줘야  컨트롤러에서 에러가 안난다.  아니면 컨트롤러에서 받을때 default값을 설정하던가.
function getJsonToSend(inputJson){
	console.log("getJsonToSend함수 실행");
	inputJson.reportNum=0;
	inputJson["sum"] = inputJson["sum"].replace(/,/gi, "").replace(/￥/gi, "");
	inputJson["sumWithTax"] = inputJson["sumWithTax"].replace(/,/gi, "").replace(/￥/gi, "");
	inputJson["sumWithTax2"] = inputJson["sumWithTax2"].replace(/,/gi, "").replace(/￥/gi, "");
	
	var estimateItems = new Array();
	//alert(parseInt(inputJson["repeat"],10));
	for(var i=1 ; i <= parseInt(inputJson["repeat"],10) ; i++){
		if(inputJson["itemName"+i] != ""){
			var item = new Object();
			item.itemNum = inputJson["itemNum"+i];
			item.itemName = inputJson["itemName"+i];
			item.amount = inputJson["amount"+i];
			item.unit = inputJson["unit"+i];
			
			inputJson["unitPrice"+i] =inputJson["unitPrice"+i].replace(/,/gi, "").replace(/￥/gi, "");
			item.unitPrice = inputJson["unitPrice"+i];
			console.log("변환:"+item.unitPrice);
			
			inputJson["price"+i] = inputJson["price"+i].replace(/,/gi, "").replace(/￥/gi, "");
			item.price = inputJson["price"+i];
			console.log("변환:"+item.price);
			
			inputJson["amount"+i] = inputJson["amount"+i].replace(/,/gi, "").replace(/￥/gi, "");
			item.amount = inputJson["amount"+i];
			console.log("변환:"+item.amount);
			
			estimateItems.push(item);
			console.log("아이템 하나 추가");
		}
	}
	console.log("정규화작업완료");
	var estimateItemsJson = new Object();
	estimateItemsJson.itemArray=estimateItems;
	inputJson.estimateItemsString = JSON.stringify(estimateItemsJson);
	console.log(inputJson.estimateItemsString);
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
					alert("見積書を作成しました。");
					location.href="memberMain";
				},
				error: function(e){
					console.log(JSON.stringify(e));
					alert('エラー！');
				}
			}		
	);
}


//숫자에 컴마 붙이기
//input: 인트나 숫자로된 스트링
//output: 3자리 마다 컴마가 붙은 스트링
function comma(num){
	var len, point, str;
	num = num+"";
	point = num.length % 3;
	len= num.length;
	
	str = num.substring(0,point);
	while (point < len){
		if (str != "") str += ",";
		str += num.substring(point, point+3);
		point += 3;
	}
	return str;
}
