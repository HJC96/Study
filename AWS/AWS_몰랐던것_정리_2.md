1. Replication은 “구조”이지 “목적”이 아니다

❌ 흔한 오해
	•	Master–Slave = 고가용성
	•	Replication = 백업

✅ 실제 개념

개념	의미
Primary–Replica	토폴로지(구조)
Read Replica	읽기 확장 (성능)
Standby / HA Replica	장애 대비
Snapshot	백업

Replication은 기술이고, 목적은 따로 정의해야 한다.

⸻

2. VPC는 리소스를 “이동”할 수 없다

❌ 오해

EC2나 RDS를 VPC 간에 이동할 수 있다?

✅ 현실

VPC는 네트워크 경계이므로 이동 개념이 없다.
새로 생성하고 복구하는 방식이다.

리소스	실제 이동 방법
EC2	AMI 생성 → 새 VPC에서 생성
RDS	Snapshot → Restore
ALB	새로 생성
Security Group	재설정

AWS에서 VPC 이동은 “이사”가 아니라 “복제 후 재배치”다.

⸻

3. Private Subnet도 인터넷을 사용할 수 있다

❌ 오해

Private Subnet = 인터넷 불가

✅ 정확한 개념
	•	외부에서 직접 접근 ❌
	•	내부에서 외부로 요청 가능 ⭕

NAT 동작 원리

트래픽 방향	가능 여부
내부 → 외부 요청	✅
외부 → 내부 응답	✅
외부 → 내부 신규 요청	❌

업데이트는 “외부가 들어오는 것”이 아니라
“내부가 요청하고 외부가 응답하는 것”이다.

⸻

4. Internet Gateway vs NAT Gateway

구분	Internet Gateway	NAT Gateway
연결 수	VPC당 1개	AZ마다 필요
목적	VPC ↔ 인터넷 연결	Private → 인터넷
Public IP 필요	인스턴스 필요	NAT만 필요
외부에서 직접 접근	가능	불가

집 공유기는 IGW + NAT 역할을 합쳐놓은 것과 같다.

⸻

5. CIDR은 네트워크 설계의 시작점

예: 10.0.0.0/16
	•	앞 16비트 고정
	•	2^16 = 65,536 IP
	•	VPC 내부 사설 IP 범위

중요한 점
	•	서브넷은 CIDR을 쪼개는 것
	•	서브넷끼리 겹치면 안 됨

⸻

6. 고가용성은 “리소스 개수”가 아니라 “구성 방식”

❌ 오해

리소스 2개면 HA다?

✅ HA를 위한 조건
	•	서로 다른 AZ
	•	헬스 체크
	•	자동 Failover
	•	올바른 라우팅

예:
	•	NAT Gateway 1개 → Single Point of Failure
	•	AZ마다 NAT 필요

HA는 개수 문제가 아니라 구조 문제다.

⸻

7. Route 53은 단순 DNS가 아니다

기능:
	•	A 레코드
	•	ALB 연결
	•	헬스 체크
	•	가중치 기반
	•	지연 기반

DNS가 트래픽 제어 수단이 될 수 있다.

⸻

8. Security Group은 Stateful이다
	•	들어온 요청의 응답은 자동 허용
	•	Outbound 기본 허용

NACL과의 차이

구분	Security Group	NACL
상태	Stateful	Stateless
적용 대상	인스턴스	서브넷
