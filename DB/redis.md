1. Redis Key 네이밍: 콜론(:)으로 계층 구분하기
회사마다 컨벤션이 다르긴 한데, 거의 공통적으로 쓰는 방식이 있더라구요.
콜론(:)을 활용해서 계층적으로 의미를 구분
users:100:profile     // users 중에서 PK 100인 user의 profile
products:123:details  // products 중에서 PK 123인 product의 details
→ 데이터의 의미와 용도를 한눈에 파악 가능
→ 패턴 매칭으로 특정 유형의 Key를 쉽게 검색 가능
→ 서로 다른 Key끼리 이름 충돌할 일이 거의 없음
→ 어렴풋이 알고 있었지만, 실제로 이렇게 체계적으로 관리하니까 유지보수가 훨씬 편하더라구요.

2. 캐시(Cache)와 캐싱(Caching) 개념
캐시(Cache) = 원본 저장소보다 빠르게 가져올 수 있는 임시 데이터 저장소
캐싱(Caching) = 캐시에 접근해서 데이터를 빠르게 가져오는 방식
현업에서는 이런 식으로 얘기합니다:
"이 API 응답 속도가 너무 느린데? 이거 캐싱해두고 쓰는 게 어때?"
→ API 응답 결과를 Redis같은 곳에 저장해두고 빠르게 조회하자는 의미

3. Cache Aside 전략: 조회할 때 이렇게 하세요
Cache Aside 전략(= Look Aside = Lazy Loading)은 데이터 조회할 때 가장 많이 쓰는 전략입니다.
작동 방식:

데이터 조회 요청이 들어옴
Redis에 데이터가 있는지 먼저 확인
Cache Hit (있으면) → Redis에서 바로 가져와서 응답
Cache Miss (없으면) → DB에서 조회 → 응답 → Redis에도 저장

java// 실제 코드로 보면 이런 느낌
String cachedData = redis.get("users:100:profile");
if (cachedData != null) {
    return cachedData;  // Cache Hit
}

// Cache Miss - DB에서 조회
String data = database.query("SELECT * FROM users WHERE id = 100");
redis.set("users:100:profile", data, 3600);  // TTL 1시간
return data;
→ 첫 번째 조회는 느리지만, 두 번째부터는 빠름
→ 자주 조회되는 데이터일수록 효과가 극대화됨

4. Write Around 전략: 쓰기 작업은 이렇게
Write Around 전략은 데이터 저장/수정/삭제할 때 쓰는 전략인데, Cache Aside랑 세트로 자주 씁니다.
작동 방식:
쓰기 작업(저장, 수정, 삭제)은 DB에만 반영하고, Redis에는 반영하지 않음
→ 왜 이렇게 하나 싶었는데, 쓰기보다 읽기가 훨씬 많은 서비스에서는 이게 효율적이더라구요
→ 어차피 다음에 조회할 때 Cache Miss 나면 그때 Redis에 저장되니까

5. 데이터 조회 성능 개선: SQL 튜닝부터 하세요

근데 왜 SQL 튜닝부터 해야 하나?
→ SQL 튜닝은 추가 비용이 없음 (Redis 서버 구축, 관리 비용 X)
→ 근본적인 문제 해결 (SQL이 비효율적이면 Redis 써도 한계가 있음)
→ 가장 가성비가 좋은 방법
실제로 인덱스 하나 제대로 걸어주는 것만으로도 10초 걸리던 쿼리가 0.1초로 줄어드는 겨웅도 있다고 하네요. Redis 도입하기 전에 SQL부터 점검하세요!

