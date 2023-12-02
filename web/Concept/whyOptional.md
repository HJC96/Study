## Optional<> 쓰는 이유

- **Null 참조 문제 방지**: Null 참조는 많은 버그와 NullPointerException을 일으킬 수 있습니다. **`Optional`**을 사용하면 변수가 null 값을 가질 수 있는지 명확히 표현하고 처리할 수 있게 됩니다.
- **명시적인 계약**: 메서드의 반환 값이 **`Optional`**로 되어 있으면, 해당 메서드가 값을 반환하지 않을 수도 있다는 것을 호출자에게 명시적으로 알립니다. 따라서 호출자는 값을 처리하기 전에 해당 값이 존재하는지 확인하게 됩니다.
- **함수형 프로그래밍**: **`Optional`** 클래스는 여러 함수형 메서드를 제공합니다 (**`map`**, **`filter`**, **`ifPresent`** 등). 이를 통해 값의 존재 여부에 따른 조작이나 행동을 간결하고 명확하게 표현할 수 있습니다.

`**주의**`

- 필드로 사용하지 않는다.
    - : **`Optional`**은 주로 반환 값에 대한 정보를 제공하는 용도로 사용되며, 필드나 데이터 구조의 기본 유형으로 사용하기에는 적합하지 않습니다.

```java
import java.util.Optional;

public class User {
    private Optional<String> nickname; // Optional로 필드 선언

    public User() {
        nickname = Optional.empty(); // 초기화
    }

    public Optional<String> getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = Optional.ofNullable(nickname);
    }
}
```
