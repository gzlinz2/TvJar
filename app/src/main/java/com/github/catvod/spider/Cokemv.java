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
import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Cokemv extends Spider {
    private JSONObject T;
    private JSONObject qE;
    private String e8 = "";
    private String az = "";
    private Pattern K = Pattern.compile("/vodtype/(\\d+).html");
    private Pattern iH = Pattern.compile("/voddetail/(\\d+).html");
    private Pattern n = Pattern.compile("/vodplay/(\\d+)-(\\d+)-(\\d+).html");
    private Pattern wN = Pattern.compile("/vodshow/(\\S+).html");

    protected HashMap<String, String> az(String str, String str2) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        if (!str2.equals("google")) {
            hashMap.put("Authority", "cokemv.me");
            if (str2.length() > 0) {
                if (str2.equals("origin")) {
                    hashMap.put("Origin", "https://cokemv.me");
                } else {
                    hashMap.put("Referer", str2);
                }
            }
            if (this.e8.length() > 0) {
                hashMap.put("Cookie", this.e8);
            }
        }
        hashMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        return hashMap;
    }

    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        int i;
        int i2;
        try {
            String[] strArr = {"", "", "", "", "", "", "", "", "", "", "", ""};
            strArr[0] = str;
            strArr[8] = str2;
            if (hashMap != null && hashMap.size() > 0) {
                for (String str3 : hashMap.keySet()) {
                    strArr[Integer.parseInt(str3)] = URLEncoder.encode(hashMap.get(str3));
                }
            }
            String str4 = "https://cokemv.me/vodshow/" + TextUtils.join("-", strArr) + ".html";
            System.out.println("url+++" + str4);
            String h = Vf.h(str4, az(str4, this.az));
            vR ue = JD.ue(h);
            JSONObject jSONObject = new JSONObject();
            ex B1 = ue.B1("div[id=page]");
            System.out.println("pg++" + B1);
            if (B1.size() == 0) {
                i2 = Integer.parseInt(str2);
                i = i2;
            } else {
                int i3 = 0;
                while (true) {
                    if (i3 >= B1.size()) {
                        i = -1;
                        break;
                    } else if (((Hq) B1.get(i3)).O("a") == null) {
                        i3++;
                    } else {
                        String jw = ue.B1("span.page-current").jw();
                        String Pd = ue.B1("div[id=page] a").W().Pd("href");
                        i = Integer.parseInt(jw);
                        Matcher matcher = this.wN.matcher(Pd);
                        if (matcher.find()) {
                            i2 = Integer.parseInt(matcher.group(1).split("-")[8]);
                        }
                    }
                }
                i2 = 0;
            }
            JSONArray jSONArray = new JSONArray();
            if (!h.contains("没有找到您想要的结果哦")) {
                ex B12 = ue.B1("div.module > a");
                System.out.print("list++f" + B12);
                for (int i4 = 0; i4 < B12.size(); i4++) {
                    Hq hq = (Hq) B12.get(i4);
                    String Pd2 = hq.Pd("title");
                    System.out.print("title++" + Pd2);
                    String Pd3 = hq.O("img.lazyload").Pd("data-original");
                    System.out.print("cover++" + Pd3);
                    String pE = hq.O("div.module-item-note").pE();
                    Matcher matcher2 = this.iH.matcher(hq.Pd("href"));
                    if (matcher2.find()) {
                        String group = matcher2.group(1);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("vod_id", group);
                        jSONObject2.put("vod_name", Pd2);
                        jSONObject2.put("vod_pic", Pd3);
                        jSONObject2.put("vod_remarks", pE);
                        jSONArray.put(jSONObject2);
                    }
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
        Exception e;
        String str = "-";
        String str2 = "";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://cokemv.me/voddetail/");
            int i = 0;
            sb.append(list.get(0));
            sb.append(".html");
            String sb2 = sb.toString();
            vR ue = JD.ue(Vf.h(sb2, az(sb2, this.az)));
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            String Pd = ue.O("div.module-item-cover div.module-item-pic > img").Pd("data-original");
            String pE = ue.O("div.module-info-main div.module-info-heading > h1").pE();
            String trim = ue.O("div.module-info-item div.module-info-introduction-content > p").pE().trim();
            String pE2 = ((Hq) ue.B1("div.module-info-main div.module-info-heading div.module-info-tag-link").get(2)).pE();
            String pE3 = ((Hq) ue.B1("div.module-info-main div.module-info-heading div.module-info-tag-link").get(1)).pE();
            String pE4 = ((Hq) ue.B1("div.module-info-main div.module-info-heading div.module-info-tag-link").get(0)).pE();
            ArrayList B1 = ue.B1("div.module-info-content div.module-info-items div.module-info-item");
            String str3 = str2;
            String str4 = str3;
            while (i < B1.size()) {
                try {
                    Hq hq = (Hq) B1.get(i);
                    String jw = hq.B1("span").jw();
                    str2 = str2;
                    if (jw.contains("导演")) {
                        try {
                            str3 = hq.B1("div > a").jw();
                        } catch (Exception unused) {
                            str3 = str2;
                        }
                    } else {
                        try {
                            if (jw.contains("主演")) {
                                try {
                                    str4 = hq.B1("div > a").jw();
                                } catch (Exception unused2) {
                                    str4 = str2;
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            SpiderDebug.log(e);
                            return str2;
                        }
                    }
                    i++;
                    B1 = B1;
                    str2 = str2;
                } catch (Exception e3) {
                    e = e3;
                    SpiderDebug.log(e);
                    return str2;
                }
            }
            str2 = str2;
            jSONObject2.put("vod_id", list.get(0));
            jSONObject2.put("vod_name", pE);
            jSONObject2.put("vod_pic", Pd);
            jSONObject2.put("type_name", pE2);
            jSONObject2.put("vod_year", pE4);
            jSONObject2.put("vod_area", pE3);
            jSONObject2.put("vod_actor", str4);
            jSONObject2.put("vod_director", str3);
            jSONObject2.put("vod_content", trim);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            ArrayList B12 = ue.B1("div.module-tab-items-box>div>span");
            PrintStream printStream = System.out;
            printStream.print("sor++" + B12);
            ex B13 = ue.B1("div.module-list>div.module-play-list");
            PrintStream printStream2 = System.out;
            printStream2.print("sor1++" + B13);
            int i2 = 0;
            while (i2 < B12.size()) {
                String pE5 = ((Hq) B12.get(i2)).pE();
                ex B14 = ((Hq) B13.get(i2)).B1("div.module-play-list-content>a");
                PrintStream printStream3 = System.out;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("pl++");
                str2 = str2;
                try {
                    sb3.append(str2);
                    printStream3.print(sb3.toString());
                    ArrayList arrayList = new ArrayList();
                    for (int i3 = 0; i3 < B14.size(); i3++) {
                        Hq hq2 = (Hq) B14.get(i3);
                        try {
                            Matcher matcher = this.n.matcher(hq2.Pd("href"));
                            if (!matcher.find()) {
                                B12 = B12;
                                str = str;
                            } else {
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append(matcher.group(1));
                                str = str;
                                sb4.append(str);
                                B12 = B12;
                                sb4.append(matcher.group(2));
                                sb4.append(str);
                                sb4.append(matcher.group(3));
                                String sb5 = sb4.toString();
                                arrayList.add(hq2.pE() + "$" + sb5);
                            }
                        } catch (Exception e4) {
                            e = e4;
                            SpiderDebug.log(e);
                            return str2;
                        }
                    }
                    String join = arrayList.size() > 0 ? TextUtils.join("#", arrayList) : str2;
                    if (join.length() != 0) {
                        linkedHashMap.put(pE5, join);
                    }
                    i2++;
                    B12 = B12;
                    str = str;
                    str2 = str2;
                } catch (Exception e5) {
                    e = e5;
                    SpiderDebug.log(e);
                    return str2;
                }
            }
            str2 = str2;
            if (linkedHashMap.size() > 0) {
                String join2 = TextUtils.join("$$$", linkedHashMap.keySet());
                String join3 = TextUtils.join("$$$", linkedHashMap.values());
                jSONObject2.put("vod_play_from", join2);
                jSONObject2.put("vod_play_url", join3);
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put("list", jSONArray);
            return jSONObject.toString();
        } catch (Exception e6) {
            e = e6;
        }
    }

    protected HashMap<String, String> e8(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("method", "GET");
        hashMap.put("Host", "cokemv.me");
        hashMap.put("Upgrade-Insecure-Requests", "1");
        hashMap.put("DNT", "1");
        hashMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        hashMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        hashMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        return hashMap;
    }

    public String homeContent(boolean z) {
        int i;
        try {
            vR ue = JD.ue(Vf.h("https://cokemv.me", az("https://cokemv.me", this.az)));
            ex B1 = ue.B1("ul.navbar-items>li.navbar-item>a");
            PrintStream printStream = System.out;
            printStream.println("shouye" + B1);
            JSONArray jSONArray = new JSONArray();
            Iterator it = B1.iterator();
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                Hq hq = (Hq) it.next();
                String pE = hq.pE();
                if (pE.equals("电影") || pE.equals("剧集") || pE.equals("综艺") || pE.equals("动漫")) {
                    i = 1;
                }
                if (i != 0) {
                    Matcher matcher = this.K.matcher(hq.Pd("href"));
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
                jSONObject2.put("filters", this.T);
            }
            jSONObject2.put("class", jSONArray);
            try {
                ex B12 = ((Hq) ue.B1("div.module-main").get(0)).B1("div.module-items>a");
                PrintStream printStream2 = System.out;
                printStream2.println("list..." + B12);
                JSONArray jSONArray2 = new JSONArray();
                while (i < B12.size()) {
                    Hq hq2 = (Hq) B12.get(i);
                    String Pd = hq2.Pd("title");
                    String Pd2 = hq2.O("img.lazyload").Pd("data-original");
                    String pE2 = hq2.O("div.module-item-note").pE();
                    Matcher matcher2 = this.iH.matcher(hq2.Pd("href"));
                    if (matcher2.find()) {
                        String group = matcher2.group(1);
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("vod_id", group);
                        jSONObject3.put("vod_name", Pd);
                        jSONObject3.put("vod_pic", Pd2);
                        jSONObject3.put("vod_remarks", pE2);
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
        Cokemv.super.init(context);
        try {
            this.qE = new JSONObject("{\"ddzy\":{\"sh\":\"藍光采集(暂时)\",\"pu\":\"\",\"sn\":0,\"or\":999},\"cokebb\":{\"sh\":\"極速路線\",\"pu\":\"\",\"sn\":0,\"or\":999},\"tkm3u8\":{\"sh\":\"采集路线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"777_\":{\"sh\":\"十三號線路\",\"pu\":\"\",\"sn\":0,\"or\":999},\"cokemv001\":{\"sh\":\"海外線路\",\"pu\":\"\",\"sn\":0,\"or\":999},\"mp4\":{\"sh\":\"藍光路线\",\"pu\":\"\",\"sn\":0,\"or\":999},\"cokemv\":{\"sh\":\"高速路線\",\"pu\":\"\",\"sn\":0,\"or\":999},\"if101\":{\"sh\":\"海外(禁國內)\",\"pu\":\"\",\"sn\":0,\"or\":999}}");
            this.T = new JSONObject("{\"5\":[{\"key\":3,\"name\":\"剧情\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"喜剧\",\"v\":\"喜劇\"},{\"n\":\"爱情\",\"v\":\"愛情\"},{\"n\":\"恐怖\",\"v\":\"恐怖\"},{\"n\":\"动作\",\"v\":\"動作\"},{\"n\":\"科幻\",\"v\":\"科幻\"},{\"n\":\"剧情\",\"v\":\"劇情\"},{\"n\":\"战争\",\"v\":\"戰爭\"},{\"n\":\"犯罪\",\"v\":\"犯罪\"},{\"n\":\"动画\",\"v\":\"動畫\"},{\"n\":\"奇幻\",\"v\":\"奇幻\"},{\"n\":\"悬疑\",\"v\":\"懸疑\"},{\"n\":\"微电影\",\"v\":\"微電影\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2022\",\"v\":\"2022\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"國語\"},{\"n\":\"英语\",\"v\":\"英語\"},{\"n\":\"粤语\",\"v\":\"粵語\"},{\"n\":\"闽南语\",\"v\":\"閩南語\"},{\"n\":\"韩语\",\"v\":\"韓語\"},{\"n\":\"日语\",\"v\":\"日語\"},{\"n\":\"法语\",\"v\":\"法語\"},{\"n\":\"德语\",\"v\":\"德語\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":5,\"name\":\"字母\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"A\",\"v\":\"A\"},{\"n\":\"B\",\"v\":\"B\"},{\"n\":\"C\",\"v\":\"C\"},{\"n\":\"D\",\"v\":\"D\"},{\"n\":\"E\",\"v\":\"E\"},{\"n\":\"F\",\"v\":\"F\"},{\"n\":\"G\",\"v\":\"G\"},{\"n\":\"H\",\"v\":\"H\"},{\"n\":\"I\",\"v\":\"I\"},{\"n\":\"J\",\"v\":\"J\"},{\"n\":\"K\",\"v\":\"K\"},{\"n\":\"L\",\"v\":\"L\"},{\"n\":\"M\",\"v\":\"M\"},{\"n\":\"N\",\"v\":\"N\"},{\"n\":\"O\",\"v\":\"O\"},{\"n\":\"P\",\"v\":\"P\"},{\"n\":\"Q\",\"v\":\"Q\"},{\"n\":\"R\",\"v\":\"R\"},{\"n\":\"S\",\"v\":\"S\"},{\"n\":\"T\",\"v\":\"T\"},{\"n\":\"U\",\"v\":\"U\"},{\"n\":\"V\",\"v\":\"V\"},{\"n\":\"W\",\"v\":\"W\"},{\"n\":\"X\",\"v\":\"X\"},{\"n\":\"Y\",\"v\":\"Y\"},{\"n\":\"Z\",\"v\":\"Z\"},{\"n\":\"0-9\",\"v\":\"0-9\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"2\":[{\"key\":0,\"name\":\"类型\",\"value\":[{\"n\":\"全部\",\"v\":\"2\"},{\"n\":\"大陆剧\",\"v\":\"13\"},{\"n\":\"香港剧\",\"v\":\"14\"},{\"n\":\"韩国剧\",\"v\":\"15\"},{\"n\":\"欧美剧\",\"v\":\"16\"},{\"n\":\"日本剧\",\"v\":\"20\"},{\"n\":\"台湾剧\",\"v\":\"21\"},{\"n\":\"泰国剧\",\"v\":\"22\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"中国大陆\",\"v\":\"中国大陆\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"中国香港\",\"v\":\"中国香港\"},{\"n\":\"中国台湾\",\"v\":\"中国台湾\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"泰国\",\"v\":\"泰国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"新加坡\",\"v\":\"新加坡\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2022\",\"v\":\"2022\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"},{\"n\":\"2009\",\"v\":\"2009\"},{\"n\":\"2008\",\"v\":\"2008\"},{\"n\":\"2007\",\"v\":\"2007\"},{\"n\":\"2006\",\"v\":\"2006\"},{\"n\":\"2005\",\"v\":\"2005\"},{\"n\":\"2004\",\"v\":\"2004\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":5,\"name\":\"字母\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"A\",\"v\":\"A\"},{\"n\":\"B\",\"v\":\"B\"},{\"n\":\"C\",\"v\":\"C\"},{\"n\":\"D\",\"v\":\"D\"},{\"n\":\"E\",\"v\":\"E\"},{\"n\":\"F\",\"v\":\"F\"},{\"n\":\"G\",\"v\":\"G\"},{\"n\":\"H\",\"v\":\"H\"},{\"n\":\"I\",\"v\":\"I\"},{\"n\":\"J\",\"v\":\"J\"},{\"n\":\"K\",\"v\":\"K\"},{\"n\":\"L\",\"v\":\"L\"},{\"n\":\"M\",\"v\":\"M\"},{\"n\":\"N\",\"v\":\"N\"},{\"n\":\"O\",\"v\":\"O\"},{\"n\":\"P\",\"v\":\"P\"},{\"n\":\"Q\",\"v\":\"Q\"},{\"n\":\"R\",\"v\":\"R\"},{\"n\":\"S\",\"v\":\"S\"},{\"n\":\"T\",\"v\":\"T\"},{\"n\":\"U\",\"v\":\"U\"},{\"n\":\"V\",\"v\":\"V\"},{\"n\":\"W\",\"v\":\"W\"},{\"n\":\"X\",\"v\":\"X\"},{\"n\":\"Y\",\"v\":\"Y\"},{\"n\":\"Z\",\"v\":\"Z\"},{\"n\":\"0-9\",\"v\":\"0-9\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"1\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"1\"},{\"n\":\"动作片\",\"v\":\"6\"},{\"n\":\"喜剧片\",\"v\":\"7\"},{\"n\":\"爱情片\",\"v\":\"8\"},{\"n\":\"科幻片\",\"v\":\"9\"},{\"n\":\"恐怖片\",\"v\":\"10\"},{\"n\":\"剧情片\",\"v\":\"11\"},{\"n\":\"战争片\",\"v\":\"12\"},{\"n\":\"犯罪片\",\"v\":\"23\"},{\"n\":\"奇幻片\",\"v\":\"24\"},{\"n\":\"悬疑片\",\"v\":\"25\"},{\"n\":\"记录片\",\"v\":\"27\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"中国大陆\",\"v\":\"中国大陆\"},{\"n\":\"中国香港\",\"v\":\"中国香港\"},{\"n\":\"中国台湾\",\"v\":\"中国台湾\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"法国\",\"v\":\"法国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"德国\",\"v\":\"德国\"},{\"n\":\"泰国\",\"v\":\"泰国\"},{\"n\":\"印度\",\"v\":\"印度\"},{\"n\":\"意大利\",\"v\":\"意大利\"},{\"n\":\"西班牙\",\"v\":\"西班牙\"},{\"n\":\"加拿大\",\"v\":\"加拿大\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2022\",\"v\":\"2022\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"法语\",\"v\":\"法语\"},{\"n\":\"德语\",\"v\":\"德语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":5,\"name\":\"字母\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"A\",\"v\":\"A\"},{\"n\":\"B\",\"v\":\"B\"},{\"n\":\"C\",\"v\":\"C\"},{\"n\":\"D\",\"v\":\"D\"},{\"n\":\"E\",\"v\":\"E\"},{\"n\":\"F\",\"v\":\"F\"},{\"n\":\"G\",\"v\":\"G\"},{\"n\":\"H\",\"v\":\"H\"},{\"n\":\"I\",\"v\":\"I\"},{\"n\":\"J\",\"v\":\"J\"},{\"n\":\"K\",\"v\":\"K\"},{\"n\":\"L\",\"v\":\"L\"},{\"n\":\"M\",\"v\":\"M\"},{\"n\":\"N\",\"v\":\"N\"},{\"n\":\"O\",\"v\":\"O\"},{\"n\":\"P\",\"v\":\"P\"},{\"n\":\"Q\",\"v\":\"Q\"},{\"n\":\"R\",\"v\":\"R\"},{\"n\":\"S\",\"v\":\"S\"},{\"n\":\"T\",\"v\":\"T\"},{\"n\":\"U\",\"v\":\"U\"},{\"n\":\"V\",\"v\":\"V\"},{\"n\":\"W\",\"v\":\"W\"},{\"n\":\"X\",\"v\":\"X\"},{\"n\":\"Y\",\"v\":\"Y\"},{\"n\":\"Z\",\"v\":\"Z\"},{\"n\":\"0-9\",\"v\":\"0-9\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"4\":[{\"key\":0,\"name\":\"分类\",\"value\":[{\"n\":\"全部\",\"v\":\"4\"},{\"n\":\"动画电影\",\"v\":\"41\"}]},{\"key\":3,\"name\":\"剧情\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"情感\",\"v\":\"情感\"},{\"n\":\"科幻\",\"v\":\"科幻\"},{\"n\":\"热血\",\"v\":\"热血\"},{\"n\":\"推理\",\"v\":\"推理\"},{\"n\":\"搞笑\",\"v\":\"搞笑\"},{\"n\":\"冒险\",\"v\":\"冒险\"},{\"n\":\"萝莉\",\"v\":\"萝莉\"},{\"n\":\"校园\",\"v\":\"校园\"},{\"n\":\"动作\",\"v\":\"动作\"},{\"n\":\"机战\",\"v\":\"机战\"},{\"n\":\"运动\",\"v\":\"运动\"},{\"n\":\"战争\",\"v\":\"战争\"},{\"n\":\"少年\",\"v\":\"少年\"},{\"n\":\"少女\",\"v\":\"少女\"},{\"n\":\"社会\",\"v\":\"社会\"},{\"n\":\"原创\",\"v\":\"原创\"},{\"n\":\"亲子\",\"v\":\"亲子\"},{\"n\":\"益智\",\"v\":\"益智\"},{\"n\":\"励志\",\"v\":\"励志\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"大陆\",\"v\":\"大陆\"},{\"n\":\"韩国\",\"v\":\"韩国\"},{\"n\":\"中国香港\",\"v\":\"中国香港\"},{\"n\":\"中国台湾\",\"v\":\"中国台湾\"},{\"n\":\"日本\",\"v\":\"日本\"},{\"n\":\"美国\",\"v\":\"美国\"},{\"n\":\"泰国\",\"v\":\"泰国\"},{\"n\":\"英国\",\"v\":\"英国\"},{\"n\":\"新加坡\",\"v\":\"新加坡\"},{\"n\":\"其他\",\"v\":\"其他\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2022\",\"v\":\"2022\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":5,\"name\":\"字母\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"A\",\"v\":\"A\"},{\"n\":\"B\",\"v\":\"B\"},{\"n\":\"C\",\"v\":\"C\"},{\"n\":\"D\",\"v\":\"D\"},{\"n\":\"E\",\"v\":\"E\"},{\"n\":\"F\",\"v\":\"F\"},{\"n\":\"G\",\"v\":\"G\"},{\"n\":\"H\",\"v\":\"H\"},{\"n\":\"I\",\"v\":\"I\"},{\"n\":\"J\",\"v\":\"J\"},{\"n\":\"K\",\"v\":\"K\"},{\"n\":\"L\",\"v\":\"L\"},{\"n\":\"M\",\"v\":\"M\"},{\"n\":\"N\",\"v\":\"N\"},{\"n\":\"O\",\"v\":\"O\"},{\"n\":\"P\",\"v\":\"P\"},{\"n\":\"Q\",\"v\":\"Q\"},{\"n\":\"R\",\"v\":\"R\"},{\"n\":\"S\",\"v\":\"S\"},{\"n\":\"T\",\"v\":\"T\"},{\"n\":\"U\",\"v\":\"U\"},{\"n\":\"V\",\"v\":\"V\"},{\"n\":\"W\",\"v\":\"W\"},{\"n\":\"X\",\"v\":\"X\"},{\"n\":\"Y\",\"v\":\"Y\"},{\"n\":\"Z\",\"v\":\"Z\"},{\"n\":\"0-9\",\"v\":\"0-9\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}],\"3\":[{\"key\":1,\"name\":\"地区\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"中国大陆\",\"v\":\"中国大陆\"},{\"n\":\"韩国\",\"v\":\"韩国\"}]},{\"key\":11,\"name\":\"年份\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"2022\",\"v\":\"2022\"},{\"n\":\"2021\",\"v\":\"2021\"},{\"n\":\"2020\",\"v\":\"2020\"},{\"n\":\"2019\",\"v\":\"2019\"},{\"n\":\"2018\",\"v\":\"2018\"},{\"n\":\"2017\",\"v\":\"2017\"},{\"n\":\"2016\",\"v\":\"2016\"},{\"n\":\"2015\",\"v\":\"2015\"},{\"n\":\"2014\",\"v\":\"2014\"},{\"n\":\"2013\",\"v\":\"2013\"},{\"n\":\"2012\",\"v\":\"2012\"},{\"n\":\"2011\",\"v\":\"2011\"},{\"n\":\"2010\",\"v\":\"2010\"},{\"n\":\"2009\",\"v\":\"2009\"},{\"n\":\"2008\",\"v\":\"2008\"},{\"n\":\"2007\",\"v\":\"2007\"},{\"n\":\"2006\",\"v\":\"2006\"},{\"n\":\"2005\",\"v\":\"2005\"},{\"n\":\"2004\",\"v\":\"2004\"}]},{\"key\":4,\"name\":\"语言\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"国语\",\"v\":\"国语\"},{\"n\":\"英语\",\"v\":\"英语\"},{\"n\":\"粤语\",\"v\":\"粤语\"},{\"n\":\"闽南语\",\"v\":\"闽南语\"},{\"n\":\"韩语\",\"v\":\"韩语\"},{\"n\":\"日语\",\"v\":\"日语\"},{\"n\":\"其它\",\"v\":\"其它\"}]},{\"key\":5,\"name\":\"字母\",\"value\":[{\"n\":\"全部\",\"v\":\"\"},{\"n\":\"A\",\"v\":\"A\"},{\"n\":\"B\",\"v\":\"B\"},{\"n\":\"C\",\"v\":\"C\"},{\"n\":\"D\",\"v\":\"D\"},{\"n\":\"E\",\"v\":\"E\"},{\"n\":\"F\",\"v\":\"F\"},{\"n\":\"G\",\"v\":\"G\"},{\"n\":\"H\",\"v\":\"H\"},{\"n\":\"I\",\"v\":\"I\"},{\"n\":\"J\",\"v\":\"J\"},{\"n\":\"K\",\"v\":\"K\"},{\"n\":\"L\",\"v\":\"L\"},{\"n\":\"M\",\"v\":\"M\"},{\"n\":\"N\",\"v\":\"N\"},{\"n\":\"O\",\"v\":\"O\"},{\"n\":\"P\",\"v\":\"P\"},{\"n\":\"Q\",\"v\":\"Q\"},{\"n\":\"R\",\"v\":\"R\"},{\"n\":\"S\",\"v\":\"S\"},{\"n\":\"T\",\"v\":\"T\"},{\"n\":\"U\",\"v\":\"U\"},{\"n\":\"V\",\"v\":\"V\"},{\"n\":\"W\",\"v\":\"W\"},{\"n\":\"X\",\"v\":\"X\"},{\"n\":\"Y\",\"v\":\"Y\"},{\"n\":\"Z\",\"v\":\"Z\"},{\"n\":\"0-9\",\"v\":\"0-9\"}]},{\"key\":2,\"name\":\"排序\",\"value\":[{\"n\":\"时间\",\"v\":\"time\"},{\"n\":\"人气\",\"v\":\"hits\"},{\"n\":\"评分\",\"v\":\"score\"}]}]}");
        } catch (JSONException e) {
            SpiderDebug.log(e);
        }
    }

    public String playerContent(String str, String str2, List<String> list) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("origin", " https://cokemv.me");
            jSONObject.put("User-Agent", " Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
            jSONObject.put("Accept", " */*");
            jSONObject.put("Accept-Language", " zh-CN,zh;q=0.9,en-US;q=0.3,en;q=0.7");
            jSONObject.put("Accept-Encoding", " gzip, deflate");
            String str3 = "https://cokemv.me/vodplay/" + str2 + ".html";
            ex B1 = JD.ue(Vf.h(str3, e8(str3))).B1("script");
            JSONObject jSONObject2 = new JSONObject();
            int i = 0;
            while (true) {
                if (i >= B1.size()) {
                    break;
                }
                String trim = ((Hq) B1.get(i)).Wt().trim();
                if (trim.startsWith("var player_")) {
                    JSONObject jSONObject3 = new JSONObject(trim.substring(trim.indexOf(123), trim.lastIndexOf(125) + 1));
                    if (this.qE.has(jSONObject3.getString("from"))) {
                        JSONObject jSONObject4 = this.qE.getJSONObject(jSONObject3.getString("from"));
                        String string = jSONObject3.getString("url");
                        String string2 = jSONObject4.getString("pu");
                        jSONObject2.put("parse", jSONObject4.getInt("sn"));
                        jSONObject2.put("playUrl", string2);
                        jSONObject2.put("url", string);
                        jSONObject2.put("header", jSONObject.toString());
                    }
                } else {
                    i++;
                }
            }
            return jSONObject2.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public String searchContent(String str, boolean z) {
        try {
            String str2 = "https://www.xn--flw351e.cf/search?q=site%3Acokemv.me+" + URLEncoder.encode(str);
            vR ue = JD.ue(Vf.h(str2, az(str2, "google")));
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            ex B1 = ue.B1("div.yuRUbf a");
            if (B1.size() > 0) {
                for (int i = 0; i < 1; i++) {
                    Hq hq = (Hq) B1.get(i);
                    String jw = hq.B1("h3.LC20lb.MBeuO.DKV0Md").jw();
                    String Pd = hq.Pd("href");
                    if (!Pd.contains("/s/") && !Pd.contains("play") && !Pd.contains("performer") && !Pd.contains("search") && !Pd.contains("jsessionid") && jw.contains(str)) {
                        vR ue2 = JD.ue(Vf.h(Pd, az(Pd, this.az)));
                        JSONObject jSONObject2 = new JSONObject();
                        Matcher matcher = this.iH.matcher(Pd);
                        if (matcher.find()) {
                            String Pd2 = ue2.O("div.module-item-cover div.module-item-pic > img").Pd("data-original");
                            String pE = ue2.O("div.module-info-main div.module-info-heading > h1").pE();
                            jSONObject2.put("vod_id", matcher.group(1));
                            jSONObject2.put("vod_name", pE);
                            jSONObject2.put("vod_remarks", "");
                            jSONObject2.put("vod_pic", Pd2);
                            jSONArray.put(jSONObject2);
                        }
                    }
                }
            }
            jSONObject.put("list", jSONArray);
            return jSONObject.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }
}
