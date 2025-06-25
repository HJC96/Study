import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Calendar {
    private static final int[] maxDays      = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int[] LEAP_maxDays = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static final String SAVE_FILE = "calendar.dat";
    private HashMap<Date, PlanItem> planMap;

    public Calendar(){
        planMap = new HashMap<Date, PlanItem>();
        File f = new File(SAVE_FILE);
        if(!f.exists())
            return;
        try {
            Scanner s = new Scanner(f);
            while(s.hasNext()){
                String line = s.nextLine();
                String[] words = line.split(",");
                String date = words[0];
                String detail = words[1].replaceAll("\"","");
                PlanItem p = new PlanItem(date, detail);
                planMap.put(p.getDate(),p);
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public int getMaxDaysOfMonth(int year, int month){
        if(isLeapYear(year)) {
            return LEAP_maxDays[month];
        }else {
            return maxDays[month];
        }
    }
    public void registerPlan(String strDate, String plan){
        PlanItem p = new PlanItem(strDate, plan);
        planMap.put(p.getDate(),p);

        File f = new File(SAVE_FILE);
        String item = p.saveString();
        try {
            FileWriter fw = new FileWriter(f, true);
            fw.write(item);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public PlanItem searchPlan(String strDate){
        Date date = PlanItem.getDatefromString(strDate);
        return planMap.get(date);
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
}
