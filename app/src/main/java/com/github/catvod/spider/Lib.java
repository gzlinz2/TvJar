//
// Decompiled by Jadx - 671ms
//
package com.github.catvod.spider;

import android.content.Context;
import android.text.TextUtils;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.merge.Hq;
import com.github.catvod.spider.merge.JD;
import com.github.catvod.spider.merge.Vf;
import com.github.catvod.spider.merge.ex;
import com.github.catvod.spider.merge.vR;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Lib extends Spider {
    private JSONObject u;
    private JSONObject ue;
    private Pattern B = Pattern.compile("/type/(\\d+).html");
    private Pattern W = Pattern.compile("/detail/(\\d+).html");
    private Pattern Pd = Pattern.compile("/play/(\\d+)-(\\d+)-(\\d+).html");
    private Pattern u6 = Pattern.compile("/show/(\\S+).html");
    protected String jw = null;

    private static String B(Pattern pattern, String str) {
        if (pattern == null) {
            return str;
        }
        try {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return matcher.group(1).trim();
            }
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return str;
    }

    protected static HashMap<String, String> ue() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.62 Safari/537.36");
        hashMap.put("Referer", "https://www.libvio.me/");
        return hashMap;
    }

    protected HashMap<String, String> W(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("method", "GET");
        hashMap.put("Upgrade-Insecure-Requests", "1");
        hashMap.put("DNT", "1");
        hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        hashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        hashMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        return hashMap;
    }

    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        int i;
        int i2;
        try {
            int i3 = 1;
            String[] strArr = {"", "", "", "", "", "", "", "", "", "", "", ""};
            strArr[0] = str;
            strArr[8] = str2;
            if (hashMap != null && hashMap.size() > 0) {
                for (String str3 : hashMap.keySet()) {
                    strArr[Integer.parseInt(str3)] = URLEncoder.encode(hashMap.get(str3));
                }
            }
            String str4 = "https://www.libvio.me/show/" + TextUtils.join("-", strArr) + ".html";
            String h = Vf.h(str4, W(str4));
            vR ue = JD.ue(h);
            JSONObject jSONObject = new JSONObject();
            ex B1 = ue.B1("ul.stui-page__item li");
            if (B1.size() == 0) {
                i2 = Integer.parseInt(str2);
                i = i2;
            } else {
                int i4 = -1;
                i = -1;
                int i5 = 0;
                while (true) {
                    if (i5 >= B1.size()) {
                        break;
                    }
                    Hq hq = (Hq) B1.get(i5);
                    Hq O = hq.O("a");
                    if (O != null) {
                        String pE = O.pE();
                        if (i == i4 && hq.f("active")) {
                            Matcher matcher = this.u6.matcher(O.Pd("href"));
                            i = matcher.find() ? Integer.parseInt(matcher.group(1).split("-")[8]) : 0;
                        }
                        if (pE.equals("尾页")) {
                            Matcher matcher2 = this.u6.matcher(O.Pd("href"));
                            if (matcher2.find()) {
                                i2 = Integer.parseInt(matcher2.group(1).split("-")[8]);
                            }
                        }
                    }
                    i5++;
                    i4 = -1;
                }
                i2 = 0;
            }
            JSONArray jSONArray = new JSONArray();
            if (!h.contains("没有找到您想要的结果哦")) {
                ex B12 = ue.B1("div.stui-vodlist__box");
                int i6 = 0;
                while (i6 < B12.size()) {
                    Hq hq2 = (Hq) B12.get(i6);
                    String ue2 = hq2.B1("a").ue("title");
                    String ue3 = hq2.B1("a").ue("data-original");
                    String jw = hq2.B1("a .pic-text").jw();
                    Matcher matcher3 = this.W.matcher(hq2.B1("a").ue("href"));
                    if (matcher3.find()) {
                        String group = matcher3.group(i3);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("vod_id", group);
                        jSONObject2.put("vod_name", ue2);
                        jSONObject2.put("vod_pic", ue3);
                        jSONObject2.put("vod_remarks", jw);
                        jSONArray.put(jSONObject2);
                    }
                    i6++;
                    i3 = 1;
                }
            }
            jSONObject.put("page", i);
            jSONObject.put("pagecount", i2);
            jSONObject.put("limit", 48);
            jSONObject.put("total", i2 <= 1 ? jSONArray.length() : i2 * 48);
            jSONObject.put("list", jSONArray);
            return jSONObject.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public String detailContent(List<String> list) {
        String str;
        Exception e;
        vR ue;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        String ue2;
        String jw;
        ex B1;
        String B;
        String B2;
        String B3;
        String B4;
        String str2;
        boolean z;
        Hq hq;
        Matcher matcher;
        try {
            String str3 = "https://www.libvio.me/detail/" + list.get(0) + ".html";
            ue = JD.ue(Vf.h(str3, W(str3)));
            jSONObject = new JSONObject();
            jSONObject2 = new JSONObject();
            ue2 = ue.B1("div.stui-content__thumb a img").ue("data-original");
            jw = ue.B1("div.stui-content__detail h1").jw();
            B1 = ue.B1("p.data");
            B = B(Pattern.compile("类型：(\\S+)"), ((Hq) B1.get(0)).pE());
            B2 = B(Pattern.compile("地区：(\\S+)"), ((Hq) B1.get(0)).pE());
            B3 = B(Pattern.compile("年份：(\\S+)"), ((Hq) B1.get(0)).pE());
            B4 = B(Pattern.compile("更新：(\\S+)"), ((Hq) B1.get(3)).pE());
            str = "";
        } catch (Exception e2) {
            e = e2;
            str = "";
        }
        try {
            String B5 = B(Pattern.compile("主演：(\\S+)"), ((Hq) B1.get(1)).pE());
            String B6 = B(Pattern.compile("导演：(\\S+)"), ((Hq) B1.get(1)).pE());
            String trim = ue.O("span.detail-content").pE().trim();
            jSONObject2.put("vod_id", list.get(0));
            jSONObject2.put("vod_name", jw);
            jSONObject2.put("vod_pic", ue2);
            jSONObject2.put("type_name", B);
            jSONObject2.put("vod_year", B3);
            jSONObject2.put("vod_area", B2);
            jSONObject2.put("vod_remarks", B4);
            jSONObject2.put("vod_actor", B5);
            jSONObject2.put("vod_director", B6);
            jSONObject2.put("vod_content", trim);
            TreeMap treeMap = new TreeMap((Comparator) new Lib$1(this));
            ex B12 = ue.B1("div.stui-vodlist__head h3");
            ex B13 = ue.B1("ul.stui-content__playlist");
            for (int i = 0; i < B12.size(); i++) {
                String pE = ((Hq) B12.get(i)).pE();
                Iterator<String> keys = this.ue.keys();
                while (true) {
                    if (!keys.hasNext()) {
                        str2 = pE;
                        z = false;
                        break;
                    }
                    str2 = keys.next();
                    if (this.ue.getJSONObject(str2).getString("sh").equals(pE)) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    ex B14 = ((Hq) B13.get(i)).B1("li a");
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < B14.size(); i2++) {
                        if (this.Pd.matcher(((Hq) B14.get(i2)).Pd("href")).find()) {
                            arrayList.add(hq.pE() + "$" + (matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3)));
                        }
                    }
                    String join = arrayList.size() > 0 ? TextUtils.join("#", arrayList) : str;
                    if (join.length() != 0) {
                        treeMap.put(str2, join);
                    }
                }
            }
            if (treeMap.size() > 0) {
                String join2 = TextUtils.join("$$$", treeMap.keySet());
                String join3 = TextUtils.join("$$$", treeMap.values());
                jSONObject2.put("vod_play_from", join2);
                jSONObject2.put("vod_play_url", join3);
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put("list", jSONArray);
            return jSONObject.toString();
        } catch (Exception e3) {
            e = e3;
            SpiderDebug.log(e);
            return str;
        }
    }

    public String homeContent(boolean z) {
        int i;
        try {
            vR ue = JD.ue(Vf.h("https://www.libvio.me/", W("https://www.libvio.me/")));
            ex B1 = ue.B1("ul.stui-header__menu li a");
            JSONArray jSONArray = new JSONArray();
            Iterator it = B1.iterator();
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                Hq hq = (Hq) it.next();
                String pE = hq.pE();
                if (pE.equals("电影") || pE.equals("剧集") || pE.equals("动漫") || pE.equals("日韩剧") || pE.equals("欧美剧")) {
                    i = 1;
                }
                if (i != 0) {
                    Matcher matcher = this.B.matcher(hq.Pd("href"));
                    if (matcher.find()) {
                        String trim = matcher.group(1).trim();
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("type_id", trim);
                        jSONObject.put("type_name", pE);
                        jSONArray.put(jSONObject);
                    }
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            if (z) {
                jSONObject2.put("filters", this.u);
            }
            jSONObject2.put("class", jSONArray);
            try {
                ex B12 = ((Hq) ue.B1("ul.stui-vodlist").get(0)).B1("div.stui-vodlist__box");
                JSONArray jSONArray2 = new JSONArray();
                while (i < B12.size()) {
                    Hq hq2 = (Hq) B12.get(i);
                    String ue2 = hq2.B1("a").ue("title");
                    String ue3 = hq2.B1("a").ue("data-original");
                    String jw = hq2.B1("a .pic-text").jw();
                    Matcher matcher2 = this.W.matcher(hq2.B1("a").ue("href"));
                    if (matcher2.find()) {
                        String group = matcher2.group(1);
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("vod_id", group);
                        jSONObject3.put("vod_name", ue2);
                        jSONObject3.put("vod_pic", ue3);
                        jSONObject3.put("vod_remarks", jw);
                        jSONArray2.put(jSONObject3);
                    }
                    i++;
                }
                jSONObject2.put("list", jSONArray2);
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
            return jSONObject2.toString();
        } catch (Exception e2) {
            SpiderDebug.log(e2);
            return "";
        }
    }

    public void init(Context context) {
        Lib.super.init(context);
        try {
            JSONObject jSONObject = new JSONObject(Vf.h("http://xn--sss604efuw.ml/tvb/json/Lib.json", (Map) null));
            this.ue = jSONObject.getJSONObject("player");
            this.u = jSONObject.getJSONObject("filter");
        } catch (JSONException e) {
            SpiderDebug.log(e);
        }
    }

    public String playerContent(String str, String str2, List<String> list) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("origin", " https://www.libvio.me/");
            jSONObject.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:91.0) Gecko/20100101 Firefox/91.0");
            jSONObject.put("Accept", " */*");
            jSONObject.put("Accept-Language", " zh-CN,zh;q=0.9,en-US;q=0.3,en;q=0.7");
            jSONObject.put("Accept-Encoding", " gzip, deflate");
            jSONObject.put("referer", " https://www.libvio.me/");
            String str3 = "https://www.libvio.me//play/" + str2 + ".html";
            ex B1 = JD.ue(Vf.h(str3, W(str3))).B1("script");
            int i = 0;
            while (true) {
                if (i >= B1.size()) {
                    break;
                }
                String trim = ((Hq) B1.get(i)).Wt().trim();
                if (trim.startsWith("var player_")) {
                    JSONObject jSONObject2 = new JSONObject(trim.substring(trim.indexOf(123), trim.lastIndexOf(125) + 1));
                    if (this.ue.has(jSONObject2.getString("from"))) {
                        JSONObject jSONObject3 = this.ue.getJSONObject(jSONObject2.getString("from"));
                        if (jSONObject2.getString("from").contains("LINE")) {
                            vR ue = JD.ue(Vf.h(jSONObject3.getString("pu") + jSONObject2.getString("url"), ue()));
                            Pattern compile = Pattern.compile("(?<=urls\\s=\\s').*?(?=')");
                            ex B12 = ue.B1("body script");
                            for (int i2 = 0; i2 < B12.size(); i2++) {
                                Matcher matcher = compile.matcher(((Hq) B12.get(i2)).Wt().trim());
                                if (matcher.find()) {
                                    JSONObject jSONObject4 = new JSONObject();
                                    String group = matcher.group(0);
                                    jSONObject4.put("parse", 0);
                                    jSONObject4.put("playUrl", "");
                                    jSONObject4.put("url", group);
                                    return jSONObject4.toString();
                                }
                            }
                        } else {
                            SpiderDebug.log(str3);
                            JSONObject jSONObject5 = new JSONObject();
                            jSONObject5.put("parse", 1);
                            jSONObject5.put("playUrl", "");
                            jSONObject5.put("url", str3);
                            return jSONObject5.toString();
                        }
                    }
                }
                i++;
            }
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    public String searchContent(String str, boolean z) {
        try {
            String str2 = "https://www.libvio.me//index.php/ajax/suggest?mid=1&wd=" + URLEncoder.encode(str) + "&limit=10&timestamp=" + System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject(Vf.h(str2, W(str2)));
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            if (jSONObject.getInt("total") > 0) {
                JSONArray jSONArray2 = new JSONArray(jSONObject.getString("list"));
                for (int i = 0; i < jSONArray2.length(); i++) {
                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i);
                    String string = jSONObject3.getString("id");
                    String string2 = jSONObject3.getString("name");
                    String string3 = jSONObject3.getString("pic");
                    if (!TextUtils.isEmpty(string3) && !string3.startsWith("http")) {
                        string3 = "https://www.libvio.me/" + string3;
                    }
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("vod_id", string);
                    jSONObject4.put("vod_name", string2);
                    jSONObject4.put("vod_pic", string3);
                    jSONObject4.put("vod_remarks", "");
                    jSONArray.put(jSONObject4);
                }
            }
            jSONObject2.put("list", jSONArray);
            return jSONObject2.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }
}
