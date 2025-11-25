### 1. 개념 및 예시
* **개념:** 호환되지 않는 인터페이스를 가진 코드를 함께 작동할 수 있도록, **중간에서 변환해주는 패턴**임.
* **비유:** 110V 플러그를 220V 콘센트에 꽂기 위해 사용하는 **'돼지코(변환 어댑터)'**와 같음.
* **구조:**
    * **Client:** 타겟 인터페이스(`UserDetailsService`)를 사용하는 코드 (`LoginHandler`).
    * **Target Interface:** 클라이언트가 기대하는 인터페이스 (`UserDetailsService`).
    * **Adaptee:** 기능을 제공하지만 인터페이스가 맞지 않는 기존 코드 (`AccountService`).
    * **Adapter:** Adaptee를 감싸서 Target Interface로 변환해주는 클래스.

### 2. 구현 방법 2가지
**상황:** `LoginHandler`는 `UserDetailsService`를 원하지만, 우리는 `AccountService`만 가지고 있음.

* **방법 1: 별도의 어댑터 클래스 생성 (권장)**
    * `AccountUserDetailsService`라는 새 클래스를 만들고 `UserDetailsService`를 구현(implements)함.
    * 내부에서 `AccountService`를 주입받아 사용함.
    * **장점:** 기존 코드(`AccountService`)를 건드리지 않음 (OCP, SRP 준수).
    * **사용:** 우리가 수정할 수 없는 라이브러리나 레거시 코드일 때 유용함.

* **방법 2: 직접 구현 (실용적 접근)**
    * `AccountService`가 직접 `UserDetailsService`를 구현(implements)하도록 기존 코드를 수정함.
    * **장점:** 클래스를 새로 만들지 않아 구조가 단순해짐.
    * **단점:** 기존 코드를 수정해야 하므로 OCP, SRP 위반 소지가 있음.
    * **사용:** 우리가 직접 관리하는 코드이고, 복잡도를 낮추고 싶을 때 선택 가능.

### 3. 관련 객체지향 원칙 (OOP Principles)
* **OCP (개방-폐쇄 원칙):** 기존 코드(`Adaptee`)를 수정하지 않고도, 어댑터를 통해 새로운 인터페이스(`Target`)로 기능을 확장할 수 있음.
* **SRP (단일 책임 원칙):** '기존 비즈니스 로직'과 '인터페이스 변환 로직'을 분리하여 관리할 수 있음.

### 4. 실무 활용 사례 (Java & Spring)
* **Java:**
    * `Arrays.asList(arr)`: 배열(Array)을 리스트(List) 인터페이스로 변환.
    * `Collections.list(enumeration)`: Enumeration을 List로 변환.
    * `InputStreamReader(inputStream)`: 바이트 스트림(`InputStream`)을 문자 스트림(`Reader`)으로 변환.
* **Spring MVC:**
    * **`HandlerAdapter`**: 스프링 MVC의 핵심.
    * **문제:** 컨트롤러(핸들러)의 구현 방식이 다양함(어노테이션, 인터페이스 등).
    * **해결:** `DispatcherServlet`은 구체적인 핸들러를 몰라도 `HandlerAdapter` 인터페이스를 통해 요청을 처리함. 즉, 다양한 핸들러를 `HandlerAdapter`가 감싸서 통일된 방식으로 실행시켜 줌.
