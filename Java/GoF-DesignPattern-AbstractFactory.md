### 1. 개념 및 문제 상황
* **개념:** 서로 **관련 있는 여러 객체**를 만들어주는 인터페이스를 제공하는 패턴.
* **기존 문제점:**
    * `WhiteShip`을 만들 때 `WhiteAnchor`, `WhiteWheel` 등 구체적인 클래스를 직접 `new` 해서 사용했음.
    * 만약 `Pro` 버전 부품으로 교체하려면, 클라이언트 코드 내의 모든 `new` 부분을 일일이 수정해야 함.
    * 이는 객체지향 원칙 중 **OCP(개방-폐쇄 원칙)**에 위배됨.

### 2. 해결책: 추상 팩토리 적용
* **구현 방법:**
    1.  **추상화:** 부품들을 생성하는 인터페이스(`ShipPartsFactory`)와 각 부품의 인터페이스(`Anchor`, `Wheel`)를 정의함.
    2.  **구체화:** `WhiteShipPartsFactory` 등 실제 부품을 찍어내는 구체적인 팩토리 클래스를 만듦.
    3.  **사용(Client):** 클라이언트는 `new WhiteAnchor()` 대신 `partsFactory.createAnchor()`를 호출함.
* **결과:**
    * 클라이언트는 구체적인 클래스(`WhiteAnchor`)를 몰라도 됨.
    * 부품 군을 `Pro` 버전으로 바꾸고 싶다면, 단순히 주입해주는 팩토리만 `WhiteShipPartsProFactory`로 교체하면 됨. (코드 변경 없음)

### 3. 팩토리 메서드 vs 추상 팩토리 (비교)
두 패턴 모두 **"객체 생성 과정을 추상화"** 한다는 점은 같지만, 관점과 목적이 다름.

| 구분 | 팩토리 메서드 패턴 (Factory Method) | 추상 팩토리 패턴 (Abstract Factory) |
| :--- | :--- | :--- |
| **관점** | **상속 (Inheritance)** | **조합 (Composition)** |
| **초점** | 구체적인 객체 생성 책임을 **하위 클래스**로 넘기는 것. | 클라이언트가 **팩토리 인터페이스**를 사용하여 객체 군을 생성하는 것. |
| **목적** | **한 가지** 제품을 생산하는 구조 유연성 확보. | **관련된 여러 제품(Family)**을 일관성 있게 생성. |

### 4. 실무 적용 사례 (Java & Spring)
* **Java:** `DocumentBuilderFactory` (XML 파싱)
    * `newInstance()`로 팩토리를 얻고, 그 팩토리가 구체적인 파서(`DocumentBuilder`)를 생성해줌.
* **Spring:** `FactoryBean`
    * 복잡한 빈(Bean) 생성 과정이 필요할 때 사용함.
    * Spring 내부에서 이 인터페이스를 구현한 클래스를 빈으로 등록하면, 실제로는 `getObject()`가 반환하는 객체가 빈으로 사용됨.
    * 즉, 빈 생성 로직을 추상화하여 제공하는 형태임.

ex)
패턴 적용 (FactoryBean 구현):

1. ShipFactory라는 클래스를 만들고 스프링의 FactoryBean 인터페이스를 구현함.
2. 이 ShipFactory를 스프링 빈으로 등록함. (ID: "whiteShip")
3. 개발자가 getBean("whiteShip")을 요청하면?
  - 스프링은 "어? 이거 팩토리네?" 하고 감지함.
  - 팩토리를 주는 게 아니라, 팩토리 내부의 getObject()를 호출해서 거기서 만들어진 결과물(배, Ship) 을 줌.
