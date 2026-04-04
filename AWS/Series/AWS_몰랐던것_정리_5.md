## 1. AWS Organizations - Service Control Policy (SCP)

### 목적
여러 AWS 계정의 **서비스 사용 권한을 중앙에서 제한**.

### 특징
- AWS Organizations에서 사용
- **계정 또는 OU 단위 적용**
- IAM보다 **상위 레벨 정책**
- 허용이 아니라 **최대 권한 제한**

### 구조

Organization
├ Root OU (SCP 적용)
│   ├ Account A
│   ├ Account B
│   └ Account C

### 예시
EC2 사용 금지

```json
{
 "Effect": "Deny",
 "Action": "ec2:*",
 "Resource": "*"
}

⸻

2. CloudWatch Dashboard 공유

상황

AWS 계정이 없는 외부 사용자에게 CloudWatch Dashboard를 보여줘야 할 때.

해결

CloudWatch Dashboard Share 기능

특징
	•	AWS 계정 필요 없음
	•	읽기 전용
	•	URL 공유

구조

CloudWatch Dashboard
        │
   Share Link
        │
External User

⸻

3. EC2 구매 옵션

On-Demand
	•	기본 요금
	•	가장 비쌈
	•	중단 없음

사용
	•	예측 불가능한 트래픽
	•	중요한 서비스

⸻

Spot Instance

최대 90% 저렴

특징
	•	언제든지 중단 가능
	•	AWS 여유 용량 사용

사용
	•	stateless workload
	•	batch processing
	•	container workloads

⸻

4. Redis vs Memcached

Redis (ElastiCache)

특징
	•	Replication 지원
	•	Multi AZ 가능
	•	Automatic Failover
	•	Persistence 가능

구조

Primary Redis
     │
Replication
     │
Replica Redis (다른 AZ)

Primary 장애 발생

Replica → 자동 승격

사용
	•	고가용성 캐시
	•	세션 저장
	•	실시간 데이터

⸻

Memcached

특징
	•	매우 빠름
	•	단순 캐시
	•	Replication 없음
	•	Failover 없음

사용
	•	단순 캐시

⸻

Redis vs Memcached

기능	Redis	Memcached
Replication	O	X
Failover	O	X
Persistence	O	X
Multi AZ	O	X
성능	빠름	더 빠름

⸻

5. DB Read 감소 방법

캐시 아키텍처

Application
     │
  Cache
     │
Database

동작

Cache Hit

Application → Cache

Cache Miss

Application → Cache → DB

효과
	•	DB Read 감소
	•	성능 향상
	•	비용 절감

⸻

6. Instance Store vs EBS vs EFS

Instance Store

특징
	•	EC2에 직접 연결된 디스크
	•	매우 빠름
	•	휘발성

문제

EC2 종료 → 데이터 삭제

사용
	•	캐시
	•	임시 데이터
	•	버퍼

⸻

EBS

특징
	•	영구 블록 스토리지
	•	EC2 전용

구조

EC2
 │
EBS

사용
	•	OS 디스크
	•	데이터베이스

⸻

EFS

특징
	•	네트워크 파일 시스템
	•	여러 EC2 공유
	•	Multi AZ

구조

EC2 ─┐
EC2 ─┼─ EFS
EC2 ─┘

사용
	•	웹 콘텐츠
	•	공유 스토리지
	•	컨테이너 스토리지

⸻

7. S3 Glacier Deep Archive

목적

장기 보관

특징
	•	매우 저렴
	•	복원 필요
	•	즉시 접근 불가

사용
	•	백업
	•	아카이브
	•	규정 준수 데이터

⸻

8. 스토리지 서비스 비교

서비스	특징	사용
Instance Store	임시 스토리지	캐시
EBS	EC2 전용 블록 스토리지	OS / DB
EFS	공유 파일 스토리지	웹 서버
S3	오브젝트 스토리지	파일 저장
Glacier	장기 보관	백업


⸻
