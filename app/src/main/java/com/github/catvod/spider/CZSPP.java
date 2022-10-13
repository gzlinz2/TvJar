package com.github.catvod.spider;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.merge.AbstractC0271P;
import com.github.catvod.spider.merge.C0052CU;
import com.github.catvod.spider.merge.C0158L;
import com.github.catvod.spider.merge.C0284Qh;
import com.github.catvod.spider.merge.C0578hV;
import com.github.catvod.spider.merge.C0725uN;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: C:\Users\qi139\AppData\Local\Temp\jadx-17687371776977690783\classes.dex */
public class CZSPP extends Spider {

    /* renamed from: Sh */
    private final Pattern f61Sh = Pattern.compile("/movie/(\\d+).html");

    /* renamed from: Ml */
    private final Pattern f58Ml = Pattern.compile("/page/(\\d+)");

    /* renamed from: H */
    private final Pattern f56H = Pattern.compile("/v_play/(.*)\\.html");

    /* renamed from: E */
    private final Pattern f53E = Pattern.compile("\\\"([^\\\"]+)\\\";var [\\d\\w]+=function dncry.*md5.enc.Utf8.parse\\(\\\"([\\d\\w]+)\\\".*md5.enc.Utf8.parse\\(([\\d]+)\\)");

    /* renamed from: rp */
    private final Pattern f63rp = Pattern.compile("video: \\{url: \\\"([^\\\"]+)\\\"");

    /* renamed from: P */
    private final Pattern f59P = Pattern.compile("subtitle: \\{url:\\\"([^\\\"]+\\.vtt)\\\"");

    /* renamed from: G9 */
    private final Pattern f55G9 = Pattern.compile("var time = ['\\\"]([^'\\\"]+)['\\\"]");

    /* renamed from: C3 */
    private final Pattern f52C3 = Pattern.compile("var url = ['\\\"]([^'\\\"]+)['\\\"]");

    /* renamed from: s */
    private final Pattern f64s = Pattern.compile("src: '([^']+\\.css)',");

    /* renamed from: d */
    private final Pattern f62d = Pattern.compile("var vkey = ['\\\"]([^'\\\"]+)['\\\"]");

    /* renamed from: Ei */
    private final Pattern f54Ei = Pattern.compile("var fvkey = ['\\\"]([^'\\\"]+)['\\\"]");

    /* renamed from: IJ */
    private final Pattern f57IJ = Pattern.compile("var ua = ['\\\"]([^'\\\"]+)['\\\"]");

    /* renamed from: SJ */
    private final Pattern f60SJ = Pattern.compile("var cip = ['\\\"]([^'\\\"]+)['\\\"]");

