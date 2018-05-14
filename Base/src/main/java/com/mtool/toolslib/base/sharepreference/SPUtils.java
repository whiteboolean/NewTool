package com.mtool.toolslib.base.sharepreference;

/**
 * Created by gerry on 2017/4/29.
 */


import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * must call init at your application level or equiv, before you use any other methods
 * Created by gerry
 */
public class SPUtils {

    private static SharedPreferences sharedPreferences;
    private static final String preferencesName = "preferences";
    private static final String LENGTH = "_length";
    private static final String DEFAULT_STRING_VALUE = "";
    private static final int DEFAULT_INT_VALUE = -1;
    private static final double DEFAULT_DOUBLE_VALUE = -1d;
    private static final float DEFAULT_FLOAT_VALUE = -1f;
    private static final long DEFAULT_LONG_VALUE = -1L;
    private static final boolean DEFAULT_BOOLEAN_VALUE = false;
    public Context context;

    private static SPUtils instance;

    public SPUtils(Context context){
        this.context = context;
    }

    public static SPUtils getInstance(Context context) {
        instance = null;
        instance = new SPUtils(context);
        return instance;
    }
    private SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = context
                    .getApplicationContext().getSharedPreferences(
                            preferencesName,
                            Context.MODE_PRIVATE
                    );
        }
        return sharedPreferences;
    }


