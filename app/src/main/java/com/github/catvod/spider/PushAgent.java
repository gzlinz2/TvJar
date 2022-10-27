package com.github.catvod.spider;

import android.content.Context;
import android.net.UrlQuerySanitizer;
import android.text.TextUtils;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.merge.MJ;
import com.github.catvod.spider.merge.Nv;
import com.github.catvod.spider.merge.Uj;
import com.github.catvod.spider.merge.Xu;
import com.github.catvod.spider.merge.mI;
import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PushAgent extends Spider {
    private static long I = 0;
    private static String PL = "";
    private static long V7 = 0;
    private static String c = "";
    private static Map<String, String> jv = new HashMap();
    private static Map<String, Long> wF = new HashMap();
    private static final Map<String, Map<String, String>> H = new HashMap();
    private static final ReentrantLock ux = new ReentrantLock();
    public static Pattern p = Pattern.compile("(https://www.aliyundrive.com/s/[^\"]+)");
    public static Pattern F = Pattern.compile("www.aliyundrive.com/s/([^/]+)(/folder/([^/]+))?");
    private static String rp = "asc";

    public static Object[] File(Map<String, String> map) {
        try {
            String str = map.get("share_id");
            return new Object[]{200, "application/octet-stream", new ByteArrayInputStream(H(str, jv(str, ""), map.get("file_id")).getBytes())};
        } catch (Exception e) {
            SpiderDebug.log(e);
            return null;
        }
    }

    private static String H(String str, String str2, String str3) {
        String jv2;
        String[] split;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("share_id", str);
            jSONObject.put("category", "live_transcoding");
            jSONObject.put("file_id", str3);
            jSONObject.put("template_id", "");
            HashMap<String, String> I2 = I();
            I2.put("x-share-token", str2);
            I2.put("authorization", c);
            JSONArray jSONArray = new JSONObject(ux("https://api.aliyundrive.com/v2/file/get_share_link_video_preview_play_info", jSONObject.toString(), I2)).getJSONObject("video_preview_play_info").getJSONArray("live_transcoding_task_list");
            String[] strArr = {"FHD", "HD", "SD"};
            String str4 = "";
            int i = 0;
            for (int i2 = 3; i < i2; i2 = 3) {
                String str5 = strArr[i];
                int i3 = 0;
                while (true) {
                    if (i3 >= jSONArray.length()) {
                        break;
                    }
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                    if (jSONObject2.optString("template_id").equals(str5)) {
                        str4 = jSONObject2.getString("url");
                        break;
                    }
                    i3++;
                }
                if (!str4.isEmpty()) {
                    break;
                }
                i++;
            }
            if (str4.isEmpty() && jSONArray.length() > 0) {
                str4 = jSONArray.getJSONObject(0).getString("url");
            }
            HashMap hashMap = new HashMap();
            mI.sP(str4, I(), hashMap);
            String gR = mI.gR(mI.jv(hashMap), I());
            String str6 = jv2.substring(0, jv2.lastIndexOf("/")) + "/";
            ArrayList arrayList = new ArrayList();
            HashMap hashMap2 = new HashMap();
            int i4 = 0;
            for (String str7 : gR.split("\n")) {
                if (str7.contains("x-oss-expires")) {
                    i4++;
                    hashMap2.put("" + i4, str6 + str7);
                    str7 = Proxy.localProxyUrl() + "?do=push&type=media&share_id=" + str + "&file_id=" + str3 + "&media_id=" + i4;
                }
                arrayList.add(str7);
            }
            H.put(str3, hashMap2);
            return TextUtils.join("\n", arrayList);
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    private static HashMap<String, String> I() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.54 Safari/537.36");
        hashMap.put("Referer", "https://www.aliyundrive.com/");
        return hashMap;
    }

    private static void PL() {
        long wF2 = wF();
        if (c.isEmpty() || I - wF2 <= 600) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("refresh_token", PL);
                JSONObject jSONObject2 = new JSONObject(ux("https://api.aliyundrive.com/token/refresh", jSONObject.toString(), I()));
                c = jSONObject2.getString("token_type") + " " + jSONObject2.getString("access_token");
                V7 = jSONObject2.getLong("expires_in");
                I = I + wF2;
            } catch (JSONException e) {
                SpiderDebug.log(e);
            }
        }
    }

    public static Object[] ProxyMedia(Map<String, String> map) {
        try {
            String str = map.get("share_id");
            String str2 = map.get("file_id");
            String str3 = map.get("media_id");
            String jv2 = jv(str, "");
            ReentrantLock reentrantLock = ux;
            reentrantLock.lock();
            Map<String, Map<String, String>> map2 = H;
            String str4 = map2.get(str2).get(str3);
            if (new Long(new UrlQuerySanitizer(str4).getValue("x-oss-expires")).longValue() - wF() <= 60) {
                H(str, jv2, str2);
                str4 = map2.get(str2).get(str3);
            }
            reentrantLock.unlock();
            PushAgent$2 r12 = new PushAgent$2();
            mI.PL(mI.c(), str4, (Map) null, I(), r12);
            return new Object[]{200, "video/MP2T", ((Response) r12.getResult()).body().byteStream()};
        } catch (Exception e) {
            SpiderDebug.log(e);
            return null;
        }
    }

    private static HashMap<String, String> V7() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.54 Safari/537.36");
        return hashMap;
    }

    private static String c(String str, String str2, String str3, String str4) {
        try {
            HashMap<String, String> I2 = I();
            I2.put("x-share-token", str2);
            I2.put("authorization", c);
            if (str4.equals("video")) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("share_id", str);
                jSONObject.put("category", "live_transcoding");
                jSONObject.put("file_id", str3);
                jSONObject.put("template_id", "");
                JSONObject jSONObject2 = new JSONObject(ux("https://api.aliyundrive.com/v2/file/get_share_link_video_preview_play_info", jSONObject.toString(), I2));
                String string = jSONObject2.getString("share_id");
                str3 = jSONObject2.getString("file_id");
                str = string;
            }
            JSONObject jSONObject3 = new JSONObject();
            if (str4.equals("video")) {
                jSONObject3.put("expire_sec", 600);
                jSONObject3.put("file_id", str3);
                jSONObject3.put("share_id", str);
            }
            if (str4.equals("audio")) {
                jSONObject3.put("share_id", str);
                jSONObject3.put("get_audio_play_info", true);
                jSONObject3.put("file_id", str3);
            }
            return new JSONObject(ux("https://api.aliyundrive.com/v2/file/get_share_link_download_url", jSONObject3.toString(), I2)).getString("download_url");
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    private static synchronized String jv(String str, String str2) {
        synchronized (PushAgent.class) {
            synchronized (PushAgent.class) {
                try {
                    long wF2 = wF();
                    String str3 = jv.get(str);
                    Long l = wF.get(str);
                    if (!TextUtils.isEmpty(str3) && l.longValue() - wF2 > 600) {
                        return str3;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("share_id", str);
                    jSONObject.put("share_pwd", str2);
                    JSONObject jSONObject2 = new JSONObject(ux("https://api.aliyundrive.com/v2/share_link/get_share_token", jSONObject.toString(), I()));
                    String string = jSONObject2.getString("share_token");
                    wF.put(str, Long.valueOf(wF2 + jSONObject2.getLong("expires_in")));
                    jv.put(str, string);
                    return string;
                } catch (JSONException e) {
                    SpiderDebug.log(e);
                    return "";
                }
            }
        }
    }

    public static Object[] loadsub(String str) {
        try {
            return new Object[]{200, "application/octet-stream", new ByteArrayInputStream(mI.gR(str, I()).getBytes())};
        } catch (Exception e) {
            e.printStackTrace();
            SpiderDebug.log(e);
            return null;
        }
    }

    private static String ux(String str, String str2, Map<String, String> map) {
        PushAgent$1 r0 = new PushAgent$1();
        mI.rp(mI.c(), str, str2, map, r0);
        return (String) r0.getResult();
    }

    public static Object[] vod(Map<String, String> map) {
        String str = map.get("type");
        if (str.equals("m3u8")) {
            return File(map);
        }
        if (!str.equals("media")) {
            return null;
        }
        return ProxyMedia(map);
    }

    protected static long wF() {
        return System.currentTimeMillis() / 1000;
    }

    /* JADX WARN: Not initialized variable reg: 27, insn: 0x0324: MOVE  (r5 I:??[OBJECT, ARRAY]) = (r27 I:??[OBJECT, ARRAY]), block:B:56:0x0324 */
    public String detailContent(List<String> list) {
        String str;
        Exception e;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        Object obj;
        String str7;
        String str8;
        String str9;
        JSONObject jSONObject;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        String str18;
        String str19;
        String str20 = "";
        try {
            str2 = list.get(0);
            str3 = "vod_year";
            str4 = "type_name";
        } catch (Exception e2) {
            e = e2;
            str = str20;
        }
        if (!Uj.jv(str2) || str2.contains("qq.com") || str2.contains("mgtv.com")) {
            String str21 = "$";
            String str22 = "#";
            try {
                try {
                    if (!Uj.jv(str2)) {
                        str6 = "vod_content";
                        obj = "jx";
                        str5 = "vod_play_from";
                        str7 = "vod_actor";
                    } else if (str2.contains("qq.com")) {
                        ArrayList arrayList = new ArrayList();
                        JSONObject jSONObject2 = new JSONObject();
                        JSONArray jSONArray = new JSONArray();
                        JSONObject jSONObject3 = new JSONObject();
                        MJ I2 = Nv.I(mI.gR(str2, V7()));
                        String wF2 = I2.mV("head > title").wF();
                        ArrayList mV = I2.mV("div.episode-list-rect__item");
                        if (!mV.isEmpty()) {
                            int i = 0;
                            while (i < mV.size()) {
                                Xu xu = (Xu) mV.get(i);
                                String I3 = xu.mV("div").I("data-vid");
                                String str23 = "https://v.qq.com/x/cover/" + xu.mV("div").I("data-cid") + "/" + I3;
                                arrayList.add(xu.mV("div span").wF() + str21 + str23);
                                i++;
                                mV = mV;
                                str20 = str20;
                                str3 = str3;
                                str4 = str4;
                            }
                            str19 = str20;
                            str18 = str3;
                            str17 = str4;
                            jSONObject3.put("vod_play_url", TextUtils.join(str22, arrayList));
                        } else {
                            str19 = str20;
                            str18 = str3;
                            str17 = str4;
                            jSONObject3.put("vod_play_url", "立即播放$" + str2);
                        }
                        jSONObject3.put("vod_id", str2);
                        jSONObject3.put("vod_name", wF2);
                        jSONObject3.put("vod_pic", "https://img2.baidu.com/it/u=2655029475,2190949369&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=593");
                        jSONObject3.put(str17, "腾讯视频");
                        jSONObject3.put(str18, str19);
                        jSONObject3.put("vod_area", str19);
                        jSONObject3.put("vod_remarks", str19);
                        jSONObject3.put("vod_actor", str19);
                        jSONObject3.put("vod_director", str19);
                        jSONObject3.put("vod_content", str2);
                        jSONObject3.put("vod_play_from", "jx");
                        jSONArray.put(jSONObject3);
                        jSONObject2.put("list", jSONArray);
                        return jSONObject2.toString();
                    } else {
                        str7 = "vod_actor";
                        str6 = "vod_content";
                        obj = "jx";
                        str5 = "vod_play_from";
                    }
                    String str24 = str20;
                    String str25 = str4;
                    str = "list";
                    try {
                        if (!Uj.jv(str2) || !str2.contains("mgtv.com")) {
                            str = str24;
                            if (Uj.PL(str2)) {
                                JSONObject jSONObject4 = new JSONObject();
                                JSONArray jSONArray2 = new JSONArray();
                                JSONObject jSONObject5 = new JSONObject();
                                jSONObject5.put("vod_id", str2);
                                jSONObject5.put("vod_name", str2);
                                jSONObject5.put("vod_pic", "https://pic.rmb.bdstatic.com/bjh/1d0b02d0f57f0a42201f92caba5107ed.jpeg");
                                jSONObject5.put(str25, "直连");
                                jSONObject5.put(str5, "player");
                                jSONObject5.put("vod_play_url", "立即播放$" + str2);
                                jSONArray2.put(jSONObject5);
                                jSONObject4.put(str, jSONArray2);
                                return jSONObject4.toString();
                            } else if (str2.startsWith("magnet:")) {
                                Matcher matcher = Pattern.compile("(^|&)dn=([^&]*)(&|$)").matcher(URLDecoder.decode(str2));
                                String group = matcher.find() ? matcher.group(2) : str;
                                JSONObject jSONObject6 = new JSONObject();
                                JSONArray jSONArray3 = new JSONArray();
                                JSONObject jSONObject7 = new JSONObject();
                                jSONObject7.put("vod_id", str2);
                                if (group.equals(str)) {
                                    group = str2;
                                }
                                jSONObject7.put("vod_name", group);
                                jSONObject7.put("vod_pic", "https://pic.rmb.bdstatic.com/bjh/1d0b02d0f57f0a42201f92caba5107ed.jpeg");
                                jSONObject7.put(str25, "磁力链接");
                                jSONObject7.put(str6, str2);
                                jSONObject7.put(str5, "magnet");
                                jSONObject7.put("vod_play_url", "立即播放$" + str2);
                                jSONArray3.put(jSONObject7);
                                jSONObject6.put(str, jSONArray3);
                                return jSONObject6.toString();
                            } else {
                                try {
                                    if (p.matcher(str2).find()) {
                                        Matcher matcher2 = F.matcher(str2);
                                        if (!matcher2.find()) {
                                            return str;
                                        }
                                        String group2 = matcher2.group(1);
                                        String group3 = matcher2.groupCount() == 3 ? matcher2.group(3) : str;
                                        JSONObject jSONObject8 = new JSONObject();
                                        jSONObject8.put("share_id", group2);
                                        JSONObject jSONObject9 = new JSONObject(ux("https://api.aliyundrive.com/adrive/v3/share_link/get_share_by_anonymous", jSONObject8.toString(), I()));
                                        JSONArray jSONArray4 = jSONObject9.getJSONArray("file_infos");
                                        if (jSONArray4.length() == 0) {
                                            return str;
                                        }
                                        if (!TextUtils.isEmpty(group3)) {
                                            int i2 = 0;
                                            while (true) {
                                                if (i2 >= jSONArray4.length()) {
                                                    str8 = str21;
                                                    str9 = str22;
                                                    jSONObject = null;
                                                    break;
                                                }
                                                JSONObject jSONObject10 = jSONArray4.getJSONObject(i2);
                                                str9 = str22;
                                                str8 = str21;
                                                if (jSONObject10.getString("file_id").equals(jSONObject10.getString("file_id"))) {
                                                    jSONObject = jSONObject10;
                                                    break;
                                                }
                                                i2++;
                                                str22 = str9;
                                                str21 = str8;
                                            }
                                            str10 = group3;
                                        } else {
                                            str8 = str21;
                                            str9 = str22;
                                            JSONObject jSONObject11 = jSONArray4.getJSONObject(0);
                                            jSONObject = jSONObject11;
                                            str10 = jSONObject11.getString("file_id");
                                        }
                                        JSONObject jSONObject12 = new JSONObject();
                                        jSONObject12.put("vod_id", str2);
                                        jSONObject12.put("vod_name", jSONObject9.getString("share_name"));
                                        jSONObject12.put("vod_pic", jSONObject9.getString("avatar"));
                                        jSONObject12.put(str6, str2);
                                        jSONObject12.put(str25, "阿里云盘");
                                        if (rp.equals("asc")) {
                                            jSONObject12.put(str5, "AliYun$$$AliYun原画");
                                        } else {
                                            jSONObject12.put(str5, "AliYun原画$$$AliYun");
                                        }
                                        ArrayList arrayList2 = new ArrayList();
                                        if (!jSONObject.getString("type").equals("folder")) {
                                            if (jSONObject.getString("type").equals("file") && jSONObject.getString("category").equals("video")) {
                                                str10 = "root";
                                            }
                                            return str;
                                        }
                                        String jv2 = jv(group2, str);
                                        Map<String, String> hashMap = new HashMap<>();
                                        listFiles(hashMap, group2, jv2, str10);
                                        ArrayList arrayList3 = new ArrayList(hashMap.keySet());
                                        Collections.sort(arrayList3);
                                        Iterator it = arrayList3.iterator();
                                        while (it.hasNext()) {
                                            arrayList2.add(((String) it.next()) + str8 + hashMap.get(str11));
                                            str8 = str8;
                                        }
                                        ArrayList arrayList4 = new ArrayList();
                                        arrayList4.add(TextUtils.join(str9, arrayList2));
                                        arrayList4.add(TextUtils.join(str9, arrayList2));
                                        jSONObject12.put("vod_play_url", TextUtils.join("$$$", arrayList4));
                                        JSONObject jSONObject13 = new JSONObject();
                                        JSONArray jSONArray5 = new JSONArray();
                                        jSONArray5.put(jSONObject12);
                                        jSONObject13.put(str, jSONArray5);
                                        return jSONObject13.toString();
                                    }
                                    if (!str2.startsWith("http://") && !str2.startsWith("https://")) {
                                        return str;
                                    }
                                    JSONObject jSONObject14 = new JSONObject();
                                    JSONArray jSONArray6 = new JSONArray();
                                    JSONObject jSONObject15 = new JSONObject();
                                    jSONObject15.put("vod_id", str2);
                                    jSONObject15.put("vod_name", str2);
                                    jSONObject15.put("vod_pic", "https://pic.rmb.bdstatic.com/bjh/1d0b02d0f57f0a42201f92caba5107ed.jpeg");
                                    jSONObject15.put(str25, "网页");
                                    jSONObject15.put(str5, "嗅探");
                                    jSONObject15.put("vod_play_url", "立即播放$" + str2);
                                    jSONArray6.put(jSONObject15);
                                    jSONObject14.put(str, jSONArray6);
                                    return jSONObject14.toString();
                                } catch (Exception e3) {
                                    e = e3;
                                }
                            }
                        } else {
                            ArrayList arrayList5 = new ArrayList();
                            JSONObject jSONObject16 = new JSONObject();
                            JSONArray jSONArray7 = new JSONArray();
                            JSONObject jSONObject17 = new JSONObject();
                            Matcher matcher3 = Pattern.compile("https://\\S+mgtv.com/b/(\\d+)/(\\d+).html.*").matcher(str2);
                            if (matcher3.find()) {
                                str15 = "vod_area";
                                StringBuilder sb = new StringBuilder();
                                str14 = str24;
                                sb.append("https://pcweb.api.mgtv.com/episode/list?video_id=");
                                sb.append(matcher3.group(2));
                                str13 = str3;
                                JSONObject jSONObject18 = new JSONObject(mI.gR(sb.toString(), V7()));
                                str24 = jSONObject18.getJSONObject("data").getJSONObject("info").getString("title");
                                JSONArray jSONArray8 = new JSONArray(jSONObject18.getJSONObject("data").getString(str));
                                if (jSONArray8.length() > 0) {
                                    int i3 = 0;
                                    while (i3 < jSONArray8.length()) {
                                        JSONObject jSONObject19 = jSONArray8.getJSONObject(i3);
                                        if (jSONObject19.getString("isIntact").equals("1")) {
                                            String string = jSONObject19.getString("t4");
                                            String string2 = jSONObject19.getString("video_id");
                                            StringBuilder sb2 = new StringBuilder();
                                            str = str;
                                            sb2.append("https://www.mgtv.com/b/");
                                            str25 = str25;
                                            sb2.append(matcher3.group(1));
                                            sb2.append("/");
                                            sb2.append(string2);
                                            sb2.append(".html");
                                            arrayList5.add(string + str21 + sb2.toString());
                                        } else {
                                            str25 = str25;
                                            str = str;
                                        }
                                        i3++;
                                        jSONArray8 = jSONArray8;
                                    }
                                    str12 = str25;
                                    str16 = str;
                                    jSONObject17.put("vod_play_url", TextUtils.join(str22, arrayList5));
                                } else {
                                    str12 = str25;
                                    str16 = str;
                                    jSONObject17.put("vod_play_url", "立即播放$" + str2);
                                }
                            } else {
                                str12 = str25;
                                str16 = str;
                                str14 = str24;
                                str13 = str3;
                                str15 = "vod_area";
                            }
                            try {
                                jSONObject17.put("vod_id", str2);
                                jSONObject17.put("vod_name", str24);
                                jSONObject17.put("vod_pic", "https://img2.baidu.com/it/u=2562822927,704100654&fm=253&fmt=auto&app=138&f=JPEG?w=600&h=380");
                                jSONObject17.put(str12, "芒果视频");
                                jSONObject17.put(str13, str14);
                                jSONObject17.put(str15, str14);
                                jSONObject17.put("vod_remarks", str14);
                                jSONObject17.put(str7, str14);
                                jSONObject17.put("vod_director", str14);
                                jSONObject17.put(str6, str2);
                                jSONObject17.put(str5, obj);
                                jSONArray7.put(jSONObject17);
                                jSONObject16.put(str16, jSONArray7);
                                return jSONObject16.toString();
                            } catch (Exception e4) {
                                e = e4;
                                str = str14;
                            }
                        }
                    } catch (Exception e5) {
                        e = e5;
                    }
                } catch (Exception e6) {
                    e = e6;
                    str = "vod_actor";
                }
            } catch (Exception e7) {
                e = e7;
            }
            SpiderDebug.log(e);
            return str;
        }
        JSONObject jSONObject20 = new JSONObject();
        JSONArray jSONArray9 = new JSONArray();
        JSONObject jSONObject21 = new JSONObject();
        jSONObject21.put("vod_id", str2);
        jSONObject21.put("vod_name", str2);
        jSONObject21.put("vod_pic", "https://pic.rmb.bdstatic.com/bjh/1d0b02d0f57f0a42201f92caba5107ed.jpeg");
        jSONObject21.put(str4, "官源");
        jSONObject21.put(str3, str20);
        jSONObject21.put("vod_area", str20);
        jSONObject21.put("vod_remarks", str20);
        jSONObject21.put("vod_actor", str20);
        jSONObject21.put("vod_director", str20);
        jSONObject21.put("vod_content", str20);
        jSONObject21.put("vod_play_from", "jx");
        jSONObject21.put("vod_play_url", "立即播放$" + str2);
        jSONArray9.put(jSONObject21);
        jSONObject20.put("list", jSONArray9);
        return jSONObject20.toString();
    }

    public void init(Context context, String str) {
        if (str.isEmpty()) {
            str = "http://home.jundie.top:666/token.txt;1";
        }
        String str2 = str.split(";")[0];
        rp = str.split(";")[1];
        if (str2.isEmpty() || str2.equals("token")) {
            str2 = "http://home.jundie.top:666/token.txt";
        }
        PushAgent.super.init(context, str2);
        if (str2.startsWith("http")) {
            PL = mI.gR(str2, (Map) null);
        } else {
            PL = str2;
        }
    }

    public void listFiles(Map<String, String> map, String str, String str2, String str3) {
        Exception e;
        String str4 = "_";
        try {
            String str5 = "https://api.aliyundrive.com/adrive/v3/file/list";
            HashMap<String, String> I2 = I();
            I2.put("x-share-token", str2);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("image_thumbnail_process", "image/resize,w_160/format,jpeg");
            jSONObject.put("image_url_process", "image/resize,w_1920/format,jpeg");
            jSONObject.put("limit", 200);
            jSONObject.put("order_by", "updated_at");
            jSONObject.put("order_direction", "DESC");
            jSONObject.put("parent_file_id", str3);
            jSONObject.put("share_id", str);
            jSONObject.put("video_thumbnail_process", "video/snapshot,t_1000,f_jpg,ar_auto,w_300");
            String str6 = "";
            ArrayList arrayList = new ArrayList();
            int i = 1;
            for (int i2 = 1; i <= 50 && (i <= i2 || !str6.isEmpty()); i2 = 1) {
                jSONObject.put("marker", str6);
                JSONObject jSONObject2 = new JSONObject(ux(str5, jSONObject.toString(), I2));
                JSONArray jSONArray = jSONObject2.getJSONArray("items");
                int i3 = 0;
                while (i3 < jSONArray.length()) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i3);
                    if (jSONObject3.getString("type").equals("folder")) {
                        arrayList.add(jSONObject3.getString("file_id"));
                        str4 = str4;
                        I2 = I2;
                    } else {
                        I2 = I2;
                        if (!jSONObject3.getString("mime_type").contains("video") && !jSONObject3.getString("mime_type").contains("vnd.rn-realmedia-vbr")) {
                            str4 = str4;
                        }
                        String replace = jSONObject3.getString("name").replace("#", str4).replace("$", str4);
                        str4 = str4;
                        if (map.size() <= 2000) {
                            map.put(replace, str + "+" + str2 + "+" + jSONObject3.getString("file_id") + "+" + jSONObject3.getString("category"));
                        } else {
                            return;
                        }
                    }
                    i3++;
                    str5 = str5;
                }
                str6 = jSONObject2.getString("next_marker");
                i++;
                str5 = str5;
                I2 = I2;
                str4 = str4;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    listFiles(map, str, str2, (String) it.next());
                } catch (Exception e2) {
                    try {
                        SpiderDebug.log(e2);
                        return;
                    } catch (Exception e3) {
                        e = e3;
                        SpiderDebug.log(e);
                        return;
                    }
                }
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    public String playerContent(String str, String str2, List<String> list) {
        if (str.equals("jx")) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("parse", 1);
            jSONObject.put("jx", "1");
            jSONObject.put("url", str2);
            return jSONObject.toString();
        } else if (str.equals("嗅探")) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("parse", 1);
            jSONObject2.put("playUrl", "");
            jSONObject2.put("url", str2);
            return jSONObject2.toString();
        } else {
            if (!str.equals("player") && !str.equals("magnet")) {
                if (str.equals("AliYun")) {
                    PL();
                    String[] split = str2.split("\\+");
                    String str3 = Proxy.localProxyUrl() + "?do=push&type=m3u8&share_id=" + split[0] + "&file_id=" + split[2];
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.54 Safari/537.36");
                    jSONObject3.put("Referer", "https://www.aliyundrive.com/");
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("parse", "0");
                    jSONObject4.put("playUrl", "");
                    jSONObject4.put("url", str3);
                    jSONObject4.put("header", jSONObject3.toString());
                    return jSONObject4.toString();
                }
                if (str.equals("AliYun原画")) {
                    PL();
                    String[] split2 = str2.split("\\+");
                    String c2 = c(split2[0], split2[1], split2[2], split2[3]);
                    HashMap hashMap = new HashMap();
                    mI.sP(c2, I(), hashMap);
                    String jv2 = mI.jv(hashMap);
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("parse", "0");
                    jSONObject5.put("playUrl", "");
                    jSONObject5.put("url", jv2);
                    jSONObject5.put("header", new JSONObject(I()).toString());
                    return jSONObject5.toString();
                }
                return "";
            }
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("parse", 0);
            jSONObject6.put("playUrl", "");
            jSONObject6.put("url", str2);
            return jSONObject6.toString();
        }
    }

    public String searchContent(String str, boolean z) {
        try {
            String trim = str.trim();
            if (!p.matcher(trim).find()) {
                return "";
            }
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("vod_id", trim);
            jSONObject.put("vod_name", trim);
            jSONArray.put(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("list", jSONArray);
            return jSONObject2.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }
}
