package org.icegeneral.injector.sample;

import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by tanjingjing on 2017/12/25.
 */

public class ClickManager implements View.OnClickListener {
    public static final boolean PRE_CLICK_OK = true;
    public static final boolean PRE_CLICK_NOT_OK = false;
    private static long mLastClickTime;
    private static int mViewId;
    private HashMap<Integer, IOnClick> mClickHashMap = new HashMap();
    private HashSet<Integer> mNormalClick = new HashSet();
    private ClickManager.IPreClick mPreClick;
    private View.OnClickListener mNormalClickListener;

    public ClickManager() {
    }

    public void addListener(View view, ClickManager.IOnClick callback) {
        if (view != null) {
            view.setOnClickListener(this);
            this.mClickHashMap.put(Integer.valueOf(view.getId()), callback);
        }
    }

    public void removeClickListener(View view) {
        if (view != null) {
            this.mClickHashMap.remove(Integer.valueOf(view.getId()));
        }
    }

    public void addToNormalClick(View view) {
        if (view != null) {
            view.setOnClickListener(this);
            this.mNormalClick.add(Integer.valueOf(view.getId()));
        }

    }

    public void setPreClickListener(ClickManager.IPreClick callable) {
        this.mPreClick = callable;
    }

    public void setNormalClickListener(View.OnClickListener listener) {
        this.mNormalClickListener = listener;
    }

    public boolean checkPreClick() {
        if (this.mPreClick == null) {
            return true;
        } else {
            try {
                return this.mPreClick.onPreClick();
            } catch (Exception var2) {
                var2.printStackTrace();
                this.log("exception on pre click");
                this.log(var2.getMessage());
                return false;
            }
        }
    }

    public void onClick(View view) {
        this.log("View Id:" + view.getId() + " Clicked");
        if (this.mPreClick != null) {
            try {
                if (!this.mPreClick.onPreClick()) {
                    return;
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        int id = view.getId();
        ClickManager.IOnClick callback = (ClickManager.IOnClick) this.mClickHashMap.get(Integer.valueOf(id));
        if (callback != null) {
            callback.onClick(view);
        } else {
            if (this.mNormalClickListener != null && this.mNormalClick != null && this.mNormalClick.contains(Integer.valueOf(id))) {
                this.mNormalClickListener.onClick(view);
            }

        }
    }

    public static boolean isFastDoubleClick() {
        boolean rtn = false;
        if (System.currentTimeMillis() - mLastClickTime < 500L) {
            rtn = true;
            //PPLog.d("------click too fast!");
        }

        mLastClickTime = System.currentTimeMillis();
        return rtn;
    }

    public static boolean isFastDoubleClick(int id) {
        boolean rtn = false;
        if (System.currentTimeMillis() - mLastClickTime < 500L) {
            if (mViewId == id) {
                rtn = true;
                //PPLog.d("--click same view too fast!");
            }

            mViewId = id;
        }

        mLastClickTime = System.currentTimeMillis();
        return rtn;
    }

    private void log(String message) {
        Log.e("ClickManager", message == null ? "" : message);
    }

    public interface IPreClick {
        boolean onPreClick();
    }

    public interface IOnClick {
        void onClick(View var1);
    }

    public interface TestOnClick extends View.OnClickListener {
        void onClick(View view);
    }

    public class TestOnClickClass {
        void onClick(View view) {}
    }
}
