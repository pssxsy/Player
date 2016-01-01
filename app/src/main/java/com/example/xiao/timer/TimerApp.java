package com.example.xiao.timer;

import android.app.Application;

import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by xiao on 2015/12/13.
 */
public class TimerApp extends Application{
    private static Application sInstance;
    private static RefWatcher sWatcher;
    public static RefWatcher getRefWatcher() {
        return sWatcher;
    }
    public static Application getInstance() {
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();

//        ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults()
//                .instanceField("android.view.inputmethod.InputMethodManager.ControlledInputConnectionWrapper", "mParentInputMethodManager")
//                .build();

        sWatcher = LeakCanary.install(this);
        sInstance = this;
        getCacheDir();

    }
}
