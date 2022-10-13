package com.github.catvod.spider;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.merge.Ok;
import com.github.catvod.spider.merge.RC;
import com.github.catvod.spider.merge.Vf;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class XBiu extends Spider {
    protected String m = null;
    public JSONObject M = null;
    private ArrayList<String> Q = new ArrayList<>(Arrays.asList(".m3u8", ".mp4", ".mpeg", ".flv", ".mkv"));
    protected final int r9 = 10;
    protected final ArrayList<String> cG = new ArrayList<>(Arrays.asList("电影", "剧集", "电视剧", "连续剧", "综艺", "动漫"));
    protected final ArrayList<String> km = new ArrayList<>(Arrays.asList("更多", "下载", "首页", "资讯", "留言", "导航", "专题", "短视频", "热榜", "排行", "追剧", "更新", "APP", "直播", "label", "Netflix"));
    protected final ArrayList<String> gw = new ArrayList<>(Arrays.asList("导演", "主演", "演员", "地区", "类型", "年份", "年代"));
    protected final ArrayList<String> sL = new ArrayList<>(Arrays.asList("vod_director", "vod_actor", "vod_actor", "vod_area", "type_name", "vod_year", "vod_year"));
    protected String W = "";

    /* loaded from: classes.dex */
    public class HtmlMatchInfo {
        public String M;
        public String Q;
        public int W;
        public int cG;
        public ArrayList<Integer> gw;
        public int kk;
        public int km;
        public String m;
        public String r9;
        public int sL = -1;

        protected HtmlMatchInfo() {
            XBiu.this = r1;
        }

        public boolean findDiffStr(HtmlMatchInfo htmlMatchInfo, String str) {
            int min = Math.min(this.M.length(), htmlMatchInfo.M.length());
            for (int i = 0; i < min; i++) {
                char charAt = this.M.charAt(i);
                char charAt2 = htmlMatchInfo.M.charAt(i);
                if (charAt == charAt2 && str.indexOf(charAt) != -1) {
                    int i2 = i + 1;
                    this.W = i2;
                    htmlMatchInfo.W = i2;
                }
                if (charAt != charAt2) {
                    break;
                }
            }
            this.kk = this.M.length();
            htmlMatchInfo.kk = htmlMatchInfo.M.length();
            for (int i3 = 1; i3 < min; i3++) {
                String str2 = this.M;
                char charAt3 = str2.charAt(str2.length() - i3);
                String str3 = htmlMatchInfo.M;
                char charAt4 = str3.charAt(str3.length() - i3);
                if (charAt3 == charAt4 && str.indexOf(charAt3) != -1) {
                    this.kk = this.M.length() - i3;
                    htmlMatchInfo.kk = htmlMatchInfo.M.length() - i3;
                }
                if (charAt3 != charAt4) {
                    break;
                }
            }
            String str4 = this.r9;
            if (str4 != null && (!str4.isEmpty() || this.W >= this.kk)) {
                int i4 = this.kk;
                int i5 = this.W;
                if (i4 < i5 || !this.r9.equals(this.M.substring(i5, i4))) {
                    return false;
                }
            } else {
                this.r9 = this.M.substring(this.W, this.kk);
            }
            int i6 = htmlMatchInfo.W;
            int i7 = htmlMatchInfo.kk;
            if (i6 < i7) {
                htmlMatchInfo.r9 = htmlMatchInfo.M.substring(i6, i7);
            }
            return true;
        }

        public void init(Matcher matcher) {
            this.m = matcher.group(0);
            if (matcher.groupCount() > 0) {
                this.M = matcher.group(1);
            }
            if (matcher.groupCount() > 1) {
                this.Q = matcher.group(2);
            }
            this.cG = matcher.start(0);
            this.km = matcher.end(0);
        }

        boolean m(HtmlMatchInfo htmlMatchInfo) {
            if (htmlMatchInfo.gw.size() != this.gw.size()) {
                return false;
            }
            for (int i = 0; i < this.gw.size(); i++) {
                if (this.gw.get(i).intValue() == htmlMatchInfo.gw.get(i).intValue()) {
                    if (this.sL != -1 && this.gw.get(i).intValue() != this.sL) {
                        return false;
                    }
                    this.sL = this.gw.get(i).intValue();
                    htmlMatchInfo.sL = this.gw.get(i).intValue();
                    return true;
                }
            }
            return false;
        }
    }

    protected String Dr(String str) {
        try {
            Object kk = kk(new JSONObject(str));
            if (kk == null) {
                return "";
            }
            JSONArray jSONArray = new JSONArray();
            if (kk.getClass().getSimpleName().equals("JSONObject")) {
                jSONArray.put(kk);
            } else {
                jSONArray = (JSONArray) kk;
            }
            JSONObject optJSONObject = this.M.optJSONObject("search");
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (optJSONObject.has("vod_id") && jSONObject2.has(optJSONObject.getString("vod_id"))) {
                    jSONObject.put("vod_id", jSONObject2.get(optJSONObject.getString("vod_id")).toString());
                    if (optJSONObject.has("vod_name") && jSONObject2.has(optJSONObject.getString("vod_name"))) {
                        jSONObject.put("vod_name", jSONObject2.get(optJSONObject.getString("vod_name")).toString());
                    } else {
                        jSONObject.put("vod_name", "未知");
                    }
                    if (optJSONObject.has("vod_pic") && jSONObject2.has(optJSONObject.getString("vod_pic"))) {
                        jSONObject.put("vod_pic", jSONObject2.get(optJSONObject.getString("vod_pic")).toString());
                    } else {
                        jSONObject.put("vod_pic", "");
                    }
                    if (optJSONObject.has("vod_remarks") && jSONObject2.has(optJSONObject.getString("vod_remarks"))) {
                        jSONObject.put("vod_remarks", jSONObject2.get(optJSONObject.getString("vod_remarks")).toString());
                    } else {
                        jSONObject.put("vod_remarks", "");
                    }
                    jSONObject.put("vod_id", Base64.encodeToString(jSONObject.toString().getBytes(RC.u), 10));
                    jSONArray2.put(jSONObject);
                }
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("list", jSONArray2);
            return jSONObject3.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    protected String J(String str, String str2, List<String> list) {
        int i;
        try {
            JSONObject optJSONObject = this.M.optJSONObject("play");
            if (optJSONObject == null) {
                return "";
            }
            String region = Utils.getRegion(Q(str2, optJSONObject.optJSONObject("header")), optJSONObject);
            JSONArray lookbackArray = Utils.getLookbackArray(optJSONObject);
            if (lookbackArray == null || (i = region.indexOf(lookbackArray.getString(0), 0)) == -1) {
                i = 0;
            } else {
                ArrayList<Integer> findUpNodes = HtmlNodeHlper.findUpNodes(region, i - 1, lookbackArray.getInt(4));
                if (findUpNodes.size() > 0) {
                    i = findUpNodes.get(findUpNodes.size() - 1).intValue();
                }
            }
            String replace = Utils.findSubString(region, i, optJSONObject.optJSONArray("vod_url")).replace("\\/", "/");
            if (!replace.isEmpty() && isVideoFormat(replace)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("parse", 0);
                jSONObject.put("playUrl", "");
                jSONObject.put("url", replace);
                return jSONObject.toString();
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void JG(List<String> list, JSONObject jSONObject, String str) {
        JSONArray guess_rule_vod_play_url;
        try {
            M();
            JSONObject jSONObject2 = new JSONObject(new String(Base64.decode(list.get(0), 10), "UTF-8"));
            JSONObject optJSONObject = this.M.optJSONObject("playlist");
            if (optJSONObject == null) {
                return;
            }
            if (optJSONObject.has("url") && !this.M.getJSONObject("detail").optString("url").equals(optJSONObject.getString("url"))) {
                str = Q(optJSONObject.getString("url").replace("{vid}", jSONObject2.getString("vod_id")), optJSONObject.optJSONObject("header"));
            }
            String region = Utils.getRegion(str, optJSONObject);
            if (!optJSONObject.has("vod_play_url") && (guess_rule_vod_play_url = guess_rule_vod_play_url(region, jSONObject2.getString("vod_id"))) != null) {
                optJSONObject.put("vod_play_url", guess_rule_vod_play_url);
            }
            ArrayList<String> findVodPlayUrl = findVodPlayUrl(region);
            ArrayList<String> findVodPlayFrom = findVodPlayFrom(region, findVodPlayUrl.size());
            if (!TextUtils.join("$$$", findVodPlayFrom).equals(TextUtils.join("$$$", makeVodPlayFrom(findVodPlayUrl.size())))) {
                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<String> arrayList2 = new ArrayList<>();
                JSONArray jSONArray = optJSONObject.getJSONArray("vod_play_from");
                for (int i = 0; i < jSONArray.length(); i++) {
                    String simpleName = jSONArray.get(i).getClass().getSimpleName();
                    String str2 = "";
                    if (simpleName.equals("String")) {
                        str2 = jSONArray.getString(i);
                    } else if (simpleName.equals("JSONArray")) {
                        JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                        str2 = jSONArray2.getString(0);
                        if (jSONArray2.length() > 1) {
                            str2 = jSONArray2.getString(1);
                        }
                    }
                    for (int i2 = 0; i2 < findVodPlayFrom.size(); i2++) {
                        if (findVodPlayFrom.get(i2).equals(str2)) {
                            arrayList.add(findVodPlayUrl.get(i2));
                            arrayList2.add(findVodPlayFrom.get(i2));
                        }
                    }
                }
                findVodPlayUrl = arrayList;
                findVodPlayFrom = arrayList2;
            }
            jSONObject.put("vod_play_url", TextUtils.join("$$$", findVodPlayUrl));
            jSONObject.put("vod_play_from", TextUtils.join("$$$", findVodPlayFrom));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void M() {
        String str;
        JSONArray optJSONArray;
        if (this.M != null || (str = this.m) == null) {
            return;
        }
        try {
            if (str.startsWith("http")) {
                if (this.m.indexOf("{cateId}") == -1 && this.m.indexOf("{catePg}") == -1) {
                    this.M = new JSONObject(Vf.h(this.m, null));
                }
                JSONObject jSONObject = new JSONObject();
                this.M = jSONObject;
                jSONObject.put("homeUrl", this.m);
            } else {
                this.M = new JSONObject(this.m);
            }
            if (!this.M.has("list")) {
                this.M.put("list", new JSONObject());
            }
            JSONObject jSONObject2 = this.M.getJSONObject("list");
            String string = this.M.getString("homeUrl");
            if (string.indexOf("{cateId}") != -1) {
                this.M.put("homeUrl", string.substring(0, string.indexOf(new URL(string).getPath())));
                if (!jSONObject2.has("url")) {
                    jSONObject2.put("url", string);
                }
            }
            String string2 = jSONObject2.getString("url");
            if (string2.indexOf("/") != -1) {
                this.W += '/';
            }
            if (string2.indexOf(".") != -1) {
                this.W += '.';
            }
            if (string2.indexOf("-") != -1) {
                this.W += '-';
            }
            if (!this.M.has("detail")) {
                this.M.put("detail", new JSONObject());
            }
            if (!this.M.has("playlist")) {
                this.M.put("playlist", new JSONObject());
            }
            if (!this.M.has("search")) {
                String addHttpPrefix = addHttpPrefix("index.php/ajax/suggest?mid=1&wd=阿凡达");
                try {
                    new JSONObject(Vf.h(addHttpPrefix, r9(addHttpPrefix)));
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("vod_id", "id");
                    jSONObject3.put("vod_name", "name");
                    jSONObject3.put("vod_pic", "pic");
                    jSONObject3.put("url", addHttpPrefix("index.php/ajax/suggest?mid=1&wd={wd}"));
                    this.M.put("search", jSONObject3);
                } catch (Exception unused) {
                }
            }
            if (!this.M.has("play")) {
                JSONObject jSONObject4 = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                jSONArray.put("var player_aaaa=");
                jSONArray.put(0);
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.put("\"url\":\"");
                jSONArray2.put("\"");
                jSONObject4.put("region", jSONArray);
                jSONObject4.put("vod_url", jSONArray2);
                this.M.put("play", jSONObject4);
            }
            if (this.M.has("play") && (optJSONArray = this.M.getJSONObject("play").optJSONArray("keywords")) != null) {
                this.Q.clear();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    this.Q.add(optJSONArray.getString(i));
                }
            }
            String str2 = "";
            if (this.M.optJSONObject("cateManual") == null) {
                String Q = Q(this.M.getString("homeUrl"), this.M.optJSONObject("header"));
                if (Q.length() > 32768) {
                    Q = Q.substring(0, 32768);
                }
                str2 = Q;
                JSONObject gw = gw(str2);
                if (gw != null) {
                    this.M.put("cateManual", gw);
                }
            }
            if (!jSONObject2.has("vod_id")) {
                if (str2.isEmpty()) {
                    String Q2 = Q(this.M.getString("homeUrl"), this.M.optJSONObject("header"));
                    if (Q2.length() > 32768) {
                        Q2 = Q2.substring(0, 32768);
                    }
                    str2 = Q2;
                }
                jSONObject2.put("vod_id", guess_rule_vod_id(str2));
            }
            if (!this.M.has("search")) {
                if (str2.isEmpty()) {
                    str2 = Q(this.M.getString("homeUrl"), this.M.optJSONObject("header"));
                    if (str2.length() > 32768) {
                        str2 = str2.substring(0, 32768);
                    }
                }
                String sL = sL(str2);
                if (!sL.isEmpty()) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("url", sL);
                    this.M.put("search", jSONObject5);
                }
            }
            SpiderDebug.log(String.format("默认rule: %s", this.M.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String Q(String str, JSONObject jSONObject) {
        return W(str, Vf.h(str, r9(str)), jSONObject).replaceAll("<!--.+?-->", "").replace("\r\n", "").replace("\n", "");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0065 A[Catch: Exception -> 0x00c2, TryCatch #0 {Exception -> 0x00c2, blocks: (B:40:0x0006, B:43:0x0010, B:45:0x0016, B:47:0x001e, B:49:0x0024, B:50:0x005f, B:52:0x0065, B:54:0x0079, B:56:0x0087, B:58:0x009b, B:59:0x00a5, B:60:0x00b0, B:61:0x00b5, B:63:0x00bb), top: B:69:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.String W(java.lang.String r8, java.lang.String r9, org.json.JSONObject r10) {
        /*
            r7 = this;
            java.lang.String r0 = "检测中"
            java.lang.String r1 = "header"
            java.lang.String r2 = "btwaf"
            org.json.JSONObject r3 = r7.M     // Catch: java.lang.Exception -> Lc2
            r4 = 0
            boolean r3 = r3.optBoolean(r2, r4)     // Catch: java.lang.Exception -> Lc2
            if (r3 != 0) goto L10
            return r9
        L10:
            boolean r3 = r9.contains(r0)     // Catch: java.lang.Exception -> Lc2
            if (r3 == 0) goto Lb5
            java.lang.String r3 = "跳转中"
            boolean r3 = r9.contains(r3)     // Catch: java.lang.Exception -> Lc2
            if (r3 == 0) goto Lb5
            boolean r3 = r9.contains(r2)     // Catch: java.lang.Exception -> Lc2
            if (r3 == 0) goto Lb5
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch: java.lang.Exception -> Lc2
            r3.<init>()     // Catch: java.lang.Exception -> Lc2
            java.lang.String r5 = "btwaf="
            r3.put(r5)     // Catch: java.lang.Exception -> Lc2
            java.lang.String r5 = "\""
            r3.put(r5)     // Catch: java.lang.Exception -> Lc2
            java.lang.String r3 = com.github.catvod.spider.XBiu.Utils.findSubString(r9, r4, r3)     // Catch: java.lang.Exception -> Lc2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc2
            r4.<init>()     // Catch: java.lang.Exception -> Lc2
            r4.append(r8)     // Catch: java.lang.Exception -> Lc2
            java.lang.String r5 = "?btwaf="
            r4.append(r5)     // Catch: java.lang.Exception -> Lc2
            r4.append(r3)     // Catch: java.lang.Exception -> Lc2
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Exception -> Lc2
            java.util.HashMap r4 = new java.util.HashMap     // Catch: java.lang.Exception -> Lc2
            r4.<init>()     // Catch: java.lang.Exception -> Lc2
            java.util.HashMap r5 = r7.r9(r8)     // Catch: java.lang.Exception -> Lc2
            com.github.catvod.spider.merge.Vf.p(r3, r5, r4)     // Catch: java.lang.Exception -> Lc2
            java.util.Set r3 = r4.entrySet()     // Catch: java.lang.Exception -> Lc2
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Exception -> Lc2
        L5f:
            boolean r4 = r3.hasNext()     // Catch: java.lang.Exception -> Lc2
            if (r4 == 0) goto Lb0
            java.lang.Object r4 = r3.next()     // Catch: java.lang.Exception -> Lc2
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch: java.lang.Exception -> Lc2
            java.lang.Object r5 = r4.getKey()     // Catch: java.lang.Exception -> Lc2
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> Lc2
            java.lang.String r6 = "set-cookie"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Exception -> Lc2
            if (r5 != 0) goto L87
            java.lang.Object r5 = r4.getKey()     // Catch: java.lang.Exception -> Lc2
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> Lc2
            java.lang.String r6 = "Set-Cookie"
            boolean r5 = r5.equals(r6)     // Catch: java.lang.Exception -> Lc2
            if (r5 == 0) goto L5f
        L87:
            java.lang.String r3 = ";"
            java.lang.Object r4 = r4.getValue()     // Catch: java.lang.Exception -> Lc2
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch: java.lang.Exception -> Lc2
            java.lang.String r3 = android.text.TextUtils.join(r3, r4)     // Catch: java.lang.Exception -> Lc2
            org.json.JSONObject r4 = r7.M     // Catch: java.lang.Exception -> Lc2
            boolean r4 = r4.has(r1)     // Catch: java.lang.Exception -> Lc2
            if (r4 != 0) goto La5
            org.json.JSONObject r4 = r7.M     // Catch: java.lang.Exception -> Lc2
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lc2
            r5.<init>()     // Catch: java.lang.Exception -> Lc2
            r4.put(r1, r5)     // Catch: java.lang.Exception -> Lc2
        La5:
            org.json.JSONObject r4 = r7.M     // Catch: java.lang.Exception -> Lc2
            org.json.JSONObject r1 = r4.getJSONObject(r1)     // Catch: java.lang.Exception -> Lc2
            java.lang.String r4 = "cookie"
            r1.put(r4, r3)     // Catch: java.lang.Exception -> Lc2
        Lb0:
            java.lang.String r8 = r7.Q(r8, r10)     // Catch: java.lang.Exception -> Lc2
            r9 = r8
        Lb5:
            boolean r8 = r9.contains(r0)     // Catch: java.lang.Exception -> Lc2
            if (r8 != 0) goto Lc6
            boolean r8 = r9.contains(r2)     // Catch: java.lang.Exception -> Lc2
            if (r8 != 0) goto Lc6
            return r9
        Lc2:
            r8 = move-exception
            r8.printStackTrace()
        Lc6:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.catvod.spider.XBiu.W(java.lang.String, java.lang.String, org.json.JSONObject):java.lang.String");
    }

    public String addHttpPrefix(String str) {
        try {
            if (str.isEmpty()) {
                return "";
            }
            if (str.startsWith("http")) {
                return str;
            }
            String string = this.M.getString("homeUrl");
            if (string.endsWith("/")) {
                string = string.substring(0, string.length() - 1);
            }
            if (str.startsWith("/")) {
                return string + str;
            }
            return string + "/" + str;
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    protected String cG(String str) {
        boolean z;
        Matcher matcher = Pattern.compile(String.format("<a.+?href=\"(.+?)\".*?<", TextUtils.join("|", this.cG))).matcher(str);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (matcher.find() && ((i = i + 1) <= 30 || arrayList.isEmpty())) {
            HtmlMatchInfo htmlMatchInfo = new HtmlMatchInfo();
            htmlMatchInfo.init(matcher);
            String trimHtmlString = HtmlNodeHlper.trimHtmlString(HtmlNodeHlper.nodeString(str, htmlMatchInfo.cG));
            htmlMatchInfo.Q = trimHtmlString;
            if (!trimHtmlString.isEmpty()) {
                Iterator<String> it = this.cG.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (htmlMatchInfo.Q.indexOf(it.next()) != -1) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    continue;
                } else {
                    htmlMatchInfo.gw = HtmlNodeHlper.findUpNodes(str, htmlMatchInfo.cG, 3);
                    if (!arrayList.isEmpty() && !((HtmlMatchInfo) arrayList.get(0)).m(htmlMatchInfo)) {
                        if (arrayList.size() > 1) {
                            return HtmlNodeHlper.nodeString(str, ((HtmlMatchInfo) arrayList.get(0)).sL);
                        }
                        arrayList.clear();
                    }
                    arrayList.add(htmlMatchInfo);
                }
            }
        }
        return arrayList.size() > 1 ? HtmlNodeHlper.nodeString(str, ((HtmlMatchInfo) arrayList.get(0)).sL) : "";
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x01ce A[LOOP:1: B:80:0x004d->B:124:0x01ce, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0129 A[EDGE_INSN: B:141:0x0129->B:111:0x0129 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String categoryContent(java.lang.String r22, java.lang.String r23, boolean r24, java.util.HashMap<java.lang.String, java.lang.String> r25) {
        /*
            Method dump skipped, instructions count: 525
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.catvod.spider.XBiu.categoryContent(java.lang.String, java.lang.String, boolean, java.util.HashMap):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x00f5 A[Catch: Exception -> 0x0091, TryCatch #2 {Exception -> 0x0091, blocks: (B:142:0x005f, B:146:0x009a, B:148:0x00bb, B:150:0x00c6, B:152:0x00d7, B:154:0x00ef, B:156:0x00f5, B:157:0x00f9, B:161:0x010a), top: B:248:0x005f }] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01aa A[Catch: Exception -> 0x039c, TryCatch #1 {Exception -> 0x039c, blocks: (B:164:0x0196, B:166:0x01a0, B:168:0x01aa, B:169:0x01b1, B:171:0x01bb, B:172:0x01c2, B:174:0x01cc, B:176:0x01d5, B:178:0x01dc, B:179:0x020d, B:181:0x0213, B:185:0x0227, B:187:0x023c, B:188:0x024d, B:190:0x0257, B:191:0x0268, B:193:0x0272, B:194:0x027e, B:196:0x0281, B:198:0x028d, B:199:0x028f, B:200:0x0292, B:236:0x0380, B:201:0x029b, B:203:0x02a5, B:204:0x02c4, B:206:0x02ca, B:209:0x02e7, B:211:0x02f4, B:215:0x0300, B:214:0x02fd, B:216:0x0303, B:217:0x0308, B:219:0x030e, B:221:0x0314, B:222:0x031a, B:224:0x0322, B:226:0x033d, B:228:0x0347, B:231:0x0360, B:229:0x0355, B:232:0x0367, B:233:0x0373, B:235:0x037d), top: B:246:0x0196 }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01bb A[Catch: Exception -> 0x039c, TryCatch #1 {Exception -> 0x039c, blocks: (B:164:0x0196, B:166:0x01a0, B:168:0x01aa, B:169:0x01b1, B:171:0x01bb, B:172:0x01c2, B:174:0x01cc, B:176:0x01d5, B:178:0x01dc, B:179:0x020d, B:181:0x0213, B:185:0x0227, B:187:0x023c, B:188:0x024d, B:190:0x0257, B:191:0x0268, B:193:0x0272, B:194:0x027e, B:196:0x0281, B:198:0x028d, B:199:0x028f, B:200:0x0292, B:236:0x0380, B:201:0x029b, B:203:0x02a5, B:204:0x02c4, B:206:0x02ca, B:209:0x02e7, B:211:0x02f4, B:215:0x0300, B:214:0x02fd, B:216:0x0303, B:217:0x0308, B:219:0x030e, B:221:0x0314, B:222:0x031a, B:224:0x0322, B:226:0x033d, B:228:0x0347, B:231:0x0360, B:229:0x0355, B:232:0x0367, B:233:0x0373, B:235:0x037d), top: B:246:0x0196 }] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01cc A[Catch: Exception -> 0x039c, TryCatch #1 {Exception -> 0x039c, blocks: (B:164:0x0196, B:166:0x01a0, B:168:0x01aa, B:169:0x01b1, B:171:0x01bb, B:172:0x01c2, B:174:0x01cc, B:176:0x01d5, B:178:0x01dc, B:179:0x020d, B:181:0x0213, B:185:0x0227, B:187:0x023c, B:188:0x024d, B:190:0x0257, B:191:0x0268, B:193:0x0272, B:194:0x027e, B:196:0x0281, B:198:0x028d, B:199:0x028f, B:200:0x0292, B:236:0x0380, B:201:0x029b, B:203:0x02a5, B:204:0x02c4, B:206:0x02ca, B:209:0x02e7, B:211:0x02f4, B:215:0x0300, B:214:0x02fd, B:216:0x0303, B:217:0x0308, B:219:0x030e, B:221:0x0314, B:222:0x031a, B:224:0x0322, B:226:0x033d, B:228:0x0347, B:231:0x0360, B:229:0x0355, B:232:0x0367, B:233:0x0373, B:235:0x037d), top: B:246:0x0196 }] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02a5 A[Catch: Exception -> 0x039c, TryCatch #1 {Exception -> 0x039c, blocks: (B:164:0x0196, B:166:0x01a0, B:168:0x01aa, B:169:0x01b1, B:171:0x01bb, B:172:0x01c2, B:174:0x01cc, B:176:0x01d5, B:178:0x01dc, B:179:0x020d, B:181:0x0213, B:185:0x0227, B:187:0x023c, B:188:0x024d, B:190:0x0257, B:191:0x0268, B:193:0x0272, B:194:0x027e, B:196:0x0281, B:198:0x028d, B:199:0x028f, B:200:0x0292, B:236:0x0380, B:201:0x029b, B:203:0x02a5, B:204:0x02c4, B:206:0x02ca, B:209:0x02e7, B:211:0x02f4, B:215:0x0300, B:214:0x02fd, B:216:0x0303, B:217:0x0308, B:219:0x030e, B:221:0x0314, B:222:0x031a, B:224:0x0322, B:226:0x033d, B:228:0x0347, B:231:0x0360, B:229:0x0355, B:232:0x0367, B:233:0x0373, B:235:0x037d), top: B:246:0x0196 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String detailContent(java.util.List<java.lang.String> r27) {
        /*
            Method dump skipped, instructions count: 932
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.catvod.spider.XBiu.detailContent(java.util.List):java.lang.String");
    }

    public ArrayList<String> findVodPlayFrom(String str, int i) {
        String string;
        String str2;
        try {
            JSONObject jSONObject = this.M.getJSONObject("playlist");
            if (!jSONObject.has("vod_play_from")) {
                return makeVodPlayFrom(i);
            }
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("vod_play_from");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                String simpleName = jSONArray.get(i2).getClass().getSimpleName();
                if (simpleName.equals("String")) {
                    str2 = jSONArray.getString(i2);
                    string = str2;
                } else if (simpleName.equals("JSONArray")) {
                    JSONArray jSONArray2 = jSONArray.getJSONArray(i2);
                    String string2 = jSONArray2.getString(0);
                    string = jSONArray2.length() > 1 ? jSONArray2.getString(1) : string2;
                    str2 = string2;
                } else {
                    return makeVodPlayFrom(i);
                }
                int indexOf = str.indexOf(str2);
                if (indexOf != -1) {
                    arrayList.add(new Pair(Integer.valueOf(indexOf), string));
                }
            }
            if (arrayList.size() != i) {
                return makeVodPlayFrom(i);
            }
            Collections.sort(arrayList, new Comparator<Pair<Integer, String>>() { // from class: com.github.catvod.spider.XBiu.1
                @Override // java.util.Comparator
                public int compare(Pair<Integer, String> pair, Pair<Integer, String> pair2) {
                    return ((Integer) pair.first).intValue() - ((Integer) pair2.first).intValue();
                }
            });
            ArrayList<String> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                arrayList2.add((String) ((Pair) arrayList.get(i3)).second);
            }
            return arrayList2;
        } catch (Exception e) {
            e.printStackTrace();
            return makeVodPlayFrom(i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0105 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00d4 A[Catch: Exception -> 0x0155, TryCatch #0 {Exception -> 0x0155, blocks: (B:60:0x0011, B:64:0x0038, B:67:0x0049, B:69:0x0056, B:71:0x0062, B:74:0x0085, B:83:0x00c2, B:85:0x00d4, B:86:0x00e2, B:88:0x00fd, B:89:0x0105, B:76:0x008c, B:78:0x00a7, B:80:0x00ae, B:81:0x00b1, B:90:0x0126, B:93:0x012e, B:94:0x0131, B:96:0x0139, B:98:0x013f, B:100:0x0149), top: B:105:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fd A[Catch: Exception -> 0x0155, TryCatch #0 {Exception -> 0x0155, blocks: (B:60:0x0011, B:64:0x0038, B:67:0x0049, B:69:0x0056, B:71:0x0062, B:74:0x0085, B:83:0x00c2, B:85:0x00d4, B:86:0x00e2, B:88:0x00fd, B:89:0x0105, B:76:0x008c, B:78:0x00a7, B:80:0x00ae, B:81:0x00b1, B:90:0x0126, B:93:0x012e, B:94:0x0131, B:96:0x0139, B:98:0x013f, B:100:0x0149), top: B:105:0x0011 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList<java.lang.String> findVodPlayUrl(java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.catvod.spider.XBiu.findVodPlayUrl(java.lang.String):java.util.ArrayList");
    }

    public JSONArray guess_rule_vod_id(String str) {
        int i;
        int i2;
        int i3;
        try {
            Matcher matcher = Pattern.compile("<a.+?href=\"(.+?)\"").matcher(str);
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            while (true) {
                i = 1;
                if (!matcher.find()) {
                    break;
                }
                HtmlMatchInfo htmlMatchInfo = new HtmlMatchInfo();
                htmlMatchInfo.init(matcher);
                htmlMatchInfo.gw = HtmlNodeHlper.findUpNodes(str, htmlMatchInfo.cG, 4);
                if (!arrayList.isEmpty()) {
                    if (!htmlMatchInfo.M.equals(((HtmlMatchInfo) arrayList.get(arrayList.size() - 1)).M)) {
                        if (!((HtmlMatchInfo) arrayList.get(arrayList.size() - 1)).m(htmlMatchInfo)) {
                            if (arrayList.size() > 1) {
                                HtmlMatchInfo htmlMatchInfo2 = (HtmlMatchInfo) arrayList.get(0);
                                htmlMatchInfo2.findDiffStr((HtmlMatchInfo) arrayList.get(1), this.W);
                                try {
                                    i3 = Integer.valueOf(htmlMatchInfo2.r9).intValue();
                                } catch (Exception unused) {
                                    i3 = 0;
                                }
                                if (i3 > 100) {
                                    String replace = htmlMatchInfo2.M.replace(((HtmlMatchInfo) arrayList.get(0)).r9, "{vid}");
                                    JSONArray jSONArray = new JSONArray();
                                    String substring = replace.substring(0, replace.indexOf("{vid}"));
                                    String substring2 = replace.substring(substring.length() + 5);
                                    int indexOf = htmlMatchInfo2.gw.indexOf(Integer.valueOf(htmlMatchInfo2.sL)) - 1;
                                    if (indexOf < 1) {
                                        indexOf = 1;
                                    }
                                    jSONArray.put(substring);
                                    jSONArray.put(substring2);
                                    jSONArray.put(0);
                                    jSONArray.put(0);
                                    jSONArray.put(indexOf);
                                    jSONArray.put(arrayList.size());
                                    if (!hashMap.containsKey(replace)) {
                                        hashMap.put(replace, jSONArray);
                                    } else {
                                        int i4 = ((JSONArray) hashMap.get(replace)).getInt(5) + arrayList.size();
                                        jSONArray.put(5, i4);
                                        hashMap.put(replace, jSONArray);
                                        if (i4 >= 30) {
                                            arrayList.clear();
                                            break;
                                        }
                                    }
                                }
                            }
                            arrayList.clear();
                        }
                    }
                }
                arrayList.add(htmlMatchInfo);
                if (arrayList.size() > 30) {
                    break;
                }
            }
            if (arrayList.size() > 5 || (arrayList.size() > 1 && hashMap.isEmpty())) {
                HtmlMatchInfo htmlMatchInfo3 = (HtmlMatchInfo) arrayList.get(0);
                htmlMatchInfo3.findDiffStr((HtmlMatchInfo) arrayList.get(1), this.W);
                try {
                    i2 = Integer.valueOf(htmlMatchInfo3.r9).intValue();
                } catch (Exception unused2) {
                    i2 = 0;
                }
                if (i2 > 100) {
                    String replace2 = htmlMatchInfo3.M.replace(((HtmlMatchInfo) arrayList.get(0)).r9, "{vid}");
                    JSONArray jSONArray2 = new JSONArray();
                    String substring3 = replace2.substring(0, replace2.indexOf("{vid}"));
                    String substring4 = replace2.substring(substring3.length() + 5);
                    int indexOf2 = htmlMatchInfo3.gw.indexOf(Integer.valueOf(htmlMatchInfo3.sL)) - 1;
                    if (indexOf2 >= 1) {
                        i = indexOf2;
                    }
                    jSONArray2.put(substring3);
                    jSONArray2.put(substring4);
                    jSONArray2.put(0);
                    jSONArray2.put(0);
                    jSONArray2.put(i);
                    jSONArray2.put(arrayList.size());
                    if (!hashMap.containsKey(replace2)) {
                        hashMap.put(replace2, jSONArray2);
                    } else {
                        jSONArray2.put(5, ((JSONArray) hashMap.get(replace2)).getInt(5) + arrayList.size());
                        hashMap.put(replace2, jSONArray2);
                    }
                }
            }
            JSONArray jSONArray3 = null;
            for (String str2 : hashMap.keySet()) {
                JSONArray jSONArray4 = (JSONArray) hashMap.get(str2);
                if (jSONArray3 == null || jSONArray3.getInt(5) < jSONArray4.getInt(5)) {
                    jSONArray3 = jSONArray4;
                }
            }
            return jSONArray3;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x006d, code lost:
        if (r5 != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x006f, code lost:
        r3.clear();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONArray guess_rule_vod_play_url(java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.catvod.spider.XBiu.guess_rule_vod_play_url(java.lang.String, java.lang.String):org.json.JSONArray");
    }

    public String guess_value_vod_name(String str, int i) {
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("alt=\"");
            jSONArray.put("\"");
            String findSubString = Utils.findSubString(str, i, jSONArray);
            int i2 = 0;
            if (findSubString.isEmpty()) {
                jSONArray.put(0, "\" title=\"");
                findSubString = Utils.findSubString(str, i, jSONArray);
            }
            if (findSubString.isEmpty()) {
                String[] split = HtmlNodeHlper.trimHtmlString(str, "!!!!").split("!!!!");
                HashMap hashMap = new HashMap();
                for (int i3 = 0; i3 < split.length; i3++) {
                    split[i3] = split[i3].trim();
                    if (!split[i3].isEmpty() && split[i3].indexOf("更新") == -1) {
                        int i4 = 1;
                        if (hashMap.containsKey(split[i3])) {
                            i4 = 1 + ((Integer) hashMap.get(split[i3])).intValue();
                        }
                        hashMap.put(split[i3], Integer.valueOf(i4));
                    }
                }
                findSubString = "";
                for (String str2 : hashMap.keySet()) {
                    int intValue = ((Integer) hashMap.get(str2)).intValue();
                    if (intValue > i2) {
                        findSubString = str2;
                        i2 = intValue;
                    }
                }
            }
            return findSubString.replace("在线", "").replace("立即", "").replace("观看", "").replace("点播", "").replace("影片", "").replace("信息", "").replace("播放", "").trim();
        } catch (Exception unused) {
            return "";
        }
    }

    public String guess_value_vod_pic(String str, int i) {
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("data-original=\"");
            jSONArray.put("\"");
            String findSubString = Utils.findSubString(str, i, jSONArray);
            if (findSubString.isEmpty()) {
                jSONArray.put(0, "data-src=\"");
                findSubString = Utils.findSubString(str, i, jSONArray);
            }
            if (findSubString.isEmpty()) {
                jSONArray.put(0, "src=\"");
                findSubString = Utils.findSubString(str, i, jSONArray);
            }
            if (findSubString.isEmpty()) {
                jSONArray.put(0, "data-bg=\"");
                findSubString = Utils.findSubString(str, i, jSONArray);
            }
            findSubString.isEmpty();
            return addHttpPrefix(findSubString);
        } catch (Exception unused) {
            return "";
        }
    }

    public String guess_value_vod_remarks(String str, int i, String str2) {
        try {
            String str3 = "";
            for (String str4 : HtmlNodeHlper.trimHtmlString(str, "!!!!").split("!!!!")) {
                String trim = str4.trim();
                if (!trim.isEmpty() && trim.indexOf(str2) == -1) {
                    String str5 = str3 + (!str3.isEmpty() ? "," : "") + trim;
                    if (str5.length() > 20) {
                        break;
                    }
                    str3 = str5;
                }
            }
            return str3;
        } catch (Exception unused) {
            return "";
        }
    }

    protected JSONObject gw(String str) {
        boolean z;
        boolean z2;
        try {
            String cG = cG(str);
            if (cG.isEmpty()) {
                return new JSONObject();
            }
            Matcher matcher = Pattern.compile(String.format("<a.+?href=\"(.+?)\".*?[\"|>](\\s*?\\S+?\\s*?)(\"|<)", TextUtils.join("|", this.cG)), 2).matcher(cG);
            ArrayList arrayList = new ArrayList();
            while (matcher.find()) {
                HtmlMatchInfo htmlMatchInfo = new HtmlMatchInfo();
                htmlMatchInfo.init(matcher);
                if (htmlMatchInfo.M.length() >= 5) {
                    String trimHtmlString = HtmlNodeHlper.trimHtmlString(HtmlNodeHlper.nodeString(cG, htmlMatchInfo.cG));
                    htmlMatchInfo.Q = trimHtmlString;
                    if (!trimHtmlString.isEmpty()) {
                        int i = 0;
                        while (true) {
                            if (i >= this.km.size()) {
                                z2 = true;
                                break;
                            } else if (htmlMatchInfo.Q.indexOf(this.km.get(i)) != -1) {
                                SpiderDebug.log(String.format("排除无效分类：%s --> %s", htmlMatchInfo.M, htmlMatchInfo.Q));
                                z2 = false;
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (z2) {
                            if (arrayList.isEmpty() || ((HtmlMatchInfo) arrayList.get(0)).findDiffStr(htmlMatchInfo, this.W)) {
                                arrayList.add(htmlMatchInfo);
                            } else {
                                SpiderDebug.log(String.format("排除可能无效的分类 %s <--> %s", htmlMatchInfo.M, htmlMatchInfo.Q));
                            }
                        }
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                ((HtmlMatchInfo) arrayList.get(i2)).r9 = null;
                Iterator<String> it = this.cG.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (((HtmlMatchInfo) arrayList.get(i2)).Q.indexOf(it.next()) != -1) {
                        arrayList2.add(Integer.valueOf(i2));
                        break;
                    }
                }
            }
            int i3 = 0;
            for (int i4 = 1; i4 < arrayList2.size(); i4++) {
                i3 = ((Integer) arrayList2.get(0)).intValue();
                ((HtmlMatchInfo) arrayList.get(i3)).findDiffStr((HtmlMatchInfo) arrayList.get(((Integer) arrayList2.get(i4)).intValue()), this.W);
            }
            JSONObject jSONObject = new JSONObject();
            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                if ((((HtmlMatchInfo) arrayList.get(i5)).r9 == null || ((HtmlMatchInfo) arrayList.get(i5)).r9.isEmpty()) && !((HtmlMatchInfo) arrayList.get(i3)).findDiffStr((HtmlMatchInfo) arrayList.get(i5), this.W)) {
                    SpiderDebug.log(String.format("排除可能无效的分类 : %s", ((HtmlMatchInfo) arrayList.get(i5)).m));
                } else {
                    String str2 = ((HtmlMatchInfo) arrayList.get(i5)).Q;
                    String str3 = ((HtmlMatchInfo) arrayList.get(i5)).r9;
                    if (str3 != null && !str3.isEmpty() && str2 != null && !str2.isEmpty()) {
                        for (int i6 = 0; i6 < str3.length(); i6++) {
                            if (this.W.indexOf(str3.charAt(i6)) != -1) {
                                SpiderDebug.log(String.format("跳过无效的分类ID :%s", str3));
                            }
                        }
                        int i7 = 0;
                        while (true) {
                            if (i7 >= this.km.size()) {
                                z = true;
                                break;
                            } else if (str2.indexOf(this.km.get(i7)) != -1) {
                                z = false;
                                break;
                            } else {
                                i7++;
                            }
                        }
                        if (z && !jSONObject.has(str2)) {
                            jSONObject.put(str2, str3);
                        }
                    }
                }
            }
            this.M.put("cateManual", jSONObject);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public String homeContent(boolean z) {
        try {
            M();
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject optJSONObject = this.M.optJSONObject("cateManual");
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type_name", next);
                jSONObject2.put("type_id", optJSONObject.getString(next));
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("class", jSONArray);
            if (z && this.M.has("filter")) {
                jSONObject.put("filters", this.M.getJSONObject("filter"));
            }
            return jSONObject.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public void init(Context context, String str) {
        super.init(context, str);
        this.m = str;
    }

    public boolean isVideoFormat(String str) {
        String lowerCase = str.toLowerCase();
        if (!lowerCase.contains("=http") && !lowerCase.contains("=https") && !lowerCase.contains("=https%3a%2f") && !lowerCase.contains("=http%3a%2f")) {
            Iterator<String> it = this.Q.iterator();
            while (it.hasNext()) {
                if (lowerCase.contains(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    protected Object kk(Object obj) {
        JSONObject optJSONObject;
        if (obj == null) {
            return null;
        }
        try {
            optJSONObject = this.M.optJSONObject("search");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (optJSONObject == null) {
            return null;
        }
        String string = optJSONObject.getString("vod_id");
        String string2 = optJSONObject.getString("vod_name");
        String simpleName = obj.getClass().getSimpleName();
        if (simpleName.equals("JSONObject")) {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.has(string) && jSONObject.has(string2)) {
                return jSONObject;
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                Object kk = kk(jSONObject.get(keys.next()));
                if (kk != null) {
                    return kk;
                }
            }
        } else if (simpleName.equals("JSONArray")) {
            JSONArray jSONArray = (JSONArray) obj;
            for (int i = 0; i < jSONArray.length(); i++) {
                if (kk(jSONArray.get(i)) != null) {
                    return jSONArray;
                }
            }
        }
        return null;
    }

    protected String km(String str) {
        Matcher matcher = Pattern.compile(String.format(">\\s*?(%s)|(%s)", TextUtils.join("|", this.gw), TextUtils.join("：|", this.gw)), 2).matcher(str);
        ArrayList arrayList = new ArrayList();
        while (matcher.find()) {
            HtmlMatchInfo htmlMatchInfo = new HtmlMatchInfo();
            htmlMatchInfo.init(matcher);
            htmlMatchInfo.gw = HtmlNodeHlper.findUpNodes(str, htmlMatchInfo.cG, 5);
            if (!arrayList.isEmpty() && !((HtmlMatchInfo) arrayList.get(0)).m(htmlMatchInfo)) {
                if (arrayList.size() > 1) {
                    boolean z = false;
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (((HtmlMatchInfo) arrayList.get(i)).m.indexOf("导演") != -1) {
                            z = true;
                        }
                    }
                    if (z) {
                        return HtmlNodeHlper.nodeString(str, ((HtmlMatchInfo) arrayList.get(0)).sL);
                    }
                    arrayList.clear();
                }
                arrayList.clear();
            }
            arrayList.add(htmlMatchInfo);
        }
        return arrayList.size() > 1 ? HtmlNodeHlper.nodeString(str, ((HtmlMatchInfo) arrayList.get(0)).sL) : "";
    }

    protected String m(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        try {
            JSONObject jSONObject = this.M.getJSONObject("list");
            String optString = jSONObject.optString(str2, "");
            if (optString.isEmpty()) {
                optString = jSONObject.getString("url");
            }
            if (z && hashMap != null && hashMap.size() > 0) {
                for (String str3 : hashMap.keySet()) {
                    String str4 = hashMap.get(str3);
                    if (str4.length() > 0) {
                        optString = optString.replace("{" + str3 + "}", URLEncoder.encode(str4));
                    }
                }
            }
            String replace = optString.replace("{cateId}", str).replace("{catePg}", str2);
            Matcher matcher = Pattern.compile("\\{(.*?)\\}").matcher(replace);
            while (matcher.find()) {
                String replace2 = matcher.group(0).replace("{", "").replace("}", "");
                String replace3 = replace.replace(matcher.group(0), "");
                replace = replace3.replace("/" + replace2 + "/", "");
            }
            return replace;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public ArrayList<String> makeVodPlayFrom(int i) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 1; i2 <= i; i2++) {
            arrayList.add("播放列表" + i2);
        }
        return arrayList;
    }

    public boolean manualVideoCheck() {
        return true;
    }

    public String playerContent(String str, String str2, List<String> list) {
        try {
            M();
            String J = J(str, str2, list);
            if (!J.isEmpty()) {
                return J;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("parse", 1);
            jSONObject.put("playUrl", "");
            jSONObject.put("url", str2);
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    protected HashMap<String, String> r9(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            if (this.M.has("header")) {
                JSONObject jSONObject = this.M.getJSONObject("header");
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, jSONObject.getString(next));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!hashMap.containsKey("User-Agent")) {
            hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.54 Safari/537.36");
        }
        return hashMap;
    }

    protected String sL(String str) {
        Matcher matcher = Pattern.compile("<input.+?name=\"(.+?)\"", 2).matcher(str);
        if (matcher.find()) {
            matcher.group(0);
            String group = matcher.group(1);
            for (int i = 1; i < 4; i++) {
                ArrayList<Integer> findUpNodes = HtmlNodeHlper.findUpNodes(str, matcher.start(0), i);
                Matcher matcher2 = Pattern.compile("action=\"(.+?)\"", 2).matcher(HtmlNodeHlper.nodeString(str, findUpNodes.get(findUpNodes.size() - 1).intValue()));
                if (matcher2.find()) {
                    String group2 = matcher2.group(1);
                    char c = '?';
                    if (group2.indexOf(63) != -1) {
                        c = '&';
                    }
                    return addHttpPrefix(group2 + c + group + "={wd}");
                }
            }
            return "";
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x020d A[LOOP:1: B:112:0x008c->B:156:0x020d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0168 A[EDGE_INSN: B:173:0x0168->B:143:0x0168 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String searchContent(java.lang.String r23, boolean r24) {
        /*
            Method dump skipped, instructions count: 561
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.catvod.spider.XBiu.searchContent(java.lang.String, boolean):java.lang.String");
    }

    protected String uF(String str, boolean z) {
        try {
            JSONObject optJSONObject = this.M.optJSONObject("search");
            if (optJSONObject == null) {
                return "";
            }
            String string = optJSONObject.getString("url");
            JSONObject jSONObject = optJSONObject.getJSONObject("post");
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next).replace("{wd}", str));
            }
            final String[] strArr = {""};
            HashMap<String, String> r9 = r9(string);
            r9.put("content-type", "application/x-www-form-urlencoded");
            Vf.w(Vf.B(), string, hashMap, r9, new Ok.m() { // from class: com.github.catvod.spider.XBiu.2
                /* renamed from: cG */
                public void M(String str2) {
                    strArr[0] = str2;
                }

                protected void m(Call call, Exception exc) {
                }
            });
            return strArr[0];
        } catch (Exception unused) {
            return "";
        }
    }

    /* loaded from: classes.dex */
    public static class HtmlNodeHlper {
        protected static ArrayList<String> m = new ArrayList<>(Arrays.asList("img", "br", "meta", "!--"));

        public static ArrayList<Integer> findUpNodes(String str, int i, int i2) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            ArrayList arrayList2 = new ArrayList();
            if (i == -1) {
                return arrayList;
            }
            int i3 = 0;
            while (i >= 0) {
                char charAt = str.charAt(i);
                if (charAt != '/') {
                    if (charAt == '<') {
                        if (i3 == 0) {
                            arrayList2.add(String.format("%5d", Integer.valueOf(i)));
                            arrayList.add(Integer.valueOf(i));
                        } else if (isPairedHtmlTag(str, i) && i3 - 1 < 0) {
                            i3 = 0;
                        }
                    }
                } else if (str.charAt(i + 1) == '>') {
                    i3++;
                } else if (str.charAt(i - 1) == '<') {
                    i3++;
                    i--;
                }
                if (arrayList.size() >= i2) {
                    break;
                }
                i--;
            }
            return arrayList;
        }

        public static ArrayList<String> getChildNodes(String str) {
            ArrayList<String> arrayList = new ArrayList<>();
            if (str.length() > 0 && str.charAt(0) == '<') {
                int i = 1;
                while (i > -1 && i < str.length()) {
                    int indexOf = str.indexOf(60, i);
                    String nodeString = nodeString(str, indexOf);
                    if (nodeString.isEmpty()) {
                        break;
                    }
                    arrayList.add(nodeString);
                    i = indexOf + nodeString.length();
                }
            }
            return arrayList;
        }

        public static boolean isPairedHtmlTag(String str, int i) {
            String substring = str.substring(i, Math.min(str.length(), i + 10));
            Iterator<String> it = m.iterator();
            while (it.hasNext()) {
                if (substring.indexOf(it.next()) != -1) {
                    for (int i2 = i + 1; i2 < str.length(); i2++) {
                        str.substring(i2);
                        if (str.charAt(i2) == '>') {
                            return str.charAt(i2 - 1) == '/';
                        }
                    }
                    return false;
                }
            }
            return true;
        }

        public static String nodeString(String str, int i) {
            if (i < 0 || i >= str.length() || str.charAt(i) != '<') {
                return str;
            }
            int i2 = 0;
            for (int i3 = i; i3 < str.length() - 1; i3++) {
                char charAt = str.charAt(i3);
                if (charAt != '/') {
                    if (charAt != '<') {
                        if (charAt == '>' && i2 == 0) {
                            return str.substring(i, i3 + 1);
                        }
                    } else if (str.charAt(i3 + 1) != '/' && isPairedHtmlTag(str, i3)) {
                        i2++;
                    }
                } else if (str.charAt(i3 + 1) == '>' || str.charAt(i3 - 1) == '<') {
                    i2--;
                }
            }
            return str.substring(i);
        }

        public static String trimHtmlString(String str, String str2) {
            return str.replace("\r\n", "").replace("\n", "").replaceAll("<.+?>", str2).replaceAll("\\s+", " ").replace("&nbsp;", "").replace("&emsp;", "").trim();
        }

        public static String trimHtmlString(String str) {
            return trimHtmlString(str, "");
        }
    }

    /* loaded from: classes.dex */
    public static class Utils {
        public static int findBlockPos(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2) {
            int size = arrayList.size() > arrayList2.size() ? arrayList2.size() : arrayList.size();
            if (size == 1) {
                return arrayList2.get(0).intValue();
            }
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i).intValue() == arrayList2.get(i).intValue()) {
                    return arrayList2.get(i - 1).intValue();
                }
            }
            return arrayList2.get(size - 1).intValue();
        }

        public static String findSubString(String str, int i, JSONArray jSONArray, String str2) {
            int indexOf;
            if (jSONArray == null) {
                return str2;
            }
            int i2 = 0;
            try {
                String string = jSONArray.getString(0);
                String string2 = jSONArray.getString(1);
                int i3 = jSONArray.length() > 2 ? jSONArray.getInt(2) : 0;
                if (jSONArray.length() > 3) {
                    i2 = jSONArray.getInt(3);
                }
                int indexOf2 = str.indexOf(string, i) + string.length();
                return (indexOf2 >= string.length() && (indexOf = str.indexOf(string2, indexOf2)) >= indexOf2) ? HtmlNodeHlper.trimHtmlString(str.substring(indexOf2 + i3, indexOf + i2)) : str2;
            } catch (JSONException e) {
                e.printStackTrace();
                return str2;
            }
        }

        public static JSONArray getLookbackArray(JSONObject jSONObject) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    Object obj = jSONObject.get(keys.next());
                    if (obj.getClass().getSimpleName().equals("JSONArray") && getLookbackCount((JSONArray) obj) > 0) {
                        return (JSONArray) obj;
                    }
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static int getLookbackCount(JSONArray jSONArray) {
            if (jSONArray != null) {
                try {
                    if (jSONArray.length() <= 4) {
                        return 0;
                    }
                    return jSONArray.getInt(4);
                } catch (Exception unused) {
                    return 0;
                }
            }
            return 0;
        }

        public static String getRegion(String str, JSONObject jSONObject) {
            String string;
            int indexOf;
            if (jSONObject == null) {
                return str;
            }
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("region");
                if (optJSONArray == null || (indexOf = str.indexOf((string = optJSONArray.getString(0)))) == -1) {
                    return str;
                }
                int length = str.length();
                if (optJSONArray.length() > 1 && (length = str.indexOf(optJSONArray.getString(1), string.length() + indexOf)) == -1) {
                    length = str.length();
                }
                return str.substring(indexOf, length);
            } catch (JSONException e) {
                e.printStackTrace();
                return str;
            }
        }

        public static int getSubStringCount(String str, String str2) {
            int indexOf;
            int i = 0;
            int i2 = 0;
            while (i < str.length() && (indexOf = str.indexOf(str2, i)) != -1) {
                i = indexOf + str2.length();
                i2++;
            }
            return i2;
        }

        public static String findSubString(String str, int i, JSONArray jSONArray) {
            return findSubString(str, i, jSONArray, "");
        }
    }
}