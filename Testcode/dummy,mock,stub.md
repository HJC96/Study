# dummy, mock, stub, spy, fake 정리
```java
// 의존성 인터페이스
public interface EmailClient {
    String sendEmail(String to, String subject, String body);
}

// 테스트 대상 클래스 (SUT: System Under Test)
public class NotificationService {
    private EmailClient emailClient;

    public NotificationService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public void notifyUser(String userEmail, String message) {
        String subject = "알림 메시지입니다.";
        this.emailClient.sendEmail(userEmail, subject, message);
    }
}
```

-----

### 1\. Dummy (더미)

  * **핵심 개념**: 실제 기능 없이 단지 인자값의 자리를 채우기 위한 껍데기 객체입니다. 보통 `null`을 사용하거나 비어있는 객체를 전달합니다.

  * **시나리오**: `NotificationService` 객체 생성 자체만을 테스트하고 싶을 때, `EmailClient`는 어떤 기능도 할 필요가 없습니다.

  * **예제 코드** (JUnit 5)

    ```java
    import org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.*;

    class DummyTest {
        @Test
        void testServiceCreation() {
            // Dummy: 단순히 자리를 채우기 위해 null을 전달
            EmailClient dummyClient = null;

            NotificationService service = new NotificationService(dummyClient);

            // 객체가 성공적으로 생성되었는지 확인
            assertNotNull(service);
            System.out.println("Dummy 예제: 객체 생성 성공!");
        }
    }
    ```

  * **코드 설명**: `NotificationService` 생성자의 `EmailClient` 파라미터 자리를 채우기 위해 `null`을 더미 객체로 사용했습니다. 이 테스트에서는 이메일 발송 로직을 전혀 사용하지 않으므로, 더미는 아무런 문제가 되지 않습니다.

-----

### 2\. Fake (페이크)

  * **핵심 개념**: 실제 로직을 가지고 동작하지만, 프로덕션용이 아닌 테스트용으로 단순화된 구현체입니다.

  * **시나리오**: 실제 이메일 서버(SMTP) 연동 없이, 보낸 이메일을 리스트(List)에 저장하는 가짜 `FakeEmailClient`를 만들어 테스트합니다.

  * **예제 코드** (JUnit 5)

    ```java
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    // Fake: 실제 로직을 단순하게 흉내 낸 가짜 구현체
    public class FakeEmailClient implements EmailClient {
        private List<Map<String, String>> sentEmails = new ArrayList<>();

        @Override
        public String sendEmail(String to, String subject, String body) {
            Map<String, String> email = new HashMap<>();
            email.put("to", to);
            email.put("subject", subject);
            email.put("body", body);
            sent_emails.add(email);
            return "SUCCESS";
        }

        public List<Map<String, String>> getSentEmails() {
            return sentEmails;
        }
    }

    class FakeTest {
        @Test
        void testNotifyUserWithFake() {
            FakeEmailClient fakeClient = new FakeEmailClient();
            NotificationService service = new NotificationService(fakeClient);

            service.notifyUser("test@example.com", "페이크 테스트");

            // Fake 객체의 내부 상태를 직접 확인하여 검증
            assertEquals(1, fakeClient.getSentEmails().size());
            assertEquals("test@example.com", fakeClient.getSentEmails().get(0).get("to"));
            System.out.println("Fake 예제: 이메일이 Fake 객체에 잘 저장되었습니다.");
        }
    }
    ```

  * **코드 설명**: `FakeEmailClient`는 `sendEmail` 메서드가 호출되면 이메일 정보를 내부 리스트에 저장합니다. 테스트가 끝난 후, 이 리스트의 상태를 확인하여 `NotificationService`가 올바르게 동작했는지 검증할 수 있습니다. **인메모리 DB**가 대표적인 Fake의 예입니다.

-----

