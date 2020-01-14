package com.pullein.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;

/**
 * ProjectName: X-Util
 * ClassName: SPUtil
 * Author: XG
 * CreateDate: 2020/1/14 22:05
 * Description:SP工具类
 */
public class SPUtil {
    private static Context mContext;
    private static String mFileName;

    private SPUtil() {
    }

    public static void init(Context context) {
        init(context, context.getPackageName().replaceAll("\\.", "_"));
    }

    public static void init(Context context, String fileName) {
        mContext = context;
        mFileName = fileName;
    }


    public static void putInt(String key, int value) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePre.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putString(String key, String value) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePre.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePre.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putFloat(String key, float value) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePre.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void putLong(String key, long value) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePre.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void putStringSet(String key, Set<String> value) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePre.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public static <T> void putObject(String key, T value) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            String str = new String(Base64.encode(bos.toByteArray(), Base64.DEFAULT));
            SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharePre.edit();
            editor.putString(key, str);
            editor.apply();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            CloseableUtil.close(oos, bos);
        }
    }

    public static <T> T getObject(String key) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        String res = sharePre.getString(key, null);
        if (TextUtils.isEmpty(res)) {
            return null;
        }
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //读取字节
            byte[] base64 = Base64.decode(res.getBytes(), Base64.DEFAULT);
            //封装到字节流
            bis = new ByteArrayInputStream(base64);
            //再次封装
            ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            CloseableUtil.close(ois, bis);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getAllMap(Context context) {
        SharedPreferences sharePre = context.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return (Map<String, Object>) sharePre.getAll();
    }


    public static float getFloat(String key) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getFloat(key, -1);
    }

    public static float getFloat(String key, float def) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getFloat(key, def);
    }

    public static long getLong(String key) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getLong(key, -1);
    }

    public static long getLong(String key, long def) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getLong(key, def);
    }

    public static Set<String> getStringSet(String key) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getStringSet(key, null);
    }

    public static Set<String> getStringSet(String key, Set<String> def) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getStringSet(key, def);
    }

    public static int getInt(String key) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getInt(key, -1);
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getInt(key, defValue);
    }

    public static String getString(String key) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getString(key, null);
    }

    public static String getString(String key, String defaultValue) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getString(key, defaultValue);
    }

    public static Boolean getBoolean(String key) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getBoolean(key, false);
    }


    public static Boolean getBoolean(String key, boolean def) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.getBoolean(key, def);
    }

    public static void clearData() {
        SharedPreferences sharedPre = mContext.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.clear();
        editor.apply();
    }

    public static void removeKey(String key) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePre.edit();
        editor.remove(key);
        editor.apply();
    }

    public static boolean containsKey(String key) {
        SharedPreferences sharePre = mContext.getSharedPreferences(mFileName,
                Context.MODE_PRIVATE);
        return sharePre.contains(key);
    }
}
