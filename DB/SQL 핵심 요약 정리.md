
# SQL

- Structured Query Language

# MySQL Command

## MySQL 시작
~~~mysql
    cd /usr/local/mysql/bin 
    ./mysql -uroot -p
~~~
혹은
~~~mysql
    cd /opt/homebrew/Cellar/mariadb/10.11.3/bin
    ./mariadb
~~~

## 스키마(DB) 만들기 
~~~mysql
    CREATE DATABASE Tutorials;
~~~
 

## DB 삭제 <- 조심
~~~mysql
    DROP DATABASE Tutorials;
~~~


## DB 보기
~~~mysql
    SHOW DATABASES;
~~~


## DB 사용
~~~mysql
    USE Tutorials;
~~~


## 테이블 만들기
~~~mysql
    CREATE TABLE topic(
      id INT(11) NOT NULL AUTO_INCREMENT,
      title VARCHAR(100) NOT NULL,
      description TEXT NULL,
      created DATETIME NOT NULL,
      author VARCHAR(15) NULL,
      profile VARCHAR(200) NULL, PRIMARY KEY(id));
~~~

## 테이블 삭제
~~~mysql
    DROP TABLE topic;
~~~

## 테이블 구조 참조
~~~mysql
    DESC topic;
~~~


데이터 삽입
~~~mysql
    INSERT INTO topic(title,description,created,author,profile) VALUES('MySQL','MySQL is ...',NOW(),'JC','developer')
~~~

## 데이터 읽기(1)
~~~mysql
    SELECT * FROM topic; 
~~~

## 데이터 읽기(2)
~~~mysql
    SELECT id,title,created,author FROM topic; 
~~~

## 데이터 읽기(3)
~~~mysql
    SELECT "JC" FROM topic; 
~~~

## 데이터 읽기(4)
~~~mysql
    SELECT "JC",1+1; 
~~~

## 데이터 읽기(5)
~~~mysql
    SELECT id,title,created,author FROM topic WHERE author='JC'; 
~~~

## 데이터 읽기(6)
~~~mysql
    SELECT id,title,created,author FROM topic WHERE author='JC'
    ORDER BY id DESC;
~~~

## 데이터 읽기(7)
~~~mysql
    SELECT id,title,created,author FROM topic WHERE author='JC'
    ORDER BY id DESC LIMIT 2;
~~~

## 데이터 업데이트
~~~mysql
    UPDATE topic SET description='AAAAAAA', title='AAAAAA' WHERE id=2;
~~~


## 데이터 삭제 <- where을 넣어주지 않으면 재앙이 일어난다… 모든 행이 삭제됨.
~~~mysql
    DELETE FROM topic WHERE id = 5;
~~~

## COALESCE
- COALESCE <- NULL이 아닌 값을 반환하는 함수
- 아래 예제에서는 nickname이 NULL이 아니면 nickname을, NULL이면 name을 반환한다.
~~~mysql
    SELECT COALESCE(nickname, name) AS 'username' FROM users;
~~~

## JOIN 예제
- 독립적이고, 분리된 테이블을 하나의 테이블로 읽을 수 있다.
- 예제 테이블   
~~~mysql
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
~~~

### LEFT JOIN
→ 기본적으로 왼쪽 테이블을 모두 가져온다.
→ 오른쪽 테이블에 있는 조건들을 가져온다.
→ 만약 오른쪽 테이블에 있는 조건이 없을시 NULL로 표기.
→ “왼쪽 테이블에 오른쪽 테이블을 붙인다”로 생각하면 될듯.

**CUSTOMERS 테이블**

```java
CUSTOMER_ID | CUSTOMER_NAME
1           | John
2           | Sarah
3           | Mike
```

**ORDERS 테이블**

```java
ORDER_ID | CUSTOMER_ID | ORDER_DATE
101      | 2           | 2023-07-01
102      | 1           | 2023-07-05
```

**LEFT JOIN을 사용한 쿼리**

```java
SELECT CUSTOMERS.CUSTOMER_ID, CUSTOMERS.CUSTOMER_NAME, ORDERS.ORDER_ID, ORDERS.ORDER_DATE
FROM CUSTOMERS
LEFT JOIN ORDERS
ON CUSTOMERS.CUSTOMER_ID = ORDERS.CUSTOMER_ID;
```

**결과**

```java
CUSTOMER_ID | CUSTOMER_NAME | ORDER_ID | ORDER_DATE
1           | John          | 102      | 2023-07-05
2           | Sarah         | 101      | 2023-07-01
3           | Mike          | NULL     | NULL
```


## id, title, description, created, name, profile의 정보로 필터하여 읽음
~~~mysql
    SELECT topic.id,title,description,created,name,profile FROM topic LEFT JOIN author ON topic.author_id = author.id;
~~~


## 위 커맨드에서 id를 t_id로 바꾸어 읽는 기능
~~~mysql
    SELECT topic.id AS t_id ,title,description,created,name,profile FROM topic LEFT JOIN author ON topic.author_id = author.id;
~~~