### 3\. Stub (스텁)

  * **핵심 개념**: 특정 메서드가 호출되면, 미리 정해놓은 값을 반환하도록 만들어진 객체입니다. 주로 **상태 검증**에 사용됩니다.

  * **시나리오**: 이메일 발송이 실패했을 때(`sendEmail`이 "FAIL"을 반환) `NotificationService`가 어떻게 반응하는지 테스트합니다.

  * **예제 코드** (JUnit 5 + Mockito)

    ```java
    import static org.mockito.Mockito.*;
    // (NotificationServiceV2는 이메일 발송 결과에 따라 다른 값을 리턴하는 수정된 버전이라 가정)

    class StubTest {
        @Test
        void testFailedNotification() {
            // Stub: Mockito로 가짜 객체를 만들고, 미리 정해진 답변을 설정
            EmailClient stubClient = mock(EmailClient.class);
            when(stubClient.sendEmail(anyString(), anyString(), anyString())).thenReturn("FAIL");

            NotificationServiceV2 service = new NotificationServiceV2(stubClient);
            String result = service.notifyUser("test@example.com", "스텁 테스트");

            // Stub이 "FAIL"을 반환했기 때문에, 서비스의 최종 상태(결과)가
            // 'LOGGED_FAIL'이 되었는지 검증
            assertEquals("LOGGED_FAIL", result);
            System.out.println("Stub 예제: Stub이 'FAIL'을 반환하여 실패 로직이 잘 동작했습니다.");
        }
    }
    ```

  * **코드 설명**: Mockito의 `when(...).thenReturn(...)`을 사용해 `sendEmail` 메서드가 어떤 인자로 호출되든 무조건 "FAIL"을 반환하도록 스텁을 만들었습니다. 이 스텁 덕분에 `NotificationServiceV2`의 실패 처리 로직(상태)을 테스트할 수 있습니다.

-----

### 4\. Spy (스파이)

  * **핵심 개념**: **실제 객체**를 감싸서, 어떤 메서드가 호출되었는지 등의 정보를 기록합니다. 실제 객체의 로직은 그대로 수행됩니다. **행위 검증**에 사용됩니다.

  * **시나리오**: `NotificationService`가 실제 `EmailClient` 객체의 `sendEmail` 메서드를 올바르게 호출하는지 확인하고 싶습니다.

  * **예제 코드** (JUnit 5 + Mockito)

    ```java
    import static org.mockito.Mockito.*;

    class SpyTest {
        @Test
        void testNotifyUserWithSpy() {
            // 실제 동작하는 객체를 생성
            RealEmailClient realClient = new RealEmailClient();
            // Spy: 실제 객체를 spy()로 감싸서 행위를 기록
            EmailClient spyClient = spy(realClient);

            NotificationService service = new NotificationService(spyClient);
            service.notifyUser("test@example.com", "스파이 테스트");

            // 테스트 실행 후, spy를 통해 실제 객체의 메서드가 호출되었는지 검증
            verify(spyClient, times(1)).sendEmail(
                eq("test@example.com"),
                eq("알림 메시지입니다."),
                eq("스파이 테스트")
            );
            System.out.println("Spy 예제: 실제 객체의 메서드가 의도대로 호출되었는지 확인했습니다.");
        }
    }
    ```

  * **코드 설명**: `spy(realClient)`를 통해 실제 객체의 동작은 그대로 두면서 호출 정보만 감시하는 스파이를 만들었습니다. 테스트가 끝난 후, `verify()`를 사용해 `sendEmail` 메서드가 정확히 1번, 올바른 인자들로 호출되었는지 **사후에 확인**합니다.

-----

### 5\. Mock (목)

  * **핵심 개념**: 순수한 가짜 객체로, 테스트 시작 전 **기대하는 행위(시나리오)를 정의**하고 그대로 수행되었는지 검증합니다. 역시 **행위 검증**에 사용됩니다.

  * **시나리오**: `NotificationService`가 `sendEmail` 메서드를 정확한 인자들로 딱 한 번 호출하는지 검증합니다.

  * **예제 코드** (JUnit 5 + Mockito)

    ```java
    import static org.mockito.Mockito.*;

    class MockTest {
        @Test
        void testNotifyUserWithMock() {
            // Mock: 순수한 가짜 객체를 생성
            EmailClient mockClient = mock(EmailClient.class);

            NotificationService service = new NotificationService(mockClient);
            service.notifyUser("test@example.com", "목 테스트");

            // 테스트 후, Mock 객체의 특정 메서드가 우리가 기대했던 시나리오대로
            // 호출되었는지 '행위'를 검증
            verify(mockClient, times(1)).sendEmail(
                eq("test@example.com"),
                eq("알림 메시지입니다."),
                eq("목 테스트")
            );
            System.out.println("Mock 예제: 기대했던 시나리오대로 메서드가 정확히 호출되었습니다.");
        }
    }
    ```

  * **코드 설명**: `mock(EmailClient.class)`로 만들어진 `mockClient`는 속이 텅 빈 가짜 객체입니다. Spy와 코드상 검증 부분은 비슷하지만, Mock은 '실제 객체'가 아닌 '기대 행위 명세' 그 자체라는 점에서 개념적 차이가 있습니다. `verify()`는 \*\*테스트 전에 세운 기대(시나리오)\*\*가 충족되었는지 확인하는 과정입니다.
