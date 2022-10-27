//
// Decompiled by Jadx - 589ms
//
package com.github.catvod.spider;

import android.util.Base64;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.parser.MixDemo;
import com.github.catvod.parser.MixWeb;
import com.github.catvod.spider.merge.Vo;
import com.github.catvod.spider.merge.mI;
import java.io.ByteArrayInputStream;
import java.util.Map;

public class Proxy extends Spider {
    public static int I = -1;

    static void I() {
        if (I <= 0) {
            for (int i = 9978; i < 10000; i++) {
                if (mI.gR("http://127.0.0.1:" + i + "/proxy?do=ck", (Map) null).equals("ok")) {
                    SpiderDebug.log("Found local server port " + i);
                    I = i;
                    return;
                }
            }
        }
    }

    public static String localProxyUrl() {
        I();
        return "http://127.0.0.1:" + I + "/proxy";
    }

    public static Object[] proxy(Map<String, String> map) {
        try {
            String str = map.get("do");
            if (str.equals("live")) {
                if (!map.get("type").equals("txt")) {
                    return null;
                }
                String str2 = map.get("ext");
                if (!str2.startsWith("http")) {
                    str2 = new String(Base64.decode(str2, 10), "UTF-8");
                }
                return Vo.V7(str2);
            } else if (str.equals("MixDemo")) {
                return MixDemo.loadHtml(map.get("flag"), map.get("url"));
            } else {
                if (str.equals("MixWeb")) {
                    return MixWeb.loadHtml(map.get("flag"), map.get("url"));
                }
                if (str.equals("ck")) {
                    return new Object[]{200, "text/plain; charset=utf-8", new ByteArrayInputStream("ok".getBytes("UTF-8"))};
                }
                if (str.equals("push")) {
                    return PushAgent.vod(map);
                }
                if (str.equals("xBPQ")) {
                    return xBPQ.loadPic(map);
                }
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }
}
