package preference.demo.com.sharedpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created on 16/11/29.
 */
public class SpUtil {
    private static final String SP_NAME = "com.tjz.sp";
    private static final String SP_KEY = "key";
    static SharedPreferences preferences = null;

    public static void put(Context context, String value){
            preferences =context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        preferences.edit().putString(SP_KEY,value).commit();
    }

    public static String get(Context context){
            preferences =context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
        return preferences.getString(SP_KEY,null);
    }
}
