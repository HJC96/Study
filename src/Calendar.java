import java.util.Scanner;

public class Calendar {
    private static final int[] maxDays      = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_maxDays = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int getMaxDaysOfMonth(int year, int month){
        if(isLeapYear(year)) {
            return LEAP_maxDays[month];
        }else {
            return maxDays[month];
        }
    }

    public boolean isLeapYear(int year){
        if((year%4 == 0) && (year %100 !=0 || year%400 ==0))
            return true;
        else
            return false;

    }
    public int getBlankCnt(int year, int month, int day){
        int syear = 1970;
        final int STANDARDWEEKDAY = 3; // 1970/Jan/1st = Thursday

        int count = 0;
        for(int i = syear;i < year;i++){
            int delta = isLeapYear(i)?366:365;
            count+=delta;
        }
        for(int i=1;i<month;i++){
            int delta = getMaxDaysOfMonth(year,i);
            count+=delta;
        }
        count += day;

        int weekday = (count + STANDARDWEEKDAY)%7;
        return weekday;

    }
    public void printSampleCalendar(int year, int month){
        System.out.printf("<<%4d년%3d월>>\n", year, month);

        System.out.println("SU MO TU WE TH FR SA");
        System.out.println("----------------------");

        int blank_cnt = getBlankCnt(year, month, 1);
        for(int i=0;i<blank_cnt;i++){
            System.out.print("   ");
        }

        int maxDay = getMaxDaysOfMonth(year, month);
        for(int i=blank_cnt+1;i<=maxDay+blank_cnt;i++){
            System.out.printf("%2d ",i-blank_cnt);
            if(i%7==0)
                System.out.println();
        }
        System.out.println();
    }
}
