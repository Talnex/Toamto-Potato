package Beans;


        import java.text.SimpleDateFormat;
        import java.util.Date;

public class Potato {
    private SimpleDateFormat simpleDateFormat_time = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat simpleDateFormat_date = new SimpleDateFormat("yyyy-MM-dd");
    private Date date;
    private boolean is_ok;
    private String  color;
    private String ca;
    private String name;
    private String info;
    private int no;


    public Potato(Date date, boolean is_ok, String color, String ca, String name, String info, int no) {
        this.date = date;
        this.is_ok = is_ok;
        this.color = color;

        this.ca = ca;
        this.name = name;
        this.info = info;
        this.no = no;
    }

    public Potato(){

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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTimeString() {
        return simpleDateFormat_time.format(date);
    }

    public String getDateString(){return simpleDateFormat_date.format(date);}

    @Override
    public String toString() {
        return date+" "+name+" "+ca+" "+color+" "+info+" "+isIs_ok();
    }
}
