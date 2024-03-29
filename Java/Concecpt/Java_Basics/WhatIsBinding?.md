# What Is Binding?
| 바인딩 유형     | 정의                                              | 언제 사용되는가                              | 특징                                                                 |
|-------------|-------------------------------------------------|-----------------------------------------|---------------------------------------------------------------------|
| 정적 바인딩  | 컴파일 시간에 메서드 호출과 메서드 정의를 연결하는 방식       | private, static 또는 final 메서드 호출 시 사용 | - 컴파일러가 메서드 호출을 처리<br>- 오버로딩된 메서드에서 주로 발생  |
| 동적 바인딩  | 런타임(실행 시간)에 메서드 호출과 메서드 정의를 연결하는 방식   | 오버라이딩된 메서드 호출 시 사용             | - 런타임에 객체의 실제 타입에 따라 메서드 결정<br>- 다형성을 지원     |


### 정적 바인딩 (Static Binding)
- 정의: 컴파일 시간에 메서드 호출과 메서드 정의가 연결됩니다.
- 사용: private, static, final과 같은 메서드에 대해 사용됩니다. 이런 메서드는 런타임에 변경될 수 없기 때문에 컴파일 시점에 결정됩니다.
- 특징: 컴파일러는 메서드 호출을 처리하고, 오버로딩된 메서드의 경우에 주로 발생합니다. 컴파일 시간에 어떤 메서드가 호출될지 결정되므로 성능적 이점이 있습니다.
~~~java
MyClass myClass = new MyClass();
myClass.print("Hello"); // 매개변수 타입에 따라 컴파일 시 결정된 'print' 메서드 호출
~~~

### 동적 바인딩 (Dynamic Binding)
- 정의: 프로그램이 실행되는 동안(런타임)에 메서드 호출과 메서드 정의가 연결됩니다.
- 사용: 오버라이딩된 메서드에 대해 사용됩니다. 상속 관계에서 부모 클래스 타입의 참조 변수가 자식 클래스의 객체를 참조할 때 발생합니다.
- 특징: 런타임에 객체의 실제 타입을 확인하고, 해당 타입에 맞는 오버라이딩된 메서드를 호출합니다. 이는 다형성을 지원하는 중요한 메커니즘입니다.
~~~java
Animal animal = new Dog();
animal.sound(); // 'Dog' 클래스의 'sound()' 메서드가 호출됨
~~~
