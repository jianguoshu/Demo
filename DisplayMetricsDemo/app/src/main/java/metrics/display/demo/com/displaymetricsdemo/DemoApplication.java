package metrics.display.demo.com.displaymetricsdemo;

import android.app.Application;

/**
 * Created on 16/11/25.
 */
public class DemoApplication extends Application {
    static DemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance(){
        return instance;
    }
}
