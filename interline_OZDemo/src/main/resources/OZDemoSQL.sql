/* **업데이트 내용
김대은 ver4: 유저인폼에 대표자정보, 전화번호 칼럼 추가
김대은 ver6: estimateItems 에 price(단가*수량 한것) 칼럼 추가
		견적서에 공급자 정보 추가
		청구서에도 post 칼럼 삭제 ---> 그냥 주소에 우편번호 쓰게하는쪽이 편할것같음.
		회원정보에 post칼럼 삭제
		아이템테이블에 basicPrice 칼럼명  unitPrice로 변경
		아이템테이블에 unit varchar(6) 추가  단위를 저장하는 칼럼
김대은 ver7: post 칼럼 다시 추가
김대은 ver8: 견적서 컬럼 대거 수정(중복된 컬럼, 오즈 및 vo 와 불일치하는 컬럼 정정)
김대은 ver9: 견적서에 유저넘버 컬럼 추가. (유저별로 자기 견적서만 볼수있게)
                  not Null들 제거
김대은 ver10: 견적아이템 에 오타 수정 imtemNum -> itemNum
박승훈 var11: 청구서 테이블 수정 incharge -> representative
			   cautions 항목 삭제
	     		   hurigana 30 -> 60  크기 변경
	     유저 테이블 수정  hurigana 30 -> 60   크기 변경

김대은 ver13: 견적서테이블에 dateForDisplaying 칼럼추가. 표시용날짜 저장할 곳
김대은 ver14: 최대입력 길이 제한 변경 반영
		userInform-address 100 ->75
			-hurigana 30 ->60
		estimateSheet -address 100->75
			-representative 10->20
			-reportName 30 ->25
			-payCondition 30 ->10
			-cautions 1000->400
			-sum int -> BigInt
			-sumWithTax int -> BigInt
			-sumWithTax2 int -> BigInt
		estimateItems -price int -> BigInt
			-note 삭제
		billInform -hurigana 30->60]

박승훈 var15: 청구서 테이블 수정 
			   sum int -> BigInt
			   sumWithTax int -> BigInt
		    	   sumWithTax2 int -> BigInt
  
김대은 ver16: 도장을 위한 테이블 수정 
			alter table estimateSheet add stamp varchar(100) default "http://192.168.1.20:8887/OZDemo/resources/stamp/defaultStamp.png";
*/






/*
*********************************계경및 스키마 생성***************************************

create database interline_OZdemo default character set utf8;

SET GLOBAL validate_password.policy=low;
SET GLOBAL validate_password.length=4;
SET GLOBAL validate_password.mixed_case_count=0;
SET GLOBAL validate_password.number_count=0;
SET GLOBAL validate_password.special_char_count=0;

create user 'hrhr3'@'%' identified by "hrhr";

GRANT ALL privileges ON interline_OZdemo.* TO 'hrhr3'@'%';

SET GLOBAL log_bin_trust_function_creators = 1;

************************************************************************************
*/

use interline_OZdemo;

create table userInform(
  userNum INT NOT NULL,
  userId VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  companyName VARCHAR(60) DEFAULT '○○株式会社',
  representative VARCHAR(40) DEFAULT '鈴木キョースケ',
  phoneNumber VARCHAR(20) DEFAULT '03-111-1111',
  address VARCHAR(180) DEFAULT '東京都○○区○○丁目○番○号○○ビル○F',
  post VARCHAR(10) DEFAULT '111-1111',
  authority VARCHAR(1) DEFAULT 'u',
  stampFileName VARCHAR(50) DEFAULT 'defaultStamp.png',
  startDate DATETIME DEFAULT CURRENT_TIMESTAMP,
  bankName VARCHAR(30) DEFAULT '○○銀行',
  depositeClassification VARCHAR(10) DEFAULT '普通預金',
  accountNumber VARCHAR(20) DEFAULT '111-11111',
  accountOwner VARCHAR(30) DEFAULT '○○株式会社',
  hurigana VARCHAR(60) DEFAULT 'ﾏﾙﾏﾙｶﾌﾞｼｷｶﾞｲｼｬ',
    
    primary key (userNum),
    UNIQUE key (userId)
); 

