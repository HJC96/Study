# 🎣 AWS SAA 낚시 포인트 정리 (실전용, 불필요한 내용 제외)

---

## 1️⃣ ALB vs 고정 IP

- ❌ ALB는 고정 IP 없음
- ❌ 방화벽 IP 화이트리스트와 충돌 가능
- ✅ 고정 IP 필요 → Global Accelerator 또는 NLB

---

## 2️⃣ SQS Standard vs FIFO

| 요구사항 | 선택 |
|----------|------|
| 순서 보장 | FIFO |
| 정확히 한 번 | FIFO |
| 일반 비동기 | Standard |
| 우선순위 | 큐 분리 |

👉 “순서”와 “우선순위”는 다름

---

## 3️⃣ SNS를 괜히 넣는 함정

- SNS는 Fan-out 전용
- 단일 소비자 비동기 처리에는 필요 없음

👉 SNS는 “여러 소비자 동시에”일 때만

---

## 4️⃣ Secrets Manager vs KMS

| 요구사항 | 선택 |
|----------|------|
| 자동 비밀번호 교체 | Secrets Manager |
| 암호화 키 관리 | KMS |

👉 Rotation 나오면 무조건 Secrets Manager

---

## 5️⃣ S3 Cross-Region Replication

- 소스 + 대상 버킷 모두 **버전 관리 필수**
- 버전 관리 언급 없으면 오답

---

## 6️⃣ Storage Gateway vs DataSync

| 상황 | 선택 |
|------|------|
| 주기적 백업 | DataSync |
| 하이브리드 파일 시스템 | Storage Gateway |

👉 “백업”이면 DataSync가 기본

---

## 7️⃣ ElastiCache vs Read Replica

| 문제 | 선택 |
|------|------|
| 동일 데이터 반복 조회 | ElastiCache |
| 읽기 부하 분산 | Read Replica |

👉 캐시는 DB 부하 자체 감소  
👉 레플리카는 부하 분산

---

## 8️⃣ 정적 웹사이트 + CloudFront

공식:

CloudFront → Private S3 (OAI)

- 퍼블릭 S3 필요 없음
- EC2 쓰면 과설계

---

## 9️⃣ Serverless 데이터 분석

- CSV + SQL + 저비용 → S3 + Athena
- Glacier는 분석용 아님 (아카이브용)

---

## 🔟 관리형 서비스 vs 직접 구성

시험 기본 원칙:

- “가장 비용 효율적”
- “최소 운영 부담”
- “관리형 서비스 우선”

EC2 + ASG + ALB 보이면:
→ 정적 사이트면 과설계일 가능성 높음

---

## 1️⃣1️⃣ Route 53 오해

- Route 53은 DNS
- 요청을 Lambda로 직접 전달하지 않음

---

## 1️⃣2️⃣ 고가용성 기본 전제

다음은 기본적으로 멀티 AZ:

- API Gateway
- Lambda
- DynamoDB
- S3

EC2 단독은 HA 아님

---

## 1️⃣3️⃣ Glacier 오해

- 장기 보관용
- 즉시 SQL 분석용 아님

---

## 1️⃣4️⃣ “자동”이라는 단어

문제에 자동/지속적/회전 나오면:

- 자동 확장 → 서버리스
- 자동 교체 → Secrets Manager
- 자동 복제 → 관리형 복제 기능

---

## 1️⃣5️⃣ 과설계는 거의 오답

불필요한 구성 요소가 추가되면:
→ 대부분 함정

예:
- SNS 불필요한 경우
- EC2 불필요한 경우
- Direct Connect 소규모 데이터 전송

---

# 📌 시험 사고 공식

1. 관리형인가?
2. 서버리스 가능한가?
3. 더 단순한 방법은 없는가?
4. 요구사항의 “핵심 키워드”는 무엇인가?

---

# 🚫 자주 틀리는 이유

- 기능이 아니라 “서비스 이름”에 반응함
- 키워드 해석을 놓침
- 과설계를 선택함

---

# 🎯 기억할 핵심 6개

- 순서 → FIFO
- 회전 → Secrets Manager
- 반복 조회 → Cache
- 하이브리드 → Storage Gateway
- 분석 → S3 + Athena
- 고정 IP → Global Accelerator
