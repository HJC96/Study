import java.text.ParseException;
import java.util.Scanner;

public class Prompt {
    private final static String PROMPT ="cal>";
    public void printMenu(){
        System.out.println("+----------------------+");
        System.out.println("1. 일정 등록");
        System.out.println("2. 일정 검색");
        System.out.println("3. 달력 보기");
        System.out.println("h. 도움말 q. 종료");
        System.out.println("+----------------------+");
    }
    public void runPrompt() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Calendar cal = new Calendar();
        printMenu();
        boolean isLoop = true;
        while(isLoop){
            System.out.println("명령(1, 2, 3, h, q)");
            String cmd = scanner.next();
            switch(cmd) {
                case "1":
                    cmdRegister(scanner, cal);
                    break;
                case "2":
                    cmdSearch(scanner, cal);
                    break;
                case "3":
                    cmdCal(scanner, cal);
                    break;
                case "h":
                    printMenu();
                    break;
                case "q":
                    isLoop = false;
                    break;
            }
        }
        scanner.close();
    }

    private void cmdRegister(Scanner s, Calendar c) throws ParseException {
        System.out.println("[새 일정 등록]");
        System.out.println("[날짜를 입력해 주세요 (yyyy-mm-dd).]");
        String date = s.next();
        String text = "";
        System.out.println("[일정을 입력해 주세요.(문장의 끝에 ;을 입력해주세요)]");
        while(true){
            String word = s.next();
            text += word +" ";
            if(word.endsWith(";")){
                break;
            }
        }
        c.registerPlan(date, text);
    }

    private void cmdSearch(Scanner s, Calendar c) {
        System.out.println("[일정 검색]");
        System.out.println("[날짜를 입력해 주세요 (yyyy-mm-dd).]");
        String date = s.next();
        String plan = "";
        try {
            plan = c.searchPlan(date);
        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("일정 검색 중 오류가 발생했습니다.");
        }
        System.out.println(plan);
    }

    private void cmdCal(Scanner s, Calendar c) {

        while(true){
            System.out.println("연도 입력하세요(exit: -1)");
            System.out.print("YEAR> ");
            int year = s.nextInt();
            System.out.println("달을 입력하세요");
            System.out.print("MONTH> ");
            int month = s.nextInt();

            if(month > 12 || month < 1){
                System.out.println("잘못된 입력입니다.");
                return;
            }
            c.printSampleCalendar(year, month);
        }
    }

    public static void  main(String[] args) throws ParseException {
        Prompt p = new Prompt();
        p.runPrompt();


    }
}
