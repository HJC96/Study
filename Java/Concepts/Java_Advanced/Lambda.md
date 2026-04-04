## 람다식(Lambda expression)

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
  - 괄호 생략
    - 소괄호 생략
      - 매개변수가 하나뿐인 경우 괄호 생략 가능. 
        - 단 매개변수 타입이 있으면 생략 불가
        - 문장의 끝에 ;를 붙이면 안됨.
      - a -> a * a (O)
      - int a -> a * a (X)
    - 중괄호 생략
      - 중괄호 안의 문장이 하나일 때는 생략 가능. 
        - 단 괄호 안의 문장이 return문일 경우 생략 불가.
      - (String name, int i) -> System.out.println(name + "="+i)
        - (int a, int b) -> {return a > b ? a : b ;} 

          
### 함수형 인터페이스
람다식은 어떤 클래스에 포함된 메서드가 아닌, 익명 클래스의 **객체**와 동일하다.
(int a, int b) -> a > b ? a : b;   
- new Object(){ int max(int a, int b) {return a > b ? a : b}}
~~~java
MyFunction f = new MyFunction(){
                public int max(int a, int b){
                  return a > b ? a : b;
                }
};
int big = f.max(5,3);

//람다식으로 정의된 익명 객체의 메서드 호출 방법
MyFunction f = (int a, int b) -> a > b ? a : b;
int big = f.max(5,3);

~~~

- 람다식의 타입과 형변환
  - 람다식은 익명 객체이고 익명 객체는 타입이 없다.
    - 정확히는 타입은 있지만 컴파일러가 임의로 이름을 정하기 때문에 알 수 없다.
  - MyFunction f = (MyFunction)(()->{});    






     
