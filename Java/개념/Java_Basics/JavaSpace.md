# 예제로 정리하는 자바 문법

## 입/출력

~~~ java
/*1 2 를 입력받고 1 + 2를 출력하는 예제*/

import java.util.*;
import java.io.*;

public class Main
{
  public static void main(String[] args) throws IOException
  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] s = br.readLine().split(" ");
    int sum = 0;
    for(int i=0;i<s.length;i++)
    {
        sum += Integer.parseInt(s[i]);
    }
      System.out.println(sum);
  }
}
~~~


~~~java
/*1 3을 입력받고, 소수점 9자리까지 출력하는 예제*/
import java.io.*;
import java.util.*;

public class Main{
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] s = br.readLine().split(" ");
    double num1 = Integer.parseInt(s[0]);
    double num2 = Integer.parseInt(s[1]);
    System.out.printf("%.9f", num1/num2);

  }
}
~~~

~~~java
/*string 이어 붙이기*/

import java.io.*;
import java.util.*;

public class Main{
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    s += "??!";
    System.out.println(s);
  }
}
~~~

~~~java
/*string 원소에 접근할때 charAt 사용*/
import java.io.*;
import java.util.*;

public class Main{
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s1 = br.readLine();
    String s2 = br.readLine();

    int[] ans = new int[6];
    ans[0] = Integer.parseInt(s1);
    ans[1] = Integer.parseInt(s2);
    for(int i=2;i<5;i++){
        ans[i] = (Integer.parseInt(s1) * (s2.charAt(4-i)-'0'));
    }
    ans[5] = Integer.parseInt(s1) * Integer.parseInt(s2); 
    for(int i=2;i<6;i++){
        System.out.println(ans[i]);
    }
  } 
}


~~~


## ArrayList
~~~java
/* ArrayList 사용시 get()을 통해 값을 얻어온다.*/
import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException {
		ArrayList<String> arr = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String[] st = br.readLine().split(" ");
			double num1 = Double.parseDouble(st[0]);
			double num2 = Double.parseDouble(st[1]);
			if(num1 == 0 && num2 == 0)
				break;
			if(num1%num2 == 0) {
				arr.add("multiple");
			}
			else if(num2%num1 == 0) {
				arr.add("factor");
			}
			else
				arr.add("neither");
		}
		for(int i=0;i<arr.size();i++) {
			System.out.println(arr.get(i));
		}
	}
}
/* 
8 16
32 4
17 5
0 0
*/
~~~

## 정렬
~~~java
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T_str = br.readLine();
        HashMap<String,Integer> map = new HashMap();
        ArrayList<String> answer = new ArrayList<>();

        int T = Integer.parseInt(T_str);
        for(int i=0;i<T;i++) {
            String num_str = br.readLine();
            int num = Integer.parseInt(num_str);
            ArrayList<String> list = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                String[] txt = br.readLine().split(" ");
                map.put(txt[0], Integer.parseInt(txt[1]));
                list = new ArrayList<>(map.keySet());
                Collections.sort(list, new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        return map.get(o2) - map.get(o1);
                    }
                });
            }
            answer.add(list.get(0));
        }
        for(String key:answer){
            System.out.println(key);
        }

    }
}
/*
2
3
Yonsei 10
Korea 10000000
Ewha 20
2
Yonsei 1
Korea 10000000
*/
~~~

~~~java
/* Comparator 사용 */
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int num = Integer.parseInt((br.readLine()));
        String[][] student = new String[num][4];
        for (int i = 0; i < num; i++) {
            String[] read = br.readLine().split(" ");
            student[i][0] = read[0];
            student[i][1] = read[1];
            student[i][2] = read[2];
            student[i][3] = read[3];
        }
        Arrays.sort(student, new Comparator<String[]>(){
            public int compare(String[] o1, String[] o2){
                if(o2[1].equals(o1[1])){
                    if(o1[2].equals(o2[2])){
                        if(o2[3].equals(o1[3])){
                            return o1[0].compareTo(o2[0]);
                        }
                        return Integer.parseInt(o2[3]) - Integer.parseInt(o1[3]);
                    }
                    return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
                }
                return Integer.parseInt(o2[1]) - Integer.parseInt(o1[1]);
            }
        });
        for (int i = 0; i < num; i++) {
            System.out.println(student[i][0]);
        }
    }
}
~~~


