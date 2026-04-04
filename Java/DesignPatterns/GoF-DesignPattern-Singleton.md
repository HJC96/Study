~~~java
public class Settings {
  private static Settings instance;
  private Settings() { }
  
  public static Settings getInstance() {
    if (instance == null) {
      instance = new Settings();
    }
    return instance;
  }
}
~~~

위 코드는 스레드 세이프하지 않다.

요청하신 내용에 맞춰 예시 코드를 간략히 정리함.

### 1\. 메서드 동기화 (Synchronized Method)

가장 단순하지만 성능 저하가 발생할 수 있는 방식임.

```java
public class Settings {
    private static Settings instance;

    private Settings() {}

    // synchronized 키워드로 락을 검
    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
```

### 2\. 이른 초기화 (Eager Initialization)

Thread-safe 하지만, 사용하지 않아도 메모리를 차지함.

```java
public class Settings {
    // 클래스 로딩 시점에 즉시 생성 (static final)
    private static final Settings INSTANCE = new Settings();

    private Settings() {}

    public static Settings getInstance() {
        return INSTANCE;
    }
}
```

### 3\. Double-Checked Locking (DCL)

효율적이지만 구현이 복잡하고 `volatile`을 써야 함.

```java
public class Settings {
    // volatile 필수 (Java 1.5+)
    private static volatile Settings instance;

    private Settings() {}

    public static Settings getInstance() {
        if (instance == null) { // 1차 체크
            synchronized (Settings.class) {
                if (instance == null) { // 2차 체크 (동기화 블록 내)
                    instance = new Settings();
                }
            }
        }
        return instance;
    }
}
```

### 4\. Static Inner Class (Holder) - **[권장]**

가장 깔끔하고 안전하며, 지연 로딩(Lazy Loading)도 지원함.

```java
public class Settings {
    
    private Settings() {}

    // 1. private static inner class 정의
    private static class SettingsHolder {
        private static final Settings INSTANCE = new Settings();
    }

    // 2. 메서드 호출 시점에 Holder 클래스가 로딩되며 인스턴스 생성됨
    public static Settings getInstance() {
        return SettingsHolder.INSTANCE;
    }
}
```


### 1\. 싱글톤이 깨지는 경우

  * **리플렉션 공격:**
      * `Reflection API`를 쓰면 `private` 생성자도 강제로 열 수 있음(`setAccessible(true)`).
      * 이를 통해 새로운 인스턴스를 강제로 생성하면 싱글톤이 깨짐.
  * **직렬화/역직렬화 (Serialization):**
      * 싱글톤 객체를 파일로 저장(직렬화)했다가 다시 읽어올 때(역직렬화), 생성자가 실행되지 않고 새 인스턴스가 만들어짐.

### 2\. 대응 방법 (기존 코드 수정)

  * **리플렉션 방어:** 생성자 안에서 이미 인스턴스가 있는지 체크하고 예외를 던지게 함.
  * **직렬화 방어:** `readResolve()`라는 특별한 메서드를 구현하여, 역직렬화 시 새로 만든 객체 대신 기존 싱글톤 인스턴스를 반환하게 함.

<!-- end list -->

```java
public class Settings implements Serializable {
    
    // ... 기존 Holder 패턴 코드 ...

    private Settings() {
        // [리플렉션 방어] 생성자가 호출되었는데 이미 인스턴스가 있다면 예외 발생시킴
        if (SettingsHolder.INSTANCE != null) {
            throw new RuntimeException("생성자를 호출할 수 없습니다.");
        }
    }

    // [직렬화 방어] 역직렬화 시 이 메서드가 호출되며, 기존 인스턴스를 반환함
    protected Object readResolve() {
        return getInstance();
    }
}
```

### 3\. 가장 확실하고 간편한 해결책: Enum

이 모든 걸 방어하려고 코드를 덕지덕지 붙이는 대신, \*\*Enum(열거형)\*\*을 사용하면 해결됨.

  * **방법:** 그냥 `class` 대신 `enum`으로 만듦.
  * **장점:**
      * 자바 언어 차원에서 **리플렉션 공격을 막아줌** (생성자 호출 시 에러 발생).
      * **직렬화/역직렬화도 완벽하게 처리**되어 있음.
      * **Effective Java**의 저자 조슈아 블로크가 가장 권장하는 방법임.
  * **단점:** 상속(Inheritance)을 사용할 수 없고, 로딩 시점에 만들어짐(Lazy Loading 아님).

**[Enum 싱글톤 예시 코드]**

```java
public enum Settings {
    INSTANCE; // 이게 끝임.

    // 필요한 비즈니스 로직 메서드 추가
    public void doSomething() {
        // ...
    }
}

// 사용법
Settings.INSTANCE.doSomething();
```


### 1\. Enum도 생성자가 있는가? -\> **있음**

  * **특징:** `enum`의 생성자는 무조건 \*\*`private`\*\*임. (public으로 선언하면 컴파일 에러 남).
  * **호출 시점:** 개발자가 `new Enum()`으로 호출하는 게 아님. **JVM이 클래스를 로딩하고 상수(INSTANCE)를 만들 때 자동으로 호출**함.
  * 따라서 외부에서 임의로 생성할 수 없어 싱글톤 보장이 강력함.

### 2\. 일반 클래스처럼 쓸 수 있는가? -\> **가능함**

  * 멤버 변수(필드) 선언 가능.
  * 메서드 선언 가능.
  * 인터페이스 구현(`implements`) 가능.
  * **즉, 싱글톤 객체로서 필요한 비즈니스 로직과 상태를 다 가질 수 있음.**

### 3\. 결정적인 차이점 (제약사항)

  * **상속 불가:** 이미 `java.lang.Enum` 클래스를 상속받고 있기 때문에 **다른 클래스를 상속(`extends`)할 수 없음.** (단, 인터페이스는 구현 가능).
  * 이게 유일한 단점이라, 상속이 꼭 필요한 계층 구조가 아니라면 `enum`이 가장 좋음.

-----

### [코드 예시] 생성자와 로직이 있는 Enum 싱글톤

보통 `INSTANCE;` 만 적고 끝내지만, 실제로는 아래처럼 일반 클래스같이 씀.

```java
public enum Settings {
    INSTANCE; // 1. 이 시점에 생성자가 호출됨

    // 멤버 변수 (일반 클래스와 같음)
    private String dbUrl;
    private int timeout;

    // 생성자 (private 생략되어 있음, 직접 호출 불가)
    Settings() {
        System.out.println("Settings 인스턴스 생성됨 (초기화 로직 수행)");
        this.dbUrl = "jdbc:mysql://localhost:3306/mydb";
        this.timeout = 5000;
    }

    // 비즈니스 메서드 (일반 클래스와 같음)
    public void printConfig() {
        System.out.println("DB: " + dbUrl + ", Timeout: " + timeout);
    }
}

// 사용
public class Main {
    public static void main(String[] args) {
        // 이때 생성자가 실행되고, 이미 만들어진 객체를 가져옴
        Settings.INSTANCE.printConfig();
    }
}
```

### 요약

  * `enum`도 생성자가 있지만 \*\*JVM만 호출 가능(private)\*\*함.
  * 필드, 메서드 추가해서 **일반 클래스처럼 사용 가능**함.
  * 단, **상속(`extends`)은 불가능**함.

