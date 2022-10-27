//
// Decompiled by Jadx - 818ms
//
package com.github.catvod.spider;

import android.content.Context;
import com.github.catvod.crawler.Spider;
import com.github.catvod.spider.merge.AM;
import com.github.catvod.spider.merge.I;
import com.github.catvod.spider.merge.Qy;
import com.github.catvod.spider.merge.q2;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class AliPS extends Spider {
    private static Pattern Mo = Pattern.compile("(https:\\/\\/www.aliyundrive.com\\/s\\/[^\\\"]+)");
    private static String rF = "https://www.alipansou.com";
    private PushAgent g;

    protected static HashMap<String, String> rF() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.62 Safari/537.36");
        return hashMap;
    }
    
    public String detailContent(List<String> list) {
        String str;
        Matcher matcher;
        try {
            Pattern pattern = Mo;
            if (pattern.matcher(list.get(0)).find()) {
                return this.g.detailContent(list);
            }
            String k = I.k("https://www.alipansou.com" + list.get(0), (Map) null);
            String str2 = k.split("window\\.open\\(\"")[1].split("\"")[0];
            HashMap hashMap = new HashMap();
            hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.62 Safari/537.36");
            hashMap.put("Referer", "https://www.alipansou.com" + list.get(0));
            HashMap hashMap2 = new HashMap();
            I.T("https://www.alipansou.com" + str2, hashMap, hashMap2);
            if (!hashMap2.containsKey("Location") && !hashMap2.containsKey("location")) {
                str = "";
                if (str.isEmpty()) {
                    k = str;
                }
                matcher = pattern.matcher(k);
                if (matcher.find()) {
                    return "";
                }
                list.set(0, matcher.group(1).replaceAll("\\\\", ""));
                return this.g.detailContent(list);
            }
            str = I.s(hashMap2);
            if (str.isEmpty()) {
            }
            matcher = pattern.matcher(k);
            if (matcher.find()) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void init(Context context, String str) {
        AliPS.super.init(context, str);
        PushAgent pushAgent = new PushAgent();
        this.g = pushAgent;
        pushAgent.init(context, str);
    }

    public String playerContent(String str, String str2, List<String> list) {
        return this.g.playerContent(str, str2, list);
    }

    public String searchContent(String str, boolean z) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("7", "文件夹");
            hashMap.put("1", "视频");
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry entry : hashMap.entrySet()) {
                String str2 = (String) entry.getValue();
                AM TX = q2.rF(I.k(rF + "/search?k=" + URLEncoder.encode(str) + "&t=" + ((String) entry.getKey()), rF())).TX("van-row a");
                for (int i = 0; i < TX.size(); i++) {
                    Qy qy = (Qy) TX.get(i);
                    String ZN = qy.TX("template div").ZN();
                    Matcher matcher = Pattern.compile("(时间: \\S+)").matcher(ZN);
                    if (matcher.find()) {
                        String group = matcher.group(1);
                        if (ZN.contains(str)) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("vod_id", qy.g("href"));
                            jSONObject.put("vod_name", ZN);
                            jSONObject.put("remark", group);
                            jSONObject.put("vod_pic", "https://inews.gtimg.com/newsapp_bt/0/13263837859/1000");
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("list", jSONArray);
            return jSONObject2.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
