# 📚 AWS 문제 풀이하면서 내가 몰랐던 것 정리

## 1️⃣ ALB와 고정 IP 관련

- ALB는 **고정 IP를 가질 수 없다**
- 이유:
  - 자동 확장 시 노드 수가 변함
  - IP가 동적으로 변경됨
- 온프레 방화벽이 IP 화이트리스트 기반일 경우 문제가 발생
- 해결책:
  - **Global Accelerator**
  - 또는 NLB (Elastic IP 가능)

---

## 2️⃣ SQS Standard vs FIFO 차이

| 구분 | Standard | FIFO |
|------|----------|------|
| 순서 보장 | ❌ | ✅ |
| 중복 가능성 | 있음 (at-least-once) | 없음 (exactly-once 지원) |
| 처리량 | 높음 | 상대적으로 낮음 |
| 비용 | 저렴 | 조금 더 비쌈 |

### 핵심 공식
- **순서 + 정확히 한 번** → FIFO
- **우선순위 분리** → 큐 2개 (Standard로 충분)

---

## 3️⃣ SNS는 언제 쓰는가?

- 여러 소비자가 **동시에 같은 이벤트를 받아야 할 때**
- Fan-out 패턴

사용 예:

SNS
├ Email
├ Analytics
└ Processing

필요 없을 때:
- 단순히 비동기 처리 분리만 필요할 때 (SQS만으로 충분)

---

## 4️⃣ Secrets Manager vs KMS

### Secrets Manager
- 자격 증명 저장
- 자동 Rotation 지원
- RDS 통합 가능
- IAM 접근 제어

### KMS
- 암호화 키 관리 서비스
- 비밀번호 저장/회전 서비스 아님

📌 “자동 교체” 요구가 있으면 → Secrets Manager

---

## 5️⃣ S3 Cross-Region Replication (CRR)

- **소스 + 대상 버킷 모두 버전 관리 활성화 필수**
- 버전 관리 없으면 복제 안 됨

---

## 6️⃣ Storage Gateway vs DataSync

| 구분 | Storage Gateway | DataSync |
|------|-----------------|----------|
| 목적 | 하이브리드 파일 시스템 | 데이터 전송/백업 |
| 사용 패턴 | 지속적 사용 | 주기적 동기화 |
| 비용 | 상대적으로 높음 | 전송량 기반 |

📌 “주기적 백업” → DataSync

---

## 7️⃣ ElastiCache vs Read Replica

- 같은 데이터 반복 조회 → **ElastiCache**
- 읽기 트래픽 분산 → Read Replica

📌 캐시는 DB 부하 자체를 줄임  
📌 Read Replica는 DB 부하를 나눔

---

## 8️⃣ S3 + Athena 조합

- CSV 파일
- 서버리스
- SQL 쿼리
- 비용 효율

→ **S3 저장 + Athena 쿼리**

Glacier는 아카이브용이지 분석용 아님.

---

## 9️⃣ 정적 웹사이트 + CloudFront 공식

CloudFront
↓
Private S3
(OAI로 접근 제한)

- 퍼블릭 S3 필요 없음
- EC2 불필요
- 가장 비용 효율적 + 탄력적

---

## 🔟 서버리스 고가용성 개념

관리형 서비스는 기본적으로:
- 멀티 AZ
- 자동 확장
- 인프라 관리 불필요

예:
- API Gateway
- Lambda
- DynamoDB

---

# 🎯 시험에서 느낀 핵심 패턴

- “가장 비용 효율적” → 서버리스 먼저 생각
- “순서 보장” → FIFO
- “자동 회전” → Secrets Manager
- “하이브리드” → Storage Gateway
- “반복 조회” → ElastiCache
- “정적 웹사이트” → S3 + CloudFront


