## 기본 자료구조
---
1. 어레이/구조체(클래스)
~~~java
int[] arr = new int[5]; // 정수 배열 생성
~~~
~~~java
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
~~~

2. 스택/큐
~~~java
import java.util.Stack;

Stack<Integer> stack = new Stack<>();
stack.push(1);
stack.push(2);
int top = stack.pop(); // 2
~~~
~~~java
import java.util.LinkedList;
import java.util.Queue;

Queue<Integer> queue = new LinkedList<>();
queue.add(1);
queue.add(2);
int front = queue.poll(); // 1

~~~
3. 연결리스트
~~~java
import java.util.LinkedList;

LinkedList<Integer> linkedList = new LinkedList<>();
linkedList.add(1);
linkedList.add(2);
int first = linkedList.get(0); // 1
~~~
4. 트리
~~~java
class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}
~~~
5. 그래프
~~~java
import java.util.LinkedList;

class Graph {
    int vertices;
    LinkedList<Integer>[] adjacencyList;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    void addEdge(int src, int dest) {
        adjacencyList[src].add(dest);
        adjacencyList[dest].add(src);
    }
}
~~~

6. 정렬
~~~java
void bubbleSort(int[] arr) {
    int n = arr.length;

    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j
~~~
7. 해시
~~~java
import java.util.HashMap;

HashMap<String, Integer> hashMap = new HashMap<>();
hashMap.put("one", 1);
hashMap.put("two", 2);
int value = hashMap.get("one"); // 1
~~~
8. 우선순위 큐
~~~java
import java.util.PriorityQueue;

PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
priorityQueue.add(3);
priorityQueue.add(1);
priorityQueue.add(2);
int highestPriority = priorityQueue.poll(); // 1
~~~