create table estimateType(
	estimateType varchar(2),
    estimateName varchar(20),
    EstimateOzrName varchar(20),
    EstimateDBName varchar(20),
    BillOzrName varchar(20),
    BillDBName varchar(20),
    ItemDBName varchar(20),
    jsName varchar(20),
    primary key (estimateType)
);



create table estimateSheet(
	reportNum int(5) not null,
	userNum INT NOT NULL,
	reportDate datetime default current_timestamp,
    estimateType varchar(2) default "a", 
    
	supplier varchar(60) default "○○株式会社",
	address varchar(180) default "東京都○○区○○丁目○番○ ○○ビル○F",
	post VARCHAR(10) DEFAULT '111-1111',
	phoneNumber varchar(20) default "03-111-1111",
	representative varchar(40),
	
	receiver varchar(60) default "□□株式会社",
	reportName varchar(120) default "OZ-DEMOの件",
	deadline datetime,
	supplyPoint varchar(180) default "東京都□□区□□丁目□番□号□□ビル□F",
	payCondition varchar(60) default "前払い",

	cautions varchar(600),
	sum BigInt,
	sumWithTax BigInt,
	sumWithTax2  BigInt,
	state varchar(2),
	dateForDisplaying date,
	expirationDate varchar(60),
	stamp varchar(100) default "http://192.168.1.20:8887/OZDemo/resources/stamp/defaultStamp.png",
	PRIMARY KEY(reportNum),
	FOREIGN KEY (userNum) REFERENCES userInform (userNum) on delete cascade
);

create table estimateItems(
    reportNum int(5) not null,
    itemName varchar(120),
    itemNum int(5),
    amount int not null default 0,
    unit varchar(4),
    unitPrice int not null default 0,
    price BigInt not null default 0,
    FOREIGN KEY (reportNum) REFERENCES estimateSheet (reportNum) on delete cascade
);

    

create table items(
	itemName varchar(30),
    itemNum int(5),
    unit varchar(6),
    unitPrice int(8),
    primary key (itemNum)
);

create table billInform(
   billNum int(5) not null,
   reportNum int(5) not null,
	userNum int,
	billDate datetime default current_timestamp,
	supplier varchar(60),
	address varchar(180),
	post VARCHAR(10) DEFAULT '111-1111',
	phoneNumber varchar(20),
	representative varchar(40),
	receiver varchar(60) not null,
	supplyPoint varchar(180),
	sum BigInt,
	sumWithTax BigInt,
	sumWithTax2 BigInt,
   bankName varchar(30),
   depositeClassification varchar(10),
   accountNumber varchar(20),
   accountOwner varchar(30),
   hurigana varchar(60),
	stamp varchar(100),
    state varchar(2),
   PRIMARY KEY(billNum),
   foreign key(reportNum) references estimateSheet(reportNum) on delete cascade
); 




/*동의서 시스템에 쓰이는 테이블들입니다.*/
/*
수정사항
2020.10.16 김대은
	-리스트에 문서들을 나열할때 동의서인지 각서인지 나타내기위해 각각에 sort 칼럼을 추가하였습니다.
	-동의서와 각서의 systemReportNum을 AutoIncrease로 처리하였지만 해당 기능은 그 테이블에 한정인것이 확인되었습니다. 따라서 AutoIncrease 속성을 제거했습니다.
	 systemReportNum은 전역에서 유니크해야하기때문에 MySql에서 유일한 seq방법인 시퀀스테이블과 함수생성으로 해결하였습니다. 사용법은 함수생성부분 아래 설명에 있습니다.
*/

create table agreementUserInform(

	userNum	int not null,
	company	varchar(20) DEFAULT '○○株式会社',
	representative	varchar(20) DEFAULT '鈴木キョースケ',
	post	varchar(10) DEFAULT '111-1111',
	address	varchar(75) DEFAULT '東京都○○区○○丁目○番○号○○ビル○F',
	email	varchar(30) DEFAULT 'example123@example.com',
	phoneNumber varchar(20) DEFAULT '03-111-1111',
	incharge varchar(20) DEFAULT '橋本桜',

	primary key (userNum),
    	FOREIGN KEY (userNum) REFERENCES userInform (userNum) on delete cascade

);

