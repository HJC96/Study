Comparator? Java 8 쓰면 쉬움.
예전처럼 Comparator 인터페이스 막 구현하고 그럴 필요 X

Comparator.comparing(키) 이걸로 시작.

comparing(Person::getLastName) 이런 식으로 메소드 레퍼런스 넘겨주면 알아서 그걸로 비교

비교 조건이 여러 개면? 체이닝

예시: comparing(Person::getLastName).thenComparing(Person::getFirstName)

성능? 직접 구현하는 것보다 느릴 수 있지만, 요즘 자바에선 별 차이 안난다고 함.
