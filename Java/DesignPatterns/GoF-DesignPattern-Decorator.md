### 1. 문제점: 상속을 통한 확장의 한계
* **상황:** `CommentService`에 'Trimming(공백 제거)' 기능과 'SpamFiltering(스팸 제거)' 기능을 추가하고 싶음.
* **상속 사용 시 문제점:**
    * `TrimmingCommentService`, `SpamFilteringCommentService`를 만듦.
    * 두 기능이 모두 필요한 경우 `TrimmingAndSpamFilteringService`를 또 만들어야 함.
    * 기능이 늘어날수록 클래스 조합이 폭발적으로 증가함 (**Class Explosion**).
    * 상속은 **정적(Static)**이라 컴파일 타임에 기능이 고정되어 유연하지 않음.

### 2. 해결책: 데코레이터 패턴 적용
* **개념:** 객체를 **감싸는(Wrapper)** 방식을 사용하여, **런타임(Runtime)에 동적으로** 기능을 추가하거나 조합하는 패턴임.
* **구조:**
    1.  **Component (Interface):** `CommentService` 인터페이스 정의.
    2.  **Concrete Component:** 기본 기능을 하는 `DefaultCommentService`.
    3.  **Decorator:** `Component`를 구현하면서, 동시에 내부에 `Component`를 필드로 가짐. (위임).
    4.  **Concrete Decorator:** `TrimmingDecorator`, `SpamFilteringDecorator` 등 부가 기능을 구현하고 원본 객체를 호출함.



### 3. 장단점
* **장점:**
    * **OCP (개방-폐쇄 원칙):** 기존 코드를 수정하지 않고도 데코레이터를 추가하여 기능을 확장할 수 있음.
    * **SRP (단일 책임 원칙):** 각 데코레이터는 하나의 부가 기능(자르기, 필터링 등)만 담당함.
    * **유연한 조합:** 상속처럼 클래스를 미리 만들지 않고, `new Trimming(new Spam(new Default()))` 처럼 런타임에 마음대로 조립 가능함.
* **단점:**
    * 작은 클래스(데코레이터)들이 많이 생겨 코드가 다소 복잡해질 수 있음.
    * 객체가 여러 겹으로 감싸져 있어 디버깅이 어려울 수 있음.

### 4. 실무 적용 사례 (Java & Spring)
* **Java I/O:**
    * 가장 대표적인 예시.
    * `new BufferedReader(new InputStreamReader(new FileInputStream(...)))`
    * `InputStream`이라는 추상화 뒤에 기능을 계속 덧붙이는 구조임.
* **Java Collections:**
    * `Collections.checkedList()`, `Collections.synchronizedList()`, `Collections.unmodifiableList()`
    * 기존 리스트를 감싸서 타입 체크, 동기화, 불변성 등의 기능을 추가함.
* **Spring / Servlet:**
    * `HttpServletRequestWrapper`, `HttpServletResponseWrapper`
    * 서블릿 필터(Filter)에서 요청/응답을 감싸서 변경하거나 부가 작업을 할 때 사용함.
