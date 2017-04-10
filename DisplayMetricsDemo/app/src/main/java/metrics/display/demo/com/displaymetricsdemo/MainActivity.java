package metrics.display.demo.com.displaymetricsdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);

        Log.i("displaydemo", "mDisplayMetrics.widthPixels" + mDisplayMetrics.widthPixels);
        Log.i("displaydemo", "mDisplayMetrics.heightPixels" + mDisplayMetrics.heightPixels);
        Log.i("displaydemo", "mDisplayMetrics.density" + mDisplayMetrics.density);
        Log.i("displaydemo", "mDisplayMetrics.scaledDensity" + mDisplayMetrics.scaledDensity);
        Log.i("displaydemo", "mDisplayMetrics.densityDpi" + mDisplayMetrics.densityDpi);
        Log.i("displaydemo", "mDisplayMetrics.xdpi" + mDisplayMetrics.xdpi);
        Log.i("displaydemo", "mDisplayMetrics.ydpi" + mDisplayMetrics.ydpi);
        Log.i("displaydemo", "activity_vertical_margin" + getResources().getDimension(R.dimen.activity_vertical_margin));

//        01-06 19:32:36.460 26054-26054/? I/displaydemo: mDisplayMetrics.widthPixels1440
//        01-06 19:32:36.460 26054-26054/? I/displaydemo: mDisplayMetrics.heightPixels2408
//        01-06 19:32:36.460 26054-26054/? I/displaydemo: mDisplayMetrics.density4.0
//        01-06 19:32:36.460 26054-26054/? I/displaydemo: mDisplayMetrics.scaledDensity4.0
//        01-06 19:32:36.460 26054-26054/? I/displaydemo: mDisplayMetrics.densityDpi640
//        01-06 19:32:36.460 26054-26054/? I/displaydemo: mDisplayMetrics.xdpi487.68
//        01-06 19:32:36.460 26054-26054/? I/displaydemo: mDisplayMetrics.ydpi488.902

//        01-07 11:36:39.442 3079-3079/? I/displaydemo: mDisplayMetrics.widthPixels540
//        01-07 11:36:39.442 3079-3079/? I/displaydemo: mDisplayMetrics.heightPixels960
//        01-07 11:36:39.442 3079-3079/? I/displaydemo: mDisplayMetrics.density1.5
//        01-07 11:36:39.442 3079-3079/? I/displaydemo: mDisplayMetrics.scaledDensity1.5
//        01-07 11:36:39.442 3079-3079/? I/displaydemo: mDisplayMetrics.densityDpi240
//        01-07 11:36:39.442 3079-3079/? I/displaydemo: mDisplayMetrics.xdpi221.225
//        01-07 11:36:39.442 3079-3079/? I/displaydemo: mDisplayMetrics.ydpi221.672

//        03-29 13:40:30.579 29517-29517/? I/displaydemo: mDisplayMetrics.widthPixels1440
//        03-29 13:40:30.579 29517-29517/? I/displaydemo: mDisplayMetrics.heightPixels2392
//        03-29 13:40:30.579 29517-29517/? I/displaydemo: mDisplayMetrics.density3.5
//        03-29 13:40:30.579 29517-29517/? I/displaydemo: mDisplayMetrics.scaledDensity3.5
//        03-29 13:40:30.579 29517-29517/? I/displaydemo: mDisplayMetrics.densityDpi560
//        03-29 13:40:30.580 29517-29517/? I/displaydemo: mDisplayMetrics.xdpi494.27
//        03-29 13:40:30.580 29517-29517/? I/displaydemo: mDisplayMetrics.ydpi492.606

//        01-19 17:59:28.580 16334-16334/metrics.display.demo.com.displaymetricsdemo I/displaydemo: mDisplayMetrics.widthPixels720
//        01-19 17:59:28.580 16334-16334/metrics.display.demo.com.displaymetricsdemo I/displaydemo: mDisplayMetrics.heightPixels1184
//        01-19 17:59:28.580 16334-16334/metrics.display.demo.com.displaymetricsdemo I/displaydemo: mDisplayMetrics.density2.0
//        01-19 17:59:28.580 16334-16334/metrics.display.demo.com.displaymetricsdemo I/displaydemo: mDisplayMetrics.scaledDensity2.0
//        01-19 17:59:28.580 16334-16334/metrics.display.demo.com.displaymetricsdemo I/displaydemo: mDisplayMetrics.densityDpi320
//        01-19 17:59:28.580 16334-16334/metrics.display.demo.com.displaymetricsdemo I/displaydemo: mDisplayMetrics.xdpi299.803
//        01-19 17:59:28.580 16334-16334/metrics.display.demo.com.displaymetricsdemo I/displaydemo: mDisplayMetrics.ydpi298.275
    }
}
