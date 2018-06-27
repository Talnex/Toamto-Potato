package Utils;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONException;
import org.json.JSONObject;

public class Detect {
    //设置APPID/AK/SK
    public static final String APP_ID = "11404512";
    public static final String API_KEY = "WXch1cH0mzo3TtmMOEKTVwdQ";
    public static final String SECRET_KEY = "jroD572v4zTEHDMGrIbzErHUrTIggZo3";

    public static void main(String[] args) throws JSONException {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 调用接口
        JSONObject res = client.asr("/Users/talnex/Downloads/16k.pcm", "pcm", 16000, null);
        System.out.println(res.toString(2));

    }

}
