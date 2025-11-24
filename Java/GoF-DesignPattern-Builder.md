### 1\. 문제점: 복잡한 객체 생성의 어려움

  * **상황:** `TourPlan`(여행 계획)처럼 필드가 많고, 상황에 따라 필요한 필드가 다른 복잡한 객체를 생성해야 함.
  * **기존 방식의 문제 (Telescoping Constructor):**
      * 생성자 파라미터가 너무 많아짐.
      * 필요 없는 필드에 `null`이나 `0`을 넣어야 해서 코드가 지저분함.
      * **일관성 부족:** `days`를 설정하면 `nights`도 설정해야 하는데, 이를 강제하기 어려움.
      * **가독성 저하:** 생성자만 보고는 어떤 값이 어디에 들어가는지 파악하기 힘듦.

### 2\. 해결책: 빌더 패턴 (Builder Pattern)

  * **개념:** 복잡한 객체의 생성 과정과 표현 방법을 분리하여, 동일한 생성 절차로 서로 다른 표현 결과를 만들게 하는 패턴.
  * **구조:**
    1.  **Builder Interface:** 객체 생성 단계(`title`, `nightsAndDays` 등)를 정의.
    2.  **Concrete Builder:** 실제 객체를 생성하고 조립하는 로직 구현. 메서드 체이닝(Method Chaining)을 위해 `return this` 사용.
    3.  **Product:** 최종적으로 생성될 객체 (`TourPlan`).
    4.  **Director (선택사항):** 빌더를 사용하여 자주 사용되는 객체 조합(예: 칸쿤 여행, 롱비치 여행)을 미리 정의해두는 관리자 클래스.

### 3\. 구현의 핵심 포인트

  * **메서드 체이닝:** `builder.title("...").days(3)...` 처럼 물 흐르듯 작성 가능하게 함.
  * **검증 (Validation):** 최종 객체를 반환하는 `build()` (또는 `getPlan()`) 메서드에서 객체의 상태가 온전한지 검증함. (예: 2박 3일인데 숙소가 없는지 체크).
  * **Director 활용:** 클라이언트는 빌더의 복잡한 메서드를 몰라도, 디렉터에게 "칸쿤 여행 만들어줘"라고 요청만 하면 됨.

### 4\. 장단점 요약

  * **장점:**
      * **가독성:** 필요한 데이터만 설정하며 코드가 직관적임.
      * **안전성:** 불완전한 객체 생성을 방지하고 검증 로직을 넣기 좋음.
      * **유연성:** `Director`와 조합하여 다양한 구성의 객체를 쉽게 생성 가능.
      * **다형성:** 인터페이스를 통해 다른 빌더(예: `VipTourBuilder`)로 교체하여 확장하기 용이함.
  * **단점:**
      * **코드량 증가:** 빌더 클래스, 디렉터 등을 추가로 만들어야 해서 구조가 복잡해짐.
      * **성능:** 객체 생성 시 빌더 객체도 함께 생성해야 하므로 미세한 성능/메모리 오버헤드 존재함.

### 5\. 실무 적용 사례 (Java & Spring)

  * **Java:**
      * `StringBuilder` / `StringBuffer`: `append()`를 통해 문자열을 조립하고 `toString()`으로 완성함.
      * `Stream.Builder`: 스트림을 빌더로 생성 가능.
  * **Lombok (`@Builder`):**
      * 실무에서 가장 많이 쓰는 방식.
      * 클래스 위에 `@Builder` 애노테이션만 붙이면 컴파일 시점에 자동으로 빌더 코드를 생성해줌. (매우 편리함).
  * **Spring:**
      * `UriComponentsBuilder`: URI를 안전하게 생성하고 인코딩까지 처리해줌.
      * `MockMvcWebClientBuilder` 등 다양한 빌더 제공.

### 6\. 코드 예시 (간략)

**[Builder & Director 적용]**

```java
// 사용 (Client)
TourDirector director = new TourDirector(new DefaultTourBuilder());
TourPlan cancunTrip = director.cancunTrip(); // 디렉터를 통해 생성
```

**[Lombok 활용 (실무 권장)]**

```java
@Builder
public class TourPlan {
    private String title;
    private int days;
    // ...
}

// 사용
TourPlan plan = TourPlan.builder()
    .title("칸쿤 여행")
    .days(3)
    .build();
```
