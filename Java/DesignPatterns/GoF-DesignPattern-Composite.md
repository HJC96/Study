1. 개념: 전체와 부분의 일치
정의: 객체들의 관계를 트리 구조로 구성하여, 클라이언트가 **단일 객체(Leaf)**와 **복합 객체(Composite)**를 동일하게 취급할 수 있게 하는 패턴임.

핵심: 전체(Whole)와 부분(Part)이 같은 인터페이스(Component)를 구현함.

2. 예시: 게임 아이템과 가방
문제 상황:

Item(개별 아이템)과 Bag(아이템을 담는 가방)이 있음.

클라이언트는 "아이템의 가격"과 "가방(내부 아이템 합산)의 가격"을 구할 때마다 대상을 구분해서 로직을 짜야 함.

패턴 적용:

Component 인터페이스 정의: getPrice() 메서드를 선언함.

Leaf (Item): getPrice() 구현 -> 자신의 가격 반환.

Composite (Bag): getPrice() 구현 -> 내부 리스트(List<Component>)를 순회하며 가격의 합을 반환.

결과:

클라이언트는 대상이 Item인지 Bag인지 신경 쓰지 않고 component.getPrice()만 호출하면 됨.

3. 장단점
장점:

편의성: 복잡한 트리 구조를 단순하게(단일 인터페이스로) 다룰 수 있음.

확장성 (OCP): 새로운 타입의 아이템이나 가방이 추가되어도 클라이언트 코드는 변경되지 않음.

단점:

설계의 어려움: 모든 객체를 아우르는 공통 인터페이스를 정의하기 어려울 수 있음 (억지스러운 일반화 발생 가능).

타입 안전성: 런타임에 구체적인 타입을 체크해야 하는 경우가 생길 수 있음.

4. 실무 적용 사례 (Java Swing)
구조:

Component: java.awt.Component (공통 부모).

Leaf: JButton, JTextField 등 (내용물).

Composite: JFrame, JPanel, Container 등 (그릇).

동작:

JFrame에 JButton을 추가하든, JPanel을 추가하든 add(Component c) 메서드 하나로 처리됨.

setVisible(true) 같은 메서드 호출 시, 포함된 모든 자식 컴포넌트들에게 재귀적으로 적용됨.
