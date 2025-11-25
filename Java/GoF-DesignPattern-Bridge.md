### 1. 개념: 추상과 구현의 분리
* **정의:** 추상적인 것(Abstraction)과 구체적인 것(Implementation)을 분리하여 서로 독립적으로 확장할 수 있게 하는 패턴임.
* **핵심:** **상속(Inheritance)** 대신 **합성(Composition)** 을 사용하여 두 계층을 연결(Bridge)함.
* **비교:**
    * **어댑터 패턴:** 서로 다른 인터페이스를 연결해주는 것 (호환성).
    * **브리지 패턴:** 하나의 덩어리를 추상과 구현 두 개로 나누어 연결하는 것 (독립적 확장).

### 2. 예시: 게임 캐릭터(Champion)와 스킨(Skin)
* **잘못된 설계 (상속 사용):**
    * `KDA아리`, `풀파티아리`, `KDA아칼리`, `풀파티아칼리`...
    * 챔피언이나 스킨이 추가될 때마다 클래스가 기하급수적으로 늘어남 (Class Explosion).
    * 코드 중복 발생.
* **브리지 패턴 적용:**
    * **Abstraction (챔피언):** `DefaultChampion` (동작 정의).
    * **Implementation (스킨):** `Skin` 인터페이스 (외형 정의).
    * **연결:** 챔피언이 스킨 인터페이스를 멤버 변수로 가짐 (Composition).
    * **사용:** `new Ahri(new KDASkin())` 처럼 조합해서 생성함.
* **결과:**
    * 새 챔피언 추가 시 `Champion`만 확장하면 됨.
    * 새 스킨 추가 시 `Skin`만 구현하면 됨. (서로 영향 없음).

### 3. 장단점
* **장점:**
    * **OCP(개방-폐쇄 원칙):** 추상 코드와 구체적인 코드를 독립적으로 확장 가능함.
    * **SRP(단일 책임 원칙):** 추상 로직과 세부 구현 로직을 분리하여 관리 가능함.
    * 코드 재사용성 증가 및 중복 감소.
* **단점:**
    * 계층 구조가 2개로 늘어나 코드가 복잡해 보일 수 있음.

### 4. 실무 적용 사례 (Java & Spring)
* **Java - JDBC:**
    * **Abstraction:** `java.sql.Connection`, `DriverManager` (DB가 바뀌어도 클라이언트 코드는 그대로임).
    * **Implementation:** `java.sql.Driver` (각 DB 벤더가 구현하는 실제 드라이버: H2, MySQL 등).
* **Java - SLF4J (Logging):**
    * **Abstraction:** SLF4J API (Logger, LoggerFactory).
    * **Implementation:** Log4j, Logback 등 실제 로깅 프레임워크.
* **Spring - PSA (Portable Service Abstraction):**
    * **`MailSender`:** 인터페이스 뒤에 `JavaMailSender` 등 구현체가 숨어 있음.
    * **`PlatformTransactionManager`:** 트랜잭션 처리라는 추상화 뒤에 `JdbcTransactionManager`, `HibernateTransactionManager` 등 기술별 구현체가 분리되어 있음.
