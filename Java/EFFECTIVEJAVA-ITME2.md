~~~java
public abstract class Pizza {

    public enum Topping {
        HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
    }

    final Set<Topping> toppings;

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }

    abstract static class Builder<T extends Builder<T>> {

        private EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(topping);
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }
}
~~~

~~~java
public class NyPizza extends Pizza {

    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    private final Size size; // 필수 매개변수

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }

    public static class Builder extends Pizza.Builder<Builder> {

        private final Size size;

        public Builder(Size size) {
            this.size = size;
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}

public class CalzonePizza extends Pizza {

    private final boolean sauceInside; // 선택 매개변수

    private CalzonePizza(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }

    public static class Builder extends Pizza.Builder<Builder> {

        private boolean sauceInside = false;

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        CalzonePizza build() {
            return new CalzonePizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

}
~~~


### ## 1. 모든 것의 시작: 기본 빌더 패턴

가장 먼저, 상속이 없는 단순한 클래스(`SimplePizza`)를 위한 빌더를 살펴봤습니다.

* **목표**: 선택적 매개변수가 많은 객체를 **안전하고 읽기 쉽게** 만드는 것.
* **핵심 기술**: 각 설정 메서드(예: `addCheese()`)가 **빌더 자신(`this`)**을 반환하여, `.`을 계속 찍어 나가는 **메서드 체이닝**을 구현하는 것입니다.
* **결론**: 이 단계의 코드는 아주 직관적이고 이해하기 쉽습니다.

---

### ## 2. 상속 구조의 등장과 '강등' 문제

다음으로 `Pizza`라는 부모와 `NyPizza`라는 자식 클래스 구조에 기본 빌더를 적용했습니다. 여기서 심각한 문제가 발생했습니다.

* **문제**: 자식 빌더(`NyPizza.Builder`)에서 부모의 메서드(`addTopping()`)를 호출하는 순간, 반환 타입이 부모인 `Pizza.Builder`로 **강등**되어 버립니다.
* **결과**: 이로 인해 메서드 체이닝이 **뚝 끊겨서**, `NyPizza.Builder`만의 메서드를 이어서 호출할 수 없게 됩니다.
* **나쁜 해결책**: `(NyPizza.Builder)`처럼 매번 **수동으로 형변환**을 하면 되지만, 코드가 복잡해지고 위험해져 빌더 패턴의 장점이 사라집니다.



---

### ## 3. 궁극의 해결책: 재귀적 제네릭과 `self()`

위의 '강등' 문제를 우아하고 안전하게 해결하기 위해 이펙티브 자바의 최종 코드가 등장합니다. 이 코드는 두 가지 고급 기술을 사용합니다.

#### **① 재귀적 타입 한정: `T extends Builder<T>`**

이 복잡한 문법의 유일한 목표는 **"자기소개 규칙"**을 만드는 것입니다.

* **의미**: "나(`Builder`)를 상속하는 모든 자식(`T`)은, 반드시 **자기 자신의 타입(`T`)**을 이름표처럼 달아서 나에게 알려줘야 해!"
* **역할**: 자식 빌더가 `class NyPizza.Builder extends Pizza.Builder<CalzoneBuilder>`처럼 자기 타입에 대해 거짓말을 하는 것을 **컴파일 시점에 원천 차단**하는 강력한 보안 장치입니다. `NyPizza.Builder`는 반드시 `Pizza.Builder<NyPizza.Builder>`를 상속해야만 합니다.

#### **② `self()` 메서드**

이것은 '자기소개 규칙'을 실제로 구현하는 행동대장입니다.

* **의미**: 부모 빌더는 `protected abstract T self();` 라는 추상 메서드를 정의합니다.
* **역할**: 모든 자식 빌더는 이 `self()` 메서드를 구현하여 **자기 자신(`this`)**을 반환해야 합니다. 부모의 `addTopping()` 같은 메서드는 `this` 대신 이 `self()`를 반환합니다. 그 결과, `addTopping()`을 호출해도 `NyPizza.Builder`의 `self()`가 호출되어, 강등 문제 없이 `NyPizza.Builder` 타입이 그대로 반환됩니다.



---

### ## 최종 요약

| 단계 | 핵심 | 결과 |
| :--- | :--- | :--- |
| **1. 기본 빌더** | `return this;` | 메서드 체이닝이 잘 동작함. |
| **2. 상속 문제** | `return this;` (부모 메서드에서) | 자식 타입이 부모 타입으로 **강등**되어 체인이 끊김. |
| **3. 최종 해결** | `<T extends Builder<T>>` + `return self();` | **자기소개 규칙**으로 타입 안전성을 확보하고, `self()`를 통해 **강등 문제 없이** 체인을 완벽하게 유지함. |

결론적으로, 이펙티브 자바의 피자 빌더 예제는 **상속 관계가 있는 복잡한 객체 계층 구조에서도 타입 안전성을 잃지 않고 유연한 메서드 체이닝을 구현**하기 위한, 매우 정교하고 우아한 설계 패턴의 정수라고 할 수 있습니다.



