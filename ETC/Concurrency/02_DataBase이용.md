Mysql을 활용한 다양한 방법
1. Pessimistic Lock
- 실제로 데이터에 Lock을 걸어서 정합성을 맞추는 방법.
- exclusive lock을 걸게되면 다른 트랜잭션에서는 lock이 해제되기 전에 데이터를 가져갈 수 없음
- 데드락이 걸릴 수 있기 때문에 주의해서 사용해야 한다.

2. Optimistic Lock
- 실제로 Lock을 이용하지 않고 버전을 이용함으로써 정합성을 맞추는 방법.
- 먼저 데이터를 읽은 후에 update를 수행할 때 현재 내가 읽은 버전이 맞는지 확인하며 업데이트
- 내가 읽은 버전에서 수정사항이 생겼을 경우에는 application에서 다시 읽은 후에 작업을 수행

3. Named Lock
- 이름을 가진 metadata locking
- 이름을 가진 lock을 획득한 후 해제할때까지 다른 세션은 이 lock을 획득할 수 없음
- 주의할 점으로는 transaction이 종료될 때 lock이 자동으로 해제되지 않음
- 별도의 명령어로 해제를 수행해주거나 선점시간이 끝나야 해제됨

