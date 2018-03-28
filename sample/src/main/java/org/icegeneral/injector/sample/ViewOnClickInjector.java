package org.qiyi.video.utils.injector;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by tanjingjing on 2017/11/23.
 */

public class ViewOnClickInjector {
    private static int SCREEN_WIDTH = 0;
    private static int SCREEN_HEIGHT = 0;

    public static String getViewInfo(View view) {
        if (view == null)
            return "";

        StringBuilder builder = new StringBuilder();
        if (view.getContext() != null && SCREEN_WIDTH == 0) {
            WindowManager windowManager = (WindowManager)view.getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(metrics);
            SCREEN_WIDTH = metrics.widthPixels;
            SCREEN_HEIGHT = metrics.heightPixels;
        }
        builder.append("screen_width = ").append(SCREEN_WIDTH).append(", screen_height = ").append(SCREEN_HEIGHT).append(",");

        ViewParent parent = view.getParent();
        int[] location = new int[2];
        if (parent != null && parent instanceof View) {
            ((View)parent).getLocationOnScreen(location);
            builder.append("parent_width = ").append(((View) parent).getWidth()).append(", parent_height = ").append(((View) parent).getHeight()).append(",");
            builder.append("parent_x = ").append(location[0]).append(", parent_y = ").append(location[1]).append(",");
        }

        builder.append(getViewInfo( view, false));

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                if (((ViewGroup) view).getChildAt(i) instanceof TextView) {
                    builder.append(getViewInfo( ((ViewGroup) view).getChildAt(i), true));
                }
            }
        }

        return builder.toString();
    }

    private static String getViewInfo(View view, boolean child) {
        if (view == null)
            return "";

        StringBuilder builder = new StringBuilder();
        if (child) {
            builder.append("child_view_width = ").append(view.getWidth()).append(",");
            builder.append("child_view_height = ").append(view.getHeight()).append(",");
            builder.append("child_view_x = ").append(view.getX()).append(",");
            builder.append("child_view_y = ").append(view.getY()).append(",");
            if (view instanceof TextView) {
                builder.append("child_view_text = ").append(((TextView)view).getText()).append(",");
                builder.append("child_view_text_size = ").append(((TextView)view).getTextSize()).append(",");
            }
        } else {
            builder.append("view_width = ").append(view.getWidth()).append(",");
            builder.append("view_height = ").append(view.getHeight()).append(",");
            builder.append("view_x = ").append(view.getX()).append(",");
            builder.append("view_y = ").append(view.getY()).append(",");
            if (view instanceof TextView) {
                builder.append("view_text = ").append(((TextView)view).getText()).append(",");
                builder.append("view_text_size = ").append(((TextView)view).getTextSize()).append(",");
            }
        }

        return builder.toString();
    }
}
