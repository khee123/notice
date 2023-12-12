# 공지사항 API

## 1. 문제 해결 전략
### 1. jpa와 queryDsl을 사용하여 테이블 생성 및 데이터 CRUD 로직 구현.
### 2. 첨부파일 기능 구현 시 현재 기능은 공지사항 뿐이지만, 여러 곳에서 사용이 가능하도록 기능 구현.
````
- 첨부파일 선택 시 file 테이블에만 저장
- 이후 공지사항 저장 시 file 테이블의 첨부파일 데이터에 저장한 공지사항의 메뉴 코드, 공지사항 인덱스 정보 추가
````
### 3. swagger  추가하여 API 정의 및 기능 테스트 하며 구현.
### 4. 단위 테스트/통합테스트

----

## 2. 실행 방법
### 1. jdk-11, mariadb 사용
### 2. QEntity Class 생성 필요
````
pom.xml 우클릭 > Maven > Generate Sources and Update Folders
````
### 3. 첨부파일 업로드 경로는 application.yml > spring.upload.path
### 4. application 실행 시 로컬 데이터베이스에 테이블 및 사용자 데이터 생성 됨.
### 5. swagger 주소: http://localhost:8080/swagger-ui