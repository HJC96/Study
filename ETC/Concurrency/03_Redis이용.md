Lettuce
- setnx 명령어를 활용하여 분산락 구현
- spin lock 방식
- 구현이 간단하다.
- spring data redis를 이용하면 lettuce가 기본이기 때문에 별도의 라이브러리를 사용하지 않아도 된다.
- spin lock 방식이기 때문에 동시에 많은 스레드가 lock 획득 대기 상태라면 redis에 부하가 갈 수 있다.

Redission
- pub-sub 기반으로 Lock 구현 제공
- 락 획득 재시도를 기본으로 제공
- 별도의 라이브러리를 사용해야 함
- lock을 라이브러리 차원에서 제공해주기 때문에 사용법을 공부해야 함

실무에서는?
- 재시도가 필요하지 않은 lock은 letuce 활용
- 재시도가 필요한 경우는 redisson 활용