//    private SPUtils(@NonNull Context context) {
//        if (sharedPreferences == null) {
//            sharedPreferences = context.getApplicationContext().getSharedPreferences(
//                    preferencesName,
//                    Context.MODE_PRIVATE
//            );
//        }
//    }
//
//    /**
//     * @param context
//     * @return Returns a 'Prefs' instance
//     */
//    public static SPUtils init(@NonNull Context context) {
//        if (prefsInstance == null) {
//            prefsInstance = new SPUtils(context);
//        }
//        return prefsInstance;
//    }

    /**
     * @param what
     * @return Returns the stored value of 'what'
     */
    public String getString(String what) {
        return getSharedPreferences().getString(what, DEFAULT_STRING_VALUE);
    }

    /**
     * @param what
     * @param defaultString
     * @return Returns the stored value of 'what'
     */
    public String getString(String what, String defaultString) {
        return getSharedPreferences().getString(what, defaultString);
    }

    /**
     * @param where
     * @param what
     */
    public void putString(String where, String what) {
        getSharedPreferences().edit().putString(where, what).apply();
    }

    // int related methods

    /**
     * @param what
     * @return Returns the stored value of 'what'
     */
    public int getInt(String what) {
        return getSharedPreferences().getInt(what, DEFAULT_INT_VALUE);
    }

    /**
     * @param what
     * @param defaultInt
     * @return Returns the stored value of 'what'
     */
    public int getInt(String what, int defaultInt) {
        return getSharedPreferences().getInt(what, defaultInt);
    }

    /**
     * @param where
     * @param what
     */
    public void putInt(String where, int what) {
        getSharedPreferences().edit().putInt(where, what).apply();
    }

    // double related methods

    /**
     * @param what
     * @return Returns the stored value of 'what'
     */
    public double getDouble(String what) {
        if (!contains(what))
            return DEFAULT_DOUBLE_VALUE;
        return Double.longBitsToDouble(getLong(what));
    }

    /**
     * @param what
     * @param defaultDouble
     * @return Returns the stored value of 'what'
     */
    public double getDouble(String what, double defaultDouble) {
        if (!contains(what))
            return defaultDouble;
        return Double.longBitsToDouble(getLong(what));
    }

    /**
     * @param where
     * @param what
     */
    public void putDouble(String where, double what) {
        putLong(where, Double.doubleToRawLongBits(what));
    }

    // float related methods

    /**
     * @param what
     * @return Returns the stored value of 'what'
     */
    public float getFloat(String what) {
        return getSharedPreferences().getFloat(what, DEFAULT_FLOAT_VALUE);
    }

    /**
     * @param what
     * @param defaultFloat
     * @return Returns the stored value of 'what'
     */
    public float getFloat(String what, float defaultFloat) {
        return getSharedPreferences().getFloat(what, defaultFloat);
    }

    /**
     * @param where
     * @param what
     */
    public void putFloat(String where, float what) {
        getSharedPreferences().edit().putFloat(where, what).apply();
    }

    // long related methods

    /**
     * @param what
     * @return Returns the stored value of 'what'
     */
    public long getLong(String what) {
        return getSharedPreferences().getLong(what, DEFAULT_LONG_VALUE);
    }

    /**
     * @param what
     * @param defaultLong
     * @return Returns the stored value of 'what'
     */
    public long getLong(String what, long defaultLong) {
        return getSharedPreferences().getLong(what, defaultLong);
    }

    /**
     * @param where
     * @param what
     */
    public void putLong(String where, long what) {
        getSharedPreferences().edit().putLong(where, what).apply();
    }

    // boolean related methods

    /**
     * @param what
     * @return Returns the stored value of 'what'
     */
    public boolean getBoolean(String what) {
        return getSharedPreferences().getBoolean(what, DEFAULT_BOOLEAN_VALUE);
    }

    /**
     * @param what
     * @param defaultBoolean
     * @return Returns the stored value of 'what'
     */
    public boolean getBoolean(String what, boolean defaultBoolean) {
        return getSharedPreferences().getBoolean(what, defaultBoolean);
    }

    /**
     * @param where
     * @param what
     */
    public void putBoolean(String where, boolean what) {
        getSharedPreferences().edit().putBoolean(where, what).apply();
    }

    // String set methods

    /**
     * @param key
     * @param value
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void putStringSet(final String key, final Set<String> value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getSharedPreferences().edit().putStringSet(key, value).apply();
        } else {
            // Workaround for pre-HC's lack of StringSets
            putOrderedStringSet(key, value);
        }
    }

    /**
     * @param key
     * @param value
     */
    public void putOrderedStringSet(String key, Set<String> value) {
        int stringSetLength = 0;
        if (getSharedPreferences().contains(key + LENGTH)) {
            // First getString what the value was
            stringSetLength = getInt(key + LENGTH);
        }
        putInt(key + LENGTH, value.size());
        int i = 0;
        for (String aValue : value) {
            putString(key + "[" + i + "]", aValue);
            i++;
        }
        for (; i < stringSetLength; i++) {
            // Remove any remaining values
            remove(key + "[" + i + "]");
        }
    }

    /**
     * @param key
     * @param defValue
     * @return Returns the String Set with HoneyComb compatibility
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(final String key, final Set<String> defValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return getSharedPreferences().getStringSet(key, defValue);
        } else {
            // Workaround for pre-HC's missing getStringSet
            return getOrderedStringSet(key, defValue);
        }
    }

    /**
     * @param key
     * @param defValue
     * @return Returns the ordered String Set
     */
    public Set<String> getOrderedStringSet(String key, final Set<String> defValue) {
        if (contains(key + LENGTH)) {
            LinkedHashSet<String> set = new LinkedHashSet<>();
            int stringSetLength = getInt(key + LENGTH);
            if (stringSetLength >= 0) {
                for (int i = 0; i < stringSetLength; i++) {
                    set.add(getString(key + "[" + i + "]"));
                }
            }
            return set;
        }
        return defValue;
    }

    // end related methods

    /**
     * @param key
     */
    public void remove(final String key) {
        if (contains(key + LENGTH)) {
            // Workaround for pre-HC's lack of StringSets
            int stringSetLength = getInt(key + LENGTH);
            if (stringSetLength >= 0) {
                getSharedPreferences().edit().remove(key + LENGTH).apply();
                for (int i = 0; i < stringSetLength; i++) {
                    getSharedPreferences().edit().remove(key + "[" + i + "]").apply();
                }
            }
        }
        getSharedPreferences().edit().remove(key).apply();
    }

    /**
     * @param key
     * @return Returns if that key exists
     */
    public boolean contains(final String key) {
        return getSharedPreferences().contains(key);
    }

    /**
     * Clear all the preferences
     */
    public void clear() {
        getSharedPreferences().edit().clear().apply();
    }

}