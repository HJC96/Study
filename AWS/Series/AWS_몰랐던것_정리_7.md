1. AWS Fargate

Fargate는 AWS에서 제공하는 서버리스 컨테이너 실행 서비스이다.
컨테이너를 실행할 때 보통은 EC2 서버를 준비하고 Docker를 설치해야 하지만, Fargate는 서버를 직접 관리하지 않고 컨테이너만 실행할 수 있게 해준다.

즉 사용자는 컨테이너 이미지만 제공하면 되고, 서버 관리와 스케일링은 AWS가 처리한다.

주로 다음 서비스와 함께 사용된다.
	•	ECS (Elastic Container Service)
	•	EKS (Elastic Kubernetes Service)

시험에서는 다음 키워드가 나오면 Fargate를 떠올리면 된다.
	•	컨테이너 실행
	•	서버 관리 없음
	•	운영 오버헤드 최소
	•	자동 스케일링

⸻

2. Serverless 개념

Serverless는 서버가 없다는 뜻이 아니라 서버를 직접 관리하지 않아도 되는 컴퓨팅 모델이다.

사용자는 코드나 컨테이너만 신경 쓰고 다음 요소들은 AWS가 관리한다.
	•	서버
	•	운영체제
	•	확장
	•	용량 관리

대표적인 Serverless 서비스
	•	Lambda
	•	Fargate
	•	DynamoDB
	•	API Gateway

즉 Fargate는 Serverless Container 서비스라고 이해하면 된다.

⸻

3. DynamoDB vs MongoDB

DynamoDB는 MongoDB가 아니다.
둘 다 NoSQL 데이터베이스이지만 구조가 다르다.

DynamoDB는 Key-Value 기반 데이터베이스이다.
데이터 조회는 보통 Primary Key 중심으로 이루어진다.

MongoDB는 Document 기반 데이터베이스이다.
JSON 문서를 저장하고 다양한 쿼리를 사용할 수 있다.

AWS에서 MongoDB와 비슷한 서비스는 Amazon DocumentDB이다.

시험에서 기억할 점
	•	DynamoDB → Key-value NoSQL
	•	DocumentDB → MongoDB 호환

⸻

4. Redis vs Memcached

둘 다 ElastiCache에서 사용하는 캐시 시스템이다.

Redis는 다음 특징을 가진다.
	•	replication 지원
	•	failover 가능
	•	persistence 가능
	•	Multi-AZ 지원

Memcached는 다음 특징을 가진다.
	•	매우 빠름
	•	단순 캐시
	•	replication 없음
	•	failover 없음

그래서 시험에서는 이렇게 기억하면 된다.

고가용성 캐시 → Redis
단순 캐시 → Memcached

⸻

5. S3 Object Lambda

S3 Object Lambda는 S3 객체를 읽을 때 데이터를 실시간으로 변환할 수 있는 기능이다.

예를 들어 S3에 원본 데이터가 있고, 요청 애플리케이션마다 다른 형태의 데이터가 필요할 때 사용한다.

예시

원본 데이터에는 PII가 포함되어 있다.
	•	App1 → 원본 데이터
	•	App2 → PII 제거 데이터
	•	App3 → PII 제거 데이터

이 경우 데이터를 여러 버킷에 저장할 필요 없이 Object Lambda가 요청을 가로채서 Lambda 함수로 데이터를 변환한 후 반환한다.

장점
	•	데이터 복제 없음
	•	운영 오버헤드 최소
	•	요청 시 실시간 변환

⸻

6. EFS vs S3

S3는 Object Storage이다.

즉 파일 시스템이 아니라 객체 저장소이다.
POSIX 파일 시스템 기능을 제공하지 않는다.

EFS는 POSIX 호환 파일 시스템이다.

특징
	•	EC2 여러 대가 동시에 접근 가능
	•	Multi-AZ
	•	Linux 파일 시스템

시험에서 다음 키워드가 나오면 EFS이다.
	•	POSIX
	•	shared filesystem
	•	EC2 간 공유 스토리지

⸻

7. VPC Peering

VPC Peering은 두 VPC를 private 네트워크로 연결하는 서비스이다.

특징
	•	인터넷을 거치지 않는다
	•	cross-account 가능
	•	AWS 내부 네트워크 사용
	•	높은 대역폭

시험에서는 다음 상황에서 사용한다.
	•	VPC ↔ VPC 연결
	•	다른 AWS 계정 VPC 연결

⸻

8. PrivateLink

PrivateLink는 VPC 전체 연결이 아니라 특정 서비스만 연결할 때 사용한다.

예

회사 VPC에서 외부 SaaS 서비스에 연결해야 할 때

이 경우 VPC Peering 대신 PrivateLink를 사용한다.

차이

VPC Peering → VPC 전체 연결
PrivateLink → 특정 서비스 연결

⸻

9. CloudWatch Metric Streams

CloudWatch Metric Streams는 CloudWatch 메트릭을 실시간으로 다른 서비스로 스트리밍하는 기능이다.

주로 다음 구조에서 사용된다.

CloudWatch Metric Streams → Kinesis Firehose → S3

장점
	•	서버리스
	•	실시간 스트리밍
	•	EC2 내부 에이전트 필요 없음

⸻

10. Kinesis Data Firehose

Firehose는 스트리밍 데이터를 자동으로 저장소로 전달하는 서비스이다.

주로 다음과 같이 사용된다.

로그 → Firehose → S3
스트림 데이터 → Firehose → Redshift

특징
	•	서버리스
	•	자동 버퍼링
	•	자동 저장

시험에서는 이렇게 기억하면 된다.

스트리밍 데이터를 S3로 보내야 한다 → Firehose

⸻

11. Spot 인스턴스 사용 가능 상황

Spot 인스턴스는 언제든지 종료될 수 있지만 다음 경우에는 안전하게 사용할 수 있다.
	•	Auto Scaling 사용
	•	Load Balancer 사용
	•	Stateless 서비스
	•	Web server
	•	Batch 작업

사용하면 안 되는 경우
	•	데이터베이스
	•	stateful 애플리케이션
	•	단일 서버 아키텍처

⸻
핵심 시험 정리

POSIX 공유 스토리지 → EFS
컨테이너 + 서버 관리 없음 → Fargate
Key-value NoSQL → DynamoDB
MongoDB 호환 → DocumentDB
고가용성 캐시 → Redis
실시간 스트림 데이터를 S3 저장 → Firehose
VPC ↔ VPC 연결 → VPC Peering
특정 서비스만 private 연결 → PrivateLink
⸻
