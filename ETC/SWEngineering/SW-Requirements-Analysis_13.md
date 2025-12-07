# 13강 요약: 기능 요구사항(Functional Requirement)의 정의

## 1. 분석의 대원칙: 분리 (Separation)
* **MECE와 Orthogonality(직교성)의 적용:**
    * 소프트웨어 요구사항은 반드시 **기능(Functional)**과 **비기능(Non-Functional)**으로 나누어야 함.
    * 이 둘은 직교(Orthogonal)하므로 서로 섞지 말고 분리하여 작성해야 모호성이 사라짐.

## 2. 기능(Function)의 정의
* **일반적 오류:** "이동해야 한다", "편리해야 한다"와 같은 서술은 기능 요구사항이 아님 (단순한 관심사항/Concern).
* **수학적 정의:** 정의역(Domain)의 원소가 치역(Codomain)의 한 원소에 매핑되는 것.


[Image of mathematical function mapping domain to codomain]

* **소프트웨어 공학적 정의:**
    * **기능 = Input 대비 Output 값의 변화**
    * **핵심 규칙:** "Input과 Output이 명시되지 않은 문장은 기능 요구사항이 아니다." (쓰레기 취급해도 됨)

## 3. 기능 요구사항 도출 예시

### A. 자동차 (Car)
* **잘못된 정의:** "자동차는 이동해야 한다." (모호함, 코딩 불가)
* **올바른 정의 (Input → Output 변화):**
    * **Input:** 현재 위치의 탑승자 (User at Location A)
    * **Output:** 원하는 위치의 탑승자 (User at Location B)
    * **변화:** **위치(Position)의 변화**
* **설계/코딩 연결:**
    * 객체: `Car`, `User`
    * 상태(Attribute): `User.position`
    * 행위(Method): `Car.move(User)`
    * 이렇게 정의되어야 즉시 설계와 구현(Coding)이 가능함.

### B. 냉장고 (Refrigerator)
* **특이점:** 변화하지 않는 것이 기능인 경우.
* **정의:**
    * **Input:** 현재 상태의 음식물 (신선함)
    * **Output:** 시간이 지나도 상태가 변하지 않은 음식물 (여전히 신선함)
    * **변화:** 자연적인 부패(State Change)를 **거부/유지**하는 것이 기능.

### C. 인체 시스템
* **호흡계:** 공기(Input) → 혈액 내 산소 공급 + 이산화탄소 배출(Output)
* **소화계:** 음식물(Input) → 에너지 + 노폐물(Output)

## 4. 결론
* 기능 요구사항 분석이 잘 되었다면, 문장만 보고도 **객체(Object), 속성(Attribute), 메서드(Method)**가 도출되어야 함.
* 모호성을 없애는 유일한 길은 **수식(Input → Output)**으로 표현할 수 있게 만드는 것임.