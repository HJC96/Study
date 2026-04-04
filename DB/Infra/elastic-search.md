## Spring Boot에서 Elasticsearch 연동해보기
기존에는 DB의 LIKE 쿼리로 버티고 있었는데, Elasticsearch라는 검색 엔진을 사용하면 훨씬 강력한 검색이 가능하다는 것을 알게 되어서 정리해봅니다.

### 1. **Elasticsearch가 뭐길래? 왜 써야하나?**

Elasticsearch는 역색인(Inverted Index) 구조를 사용하는 검색 엔진인데, 대용량 데이터에서도 미친듯이 빠른 검색이 가능합니다.

→ 일반 DB의 LIKE 연산자로는 답이 안 나오는 성능 이슈 해결  
→ 형태소 분석, 자동완성, 오타 보정 등 정교한 검색 기능 제공  
→ 여러 노드에 데이터 분산 저장으로 확장성 굿  
→ 어렴풋이 알고 있었지만, 실제로 역색인이 어떻게 동작하는지 이제야 이해하게 되었다.

### 2. **Spring Boot와 연동하기: 생각보다 간단함**

Spring Boot에서는 의존성 하나만 추가하면 바로 사용 가능합니다.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```

application.yml에 서버 주소만 설정해주면 기본 연동 끝.

```yaml
spring:
  elasticsearch:
    uris: http://localhost:9200
```

### 3. **ElasticsearchRepository: JPA처럼 쓸 수 있다니**

JPA의 `JpaRepository`처럼, Elasticsearch도 `ElasticsearchRepository` 인터페이스를 제공합니다.

```java
public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByName(String name);
    // 메서드 이름만으로 쿼리 자동 생성!
}
```

→ **findByName 같은 메서드 정의하면, Spring Data가 알아서 검색 쿼리 만들어줌**  
→ JPA 쓰던 사람이면 바로 적응 가능  
→ 복잡한 검색 로직 직접 구현할 필요 없음

*@Query 어노테이션도 쓸 수 있나?  
→ 당연히 가능. 복잡한 쿼리는 @Query로 직접 작성 가능  
→ QueryDSL처럼 Criteria API도 제공해서 동적 쿼리도 처리 가능

### 4. **실무에서는 어떻게 쓰는가!?**

**개발 환경** → Docker로 Elasticsearch 띄워서 로컬 테스트  
**운영 환경** → AWS OpenSearch나 Elastic Cloud 같은 관리형 서비스 사용하는 경우가 많음. 직접 클러스터 관리하기엔 러닝커브가...

**주의사항**  
→ Elasticsearch 버전과 Spring Data Elasticsearch 버전 호환성 꼭 확인 필요  
→ 대용량 데이터 인덱싱할 때는 벌크 API 사용하지 않으면 성능 망함  
→ 한글 검색 제대로 하려면 nori 형태소 분석기 플러그인 필수
