package Tomato;

import Beans.Runtime_Info;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Get_Runtime_Result {
    public static List<Runtime_Info> get_runtime_result() throws IOException {
        List<Runtime_Info> result = new ArrayList<>();
        Process process = Runtime.getRuntime().exec("ps aux");
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        String[] arr;
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            arr = line.split("\\s+");
            if (!arr[0].equals("talnex")) continue;
            else {
                if (arr[10].contains("Applications")) {
                    if (arr[10].contains("Google")) continue;
                    boolean flag = true;
                    Runtime_Info runtime_info = new Runtime_Info();
                    runtime_info.setStart(arr[8]);
                    runtime_info.setTime(arr[9]);
                    runtime_info.setApp(arr[10].substring(arr[10].lastIndexOf("/") + 1, arr[10].length()));
                    for (Runtime_Info info : result
                            ) {
                        if (info.getApp().equals(runtime_info.getApp())) {
                            flag = false;
                        }
                    }
                    if (flag) result.add(runtime_info);
                    else continue;
                }
            }
        }
        for (Runtime_Info a:
                result) {
            System.out.printf(a.getTime()+" "+a.getStart()+" "+a.getApp());
            System.out.println();
        }

        return result;
    }
}
