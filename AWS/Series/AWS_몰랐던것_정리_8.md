AWS SAA 개념 정리

1. Fargate vs Elastic Beanstalk

AWS Fargate
	•	컨테이너 실행 서비스
	•	Docker 이미지 기반
	•	ECS 또는 EKS와 함께 사용
	•	서버 관리 없음 (Serverless container)

사용 흐름
개발자 → Docker 이미지 생성 → ECS/EKS → Fargate 실행

사용 사례
	•	마이크로서비스
	•	컨테이너 기반 아키텍처
	•	서버 관리 최소화

⸻

Elastic Beanstalk
	•	애플리케이션 배포 플랫폼 (PaaS)
	•	코드만 업로드하면 실행 환경 자동 구성
	•	내부적으로 EC2, Auto Scaling, Load Balancer 생성

사용 흐름
개발자 → 코드 업로드 → Beanstalk → EC2 환경 생성

사용 사례
	•	웹 애플리케이션
	•	빠른 배포
	•	인프라 관리 최소화

⸻

핵심 차이

항목	Fargate	Beanstalk
배포 대상	Docker 이미지	애플리케이션 코드
서비스 유형	Serverless Container	PaaS
사용 환경	ECS / EKS	웹 애플리케이션
인프라 관리	없음	거의 없음


⸻

2. S3 + CloudFront + WAF + OAI

기본 구조

사용자
→ CloudFront
→ AWS WAF 검사
→ S3 (Origin)

⸻

Origin 의미

CloudFront가 파일을 가져오는 원본 서버

예
	•	S3
	•	EC2
	•	ALB

즉

S3 = Origin

⸻

문제

S3가 public이면 사용자가 S3에 직접 접근 가능

사용자
→ S3

그러면 WAF를 우회하게 됨

⸻

해결 방법: OAI

OAI (Origin Access Identity)

CloudFront가 S3에 접근할 때 사용하는 특별한 IAM identity

구성
	1.	S3 public access 차단
	2.	S3 버킷 정책에서 OAI만 허용
	3.	CloudFront가 OAI로 S3 접근

⸻

최종 구조

사용자
→ CloudFront
→ WAF 검사
→ CloudFront
→ S3 (OAI 접근)

사용자 → S3 직접 접근
→ 차단

⸻

시험 공식

S3 정적 웹사이트 + 보안
→ CloudFront + OAI + WAF

⸻

3. VPC Endpoint (EC2 ↔ S3 private 연결)

기본 연결

EC2
→ Internet Gateway
→ S3

문제
인터넷 경로 사용

⸻

해결 방법

VPC Endpoint (Gateway Endpoint)

구조

EC2
→ VPC Endpoint
→ S3

특징
	•	AWS 내부 네트워크
	•	인터넷 사용 없음
	•	보안 강화

⸻

시험 공식

EC2 → S3 private access
→ VPC Endpoint

⸻

4. SQS 기반 Auto Scaling

문제 상황

Producer (빠름)
Consumer (느림)

직접 연결하면 요청 유실 가능

⸻

해결 구조

Producer
→ SQS
→ Consumer

즉 비동기 처리

⸻

Auto Scaling 기준

잘못된 방식
	•	메시지 알림 기반

올바른 방식
	•	Backlog per instance

계산

큐 메시지 수 ÷ 인스턴스 수

예

1000 메시지 / 10 서버
= 서버당 100 작업

이 값을 기준으로 Auto Scaling

⸻

시험 공식

Producer / Consumer 속도 차이
→ SQS

SQS Auto Scaling
→ backlog per instance

⸻

5. DynamoDB 복구 (PITR)

요구사항

RPO
= 데이터 손실 허용 범위

RTO
= 복구 시간

⸻

DynamoDB Point-in-Time Recovery

특징
	•	최대 35일 데이터 보존
	•	특정 시점 복구 가능
	•	약 5분 단위 복구

예

현재 → 5분 전 → 10분 전 → 15분 전

⸻

시험 공식

DynamoDB 복구
→ Point-in-Time Recovery (PITR)

⸻

6. 이미지 업로드 아키텍처

기존 구조

User
→ Web Server
→ 이미지 처리
→ S3 저장

문제
	•	웹 서버 부하
	•	느린 업로드
	•	강한 결합

⸻

개선 구조

User
→ Web Server
→ S3 업로드

S3
→ 이벤트 발생
→ Lambda
→ 이미지 리사이즈
→ S3 저장

⸻

실제 사용 구조

original/
image.jpg

resized/
image_small.jpg

⸻

시험 공식

이미지 업로드 + 처리
→ S3 + Lambda 이벤트

⸻

7. CloudFront 사용 상황

CloudFront = CDN

사용 조건
	•	글로벌 사용자
	•	정적 콘텐츠
	•	수백만 요청
	•	낮은 지연시간

⸻

구조

User
→ CloudFront Edge
→ (cache miss)
→ S3 Origin

⸻

시험 공식

정적 콘텐츠 + 글로벌 트래픽
→ CloudFront

⸻

핵심 시험 패턴 정리

상황	서비스
S3 private access	VPC Endpoint
정적 사이트 CDN	CloudFront
CloudFront 보안	OAI + WAF
컨테이너 실행	Fargate
웹 앱 배포	Beanstalk
비동기 처리	SQS
DynamoDB 복구	PITR
이미지 처리	S3 + Lambda