    /* renamed from: Ml */
    private String m2205Ml(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                while (hexString.length() < 2) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: Sh */
    protected HashMap<String, String> m2204Sh() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36");
        return hashMap;
    }

    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        try {
            C0578hV m2094Sh = C0052CU.m2094Sh(AbstractC0271P.IJ("https://czspp.com/" + str + "/page/" + str2, m2204Sh()));
            C0284Qh m461H8 = m2094Sh.m461H8("div.mi_ne_kd > ul > li");
            JSONArray jSONArray = new JSONArray();
            Iterator<C0725uN> it = m461H8.iterator();
            while (it.hasNext()) {
                C0725uN next = it.next();
                Matcher matcher = this.f61Sh.matcher(next.m451Pt("a").mo99H("href"));
                if (matcher.find()) {
                    String group = matcher.group(1);
                    String trim = next.m451Pt("img").mo99H("alt").trim();
                    String trim2 = next.m451Pt("img").mo99H("data-original").trim();
                    String trim3 = next.m451Pt("div.hdinfo > span").m438fv().trim();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("vod_id", group);
                    jSONObject.put("vod_name", trim);
                    jSONObject.put("vod_pic", trim2);
                    jSONObject.put("vod_remarks", trim3);
                    jSONArray.put(jSONObject);
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("page", str2);
            jSONObject2.put("limit", 25);
            jSONObject2.put("list", jSONArray);
            C0725uN m1620E = m2094Sh.m461H8("div.pagenavi_txt > a").m1620E();
            if (m1620E == null) {
                jSONObject2.put("pagecount", Integer.MAX_VALUE);
                jSONObject2.put("total", Integer.MAX_VALUE);
            } else {
                Matcher matcher2 = this.f58Ml.matcher(m1620E.mo99H("href").trim());
                if (matcher2.find()) {
                    int parseInt = Integer.parseInt(matcher2.group(1));
                    jSONObject2.put("pagecount", parseInt);
                    jSONObject2.put("total", parseInt * 25);
                } else {
                    jSONObject2.put("pagecount", Integer.MAX_VALUE);
                    jSONObject2.put("total", Integer.MAX_VALUE);
                }
            }
            return jSONObject2.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public String detailContent(List<String> list) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://czspp.com/movie/");
            sb.append(list.get(0));
            sb.append(".html");
            C0578hV m2094Sh = C0052CU.m2094Sh(AbstractC0271P.IJ(sb.toString(), m2204Sh()));
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            String trim = m2094Sh.m451Pt("div.moviedteail_tt > h1").m438fv().trim();
            String mo99H = m2094Sh.m451Pt("div.dyimg > img").mo99H("src");
            jSONObject2.put("vod_id", list.get(0));
            jSONObject2.put("vod_name", trim);
            jSONObject2.put("vod_pic", mo99H);
            jSONObject2.put("vod_remarks", "\u65e0");
            jSONObject2.put("vod_content", m2094Sh.m461H8("div.yp_context").m1619G9().trim());
            Iterator<C0725uN> it = m2094Sh.m461H8("ul.moviedteail_list > li").iterator();
            while (it.hasNext()) {
                C0725uN next = it.next();
                String substring = next.m438fv().substring(0, 2);
                char c = '\uffff';
                switch (substring.hashCode()) {
                    case 649273:
                        if (substring.equals("\u4e3b\u6f14")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 713226:
                        if (substring.equals("\u5730\u533a")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 758424:
                        if (substring.equals("\u5bfc\u6f14")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 769801:
                        if (substring.equals("\u5e74\u4efd")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 1010288:
                        if (substring.equals("\u7c7b\u578b")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1143133:
                        if (substring.equals("\u8c46\u74e3")) {
                            c = 5;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    jSONObject2.put("type_name", next.m461H8("a").m1619G9());
                } else if (c == 1) {
                    jSONObject2.put("vod_area", next.m461H8("a").m1619G9());
                } else if (c == 2) {
                    jSONObject2.put("vod_year", next.m461H8("a").m1619G9());
                } else if (c == 3) {
                    jSONObject2.put("vod_director", next.m461H8("a").m1619G9());
                } else if (c == 4) {
                    jSONObject2.put("vod_actor", next.m461H8("a").m1619G9());
                } else if (c == 5) {
                    jSONObject2.put("vod_remarks", next.m461H8("a").m1619G9());
                }
            }
            jSONObject2.put("vod_play_from", "\u5382\u957f\u8d44\u6e90");
            new ArrayList();
            C0284Qh m461H8 = m2094Sh.m461H8("div.paly_list_btn > a");
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < m461H8.size(); i++) {
                C0725uN c0725uN = m461H8.get(i);
                Matcher matcher = this.f56H.matcher(c0725uN.mo99H("href"));
                if (matcher.find()) {
                    sb2.append(c0725uN.m438fv());
                    sb2.append("$");
                    sb2.append(matcher.group(1));
                    if (i < m461H8.size() - 1) {
                        sb2.append("#");
                    }
                }
            }
            jSONObject2.put("vod_play_url", sb2.toString());
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put("list", jSONArray);
            return jSONObject.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public String homeContent(boolean z) {
        try {
            C0578hV m2094Sh = C0052CU.m2094Sh(AbstractC0271P.IJ("https://czspp.com", m2204Sh()));
            C0284Qh m461H8 = m2094Sh.m461H8(".navlist > li:not(:first-child) > a");
            JSONArray jSONArray = new JSONArray();
            Iterator<C0725uN> it = m461H8.iterator();
            while (it.hasNext()) {
                C0725uN next = it.next();
                String m438fv = next.m438fv();
                String mo99H = next.mo99H("href");
                if (!mo99H.equals("") && mo99H.length() > 1) {
                    String substring = mo99H.substring(1);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("type_id", substring);
                    jSONObject.put("type_name", m438fv);
                    jSONArray.put(jSONObject);
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("class", jSONArray);
            C0284Qh m461H82 = m2094Sh.m461H8("div.mi_ne_kd > ul > li");
            JSONArray jSONArray2 = new JSONArray();
            Iterator<C0725uN> it2 = m461H82.iterator();
            while (it2.hasNext()) {
                C0725uN next2 = it2.next();
                Matcher matcher = this.f61Sh.matcher(next2.m451Pt("a").mo99H("href"));
                if (matcher.find()) {
                    String group = matcher.group(1);
                    String trim = next2.m451Pt("img").mo99H("alt").trim();
                    String trim2 = next2.m451Pt("img").mo99H("data-original").trim();
                    String trim3 = next2.m451Pt("div.hdinfo > span").m438fv().trim();
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("vod_id", group);
                    jSONObject3.put("vod_name", trim);
                    jSONObject3.put("vod_pic", trim2);
                    jSONObject3.put("vod_remarks", trim3);
                    jSONArray2.put(jSONObject3);
                }
            }
            jSONObject2.put("list", jSONArray2);
            return jSONObject2.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public void init(Context context) {
        super.init(context);
    }

    public String playerContent(String str, String str2, List<String> list) {
        C0578hV m2094Sh;
        Matcher matcher;
        try {
            String IJ = AbstractC0271P.IJ("https://czspp.com/v_play/" + str2 + ".html", m2204Sh());
            m2094Sh = C0052CU.m2094Sh(IJ);
            matcher = this.f53E.matcher(IJ);
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        if (!matcher.find()) {
            return "";
        }
        String group = matcher.group(1);
        byte[] bytes = matcher.group(2).getBytes();
        byte[] bytes2 = matcher.group(3).getBytes();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, new SecretKeySpec(bytes, "AES"), new IvParameterSpec(bytes2));
        String str3 = new String(cipher.doFinal(Base64.decode(group, 0)));
        Matcher matcher2 = this.f63rp.matcher(str3);
        if (matcher2.find()) {
            String group2 = matcher2.group(1);
            Matcher matcher3 = this.f59P.matcher(str3);
            String group3 = matcher3.find() ? matcher3.group(1) : "";
            if (TextUtils.isEmpty(group2)) {
                return "";
            }
            C0725uN m451Pt = m2094Sh.m451Pt("iframe.viframe");
            if (m451Pt != null) {
                String mo99H = m451Pt.mo99H("src");
                if (!mo99H.contains("jx.xmflv.com")) {
                    return "";
                }
                String IJ2 = AbstractC0271P.IJ(mo99H, m2204Sh());
                Matcher matcher4 = this.f55G9.matcher(IJ2);
                if (!matcher4.find()) {
                    return "";
                }
                String group4 = matcher4.group(1);
                Matcher matcher5 = this.f52C3.matcher(IJ2);
                if (!matcher5.find()) {
                    return "";
                }
                String group5 = matcher5.group(1);
                String IJ3 = AbstractC0271P.IJ("https://jx.xmflv.com/player.php?time=" + group4 + "&url=" + group5, m2204Sh());
                Matcher matcher6 = this.f62d.matcher(IJ3);
                if (!matcher6.find()) {
                    return "";
                }
                String group6 = matcher6.group(1);
                Matcher matcher7 = this.f54Ei.matcher(IJ3);
                if (!matcher7.find()) {
                    return "";
                }
                String group7 = matcher7.group(1);
                Matcher matcher8 = this.f57IJ.matcher(IJ3);
                if (!matcher8.find()) {
                    return "";
                }
                String group8 = matcher8.group(1);
                Matcher matcher9 = this.f60SJ.matcher(IJ3);
                if (!matcher9.find()) {
                    return "";
                }
                String group9 = matcher9.group(1);
                Matcher matcher10 = this.f55G9.matcher(IJ3);
                if (!matcher10.find()) {
                    return "";
                }
                String group10 = matcher10.group(1);
                byte[] bytes3 = m2205Ml(group7).getBytes();
                byte[] bytes4 = "UVE1NTY4MDY2NQ==".getBytes();
                Cipher cipher2 = Cipher.getInstance("AES/CBC/NoPadding");
                cipher2.init(1, new SecretKeySpec(bytes3, "AES"), new IvParameterSpec(bytes4));
                String encodeToString = Base64.encodeToString(cipher2.doFinal(group7.getBytes()), 0);
                C0158L<String> c0158l = new C0158L<String>() { // from class: com.github.catvod.spider.CZSPP.1
                    public void onFailure(Call call, Exception exc) {
                    }

                    public void onResponse(String str4) {
                    }

                    public String onParseResponse(Call call, Response response) {
                        try {
                            return new JSONObject(response.body().string()).getString("url");
                        } catch (Exception unused) {
                            return "";
                        }
                    }
                };
                HashMap hashMap = new HashMap();
                hashMap.put("url", group5);
                hashMap.put("time", group10);
                hashMap.put("ua", group8);
                hashMap.put("cip", group9);
                hashMap.put("vkey", group6);
                hashMap.put("fvkey", encodeToString);
                AbstractC0271P.E(AbstractC0271P.H(), "https://jx.xmflv.com/xmflv-1.SVG", hashMap, m2204Sh(), c0158l);
                Matcher matcher11 = this.f64s.matcher(IJ2);
                if (!matcher11.find()) {
                    return "";
                }
                group2 = matcher11.group(1);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("parse", "0");
            jSONObject.put("playUrl", "");
            jSONObject.put("url", group2);
            jSONObject.put("header", "");
            if (!TextUtils.isEmpty(group3)) {
                jSONObject.put("subt", Proxy.localProxyUrl() + "?do=czspp&url=" + URLEncoder.encode(group3, "utf-8"));
            }
            jSONObject.put("subf", "/vtt/utf-8");
            return jSONObject.toString();
        }
        return "";
    }

    public String searchContent(String str, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            Iterator<C0725uN> it = C0052CU.m2094Sh(AbstractC0271P.IJ("https://czspp.com/xssearch?q=" + URLEncoder.encode(str, "utf-8"), m2204Sh())).m461H8("div.search_list > ul > li").iterator();
            if (it.hasNext()) {
                C0725uN next = it.next();
                Matcher matcher = this.f61Sh.matcher(next.m451Pt("a").mo99H("href"));
                if (!matcher.find()) {
                    return "";
                }
                String group = matcher.group(1);
                C0725uN m451Pt = next.m451Pt("img");
                String trim = m451Pt.mo99H("alt").trim();
                if (!trim.contains(str)) {
                    return "";
                }
                String trim2 = m451Pt.mo99H("src").trim();
                C0725uN m451Pt2 = next.m451Pt("div.jidi > span");
                String trim3 = m451Pt2 == null ? "" : m451Pt2.m438fv().trim();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("vod_id", group);
                jSONObject2.put("vod_name", trim);
                jSONObject2.put("vod_pic", trim2);
                jSONObject2.put("vod_remarks", trim3);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("list", jSONArray);
            return jSONObject.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }
}