~~~java
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> arr = new ArrayList<>();
        HashSet<String> s = new HashSet<String>();
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            String st = br.readLine();
            s.add(st);
        }
        for(String elem:s){
            arr.add(elem);
        }
        Collections.sort(arr, new Comparator<String>(){
           public int compare(String s1, String s2){
               if(s1.length() == s2.length())
                   return s1.compareTo(s2);
               else
                   return s1.length() - s2.length();
           }
        });
        for(String elem:arr){
            System.out.println(elem);
        }
    }
}
/*
13
but
i
wont
hesitate
no
more
no
more
it
cannot
wait
im
yours
*/
~~~

~~~java
/* StringBuffer를 사용하여 문자열 크기 동적으로 변경 */
import java.util.*;
import java.io.*;

public class Main{
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int num = Integer.parseInt(br.readLine());
    int[] arr = new int[num];
    for(int i=0;i<num;i++){
      arr[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(arr);
    for(int i=0;i<num;i++)
        sb.append(arr[i]+"\n");
    System.out.print(sb);
  }
}

~~~

~~~java
/*reverse sort*/
import java.io.*;
import java.util.*;

public class Main{
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    ArrayList<Integer> ar = new ArrayList<Integer>();
    for(int i=0;i<input.length();i++){
         ar.add(Integer.parseInt(String.valueOf(input.charAt(i))));
    }
    Collections.sort(ar, new Comparator<Integer>(){
       public int compare(Integer a, Integer b){
            if(a>b) return -1;
            else if(a<b) return 1;
            else         return 0;
        }
    });
    for(int i=0;i<ar.size();i++){
        System.out.print(ar.get(i));
    }
  }
}
/*
2143
*/
~~~

~~~java
import java.io.*;

public class Main{
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for(int i=0;i<T;i++)
    {
        String[] st = br.readLine().split(" ");
      	for(int j=0;j<st[0].length();j++){
			int index = st[1].indexOf(st[0].charAt(j));
        }
    }
  }
}
~~~

## Hash & Search
~~~java
/*Binary Search*/
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T1 = Integer.parseInt(br.readLine());
        String[] M1 = br.readLine().split(" ");
        long[] arr1 = new long[T1];
        for(int i=0;i<T1;i++){
            arr1[i] = Long.parseLong(M1[i]);
        }

        int T2 = Integer.parseInt(br.readLine());
        String[] M2 = br.readLine().split(" ");
        long[] arr2 = new long[T2];
        for(int i=0;i<T2;i++){
            arr2[i] = Long.parseLong(M2[i]);
        }

        Arrays.sort(arr1);

        for(int i=0;i<T2;i++) {
            int index = Arrays.binarySearch(arr1, arr2[i]);
            if(index >=0 )
            {
                sb.append("1\n");
                continue;
            }
            else{
                sb.append("0\n");
                continue;
            }
        }
        System.out.print(sb);
    }
}
/*
5
4 1 5 2 3
5
1 3 7 9 5
*/
~~~


~~~java
/* Hash */
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<Long,Long> h = new HashMap<Long,Long>();
        int N = Integer.parseInt(br.readLine());
        String[] arr1 = br.readLine().split(" ");

        for(int i=0;i<N;i++){
            h.put(Long.parseLong(arr1[i])+10000000,h.getOrDefault(arr1[i],Long.valueOf(0)+1));
        }

        int M = Integer.parseInt(br.readLine());
        String[] arr2 = br.readLine().split(" ");

        for(int i=0;i<M;i++){
            long value = h.getOrDefault(Long.parseLong(arr2[i])+10000000,Long.valueOf(0));
            if(value == 0){
                sb.append(0 + " ");
            }
            else{
                sb.append(1 + " ");
            }
        }
        System.out.print(sb);
    }
}
/*
5
6 3 2 10 -10
8
10 9 -5 2 3 4 5 -10
*/

