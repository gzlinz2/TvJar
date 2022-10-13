package com.github.catvod.spider;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.spider.merge.AbstractC0113L;
import com.github.catvod.spider.merge.C0158P;
import com.github.catvod.spider.merge.C0499rH;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Kuaikan extends Spider {

    /* renamed from: H */
    private static String f108H;

    /* renamed from: Ml */
    private static long f109Ml;

    /* renamed from: Sh */
    private static String f110Sh;

    /* renamed from: E */
    private HashMap<String, ArrayList<String>> f111E = new HashMap<>();

    /* renamed from: Ml */
    private void m1634Ml() {
        if (System.currentTimeMillis() > f109Ml) {
            try {
                JSONObject jSONObject = new JSONObject();
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                String m1627s = m1627s("bf70a456195ae394184b2e0b1b471cae1000300001dc431681b806089dac1153fb13960f87" + currentTimeMillis + "notice");
                jSONObject.put("appId", "1000300001");
                jSONObject.put("sign", m1627s);
                jSONObject.put("timestamp", currentTimeMillis);
                String m1632Sh = m1632Sh("bf70a456195ae394", "184b2e0b1b471cae", jSONObject.toString());
                String m1637Ei = m1637Ei("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDitX/rWP6WWc3h\nDzUcfD8Hbw79yCB7rz18GEkjDapjlUVIa3P8FEQFoOchu7N+vdK6HQNsl2Bql4lv\nWOYuzRWNbLGQ7mNtMca28iCuPHck0AcJzOs9F6qruPL73eY7Ai7yvzs2IfEdyXSz\nHP/yXgEOi5ZEBfsBZEPMJw6vfHJVueuD/lh/LkoJ/gXEKMCM/pkIzGpBPd2TsIg8\nZdvOGMPy+MOnJb1Qfe0y3QQdc5VPVdZFWoWsM9RAenHmE3d1THb+uNZZZcnM1tV7\nxYgBbIhgHtXAZnWDWDRBM3xJPR3O9dfBHHt/tbyrZrSwK1IgwFgqfYm/tRbmp4y/\n1DhIF6mDAgMBAAECggEAIzDjS2gEFNiZ0a6nouVSb1f47sHq8OgR1jp619seMNkR\n6Rzs4xtON8VzO1REl47lsAgi6O9SgxlEtykIiglBqDNQGNw4SNHqM6nAEuvF3sv/\n27CYb2JGFuPdq+UVAOHk4b93dH6uS95ipA8DV97psRVP3P1EqkGjGISTjf/2S4IG\n10Q0UhP4xxiRS2iCtaIq0fbvnrChqYRlYQYpkkRCCH+Q4FNKquSsqmWyCu5yfeQM\nMek+aT9KgvmCh05UCeqC1x7xPtSneNbpJpLD5zW8CcG7EYFDtIP8b9BPDlud47ZH\n5Y4/wyQQFHHLOaniTJVvEJZ7cIoXy2gOhvgcHpNryQKBgQDxNNtXnCfVK0JYNhnU\n5WCOX9NeW9Q79HldhTM5/EqsjA5H21j5S7A/duBSRfX7iou1hyXaa2p+J7rxF9sj\nNlwHPsKhprskxXaHJ7qU84gSmwORsTQQPmtTsizGwVsM330+Q2ZROaSEfDoUqH9+\nKBKs6K+FOldcTOrCZ+ILk8werwKBgQDwnQUrKATzRThVdD6Hcgt/F/SAizDmvtiT\nVvRcGkiV3EtB5Rx8zFNJ9GWrqSAArwIxTWgfYCYsvpUjbJW02Mg0Ufj2+YhjT713\nO9yViwUq996St9eLwGQO5FDMD8FYi7bRAiZEAeAeIEeveDRsNK15++8VVJVo7b2l\nzcapp5k3bQKBgQDSiJNHRhqSet6+xgIIDGoZ+1Qv0TFPX5UrZt8OpsK7FshEOhXQ\nCxt8WZN03HHXK9fEC4GjwwxBrwYB+BOjCYiMHmCd3j0M3HoXgDrgViyYKMuVuDk8\nUG83r5ZbqVuCwfO8i/HbxddueEvtyiD2CZ47ZCIHxKOKAe0K4Mex2UBaKwKBgQDi\nrEouOelP9Kn5dyVhHENQXBTu9DIBb1FAnO5fxjMTWxFa5qhLuYHNbfxKF24atsRu\nBepNhJryFCkT0jvGv2L8Ry0wHiwqwvhO14obJ3ia3iBEQAVDlt+sV9L2KvGOpTB4\n/nlmIA4u02I1IBziw02aWYkGo0SOMUo0ZQL+2PEykQKBgAV1uqiJlWuA9uPdFFJP\nZPL2YoOuTWdWfIwIb/UdsbUuTckCgqPIqUPi3HhiVSunOgcO7HWK3GD3j8k4XEbW\nG4y+Ik0lLrWzBl728tZe1GENzTf8aapElHlUfFSM4OlrTRBsbMOsQBcJNh7QbyoZ\nBHiZZTbRUY6xUR4DGYkCOksF", "bf70a456195ae394184b2e0b1b471cae");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("body", m1632Sh);
                jSONObject2.put("ticket", m1637Ei);
                C0158P.m1246d(C0158P.m1253H(), "http://ctlook.facaishiyi.com/api/v1/user/login.do", jSONObject2.toString(), m1629j(currentTimeMillis), new AbstractC0113L.AbstractC0115i1() { // from class: com.github.catvod.spider.Kuaikan.1
                    @Override // com.github.catvod.spider.merge.AbstractC0113L
                    public void onFailure(Call call, Exception exc) {
                    }

                    @Override // com.github.catvod.spider.merge.AbstractC0113L
                    public void onResponse(String str) {
                        try {
                            JSONObject jSONObject3 = new JSONObject(str);
                            String m1630d = Kuaikan.this.m1630d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiYJ5UFjeEgeUi5ynLKhj5EMF0\n3sawTyAKoPbfUCSZZQ5QdrHhrINggrYxDtB/TWXoqSF+BJP85F0vq8B6gOqFWhK/\n1PIKe2wKlRRuZSOdUzK/3vzRHmR3J3KGrvNzvwARH8gg8xZjbsalc/gBkcVxSxGd\n8j8rJ4QhOSFh9F8B2wIDAQAB", jSONObject3.getString("ticket"));
                            JSONObject jSONObject4 = new JSONObject(Kuaikan.this.m1635H(m1630d.substring(0, 16), m1630d.substring(16, 32), jSONObject3.getString("body")));
                            long unused = Kuaikan.f109Ml = jSONObject4.getLong("duration") * 1000;
                            String unused2 = Kuaikan.f110Sh = jSONObject4.getString("data");
                            Kuaikan.this.m1631Uf();
                        } catch (JSONException unused3) {
                        }
                    }
                });
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: Ei */
    String m1637Ei(String str, String str2) {
        try {
            int i = 0;
            PrivateKey generatePrivate = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str, 0)));
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, generatePrivate);
            byte[] bytes = str2.getBytes();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (bytes.length <= 256) {
                byteArrayOutputStream.write(cipher.doFinal(bytes));
            } else {
                int length = bytes.length;
                while (i < bytes.length) {
                    int i2 = i + 256;
                    byteArrayOutputStream.write(cipher.doFinal(bytes, i, i2 > length ? length - i : 256));
                    i = i2;
                }
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: H */
    String m1635H(String str, String str2, String str3) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(str2.getBytes()));
            return new String(cipher.doFinal(Base64.decode(str3, 0)), "UTF-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: Sh */
    String m1632Sh(String str, String str2, String str3) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(str2.getBytes()));
            return Base64.encodeToString(cipher.doFinal(str3.getBytes()), 2);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* renamed from: Uf */
    public void m1631Uf() {
        try {
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            String m1627s = m1627s("1000300001dc431681b806089dac1153fb13960f8700" + f108H + currentTimeMillis + "notice");
            jSONObject.put("adv", 0);
            jSONObject.put("appId", "1000300001");
            jSONObject.put("nav", 0);
            jSONObject.put("sign", m1627s);
            jSONObject.put("timestamp", currentTimeMillis);
            jSONObject.put("umId", f108H);
            String m1632Sh = m1632Sh(f110Sh.substring(0, 16), f110Sh.substring(16, 32), jSONObject.toString());
            String m1637Ei = m1637Ei("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDitX/rWP6WWc3h\nDzUcfD8Hbw79yCB7rz18GEkjDapjlUVIa3P8FEQFoOchu7N+vdK6HQNsl2Bql4lv\nWOYuzRWNbLGQ7mNtMca28iCuPHck0AcJzOs9F6qruPL73eY7Ai7yvzs2IfEdyXSz\nHP/yXgEOi5ZEBfsBZEPMJw6vfHJVueuD/lh/LkoJ/gXEKMCM/pkIzGpBPd2TsIg8\nZdvOGMPy+MOnJb1Qfe0y3QQdc5VPVdZFWoWsM9RAenHmE3d1THb+uNZZZcnM1tV7\nxYgBbIhgHtXAZnWDWDRBM3xJPR3O9dfBHHt/tbyrZrSwK1IgwFgqfYm/tRbmp4y/\n1DhIF6mDAgMBAAECggEAIzDjS2gEFNiZ0a6nouVSb1f47sHq8OgR1jp619seMNkR\n6Rzs4xtON8VzO1REl47lsAgi6O9SgxlEtykIiglBqDNQGNw4SNHqM6nAEuvF3sv/\n27CYb2JGFuPdq+UVAOHk4b93dH6uS95ipA8DV97psRVP3P1EqkGjGISTjf/2S4IG\n10Q0UhP4xxiRS2iCtaIq0fbvnrChqYRlYQYpkkRCCH+Q4FNKquSsqmWyCu5yfeQM\nMek+aT9KgvmCh05UCeqC1x7xPtSneNbpJpLD5zW8CcG7EYFDtIP8b9BPDlud47ZH\n5Y4/wyQQFHHLOaniTJVvEJZ7cIoXy2gOhvgcHpNryQKBgQDxNNtXnCfVK0JYNhnU\n5WCOX9NeW9Q79HldhTM5/EqsjA5H21j5S7A/duBSRfX7iou1hyXaa2p+J7rxF9sj\nNlwHPsKhprskxXaHJ7qU84gSmwORsTQQPmtTsizGwVsM330+Q2ZROaSEfDoUqH9+\nKBKs6K+FOldcTOrCZ+ILk8werwKBgQDwnQUrKATzRThVdD6Hcgt/F/SAizDmvtiT\nVvRcGkiV3EtB5Rx8zFNJ9GWrqSAArwIxTWgfYCYsvpUjbJW02Mg0Ufj2+YhjT713\nO9yViwUq996St9eLwGQO5FDMD8FYi7bRAiZEAeAeIEeveDRsNK15++8VVJVo7b2l\nzcapp5k3bQKBgQDSiJNHRhqSet6+xgIIDGoZ+1Qv0TFPX5UrZt8OpsK7FshEOhXQ\nCxt8WZN03HHXK9fEC4GjwwxBrwYB+BOjCYiMHmCd3j0M3HoXgDrgViyYKMuVuDk8\nUG83r5ZbqVuCwfO8i/HbxddueEvtyiD2CZ47ZCIHxKOKAe0K4Mex2UBaKwKBgQDi\nrEouOelP9Kn5dyVhHENQXBTu9DIBb1FAnO5fxjMTWxFa5qhLuYHNbfxKF24atsRu\nBepNhJryFCkT0jvGv2L8Ry0wHiwqwvhO14obJ3ia3iBEQAVDlt+sV9L2KvGOpTB4\n/nlmIA4u02I1IBziw02aWYkGo0SOMUo0ZQL+2PEykQKBgAV1uqiJlWuA9uPdFFJP\nZPL2YoOuTWdWfIwIb/UdsbUuTckCgqPIqUPi3HhiVSunOgcO7HWK3GD3j8k4XEbW\nG4y+Ik0lLrWzBl728tZe1GENzTf8aapElHlUfFSM4OlrTRBsbMOsQBcJNh7QbyoZ\nBHiZZTbRUY6xUR4DGYkCOksF", f110Sh);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("body", m1632Sh);
            jSONObject2.put("ticket", m1637Ei);
            C0158P.m1246d(C0158P.m1253H(), "http://ctlook.facaishiyi.com/api/v1/user/config.do", jSONObject2.toString(), m1629j(currentTimeMillis), new AbstractC0113L.AbstractC0115i1() { // from class: com.github.catvod.spider.Kuaikan.2
                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onFailure(Call call, Exception exc) {
                }

                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onResponse(String str) {
                    try {
                        JSONObject jSONObject3 = new JSONObject(str);
                        String m1630d = Kuaikan.this.m1630d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiYJ5UFjeEgeUi5ynLKhj5EMF0\n3sawTyAKoPbfUCSZZQ5QdrHhrINggrYxDtB/TWXoqSF+BJP85F0vq8B6gOqFWhK/\n1PIKe2wKlRRuZSOdUzK/3vzRHmR3J3KGrvNzvwARH8gg8xZjbsalc/gBkcVxSxGd\n8j8rJ4QhOSFh9F8B2wIDAQAB", jSONObject3.getString("ticket"));
                        JSONArray jSONArray = new JSONObject(Kuaikan.this.m1635H(m1630d.substring(0, 16), m1630d.substring(16, 32), jSONObject3.getString("body"))).getJSONObject("data").getJSONArray("newAnalysis");
                        Kuaikan.this.f111E.clear();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject4 = jSONArray.getJSONObject(i);
                            ArrayList arrayList = new ArrayList();
                            Kuaikan.this.f111E.put(jSONObject4.getString("id"), arrayList);
                            JSONArray jSONArray2 = jSONObject4.getJSONArray("analyses");
                            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                arrayList.add(jSONArray2.getJSONObject(i2).getString("url"));
                            }
                        }
                    } catch (JSONException unused) {
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String categoryContent(String str, final String str2, boolean z, HashMap<String, String> hashMap) {
        try {
            m1634Ml();
            int parseInt = hashMap.containsKey("areaId") ? Integer.parseInt(hashMap.get("areaId")) : 0;
            int parseInt2 = hashMap.containsKey("tagId") ? Integer.parseInt(hashMap.get("tagId")) : 0;
            int parseInt3 = hashMap.containsKey("yearId") ? Integer.parseInt(hashMap.get("yearId")) : 0;
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            String m1627s = m1627s("1000300001dc431681b806089dac1153fb13960f87" + str2 + 18 + str + parseInt2 + parseInt + parseInt3 + currentTimeMillis + "notice");
            jSONObject.put("appId", "1000300001");
            jSONObject.put("typeId", str);
            jSONObject.put("areaId", parseInt);
            jSONObject.put("tagId", parseInt2);
            jSONObject.put("yearId", parseInt3);
            jSONObject.put("page", Integer.parseInt(str2));
            jSONObject.put("pageSize", 18);
            jSONObject.put("sign", m1627s);
            jSONObject.put("timestamp", currentTimeMillis);
            String m1632Sh = m1632Sh(f110Sh.substring(0, 16), f110Sh.substring(16, 32), jSONObject.toString());
            String m1637Ei = m1637Ei("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDitX/rWP6WWc3h\nDzUcfD8Hbw79yCB7rz18GEkjDapjlUVIa3P8FEQFoOchu7N+vdK6HQNsl2Bql4lv\nWOYuzRWNbLGQ7mNtMca28iCuPHck0AcJzOs9F6qruPL73eY7Ai7yvzs2IfEdyXSz\nHP/yXgEOi5ZEBfsBZEPMJw6vfHJVueuD/lh/LkoJ/gXEKMCM/pkIzGpBPd2TsIg8\nZdvOGMPy+MOnJb1Qfe0y3QQdc5VPVdZFWoWsM9RAenHmE3d1THb+uNZZZcnM1tV7\nxYgBbIhgHtXAZnWDWDRBM3xJPR3O9dfBHHt/tbyrZrSwK1IgwFgqfYm/tRbmp4y/\n1DhIF6mDAgMBAAECggEAIzDjS2gEFNiZ0a6nouVSb1f47sHq8OgR1jp619seMNkR\n6Rzs4xtON8VzO1REl47lsAgi6O9SgxlEtykIiglBqDNQGNw4SNHqM6nAEuvF3sv/\n27CYb2JGFuPdq+UVAOHk4b93dH6uS95ipA8DV97psRVP3P1EqkGjGISTjf/2S4IG\n10Q0UhP4xxiRS2iCtaIq0fbvnrChqYRlYQYpkkRCCH+Q4FNKquSsqmWyCu5yfeQM\nMek+aT9KgvmCh05UCeqC1x7xPtSneNbpJpLD5zW8CcG7EYFDtIP8b9BPDlud47ZH\n5Y4/wyQQFHHLOaniTJVvEJZ7cIoXy2gOhvgcHpNryQKBgQDxNNtXnCfVK0JYNhnU\n5WCOX9NeW9Q79HldhTM5/EqsjA5H21j5S7A/duBSRfX7iou1hyXaa2p+J7rxF9sj\nNlwHPsKhprskxXaHJ7qU84gSmwORsTQQPmtTsizGwVsM330+Q2ZROaSEfDoUqH9+\nKBKs6K+FOldcTOrCZ+ILk8werwKBgQDwnQUrKATzRThVdD6Hcgt/F/SAizDmvtiT\nVvRcGkiV3EtB5Rx8zFNJ9GWrqSAArwIxTWgfYCYsvpUjbJW02Mg0Ufj2+YhjT713\nO9yViwUq996St9eLwGQO5FDMD8FYi7bRAiZEAeAeIEeveDRsNK15++8VVJVo7b2l\nzcapp5k3bQKBgQDSiJNHRhqSet6+xgIIDGoZ+1Qv0TFPX5UrZt8OpsK7FshEOhXQ\nCxt8WZN03HHXK9fEC4GjwwxBrwYB+BOjCYiMHmCd3j0M3HoXgDrgViyYKMuVuDk8\nUG83r5ZbqVuCwfO8i/HbxddueEvtyiD2CZ47ZCIHxKOKAe0K4Mex2UBaKwKBgQDi\nrEouOelP9Kn5dyVhHENQXBTu9DIBb1FAnO5fxjMTWxFa5qhLuYHNbfxKF24atsRu\nBepNhJryFCkT0jvGv2L8Ry0wHiwqwvhO14obJ3ia3iBEQAVDlt+sV9L2KvGOpTB4\n/nlmIA4u02I1IBziw02aWYkGo0SOMUo0ZQL+2PEykQKBgAV1uqiJlWuA9uPdFFJP\nZPL2YoOuTWdWfIwIb/UdsbUuTckCgqPIqUPi3HhiVSunOgcO7HWK3GD3j8k4XEbW\nG4y+Ik0lLrWzBl728tZe1GENzTf8aapElHlUfFSM4OlrTRBsbMOsQBcJNh7QbyoZ\nBHiZZTbRUY6xUR4DGYkCOksF", f110Sh);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("body", m1632Sh);
            jSONObject2.put("ticket", m1637Ei);
            final JSONObject jSONObject3 = new JSONObject();
            C0158P.m1246d(C0158P.m1253H(), "http://ctlook.facaishiyi.com/api/v1/search/video.do", jSONObject2.toString(), m1629j(currentTimeMillis), new AbstractC0113L.AbstractC0115i1() { // from class: com.github.catvod.spider.Kuaikan.3
                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onFailure(Call call, Exception exc) {
                }

                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onResponse(String str3) {
                    try {
                        JSONObject jSONObject4 = new JSONObject(str3);
                        String m1630d = Kuaikan.this.m1630d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiYJ5UFjeEgeUi5ynLKhj5EMF0\n3sawTyAKoPbfUCSZZQ5QdrHhrINggrYxDtB/TWXoqSF+BJP85F0vq8B6gOqFWhK/\n1PIKe2wKlRRuZSOdUzK/3vzRHmR3J3KGrvNzvwARH8gg8xZjbsalc/gBkcVxSxGd\n8j8rJ4QhOSFh9F8B2wIDAQAB", jSONObject4.getString("ticket"));
                        JSONObject jSONObject5 = new JSONObject(Kuaikan.this.m1635H(m1630d.substring(0, 16), m1630d.substring(16, 32), jSONObject4.getString("body")));
                        JSONArray jSONArray = new JSONArray();
                        JSONArray jSONArray2 = jSONObject5.getJSONArray("results");
                        for (int i = 0; i < jSONArray2.length(); i++) {
                            JSONObject jSONObject6 = jSONArray2.getJSONObject(i);
                            JSONObject jSONObject7 = new JSONObject();
                            jSONObject7.put("vod_id", jSONObject6.getString("id"));
                            jSONObject7.put("vod_name", jSONObject6.getString("name"));
                            jSONObject7.put("vod_pic", jSONObject6.getString("pic"));
                            jSONObject7.put("vod_remarks", jSONObject6.getString("progress"));
                            jSONArray.put(jSONObject7);
                        }
                        jSONObject3.put("page", Integer.parseInt(str2));
                        int i2 = jSONObject5.getInt("count");
                        jSONObject3.put("pagecount", i2 % 20 == 0 ? i2 / 20 : (i2 / 20) + 1);
                        jSONObject3.put("limit", 20);
                        jSONObject3.put("total", i2);
                        jSONObject3.put("list", jSONArray);
                    } catch (JSONException unused) {
                    }
                }
            });
            return jSONObject3.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    /* renamed from: d */
    String m1630d(String str, String str2) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            int i = 0;
            cipher.init(2, (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0))));
            byte[] decode = Base64.decode(str2, 0);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (decode.length <= 256) {
                byteArrayOutputStream.write(cipher.doFinal(decode));
            } else {
                int length = decode.length;
                while (i < length) {
                    int i2 = i + 256;
                    byteArrayOutputStream.write(cipher.doFinal(decode, i, i2 > length ? length - i : 256));
                    i = i2;
                }
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        } catch (Exception unused) {
            return "";
        }
    }

    public String detailContent(final List<String> list) {
        try {
            m1634Ml();
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            String m1627s = m1627s("1000300001dc431681b806089dac1153fb13960f87" + list.get(0) + currentTimeMillis + "notice");
            jSONObject.put("appId", "1000300001");
            jSONObject.put("id", list.get(0));
            jSONObject.put("sign", m1627s);
            jSONObject.put("timestamp", currentTimeMillis);
            String m1632Sh = m1632Sh(f110Sh.substring(0, 16), f110Sh.substring(16, 32), jSONObject.toString());
            String m1637Ei = m1637Ei("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDitX/rWP6WWc3h\nDzUcfD8Hbw79yCB7rz18GEkjDapjlUVIa3P8FEQFoOchu7N+vdK6HQNsl2Bql4lv\nWOYuzRWNbLGQ7mNtMca28iCuPHck0AcJzOs9F6qruPL73eY7Ai7yvzs2IfEdyXSz\nHP/yXgEOi5ZEBfsBZEPMJw6vfHJVueuD/lh/LkoJ/gXEKMCM/pkIzGpBPd2TsIg8\nZdvOGMPy+MOnJb1Qfe0y3QQdc5VPVdZFWoWsM9RAenHmE3d1THb+uNZZZcnM1tV7\nxYgBbIhgHtXAZnWDWDRBM3xJPR3O9dfBHHt/tbyrZrSwK1IgwFgqfYm/tRbmp4y/\n1DhIF6mDAgMBAAECggEAIzDjS2gEFNiZ0a6nouVSb1f47sHq8OgR1jp619seMNkR\n6Rzs4xtON8VzO1REl47lsAgi6O9SgxlEtykIiglBqDNQGNw4SNHqM6nAEuvF3sv/\n27CYb2JGFuPdq+UVAOHk4b93dH6uS95ipA8DV97psRVP3P1EqkGjGISTjf/2S4IG\n10Q0UhP4xxiRS2iCtaIq0fbvnrChqYRlYQYpkkRCCH+Q4FNKquSsqmWyCu5yfeQM\nMek+aT9KgvmCh05UCeqC1x7xPtSneNbpJpLD5zW8CcG7EYFDtIP8b9BPDlud47ZH\n5Y4/wyQQFHHLOaniTJVvEJZ7cIoXy2gOhvgcHpNryQKBgQDxNNtXnCfVK0JYNhnU\n5WCOX9NeW9Q79HldhTM5/EqsjA5H21j5S7A/duBSRfX7iou1hyXaa2p+J7rxF9sj\nNlwHPsKhprskxXaHJ7qU84gSmwORsTQQPmtTsizGwVsM330+Q2ZROaSEfDoUqH9+\nKBKs6K+FOldcTOrCZ+ILk8werwKBgQDwnQUrKATzRThVdD6Hcgt/F/SAizDmvtiT\nVvRcGkiV3EtB5Rx8zFNJ9GWrqSAArwIxTWgfYCYsvpUjbJW02Mg0Ufj2+YhjT713\nO9yViwUq996St9eLwGQO5FDMD8FYi7bRAiZEAeAeIEeveDRsNK15++8VVJVo7b2l\nzcapp5k3bQKBgQDSiJNHRhqSet6+xgIIDGoZ+1Qv0TFPX5UrZt8OpsK7FshEOhXQ\nCxt8WZN03HHXK9fEC4GjwwxBrwYB+BOjCYiMHmCd3j0M3HoXgDrgViyYKMuVuDk8\nUG83r5ZbqVuCwfO8i/HbxddueEvtyiD2CZ47ZCIHxKOKAe0K4Mex2UBaKwKBgQDi\nrEouOelP9Kn5dyVhHENQXBTu9DIBb1FAnO5fxjMTWxFa5qhLuYHNbfxKF24atsRu\nBepNhJryFCkT0jvGv2L8Ry0wHiwqwvhO14obJ3ia3iBEQAVDlt+sV9L2KvGOpTB4\n/nlmIA4u02I1IBziw02aWYkGo0SOMUo0ZQL+2PEykQKBgAV1uqiJlWuA9uPdFFJP\nZPL2YoOuTWdWfIwIb/UdsbUuTckCgqPIqUPi3HhiVSunOgcO7HWK3GD3j8k4XEbW\nG4y+Ik0lLrWzBl728tZe1GENzTf8aapElHlUfFSM4OlrTRBsbMOsQBcJNh7QbyoZ\nBHiZZTbRUY6xUR4DGYkCOksF", f110Sh);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("body", m1632Sh);
            jSONObject2.put("ticket", m1637Ei);
            final JSONObject jSONObject3 = new JSONObject();
            OkHttpClient m1253H = C0158P.m1253H();
            C0158P.m1246d(m1253H, "http://ctlook.facaishiyi.com/api/v1/video/play/" + list.get(0) + ".do ", jSONObject2.toString(), m1629j(currentTimeMillis), new AbstractC0113L.AbstractC0115i1() { // from class: com.github.catvod.spider.Kuaikan.4
                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onFailure(Call call, Exception exc) {
                }

                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onResponse(String str) {
                    String str2 = "name";
                    String str3 = "id";
                    try {
                        JSONObject jSONObject4 = new JSONObject(str);
                        String m1630d = Kuaikan.this.m1630d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiYJ5UFjeEgeUi5ynLKhj5EMF0\n3sawTyAKoPbfUCSZZQ5QdrHhrINggrYxDtB/TWXoqSF+BJP85F0vq8B6gOqFWhK/\n1PIKe2wKlRRuZSOdUzK/3vzRHmR3J3KGrvNzvwARH8gg8xZjbsalc/gBkcVxSxGd\n8j8rJ4QhOSFh9F8B2wIDAQAB", jSONObject4.getString("ticket"));
                        JSONObject jSONObject5 = new JSONObject(Kuaikan.this.m1635H(m1630d.substring(0, 16), m1630d.substring(16, 32), jSONObject4.getString("body"))).getJSONObject("data");
                        JSONArray jSONArray = new JSONArray();
                        JSONObject jSONObject6 = new JSONObject();
                        jSONObject6.put("vod_id", jSONObject5.getString(str3));
                        jSONObject6.put("vod_name", jSONObject5.getString(str2));
                        jSONObject6.put("vod_pic", jSONObject5.getString("pic"));
                        jSONObject6.put("vod_year", jSONObject5.getString("year"));
                        jSONObject6.put("vod_remarks", jSONObject5.getString("progress"));
                        jSONObject6.put("vod_actor", jSONObject5.getString("actor"));
                        jSONObject6.put("vod_director", jSONObject5.getString("director"));
                        jSONObject6.put("vod_content", jSONObject5.getString("content").trim());
                        JSONArray jSONArray2 = new JSONArray(Kuaikan.this.m1635H("5551e2a82a21a9a1", "a586c5236206a2cb", jSONObject5.getString("newPlayback")));
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        int i = 0;
                        while (i < jSONArray2.length()) {
                            JSONObject jSONObject7 = jSONArray2.getJSONObject(i);
                            String string = jSONObject7.getString(str2);
                            String string2 = jSONObject7.getString(str3);
                            ArrayList arrayList = new ArrayList();
                            int i2 = 0;
                            for (JSONArray jSONArray3 = jSONObject7.getJSONArray("episodes"); i2 < jSONArray3.length(); jSONArray3 = jSONArray3) {
                                JSONObject jSONObject8 = jSONArray3.getJSONObject(i2);
                                String str4 = str2;
                                String string3 = jSONObject8.getString("title");
                                String str5 = str3;
                                StringBuilder sb = new StringBuilder();
                                sb.append(string3);
                                sb.append("|");
                                JSONArray jSONArray4 = jSONArray2;
                                sb.append((String) list.get(0));
                                sb.append("|");
                                sb.append(string2);
                                sb.append("|");
                                sb.append(jSONObject8.getString("url"));
                                String encodeToString = Base64.encodeToString(sb.toString().getBytes("UTF-8"), 10);
                                arrayList.add(string3 + "$" + encodeToString);
                                i2++;
                                str2 = str4;
                                str3 = str5;
                                jSONArray2 = jSONArray4;
                            }
                            linkedHashMap.put(string, TextUtils.join("#", arrayList));
                            i++;
                            str2 = str2;
                            str3 = str3;
                            jSONArray2 = jSONArray2;
                        }
                        String join = TextUtils.join("$$$", linkedHashMap.keySet());
                        String join2 = TextUtils.join("$$$", linkedHashMap.values());
                        jSONObject6.put("vod_play_from", join);
                        jSONObject6.put("vod_play_url", join2);
                        jSONArray.put(jSONObject6);
                        jSONObject3.put("list", jSONArray);
                    } catch (Throwable unused) {
                    }
                }
            });
            return jSONObject3.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public String homeContent(boolean z) {
        try {
            m1634Ml();
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            String m1627s = m1627s("1000300001dc431681b806089dac1153fb13960f87" + currentTimeMillis + "notice");
            jSONObject.put("appId", "1000300001");
            jSONObject.put("sign", m1627s);
            jSONObject.put("timestamp", currentTimeMillis);
            String m1632Sh = m1632Sh(f110Sh.substring(0, 16), f110Sh.substring(16, 32), jSONObject.toString());
            String m1637Ei = m1637Ei("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDitX/rWP6WWc3h\nDzUcfD8Hbw79yCB7rz18GEkjDapjlUVIa3P8FEQFoOchu7N+vdK6HQNsl2Bql4lv\nWOYuzRWNbLGQ7mNtMca28iCuPHck0AcJzOs9F6qruPL73eY7Ai7yvzs2IfEdyXSz\nHP/yXgEOi5ZEBfsBZEPMJw6vfHJVueuD/lh/LkoJ/gXEKMCM/pkIzGpBPd2TsIg8\nZdvOGMPy+MOnJb1Qfe0y3QQdc5VPVdZFWoWsM9RAenHmE3d1THb+uNZZZcnM1tV7\nxYgBbIhgHtXAZnWDWDRBM3xJPR3O9dfBHHt/tbyrZrSwK1IgwFgqfYm/tRbmp4y/\n1DhIF6mDAgMBAAECggEAIzDjS2gEFNiZ0a6nouVSb1f47sHq8OgR1jp619seMNkR\n6Rzs4xtON8VzO1REl47lsAgi6O9SgxlEtykIiglBqDNQGNw4SNHqM6nAEuvF3sv/\n27CYb2JGFuPdq+UVAOHk4b93dH6uS95ipA8DV97psRVP3P1EqkGjGISTjf/2S4IG\n10Q0UhP4xxiRS2iCtaIq0fbvnrChqYRlYQYpkkRCCH+Q4FNKquSsqmWyCu5yfeQM\nMek+aT9KgvmCh05UCeqC1x7xPtSneNbpJpLD5zW8CcG7EYFDtIP8b9BPDlud47ZH\n5Y4/wyQQFHHLOaniTJVvEJZ7cIoXy2gOhvgcHpNryQKBgQDxNNtXnCfVK0JYNhnU\n5WCOX9NeW9Q79HldhTM5/EqsjA5H21j5S7A/duBSRfX7iou1hyXaa2p+J7rxF9sj\nNlwHPsKhprskxXaHJ7qU84gSmwORsTQQPmtTsizGwVsM330+Q2ZROaSEfDoUqH9+\nKBKs6K+FOldcTOrCZ+ILk8werwKBgQDwnQUrKATzRThVdD6Hcgt/F/SAizDmvtiT\nVvRcGkiV3EtB5Rx8zFNJ9GWrqSAArwIxTWgfYCYsvpUjbJW02Mg0Ufj2+YhjT713\nO9yViwUq996St9eLwGQO5FDMD8FYi7bRAiZEAeAeIEeveDRsNK15++8VVJVo7b2l\nzcapp5k3bQKBgQDSiJNHRhqSet6+xgIIDGoZ+1Qv0TFPX5UrZt8OpsK7FshEOhXQ\nCxt8WZN03HHXK9fEC4GjwwxBrwYB+BOjCYiMHmCd3j0M3HoXgDrgViyYKMuVuDk8\nUG83r5ZbqVuCwfO8i/HbxddueEvtyiD2CZ47ZCIHxKOKAe0K4Mex2UBaKwKBgQDi\nrEouOelP9Kn5dyVhHENQXBTu9DIBb1FAnO5fxjMTWxFa5qhLuYHNbfxKF24atsRu\nBepNhJryFCkT0jvGv2L8Ry0wHiwqwvhO14obJ3ia3iBEQAVDlt+sV9L2KvGOpTB4\n/nlmIA4u02I1IBziw02aWYkGo0SOMUo0ZQL+2PEykQKBgAV1uqiJlWuA9uPdFFJP\nZPL2YoOuTWdWfIwIb/UdsbUuTckCgqPIqUPi3HhiVSunOgcO7HWK3GD3j8k4XEbW\nG4y+Ik0lLrWzBl728tZe1GENzTf8aapElHlUfFSM4OlrTRBsbMOsQBcJNh7QbyoZ\nBHiZZTbRUY6xUR4DGYkCOksF", f110Sh);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("body", m1632Sh);
            jSONObject2.put("ticket", m1637Ei);
            final JSONObject jSONObject3 = new JSONObject();
            final JSONArray jSONArray = new JSONArray();
            C0158P.m1246d(C0158P.m1253H(), "http://ctlook.facaishiyi.com/api/v1/user/genres.do", jSONObject2.toString(), m1629j(currentTimeMillis), new AbstractC0113L.AbstractC0115i1() { // from class: com.github.catvod.spider.Kuaikan.5
                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onFailure(Call call, Exception exc) {
                }

                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onResponse(String str) {
                    try {
                        JSONObject jSONObject4 = new JSONObject(str);
                        String m1630d = Kuaikan.this.m1630d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiYJ5UFjeEgeUi5ynLKhj5EMF0\n3sawTyAKoPbfUCSZZQ5QdrHhrINggrYxDtB/TWXoqSF+BJP85F0vq8B6gOqFWhK/\n1PIKe2wKlRRuZSOdUzK/3vzRHmR3J3KGrvNzvwARH8gg8xZjbsalc/gBkcVxSxGd\n8j8rJ4QhOSFh9F8B2wIDAQAB", jSONObject4.getString("ticket"));
                        JSONArray jSONArray2 = new JSONObject(Kuaikan.this.m1635H(m1630d.substring(0, 16), m1630d.substring(16, 32), jSONObject4.getString("body"))).getJSONArray("genres");
                        for (int i = 0; i < jSONArray2.length(); i++) {
                            JSONObject jSONObject5 = jSONArray2.getJSONObject(i);
                            String string = jSONObject5.getString("name");
                            final int i2 = jSONObject5.getInt("typeId");
                            if (i2 > 0) {
                                JSONObject jSONObject6 = new JSONObject();
                                jSONObject6.put("type_id", i2 + "");
                                jSONObject6.put("type_name", string);
                                jSONArray.put(jSONObject6);
                                JSONObject jSONObject7 = new JSONObject();
                                long currentTimeMillis2 = System.currentTimeMillis() / 1000;
                                Kuaikan kuaikan = Kuaikan.this;
                                String m1627s2 = kuaikan.m1627s("1000300001dc431681b806089dac1153fb13960f87" + i2 + currentTimeMillis2 + "notice");
                                jSONObject7.put("appId", "1000300001");
                                jSONObject7.put("tid", i2);
                                jSONObject7.put("sign", m1627s2);
                                jSONObject7.put("timestamp", currentTimeMillis2);
                                String m1632Sh2 = Kuaikan.this.m1632Sh(Kuaikan.f110Sh.substring(0, 16), Kuaikan.f110Sh.substring(16, 32), jSONObject7.toString());
                                String m1637Ei2 = Kuaikan.this.m1637Ei("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDitX/rWP6WWc3h\nDzUcfD8Hbw79yCB7rz18GEkjDapjlUVIa3P8FEQFoOchu7N+vdK6HQNsl2Bql4lv\nWOYuzRWNbLGQ7mNtMca28iCuPHck0AcJzOs9F6qruPL73eY7Ai7yvzs2IfEdyXSz\nHP/yXgEOi5ZEBfsBZEPMJw6vfHJVueuD/lh/LkoJ/gXEKMCM/pkIzGpBPd2TsIg8\nZdvOGMPy+MOnJb1Qfe0y3QQdc5VPVdZFWoWsM9RAenHmE3d1THb+uNZZZcnM1tV7\nxYgBbIhgHtXAZnWDWDRBM3xJPR3O9dfBHHt/tbyrZrSwK1IgwFgqfYm/tRbmp4y/\n1DhIF6mDAgMBAAECggEAIzDjS2gEFNiZ0a6nouVSb1f47sHq8OgR1jp619seMNkR\n6Rzs4xtON8VzO1REl47lsAgi6O9SgxlEtykIiglBqDNQGNw4SNHqM6nAEuvF3sv/\n27CYb2JGFuPdq+UVAOHk4b93dH6uS95ipA8DV97psRVP3P1EqkGjGISTjf/2S4IG\n10Q0UhP4xxiRS2iCtaIq0fbvnrChqYRlYQYpkkRCCH+Q4FNKquSsqmWyCu5yfeQM\nMek+aT9KgvmCh05UCeqC1x7xPtSneNbpJpLD5zW8CcG7EYFDtIP8b9BPDlud47ZH\n5Y4/wyQQFHHLOaniTJVvEJZ7cIoXy2gOhvgcHpNryQKBgQDxNNtXnCfVK0JYNhnU\n5WCOX9NeW9Q79HldhTM5/EqsjA5H21j5S7A/duBSRfX7iou1hyXaa2p+J7rxF9sj\nNlwHPsKhprskxXaHJ7qU84gSmwORsTQQPmtTsizGwVsM330+Q2ZROaSEfDoUqH9+\nKBKs6K+FOldcTOrCZ+ILk8werwKBgQDwnQUrKATzRThVdD6Hcgt/F/SAizDmvtiT\nVvRcGkiV3EtB5Rx8zFNJ9GWrqSAArwIxTWgfYCYsvpUjbJW02Mg0Ufj2+YhjT713\nO9yViwUq996St9eLwGQO5FDMD8FYi7bRAiZEAeAeIEeveDRsNK15++8VVJVo7b2l\nzcapp5k3bQKBgQDSiJNHRhqSet6+xgIIDGoZ+1Qv0TFPX5UrZt8OpsK7FshEOhXQ\nCxt8WZN03HHXK9fEC4GjwwxBrwYB+BOjCYiMHmCd3j0M3HoXgDrgViyYKMuVuDk8\nUG83r5ZbqVuCwfO8i/HbxddueEvtyiD2CZ47ZCIHxKOKAe0K4Mex2UBaKwKBgQDi\nrEouOelP9Kn5dyVhHENQXBTu9DIBb1FAnO5fxjMTWxFa5qhLuYHNbfxKF24atsRu\nBepNhJryFCkT0jvGv2L8Ry0wHiwqwvhO14obJ3ia3iBEQAVDlt+sV9L2KvGOpTB4\n/nlmIA4u02I1IBziw02aWYkGo0SOMUo0ZQL+2PEykQKBgAV1uqiJlWuA9uPdFFJP\nZPL2YoOuTWdWfIwIb/UdsbUuTckCgqPIqUPi3HhiVSunOgcO7HWK3GD3j8k4XEbW\nG4y+Ik0lLrWzBl728tZe1GENzTf8aapElHlUfFSM4OlrTRBsbMOsQBcJNh7QbyoZ\nBHiZZTbRUY6xUR4DGYkCOksF", Kuaikan.f110Sh);
                                JSONObject jSONObject8 = new JSONObject();
                                jSONObject8.put("body", m1632Sh2);
                                jSONObject8.put("ticket", m1637Ei2);
                                new JSONArray();
                                C0158P.m1246d(C0158P.m1253H(), "http://ctlook.facaishiyi.com/api/v1/search/category.do", jSONObject8.toString(), Kuaikan.this.m1629j(currentTimeMillis2), new AbstractC0113L.AbstractC0115i1() { // from class: com.github.catvod.spider.Kuaikan.5.1
                                    @Override // com.github.catvod.spider.merge.AbstractC0113L
                                    public void onFailure(Call call, Exception exc) {
                                    }

                                    @Override // com.github.catvod.spider.merge.AbstractC0113L
                                    public void onResponse(String str2) {
                                        try {
                                            JSONObject jSONObject9 = new JSONObject(str2);
                                            String m1630d2 = Kuaikan.this.m1630d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiYJ5UFjeEgeUi5ynLKhj5EMF0\n3sawTyAKoPbfUCSZZQ5QdrHhrINggrYxDtB/TWXoqSF+BJP85F0vq8B6gOqFWhK/\n1PIKe2wKlRRuZSOdUzK/3vzRHmR3J3KGrvNzvwARH8gg8xZjbsalc/gBkcVxSxGd\n8j8rJ4QhOSFh9F8B2wIDAQAB", jSONObject9.getString("ticket"));
                                            JSONObject jSONObject10 = new JSONObject(Kuaikan.this.m1635H(m1630d2.substring(0, 16), m1630d2.substring(16, 32), jSONObject9.getString("body")));
                                            JSONArray jSONArray3 = jSONObject10.getJSONArray("areas");
                                            JSONArray jSONArray4 = jSONObject10.getJSONArray("years");
                                            JSONArray jSONArray5 = new JSONArray();
                                            JSONObject jSONObject11 = new JSONObject();
                                            jSONObject11.put("key", "tagId");
                                            jSONObject11.put("name", "类型");
                                            JSONArray jSONArray6 = new JSONArray();
                                            int i3 = 0;
                                            for (JSONArray jSONArray7 = jSONObject10.getJSONArray("tags"); i3 < jSONArray7.length(); jSONArray7 = jSONArray7) {
                                                JSONObject jSONObject12 = jSONArray7.getJSONObject(i3);
                                                JSONObject jSONObject13 = new JSONObject();
                                                jSONObject13.put("n", jSONObject12.getString("name"));
                                                jSONObject13.put("v", jSONObject12.getString("id"));
                                                jSONArray6.put(jSONObject13);
                                                i3++;
                                            }
                                            jSONObject11.put("value", jSONArray6);
                                            jSONArray5.put(jSONObject11);
                                            JSONObject jSONObject14 = new JSONObject();
                                            jSONObject14.put("key", "areaId");
                                            jSONObject14.put("name", "地区");
                                            JSONArray jSONArray8 = new JSONArray();
                                            for (int i4 = 0; i4 < jSONArray3.length(); i4++) {
                                                JSONObject jSONObject15 = jSONArray3.getJSONObject(i4);
                                                JSONObject jSONObject16 = new JSONObject();
                                                jSONObject16.put("n", jSONObject15.getString("name"));
                                                jSONObject16.put("v", jSONObject15.getString("id"));
                                                jSONArray8.put(jSONObject16);
                                            }
                                            jSONObject14.put("value", jSONArray8);
                                            jSONArray5.put(jSONObject14);
                                            JSONObject jSONObject17 = new JSONObject();
                                            jSONObject17.put("key", "yearId");
                                            jSONObject17.put("name", "年份");
                                            JSONArray jSONArray9 = new JSONArray();
                                            for (int i5 = 0; i5 < jSONArray4.length(); i5++) {
                                                JSONObject jSONObject18 = jSONArray4.getJSONObject(i5);
                                                JSONObject jSONObject19 = new JSONObject();
                                                jSONObject19.put("n", jSONObject18.getString("name"));
                                                jSONObject19.put("v", jSONObject18.getString("id"));
                                                jSONArray9.put(jSONObject19);
                                            }
                                            jSONObject17.put("value", jSONArray9);
                                            jSONArray5.put(jSONObject17);
                                            JSONObject jSONObject20 = jSONObject3;
                                            jSONObject20.put(i2 + "", jSONArray5);
                                        } catch (JSONException unused) {
                                        }
                                    }
                                });
                            }
                        }
                    } catch (JSONException unused) {
                    }
                }
            });
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("class", jSONArray);
            if (z) {
                jSONObject4.put("filters", jSONObject3);
            }
            return jSONObject4.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public String homeVideoContent() {
        try {
            m1634Ml();
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            String m1627s = m1627s("1000300001dc431681b806089dac1153fb13960f87115" + currentTimeMillis + "notice");
            jSONObject.put("appId", "1000300001");
            jSONObject.put("id", 115);
            jSONObject.put("sign", m1627s);
            jSONObject.put("timestamp", currentTimeMillis);
            String m1632Sh = m1632Sh(f110Sh.substring(0, 16), f110Sh.substring(16, 32), jSONObject.toString());
            String m1637Ei = m1637Ei("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDitX/rWP6WWc3h\nDzUcfD8Hbw79yCB7rz18GEkjDapjlUVIa3P8FEQFoOchu7N+vdK6HQNsl2Bql4lv\nWOYuzRWNbLGQ7mNtMca28iCuPHck0AcJzOs9F6qruPL73eY7Ai7yvzs2IfEdyXSz\nHP/yXgEOi5ZEBfsBZEPMJw6vfHJVueuD/lh/LkoJ/gXEKMCM/pkIzGpBPd2TsIg8\nZdvOGMPy+MOnJb1Qfe0y3QQdc5VPVdZFWoWsM9RAenHmE3d1THb+uNZZZcnM1tV7\nxYgBbIhgHtXAZnWDWDRBM3xJPR3O9dfBHHt/tbyrZrSwK1IgwFgqfYm/tRbmp4y/\n1DhIF6mDAgMBAAECggEAIzDjS2gEFNiZ0a6nouVSb1f47sHq8OgR1jp619seMNkR\n6Rzs4xtON8VzO1REl47lsAgi6O9SgxlEtykIiglBqDNQGNw4SNHqM6nAEuvF3sv/\n27CYb2JGFuPdq+UVAOHk4b93dH6uS95ipA8DV97psRVP3P1EqkGjGISTjf/2S4IG\n10Q0UhP4xxiRS2iCtaIq0fbvnrChqYRlYQYpkkRCCH+Q4FNKquSsqmWyCu5yfeQM\nMek+aT9KgvmCh05UCeqC1x7xPtSneNbpJpLD5zW8CcG7EYFDtIP8b9BPDlud47ZH\n5Y4/wyQQFHHLOaniTJVvEJZ7cIoXy2gOhvgcHpNryQKBgQDxNNtXnCfVK0JYNhnU\n5WCOX9NeW9Q79HldhTM5/EqsjA5H21j5S7A/duBSRfX7iou1hyXaa2p+J7rxF9sj\nNlwHPsKhprskxXaHJ7qU84gSmwORsTQQPmtTsizGwVsM330+Q2ZROaSEfDoUqH9+\nKBKs6K+FOldcTOrCZ+ILk8werwKBgQDwnQUrKATzRThVdD6Hcgt/F/SAizDmvtiT\nVvRcGkiV3EtB5Rx8zFNJ9GWrqSAArwIxTWgfYCYsvpUjbJW02Mg0Ufj2+YhjT713\nO9yViwUq996St9eLwGQO5FDMD8FYi7bRAiZEAeAeIEeveDRsNK15++8VVJVo7b2l\nzcapp5k3bQKBgQDSiJNHRhqSet6+xgIIDGoZ+1Qv0TFPX5UrZt8OpsK7FshEOhXQ\nCxt8WZN03HHXK9fEC4GjwwxBrwYB+BOjCYiMHmCd3j0M3HoXgDrgViyYKMuVuDk8\nUG83r5ZbqVuCwfO8i/HbxddueEvtyiD2CZ47ZCIHxKOKAe0K4Mex2UBaKwKBgQDi\nrEouOelP9Kn5dyVhHENQXBTu9DIBb1FAnO5fxjMTWxFa5qhLuYHNbfxKF24atsRu\nBepNhJryFCkT0jvGv2L8Ry0wHiwqwvhO14obJ3ia3iBEQAVDlt+sV9L2KvGOpTB4\n/nlmIA4u02I1IBziw02aWYkGo0SOMUo0ZQL+2PEykQKBgAV1uqiJlWuA9uPdFFJP\nZPL2YoOuTWdWfIwIb/UdsbUuTckCgqPIqUPi3HhiVSunOgcO7HWK3GD3j8k4XEbW\nG4y+Ik0lLrWzBl728tZe1GENzTf8aapElHlUfFSM4OlrTRBsbMOsQBcJNh7QbyoZ\nBHiZZTbRUY6xUR4DGYkCOksF", f110Sh);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("body", m1632Sh);
            jSONObject2.put("ticket", m1637Ei);
            final JSONArray jSONArray = new JSONArray();
            C0158P.m1246d(C0158P.m1253H(), "http://ctlook.facaishiyi.com/api/v1/user/genre/115.do", jSONObject2.toString(), m1629j(currentTimeMillis), new AbstractC0113L.AbstractC0115i1() { // from class: com.github.catvod.spider.Kuaikan.6
                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onFailure(Call call, Exception exc) {
                }

                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onResponse(String str) {
                    try {
                        JSONObject jSONObject3 = new JSONObject(str);
                        String m1630d = Kuaikan.this.m1630d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiYJ5UFjeEgeUi5ynLKhj5EMF0\n3sawTyAKoPbfUCSZZQ5QdrHhrINggrYxDtB/TWXoqSF+BJP85F0vq8B6gOqFWhK/\n1PIKe2wKlRRuZSOdUzK/3vzRHmR3J3KGrvNzvwARH8gg8xZjbsalc/gBkcVxSxGd\n8j8rJ4QhOSFh9F8B2wIDAQAB", jSONObject3.getString("ticket"));
                        JSONArray jSONArray2 = new JSONObject(Kuaikan.this.m1635H(m1630d.substring(0, 16), m1630d.substring(16, 32), jSONObject3.getString("body"))).getJSONArray("subGenres");
                        for (int i = 0; i < jSONArray2.length(); i++) {
                            if (jSONArray2.getJSONObject(i).getInt("template") == 1) {
                                JSONArray jSONArray3 = jSONArray2.getJSONObject(i).getJSONArray("video");
                                for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                                    JSONObject jSONObject4 = jSONArray3.getJSONObject(i2);
                                    JSONObject jSONObject5 = new JSONObject();
                                    jSONObject5.put("vod_id", jSONObject4.getString("id"));
                                    jSONObject5.put("vod_name", jSONObject4.getString("name"));
                                    jSONObject5.put("vod_pic", jSONObject4.getString("pic"));
                                    jSONObject5.put("vod_remarks", jSONObject4.getString("progress"));
                                    jSONArray.put(jSONObject5);
                                }
                            }
                        }
                    } catch (JSONException unused) {
                    }
                }
            });
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("list", jSONArray);
            return jSONObject3.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public void init(Context context, String str) {
        super.init(context, str);
        SharedPreferences sharedPreferences = context.getSharedPreferences("spider_Kuaikan", 0);
        try {
            String string = sharedPreferences.getString("umId", null);
            f108H = string;
            if (string != null) {
                return;
            }
        } catch (Throwable unused) {
            if (f108H != null) {
                return;
            }
        }
        f108H = m1627s(UUID.randomUUID().toString()).toLowerCase();
        sharedPreferences.edit().putString("umId", f108H).commit();
    }

    /* renamed from: j */
    public HashMap<String, String> m1629j(long j) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("appId", "1000300001");
        hashMap.put("version", "4");
        hashMap.put("timestamp", j + "");
        hashMap.put("token", m1627s("1000300001dc431681b806089dac1153fb13960f874" + j + "notice"));
        hashMap.put("User-Agent", "okhttp/5.0.0-alpha.2");
        return hashMap;
    }

    public String playerContent(String str, String str2, List<String> list) {
        try {
            m1634Ml();
            String[] split = new String(Base64.decode(str2, 10), "UTF-8").split("\\|");
            ArrayList<String> arrayList = this.f111E.get(split[2]);
            final JSONObject jSONObject = new JSONObject();
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (jSONObject.has("url")) {
                        break;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    String m1627s = m1627s("1000300001dc431681b806089dac1153fb13960f87" + split[1] + split[0] + split[3] + currentTimeMillis + "notice");
                    jSONObject2.put("appId", "1000300001");
                    jSONObject2.put("id", split[1]);
                    jSONObject2.put("title", split[0]);
                    jSONObject2.put("url", split[3]);
                    jSONObject2.put("sign", m1627s);
                    jSONObject2.put("timestamp", currentTimeMillis);
                    String m1632Sh = m1632Sh(f110Sh.substring(0, 16), f110Sh.substring(16, 32), jSONObject2.toString());
                    String m1637Ei = m1637Ei("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDitX/rWP6WWc3h\nDzUcfD8Hbw79yCB7rz18GEkjDapjlUVIa3P8FEQFoOchu7N+vdK6HQNsl2Bql4lv\nWOYuzRWNbLGQ7mNtMca28iCuPHck0AcJzOs9F6qruPL73eY7Ai7yvzs2IfEdyXSz\nHP/yXgEOi5ZEBfsBZEPMJw6vfHJVueuD/lh/LkoJ/gXEKMCM/pkIzGpBPd2TsIg8\nZdvOGMPy+MOnJb1Qfe0y3QQdc5VPVdZFWoWsM9RAenHmE3d1THb+uNZZZcnM1tV7\nxYgBbIhgHtXAZnWDWDRBM3xJPR3O9dfBHHt/tbyrZrSwK1IgwFgqfYm/tRbmp4y/\n1DhIF6mDAgMBAAECggEAIzDjS2gEFNiZ0a6nouVSb1f47sHq8OgR1jp619seMNkR\n6Rzs4xtON8VzO1REl47lsAgi6O9SgxlEtykIiglBqDNQGNw4SNHqM6nAEuvF3sv/\n27CYb2JGFuPdq+UVAOHk4b93dH6uS95ipA8DV97psRVP3P1EqkGjGISTjf/2S4IG\n10Q0UhP4xxiRS2iCtaIq0fbvnrChqYRlYQYpkkRCCH+Q4FNKquSsqmWyCu5yfeQM\nMek+aT9KgvmCh05UCeqC1x7xPtSneNbpJpLD5zW8CcG7EYFDtIP8b9BPDlud47ZH\n5Y4/wyQQFHHLOaniTJVvEJZ7cIoXy2gOhvgcHpNryQKBgQDxNNtXnCfVK0JYNhnU\n5WCOX9NeW9Q79HldhTM5/EqsjA5H21j5S7A/duBSRfX7iou1hyXaa2p+J7rxF9sj\nNlwHPsKhprskxXaHJ7qU84gSmwORsTQQPmtTsizGwVsM330+Q2ZROaSEfDoUqH9+\nKBKs6K+FOldcTOrCZ+ILk8werwKBgQDwnQUrKATzRThVdD6Hcgt/F/SAizDmvtiT\nVvRcGkiV3EtB5Rx8zFNJ9GWrqSAArwIxTWgfYCYsvpUjbJW02Mg0Ufj2+YhjT713\nO9yViwUq996St9eLwGQO5FDMD8FYi7bRAiZEAeAeIEeveDRsNK15++8VVJVo7b2l\nzcapp5k3bQKBgQDSiJNHRhqSet6+xgIIDGoZ+1Qv0TFPX5UrZt8OpsK7FshEOhXQ\nCxt8WZN03HHXK9fEC4GjwwxBrwYB+BOjCYiMHmCd3j0M3HoXgDrgViyYKMuVuDk8\nUG83r5ZbqVuCwfO8i/HbxddueEvtyiD2CZ47ZCIHxKOKAe0K4Mex2UBaKwKBgQDi\nrEouOelP9Kn5dyVhHENQXBTu9DIBb1FAnO5fxjMTWxFa5qhLuYHNbfxKF24atsRu\nBepNhJryFCkT0jvGv2L8Ry0wHiwqwvhO14obJ3ia3iBEQAVDlt+sV9L2KvGOpTB4\n/nlmIA4u02I1IBziw02aWYkGo0SOMUo0ZQL+2PEykQKBgAV1uqiJlWuA9uPdFFJP\nZPL2YoOuTWdWfIwIb/UdsbUuTckCgqPIqUPi3HhiVSunOgcO7HWK3GD3j8k4XEbW\nG4y+Ik0lLrWzBl728tZe1GENzTf8aapElHlUfFSM4OlrTRBsbMOsQBcJNh7QbyoZ\nBHiZZTbRUY6xUR4DGYkCOksF", f110Sh);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("body", m1632Sh);
                    jSONObject3.put("ticket", m1637Ei);
                    C0158P.m1246d(C0158P.m1253H(), next, jSONObject3.toString(), m1629j(currentTimeMillis), new AbstractC0113L.AbstractC0115i1() { // from class: com.github.catvod.spider.Kuaikan.7
                        @Override // com.github.catvod.spider.merge.AbstractC0113L
                        public void onFailure(Call call, Exception exc) {
                        }

                        @Override // com.github.catvod.spider.merge.AbstractC0113L
                        public void onResponse(String str3) {
                            try {
                                JSONObject jSONObject4 = new JSONObject(str3);
                                String m1630d = Kuaikan.this.m1630d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiYJ5UFjeEgeUi5ynLKhj5EMF0\n3sawTyAKoPbfUCSZZQ5QdrHhrINggrYxDtB/TWXoqSF+BJP85F0vq8B6gOqFWhK/\n1PIKe2wKlRRuZSOdUzK/3vzRHmR3J3KGrvNzvwARH8gg8xZjbsalc/gBkcVxSxGd\n8j8rJ4QhOSFh9F8B2wIDAQAB", jSONObject4.getString("ticket"));
                                JSONObject jSONObject5 = new JSONObject(Kuaikan.this.m1635H(m1630d.substring(0, 16), m1630d.substring(16, 32), jSONObject4.getString("body")));
                                if (!jSONObject5.optString("msg", "").equals("success") || jSONObject5.optInt("code", -1) != 0) {
                                    return;
                                }
                                jSONObject.put("parse", 0);
                                jSONObject.put("header", "");
                                jSONObject.put("playUrl", "");
                                jSONObject.put("url", jSONObject5.getString("url"));
                            } catch (JSONException unused) {
                            }
                        }
                    });
                }
            }
            if (jSONObject.has("url")) {
                return jSONObject.toString();
            }
            if (!C0499rH.m409E(split[3])) {
                return "";
            }
            jSONObject.put("parse", 0);
            jSONObject.put("header", "");
            jSONObject.put("playUrl", "");
            jSONObject.put("url", split[3]);
            return jSONObject.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    /* renamed from: s */
    String m1627s(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() < 2) {
                    sb.append(0);
                }
                sb.append(hexString);
            }
            return sb.toString().toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String searchContent(String str, boolean z) {
        try {
            m1634Ml();
            JSONObject jSONObject = new JSONObject();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            String m1627s = m1627s("1000300001dc431681b806089dac1153fb13960f87112" + str + currentTimeMillis + "notice");
            jSONObject.put("appId", "1000300001");
            jSONObject.put("keyword", str);
            jSONObject.put("page", 1);
            jSONObject.put("pageSize", 12);
            jSONObject.put("sign", m1627s);
            jSONObject.put("timestamp", currentTimeMillis);
            String m1632Sh = m1632Sh(f110Sh.substring(0, 16), f110Sh.substring(16, 32), jSONObject.toString());
            String m1637Ei = m1637Ei("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDitX/rWP6WWc3h\nDzUcfD8Hbw79yCB7rz18GEkjDapjlUVIa3P8FEQFoOchu7N+vdK6HQNsl2Bql4lv\nWOYuzRWNbLGQ7mNtMca28iCuPHck0AcJzOs9F6qruPL73eY7Ai7yvzs2IfEdyXSz\nHP/yXgEOi5ZEBfsBZEPMJw6vfHJVueuD/lh/LkoJ/gXEKMCM/pkIzGpBPd2TsIg8\nZdvOGMPy+MOnJb1Qfe0y3QQdc5VPVdZFWoWsM9RAenHmE3d1THb+uNZZZcnM1tV7\nxYgBbIhgHtXAZnWDWDRBM3xJPR3O9dfBHHt/tbyrZrSwK1IgwFgqfYm/tRbmp4y/\n1DhIF6mDAgMBAAECggEAIzDjS2gEFNiZ0a6nouVSb1f47sHq8OgR1jp619seMNkR\n6Rzs4xtON8VzO1REl47lsAgi6O9SgxlEtykIiglBqDNQGNw4SNHqM6nAEuvF3sv/\n27CYb2JGFuPdq+UVAOHk4b93dH6uS95ipA8DV97psRVP3P1EqkGjGISTjf/2S4IG\n10Q0UhP4xxiRS2iCtaIq0fbvnrChqYRlYQYpkkRCCH+Q4FNKquSsqmWyCu5yfeQM\nMek+aT9KgvmCh05UCeqC1x7xPtSneNbpJpLD5zW8CcG7EYFDtIP8b9BPDlud47ZH\n5Y4/wyQQFHHLOaniTJVvEJZ7cIoXy2gOhvgcHpNryQKBgQDxNNtXnCfVK0JYNhnU\n5WCOX9NeW9Q79HldhTM5/EqsjA5H21j5S7A/duBSRfX7iou1hyXaa2p+J7rxF9sj\nNlwHPsKhprskxXaHJ7qU84gSmwORsTQQPmtTsizGwVsM330+Q2ZROaSEfDoUqH9+\nKBKs6K+FOldcTOrCZ+ILk8werwKBgQDwnQUrKATzRThVdD6Hcgt/F/SAizDmvtiT\nVvRcGkiV3EtB5Rx8zFNJ9GWrqSAArwIxTWgfYCYsvpUjbJW02Mg0Ufj2+YhjT713\nO9yViwUq996St9eLwGQO5FDMD8FYi7bRAiZEAeAeIEeveDRsNK15++8VVJVo7b2l\nzcapp5k3bQKBgQDSiJNHRhqSet6+xgIIDGoZ+1Qv0TFPX5UrZt8OpsK7FshEOhXQ\nCxt8WZN03HHXK9fEC4GjwwxBrwYB+BOjCYiMHmCd3j0M3HoXgDrgViyYKMuVuDk8\nUG83r5ZbqVuCwfO8i/HbxddueEvtyiD2CZ47ZCIHxKOKAe0K4Mex2UBaKwKBgQDi\nrEouOelP9Kn5dyVhHENQXBTu9DIBb1FAnO5fxjMTWxFa5qhLuYHNbfxKF24atsRu\nBepNhJryFCkT0jvGv2L8Ry0wHiwqwvhO14obJ3ia3iBEQAVDlt+sV9L2KvGOpTB4\n/nlmIA4u02I1IBziw02aWYkGo0SOMUo0ZQL+2PEykQKBgAV1uqiJlWuA9uPdFFJP\nZPL2YoOuTWdWfIwIb/UdsbUuTckCgqPIqUPi3HhiVSunOgcO7HWK3GD3j8k4XEbW\nG4y+Ik0lLrWzBl728tZe1GENzTf8aapElHlUfFSM4OlrTRBsbMOsQBcJNh7QbyoZ\nBHiZZTbRUY6xUR4DGYkCOksF", f110Sh);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("body", m1632Sh);
            jSONObject2.put("ticket", m1637Ei);
            final JSONObject jSONObject3 = new JSONObject();
            C0158P.m1246d(C0158P.m1253H(), "http://ctlook.facaishiyi.com/api/v1/search/page.do", jSONObject2.toString(), m1629j(currentTimeMillis), new AbstractC0113L.AbstractC0115i1() { // from class: com.github.catvod.spider.Kuaikan.8
                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onFailure(Call call, Exception exc) {
                }

                @Override // com.github.catvod.spider.merge.AbstractC0113L
                public void onResponse(String str2) {
                    try {
                        JSONObject jSONObject4 = new JSONObject(str2);
                        String m1630d = Kuaikan.this.m1630d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiYJ5UFjeEgeUi5ynLKhj5EMF0\n3sawTyAKoPbfUCSZZQ5QdrHhrINggrYxDtB/TWXoqSF+BJP85F0vq8B6gOqFWhK/\n1PIKe2wKlRRuZSOdUzK/3vzRHmR3J3KGrvNzvwARH8gg8xZjbsalc/gBkcVxSxGd\n8j8rJ4QhOSFh9F8B2wIDAQAB", jSONObject4.getString("ticket"));
                        JSONObject jSONObject5 = new JSONObject(Kuaikan.this.m1635H(m1630d.substring(0, 16), m1630d.substring(16, 32), jSONObject4.getString("body")));
                        JSONArray jSONArray = new JSONArray();
                        JSONArray jSONArray2 = jSONObject5.getJSONArray("results");
                        for (int i = 0; i < jSONArray2.length(); i++) {
                            JSONObject jSONObject6 = jSONArray2.getJSONObject(i);
                            JSONObject jSONObject7 = new JSONObject();
                            jSONObject7.put("vod_id", jSONObject6.getString("id"));
                            jSONObject7.put("vod_name", jSONObject6.getString("name"));
                            jSONObject7.put("vod_pic", jSONObject6.getString("pic"));
                            jSONArray.put(jSONObject7);
                        }
                        jSONObject3.put("list", jSONArray);
                    } catch (JSONException unused) {
                    }
                }
            });
            return jSONObject3.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }
}