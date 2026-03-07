## 1. Replication / Read Replica / Multi-AZ

### 1-1. Replication은 구조이고, 목적은 따로 있다
헷갈렸던 포인트:
- Master-Slave 구조면 무조건 고가용성인가?
- Replication이면 백업인가?
- 성능 향상과 고가용성은 같은 말인가?

정리:
- **Replication**은 그냥 **데이터를 복제하는 기술**
- 그 복제본을 **무슨 용도로 쓰느냐**에 따라 이름과 의미가 달라짐

| 구분 | 의미 | 목적 |
|---|---|---|
| Primary-Replica / Master-Slave | 복제 구조 자체 | 구조 설명 |
| Read Replica | 읽기용 복제본 | 성능 향상 |
| Standby / HA Replica | 대기 복제본 | 고가용성 |
| Snapshot / Backup | 특정 시점 백업 | 복구 |

---

### 1-2. 성능 향상과 고가용성은 다르다
- **성능 향상**: 더 빠르게 처리하기 위해 부하를 나눔
- **고가용성**: 장애가 나도 서비스가 계속 되게 함

즉:
- 성능 = **빠르게**
- 고가용성 = **안 죽게**

---

### 1-3. Read Replica와 Multi-AZ 차이
이건 시험에서도 정말 자주 헷갈림.

| 구분 | Read Replica | Multi-AZ |
|---|---|---|
| 목적 | 읽기 성능 향상 | 장애 대비 |
| 복제 방식 | 보통 비동기 | 동기 |
| 읽기 사용 | 가능 | 보통 불가(대기용) |
| 자동 Failover | 기본 목적 아님 | 가능 |
| 성능 향상 | 있음 | 없음 |

핵심:
- **Read Replica = 성능**
- **Multi-AZ = 고가용성**

---

### 1-4. Read Replica는 동기인가 비동기인가?
정리:
- **RDS Read Replica는 기본적으로 비동기 복제**
- 그래서 **복제 지연(replication lag)** 이 생길 수 있음
- 바로 쓰고 바로 Replica에서 읽으면 데이터가 아직 안 보일 수 있음

---

### 1-5. “그럼 마스터-슬레이브인데 그냥 단순 복제일 수도 있잖아?”
맞다. 그래서 구조와 목적을 분리해서 봐야 한다.

보통 이렇게 부른다:
- 읽기 분산용: **Read Replica**
- 장애 대비용: **Standby Replica / HA Replica / Hot Standby**
- 둘 다 가능한 경우: **Promotable Replica** 등으로 표현

즉:
- 마스터-슬레이브는 구조
- 리드 레플리카 / 스탠바이는 역할

---

### 1-6. Replication은 백업이 아니다
왜?
- Primary에서 실수로 삭제하면 Replica에도 그대로 복제됨
- 즉, 논리적 오류는 그대로 따라감

그래서 백업은 따로 필요:
- Snapshot
- Dump
- PITR(Point In Time Recovery)

---

## 2. VPC / Subnet / NAT / Internet Gateway

### 2-1. CIDR이 뭔지
예: `10.0.0.0/16`

뜻:
- 앞의 16비트가 고정
- 뒤의 16비트가 가변
- 총 `2^16 = 65536`개의 IP 사용 가능

즉:
- VPC 내부 사설 네트워크 범위를 정의하는 방식

---

### 2-2. Public Subnet vs Private Subnet
| 구분 | Public Subnet | Private Subnet |
|---|---|---|
| 외부에서 직접 접근 | 가능 | 불가 |
| 인터넷 나가기 | 가능 | 가능(NAT 필요) |
| 대표 용도 | ALB, Bastion, Public Web | App, DB |

핵심:
- Private이라고 해서 인터넷을 아예 못 쓰는 게 아님
- **외부에서 직접 못 들어올 뿐**, 내부에서 외부로 나가는 건 가능

---

### 2-3. “업데이트 하려면 외부에서도 들어와야 하는 거 아니야?”
이 부분이 많이 헷갈렸음.

정리:
- 업데이트는 **외부가 먼저 들어오는 게 아님**
- **내부 인스턴스가 먼저 요청**하고,
- 외부 서버가 **응답**하는 것

즉:
- 내부 → 외부 요청: 가능
- 외부 → 내부 응답: 가능
- 외부 → 내부 신규 요청: 불가

이게 NAT 동작 원리다.

---

### 2-4. NAT Gateway는 뭐고 왜 필요한가
NAT Gateway의 역할:
- Private Subnet 인스턴스가 인터넷으로 나갈 수 있게 해줌
- 하지만 외부에서 먼저 들어오는 건 막음

쉽게 말하면:
- 집 공유기 비슷한 개념

---

