## 무엇이 문제일까? (메모리 누수가 발생하는 코드)
먼저 문제가 되는 pop 메서드 코드를 보겠습니다.

```Java
public class Stack {
    private Object[] elements;
    private int size = 0;
    // ... 생성자 등 생략 ...

    // 문제가 있는 pop 메서드
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size]; // 객체를 반환하고 size만 줄인다.
    }
}
```
1. stack.push("객체A"); 를 호출합니다.
elements 배열: ["객체A", null, null, ...]
size: 

2. stack.push("객체B"); 를 호출합니다.
elements 배열: ["객체A", "객체B", null, ...]
size: 2

3. Object poppedObject = stack.pop(); 을 호출합니다.
pop 메서드는 "객체B"를 반환합니다.
size는 1로 줄어듭니다.
바로 이 지점이 문제입니다! size는 1이 되었지만, elements 배열의 내부는 여전히 아래와 같습니다.

elements 배열: ["객체A", **"객체B"**, null, ...]
