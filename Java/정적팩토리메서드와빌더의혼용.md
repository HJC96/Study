이런식으로 쓰면 된다...!!!
```java
import static java.util.Objects.requireNonNull;
import static org.springframework.util.Assert.state;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 💡 2. Lombok이 사용할 private 생성자
@Builder // 💡 1. 빌더 패턴 적용
public class Member {

    @NaturalId
    private Email email;
    private String nickname;
    private String passwordHash;
    private MemberStatus status;
    private MemberDetail detail;

    // 💡 3. 모든 검증 로직을 생성자에 위임!
    // build()가 호출되면 최종적으로 이 생성자가 실행됩니다.
    public static Member create(String email, String nickname, String rawPassword, PasswordEncoder passwordEncoder) {
        // 여기에서 검증 로직을 수행합니다.
        requireNonNull(email, "email must not be null");
        requireNonNull(nickname, "nickname must not be null");
        requireNonNull(rawPassword, "rawPassword must not be null");
        requireNonNull(passwordEncoder, "passwordEncoder must not be null");
        
        return Member.builder()
                .email(new Email(email))
                .nickname(nickname)
                .passwordHash(passwordEncoder.encode(rawPassword))
                .status(MemberStatus.PENDING)
                .detail(MemberDetail.create())
                .build();
    }
    
    // ... (기타 다른 메서드들은 동일)
}
```