### 2-5. Internet Gateway와 NAT Gateway 차이
| 구분 | Internet Gateway | NAT Gateway |
|---|---|---|
| 연결 대상 | VPC 전체 | Private Subnet의 아웃바운드 |
| 역할 | 인터넷 출입구 | Private 인스턴스의 외부 통신 |
| 외부에서 직접 접근 | 가능 | 불가 |
| 위치 | VPC당 1개 | 보통 AZ마다 |

---

### 2-6. 왜 NAT Gateway를 AZ마다 둬야 하나?
NAT Gateway는 AZ 단위 리소스 느낌으로 봐야 함.

하나만 두면:
- NAT 있는 AZ 장애 시
- 다른 AZ의 Private 인스턴스도 인터넷 사용에 영향

그래서:
- **AZ마다 NAT Gateway 하나씩**
- **Private Route Table도 AZ별로**

이게 고가용성 설계

---

### 2-7. 라우트 테이블은 뭐냐
라우트 테이블은:
- “어떤 목적지의 트래픽을 어디로 보낼지” 정하는 규칙

예:
- `10.0.0.0/16` → 로컬(VPC 내부)
- `0.0.0.0/0` → IGW 또는 NAT

---

## 3. VPC 이동 / EC2 이동 / RDS 이동

### 3-1. 리소스를 VPC 간에 “옮긴다”는 표현
정확히는:
- **이동(move)** 이 아니라
- **새로 만들고 복원(recreate/restore)** 하는 것

왜?
- VPC는 네트워크 경계
- 리소스는 해당 VPC 네트워크에 종속됨

---

### 3-2. EC2는 어떻게 옮기나?
- 기존 EC2에서 **AMI 생성**
- 새 VPC에서 그 AMI로 **새 EC2 생성**

즉:
- 그대로 이동이 아니라 재생성

---

### 3-3. RDS는 어떻게 옮기나?
- 기존 RDS에서 **Snapshot 생성**
- 새 VPC + 새 DB Subnet Group 지정 후 **Restore**

즉:
- 이것도 “그대로 이동”이 아니라 새로 만드는 것

---

### 3-4. DB Subnet Group이 왜 필요한가
기본값으로 두면:
- RDS가 Public Subnet에 생성될 수도 있음

그래서:
- DB용 Subnet Group을 만들어
- **Private Subnet들만 지정**
- RDS가 거기서만 생성되게 해야 함

---

## 4. IAM / Access Key / Role / Instance Profile / Secrets

### 4-1. Console / CLI / SDK 차이
| 방식 | 용도 |
|---|---|
| Management Console | 웹 UI로 수동 관리 |
| CLI | 명령어/스크립트 자동화 |
| SDK | 애플리케이션 코드에서 AWS 제어 |

---

### 4-2. Console 로그인과 CLI 인증 방식 차이
- Console: ID/PW, MFA
- CLI/SDK: Access Key + Secret Access Key

---

### 4-3. Root Access Key를 왜 쓰면 안 되나
- Root는 계정 전체 권한
- 키 유출 시 피해가 매우 큼
- AWS 모범 사례: **Root Access Key 사용 금지**

---

### 4-4. `aws configure`가 왜 위험한가
`aws configure` 하면:
- `~/.aws/credentials`
- `~/.aws/config`

에 값이 저장됨

문제:
- 인스턴스가 털리면 키도 노출됨

즉:
- 인스턴스나 코드에 키 저장하는 건 위험

---

### 4-5. 그래서 Role을 쓰는 것
EC2가 S3에 접근하는 경우:
- 키 저장하지 말고
- **IAM Role**을 EC2에 붙여서 권한 부여

이러면:
- 키가 파일에 안 남음
- 더 안전함

---

### 4-6. Role과 Instance Profile 차이
헷갈렸던 포인트:
- EC2에 Role을 붙인다고 했는데, 왜 Instance Profile이라는 말도 나옴?

정리:
- **Role**: 권한 정의
- **Instance Profile**: 그 Role을 EC2에 붙여주는 그릇

EC2 use case로 Role 만들면 보통 같이 생성되어 헷갈린다.

---

### 4-7. Service Role vs Instance Profile
특히 Elastic Beanstalk에서 헷갈림.

- **Service Role**: Beanstalk 서비스 자체가 쓰는 권한
- **Instance Profile**: Beanstalk이 만든 EC2 인스턴스가 쓰는 권한

즉:
- 서비스가 쓰는 Role
- 인스턴스가 쓰는 Role
이 따로 있을 수 있음

---

### 4-8. Secrets Manager는 왜 쓰는가
문제:
- DB username/password 같은 민감 정보는 코드에 넣기 위험

해결:
- Secrets Manager에 저장
- 코드에서는 필요할 때 API로 가져옴

