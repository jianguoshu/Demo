package metrics.display.demo.com.displaymetricsdemo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created on 16/11/25.
 */
public class TestDemo {
    public static void main(String[] args){
        System.out.print("gaga");

        DisplayMetrics mDisplayMetrics = null;
        WindowManager windowManager = (WindowManager) DemoApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(mDisplayMetrics);
        System.out.print("gaga");
    }
}
