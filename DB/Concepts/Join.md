Q. Inner Join과 Outer Join의 차이를 설명하고, 각각을 언제 사용하는지 예를 들어 설명해주세요.

**INNER JOIN과 OUTER JOIN은 두 개 이상의 테이블을 연결하여 데이터를 조회할 때 사용하는 SQL의 핵심 기능입니다.** 두 조인의 가장 큰 차이는 조인 조건에 만족하지 않는 데이터를 결과에 포함할지 여부입니다.

예를들어 팀 테이블이 있고, 멤버 테이블이 있다고 할때, 멤버가 외래키로 team Id를 갖고 있다고 하겠습니다.

이때 member에 team을 inner join하면 team id가 있는 모든 team은 테이블에 보이게 됩니다.

이때, 멤버가 teamId 1~6를 갖고 있고, 실제 teamId는 1~4까지 있다고 하면 teamId5와 6은 실제 팀 테이블에 없으므로 보이지 않게 됩니다.

한편 outer join은 한쪽에 끼워 맞추는 join 입니다. outer join 같은 경우는 팀 테이블에 team Id가 없다고 하여도, 실제 team의 1~6까지 보여주고, 없는 team 데이터는 null로 표기됩니다.

-
