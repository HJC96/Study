
### 🤔 MongoDB, 왜 나왔을까?

제가 처음 백엔드 시작할 땐 RDBMS(MySQL 같은 거)만 썼는데요. 테이블 구조 딱딱 짜고 정해진 데이터만 넣어야 해서 좀 답답할 때가 있었어요. 특히 요즘 서비스들은 비정형 데이터가 엄청 많잖아요?

그래서 **스키마 없이 유연하게** 데이터를 넣을 수 있는 데이터베이스가 필요했고, 그렇게 나온 게 바로 MongoDB 같은 **NoSQL**입니다. 객체지향 프로그래밍에서 쓰던 객체 모양 그대로 DB에 쏙 넣을 수 있어서 개발하기 진짜 편하다고 해요.

---

### 핵심 개념: Document와 BSON

MongoDB를 이해하려면 딱 두 가지만 기억하면 됩니다.

* Document(문서): 데이터를 JSON 비슷한 형태로 저장해요. RDBMS의 `row` 한 줄이 여기선 `Document` 하나라고 생각하면 쉽습니다. 이 문서들을 Collection(컬렉션)이라는 곳에 모아두는데, 이건 RDBMS의 `Table`이랑 비슷해요. 가장 큰 차이점은 **컬렉션 안의 문서들이 꼭 같은 구조일 필요가 없다**는 거죠! 이게 바로 '스키마가 없다'는 의미입니다.

* **BSON(Binary JSON)**: 내부적으로는 JSON을 이진(Binary) 데이터로 변환해서 저장해요. 그래서 그냥 JSON보다 처리 속도도 빠르고, 더 다양한 데이터 타입을 쓸 수 있습니다.

---

### 데이터 갖고 놀기: CRUD 기본

데이터베이스의 기본은 역시 CRUD(생성, 읽기, 수정, 삭제)죠.

* **Create**: `insertOne()`이나 `insertMany()` 명령어로 문서를 추가해요.
* **Read**: `find()`나 `findOne()`으로 데이터를 찾아요. 조건절 쓰는 게 SQL이랑은 좀 다른데, 쓰다 보면 금방 익숙해져요.
* **Update**: `updateOne()`이나 `updateMany()`로 데이터를 수정합니다. `$set` 같은 연산자를 써서 특정 필드만 딱 바꿀 수 있어서 편해요.
* **Delete**: `deleteOne()`이나 `deleteMany()`로 문서를 지웁니다.

---

### 이건 꼭 알아야 해: Aggregate (집계) 🚀

이게 진짜 MongoDB의 꽃이라고 생각해요. 그냥 단순 조회 말고요, 데이터를 그룹화하고 통계를 내는 등 복잡한 분석이 필요할 때 쓰는 기능입니다.

`aggregate`는 **파이프라인**이라는 개념을 써요. 데이터를 여러 단계(stage)에 통과시키면서 원하는 결과로 만들어가는 거죠.

* **$match**: SQL의 `WHERE`처럼 조건에 맞는 데이터만 거릅니다.
* **$group**: `GROUP BY`처럼 특정 필드를 기준으로 데이터를 묶고, 개수(`$sum`)나 평균(`$avg`)을 내요.
* **$sort**: 정렬하고요.
* **$lookup**: RDBMS의 `JOIN`처럼 다른 컬렉션 데이터를 붙일 수도 있어요.

이 파이프라인만 잘 짜면 복잡한 데이터도 DB 단에서 깔끔하게 처리할 수 있어서, 백엔드 코드가 훨씬 단순해지는 경험을 할 수 있습니다!