~~~

~~~java
/* Hash */
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> h = new HashMap<String, Integer>();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> s = new ArrayList<String>();
        String[] arr = br.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);

        for(int i=0;i<N;i++){
            String in = br.readLine();
            h.put(in,h.getOrDefault(in,0) + 1);
        }

        int ans=0;
        for(int i=0;i<M;i++){
            String in = br.readLine();
            int value = h.getOrDefault(in,0);
            if(value == 0){
                continue;
            }
            else{
                ans++;
                //sb.append(in+'\n');
                s.add(in);
            }
        }
        sb.insert(0,String.valueOf(ans)+'\n');
        Collections.sort(s);
        for(String elem:s){
            sb.append(elem+'\n');
        }
        System.out.print(sb);
    }
}
/*
입력
3 4
ohhenrie
charlie
baesangwook
obama
baesangwook
ohhenrie
clinton
*/
~~~

## Stack & Queue
~~~java
/*stk*/
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<String> stk = new Stack<String>();
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String[] in = br.readLine().split(" ");
            sb.append("Case #"+(i+1)+": ");
            for(int j=0;j<in.length;j++){
                stk.push(in[j]);
            }
            while(!stk.empty()){
                sb.append(stk.pop()+" ");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
/*
입력
3
this is a test
foobar
all your base
*/
~~~

~~~java
/* Queue */
import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException{
		Queue<Integer> q = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			String[] command = br.readLine().split(" ");
			if(command[0].compareTo("push") == 0) {
				q.add(Integer.parseInt(command[1]));
			}
			else if(command[0].compareTo("front") == 0) {
				if(q.isEmpty()) {
					sb.append("-1\n");
					continue;
				}
				sb.append(String.valueOf(q.peek())+'\n');
			}
			else if(command[0].compareTo("back") == 0) {
				if(q.isEmpty()) {
					sb.append("-1\n");
					continue;
				}
				int back =((LinkedList<Integer>)q).getLast();
				sb.append((String.valueOf(back)+'\n'));
			}
			else if(command[0].compareTo("size") == 0) {
				sb.append(String.valueOf(q.size())+'\n');
			}
			else if(command[0].compareTo("pop") == 0) {
				if(q.isEmpty()) {
					sb.append("-1\n");
					continue;
				}
				sb.append(String.valueOf(q.poll())+'\n');
			}
			else if(command[0].compareTo("empty") == 0) {
				if(q.isEmpty())
					sb.append((String.valueOf(1)+'\n'));
				else
					sb.append((String.valueOf(0)+'\n'));
			}		
		}
		System.out.print(sb);
	}
}
/*
입력
15
push 1
push 2
front
back
size
empty
pop
pop
pop
size
empty
pop
push 3
empty
front
*/

~~~java
import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=1;i<=T;i++) {
			q.add(i);
		}
		int cnt = T;
		while(cnt>0) {
			int front1 = q.poll();
			sb.append(front1+" ");
			if(q.isEmpty())
				break;
			int front2 = q.poll();
			q.add(front2);
			cnt-=1;
		}
		System.out.print(sb);
	}
}
/*
7
*/
~~~


~~~
## Priority Queue
### 최소 힙
~~~java
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        PriorityQueue<Long> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            long num = Long.parseLong((br.readLine()));
            if(num == 0){
                if(pq.isEmpty()){
                    sb.append("0\n");
                    continue;
                }
                sb.append(pq.poll() + "\n");
            }
            else{
                pq.add(num);
            }
        }
        System.out.print(sb);
    }
}
~~~
### 최대힙
~~~java
/* 힙에 넣을 숫자 * -1을 하여 add 하는 테크닉 */
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        PriorityQueue<Long> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            long num = Long.parseLong((br.readLine()));
            if(num == 0){
                if(pq.isEmpty()){
                    sb.append("0\n");
                    continue;
                }
                sb.append(-1 * pq.poll() + "\n");
            }
            else{
                pq.add(-1 * num);
            }
        }
        System.out.print(sb);
    }
}
~~~
