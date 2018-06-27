package Beans;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Potato {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm E a");
    private Date date;
    private boolean is_ok;
    private int rank;
    private String ca;
    private String name;
    private String info;

    public Potato(Date date, boolean is_ok, int rank, String ca, String name, String info) {
        this.date = date;
        this.is_ok = is_ok;
        this.rank = rank;
        this.ca = ca;
        this.name = name;
        this.info = info;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isIs_ok() {
        return is_ok;
    }

    public void setIs_ok(boolean is_ok) {
        this.is_ok = is_ok;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return simpleDateFormat.format(date) +"  "+ getName() +"   "+ getInfo() +"   "+ getCa() +"  "+ getRank() +"  "+ isIs_ok();
    }
}
