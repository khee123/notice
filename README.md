# 공지사항 REST API

## 1. 문제 해결 전략

- ### 공지사항 등록/수정
  
  - jpa의 save() 메소드를 사용해서 등록, 수정 한번에 구현
  - Entity 클래스의 Column 어노테이션 속성을 사용하여 insert, update 데이터 제어
  - Transactional 어노테이션을 사용하여 트랜잭션 처리
- ### 공지사항 삭제
  
    - soft delete로 공지사항 삭제 후 복구 가능하도록 기능 구현
- ### 공지사항 상세

    - user 테이블과 join하여 작성자 정보를 가져오기 위해 querydsl 사용.
- ### 첨부파일 저장

    - 첨부파일 기능 구현 시 현재 기능은 공지사항 뿐이지만, 여러 곳에서 사용이 가능하도록 공통 기능으로 구현.
    - front 작업 시 첨부파일 선택 시 file 테이블에 임시 저장 처리를 한 뒤, 실제 공지사항 등록 버튼 클릭 시 file 테이블에 공지사항 정보를 update 하도록 구현
- ### 단위 테스트

    - @DataJpaTest로 repository Slice Test 진행
- ### 통합 테스트

    - @SpringBootTest로 Controller 통합 테스트 진행
- ### API 문서
  
    - swagger 추가하여 API 문서로 활용

---

## 2. 문제 해결

- ### 대용량 트래픽(transaction)

    - 대용량 트래픽 해결 관련하여 깊이 있게 학습하여 개발 한 경험이 부족하여 정보를 찾아보며 작업 진행
    - JPA를 활용하여 작업할 경우, 가장 중요한 부분이 transaction 처리라고 생각 되어 save 메소드 사용 시 transactional 처리 진행
    - save() 메소드 내부에 transaction이 존재 하기 때문에 save() 메소드만 호출할 경우 호출 할 때마다 transaction이 실행 되어 속도 저하 발생
    - 이를 해결하기 위해 save()메소드를 사용하는 상위 Service 구현부에 Transactional 어노테이션을 추가함.
- ### 대용량 트래픽(DB)

    - 처음 과제 시작시에는 데이터베이스를 mariadb로 세팅 하였으나, 대용량 트래픽 관련한 정보를 서치하던 중 postgresql이 트랜잭션 처리 능력이 뛰어난 데이터 베이스임을 알게되어 변경.
----

## 3. 실행 방법
### 1. jdk-11, postgresql 사용
### 2. 첨부파일 업로드 경로는 application.yml > spring.upload.path
### 3. application 실행 시 로컬 데이터베이스에 테이블 및 사용자 기본 데이터 생성 됨.
### 4. swagger 주소: http://localhost:8080/swagger-ui