---

### 4-9. Rotation을 켜면 무슨 일이 일어나나
중요:
- Secret 값만 바뀌는 게 아니라
- **실제 DB 비밀번호도 같이 바뀜**

즉:
1. 새 비밀번호 생성
2. DB에 적용
3. Secrets Manager 값도 업데이트

---

### 4-10. “그럼 나중에 사람은 비밀번호를 모르는 거 아냐?”
맞다.
그게 정상적인 구조다.

- 사람이 아는 게 아니라
- 애플리케이션이 필요할 때 가져다 쓰는 구조

필요 시:
- Secrets Manager에서 조회 권한이 있는 사람만 확인 가능

---

### 4-11. `console.log(secret)`만 안 찍으면 되냐?
반만 맞음.

정확히는:
- 로그뿐 아니라
- 예외 메시지, 응답 바디, 디버그 출력, 모니터링 도구 등
**어디에도 노출되면 안 됨**

즉:
- “로그만 안 찍으면 된다”가 아니라
- “관찰 가능한 경로 어디에도 흘러나가면 안 된다”

---

## 5. CloudFormation

### 5-1. CloudFormation이 뭔가
- 인프라를 코드(YAML/JSON)로 정의
- 스택(Stack) 단위로 배포/삭제

장점:
- 자동화
- 재사용
- 이력 관리
- 일관된 배포

---

### 5-2. Stack이 뭔가
- 여러 리소스의 묶음
- CloudFormation은 스택 단위로 관리

즉:
- 스택 삭제 = 관련 리소스 한꺼번에 정리 가능

---

### 5-3. Template에서 가장 중요한 건 Resources
Resources는:
- 실제 생성할 리소스 정의
- CloudFormation에서 사실상 핵심

다른 섹션은 optional인 경우 많지만,
- **Resources는 사실상 필수**

---

### 5-4. Resources의 기본 구조
- **Logical ID**: 템플릿 내에서 리소스 식별 이름
- **Type**: AWS 리소스 타입
- **Properties**: 속성 값들

예:
- `AWS::EC2::Instance`
- `AWS::EC2::VPC`
- `AWS::EC2::InternetGateway`

---

### 5-5. `!Ref`가 뭐냐
- CloudFormation 내장 함수
- 다른 리소스를 참조할 때 사용

예:
- 위에서 만든 VPC를 아래 리소스에서 참조
- IGW를 Attachment에서 참조

즉:
- 리소스끼리 연결할 때 핵심

---

### 5-6. Template Structure에서 자주 헷갈린 것
| 섹션 | 의미 |
|---|---|
| AWSTemplateFormatVersion | 형식 버전 |
| Description | 설명 |
| Metadata | 메타 정보 |
| Parameters | 사용자 입력값 |
| Rules | 입력값 검증 |
| Mappings | 리전별/환경별 값 매핑 |
| Conditions | 조건 분기 |
| Resources | 실제 리소스 |
| Outputs | 배포 후 출력값 |

---

### 5-7. Mappings가 왜 필요한가
리전마다 값이 다른 경우 때문

대표 예:
- AMI ID

서울 리전 AMI ID와 도쿄 리전 AMI ID는 다름.
같은 템플릿을 여러 리전에 배포하려면
Mappings가 필요함.

---

### 5-8. Conditions가 왜 필요한가
테스트/운영 환경이 다를 때
템플릿을 2개 만들지 않고
하나의 템플릿에서 조건으로 분기 가능

---

### 5-9. CLI로 배포/삭제하는 이유
콘솔은 클릭이 많아 반복 작업에 불편

예:
```bash
aws cloudformation deploy --stack-name lucky-vpc --template-file vpc.yaml
aws cloudformation delete-stack --stack-name lucky-vpc
```

즉:
- 더 빠르고 자동화 가능

---

## 6. Elastic Beanstalk

### 6-1. Elastic Beanstalk이 뭔가
- 3-Tier 웹 아키텍처를 비교적 쉽게 배포해주는 서비스
- 내부적으로 여러 AWS 리소스를 자동 생성

쉽게 말해:
- 앱 중심 배포 서비스

---

### 6-2. Elastic Beanstalk이 실제로 만드는 것
Higher Availability 선택 시 자동으로:
- EC2
- Auto Scaling Group
- Load Balancer
- Target Group
- Security Group
등을 생성

---

### 6-3. 내부적으로 CloudFormation을 사용한다
중요 포인트:
- Beanstalk이 직접 마법처럼 만드는 게 아니라
- **CloudFormation 스택을 내부적으로 생성해서 인프라를 만듦**

즉:
- Elastic Beanstalk은 CloudFormation 위에서 동작하는 상위 서비스 느낌

---

