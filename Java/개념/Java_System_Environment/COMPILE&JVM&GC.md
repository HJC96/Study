자바를 공부하면서 Application을 만드는데만 급급한 나머지 Compile 과정, JVM, GC 등 가볍게 알고 간과하고 넘어간 개념들이 많았습니다. 오늘 주말을 맞이하며 그 개념들을 정리해보고자 합니다.

### Java Compile

자바 코드를 작성하여 Machine에서 실행하기까지 내부에서 어떤 일이 일어날까요? 

크게 다음 과정을 떠올리면 됩니다.

.java File(Source Code) --> .class File(Byte Code) --> JRE(JVM) --> Machine(Window, Linux, Mac)

아키텍쳐로 보면 다음과 같습니다.


<img width="631" alt="image" src="https://github.com/HJC96/Study/assets/87226129/24f189ab-1d3e-41a4-ad65-5dc307d1e19f">

https://devaraj-durairaj.medium.com/java-architecture-and-components-febd83b3adfc

​

자바의 중요한 컨셉 중 하나는 "Write once, run anywhere". 즉, 자바 코드로 작성한 파일은 어떤 운영체제 에서든 실행할 수 있다는 것입니다. 

C/C++로 작성된 프로그램은 Window에서는 .exe 실행파일, Linux에서는 .out 파일로 실행되는것과 비교해봤을때 굉장히 파워풀하다는 것을 알 수 있습니다. 
​

그렇다면 JVM 내부에서는 어떤일이 일어나고 있는 것일까요?

JVM 내부

JVM 아키텍쳐를 설명해주는 그림이 많지만 하기의 그림이 가장 자세하게 설명된 것 같습니다.

​

JVM Architecture Diagram

<img width="625" alt="image" src="https://github.com/HJC96/Study/assets/87226129/9ff8f1ea-ba45-45d5-a8b6-0bc639b97a6c">


JVM 내부는 크게 세 부분으로 구성되어 있습니다.

1. ClassLoader Subsystem
2. Runtime Data Area
3. Execution Engine

오늘은 ```Runtime Data Area```와 ```Execution Engine(GC)```에 대해서만 정리해볼까 합니다.

### Runtime Data Area

- Method Area - 클래스 정보(클래스 이름, 부모 클래스 이름, 메서드 변수) 및 Static 변수를 저장합니다. JVM에서 한번 생성되며 모든 쓰레드가 공유합니다.
- Heap Area - 객체와 해당 객체 인스턴스의 변수와 배열이 할당되는 영역입니다. 
- Stack Area - 각 쓰레드가 별도로 갖고 있으며 LVA(Local Variable Array), OS(Operand Stack), FD(Frame Data)가 하나의 스택 프레임을 형성합니다.
- PC Registers - 현재 실행중인 명령어의 주소를 보유합니다.
- Native Method - 경우에 따라 특정 플랫폼에서 동작해야하는 시스템 호출을 해야하는 경우 자바 언어가 아닌 외부 언어로 작성된 메소드를 이용할 필요가 있는데, 이때 그것들을 위한 공간입니다

​

### Execution Engine(GC)
GC는 무슨 역할을 할까요? 

위에서 메모리에 대해 잠깐 언급했지만 우리가 자바코드로 다음과 같이 객체를 생성하면 

~~~java
Person person = new Person();
~~~

'new Person();' 은 힙 메모리에 Person 객체를 생성하고, 이 객체의 힙 주소를 반환합니다. 이 반환된 주소는 person 이라는 참조 변수에 저장되는데, 이 변수는 스택 메모리의 LVA에 위치합니다. 

​person 변수는 객체 자체를 가지고 있는 것이 아니라, 객체가 위치한 힙의 주소, 즉 참조값을 갖게 됩니다.

이때 해당 스택이 끝나게 되면 스택 영역의 person 변수는 사라지겠지만, 힙 영역의 Person 객체는 여전히 남아있게 되어 메모리 누수가 발생하게 되는데 이를 처리해주는게 Garbage Collector입니다.

프로그래머가 직접메모리를 관리하지 않아도 된다는 중요한 장점이 있지만, 작동시 CPU 리소스가 사용된다는 단점이 있습니다.

​

​
