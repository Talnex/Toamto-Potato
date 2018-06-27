package Beans;

public class Tomato_clock {
    private int hour;
    private int min;

    public Tomato_clock(){
        hour = 20;
        min = 0;
    }

    public void reset(){
        hour = 20;
        min = 1;
    }

    public String getTime(){
        if (hour==0 && min==0 ) {
            return "完成";
        }
        if (min==0){
            min = 59;
            hour--;
        }else min--;
        if (min<10&&hour<10) return "0"+hour+":"+"0"+min;
        if (min<10) return hour+":"+"0"+min;
        if (hour<10) return "0"+hour+":"+min;
        return hour+":"+min;
    }
}
