### 1. `INNER JOIN` vs `LEFT JOIN` 차이점
* **`INNER JOIN` (교집합):** 두 테이블 모두에 데이터가 존재하는 경우만 반환.
    * 예: 부서가 할당된 사원만 조회.
* **`LEFT JOIN` (차집합 + 교집합):** 왼쪽 테이블(Driving Table)의 모든 데이터를 반환하며, 오른쪽 테이블(Driven Table)에 데이터가 없으면 `NULL`로 표시.
    * 예: 모든 사원을 조회하되, 부서가 없으면 부서 정보를 `NULL`로 표시.

### 2. `LEFT JOIN` 사용 시 핵심 주의사항 (조건의 위치)
**조건을 `ON` 절에 두느냐, `WHERE` 절에 두느냐에 따라 결과가 완전히 달라집니다.**

* **Case A: `ON` 절에 조건 명시 (올바른 LEFT JOIN)**
    * `FROM user LEFT JOIN user_coupon ON user.id = user_coupon.user_id AND coupon_id = 3`
    * **동작:** 전체 유저를 조회하되, 3번 쿠폰을 가진 유저만 쿠폰 정보를 보여주고 나머지는 쿠폰 정보를 `NULL`로 표시.
    * **결과:** **전체 유저 수만큼 반환.**

* **Case B: `WHERE` 절에 조건 명시 (INNER JOIN처럼 동작)**
    * `FROM user LEFT JOIN user_coupon ON ... WHERE coupon_id = 3`
    * **동작:** LEFT JOIN 결과를 만든 후, `WHERE` 절에서 3번 쿠폰이 아닌 행(NULL 포함)을 모두 필터링해버림.
    * **결과:** **3번 쿠폰을 가진 유저만 반환 (INNER JOIN 결과와 동일).**
    * *Tip:* MySQL 옵티마이저는 이 경우 내부적으로 `INNER JOIN`으로 쿼리를 자동 변환하여 처리함.

### 3. `COUNT(*)` 쿼리에서의 `LEFT JOIN` 최적화
* **상황:** 목록 조회 시 페이징을 위해 전체 개수(`COUNT`)를 구하는 쿼리를 작성할 때, 관습적으로 조회 쿼리의 `LEFT JOIN`을 그대로 복사해서 쓰는 경우가 많음.
* **비효율:** * `LEFT JOIN`은 왼쪽 테이블(1) 대 오른쪽 테이블(N) 관계가 1:1이거나 N쪽 데이터가 없을 때, **전체 레코드 수는 왼쪽 테이블의 레코드 수와 동일함.**
    * 즉, 조인을 하나 안 하나 결과 건수가 같다면 **굳이 조인을 수행할 필요가 없음.**
* **튜닝:**
    * 결과 건수에 영향을 주지 않는 `LEFT JOIN`은 **`COUNT` 쿼리에서 제거**해야 성능이 향상됨.
    * 단, `WHERE user_coupon.id IS NULL` 처럼 **존재하지 않는 것을 찾는 쿼리(차집합)**는 조인이 필수적이므로 제거하면 안 됨.

**요약 핵심:**
`LEFT JOIN`을 사용할 때 오른쪽 테이블에 대한 필터링 조건은 반드시 **`ON` 절**에 넣어야 의도한 대로 동작합니다 (`WHERE` 절에 넣으면 `INNER JOIN`이 됨). 또한 단순 개수 조회(`COUNT`) 시 결과 건수에 영향을 주지 않는 불필요한 `LEFT JOIN`은 과감히 **제거**하세요.
