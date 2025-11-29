### 1. 개념: 언어와 문법의 해석
* **정의:** 자주 등장하는 문제나 패턴을 **언어(Language)**로 정의하고, 그 언어의 **문법(Grammar)**을 표현하는 클래스를 구현하여, 문장을 해석(Interpret)하고 실행하는 패턴임.
* **핵심:**
    * **Context:** 모든 표현식(Expression)이 공유하는 전역 정보(예: 변수 값).
    * **Expression:** 문법 규칙을 정의하는 인터페이스.
        * **Terminal Expression:** 더 이상 분해되지 않는 가장 기본적인 단위 (예: 숫자, 변수).
        * **Non-Terminal Expression:** 다른 표현식을 참조하거나 조합하는 단위 (예: 덧셈, 뺄셈).
    * **AST (Abstract Syntax Tree):** 문장을 분석하여 만든 트리 구조의 표현식 객체들.

### 2. 구현 예시 (후위 표기법 계산기)
* **문제:** `x y z + -` 같은 후위 표기법(Postfix Notation) 수식을 계산해야 함. (x=1, y=2, z=3 일 때 `1 - (2 + 3)` 계산).
* **구현:**
    1.  `Expression` 인터페이스 정의 (`interpret(Map<Character, Integer> context)` 메서드).
    2.  `VariableExpression` (Terminal): 변수 이름(`x`)을 Context에서 찾아 값(`1`)을 반환.
    3.  `PlusExpression`, `MinusExpression` (Non-Terminal): 좌/우 두 개의 Expression을 받아 계산 결과를 반환.
    4.  **Parser:** 수식 문자열을 읽어 `Stack`을 이용해 Expression 객체들을 트리 구조(AST)로 조립함.
* **결과:** 파싱된 Expression 객체의 `interpret()`만 호출하면 복잡한 수식 계산이 완료됨.

### 3. 장단점
* **장점:**
    * **확장성 (OCP):** 새로운 문법 규칙(예: 곱셈)을 추가하려면 새로운 Expression 클래스만 만들면 됨. 기존 코드를 거의 건드리지 않음.
    * **재사용성:** 자주 쓰이는 패턴을 언어로 정의해두면, 표현식만 바꿔서 쉽게 재사용 가능함.
* **단점:**
    * **복잡도 증가:** 문법 규칙 하나당 클래스 하나가 필요하므로, 문법이 복잡해지면 클래스 수가 폭발적으로 늘어남.
    * **유지보수 어려움:** 복잡한 문법을 다루기엔 구조가 너무 거대해질 수 있음 (이런 경우엔 전문적인 파서 생성기 사용 권장).

### 4. 실무 활용 사례 (Java & Spring)
* **Java - 정규표현식 (`java.util.regex.Pattern`):**
    * 문자열 패턴을 정의하고 해석하여 매칭되는지 검사함. 대표적인 인터프리터 패턴 예시임.
* **Spring - SpEL (Spring Expression Language):**
    * `#{2 + 5}` 처럼 문자열로 된 표현식을 해석하여 값을 계산하거나 빈(Bean)의 프로퍼티에 접근함.
    * `@Value("#{systemProperties['user.region']}")` 같은 애노테이션에서 자주 사용됨.
    * 내부적으로 파서가 문자열을 분석해 Expression 객체를 만들고 실행하는 구조임.
