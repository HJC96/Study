# 1️⃣ Security Group vs Network ACL

| 항목 | Security Group | Network ACL |
|-----|---------------|-------------|
| 상태 | Stateful | Stateless |
| 규칙 | Allow만 가능 | Allow + Deny |
| 적용 대상 | 인스턴스 | 서브넷 |
| 응답 트래픽 | 자동 허용 | 별도 규칙 필요 |

### 핵심
- **SG는 stateful → 인바운드만 열면 응답 자동**
- **NACL은 stateless → 인바운드 + 아웃바운드 둘 다 필요**

---

# 2️⃣ Route 53 라우팅 정책

| 정책 | 사용 상황 |
|-----|-----------|
| Simple | 단일 리소스 |
| Weighted | 트래픽 비율 분배 |
| Latency | 가장 가까운 리전 |
| Geolocation | 사용자 위치 |
| Failover | 장애 대비 |
| Multivalue | 여러 정상 IP 반환 |

### 헷갈린 포인트
- **Multivalue = 여러 정상 인스턴스 IP 반환**

---

# 3️⃣ RDS vs Aurora

| 항목 | RDS | Aurora |
|-----|-----|--------|
| 관리형 | O | O |
| 스토리지 자동 확장 | 설정 필요 | 자동 |
| 읽기 확장 | Read Replica | Aurora Replica |
| Auto Scaling | 설정 필요 | 가능 |

### 핵심
- RDS는 관리형이지만 **자동 확장은 설정해야 함**

---

# 4️⃣ ElastiCache vs Read Replica

| 상황 | 선택 |
|------|------|
| 동일 데이터 반복 조회 | ElastiCache |
| 읽기 트래픽 분산 | Read Replica |

### 핵심
- 캐시 사용 시 **약간의 stale 데이터 허용**

---

# 5️⃣ S3 암호화

| 방식 | 특징 |
|------|------|
| SSE-S3 | AWS 관리 키 |
| SSE-KMS | KMS 사용 |
| Client-side | 애플리케이션에서 암호화 |

### 시험 포인트
보안 정책 요구:
- 키 회전
- 권한 분리
- 감사

→ **Customer Managed CMK + SSE-KMS**

---

# 6️⃣ S3 Versioning + Lifecycle

문제 상황:
- Versioning 활성화
- Lifecycle로 Current 객체 삭제

### 문제
삭제 시 실제 삭제가 아니라

```
Delete Marker 생성
```

---

# 7️⃣ IPv6 네트워크

| 기능 | IPv4 | IPv6 |
|-----|-----|------|
| NAT | NAT Gateway | 없음 |
| Outbound-only | NAT | Egress-only IGW |

### 핵심
IPv6에서
```
Outbound only = Egress-only Internet Gateway
```

---

# 8️⃣ CloudFront + S3 보안

문제:
- S3 직접 접근 차단
- CloudFront 통해서만 접근

### 해결
```
CloudFront + OAI
```

---

# 9️⃣ S3 데이터 공유

다른 계정에 공유하면서 비용 최소화

→ **Requester Pays**

다운로드 비용:
```
요청자가 지불
```

---

# 🔟 AWS Transfer Family

온프레 SFTP 서버 대체

```
Users
  ↓
AWS Transfer for SFTP
  ↓
S3
```

---

# 11️⃣ Storage Gateway

| 유형 | 용도 |
|-----|------|
| File Gateway | NFS/SMB + S3 |
| Volume Gateway | iSCSI |
| Tape Gateway | 백업 |

### 시험 포인트
온프레 NFS + S3 → **File Gateway**

---

# 12️⃣ Container 서비스

| 서비스 | 특징 |
|------|------|
| ECS | AWS 전용 |
| EKS | Kubernetes |
| Fargate | 서버리스 컨테이너 |

### 시험 포인트
- 클라우드 독립 → **EKS**

---

# 13️⃣ Auto Scaling 정책

| 정책 | 특징 |
|------|------|
| Target Tracking | 목표 값 유지 |
| Step Scaling | 단계 확장 |
| Scheduled | 시간 기반 |

### 시험 포인트
CPU 40% 유지 → **Target Tracking**

---

# 14️⃣ Lambda vs EC2 vs Fargate

| 상황 | 선택 |
|------|------|
| 요청 패턴 예측 불가 | Lambda |
| 컨테이너 | Fargate |
| 장기 실행 서버 | EC2 |

---

# 15️⃣ Macie vs Inspector vs GuardDuty

| 서비스 | 용도 |
|------|------|
| Macie | S3 PII 탐지 |
| Inspector | EC2 취약점 검사 |
| GuardDuty | 위협 탐지 |

---

# 16️⃣ Global Accelerator

문제 상황:
- ALB IP가 계속 변경
- 방화벽 allow list 필요

### 해결
```
Global Accelerator
→ 고정 Anycast IP 2개
```

---

# 17️⃣ DynamoDB vs RDS

| 상황 | 선택 |
|------|------|
| Key-Value | DynamoDB |
| 관계형 DB | RDS |
| 예측 불가 트래픽 | DynamoDB |

---
