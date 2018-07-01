package Charts;

import Utils.DB.DBM;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Write_Html {
    String date;
    Connection connection = DBM.getConnection();
    List<JSONObject> list = new ArrayList<>();
    List<JSONObject> list1 = new ArrayList<>();


    public String getusename() throws JSONException {
        String result = "";
        for (JSONObject object: list
             ) {
            result = result +"'"+object.getString("name")+"',";
        }
        return result.substring(0,result.length()-1);
    }

    public String getdate(){
        return date;
    }

    public Write_Html(String date) throws SQLException, JSONException {
        this.date = date;
        String sql = "select * from View_result where 日期='"+ date +"' and 时间 > 0";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            JSONObject object = new JSONObject();
            object.put("value",resultSet.getInt(3));
            object.put("name",resultSet.getString(1).trim());
            System.out.println(object.toString());
            list.add(object);
        }

        sql = "select *\n" +
                "from View_趋势\n" +
                "where 日期<DATEADD(dd, (9-CASE when datepart(weekday,'" +
                date +
                "') = 1 Then 8 ELSE datepart(weekday,'" +
                date +
                "')END),'" +
                date +
                "')\n" +
                " and 日期>=DATEADD(dd, -(CASE when datepart(weekday,'" +
                date +
                "') = 1 Then 8 ELSE datepart(weekday,'" +
                date+
                "')END -2), '" +
                date +
                "')\n" +
                "order by 日期";
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet1 = preparedStatement.executeQuery();
        while (resultSet1.next()){
            JSONObject object1 = new JSONObject();
            object1.put("score",resultSet1.getFloat(2));
            object1.put("date",resultSet1.getString(1).trim());
            System.out.println(object1.toString());
            list1.add(object1);
        }

        preparedStatement.close();
        connection.close();
    }

    public String  getusejson() throws JSONException {
        String result ="";
        for (JSONObject object: list
             ) {
            result = result + "{value:"+object.getInt("value")+", "+"name:'"+object.getString("name")+"'},";
        }
        result.replace("\"","'");
        return result.substring(0,result.length()-1);
    }

    public String gettendencydata() throws JSONException {
        String result = "";

        for (JSONObject object: list1
                ) {
            result = result + object.getInt("score")+",";
        }
        return result.substring(0,result.length()-1);

    }

    public String gettendencyname() throws JSONException {
        String result = "";
        for (JSONObject object: list1
                ) {
            result = result +"'"+object.getString("date")+"',";
        }
        return result.substring(0,result.length()-1);
    }


    public  void write() throws IOException, JSONException {
        FileWriter fileWriter = new FileWriter("/Users/talnex/IdeaProjects/番茄土豆/src/Web/tomato_html.html");
        fileWriter.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Hello</title>\n" +
                "</head>\n" +
                "<body style=\"background-color: #FFFFFF\">\n" +
                "<!-- 为ECharts准备一个具备大小（宽高）的Dom -->\n" +
                "<div id=\"main\" style=\"background-color: #FFFFFF;height:700px;width: 600px;float: left;padding-left: 120px;padding-top: 120px\"></div>\n" +
                "<div id=\"he\" style=\"background-color: #FFFFFF;height:740px;width: 850px;float: left;padding-left: 50px;padding-top: 120px\"></div>\n" +
                "\n" +
                "<!-- ECharts单文件引入 -->\n" +
                "<script src=echarts-all.js type=\"text/javascript\"></script>\n" +
                "<script src=macarons.js></script>\n" +
                "<script>\n" +
                "    var myChart = echarts.init(document.getElementById('main'),'macarons', {renderer: 'svg'});\n" +
                "    var option = option = {\n" +
                "        title : {\n" +
                "            text: '每日软件使用情况图',\n" +
                "            subtext: '"+getdate()+"',\n" +
                "            x:'center'\n" +
                "        },\n" +
                "        tooltip : {\n" +
                "            trigger: 'item',\n" +
                "            formatter: \"{a} <br/>{b} : {c} ({d}%)\"\n" +
                "        },\n" +
                "        legend: {\n" +
                "            x : 'center',\n" +
                "            y : 'bottom',\n" +
                "            data:["+getusename()+"]\n" +
                "        },\n" +
                "        toolbox: {\n" +
                "            show : true,\n" +
                "            feature : {\n" +
                "                mark : {show: false},\n" +
                "                dataView : {show: false, readOnly: false},\n" +
                "                magicType : {\n" +
                "                    show: true,\n" +
                "                    type: ['pie', 'funnel']\n" +
                "                },\n" +
                "                restore : {show: true},\n" +
                "                saveAsImage : {show: true}\n" +
                "            }\n" +
                "        },\n" +
                "        calculable : true,\n" +
                "        series : [\n" +
                "            {\n" +
                "                name:'软件：',\n" +
                "                type:'pie',\n" +
                "                radius : [30, 200],\n" +
                "                center : ['50%', '50%'],\n" +
                "                roseType : 'radius',\n" +
                "                label: {\n" +
                "                    normal: {\n" +
                "                        show: false\n" +
                "                    },\n" +
                "                    emphasis: {\n" +
                "                        show: true\n" +
                "                    }\n" +
                "                },\n" +
                "                lableLine: {\n" +
                "                    normal: {\n" +
                "                        show: false\n" +
                "                    },\n" +
                "                    emphasis: {\n" +
                "                        show: true\n" +
                "                    }\n" +
                "                },\n" +
                "                data:[\n" +
                                getusejson()+
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    };\n" +
                "    myChart.setOption(option);\n" +
                "</script>\n" +
                "\n" +
                "<script>\n" +
                "    var myChart = echarts.init(document.getElementById('he'),'macarons', {renderer: 'svg'});\n" +
                "    var option = option = {\n" +
                "        title : {\n" +
                "            text: '本周分数趋势',\n" +
                "            subtext: '',\n" +
                "            x:'center'\n" +
                "        },\n" +
                "        xAxis: {\n" +
                "            type: 'category',\n" +
                "            data: ["+gettendencyname()+"]\n" +
                "        },\n" +
                "        yAxis: {\n" +
                "            type: 'value'\n" +
                "        },\n" +
                "        series: [{\n" +
                "            data: ["+gettendencydata()+"],\n" +
                "            type: 'line'\n" +
                "        }]\n" +
                "    };\n" +
                "    myChart.setOption(option);\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
        fileWriter.close();
    }
}
