package Utils;

import java.io.FileWriter;
import java.io.IOException;

public class Write_Html {
    public static void Write(String name, String data) throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/talnex/IdeaProjects/番茄土豆/src/Web/" + name + ".html");
        fileWriter.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Hello</title>\n" +
                "</head>\n" +
                "<body style=\"background-color: #FFFFFF\">\n" +
                "<!-- 为ECharts准备一个具备大小（宽高）的Dom -->\n" +
                "<div id=\"main\" style=\"background-color: #FFFFFF;height:600px\"></div>\n" +
                "<!-- ECharts单文件引入 -->\n" +
                "<script src= echarts-all.js type=\"text/javascript\"></script>\n" +
                "<script >\n" +
                "    var myChart = echarts.init(document.getElementById('main'),null,{renderer: 'svg'});\n");
        fileWriter.write("    var option = " + data + ";\n");
        fileWriter.write("    myChart.setOption(option);\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>");
        fileWriter.close();
    }
}
