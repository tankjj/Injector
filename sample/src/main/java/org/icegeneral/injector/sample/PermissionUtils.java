package org.icegeneral.injector.sample;

import android.content.Context;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by tanjingjing on 2018/3/12.
 */

public class PermissionUtils {
    public static String getPermissionInfo(Context context, String permission) {
        if (context == null) {
            return "null context";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("checkPermission: ")
                .append(context.getClass().getName())
                .append(", ")
                .append(permission)
                .append(", ")
                .append(getStackTraceString(new Exception()));
        return builder.toString();
    }

    public static String getStackTraceString(Throwable tr) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
