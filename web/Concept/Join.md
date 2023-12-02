# JOIN

## JOIN 개념

데이터베이스에서 "Join"은 두 개 이상의 테이블에서 행을 결합하여 단일 결과 집합을 생성하는 방법을 의미합니다. SQL(Structured Query Language)에서 JOIN 연산은 주로 관계형 데이터베이스의 테이블 간 관계를 활용하여, 관련 데이터를 조회할 때 사용됩니다.

## 다양한 유형의 JOIN

1. **INNER JOIN (또는 JOIN)**:
    - 두 테이블에서 일치하는 값이 있는 행만 반환됩니다.
    - 일치하는 값이 없는 행은 결과에서 제외됩니다.
2. **LEFT (OUTER) JOIN (또는 LEFT JOIN)**:
    - 왼쪽 테이블의 모든 행과 오른쪽 테이블의 일치하는 행이 반환됩니다.
    - 오른쪽 테이블에 일치하는 행이 없는 경우, 해당 결과 행의 오른쪽 테이블 컬럼 값은 NULL이 됩니다.
3. **RIGHT (OUTER) JOIN (또는 RIGHT JOIN)**:
    - 오른쪽 테이블의 모든 행과 왼쪽 테이블의 일치하는 행이 반환됩니다.
    - 왼쪽 테이블에 일치하는 행이 없는 경우, 해당 결과 행의 왼쪽 테이블 컬럼 값은 NULL이 됩니다.
4. **FULL (OUTER) JOIN**:
    - 두 테이블의 모든 행이 반환됩니다.
    - 일치하는 행이 없는 경우, 해당 결과 행의 누락된 테이블 컬럼 값은 NULL이 됩니다.
5. **CROSS JOIN**:
    - 두 테이블의 모든 가능한 조합이 반환됩니다. (즉, 카테시안 곱)
    - 이 연산은 두 테이블 간의 조건 없이 모든 행을 조합합니다.
6. **SELF JOIN**:
    - 테이블이 자신과 조인됩니다.
    - 자기 자신과의 JOIN은 테이블 내의 행 간 관계를 찾을 때 유용합니다.

JOIN을 사용하면 여러 테이블의 데이터를 통합하여 복잡한 질의를 수행할 수 있습니다. 일반적으로 JOIN 작업은 두 테이블 간에 공통의 컬럼 (예: 기본 키와 외래 키)을 기반으로 수행됩니다.

## 예시

### **Employees 테이블**

| EmpID | EmpName | DeptID |
| --- | --- | --- |
| 1 | Alice | 10 |
| 2 | Bob | 20 |
| 3 | Charlie | 10 |
| 4 | David | 30 |

### **Departments 테이블**

| DeptID | DeptName |
| --- | --- |
| 10 | IT |
| 20 | Sales |
| 30 | HR |
| 40 | Finance |

## JOIN

## ****INNER JOIN****

```sql
SELECT EmpName, DeptName
FROM Employees
INNER JOIN Departments ON Employees.DeptID = Departments.DeptID;
```

| EmpName | DeptName |
| --- | --- |
| Alice | IT |
| Bob | Sales |
| Charlie | IT |
| David | HR |

## **OUTER JOIN**

- 매칭되는 행이 없어도 결과를 가져오고 매칭되는 행이 없는 경우 NULL로 표시한다
- ****LEFT JOIN****

```sql
SELECT EmpName, DeptName
FROM Employees
LEFT JOIN Departments ON Employees.DeptID = Departments.DeptID;
```

| EmpName | DeptName |
| --- | --- |
| Alice | IT |
| Bob | Sales |
| Charlie | IT |
| David | HR |

- RIGHT JOIN

```sql
SELECT EmpName, DeptName
FROM Employees
RIGHT JOIN Departments ON Employees.DeptID = Departments.DeptID;
```

| EmpName | DeptName |
| --- | --- |
| Alice | IT |
| Charlie | IT |
| Bob | Sales |
| David | HR |
| NULL | Finance |

## 다이어그램

<img width="656" alt="image" src="https://github.com/HJC96/WebDev/assets/87226129/652ce82f-79c3-4a6d-a3a0-818f9afd103e">


[표 설명](https://www.notion.so/331c629d742e47909fb645b4ac8a7b14?pvs=21)
