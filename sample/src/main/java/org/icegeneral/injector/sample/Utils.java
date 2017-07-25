package org.icegeneral.injector.sample;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

/**
 * Created by linjianjun on 2017/7/24.
 */
public class Utils {

    public static String getClickText(View view) {
        if (view == null) {
            return "";
        }
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            return textView.getText().toString();
        }
        String text = deepSearchChildText(view);
        if (!TextUtils.isEmpty(text)) {
            return text;
        }
        return deepSearchParentText(view);
    }

    public static String deepSearchChildText(View view) {
        if (view == null) {
            return "";
        }
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            return textView.getText().toString();
        }
        if (view instanceof ViewGroup) {
            StringBuilder sb = null;
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                if (child instanceof TextView) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    TextView textView = (TextView) child;
                    sb.append(textView.getText().toString());
                }
            }
            if (sb != null && sb.length() > 0) {
                return sb.toString();
            }
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                return deepSearchChildText(child);
            }
        }
        return "";
    }

    public static String deepSearchParentText(View view) {
        ViewParent parent = view.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            StringBuilder sb = null;
            ViewGroup viewGroup = (ViewGroup) parent;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                if (child instanceof TextView) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    TextView textView = (TextView) child;
                    sb.append(textView.getText().toString());
                }
            }
            if (sb != null && sb.length() > 0) {
                return sb.toString();
            }
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                if (child != view) {
                    String text = deepSearchChildText(view);
                    if (!TextUtils.isEmpty(text)) {
                        return text;
                    }
                }
            }
        }
        ViewParent parentParent = parent.getParent();
        if (parentParent != null && parentParent instanceof ViewGroup) {
            return deepSearchParentText((View) parentParent);
        }
        return "";
    }

}
