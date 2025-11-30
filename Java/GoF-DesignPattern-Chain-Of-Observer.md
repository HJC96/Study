### 1. 개념: 상태 변화의 감지와 반응
* **정의:** 한 객체(Subject)의 상태가 바뀌면 그 객체에 의존하는 다른 객체들(Observer)에게 연락이 가고 자동으로 내용이 갱신되는 패턴임.
* **핵심:** 일대다(One-to-Many) 의존성을 가지며, **발행(Publish)-구독(Subscribe)** 모델을 구현할 때 사용됨.

### 2. 구현 예시 (채팅 서버)
* **문제:** 유저가 주기적으로 채팅 서버에 "새 메시지 왔어?" 하고 물어보는 **폴링(Polling)** 방식은 비효율적임.
* **해결:**
    1.  **Subject (ChatServer):** `register()`, `unregister()`, `notifyObservers()` 메서드를 가짐. 메시지가 오면 등록된 옵저버들에게 알려줌.
    2.  **Observer (Subscriber 인터페이스):** `handleMessage()` 메서드를 정의함.
    3.  **Concrete Observer (User):** `handleMessage()`를 구현하여 메시지를 받으면 출력함.
* **결과:** 유저는 가만히 있어도 서버가 메시지를 보내주면 그때 반응하면 됨. (이벤트 기반).

### 3. 장단점
* **장점:**
    * **느슨한 결합 (Loose Coupling):** Subject와 Observer는 서로 구체적인 클래스를 몰라도 됨.
    * **실시간성:** 폴링 없이 상태 변경 즉시 알림을 받을 수 있음.
    * **확장성 (OCP):** 새로운 Observer를 추가해도 Subject 코드는 변경되지 않음.
* **단점:**
    * **메모리 누수 (Memory Leak):** Observer를 등록하고 해지하지 않으면, Subject가 참조를 계속 잡고 있어 GC가 되지 않음 (WeakReference 등으로 보완 가능하지만 해지가 원칙).
    * **복잡도:** 알림 순서를 보장하기 어렵고, 코드가 다소 복잡해질 수 있음.

### 4. 실무 활용 사례 (Java & Spring)
* **Java - `java.util.Observer` / `Observable`:**
    * 자바 초기 버전에 있었으나 여러 문제(상속 강제, 유연성 부족 등)로 **Deprecated(사용 권장 안 함)** 됨.
    * **대안:** `java.beans.PropertyChangeListener` 또는 Java 9의 **Flow API** (리액티브 스트림) 사용 권장.
* **Spring - `ApplicationEventPublisher`:**
    * 스프링의 핵심 기능인 `ApplicationContext`가 이벤트 퍼블리셔 역할을 함.
    * **사용법:**
        * 이벤트 클래스 정의 (`MyEvent`).
        * `ApplicationEventPublisher.publishEvent(new MyEvent())`로 발행.
        * `@EventListener` 애노테이션을 붙인 메서드에서 이벤트를 받아 처리.
    * **특징:** 스프링 코드를 직접 상속받지 않고도 POJO 스타일로 깔끔하게 구현 가능 (비침투적).
