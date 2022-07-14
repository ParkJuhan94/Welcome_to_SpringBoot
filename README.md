# 🎈Welcome_to_SpringBoot🎈
우아한형제들 기술이사 김영한의 스프링입문 강의를 들으며 제작한 프로젝트를 저장합니다.

## 💎실제 동작하는 간단한 웹 애플리케이션을 다음 순서로 빠르게 만들어봅니다.

1. 스프링 프로젝트 생성
2. 스프링 부트로 웹 서버 실행
3. 회원 도메인 개발
4. 웹 MVC 개발
5. DB 연동 - JDBC, JPA, 스프링 데이터 JPA
6. 테스트 케이스 작성

### 프로젝트 폴더 트리
![20220714134443](https://user-images.githubusercontent.com/81701212/178901403-afae015f-a36e-4874-80dd-13f493b00a19.png)
### 스프링 컨테이너에서 컨트롤러가 템플릿의 html 파일을 웹에 띄우는 동작
![20220714134708](https://user-images.githubusercontent.com/81701212/178901427-e4887b0d-f951-424d-ab8b-b3bfa9f3cf74.png)
### 일반적인 웹 애플리케이션 계층 구조(이 프로젝트에도 적용) + 각 계층의 역찰
![20220714134558](https://user-images.githubusercontent.com/81701212/178901413-0feeb6a8-607b-4f78-b20d-57b0b9abb87e.png)
### 스프링 컨테이너에 스프링 빈을 등록한 구성도
![20220714134621](https://user-images.githubusercontent.com/81701212/178901417-a714babf-9630-4dbd-b909-a8d94ab9e350.png)


### H2 데이터베이스를 로컬 DB로 사용 합니다. 서버 실행시키는 명령
![20220714132919](https://user-images.githubusercontent.com/81701212/178899128-5d59c70f-840e-464a-8517-38dd9e173da5.png)
### H2 데이터베이스의 웹상 콘솔
![20220714132931](https://user-images.githubusercontent.com/81701212/178899133-05980a1a-eb89-412e-8329-b635da84d0c9.png)
### 초기에 Member테이블의 데이터는 비어있음을 확인
![20220714132945](https://user-images.githubusercontent.com/81701212/178899137-c0635943-95b4-492d-8c0a-ea6ce343777c.png)
### 스프링 프로젝트를 Run해서 웹서버를 띄우고 port 넘버 8080으로 접속
![20220714133055](https://user-images.githubusercontent.com/81701212/178899140-c849b67c-997b-45e7-a1a7-8681d5b8b7a5.png)
### 회원가입으로 들어가서 데이터 등록1
![20220714133113](https://user-images.githubusercontent.com/81701212/178899147-d14333b4-ceb7-4530-8aa9-b77771b39948.png)
### 회원가입으로 들어가서 데이터 등록2
![20220714133126](https://user-images.githubusercontent.com/81701212/178899154-9c3057bf-f42c-4dc2-92ea-5ee32b1884e3.png)
### 회원 목록에서 현재 모든 데이터 조회
![20220714133133](https://user-images.githubusercontent.com/81701212/178899156-fd835aa5-a5b8-46f1-b7a8-9c2b73089d52.png)
### H2 콘솔에서도 데이터 조회 : 웹 조회와 일치하는 것을 확인
![20220714133143](https://user-images.githubusercontent.com/81701212/178899158-5bb444f5-437a-4014-be46-fabf020734fa.png)

