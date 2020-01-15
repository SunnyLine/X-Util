package com.pullein.demo;

import android.app.Application;

import com.pullein.common.android.ApplicationHelper;
import com.pullein.common.android.CrashHandler;
import com.pullein.common.listener.AppCrashListener;
import com.pullein.common.listener.AppProcessStateListener;
import com.pullein.common.util.FileUtil;
import com.pullein.common.util.Log;
import com.pullein.common.util.SPUtil;

/**
 * X-Util<br>
 * describe ：
 *
 * @author xugang
 * @date 2020/1/15
 */
public class MyApp extends Application {
    private ApplicationHelper mHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.init(getApplicationContext(),true);
        SPUtil.init(getApplicationContext());
        new  CrashHandler.Builder(getApplicationContext())
                .setPrintErrorLog(true)
                .setCrashFileSavePath(getExternalFilesDir(null)+"")
                .setAppCrashListener(new AppCrashListener() {
                    @Override
                    public void onCrash() {
                        Log.d("App 崩溃了");
                    }
                })
                .build();
        mHelper = new ApplicationHelper();
        mHelper.registerActivityLifecycle(this, new AppProcessStateListener() {
            @Override
            public void onProcessForeground() {
                Log.d("进入前台");
            }

            @Override
            public void onProcessBackground() {
                Log.d("进入后台");
            }
        });
    }
}
