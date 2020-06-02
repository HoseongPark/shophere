# ShopHere Webservice

## Over View
상점 예약 시스템 BackEnd API Server 구축

## 오타 & 오류 제보

상단의 [Issue 탭](https://github.com/Lion_Park/shophere/issues)에서 검색 혹은 추가 이슈등록 해주세요!!

## 개발 환경 점검
* **Java 1.8**
* **Gradle 4.X**
* **Spring Boot 2.1.X**
* **H2 DataBase 4.199**

**Gradle 5.X** , **Spring Boot 2.2.x** Version이 업데이트 되었는데 두개의 환경에서는 정상 작동이 되지 않습니다.  
많은 변경점이 있기때문에 개발시 환경을 맞춰주세요.

H2 DataBase의 경우 4.200이 최신인데 버그가 많습니다. 4.199 Version으로 환경을 셋팅 했습니다.

## Local BackEnd Execution
Java 1.8이상 설치가 되어있어야합니다.   

Linux & Mac
* 1. Git Clone
* 2. run.sh 실행

Windows
* 1. 작성 예정

**정상적으로 실행 됬을경우**
![serverOpen](https://user-images.githubusercontent.com/39320966/83196886-974ff000-a177-11ea-8c6e-b011eddedd39.png)
![serverPortCheck](https://user-images.githubusercontent.com/39320966/83197039-d0886000-a177-11ea-8c43-855c42f07b07.png)

## Swagger Api Test
* 127.0.0.1:8080/docs URL 입력후 Swagger Test Page 사용.
![swagger](https://user-images.githubusercontent.com/39320966/83198201-b3ed2780-a179-11ea-8141-2acca4a89b18.png)

## H2 DataBase Console
* 127.0.0.1:8080/h2-console URL 입력후 H2 Console 사용.
* DriverClass : org.h2.Driver
* JDBC URL : jdbc:h2:mem:testdb
* User Name : sa
* Password :

![h2console](https://user-images.githubusercontent.com/39320966/83198824-fcf1ab80-a17a-11ea-8398-d53bff3caacd.png)

## ShopHere Project Portfolio
* Project 관리 및 Task 
- https://www.notion.so/hoseongpark/ShopHere-Backend-Project-bbb6945551fb4d58a1d60ff4321ffcc3

## Contat Us

김민수
* FrontEnd : https://github.com/Minsoo-web 

박호성
* BackEnd : https://github.com/hoseongpark