create table agreementAgreement(
	userNum int not null,
	systemReportNum	int(5) not null,
	reportNum int(5) not null,
	systemDate datetime default current_timestamp,
	
	company	varchar(20),
	address	varchar(75),
	post	varchar(10),
	phoneNumber varchar(20),
	email	varchar(30),
	incharge varchar(20),
	
	agreementDate datetime,
	customer	varchar(20),
	sign	varchar(15000),
    sort varchar(10),
	
	primary key (systemReportNum),
	FOREIGN KEY (userNum) REFERENCES userInform (userNum) on delete cascade

);


create table agreementMemorandum(
	userNum int not null,
	systemReportNum	int(5) not null,
	reportNum int(5) not null,
	systemDate datetime default current_timestamp,

	company	varchar(20),
	representative	varchar(20),
	post	varchar(10),
	address	varchar(75),

	memorandumDate datetime,
	customer	varchar(20),
	sign	varchar(15000),
	sort varchar(10),
    
	primary key (systemReportNum),
    	FOREIGN KEY (userNum) REFERENCES userInform (userNum) on delete cascade
);

/*신청서 시스템*/
create table application(
	userNum int not null,
	systemReportNum	int not null,
	reportNum int not null,
	applicationDate datetime not null,
	systemDate datetime default current_timestamp,
	name varchar(20) not null,
	nameHurigana varchar(30),
	telephone varchar(20),
	mobile varchar(20),
	birth datetime,
	gender varchar(10),
	age int,
	post varchar(10) not null,
	address varchar(75) not null,
	addressHurigana varchar(100),
	PDFPath varchar(100),
	sign varchar(15000) not null,
     	
	FOREIGN KEY (userNum) REFERENCES userInform (userNum) on delete cascade
);






/*시퀀스를 생성합니다.*/
CREATE TABLE master_seq(
     id int not null, 
     seq_name varchar(50) not null unique
 );
 
 
DROP FUNCTION IF EXISTS get_seq;  /*혹시 이미 만들어져 있다면 삭제합니다.*/
DELIMITER $$
CREATE FUNCTION get_seq (p_seq_name VARCHAR(45))
RETURNS INT READS SQL DATA
BEGIN
DECLARE RESULT_ID INT;
UPDATE master_seq SET id = LAST_INSERT_ID(id+1) 
WHERE seq_name = p_seq_name;
SET RESULT_ID = (SELECT LAST_INSERT_ID());
RETURN RESULT_ID;
END $$
DELIMITER ;

/*생성시       INSERT INTO master_seq VALUES (1, 'seq_name')*/
/*사용시     SELECT get_seq('seq_name');       */

/*동의서 시퀀스*/
INSERT INTO master_seq VALUES (1, 'agreementSeq');
/*신청서 시퀀스*/
INSERT INTO master_seq VALUES (0, 'applicationSeq');
/*견적서 시퀀스*/
INSERT INTO master_seq VALUES (0, 'estimateSeq');

/*관리자 계정*/
INSERT INTO `interline_OZdemo`.`userInform` (`userNum`,`userId`, `password`, `companyName`, `representative`, `phoneNumber`, `address`, `post`, `authority`, `stampFileName`, `startDate`, `bankName`, `depositeClassification`, `accountNumber`, `accountOwner`, `hurigana`) VALUES (0,'admin', 'admin01', '○○株式会社', '鈴木キョースケ', '03-111-1111', '東京都○○区○○丁目○番○号○○ビル○F', '111-1111', 'a', 'defaultStamp.png', '2020-08-26 15:31:56', '○○銀行', '普通預金', '111-11111', '○○株式会社', 'ﾏﾙﾏﾙｶﾌﾞｼｷｶﾞｲｼｬ');

/*셈플 견적서폼 정보*/
insert into estimateType values ("a","サンプルタイプA","EstimateSheet","estimateSheet","Bill","billInform","items");
