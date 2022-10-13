package com.github.catvod.spider;

import android.content.Context;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.merge.JD;
import com.github.catvod.spider.merge.Ok;
import com.github.catvod.spider.merge.RC;
import com.github.catvod.spider.merge.Vf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class XBiubiu extends Spider {
    protected String j = null;
    protected JSONObject c = null;
    private String[] KO = {".m3u8", ".mp4", ".mpeg", ".flv", ".m4a", ".mp3", ".wma", ".wmv"};

    private String Fa(String str) {
        return l(str, "");
    }

    private ArrayList<String> ie(String str, String str2, String str3) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (str2.isEmpty() && str3.isEmpty()) {
            arrayList.add(str);
            return arrayList;
        }
        try {
            Matcher matcher = Pattern.compile(c(str2) + "(.*?)" + c(str3)).matcher(str);
            while (matcher.find()) {
                arrayList.add(matcher.group(1));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return arrayList;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:(2:16|17)|(13:22|23|24|25|26|27|(2:46|47)(3:29|30|31)|32|(3:38|39|(4:41|35|36|37))|34|35|36|37)|54|23|24|25|26|27|(0)(0)|32|(0)|34|35|36|37|14) */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01cc, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01d1, code lost:
        r18 = r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0145 A[Catch: all -> 0x01ca, TRY_LEAVE, TryCatch #2 {all -> 0x01ca, blocks: (B:106:0x012f, B:107:0x0145), top: B:139:0x012f }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0170 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x012f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.json.JSONObject j(java.lang.String r17, java.lang.String r18, boolean r19, java.util.HashMap<java.lang.String, java.lang.String> r20) {
        /*
            Method dump skipped, instructions count: 525
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.catvod.spider.XBiubiu.j(java.lang.String, java.lang.String, boolean, java.util.HashMap):org.json.JSONObject");
    }

    private String l(String str, String str2) {
        String optString = this.c.optString(str);
        return (optString.isEmpty() || optString.equals("空")) ? str2 : optString;
    }

    private static String m(String str) {
        Matcher matcher = Pattern.compile("(\\\\u(\\w{4}))").matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            str = str.replace(group, ((char) Integer.parseInt(matcher.group(2), 16)) + "");
        }
        return str;
    }

    protected String KO(String str) {
        SpiderDebug.log(str);
        return Vf.h(str, v(str)).replaceAll("\r|\n", "");
    }

    String P(String str) {
        return JD.ue(str).pE();
    }

    protected String b(String str) {
        SpiderDebug.log(str);
        Ok.m mVar = new Ok.m() { // from class: com.github.catvod.spider.XBiubiu.1
            @Override // com.github.catvod.spider.merge.Ok
            protected void onFailure(Call call, Exception exc) {
            }

            @Override // com.github.catvod.spider.merge.Ok
            public void onResponse(String str2) {
            }
        };
        Vf.jw(Vf.B(), str, mVar);
        return mVar.getResult().replaceAll("\r|\n", "");
    }

    String c(String str) {
        if (!str.isEmpty()) {
            String[] strArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (int i = 0; i < 14; i++) {
                String str2 = strArr[i];
                if (str.contains(str2)) {
                    str = str.replace(str2, "\\" + str2);
                }
            }
        }
        return str;
    }

    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        JSONObject j = j(str, str2, z, hashMap);
        return j != null ? j.toString() : "";
    }

    /* JADX WARN: Removed duplicated region for block: B:242:0x024d A[Catch: Exception -> 0x045e, TRY_LEAVE, TryCatch #10 {Exception -> 0x045e, blocks: (B:174:0x0024, B:176:0x0043, B:178:0x0062, B:180:0x0068, B:181:0x006d, B:183:0x007d, B:185:0x0096, B:188:0x00ab, B:189:0x00ca, B:226:0x01ba, B:227:0x01bd, B:230:0x01f7, B:233:0x020d, B:240:0x0243, B:242:0x024d, B:249:0x0285, B:251:0x0291, B:258:0x02b7, B:260:0x02c3, B:267:0x02ed, B:269:0x02f9, B:276:0x0323, B:278:0x032f, B:285:0x0354, B:287:0x03a3, B:290:0x03b0, B:292:0x03b6, B:303:0x0431, B:293:0x03cd, B:295:0x03db, B:296:0x03f3, B:297:0x0404, B:283:0x0350, B:274:0x031f, B:265:0x02e9, B:256:0x02b3, B:247:0x0281, B:238:0x023f, B:229:0x01d2, B:177:0x0048, B:280:0x033b, B:299:0x040a, B:253:0x029d, B:244:0x0259, B:271:0x0305, B:235:0x0217, B:262:0x02cf), top: B:326:0x0024, inners: #0, #5, #7, #8, #9, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0291 A[Catch: Exception -> 0x045e, TRY_LEAVE, TryCatch #10 {Exception -> 0x045e, blocks: (B:174:0x0024, B:176:0x0043, B:178:0x0062, B:180:0x0068, B:181:0x006d, B:183:0x007d, B:185:0x0096, B:188:0x00ab, B:189:0x00ca, B:226:0x01ba, B:227:0x01bd, B:230:0x01f7, B:233:0x020d, B:240:0x0243, B:242:0x024d, B:249:0x0285, B:251:0x0291, B:258:0x02b7, B:260:0x02c3, B:267:0x02ed, B:269:0x02f9, B:276:0x0323, B:278:0x032f, B:285:0x0354, B:287:0x03a3, B:290:0x03b0, B:292:0x03b6, B:303:0x0431, B:293:0x03cd, B:295:0x03db, B:296:0x03f3, B:297:0x0404, B:283:0x0350, B:274:0x031f, B:265:0x02e9, B:256:0x02b3, B:247:0x0281, B:238:0x023f, B:229:0x01d2, B:177:0x0048, B:280:0x033b, B:299:0x040a, B:253:0x029d, B:244:0x0259, B:271:0x0305, B:235:0x0217, B:262:0x02cf), top: B:326:0x0024, inners: #0, #5, #7, #8, #9, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:260:0x02c3 A[Catch: Exception -> 0x045e, TRY_LEAVE, TryCatch #10 {Exception -> 0x045e, blocks: (B:174:0x0024, B:176:0x0043, B:178:0x0062, B:180:0x0068, B:181:0x006d, B:183:0x007d, B:185:0x0096, B:188:0x00ab, B:189:0x00ca, B:226:0x01ba, B:227:0x01bd, B:230:0x01f7, B:233:0x020d, B:240:0x0243, B:242:0x024d, B:249:0x0285, B:251:0x0291, B:258:0x02b7, B:260:0x02c3, B:267:0x02ed, B:269:0x02f9, B:276:0x0323, B:278:0x032f, B:285:0x0354, B:287:0x03a3, B:290:0x03b0, B:292:0x03b6, B:303:0x0431, B:293:0x03cd, B:295:0x03db, B:296:0x03f3, B:297:0x0404, B:283:0x0350, B:274:0x031f, B:265:0x02e9, B:256:0x02b3, B:247:0x0281, B:238:0x023f, B:229:0x01d2, B:177:0x0048, B:280:0x033b, B:299:0x040a, B:253:0x029d, B:244:0x0259, B:271:0x0305, B:235:0x0217, B:262:0x02cf), top: B:326:0x0024, inners: #0, #5, #7, #8, #9, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:269:0x02f9 A[Catch: Exception -> 0x045e, TRY_LEAVE, TryCatch #10 {Exception -> 0x045e, blocks: (B:174:0x0024, B:176:0x0043, B:178:0x0062, B:180:0x0068, B:181:0x006d, B:183:0x007d, B:185:0x0096, B:188:0x00ab, B:189:0x00ca, B:226:0x01ba, B:227:0x01bd, B:230:0x01f7, B:233:0x020d, B:240:0x0243, B:242:0x024d, B:249:0x0285, B:251:0x0291, B:258:0x02b7, B:260:0x02c3, B:267:0x02ed, B:269:0x02f9, B:276:0x0323, B:278:0x032f, B:285:0x0354, B:287:0x03a3, B:290:0x03b0, B:292:0x03b6, B:303:0x0431, B:293:0x03cd, B:295:0x03db, B:296:0x03f3, B:297:0x0404, B:283:0x0350, B:274:0x031f, B:265:0x02e9, B:256:0x02b3, B:247:0x0281, B:238:0x023f, B:229:0x01d2, B:177:0x0048, B:280:0x033b, B:299:0x040a, B:253:0x029d, B:244:0x0259, B:271:0x0305, B:235:0x0217, B:262:0x02cf), top: B:326:0x0024, inners: #0, #5, #7, #8, #9, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x032f A[Catch: Exception -> 0x045e, TRY_LEAVE, TryCatch #10 {Exception -> 0x045e, blocks: (B:174:0x0024, B:176:0x0043, B:178:0x0062, B:180:0x0068, B:181:0x006d, B:183:0x007d, B:185:0x0096, B:188:0x00ab, B:189:0x00ca, B:226:0x01ba, B:227:0x01bd, B:230:0x01f7, B:233:0x020d, B:240:0x0243, B:242:0x024d, B:249:0x0285, B:251:0x0291, B:258:0x02b7, B:260:0x02c3, B:267:0x02ed, B:269:0x02f9, B:276:0x0323, B:278:0x032f, B:285:0x0354, B:287:0x03a3, B:290:0x03b0, B:292:0x03b6, B:303:0x0431, B:293:0x03cd, B:295:0x03db, B:296:0x03f3, B:297:0x0404, B:283:0x0350, B:274:0x031f, B:265:0x02e9, B:256:0x02b3, B:247:0x0281, B:238:0x023f, B:229:0x01d2, B:177:0x0048, B:280:0x033b, B:299:0x040a, B:253:0x029d, B:244:0x0259, B:271:0x0305, B:235:0x0217, B:262:0x02cf), top: B:326:0x0024, inners: #0, #5, #7, #8, #9, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x03a3 A[Catch: Exception -> 0x045e, TryCatch #10 {Exception -> 0x045e, blocks: (B:174:0x0024, B:176:0x0043, B:178:0x0062, B:180:0x0068, B:181:0x006d, B:183:0x007d, B:185:0x0096, B:188:0x00ab, B:189:0x00ca, B:226:0x01ba, B:227:0x01bd, B:230:0x01f7, B:233:0x020d, B:240:0x0243, B:242:0x024d, B:249:0x0285, B:251:0x0291, B:258:0x02b7, B:260:0x02c3, B:267:0x02ed, B:269:0x02f9, B:276:0x0323, B:278:0x032f, B:285:0x0354, B:287:0x03a3, B:290:0x03b0, B:292:0x03b6, B:303:0x0431, B:293:0x03cd, B:295:0x03db, B:296:0x03f3, B:297:0x0404, B:283:0x0350, B:274:0x031f, B:265:0x02e9, B:256:0x02b3, B:247:0x0281, B:238:0x023f, B:229:0x01d2, B:177:0x0048, B:280:0x033b, B:299:0x040a, B:253:0x029d, B:244:0x0259, B:271:0x0305, B:235:0x0217, B:262:0x02cf), top: B:326:0x0024, inners: #0, #5, #7, #8, #9, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:295:0x03db A[Catch: Exception -> 0x045e, TryCatch #10 {Exception -> 0x045e, blocks: (B:174:0x0024, B:176:0x0043, B:178:0x0062, B:180:0x0068, B:181:0x006d, B:183:0x007d, B:185:0x0096, B:188:0x00ab, B:189:0x00ca, B:226:0x01ba, B:227:0x01bd, B:230:0x01f7, B:233:0x020d, B:240:0x0243, B:242:0x024d, B:249:0x0285, B:251:0x0291, B:258:0x02b7, B:260:0x02c3, B:267:0x02ed, B:269:0x02f9, B:276:0x0323, B:278:0x032f, B:285:0x0354, B:287:0x03a3, B:290:0x03b0, B:292:0x03b6, B:303:0x0431, B:293:0x03cd, B:295:0x03db, B:296:0x03f3, B:297:0x0404, B:283:0x0350, B:274:0x031f, B:265:0x02e9, B:256:0x02b3, B:247:0x0281, B:238:0x023f, B:229:0x01d2, B:177:0x0048, B:280:0x033b, B:299:0x040a, B:253:0x029d, B:244:0x0259, B:271:0x0305, B:235:0x0217, B:262:0x02cf), top: B:326:0x0024, inners: #0, #5, #7, #8, #9, #13 }] */
    /* JADX WARN: Removed duplicated region for block: B:299:0x040a A[Catch: all -> 0x042d, TRY_ENTER, TRY_LEAVE, TryCatch #10 {Exception -> 0x045e, blocks: (B:174:0x0024, B:176:0x0043, B:178:0x0062, B:180:0x0068, B:181:0x006d, B:183:0x007d, B:185:0x0096, B:188:0x00ab, B:189:0x00ca, B:226:0x01ba, B:227:0x01bd, B:230:0x01f7, B:233:0x020d, B:240:0x0243, B:242:0x024d, B:249:0x0285, B:251:0x0291, B:258:0x02b7, B:260:0x02c3, B:267:0x02ed, B:269:0x02f9, B:276:0x0323, B:278:0x032f, B:285:0x0354, B:287:0x03a3, B:290:0x03b0, B:292:0x03b6, B:303:0x0431, B:293:0x03cd, B:295:0x03db, B:296:0x03f3, B:297:0x0404, B:283:0x0350, B:274:0x031f, B:265:0x02e9, B:256:0x02b3, B:247:0x0281, B:238:0x023f, B:229:0x01d2, B:177:0x0048, B:280:0x033b, B:299:0x040a, B:253:0x029d, B:244:0x0259, B:271:0x0305, B:235:0x0217, B:262:0x02cf), top: B:326:0x0024, inners: #0, #5, #7, #8, #9, #13 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String detailContent(java.util.List<java.lang.String> r30) {
        /*
            Method dump skipped, instructions count: 1123
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.catvod.spider.XBiubiu.detailContent(java.util.List):java.lang.String");
    }

    protected void g() {
        String str;
        if (this.c != null || (str = this.j) == null) {
            return;
        }
        try {
            if (str.startsWith("http")) {
                this.c = new JSONObject(Vf.h(this.j, null));
            } else {
                this.c = new JSONObject(this.j);
            }
        } catch (JSONException unused) {
        }
    }

    public String homeContent(boolean z) {
        try {
            g();
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (String str : l("fenlei", "").split("#")) {
                String[] split = str.split("\\$");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type_name", split[0]);
                jSONObject2.put("type_id", split[1]);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("class", jSONArray);
            return jSONObject.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public String homeVideoContent() {
        JSONArray optJSONArray;
        try {
            g();
            if (Fa("shouye").equals("1")) {
                JSONArray jSONArray = new JSONArray();
                for (String str : l("fenlei", "").split("#")) {
                    JSONObject j = j(str.split("\\$")[1], "1", false, new HashMap<>());
                    if (j != null && (optJSONArray = j.optJSONArray("list")) != null) {
                        for (int i = 0; i < optJSONArray.length() && i < 5; i++) {
                            jSONArray.put(optJSONArray.getJSONObject(i));
                        }
                    }
                    if (jSONArray.length() >= 30) {
                        break;
                    }
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("list", jSONArray);
                return jSONObject.toString();
            }
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    public void init(Context context) {
        super.init(context);
    }

    public boolean isVideoFormat(String str) {
        String lowerCase = str.toLowerCase();
        if (!lowerCase.contains("=http") && !lowerCase.contains("=https%3a%2f") && !lowerCase.contains("=http%3a%2f")) {
            for (String str2 : this.KO) {
                if (lowerCase.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean manualVideoCheck() {
        return true;
    }

    public String playerContent(String str, String str2, List<String> list) {
        String str3;
        try {
            g();
            if (str2.startsWith("x:")) {
                str3 = str2.substring(2);
            } else {
                str3 = Fa("url") + str2;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("parse", 1);
            jSONObject.put("playUrl", "");
            jSONObject.put("url", str3);
            return jSONObject.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public String searchContent(String str, boolean z) {
        String str2;
        JSONArray jSONArray;
        String str3;
        String sb;
        String str4;
        String str5;
        String str6;
        StringBuilder sb2;
        try {
            g();
            boolean equals = Fa("ssmoshi").equals("0");
            String str7 = Fa("url") + Fa("sousuoqian") + str + Fa("sousuohou");
            String str8 = str7.split(";")[0];
            String b = str7.contains(";post") ? b(str8) : KO(str8);
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray2 = new JSONArray();
            String str9 = "x:";
            String str10 = "list";
            String str11 = "ssfubiaotihou";
            String str12 = "ssljqianzhui";
            if (equals) {
                JSONArray jSONArray3 = new JSONObject(b).getJSONArray(str10);
                int i = 0;
                while (i < jSONArray3.length()) {
                    JSONObject jSONObject2 = jSONArray3.getJSONObject(i);
                    JSONArray jSONArray4 = jSONArray3;
                    String trim = jSONObject2.optString(Fa("jsname")).trim();
                    String str13 = str10;
                    String trim2 = jSONObject2.optString(Fa("jsid")).trim();
                    int i2 = i;
                    String B = RC.B(str8, jSONObject2.optString(Fa("jspic")).trim());
                    StringBuilder sb3 = new StringBuilder();
                    String str14 = str8;
                    sb3.append(Fa("sousuohouzhui"));
                    sb3.append(trim2);
                    String sb4 = sb3.toString();
                    if (Fa(str12).isEmpty()) {
                        sb2 = new StringBuilder();
                        sb2.append(sb4);
                        sb2.append(Fa("ssljhouzhui"));
                    } else {
                        sb2 = new StringBuilder();
                        sb2.append(str9);
                        sb2.append(Fa(str12));
                        sb2.append(sb4);
                        sb2.append(Fa("ssljhouzhui"));
                    }
                    String sb5 = sb2.toString();
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("vod_id", trim + "$$$" + B + "$$$" + sb5);
                    jSONObject3.put("vod_name", trim);
                    jSONObject3.put("vod_pic", B);
                    jSONObject3.put("vod_remarks", "");
                    jSONArray2.put(jSONObject3);
                    i = i2 + 1;
                    jSONArray3 = jSONArray4;
                    str10 = str13;
                    str8 = str14;
                }
                str2 = str10;
            } else {
                String str15 = str8;
                str2 = str10;
                if (Fa("sousuoshifouercijiequ").equals("1")) {
                    b = ie(b, Fa("ssjiequqian"), Fa("ssjiequhou")).get(0);
                }
                ArrayList<String> ie = ie(b, Fa("ssjiequshuzuqian"), Fa("ssjiequshuzuhou"));
                int i3 = 0;
                while (i3 < ie.size()) {
                    try {
                        String str16 = ie.get(i3);
                        ArrayList<String> arrayList = ie;
                        String str17 = ie(str16, Fa("ssbiaotiqian"), Fa("ssbiaotihou")).get(0);
                        int i4 = i3;
                        String str18 = str15;
                        String B2 = RC.B(str18, ie(str16, Fa("sstupianqian"), Fa("sstupianhou")).get(0));
                        str15 = str18;
                        JSONArray jSONArray5 = jSONArray2;
                        try {
                            String str19 = ie(str16, Fa("sslianjieqian"), Fa("sslianjiehou")).get(0);
                            if (Fa(str12).isEmpty()) {
                                sb = str19 + Fa("ssljhouzhui");
                                str3 = str9;
                            } else {
                                StringBuilder sb6 = new StringBuilder();
                                sb6.append(str9);
                                str3 = str9;
                                sb6.append(Fa(str12));
                                sb6.append(str19);
                                sb6.append(Fa("ssljhouzhui"));
                                sb = sb6.toString();
                            }
                            try {
                                if (!Fa("ssfubiaotiqian").isEmpty()) {
                                    str4 = str11;
                                    if (!Fa(str4).isEmpty()) {
                                        try {
                                            str5 = str12;
                                            try {
                                            } catch (Exception e) {
                                                e = e;
                                            }
                                        } catch (Exception e2) {
                                            e = e2;
                                            str5 = str12;
                                        }
                                        try {
                                            str6 = ie(str16, Fa("ssfubiaotiqian"), Fa(str4)).get(0).replaceAll("\\s+", "").replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
                                        } catch (Exception e3) {
                                            e = e3;
                                            SpiderDebug.log(e);
                                            str6 = "";
                                            JSONObject jSONObject4 = new JSONObject();
                                            jSONObject4.put("vod_id", str17 + "$$$" + B2 + "$$$" + sb);
                                            jSONObject4.put("vod_name", str17);
                                            jSONObject4.put("vod_pic", B2);
                                            jSONObject4.put("vod_remarks", str6);
                                            jSONArray = jSONArray5;
                                            jSONArray.put(jSONObject4);
                                            i3 = i4 + 1;
                                            jSONArray2 = jSONArray;
                                            str12 = str5;
                                            ie = arrayList;
                                            str11 = str4;
                                            str9 = str3;
                                        }
                                        JSONObject jSONObject42 = new JSONObject();
                                        jSONObject42.put("vod_id", str17 + "$$$" + B2 + "$$$" + sb);
                                        jSONObject42.put("vod_name", str17);
                                        jSONObject42.put("vod_pic", B2);
                                        jSONObject42.put("vod_remarks", str6);
                                        jSONArray = jSONArray5;
                                        jSONArray.put(jSONObject42);
                                        i3 = i4 + 1;
                                        jSONArray2 = jSONArray;
                                        str12 = str5;
                                        ie = arrayList;
                                        str11 = str4;
                                        str9 = str3;
                                    }
                                } else {
                                    str4 = str11;
                                }
                                jSONArray.put(jSONObject42);
                                i3 = i4 + 1;
                                jSONArray2 = jSONArray;
                                str12 = str5;
                                ie = arrayList;
                                str11 = str4;
                                str9 = str3;
                            } catch (Throwable th) {
                                th = th;
                                th.printStackTrace();
                                jSONObject.put(str2, jSONArray);
                                return jSONObject.toString();
                            }
                            str5 = str12;
                            str6 = "";
                            JSONObject jSONObject422 = new JSONObject();
                            jSONObject422.put("vod_id", str17 + "$$$" + B2 + "$$$" + sb);
                            jSONObject422.put("vod_name", str17);
                            jSONObject422.put("vod_pic", B2);
                            jSONObject422.put("vod_remarks", str6);
                            jSONArray = jSONArray5;
                        } catch (Throwable th2) {
                            th = th2;
                            jSONArray = jSONArray5;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        jSONArray = jSONArray2;
                    }
                }
            }
            jSONArray = jSONArray2;
            jSONObject.put(str2, jSONArray);
            return jSONObject.toString();
        } catch (Exception e4) {
            SpiderDebug.log(e4);
            return "";
        }
    }

    protected HashMap<String, String> v(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        String str2 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.54 Safari/537.36";
        String trim = l("ua", str2).trim();
        if (!trim.isEmpty()) {
            str2 = trim;
        }
        hashMap.put("User-Agent", str2);
        return hashMap;
    }

    public void init(Context context, String str) {
        super.init(context, str);
        this.j = str;
    }
}