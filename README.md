# 지역경제를 살리기 위한 숨은 명소 사진 퀴즈 앱 (Catch B) - 팀 꿀벌


![image](https://user-images.githubusercontent.com/60650967/175769717-d965fabc-e7e6-48d8-8829-3de342fa9a19.png)  


## Description

> 모바일컴퓨팅 팀프로젝트  

> 2021.09.08 ~ 2021.12.15  



## Project Background and Objectives

![image](https://user-images.githubusercontent.com/60650967/175769851-cffa4947-ae9b-4023-9fa3-d32fb5c0dbae.png)  

* 현재 우리 사회에서 대두되는 다양한 사회 문제 중 하나를 고르라고 한다면 아마 대부분 코로나 바이러스를 문제점으로 선택할 것이라고 생각한다. 이로 인해 전 세계적으로 수많은 분야가 타격을 입게 되었는데 그 중 여행관련 업계와 관광지 지역 상권에 대한 타격이 매우 크다는 것을 확인할 수 있다.  

* 여행의 동기를 제공하기 위한 여러 아이디어를 구상하였고 이 중 최종적으로 선택한 방안은 여행지를 직접 방문하여 사진을 찍고 다른 여행자들과 해당 지역의 정보를 공유하면 이에 대한 보상을 지급하는 것이었다. 여행을 취미로 하는 사람들 중 대부분은 일상에서 벗어나 여행지에서 단순히 쉬는 것에 그치지 않고 아름다운 풍경과 순간들을 사진에 담고 그 감정을 타인과 공유하며 큰 만족감을 느끼는 경우가 많았다. 그런 여행자들의 니즈를 Catch-B 프로젝트를 통해 채울 수 있게 하였으며 히든 플레이스를 맞추는 과정과 맞췄을 때의 보상을 통해 여행이 주는 희열을 더 크게 느낄 수 있게 하여 여행자들의 유동을 늘려 경제적 타격을 입은 관광업계에 도움이 되는 것을 최종적인 목표로 설정하였다.  

## Summary
* 앱 로딩 화면 및 회원가입, 로그인  
앱 로딩 화면은 애니메이션으로 출력이 되고 사용자는 회원가입 및 로그인이 가능하다.  

* 메인 화면  
사용자의 주 사용 메뉴인 마이페이지, 스토어, 설정 버튼을 오른쪽 상단에 배치하여 사용하기 쉽게 구현한다. 사용자에게 한 지역뿐만 아니라 많은 지역에 관심을 가지도록 다양한 지역의 사진을 제공한다.  

* 해당 사진 클릭 시 화면  
사진에 대한 간략한 정보를 해시태그 형태로 제공한다. 사용자들은 해당 사진을 찜 버튼을 통해 인 앱에 저장할 수 있고, 공유하기를 통해 다른 SNS와 연동하여 해당 사진을 공유한다. 댓글 서비스는 해당 지역의 사진을 제출하기 전에는 공개되지 않게 구현한다. 버튼을 통해 카메라 촬영을 할 수 있고 갤러리에 있는 사진을 가져올 수 있게 한다.  

* 스토어 화면  
사용자는 자신이 보유한 Credit으로 스토어에서 다양한 기프티콘을 구매할 수 있다.  

* 마이페이지 화면  
사용자는 자신만의 기능을 마이페이지에서 사용할 수 있게 구현한다. 자신의 정보, Credit 보유 수량, 사용 내용 등을 받는다.  


## System Spec

|  |  |
|:------:| :- |
| Platform | Android |
| OS | Windows 10 |
| WAS | Spring Boot |
| DB | Mysql DB  |
| 언어 | Java |
| Prototype | Figma |
| IDE | Android Studio, Intellij IDEA |
| 형상관리 | GIT |
 

## System Configuration Diagram
![image](https://user-images.githubusercontent.com/60650967/175770637-19bd0a34-d8be-407c-bdc9-34daa9ed798e.png)  
 
* Android 앱의 개발 도구인 ‘Android Studio’를 사용하여, View를 제작하였다. 
* xml 파일을 통해 디자인을 제작하였으며, 서버에서 제공하는 REST API 데이터를 가공하는 클라이언트의 역할을 Front-End에서 진행하였다.  
* Back-End에서는 Web Server는 Tomcat을 사용하며, WAS 서버는 Spring Boot 프레임 워크를 사용하여 MVC 패턴으로 구현하였다.  


## About Project
<img src="https://img.shields.io/badge/Language-Java-green?style=flat"/>  

* 구현 영상  

[![IMAGE ALT TEXT HERE](http://img.youtube.com/vi/J4B2XNKtSn4/0.jpg)](http://www.youtube.com/watch?v=J4B2XNKtSn4)