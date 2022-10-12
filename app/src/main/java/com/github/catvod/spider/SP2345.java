package com.github.catvod.spider;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.merge.M;
import com.github.catvod.spider.p0002345.C0003AE;
import com.github.catvod.spider.p0002345.C0042G;
import com.github.catvod.spider.p0002345.C0054Hk;
import com.github.catvod.spider.p0002345.C0065JV;
import com.github.catvod.spider.p0002345.C0185Se;
import com.github.catvod.spider.p0002345.C0292at;
import com.github.catvod.spider.p0002345.C0304d;
import com.github.catvod.spider.p0002345.OBj;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import okhttp3.Call;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SP2345 extends Spider {

    /* renamed from: MM */
    protected JSONObject f1128MM = null;

    /* renamed from: j */
    public Integer f1129j = 0;

    /* renamed from: MM */
    private String m3MM(String str) {
        String m1278d = OBj.m1278d("4C");
        if (str.endsWith(m1278d)) {
            str = str.substring(0, str.length() - 1);
        }
        return str.substring(str.lastIndexOf(m1278d) + 1).replace(m1278d, "").replace(OBj.m1278d("4D091B2F25"), "");
    }

    /* renamed from: PA */
    private String m2PA(String str) {
        try {
            String[] split = str.split(OBj.m1278d("3F1D"));
            String str2 = split[0];
            String str3 = split[1];
            String str4 = OBj.m1278d("0B151B323A7F") + this.f1128MM.getJSONObject(OBj.m1278d("16130331")).getString(str2);
            boolean equals = str2.equals(OBj.m1278d("07080E2C302C0D06"));
            String m1278d = OBj.m1278d("4D091B2F25");
            if (!equals && !str2.equals(OBj.m1278d("07080E2C3A2D0A0B1A"))) {
                if (str2.equals(OBj.m1278d("190E0125302C"))) {
                    return str4 + str3 + OBj.m1278d("4C");
                } else if (!str2.equals(OBj.m1278d("070E012524240D"))) {
                    return str4;
                } else {
                    return str4 + OBj.m1278d("070C40") + str3 + m1278d;
                }
            }
            return str4 + OBj.m1278d("07041B2320294C") + str3 + m1278d;
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: W */
    private String m1W(String str) {
        SpiderDebug.log(str);
        M<String> m = new M<String>() { // from class: com.github.catvod.spider.SP2345.1
            public void onFailure(Call call, Exception exc) {
                m1339j("");
                SpiderDebug.log(exc);
            }

            public void onResponse(String str2) {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public String onParseResponse(Call call, Response response) {
                try {
                    return new String(response.body().bytes(), OBj.m1278d("24235D717877"));
                } catch (IOException unused) {
                    return "";
                }
            }
        };
        C0042G.m1629PA(C0042G.m1628W(), str, null, getHeaders(str), m);
        return (String) m.getResult();
    }

    private HashMap<String, String> getHeaders(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(OBj.m1278d("36120A30640404040136"), OBj.m1278d("2E0E152B2529024E5A6C79654B2D062C3C3D58412E2C2D370C080B627F6B534F5E7969080C1500620E654B55466B6904131103271E20012A06366670505641717F654B2A271604094F41032B222043260A21222A4A412C2A3B2A0E04407A7F6B534F5B707D754D50567A69080C03062E2C65300009233B2C4C545C75677655"));
        return hashMap;
    }

    /* renamed from: j */
    private String m0j(String str, String str2, HashMap<String, String> hashMap) {
        String str3;
        String str4;
        String str5;
        String m1278d = OBj.m1278d("02130A23");
        String m1278d2 = OBj.m1278d("1A040E30");
        String m1278d3 = OBj.m1278d("17181F27");
        String m1278d4 = OBj.m1278d("4E");
        try {
            String str6 = OBj.m1278d("0B151B323A7F") + this.f1128MM.getJSONObject(OBj.m1278d("16130331")).getString(str) + OBj.m1278d("0E20052331290A121B6D");
            if (hashMap == null || hashMap.size() <= 0) {
                str3 = "";
                str4 = str3;
                str5 = str4;
            } else {
                str5 = hashMap.containsKey(m1278d3) ? hashMap.get(m1278d3) : "";
                str4 = hashMap.containsKey(m1278d2) ? hashMap.get(m1278d2) : "";
                str3 = hashMap.containsKey(m1278d) ? hashMap.get(m1278d) : "";
            }
            boolean equals = str.equals(OBj.m1278d("07080E2C302C0D06"));
            String m1278d5 = OBj.m1278d("5C110E252C78");
            if (!equals && !str.equals(OBj.m1278d("07080E2C3A2D0A0B1A"))) {
                if (str.equals(OBj.m1278d("190E0125302C"))) {
                    return str6 + OBj.m1278d("0F") + str5 + str3 + m1278d5 + str2;
                } else if (!str.equals(OBj.m1278d("070E012524240D"))) {
                    return str6;
                } else {
                    return str6 + OBj.m1278d("0F15") + str5 + str3 + str4 + m1278d5 + str2;
                }
            }
            return (str6 + str5 + m1278d4 + str3 + m1278d4 + str4 + m1278d4 + str2 + m1278d5 + str2 + "").replace(OBj.m1278d("171741"), OBj.m1278d("14001F363F6B"));
        } catch (Exception unused) {
            return "";
        }
    }

    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        String str3;
        if (this.f1129j.intValue() == 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                String m0j = m0j(str, str2, hashMap);
                C0065JV m1732C2 = C0292at.m831MM(new JSONObject(m1W(m0j)).getString(OBj.m1278d("0B15022E"))).m1732C2(OBj.m1278d("10040C36202A0D4F1C2A2632430D06"));
                JSONArray jSONArray = new JSONArray();
                Iterator<C0003AE> it = m1732C2.iterator();
                while (it.hasNext()) {
                    C0003AE next = it.next();
                    String m1532MM = next.m1732C2(OBj.m1278d("024F0D26642C0E06")).m1532MM(OBj.m1278d("0B130A24"));
                    String str4 = str + OBj.m1278d("1F") + m3MM(m1532MM);
                    String m1525o4 = next.m1732C2(OBj.m1278d("4D1506362520")).m1525o4();
                    String m746PA = C0304d.m746PA(m0j, next.m1732C2(OBj.m1278d("0A0C08")).m1532MM(OBj.m1278d("10130C")));
                    try {
                        str3 = next.m1732C2(OBj.m1278d("10110E2C6736000E1D2769200E")).m1525o4();
                    } catch (Exception unused) {
                        str3 = "";
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(OBj.m1278d("150E0B1D2021"), str4);
                    jSONObject2.put(OBj.m1278d("150E0B1D27240E04"), m1525o4);
                    jSONObject2.put(OBj.m1278d("150E0B1D392C00"), m746PA);
                    jSONObject2.put(OBj.m1278d("150E0B1D3B200E001D293A"), str3);
                    jSONArray.put(jSONObject2);
                }
                if (jSONArray.length() == 0) {
                    return "";
                }
                jSONObject.put(OBj.m1278d("13000827"), str2);
                jSONObject.put(OBj.m1278d("130008272A2A160F1B"), Integer.MAX_VALUE);
                jSONObject.put(OBj.m1278d("0F08022B3D"), 90);
                jSONObject.put(OBj.m1278d("170E1B2325"), Integer.MAX_VALUE);
                jSONObject.put(OBj.m1278d("0F081C36"), jSONArray);
                return jSONObject.toString();
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
        }
        return "";
    }

    public String detailContent(List<String> list) {
        ArrayList arrayList;
        String str;
        ArrayList arrayList2;
        ArrayList arrayList3;
        C0065JV c0065jv;
        SP2345 sp2345 = this;
        String m1278d = OBj.m1278d("47454B");
        String m1278d2 = OBj.m1278d("10");
        if (sp2345.f1129j.intValue() == 0) {
            try {
                String m2PA = sp2345.m2PA(list.get(0));
                C0054Hk m831MM = C0292at.m831MM(sp2345.m1W(m2PA));
                C0065JV m1732C2 = m831MM.m1732C2(OBj.m1278d("0708196C392A10150A30192902020A2A262907041D"));
                C0065JV m1732C22 = m831MM.m1732C2(OBj.m1278d("0708196C3D3D172801363B2A200E01"));
                String trim = m1732C22.m1526m(OBj.m1278d("0708196C3D2C175F0773")).m1525o4().trim();
                String m746PA = C0304d.m746PA(m2PA, m1732C2.m1526m(OBj.m1278d("0708196C392C005F062F2E")).m1532MM(OBj.m1278d("10130C")));
                C0065JV m1526m = m1732C22.m1526m(OBj.m1278d("060C412724110A15"));
                String str2 = "";
                String str3 = str2;
                String str4 = str3;
                String str5 = str4;
                String str6 = str5;
                String str7 = str6;
                int i = 0;
                while (i < m1526m.size()) {
                    C0003AE c0003ae = m1526m.get(i);
                    C0065JV c0065jv2 = m1526m;
                    String replaceAll = c0003ae.m1702h().replaceAll(m1278d2, "");
                    String str8 = m1278d;
                    if (replaceAll.contains(OBj.m1278d("84D0D4"))) {
                        try {
                            str2 = c0003ae.m1708UR().m1702h().replaceAll(m1278d2, "");
                        } catch (Exception unused) {
                        }
                    } else {
                        if (!replaceAll.contains(OBj.m1278d("86D8DB")) && !replaceAll.contains(OBj.m1278d("86D8DB62ADFEDE"))) {
                            if (replaceAll.contains(OBj.m1278d("85DDFB"))) {
                                str5 = c0003ae.m1708UR().m1702h().replaceAll(m1278d2, "");
                            } else if (replaceAll.contains(OBj.m1278d("86CED3"))) {
                                str6 = c0003ae.m1708UR().m1702h().replaceAll(m1278d2, "");
                            } else if (replaceAll.contains(OBj.m1278d("86FDDF"))) {
                                str4 = c0003ae.m1708UR().m1702h().replaceAll(m1278d2, "");
                            } else if (replaceAll.contains(OBj.m1278d("84CFEF"))) {
                                str7 = c0003ae.mo466hS().m1732C2(OBj.m1278d("134F1F11212A145F1C32282B")).m1525o4().trim();
                            }
                        }
                        str3 = c0003ae.m1708UR().m1702h().replaceAll(m1278d2, "");
                    }
                    i++;
                    m1526m = c0065jv2;
                    m1278d = str8;
                }
                String str9 = m1278d;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(OBj.m1278d("150E0B1D2021"), list.get(0));
                jSONObject.put(OBj.m1278d("150E0B1D27240E04"), trim);
                jSONObject.put(OBj.m1278d("150E0B1D392C00"), m746PA);
                jSONObject.put(OBj.m1278d("17181F27162B020C0A"), str2);
                jSONObject.put(OBj.m1278d("150E0B1D30200213"), str3);
                jSONObject.put(OBj.m1278d("150E0B1D28370600"), str4);
                jSONObject.put(OBj.m1278d("150E0B1D3B200E001D293A"), "");
                jSONObject.put(OBj.m1278d("150E0B1D2826170E1D"), str5);
                jSONObject.put(OBj.m1278d("150E0B1D2D2C11040C362637"), str6);
                jSONObject.put(OBj.m1278d("150E0B1D2A2A0D150A2C3D"), str7);
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = list.get(0).trim().startsWith(OBj.m1278d("190E0125302C1F")) ? new ArrayList() : null;
                C0065JV m1526m2 = m1732C22.m1526m(OBj.m1278d("0708196C3A2011080A3164310203422E2C23175F0E"));
                for (int i2 = 0; i2 < m1526m2.size(); i2++) {
                    C0003AE c0003ae2 = m1526m2.get(i2);
                    if (arrayList5 != null) {
                        arrayList5.add(c0003ae2.mo21W(OBj.m1278d("07001B2364241308")));
                    }
                    arrayList4.add(c0003ae2.m1702h().trim());
                }
                ArrayList arrayList6 = new ArrayList();
                String m1278d3 = OBj.m1278d("47");
                String m1278d4 = OBj.m1278d("40");
                if (arrayList5 != null) {
                    String replace = list.get(0).split(OBj.m1278d("3F1D"))[1].replace(OBj.m1278d("191830"), "");
                    C0065JV m1526m3 = m1732C22.m1526m(OBj.m1278d("0708196C3A2011080A3164310203423020220B15422B"));
                    int i3 = 0;
                    while (i3 < m1526m3.size()) {
                        C0065JV m1732C23 = m1526m3.get(i3).m1732C2(OBj.m1278d("0708196C3A2011080A3164310203423020220B15423A20240F00422B3D200E"));
                        ArrayList arrayList7 = new ArrayList();
                        int i4 = 0;
                        while (i4 < m1732C23.size() && i4 < 5) {
                            try {
                                StringBuilder sb = new StringBuilder();
                                sb.append(OBj.m1278d("0B151B323A7F4C4E0423276B51525B7767260C0C4027392C100E0B2728350A4E01273E0106150E2B256A10041B0C2C3226110631262106201F2B667A0A0552"));
                                sb.append(replace);
                                sb.append(OBj.m1278d("45001F2B74"));
                                sb.append(arrayList5.get(i3));
                                sb.append(OBj.m1278d("45180A233B78"));
                                str = replace;
                                try {
                                    sb.append(m1732C23.get(i4).mo21W(OBj.m1278d("07001B23643C06001D")).trim());
                                    sb.append(OBj.m1278d("450C002C3D2D5E"));
                                    sb.append(m1732C23.get(i4).mo21W(OBj.m1278d("07001B2364280C0F1B2A")).trim());
                                    sb.append(OBj.m1278d("453E52"));
                                    arrayList3 = arrayList6;
                                    c0065jv = m1526m3;
                                    try {
                                        sb.append(System.currentTimeMillis());
                                        String sb2 = sb.toString();
                                        JSONObject jSONObject2 = new JSONObject(C0042G.m1633A(sb2, sp2345.getHeaders(sb2)).substring(1));
                                        if (jSONObject2.getInt(OBj.m1278d("000E0B27")) == 200) {
                                            JSONArray jSONArray = jSONObject2.getJSONArray(OBj.m1278d("07001B23"));
                                            int i5 = 0;
                                            while (i5 < jSONArray.length()) {
                                                JSONObject jSONObject3 = jSONArray.getJSONObject(i5);
                                                StringBuilder sb3 = new StringBuilder();
                                                arrayList2 = arrayList5;
                                                try {
                                                    sb3.append(jSONObject3.getString(OBj.m1278d("0A121C372C")));
                                                    sb3.append(m1278d3);
                                                    sb3.append(jSONObject3.getString(OBj.m1278d("161303")));
                                                    arrayList7.add(sb3.toString());
                                                    i5++;
                                                    arrayList5 = arrayList2;
                                                } catch (Exception unused2) {
                                                }
                                            }
                                        }
                                    } catch (Exception unused3) {
                                    }
                                    arrayList2 = arrayList5;
                                } catch (Exception unused4) {
                                    arrayList2 = arrayList5;
                                    arrayList3 = arrayList6;
                                    c0065jv = m1526m3;
                                    i4++;
                                    sp2345 = this;
                                    arrayList6 = arrayList3;
                                    m1526m3 = c0065jv;
                                    arrayList5 = arrayList2;
                                    replace = str;
                                }
                            } catch (Exception unused5) {
                                str = replace;
                            }
                            i4++;
                            sp2345 = this;
                            arrayList6 = arrayList3;
                            m1526m3 = c0065jv;
                            arrayList5 = arrayList2;
                            replace = str;
                        }
                        String str10 = replace;
                        ArrayList arrayList8 = arrayList5;
                        ArrayList arrayList9 = arrayList6;
                        C0065JV c0065jv3 = m1526m3;
                        if (arrayList7.size() == 0 && arrayList4.size() > i3) {
                            arrayList4.set(i3, "");
                        }
                        arrayList9.add(TextUtils.join(m1278d4, arrayList7));
                        i3++;
                        sp2345 = this;
                        arrayList6 = arrayList9;
                        m1526m3 = c0065jv3;
                        arrayList5 = arrayList8;
                        replace = str10;
                    }
                    arrayList = arrayList6;
                } else {
                    arrayList = arrayList6;
                    C0065JV m1526m4 = m1732C22.m1526m(OBj.m1278d("0708196C3A2011080A3164260C0F422B"));
                    for (int i6 = 0; i6 < m1526m4.size(); i6++) {
                        C0065JV m1732C24 = m1526m4.get(i6).m1732C2(OBj.m1278d("02"));
                        ArrayList arrayList10 = new ArrayList();
                        Iterator<C0003AE> it = m1732C24.iterator();
                        while (it.hasNext()) {
                            C0003AE next = it.next();
                            arrayList10.add(next.m1702h().trim() + m1278d3 + next.mo21W(OBj.m1278d("0B130A24")).trim());
                        }
                        if (arrayList10.size() == 0 && arrayList4.size() > i6) {
                            arrayList4.set(i6, "");
                        }
                        arrayList.add(TextUtils.join(m1278d4, arrayList10));
                    }
                }
                for (int size = arrayList4.size() - 1; size >= 0; size--) {
                    if (((String) arrayList4.get(size)).isEmpty()) {
                        arrayList4.remove(size);
                    }
                }
                for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                    if (((String) arrayList.get(size2)).isEmpty()) {
                        arrayList.remove(size2);
                    }
                }
                for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    if (size3 >= arrayList4.size()) {
                        arrayList.remove(size3);
                    }
                }
                String join = TextUtils.join(str9, arrayList4);
                String join2 = TextUtils.join(str9, arrayList);
                jSONObject.put(OBj.m1278d("150E0B1D3929021830243B2A0E"), join);
                jSONObject.put(OBj.m1278d("150E0B1D3929021830373B29"), join2);
                JSONObject jSONObject4 = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.put(jSONObject);
                jSONObject4.put(OBj.m1278d("0F081C36"), jSONArray2);
                return jSONObject4.toString();
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
        }
        return "";
    }

    public String homeContent(boolean z) {
        String str;
        String m1278d = OBj.m1278d("16130331");
        if (this.f1129j.intValue() == 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(OBj.m1278d("000D0E313A"), this.f1128MM.getJSONArray(OBj.m1278d("000D0E313A2010")));
                if (z) {
                    jSONObject.put(OBj.m1278d("050803362C3710"), this.f1128MM.getJSONObject(OBj.m1278d("050803362C37")));
                }
                try {
                    String str2 = OBj.m1278d("0B151B323A7F") + this.f1128MM.getJSONObject(m1278d).getString(OBj.m1278d("11040C2D2428060F0B"));
                    C0065JV m1732C2 = C0292at.m831MM(m1W(str2)).m1732C2(OBj.m1278d("10040C36202A0D4F1C2A2632430D06"));
                    JSONArray jSONArray = new JSONArray();
                    Iterator<C0003AE> it = m1732C2.iterator();
                    String str3 = null;
                    while (it.hasNext()) {
                        C0003AE next = it.next();
                        String replace = next.m1732C2(OBj.m1278d("024F0D26642C0E06")).m1532MM(OBj.m1278d("0B130A24")).replace(OBj.m1278d("14001F363F6B"), OBj.m1278d("171741"));
                        JSONObject jSONObject2 = this.f1128MM.getJSONObject(m1278d);
                        Iterator<String> keys = jSONObject2.keys();
                        while (true) {
                            if (keys.hasNext()) {
                                String next2 = keys.next();
                                if (replace.contains(jSONObject2.getString(next2))) {
                                    str = next2 + OBj.m1278d("1F") + m3MM(replace);
                                    break;
                                }
                            } else {
                                str = null;
                                break;
                            }
                        }
                        if (str != null) {
                            String m1525o4 = next.m1732C2(OBj.m1278d("4D1506362520")).m1525o4();
                            String m746PA = C0304d.m746PA(str2, next.m1732C2(OBj.m1278d("0A0C08")).m1532MM(OBj.m1278d("10130C")));
                            try {
                                str3 = next.m1732C2(OBj.m1278d("10110E2C6736000E1D2769200E")).m1525o4();
                            } catch (Exception unused) {
                            }
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put(OBj.m1278d("150E0B1D2021"), str);
                            jSONObject3.put(OBj.m1278d("150E0B1D27240E04"), m1525o4);
                            jSONObject3.put(OBj.m1278d("150E0B1D392C00"), m746PA);
                            jSONObject3.put(OBj.m1278d("150E0B1D3B200E001D293A"), str3);
                            jSONArray.put(jSONObject3);
                        }
                    }
                    jSONObject.put(OBj.m1278d("0F081C36"), jSONArray);
                } catch (Exception e) {
                    SpiderDebug.log(e);
                }
                return jSONObject.toString();
            } catch (Exception unused2) {
                return "";
            }
        }
        return "";
    }

    public void init(Context context, String str) {
        String m1278d = OBj.m1278d("0E00006F");
        super.init(context, str);
        try {
            if (str.trim().startsWith(m1278d)) {
                str = C0185Se.m1051A(str.replace(m1278d, ""));
                try {
                    str = new String(Base64.decode(str, 2), OBj.m1278d("3635296F71"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (str.trim().startsWith(OBj.m1278d("0B151B32"))) {
                this.f1128MM = new JSONObject(C0185Se.m1032t(C0042G.m1633A(str, null)));
            } else {
                this.f1128MM = new JSONObject(C0185Se.m1032t(str));
            }
        } catch (Exception e2) {
            SpiderDebug.log(e2);
        }
    }

    public String playerContent(String str, String str2, List<String> list) {
        if (this.f1129j.intValue() == 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(OBj.m1278d("13001D312C"), 1);
                jSONObject.put(OBj.m1278d("130D0E3B1C370F"), "");
                jSONObject.put(OBj.m1278d("0919"), OBj.m1278d("52"));
                jSONObject.put(OBj.m1278d("161303"), str2.replace(OBj.m1278d("3F4E"), OBj.m1278d("4C")));
                return jSONObject.toString();
            } catch (Exception e) {
                SpiderDebug.log(e);
            }
        }
        return "";
    }

    public String searchContent(String str, boolean z) {
        String str2;
        String m1278d = OBj.m1278d("16130331");
        if (this.f1129j.intValue() == 0) {
            try {
                JSONObject jSONObject = new JSONObject();
                String str3 = OBj.m1278d("0B151B323A7F") + this.f1128MM.getJSONObject(m1278d).getString(OBj.m1278d("10040E302A2D")).replace(OBj.m1278d("18160B3F"), URLEncoder.encode(str, OBj.m1278d("1615096F71")));
                JSONArray jSONArray = new JSONArray(m1W(str3));
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String trim = jSONObject2.getString(OBj.m1278d("17081B2E2C")).trim();
                    String replace = jSONObject2.getString(OBj.m1278d("1B101A3025")).trim().replace(OBj.m1278d("14001F363F6B"), OBj.m1278d("171741"));
                    JSONObject jSONObject3 = this.f1128MM.getJSONObject(m1278d);
                    Iterator<String> keys = jSONObject3.keys();
                    while (true) {
                        if (!keys.hasNext()) {
                            str2 = null;
                            break;
                        }
                        String next = keys.next();
                        if (replace.contains(jSONObject3.getString(next))) {
                            str2 = next + OBj.m1278d("1F") + m3MM(replace);
                            break;
                        }
                    }
                    if (str2 != null) {
                        String m746PA = C0304d.m746PA(str3, jSONObject2.optString(OBj.m1278d("0A0C08")).trim());
                        if (!trim.isEmpty() && trim.contains(str)) {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put(OBj.m1278d("150E0B1D2021"), str2);
                            jSONObject4.put(OBj.m1278d("150E0B1D27240E04"), trim);
                            jSONObject4.put(OBj.m1278d("150E0B1D392C00"), m746PA);
                            jSONObject4.put(OBj.m1278d("150E0B1D3B200E001D293A"), "");
                            jSONArray2.put(jSONObject4);
                        }
                    }
                }
                jSONObject.put(OBj.m1278d("0F081C36"), jSONArray2);
                return jSONObject.toString();
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
