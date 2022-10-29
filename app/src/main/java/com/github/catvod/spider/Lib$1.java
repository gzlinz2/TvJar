//
// Decompiled by Jadx - 592ms
//
package com.github.catvod.spider;

import com.github.catvod.crawler.SpiderDebug;
import java.util.Comparator;
import org.json.JSONException;

class Lib$1 implements Comparator<String> {
    final Lib ue;

    Lib$1(Lib lib) {
        this.ue = lib;
    }

    @Override
    public int compare(String str, String str2) {
        return compare(str, str2);
    }

    public int compare(String str, String str2) {
        try {
            int i = Lib.u(this.ue).getJSONObject(str).getInt("or");
            int i2 = Lib.u(this.ue).getJSONObject(str2).getInt("or");
            return (i != i2 && i - i2 <= 0) ? -1 : 1;
        } catch (JSONException e) {
            SpiderDebug.log(e);
            return 1;
        }
    }
}
