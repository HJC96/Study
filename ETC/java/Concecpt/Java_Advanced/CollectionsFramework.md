# 컬렉션 프레임웍

### 컬렉션 프레임웍
- 개념
  - 데이터 그룹을 저장하는 클래스들을 표준화한 설계
- 효과
  - 컬렉션, 다수의 데이터 등을 다루는데 필요한 다양하고 풍부한 클래스를 제공하여, 개발자의 짐을 덜어준다.

### 핵심 인터페이스
- List
  - ArrayList, LinkedList, Stack, Queue, Vector(사용하지 말자 - 오래됨) 등  
- Set
  - HashSet, TreeSet
- Map
  - HashMap(사용하지 말자 - 오래됨), TreeMap, Hashtable, Properties

### 인터페이스간 상속 계층도
<img width="358" alt="image" src="https://user-images.githubusercontent.com/87226129/232105869-f5dfe359-977a-43e6-9205-f51daa8aafac.png">

- List가 Collections를 상속받는 것을 볼 수 있는데, 이것의 의미는 다음 코드 한줄로 이해할 수 있다.
  - Collections.sort(list1); // list1은 ArrayList의 객체

