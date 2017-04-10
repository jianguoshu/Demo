package task.activity.com.progressdemo;

import android.app.Application;
import android.util.Log;

/**
 * Created on 16/9/7.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("douzi", "====================================");
    }
}
