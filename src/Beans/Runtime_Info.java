package Beans;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Runtime_Info {
    private String Start;
    private String Time;
    private Date date = new Date();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String App;

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        String hour = start.substring(0, start.indexOf(":"));
        if (start.substring(start.length()-2,start.length()).equals("PM")){
            hour = String.valueOf(Integer.valueOf(hour) + 12);
        }
        Start = simpleDateFormat.format(date).toString()+" "+hour+":"+start.substring(start.indexOf(":") + 1, start.length() - 2);
    }

    public String getTime() {
        if (Time.lastIndexOf(":")==Time.indexOf(":"))return "00:"+Time;
        else return Time;
    }

    public void setTime(String time)  {
        Time = time;
    }

    public String getApp() {
        return App;
    }

    public void setApp(String app) {
        App = app;
    }

    @Override
    public String toString() {
        return getApp() + " " + getStart() + " " + getTime();
    }

    public int findweek(String week) {
        switch (week) {
            case "Mon":
                return 1;
            case "Tues":
                return 2;
            case "Wed":
                return 3;
            case "Thur":
                return 4;
            case "Fri":
                return 5;
            case "Sat":
                return 6;
            case "Sun":
                return 7;
            default:
                return 0;
        }
    }
}
