import java.util.Scanner;

public class Calendar {
    private static final int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_maxDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int getMaxDaysOfMonth(int year, int month){
        if(isLeapYear(year)) {
            return LEAP_maxDays[month - 1];
        }else {
            return maxDays[month - 1];
        }
    }

    public boolean isLeapYear(int year){
        if((year%4 == 0) && (year %100 !=0 || year%400 !=0))
            return true;
        else
            return false;

    }
    public int getBlankCnt(String day){
        if(day.equals("su"))    return 1;
        else if(day.equals("mo"))    return 2;
        else if(day.equals("tu"))    return 3;
        else if(day.equals("we"))    return 4;
        else if(day.equals("th"))    return 5;
        else if(day.equals("fr"))    return 6;
        else return 7;
    }
    public void printSampleCalendar(int year, int month, String day){
        System.out.printf("<<%4d년%3d월>>\n", year, month);

        System.out.println("SU MO TU WE TH FR SA");
        System.out.println("----------------------");

        int blank_cnt = getBlankCnt(day);
        for(int i=1;i<blank_cnt;i++){
            System.out.print("   ");
        }

        int maxDay = getMaxDaysOfMonth(year, month);
        for(int i=blank_cnt;i<=maxDay+blank_cnt-1;i++){
            System.out.printf("%2d ",i-blank_cnt+1);
            if(i%7==0)
                System.out.println();
        }
        System.out.println();
    }
}
