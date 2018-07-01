package Utils;

import Charts.Write_Html;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws IOException, SQLException, JSONException {
        Write_Html write_html = new Write_Html("2018-7-2");
        System.out.println(write_html.getusename());
        System.out.println(write_html.getusejson());
        System.out.println(write_html.gettendencydata());
        System.out.println(write_html.gettendencyname());
        write_html.write();

        Runtime.getRuntime().exec("open /Users/talnex/IdeaProjects/番茄土豆/src/Web/tomato_html.html");

    }


}