## 7. 캐시 / ElastiCache / CloudFront

### 7-1. Read Replica로도 한계가 오면?
그 다음은 DB만 계속 늘리는 게 아니라
**DB 요청 자체를 줄여야 함**

그래서:
- 캐시 계층 도입

---

### 7-2. 캐시 종류 2가지 구분
#### CloudFront
- 네트워크 캐시
- 사용자 가까운 곳에서 콘텐츠 제공
- 정적 콘텐츠, 지연 감소

#### ElastiCache
- 애플리케이션과 RDS 사이의 캐시
- 인메모리 DB(Redis/Memcached)
- DB 부하 감소

---

### 7-3. 왜 인메모리 캐시가 빠른가
속도 순서:
- CPU > RAM > SSD > HDD > Network

ElastiCache는 RAM 기반
RDS는 보통 디스크 기반
그래서 캐시가 훨씬 빠름

---

### 7-4. 캐시 히트율이 왜 중요하냐
캐시에 저장한 데이터를 얼마나 자주 다시 쓰느냐가 핵심

- 히트율 높음 → 성능 좋아짐, DB 부하 감소
- 히트율 낮음 → 비용만 들고 효과 없음

즉:
- 캐시는 무조건 좋은 게 아니라
- **반복 조회 데이터가 많을 때 효과적**

---

### 7-5. 캐시는 코드 수정이 필요한 경우가 많다
중간에 캐시를 도입하면 보통 앱 로직도 바뀜

예:
1. 캐시에 먼저 조회
2. 없으면 DB 조회
3. 결과를 캐시에 저장

즉:
- 인프라만 바꾸는 게 아니라 애플리케이션도 바뀔 수 있음

---

## 8. Systems Manager (SSM)

### 8-1. Systems Manager는 언제 쓰나
- 배포 후 운영 단계에서 인스턴스/설정/애플리케이션을 관리할 때 사용
- CloudFormation은 초기 배포에는 좋지만,
  운영 중 인스턴스 관리에는 불편할 수 있음

---

### 8-2. Systems Manager를 쓰려면 필요한 것
1. EC2에 적절한 IAM Role
2. SSM Agent 설치

Amazon Linux는 보통 SSM Agent 기본 포함

---

### 8-3. Run Command가 뭔가
- 여러 인스턴스에 동시에 명령 실행 가능
- 태그, 수동 선택, 리소스 그룹 등으로 대상 지정 가능

즉:
- SSH 안 들어가도 중앙에서 명령 실행 가능

---

### 8-4. Document가 뭔가
Systems Manager의 핵심 개념

- Document = SSM Agent에 전달되는 명령/작업 정의 문서
- Run Command도 Document를 기반으로 동작

예:
- `AWS-RunShellScript`

즉:
- 명령 실행 규칙/파라미터/스크립트 형식을 담은 문서

---

### 8-5. 실무 포인트
- 직접 문서를 만들 수도 있음
- 하지만 웬만하면 AWS가 제공하는 문서를 재사용하는 것이 편함

---

## 9. 지금까지의 사고 전환 포인트

### 9-1. 구조와 목적을 분리해서 보기
예:
- Master-Slave = 구조
- Read Replica / HA = 목적

---

### 9-2. “이동”이 아니라 “재생성”으로 보기
예:
- VPC 이동
- RDS 이동
- EC2 이동

AWS는 대체로 복사/재배치 개념이 많다.

---

### 9-3. 성능 문제를 무조건 스펙 업으로 해결하지 않기
순서:
1. Scale Up
2. Read Replica
3. Cache
4. 필요 시 구조 변경

---

### 9-4. 보안은 “비밀을 안 넣는 것” + “흘러가지 않게 하는 것”
- 코드에 안 넣기
- 로그에 안 남기기
- 예외/응답/APM에도 안 남기기
- 최소 권한 원칙
- Role 사용
- MFA 사용

---

## 10. 시험/실무에서 특히 자주 헷갈리는 한 줄 정리

- **Read Replica = 읽기 성능**
- **Multi-AZ = 장애 대비**
- **Private Subnet = 외부에서 직접 못 들어옴, 하지만 밖으로는 나갈 수 있음**
- **NAT = 내부가 먼저 요청한 응답만 허용**
- **VPC 간 이동 = 이동이 아니라 복원/재생성**
- **Role = 권한, Instance Profile = EC2에 붙이는 그릇**
- **Secrets Manager Rotation = 실제 DB 비밀번호도 바뀜**
- **CloudFormation = 인프라를 코드로 정의**
- **Elastic Beanstalk = CloudFormation을 감춘 앱 배포 서비스**
- **ElastiCache = DB 앞단 캐시**
- **Systems Manager = 배포 후 운영 관리**

---
