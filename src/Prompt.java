import java.util.Scanner;

public class Prompt {
    private final static String PROMPT ="cal>";
    public void runPrompt(){
        Scanner scanner = new Scanner(System.in);
        Calendar cal = new Calendar();


        while(true){
            System.out.println("연도 입력하세요(exit: -1)");
            System.out.print("YEAR> ");
            int year = scanner.nextInt();
            if(year == -1){
                break;
            }
            System.out.println("달을 입력하세요");
            System.out.print("MONTH> ");
            int month = scanner.nextInt();


            if(month > 12 || month < 1){
                continue;
            }
            cal.printSampleCalendar(year, month);
        }
        scanner.close();
    }
    public static void  main(String[] args){
        Prompt p = new Prompt();
        p.runPrompt();

    }
}
