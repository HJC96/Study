import java.util.Scanner;

public class Sum {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1, s2;
        s1 = sc.next();
        s2 = sc.next();
        int num1 = Integer.parseInt(s1);
        int num2 = Integer.parseInt(s2);
        System.out.println(num1 + num2);

        sc.close();
    }
}
