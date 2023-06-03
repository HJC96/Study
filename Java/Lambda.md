### 람다식(Lambda expression)

### 람다식
  - 개념
    - 메서드를 하나의 '식'으로 표현한 것
  - 작성 방법
    - 일반적인 함수 표현
      - 반환타입 메서드이름(매개변수 선언) { expression }
    - 일반적인 람다식 표현
      - (매개변수 선언) -> { expression }   
### 람다식 표현
  - 매개변수 타입 생략 
    - 자료형을 추론 가능한 경우 생략 가능 
    - (int a, int b) -> a > b ? a : b
      - (a, b) -> a > b ? a : b
  - 대괄호 생략
    - 매개변수가 하나뿐인 경우 괄호 생략 가능. 
      - 단 매개변수 타입이 있으면 생략 불가
      - 문장의 끝에 ;를 붙이면 안됨.
    - (a) -> a * a (O)
    - (int a) -> a * a (X)
  - 중괄호 생략
    - 중괄호 안의 문장이 하나일 때는 생략 가능. 
      - 단 괄호 안의 문장이 return문일 경우 생략 불가.
    - (String name, int i) -> System.out.println(name + "="+i)
      - (int a, int b) -> {return a > b ? a : b ;} 

          







     
