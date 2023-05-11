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
    public void printSampleCalendar(int year, int month){
        System.out.printf("<<%4d년%3d월>>\n", year, month);

        System.out.println("SU MO TU WE TH FR SA");
        System.out.println("----------------------");

        int maxDay = getMaxDaysOfMonth(year, month);
        for(int i=1;i<=maxDay;i++){
            System.out.printf("%2d ",i);
            if(i%7==0)
                System.out.println();
        }
        System.out.println();
    }
}
