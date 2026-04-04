### 1. 개념: 복잡한 관계의 정리
* **정의:** 여러 객체(Component/Colleague)들 간의 복잡한 상호작용을 캡슐화하여, **중재자(Mediator) 객체** 하나가 조정하게 만드는 패턴임.
* **비유:**
    * **아파트 관리소:** 입주민 간 층간소음 문제를 직접 해결하지 않고 관리소를 통해 중재함.
    * **관제탑:** 비행기끼리 직접 통신하지 않고 관제탑을 통해 이착륙 순서를 조정함.
    * **연예인 매니저:** 모든 스케줄과 예약을 매니저가 전담하여 처리함.
* **구조:**
    * **Colleague (동료/컴포넌트):** `Guest`, `Restaurant`, `CleaningService` 등. 서로를 직접 참조하지 않고 `Mediator`만 앎.
    * **Mediator (중재자):** 모든 Colleague를 알고 있으며, 요청을 받아 적절한 Colleague에게 작업을 시킴.

### 2. 구현 예시 (호텔 서비스)
* **문제:** `Guest`가 `CleaningService`를 부르려면 직접 알아야 하고, `Restaurant` 예약도 직접 해야 함. 객체 간 의존성(결합도)이 너무 높아짐 (M:N 관계).
* **해결:**
    1.  `FrontDesk` (Mediator) 클래스를 만듦.
    2.  `Guest`는 `frontDesk.getTowel(id)` 처럼 프론트에만 요청함.
    3.  `FrontDesk`는 `Guest` 정보를 이용해 `CleaningService`에 수건 배달을 지시함.
* **결과:** `Guest`, `Restaurant`, `CleaningService`는 서로를 몰라도 됨. 오직 `FrontDesk`만 알면 됨.

### 3. 장단점
* **장점:**
    * **결합도 감소:** M:N 관계를 1:N 관계로 단순화하여 컴포넌트 간 의존성을 줄임.
    * **재사용성 증가:** 개별 컴포넌트(`Guest`, `Restaurant`)는 다른 곳에서도 쉽게 재사용 가능함.
    * **단일 책임 원칙 (SRP) 준수:** 컴포넌트는 자기 일만 하고, 조정 업무는 중재자에게 맡김.
* **단점:**
    * **중재자의 거대화:** 모든 로직이 중재자(`FrontDesk`)로 몰리기 때문에, 중재자 클래스가 매우 복잡하고 거대해질 수 있음 (God Object 위험).

### 4. 실무 활용 사례 (Java & Spring)
* **Java - `ExecutorService`:**
    * `Executor`가 중재자 역할을 하여, `Runnable` Task들을 적절한 스레드에 할당하고 실행을 관리함.
* **Spring - `DispatcherServlet` (MVC의 핵심):**
    * **가장 대표적인 예시.**
    * `DispatcherServlet`이 중재자가 되어 `HandlerMapping`, `HandlerAdapter`, `ViewResolver` 등 다양한 컴포넌트들을 조율함.
    * 덕분에 각 컴포넌트들은 서로를 알 필요 없이 독립적으로 동작할 수 있음.
