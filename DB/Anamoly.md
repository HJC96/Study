# 데이터 이상 현상
- 데이터의 정확성 100%를 보장하기 힘들다.
- 예시
	- 테이블에 1학년 정보 1000개 있다고 했을때
	- 1힉년, 1핵년 등으로 들어간 잘못된 정보가 있을 수 있다.
- 결론은 ?
	- 테이블 분리가 필요하다.

# Anomaly란?
- 데이터 모델링에서 제일 중요한 **무결성**을 보장하는 것
- **중복**때문에 anomaly가 발생할 수 있다.
	- Update Anomaly
		- <img width="585" alt="image" src="https://github.com/HJC96/Study/assets/87226129/0a2d2670-78c0-4e99-b76d-a2a48d3748ff">

		- 홍길동을 2루수로 바꿀 때
			- 일부 홍길동이 바뀌지 않는 현상이 있을 수 있다.
			- 정규화에서는 홍길동이 중복으로 나타날 수 없다.
	- Delete Anomaly
		- <img width="593" alt="image" src="https://github.com/HJC96/Study/assets/87226129/1cd57ba2-3c54-4fcf-9994-7d8b0ce775c2">

		- 리플링 이펙트
	- Insert Anomaly
		- <img width="588" alt="image" src="https://github.com/HJC96/Study/assets/87226129/16e3de16-203e-45ec-a57b-1558aeadf997">

		- 장길동이라는 새로운 선수를 등록하려 할 때
			- 팀이 결정되지 않았다면(null) 입력할 수 없는 문제가 발생
				- PK(#팀번호)가 null 일수는 없기 때문에
			- 다른 데이터가 존재하지 않아 원하는 데이터를 입력할 수 없는 것이 삽입 이상 현상
			- 팀이 없다고 야구선수로 등록할 수 없다는 것은 비논리적인 것.
### 최종 목표
<img width="717" alt="image" src="https://github.com/HJC96/Study/assets/87226129/3131891f-44e9-4c44-b9c9-6c345734cae2">



# 테이블 설계 과정
<img width="716" alt="image" src="https://github.com/HJC96/Study/assets/87226129/9fe1a56f-1de9-4eb2-b1e6-a049ab335ff1">

위와 같은 (중복이 있는) 하나의 테이블을 받았을때
1. 조인된 테이블이라 생각한다.


# 정규화 맛보기

1. 쇼핑 리스트 테이블
<img width="679" alt="image" src="https://github.com/HJC96/Study/assets/87226129/cbce8314-1a92-4bc6-a63d-123201cffa57">

2. 이를 하기와 같이 바꿀 수 있다
	1. 다가속성 제거
<img width="677" alt="image" src="https://github.com/HJC96/Study/assets/87226129/1a4149d3-452c-4411-9ebe-a2b24a170a2f">

3. 또한 하기와 같이 바꿀 수 있다.  <- 1 정규화
	1. 복합속성 제거
<img width="680" alt="image" src="https://github.com/HJC96/Study/assets/87226129/4af7dc36-caea-4239-aef4-dc1091d15dde">

4. 중복이 되는 부분을 테이블로 따로 만들어 준다.
<img width="675" alt="image" src="https://github.com/HJC96/Study/assets/87226129/c66fe85b-c3e7-4627-a13d-df92363b0a15">

5. 주문번호를 떼어낸다
<img width="675" alt="image" src="https://github.com/HJC96/Study/assets/87226129/b924e48c-a4c9-4c7a-a5d7-e1dbe4ea7349">

6. 두 개 이상으로 구성된 PK에서 식별자 일부에 종속되는 속성은 제거 <- 제 2 정규화
	1. 상품명은 상품코드에만 종속되고 주문 번호와는 관계 없음
	2. 현재 테이블은 배, 사과에서 anomaly 발생
<img width="667" alt="image" src="https://github.com/HJC96/Study/assets/87226129/b6808968-225a-48fb-8804-ea9aa234dfc4">

7. 다음과 같이 변환
	1. <img width="644" alt="image" src="https://github.com/HJC96/Study/assets/87226129/6d9b1d67-d32b-4938-ba0f-98a8d29b93f2">
8. 식별자 이외의 속성간에 종속관계가 존재하면 안된다. <- 제 3 정규화
	1. <img width="642" alt="image" src="https://github.com/HJC96/Study/assets/87226129/e7b1955c-671c-431f-9745-013278e80dbf">

9. 고객 테이블을 새로 생성하여, 테이블간 관계 성립.
	1. <img width="645" alt="image" src="https://github.com/HJC96/Study/assets/87226129/cd210ea7-7e5c-4bc5-84a6-a43193cb5476">


### 최종 결과
<img width="707" alt="image" src="https://github.com/HJC96/Study/assets/87226129/57913b6b-125e-49bf-9e48-f14f4c103efb">

다가속성
- 하나의 엔티티에 대해 여러 값을 가지는 속성
	- 예를들어, 한 사람이 여러 전화번호를 갖고 있을때
- 행이 늘어남.

복합속성
- 여러개의 속성이 결합하여 하나의 속성을 형성
	- 예를들어, '이름'이라는 복합송성은 '성'과 '이름'으로 나눌 수 있다
- 열이 늘어남

# 출처
- 강의
	- RDBMS Modeling 기초
- 링크
	- https://www.inflearn.com/course/%EA%B4%80%EA%B3%84%ED%98%95%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4-rdbms/dashboard
