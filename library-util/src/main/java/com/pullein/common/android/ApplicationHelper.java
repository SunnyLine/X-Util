package com.pullein.common.android;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.pullein.common.listener.AppProcessStateListener;
import com.pullein.common.util.CollectionUtil;
import com.pullein.common.util.Log;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ProjectName: X-Util
 * ClassName: ApplicationHelper
 * Author: XG
 * CreateDate: 2020/1/14 22:54
 * Description:Application工具，维护Activity栈，
 */
public class ApplicationHelper {
    private List<Activity> activityList = Collections.synchronizedList(new LinkedList<Activity>());
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * 获取栈顶的Activity
     *
     * @return
     */
    public Activity getTopActivity() {
        if (CollectionUtil.isEmpty(activityList)) {
            return null;
        }
        return activityList.get(activityList.size() - 1);
    }

    public void addActivity(Activity activity) {
        if (activityList != null) {
            activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activityList != null) {
            activityList.remove(activity);
        }
    }

    /**
     * 关闭所有的Activity
     */
    public void closeAllActivity() {
        try {
            if (CollectionUtil.isEmpty(activityList)) {
                return;
            }
            Iterator<Activity> iterator = activityList.iterator();
            while (iterator.hasNext()) {
                Activity activity = iterator.next();
                activity.finish();
                iterator.remove();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * APP是否在前台
     *
     * @return
     */
    public boolean isAppForeground() {
        return count.get() != 0;
    }

    /**
     * 注册Activity生命周期
     */
    public void registerActivityLifecycle(Application application, final AppProcessStateListener appProcessStateListener) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (activity != null) {
                    Log.i(activity.getClass().getSimpleName() + "\tonActivityCreated");
                }
                addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (activity != null) {
                    Log.i(activity.getClass().getSimpleName() + "\tonActivityStarted");
                }
                if (count.get() == 0) {
                    if (appProcessStateListener != null) {
                        appProcessStateListener.onProcessForeground();
                    }
                }
                count.addAndGet(1);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (activity != null) {
                    Log.i(activity.getClass().getSimpleName() + "\tonActivityResumed");
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
                if (activity != null) {
                    Log.i(activity.getClass().getSimpleName() + "\tonActivityPaused");
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {
                if (activity != null) {
                    Log.i(activity.getClass().getSimpleName() + "\tonActivityStopped");
                }
                count.addAndGet(-1);
                if (count.get() == 0) {
                    if (appProcessStateListener != null) {
                        appProcessStateListener.onProcessBackground();
                    }
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                if (activity != null) {
                    Log.i(activity.getClass().getSimpleName() + "\tonActivitySaveInstanceState");
                }
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (activity != null) {
                    Log.i(activity.getClass().getSimpleName() + "\tonActivityDestroyed");
                }
                removeActivity(activity);
            }
        });
    }
}
