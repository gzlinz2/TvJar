package com.github.catvod.spider;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.2345.AE;
import com.github.catvod.spider.2345.G;
import com.github.catvod.spider.2345.Hk;
import com.github.catvod.spider.2345.JV;
import com.github.catvod.spider.2345.Se;
import com.github.catvod.spider.2345.at;
import com.github.catvod.spider.2345.d;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

 
    protected JSONArray xs;
    public Integer j = 0;

    private String MM(String str) {
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        return str.substring(str.lastIndexOf("/") + 1).replace("/", "").replace(".html", "");
    }

    private String PA(String str) {
        try {
            String[] split = str.split("\\|");
            String str2 = split[0];
            String str3 = split[1];
            String str4 = "https:" + this.MM.getJSONObject("urls").getString(str2);
            if (!str2.equals("dianying") && !str2.equals("dianshiju")) {
                if (str2.equals("zongyi")) {
                    return str4 + str3 + "/";
                } else if (!str2.equals("dongman")) {
                    return str4;
                } else {
                    return str4 + "dm/" + str3 + ".html";
                }
            }
            return str4 + "detail/" + str3 + ".html";
        } catch (Exception unused) {
            return "";
        }
    }

    private String W(String str) {
        SpiderDebug.log(str);
        SP2345$1 r0 = new SP2345$1(this);
        G.PA(G.W(), str, (Map) null, getHeaders(str), r0);
        return (String) r0.getResult();
    }

    private HashMap<String, String> getHeaders(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0.1; Moto G (4)) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Mobile Safari/537.36");
        return hashMap;
    }

    private String j(String str, String str2, HashMap<String, String> hashMap) {
        String str3;
        String str4;
        String str5;
        try {
            String str6 = "https:" + this.MM.getJSONObject("urls").getString(str) + "mAjaxlist/";
            if (hashMap == null || hashMap.size() <= 0) {
                str3 = "";
                str5 = str3;
                str4 = str5;
            } else {
                str4 = hashMap.containsKey("type") ? hashMap.get("type") : "";
                str5 = hashMap.containsKey("year") ? hashMap.get("year") : "";
                str3 = hashMap.containsKey("area") ? hashMap.get("area") : "";
            }
            if (!str.equals("dianying") && !str.equals("dianshiju")) {
                if (str.equals("zongyi")) {
                    return str6 + "l" + str4 + str3 + "?page=" + str2;
                } else if (!str.equals("dongman")) {
                    return str6;
                } else {
                    return str6 + "lt" + str4 + str3 + str5 + "?page=" + str2;
                }
            }
            return (str6 + str4 + "-" + str3 + "-" + str5 + "-" + str2 + "?page=" + str2 + "").replace("tv.", "waptv.");
        } catch (Exception unused) {
            return "";
        }
    }

    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        String str3;
        if (this.j.intValue() == 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                String j = j(str, str2, hashMap);
                JV C2 = at.MM(new JSONObject(W(j)).getString("html")).C2("section.show li");
                JSONArray jSONArray = new JSONArray();
                Iterator it = C2.iterator();
                while (it.hasNext()) {
                    AE ae = (AE) it.next();
                    String MM = ae.C2("a.bd-img").MM("href");
                    String str4 = str + "|" + MM(MM);
                    String o4 = ae.C2(".title").o4();
                    String PA = d.PA(j, ae.C2("img").MM("src"));
                    try {
                        str3 = ae.C2("span.score em").o4();
                    } catch (Exception unused) {
                        str3 = "";
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("vod_id", str4);
                    jSONObject2.put("vod_name", o4);
                    jSONObject2.put("vod_pic", PA);
                    jSONObject2.put("vod_remarks", str3);
                    jSONArray.put(jSONObject2);
                }
                if (jSONArray.length() == 0) {
                    return "";
                }
                jSONObject.put("page", str2);
                jSONObject.put("pagecount", Integer.MAX_VALUE);
                jSONObject.put("limit", 90);
                jSONObject.put("total", Integer.MAX_VALUE);
                jSONObject.put("list", jSONArray);
                return jSONObject.toString();
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
        }
        return "";
    }

    public String detailContent(List<String> list) {
        ArrayList arrayList;
        SP2345 sp2345 = this;
        String str = "$$$";
        if (sp2345.j.intValue() == 0) {
            try {
                String PA = sp2345.PA(list.get(0));
                Hk MM = at.MM(sp2345.W(PA));
                JV C2 = MM.C2("div.posterPlaceholder");
                JV C22 = MM.C2("div.txtIntroCon");
                String trim = C22.m("div.tit>h1").o4().trim();
                String PA2 = d.PA(PA, C2.m("div.pic>img").MM("src"));
                ArrayList m = C22.m("em.emTit");
                String str2 = "";
                String str3 = str2;
                String str4 = str3;
                String str5 = str4;
                String str6 = str5;
                String str7 = str6;
                int i = 0;
                while (i < m.size()) {
                    AE ae = (AE) m.get(i);
                    String replaceAll = ae.h().replaceAll("s", "");
                    if (replaceAll.contains("类")) {
                        try {
                            str2 = ae.UR().h().replaceAll("s", "");
                        } catch (Exception unused) {
                        }
                    } else {
                        if (!replaceAll.contains("年") && !replaceAll.contains("年 份")) {
                            if (replaceAll.contains("演")) {
                                str5 = ae.UR().h().replaceAll("s", "");
                            } else if (replaceAll.contains("导")) {
                                str6 = ae.UR().h().replaceAll("s", "");
                            } else if (replaceAll.contains("地")) {
                                str4 = ae.UR().h().replaceAll("s", "");
                            } else if (replaceAll.contains("简")) {
                                str7 = ae.v().C2("p.pShow>span").o4().trim();
                            }
                        }
                        str3 = ae.UR().h().replaceAll("s", "");
                    }
                    i++;
                    m = m;
                    str = str;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("vod_id", list.get(0));
                jSONObject.put("vod_name", trim);
                jSONObject.put("vod_pic", PA2);
                jSONObject.put("type_name", str2);
                jSONObject.put("vod_year", str3);
                jSONObject.put("vod_area", str4);
                jSONObject.put("vod_remarks", "");
                jSONObject.put("vod_actor", str5);
                jSONObject.put("vod_director", str6);
                jSONObject.put("vod_content", str7);
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = list.get(0).trim().startsWith("zongyi|") ? new ArrayList() : null;
                JV m2 = C22.m("div.series-tab-left>a");
                for (int i2 = 0; i2 < m2.size(); i2++) {
                    AE ae2 = (AE) m2.get(i2);
                    if (arrayList3 != null) {
                        arrayList3.add(ae2.W("data-api"));
                    }
                    arrayList2.add(ae2.h().trim());
                }
                ArrayList arrayList4 = new ArrayList();
                if (arrayList3 != null) {
                    String replace = list.get(0).split("\\|")[1].replace("zy_", "");
                    ArrayList m3 = C22.m("div.series-tab-right-i");
                    int i3 = 0;
                    while (i3 < m3.size()) {
                        JV C23 = ((AE) m3.get(i3)).C2("div.series-tab-right-xiala-item");
                        ArrayList arrayList5 = new ArrayList();
                        int i4 = 0;
                        while (i4 < C23.size() && i4 < 5) {
                            try {
                                StringBuilder sb = new StringBuilder();
                                sb.append("https://kan.2345.com/episodeapi/newDetail/setNewEpisodeApi/?id=");
                                sb.append(replace);
                                sb.append("&api=");
                                sb.append(arrayList3.get(i3));
                                sb.append("&year=");
                                replace = replace;
                                try {
                                    sb.append(((AE) C23.get(i4)).W("data-year").trim());
                                    sb.append("&month=");
                                    sb.append(((AE) C23.get(i4)).W("data-month").trim());
                                    sb.append("&_=");
                                    arrayList4 = arrayList4;
                                    m3 = m3;
                                    try {
                                        sb.append(System.currentTimeMillis());
                                        String sb2 = sb.toString();
                                        JSONObject jSONObject2 = new JSONObject(G.A(sb2, sp2345.getHeaders(sb2)).substring(1));
                                        if (jSONObject2.getInt("code") == 200) {
                                            JSONArray jSONArray = jSONObject2.getJSONArray("data");
                                            int i5 = 0;
                                            while (i5 < jSONArray.length()) {
                                                JSONObject jSONObject3 = jSONArray.getJSONObject(i5);
                                                StringBuilder sb3 = new StringBuilder();
                                                arrayList3 = arrayList3;
                                                try {
                                                    sb3.append(jSONObject3.getString("issue"));
                                                    sb3.append("$");
                                                    sb3.append(jSONObject3.getString("url"));
                                                    arrayList5.add(sb3.toString());
                                                    i5++;
                                                    arrayList3 = arrayList3;
                                                } catch (Exception unused2) {
                                                }
                                            }
                                        }
                                    } catch (Exception unused3) {
                                    }
                                } catch (Exception unused4) {
                                    arrayList3 = arrayList3;
                                    arrayList4 = arrayList4;
                                    m3 = m3;
                                    i4++;
                                    sp2345 = this;
                                }
                            } catch (Exception unused5) {
                                replace = replace;
                            }
                            i4++;
                            sp2345 = this;
                        }
                        if (arrayList5.size() == 0 && arrayList2.size() > i3) {
                            arrayList2.set(i3, "");
                        }
                        arrayList4.add(TextUtils.join("#", arrayList5));
                        i3++;
                        sp2345 = this;
                        arrayList4 = arrayList4;
                        m3 = m3;
                        arrayList3 = arrayList3;
                        replace = replace;
                    }
                    arrayList = arrayList4;
                } else {
                    arrayList = arrayList4;
                    JV m4 = C22.m("div.series-con-i");
                    for (int i6 = 0; i6 < m4.size(); i6++) {
                        JV C24 = ((AE) m4.get(i6)).C2("a");
                        ArrayList arrayList6 = new ArrayList();
                        Iterator it = C24.iterator();
                        while (it.hasNext()) {
                            AE ae3 = (AE) it.next();
                            arrayList6.add(ae3.h().trim() + "$" + ae3.W("href").trim());
                        }
                        if (arrayList6.size() == 0 && arrayList2.size() > i6) {
                            arrayList2.set(i6, "");
                        }
                        arrayList.add(TextUtils.join("#", arrayList6));
                    }
                }
                for (int size = arrayList2.size() - 1; size >= 0; size--) {
                    if (((String) arrayList2.get(size)).isEmpty()) {
                        arrayList2.remove(size);
                    }
                }
                for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                    if (((String) arrayList.get(size2)).isEmpty()) {
                        arrayList.remove(size2);
                    }
                }
                for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    if (size3 >= arrayList2.size()) {
                        arrayList.remove(size3);
                    }
                }
                String join = TextUtils.join(str, arrayList2);
                String join2 = TextUtils.join(str, arrayList);
                jSONObject.put("vod_play_from", join);
                jSONObject.put("vod_play_url", join2);
                JSONObject jSONObject4 = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.put(jSONObject);
                jSONObject4.put("list", jSONArray2);
                return jSONObject4.toString();
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
        }
        return "";
    }

    public String homeContent(boolean z) {
        String str;
        if (this.j.intValue() != 0) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("class", this.MM.getJSONArray("classes"));
            if (z) {
                jSONObject.put("filters", this.MM.getJSONObject("filter"));
            }
            try {
                String str2 = "https:" + this.MM.getJSONObject("urls").getString("recommend");
                JV C2 = at.MM(W(str2)).C2("section.show li");
                JSONArray jSONArray = new JSONArray();
                Iterator it = C2.iterator();
                String str3 = null;
                while (it.hasNext()) {
                    AE ae = (AE) it.next();
                    String replace = ae.C2("a.bd-img").MM("href").replace("waptv.", "tv.");
                    JSONObject jSONObject2 = this.MM.getJSONObject("urls");
                    Iterator<String> keys = jSONObject2.keys();
                    while (true) {
                        if (keys.hasNext()) {
                            String next = keys.next();
                            if (replace.contains(jSONObject2.getString(next))) {
                                str = next + "|" + MM(replace);
                                break;
                            }
                        } else {
                            str = null;
                            break;
                        }
                    }
                    if (str != null) {
                        String o4 = ae.C2(".title").o4();
                        String PA = d.PA(str2, ae.C2("img").MM("src"));
                        try {
                            str3 = ae.C2("span.score em").o4();
                        } catch (Exception unused) {
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("vod_id", str);
                        jSONObject3.put("vod_name", o4);
                        jSONObject3.put("vod_pic", PA);
                        jSONObject3.put("vod_remarks", str3);
                        jSONArray.put(jSONObject3);
                    }
                }
                jSONObject.put("list", jSONArray);
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
            return jSONObject.toString();
        } catch (Exception unused2) {
            return "";
        }
    }
    
//---------------------------------------------------

    public void init(Context context) {
        IQIYI.super.init(context);
        try {
            this.xs = new JSONArray("[{\"type_name\":\"电影\",\"type_id\":\"dianying\"},{\"type_name\":\"电视剧\",\"type_id\":\"dianshiju\"},{\"type_name\":\"综艺\",\"type_id\":\"zongyi\"},{\"type_name\":\"动漫\",\"type_id\":\"dongman\"}]");
            
            this.a = new JSONObject(" { \"dianying\": [ { \"key\": \"type\", \"name\": \"类型\", \"value\": [ { \"n\": \"全部\", \"v\": \"\" }, { \"n\": \"爱情\", \"v\": \"aiqing\" },{ \"n\": \"动作\", \"v\": \"dongzuo\" },{ \"n\": \"喜剧\", \"v\": \"xiju\" },{ \"n\": \"战争\", \"v\": \"zhanzheng\" },{ \"n\": \"科幻\", \"v\": \"kehuan\" },{ \"n\": \"剧情\", \"v\": \"juqing\" },{ \"n\": \"武侠\", \"v\": \"wuxia\" },{ \"n\": \"冒险\", \"v\": \"maoxian\" },{ \"n\": \"枪战\", \"v\": \"qiangzhan\" },{ \"n\": \"恐怖\", \"v\": \"kongbu\" },{ \"n\": \"悬疑\", \"v\": \"xuanyi\" },{ \"n\": \"犯罪\", \"v\": \"fanzui\" },{ \"n\": \"奇幻\", \"v\": \"qihuan\" },{ \"n\": \"动画\", \"v\": \"donghua\" },{ \"n\": \"惊悚\", \"v\": \"jingsong\" },{ \"n\": \"经典\", \"v\": \"jingdian\" },{ \"n\": \"青春\", \"v\": \"qingchun\" },{ \"n\": \"文艺\", \"v\": \"wenyi\" },{ \"n\": \"微电影\", \"v\": \"weidianying\" },{ \"n\": \"古装\", \"v\": \"guzhuang\" },{ \"n\": \"历史\", \"v\": \"lishi\" },{ \"n\": \"运动\", \"v\": \"yundong\" },{ \"n\": \"农村\", \"v\": \"nongcun\" },{ \"n\": \"儿童\", \"v\": \"ertong\" },{ \"n\": \"网络电影\", \"v\": \"wangluodianying\" },{ \"n\": \"其它\", \"v\": \"qita\" }]},{ \"key\": \"area\", \"name\": \"地区\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"内地\", \"v\": \"neidi\" },{ \"n\": \"美国\", \"v\": \"meiguo\" },{ \"n\": \"香港\", \"v\": \"xianggang\" },{ \"n\": \"台湾\", \"v\": \"taiwan\" },{ \"n\": \"日本\", \"v\": \"riben\" },{ \"n\": \"泰国\", \"v\": \"taiguo\" },{ \"n\": \"印度\", \"v\": \"yindu\" },{ \"n\": \"东南亚地区\", \"v\": \"dongnanyadiqu\" },{ \"n\": \"欧美地区\", \"v\": \"oumeidiqu\" },{ \"n\": \"其它\", \"v\": \"qita\" }]},{ \"key\": \"year\", \"name\": \"年份\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"2021\", \"v\": \"2021\" },{ \"n\": \"2020\", \"v\": \"2020\" },{ \"n\": \"2019\", \"v\": \"2019\" },{ \"n\": \"2018\", \"v\": \"2018\" },{ \"n\": \"2017\", \"v\": \"2017\" },{ \"n\": \"2016\", \"v\": \"2016\" },{ \"n\": \"2015\", \"v\": \"2015\" },{ \"n\": \"2014\", \"v\": \"2014\" },{ \"n\": \"2013\", \"v\": \"2013\" },{ \"n\": \"2012\", \"v\": \"2012\" },{ \"n\": \"2011\", \"v\": \"2011\" },{ \"n\": \"2010-2000\", \"v\": \"20102000\" },{ \"n\": \"更早\", \"v\": \"19691800\" }]}],\"dianshiju\": [{ \"key\": \"type\", \"name\": \"类型\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"言情\", \"v\": \"yanqing\" },{ \"n\": \"偶像\", \"v\": \"ouxiang\" },{ \"n\": \"都市\", \"v\": \"dushi\" },{ \"n\": \"战争\", \"v\": \"zhanzheng\" },{ \"n\": \"悬疑\", \"v\": \"xuanyi\" },{ \"n\": \"伦理\", \"v\": \"lunli\" },{ \"n\": \"惊悚\", \"v\": \"jingsong\" },{ \"n\": \"网络剧\", \"v\": \"wangluoju\" },{ \"n\": \"警匪\", \"v\": \"jingfei\" },{ \"n\": \"古装\", \"v\": \"guzhuang\" },{ \"n\": \"武侠\", \"v\": \"wuxia\" },{ \"n\": \"神话\", \"v\": \"shenhua\" },{ \"n\": \"历史\", \"v\": \"lishi\" },{ \"n\": \"动作\", \"v\": \"dongzuo\" },{ \"n\": \"谍战\", \"v\": \"diezhan\" },{ \"n\": \"喜剧\", \"v\": \"xiju\" },{ \"n\": \"抗日\", \"v\": \"kangri\" },{ \"n\": \"家庭\", \"v\": \"jiating\" },{ \"n\": \"剧情\", \"v\": \"juqing\" },{ \"n\": \"励志\", \"v\": \"lizhi\" },{ \"n\": \"农村\", \"v\": \"nongcun\" },{ \"n\": \"科幻\", \"v\": \"kehuan\" },{ \"n\": \"军旅\", \"v\": \"junlv\" },{ \"n\": \"奇幻\", \"v\": \"qihuan\" },{ \"n\": \"其它\", \"v\": \"qita\" }]},{ \"key\": \"area\", \"name\": \"地区\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"内地\", \"v\": \"neidi\" },{ \"n\": \"台湾\", \"v\": \"taiwan\" },{ \"n\": \"香港\", \"v\": \"xianggang\" },{ \"n\": \"韩国\", \"v\": \"hanguo\" },{ \"n\": \"美国\", \"v\": \"meiguo\" },{ \"n\": \"泰国\", \"v\": \"taiguo\" },{ \"n\": \"日本\", \"v\": \"riben\" },{ \"n\": \"英国\", \"v\": \"yingguo\" },{ \"n\": \"新加坡\", \"v\": \"xinjiapo\" },{ \"n\": \"其它\", \"v\": \"qita\" }]},{ \"key\": \"year\", \"name\": \"年份\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"2021\", \"v\": \"2021\" },{ \"n\": \"2020\", \"v\": \"2020\" },{ \"n\": \"2019\", \"v\": \"2019\" },{ \"n\": \"2018\", \"v\": \"2018\" },{ \"n\": \"2017\", \"v\": \"2017\" },{ \"n\": \"2016\", \"v\": \"2016\" },{ \"n\": \"2015\", \"v\": \"2015\" },{ \"n\": \"2014\", \"v\": \"2014\" },{ \"n\": \"2013\", \"v\": \"2013\" },{ \"n\": \"2012\", \"v\": \"2012\" },{ \"n\": \"2011\", \"v\": \"2011\" },{ \"n\": \"2010-2000\", \"v\": \"20102000\" },{ \"n\": \"90年代\", \"v\": \"19991990\" },{ \"n\": \"更早\", \"v\": \"19891800\" }]}],\"zongyi\": [{ \"key\": \"type\", \"name\": \"类型\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"选秀\", \"v\": \"lxxuanxiu/\" },{ \"n\": \"情感\", \"v\": \"lxqinggan/\" },{ \"n\": \"访谈\", \"v\": \"lxfangtan/\" },{ \"n\": \"搞笑\", \"v\": \"lxgaoxiao/\" },{ \"n\": \"游戏\", \"v\": \"lxyouxi/\" },{ \"n\": \"职场\", \"v\": \"lxzhichang/\" },{ \"n\": \"娱乐\", \"v\": \"lxyule/\" },{ \"n\": \"真人秀\", \"v\": \"lxzhenrenxiu/\" },{ \"n\": \"生活\", \"v\": \"lxshenghuo/\" },{ \"n\": \"体育\", \"v\": \"lxtiyu/\" },{ \"n\": \"盛会\", \"v\": \"lxshenghui/\" },{ \"n\": \"财经\", \"v\": \"lxcaijing/\" },{ \"n\": \"亲子\", \"v\": \"lxqinzi/\" },{ \"n\": \"纪实\", \"v\": \"lxjishi/\" },{ \"n\": \"脱口秀\", \"v\": \"lxtuokouxiu/\" },{ \"n\": \"网络节目\", \"v\": \"lxwangluojiemu/\" },{ \"n\": \"美食\", \"v\": \"lxmeishi/\" }]},{ \"key\": \"area\", \"name\": \"地区\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"大陆\", \"v\": \"dqdalu/\" },{ \"n\": \"港台\", \"v\": \"dqgangtai/\" },{ \"n\": \"日韩\", \"v\": \"dqrihan/\" },{ \"n\": \"欧美\", \"v\": \"dqoumei/\" },{ \"n\": \"其他\", \"v\": \"dqqita/\" }]}],\"dongman\": [{ \"key\": \"type\", \"name\": \"类型\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"热血\", \"v\": \"lxrexue/\" },{ \"n\": \"爱情\", \"v\": \"lxaiqing/\" },{ \"n\": \"搞笑\", \"v\": \"lxgaoxiao/\" },{ \"n\": \"美女\", \"v\": \"lxmeinv/\" },{ \"n\": \"少儿\", \"v\": \"lxshaoer/\" },{ \"n\": \"亲子\", \"v\": \"lxqinzi/\" },{ \"n\": \"魔法\", \"v\": \"lxmofa/\" },{ \"n\": \"运动\", \"v\": \"lxyundong/\" },{ \"n\": \"机战\", \"v\": \"lxjizhan/\" },{ \"n\": \"科幻\", \"v\": \"lxkehuan/\" },{ \"n\": \"校园\", \"v\": \"lxxiaoyuan/\" },{ \"n\": \"动物\", \"v\": \"lxdongwu/\" },{ \"n\": \"冒险\", \"v\": \"lxmaoxian/\" },{ \"n\": \"神话\", \"v\": \"lxshenhua/\" },{ \"n\": \"推理\", \"v\": \"lxtuili/\" },{ \"n\": \"剧情\", \"v\": \"lxjuqing/\" },{ \"n\": \"历史\", \"v\": \"lxlishi/\" },{ \"n\": \"战争\", \"v\": \"lxzhanzheng/\" },{ \"n\": \"经典\", \"v\": \"lxjingdian/\" },{ \"n\": \"男性向\", \"v\": \"lxnanxingxiang/\" },{ \"n\": \"女性向\", \"v\": \"lxnvxingxiang/\" }]},{ \"key\": \"area\", \"name\": \"地区\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"大陆\", \"v\": \"dqdalu/\" },{ \"n\": \"日本\", \"v\": \"dqriben/\" },{ \"n\": \"美国\", \"v\": \"dqmeiguo/\" },{ \"n\": \"韩国\", \"v\": \"dqhanguo/\" },{ \"n\": \"其他\", \"v\": \"dqqita/\" }]},{ \"key\": \"year\", \"name\": \"年份\", \"value\": [{ \"n\": \"全部\", \"v\": \"\" },{ \"n\": \"2021\", \"v\": \"nd2021/\" },{ \"n\": \"2020\", \"v\": \"nd2020/\" },{ \"n\": \"2019\", \"v\": \"nd2019/\" },{ \"n\": \"2018\", \"v\": \"nd2018/\" },{ \"n\": \"2017\", \"v\": \"nd2017/\" },{ \"n\": \"2016\", \"v\": \"nd2016/\" },{ \"n\": \"2015\", \"v\": \"nd2015/\" },{ \"n\": \"2014\", \"v\": \"nd2014/\" },{ \"n\": \"2013\", \"v\": \"nd2013/\" },{ \"n\": \"2012\", \"v\": \"nd2012/\" },{ \"n\": \"2011\", \"v\": \"nd2011/\" },{ \"n\": \"2010-2000\", \"v\": \"nd20102000/\" },{ \"n\": \"更早\", \"v\": \"nd19991800/\" }]}");
            
        } catch (JSONException e) {
            SpiderDebug.log(e);
        }
    }

    public String join(@NonNull CharSequence charSequence, @NonNull Iterable iterable) {
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(it.next());
        while (it.hasNext()) {
            sb.append(charSequence);
            sb.append(it.next());
        }
        return sb.toString();
    }


/*
    public void init(Context context, String str) {
        SP2345.super.init(context, str);
        try {
            if (str.trim().startsWith("mao-")) {
                str = Se.A(str.replace("mao-", ""));
                try {
                    str = new String(Base64.decode(str, 2), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (str.trim().startsWith("http")) {
                this.MM = new JSONObject(Se.t(G.A(str, (Map) null)));
            } else {
                this.MM = new JSONObject(Se.t(str));
            }
        } catch (Exception e2) {
            SpiderDebug.log(e2);
        }
    }
    */
//---------------------------------------------------

    public String playerContent(String str, String str2, List<String> list) {
        if (this.j.intValue() == 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("parse", 1);
                jSONObject.put("playUrl", "");
                jSONObject.put("jx", "1");
                jSONObject.put("url", str2.replace("\\/", "/"));
                return jSONObject.toString();
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
        }
        return "";
    }

    public String searchContent(String str, boolean z) {
        String str2;
        if (this.j.intValue() == 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                String str3 = "https:" + this.MM.getJSONObject("urls").getString("search").replace("{wd}", URLEncoder.encode(str, "utf-8"));
                JSONArray jSONArray = new JSONArray(W(str3));
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String trim = jSONObject2.getString("title").trim();
                    String replace = jSONObject2.getString("xqurl").trim().replace("waptv.", "tv.");
                    JSONObject jSONObject3 = this.MM.getJSONObject("urls");
                    Iterator<String> keys = jSONObject3.keys();
                    while (true) {
                        if (!keys.hasNext()) {
                            str2 = null;
                            break;
                        }
                        String next = keys.next();
                        if (replace.contains(jSONObject3.getString(next))) {
                            str2 = next + "|" + MM(replace);
                            break;
                        }
                    }
                    if (str2 != null) {
                        String PA = d.PA(str3, jSONObject2.optString("img").trim());
                        if (!trim.isEmpty() && trim.contains(str)) {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("vod_id", str2);
                            jSONObject4.put("vod_name", trim);
                            jSONObject4.put("vod_pic", PA);
                            jSONObject4.put("vod_remarks", "");
                            jSONArray2.put(jSONObject4);
                        }
                    }
                }
                jSONObject.put("list", jSONArray2);
                return jSONObject.toString();
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
