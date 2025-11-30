### 1. 개념: 구조와 기능의 분리
* **정의:** 기존 객체(Element)의 구조를 변경하지 않고 새로운 기능(Operation)을 추가할 수 있게 해주는 패턴임.
* **핵심:**
    * **더블 디스패치 (Double Dispatch):** 실행할 메서드를 결정하기 위해 객체 타입과 메서드 파라미터 타입 두 가지를 모두 고려하여 동적으로 메서드를 선택하는 기법.
    * **구조:** `Element`(도형 등)와 `Visitor`(기능 수행자)를 분리함. `Element`는 `accept(Visitor)` 메서드만 가지며, 실제 기능은 `Visitor` 구현체에 담김.

### 2. 구현 예시 (도형과 디바이스 출력)
* **문제:** `Shape`(삼각형, 사각형 등) 인터페이스에 `printTo(Device)` 기능을 추가하려면 모든 도형 클래스를 수정해야 함 (OCP 위반). 디바이스 종류가 늘어날 때마다 `if-else` 문이 복잡해짐.
* **해결:**
    1.  **Element (`Shape`):** `accept(Device device)` 메서드를 정의하고, 구현체는 `device.print(this)`를 호출하여 자신(`this`)을 넘김.
    2.  **Visitor (`Device`):** `print(Triangle)`, `print(Rectangle)` 처럼 도형별로 오버로딩된 메서드를 정의함.
    3.  **Concrete Visitor (`Phone`, `Watch`):** 각 도형을 어떻게 출력할지 구체적인 로직을 구현함.
* **결과:** 새로운 디바이스(`iPad`)를 추가하려면 `Device` 구현체만 만들면 됨. 도형 클래스는 건드리지 않음.

### 3. 장단점
* **장점:**
    * **확장성 (OCP):** 기존 요소(`Element`) 코드를 수정하지 않고 새로운 연산(`Visitor`)을 추가하기 쉬움.
    * **SRP (단일 책임 원칙):** 각 `Visitor`는 특정 기능에만 집중하면 됨.
* **단점:**
    * **복잡도:** 더블 디스패치 구조로 인해 흐름을 이해하기 어려울 수 있음.
    * **유연성 부족:** 새로운 `Element`(예: `Circle`)가 추가되면 모든 `Visitor` 인터페이스와 구현체를 수정해야 함. (Element 변경에 취약).

### 4. 실무 활용 사례 (Java & Spring)
* **Java - `FileVisitor`:**
    * 파일 트리를 순회하면서 파일마다 특정 작업(삭제, 복사, 검색 등)을 수행할 때 사용됨.
    * `Files.walkFileTree(path, visitor)` 형태로 사용하며, `visitFile`, `preVisitDirectory` 등의 메서드를 오버라이딩하여 기능을 정의함.
* **Spring - `BeanDefinitionVisitor`:**
    * 스프링 내부에서 빈 설정 정보(`BeanDefinition`)를 방문하며 값을 수정하거나 처리할 때 사용됨 (일반 개발자가 직접 쓸 일은 거의 없음).
