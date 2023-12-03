## 스트림
스트림은 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 정의해 놓았다.

기존에는 예를들어 List를 정렬할때는 Collection.sort()를 사용하고, 배열을 정리할 때는 Arrays.sort()를 사용해야 했다.

예제 코드
~~~java

String[] strArr = {"aaa", "bbb", "ccc"};
List<String> strList = Arrays.asList(strArr);
//위의 두 소스를 기반으로 하는 스트림은 다음과 같다.
Stream<String> strStream1 = strList.stream();
Stream<String> strStream2 = Arrays.stream(strArr);

//다음과 같이 정렬할 수 있다
strStream1.sorted().forEach(System.out::println);
strStream2.sorted().forEach(System.out::println);

~~~
  
    
