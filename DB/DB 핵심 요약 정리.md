

# 데이터베이스 1

## 데이터를 관리하는 방법 두 가지

1. 파일
   1. 배우기 쉽다
   2. 운영체제마다 제공한다
   3. 성능, 보안, 편의성에 한계를 가진다
2. 전문화된 소프트웨어
   1. 데이터를 안전하고 편리하고 빠르게 보관가능
      1. My SQL
      2. Mongo DB
      3. ETC

## 데이터베이스의 본질

- CRUD
  - Create
  - Read
  - Update
  - Delete
- 그 외 복잡한 기능들은 CRUD를 서포트하는 것이라고 생각해도 된다.

## File ve Database

- 데이터베이스의 등장배경
  - 데이터를 빠르고, 편리하고, 안전하게 다루고 싶은 필요에 의해 생겨나게 됨.

- 기술의 발전
  - File -> Spreadsheet -> Database


# 데이터베이스 2

- RDBMS란?
  - 데이터를 표의 형태로 정렬
  - 정렬, 검색과 같은 작업을 빠르고 간결하게 할 수 있다.
    - MySQL
    - Oracle
    - SQL Server
    - PostgreSQL
    - ETC

- 스프레드시트와 데이터베이스의 공통점 및 차이점
  - 공통점
    - 데이터를 표의 형태로 표현해준다.
  - 차이점
    - 스프레드시트
      - UI 이용
    - 데이터베이스
      - 코드 이용
- 용어 정리





# SQL

- Structured Query Language

# MySQL Command

MySQL 시작

    cd /usr/local/mysql/bin 
    ./mysql -uroot -p



스키마(DB) 만들기 

    CREATE DATABASE Tutorials;

 

DB 삭제 <- 조심

    DROP DATABASE Tutorials;



DB 보기

    SHOW DATABASES;



DB 사용

    USE Tutorials;



테이블 만들기



    CREATE TABLE topic(
      id INT(11) NOT NULL AUTO_INCREMENT,
      title VARCHAR(100) NOT NULL,
      description TEXT NULL,
      created DATETIME NOT NULL,
      author VARCHAR(15) NULL,
      profile VARCHAR(200) NULL, PRIMARY KEY(id));



테이블 삭제

    DROP TABLE topic;



테이블 구조 참조

    DESC topic;



데이터 삽입

    INSERT INTO topic(title,description,created,author,profile) VALUES('MySQL','MySQL is ...',NOW(),'JC','developer')



데이터 읽기(1)

    SELECT * FROM topic; 

스크린샷 2023-03-03 오후 11.20.44.png



데이터 읽기(2)

    SELECT id,title,created,author FROM topic; 





데이터 읽기(3)

    SELECT "JC" FROM topic; 





데이터 읽기(4)

    SELECT "JC",1+1; 





데이터 읽기(5)

    SELECT id,title,created,author FROM topic WHERE author='JC'; 





데이터 읽기(6)

    SELECT id,title,created,author FROM topic WHERE author='JC'
    ORDER BY id DESC;





데이터 읽기(7)

    SELECT id,title,created,author FROM topic WHERE author='JC'
    ORDER BY id DESC LIMIT 2;





데이터 업데이트

    UPDATE topic SET description='AAAAAAA', title='AAAAAA' WHERE id=2;





데이터 삭제 <- where을 넣어주지 않으면 재앙이 일어난다… 모든 행이 삭제됨.

    DELETE FROM topic WHERE id = 5;



JOIN 예제

- 독립적이고, 분리된 테이블을 하나의 테이블로 읽을 수 있다.
- 예제 테이블

    structure for table `author`
    --
     
     
    CREATE TABLE `author` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `name` varchar(20) NOT NULL,
      `profile` varchar(200) DEFAULT NULL,
      PRIMARY KEY (`id`)
    ) 
     
    --
    -- Dumping data for table `author`
    --
     
    INSERT INTO `author` VALUES (1,'egoing','developer');
    INSERT INTO `author` VALUES (2,'duru','database administrator');
    INSERT INTO `author` VALUES (3,'taeho','data scientist, developer');
     
    --
    -- Table structure for table `topic`
    --
     
    CREATE TABLE `topic` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `title` varchar(30) NOT NULL,
      `description` text,
      `created` datetime NOT NULL,
      `author_id` int(11) DEFAULT NULL,
      PRIMARY KEY (`id`)
    )
     
    --
    -- Dumping data for table `topic`
    --
     
    INSERT INTO `topic` VALUES (1,'MySQL','MySQL is...','2018-01-01 12:10:11',1);
    INSERT INTO `topic` VALUES (2,'Oracle','Oracle is ...','2018-01-03 13:01:10',1);
    INSERT INTO `topic` VALUES (3,'SQL Server','SQL Server is ...','2018-01-20 11:01:10',2);
    INSERT INTO `topic` VALUES (4,'PostgreSQL','PostgreSQL is ...','2018-01-23 01:03:03',3);
    INSERT INTO `topic` VALUES (5,'MongoDB','MongoDB is ...','2018-01-30 12:31:03',1);



id를 기준으로 하여 부착하여 읽음

    SELECT * FROM topic LEFT JOIN author ON topic.author_id = author.id;



id, title, description, created, name, profile의 정보로 필터하여 읽음

    SELECT topic.id,title,description,created,name,profile FROM topic LEFT JOIN author ON topic.author_id = author.id;



위 커맨드에서 id를 t_id로 바꾸어 읽는 기능

    SELECT topic.id AS t_id ,title,description,created,name,profile FROM topic LEFT JOIN author ON topic.author_id = author.id;







