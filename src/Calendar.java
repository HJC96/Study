import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Calendar {
    private static final int[] maxDays      = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_maxDays = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private HashMap<Date, String> planMap;

    public Calendar(){
        planMap = new HashMap<Date, String>();
    }
    public int getMaxDaysOfMonth(int year, int month){
        if(isLeapYear(year)) {
            return LEAP_maxDays[month];
        }else {
            return maxDays[month];
        }
    }
    public void registerPlan(String strDate, String plan) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(strDate);
        System.out.println(date);
        planMap.put(date,plan);
    }
    public String searchPlan(String strDate) throws ParseException{
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(strDate);
        String plan = planMap.get(date);
        return plan;
    }

    public boolean isLeapYear(int year){
        if((year%4 == 0) && (year %100 !=0 || year%400 ==0))
            return true;
        else
            return false;

    }
    public int getBlankCnt(int year, int month, int day){
        int syear = 1970;
        final int STANDARDWEEKDAY = 4; // 1970/Jan/1st = Thursday

        int count = 0;
        for(int i = syear;i < year;i++){
            int delta = isLeapYear(i)?366:365;
            count+=delta;
        }
        for(int i=1;i<month;i++){
            int delta = getMaxDaysOfMonth(year,i);
            count+=delta;
        }
        count += day-1;

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

//    public static void main(String[] args) throws ParseException {
//        Calendar cal = new Calendar();
//        cal.registerPlan("2017-06-23", "Let's eat beef");
//        System.out.println(cal.searchPlan("2017-06-23"));
//    }
}
