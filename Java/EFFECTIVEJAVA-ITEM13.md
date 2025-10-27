`Cloneable`? Java에선 **사용 안 하는 걸 권장.**

예전처럼 `Cloneable` 인터페이스 구현하고, `clone()` 메소드 오버라이드하고, `super.clone()` 호출하고, `try-catch`로 `CloneNotSupportedException` 감싸고, 배열이나 리스트 같은 가변 필드는 일일이 깊은 복사(Deep Copy) 해주고... **매우 복잡하고 버그 생기기 쉬움.**

**'복사 생성자(Copy Constructor)'** 이걸로 해결.

`public Person(Person original) { ... }` 이런 식으로 원본 객체를 받는 생성자를 만들고, 필드 값을 직접 복사.

`final` 필드 있어도? `clone`은 `final` 필드 재할당이 불가능해서 에러 나지만, **복사 생성자는 `final` 필드 초기화가 가능해서 완벽하게 작동.**

예시: `public Person(Person original) { this.name = original.name; this.address = new Address(original.address); // 깊은 복사 }`

다른 타입으로 복사하려면? `clone`은 불가능하지만, 생성자는 유연함.

예시: `public TreeSet(Collection c) { ... }` (ex: `HashSet`을 받아 `TreeSet`으로 복사 가능)

**결론?** `clone` 쓰지 말고, 명확하고 안전한 **복사 생성자**나 **복사 팩토리 메소드**를 쓰자.
