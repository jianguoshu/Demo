package sharesdk.zhihui.com.xydemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View one;
    private View two;
    private View three;
    private View threeChild;
    private boolean hasChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = this.findViewById(R.id.tv_one);
        two = this.findViewById(R.id.tv_two);
        three = this.findViewById(R.id.tv_three);
        threeChild = this.findViewById(R.id.tv_three_child);

        this.findViewById(R.id.tv_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.one.requestLayout();
                MainActivity.this.two.requestLayout();
                MainActivity.this.three.requestLayout();
                MainActivity.this.threeChild.requestLayout();
                threeChild.post(new Runnable() {
                    @Override
                    public void run() {
                        printXYInfo("reset");
                    }
                });
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        changeXY();
        super.onWindowFocusChanged(hasFocus);
    }

    private void changeXY() {
        if (!hasChanged) {
            printXYInfo("default");
            one.offsetLeftAndRight(50); // 修改mLeft、mRight（重新布局后恢复）
            one.offsetTopAndBottom(50); // 修改mTop、mBottom的值（重新布局后恢复）
            two.setTranslationX(50); // 修改TranslationX属性
            two.setTranslationY(50); // 修改TranslationY属性
            three.scrollBy(-50, -50);// 修改mScrollX、mScrollY属性
            printXYInfo("change");
            hasChanged = true;
        }
    }

    private void printXYInfo(String divider) {
        Rect oneLocalVisibleRect = new Rect();
        one.getLocalVisibleRect(oneLocalVisibleRect);
        Rect oneGlobalVisibleRect = new Rect();
        one.getGlobalVisibleRect(oneGlobalVisibleRect);
        int[] oneLocationInWindow = new int[2];
        one.getLocationInWindow(oneLocationInWindow);
        int[] oneLocationOnScreen = new int[2];
        one.getLocationOnScreen(oneLocationOnScreen);
        Rect twoLocalVisibleRect = new Rect();
        two.getLocalVisibleRect(twoLocalVisibleRect);
        Rect twoGlobalVisibleRect = new Rect();
        two.getGlobalVisibleRect(twoGlobalVisibleRect);
        int[] twoLocationInWindow = new int[2];
        two.getLocationInWindow(twoLocationInWindow);
        int[] twoLocationOnScreen = new int[2];
        two.getLocationOnScreen(twoLocationOnScreen);
        Rect threeLocalVisibleRect = new Rect();
        three.getLocalVisibleRect(threeLocalVisibleRect);
        Rect threeGlobalVisibleRect = new Rect();
        three.getGlobalVisibleRect(threeGlobalVisibleRect);
        int[] threeLocationInWindow = new int[2];
        three.getLocationInWindow(threeLocationInWindow);
        int[] threeLocationOnScreen = new int[2];
        three.getLocationOnScreen(threeLocationOnScreen);
        Rect threeChildLocalVisibleRect = new Rect();
        threeChild.getLocalVisibleRect(threeChildLocalVisibleRect);
        Rect threeChildGlobalVisibleRect = new Rect();
        threeChild.getGlobalVisibleRect(threeChildGlobalVisibleRect);
        int[] threeChildLocationInWindow = new int[2];
        threeChild.getLocationInWindow(threeChildLocationInWindow);
        int[] threeChildLocationOnScreen = new int[2];
        threeChild.getLocationOnScreen(threeChildLocationOnScreen);
        Log.i("douziXY", "-------");
        Log.i("douziXY", "========="+divider+"=========");
        Log.i("douziXY", "one.getLeft() : " + one.getLeft());
        Log.i("douziXY", "one.getRight() : " + one.getRight());
        Log.i("douziXY", "one.getTop() : " + one.getTop());
        Log.i("douziXY", "one.getBottom() : " + one.getBottom());
        Log.i("douziXY", "oneLocalVisibleRect : " + oneLocalVisibleRect);
        Log.i("douziXY", "oneGlobalVisibleRect : " + oneGlobalVisibleRect);
        Log.i("douziXY", "oneLocationInWindow : " + oneLocationInWindow[0] + "-" + oneLocationInWindow[1]);
        Log.i("douziXY", "oneLocationOnScreen : " + oneLocationOnScreen[0] + "-" + oneLocationOnScreen[1]);
        Log.i("douziXY", "------------------------");
        Log.i("douziXY", "two.getLeft() : " + two.getLeft());
        Log.i("douziXY", "two.getRight() : " + two.getRight());
        Log.i("douziXY", "two.getTop() : " + two.getTop());
        Log.i("douziXY", "two.getBottom() : " + two.getBottom());
        Log.i("douziXY", "twoLocalVisibleRect : " + twoLocalVisibleRect);
        Log.i("douziXY", "twoGlobalVisibleRect : " + twoGlobalVisibleRect);
        Log.i("douziXY", "twoLocationInWindow : " + twoLocationInWindow[0] + "-" + twoLocationInWindow[1]);
        Log.i("douziXY", "twoLocationOnScreen : " + twoLocationOnScreen[0] + "-" + twoLocationOnScreen[1]);
        Log.i("douziXY", "------------------------");
        Log.i("douziXY", "three.getLeft() : " + three.getLeft());
        Log.i("douziXY", "three.getRight() : " + three.getRight());
        Log.i("douziXY", "three.getTop() : " + three.getTop());
        Log.i("douziXY", "three.getBottom() : " + three.getBottom());
        Log.i("douziXY", "threeLocalVisibleRect : " + threeLocalVisibleRect);
        Log.i("douziXY", "threeGlobalVisibleRect : " + threeGlobalVisibleRect);
        Log.i("douziXY", "threeLocationInWindow : " + threeLocationInWindow[0] + "-" + threeLocationInWindow[1]);
        Log.i("douziXY", "threeLocationOnScreen : " + threeLocationOnScreen[0] + "-" + threeLocationOnScreen[1]);
        Log.i("douziXY", "------------------------");
        Log.i("douziXY", "threeChild.getLeft() : " + threeChild.getLeft());
        Log.i("douziXY", "threeChild.getRight() : " + threeChild.getRight());
        Log.i("douziXY", "threeChild.getTop() : " + threeChild.getTop());
        Log.i("douziXY", "threeChild.getBottom() : " + threeChild.getBottom());
        Log.i("douziXY", "threeChildLocalVisibleRect : " + threeChildLocalVisibleRect);
        Log.i("douziXY", "threeChildGlobalVisibleRect : " + threeChildGlobalVisibleRect);
        Log.i("douziXY", "threeChildLocationInWindow : " + threeChildLocationInWindow[0] + "-" + threeChildLocationInWindow[1]);
        Log.i("douziXY", "threeChildLocationOnScreen : " + threeChildLocationOnScreen[0] + "-" + threeChildLocationOnScreen[1]);
        Log.i("douziXY", "========="+divider+"=========");
        Log.i("douziXY", "-------");
    }
}

// 02-17 14:04:18.732 19036-19036/? I/douziXY: =========printXYInfo()=========
//        02-17 14:04:18.732 19036-19036/? I/douziXY: one.getLeft() : 100
//        02-17 14:04:18.732 19036-19036/? I/douziXY: one.getTop() : 100
//        02-17 14:04:18.732 19036-19036/? I/douziXY: oneLocalVisibleRect : Rect(0, 0 - 100, 100)
//        02-17 14:04:18.732 19036-19036/? I/douziXY: oneGlobalVisibleRect : Rect(100, 196 - 200, 296)
//        02-17 14:04:18.732 19036-19036/? I/douziXY: oneLocationInWindow : 100-196
//        02-17 14:04:18.732 19036-19036/? I/douziXY: oneLocationOnScreen : 100-196
//        02-17 14:04:18.732 19036-19036/? I/douziXY: ------------------------
//        02-17 14:04:18.732 19036-19036/? I/douziXY: two.getLeft() : 100
//        02-17 14:04:18.732 19036-19036/? I/douziXY: two.getTop() : 100
//        02-17 14:04:18.733 19036-19036/? I/douziXY: twoLocalVisibleRect : Rect(0, 0 - 100, 100)
//        02-17 14:04:18.733 19036-19036/? I/douziXY: twoGlobalVisibleRect : Rect(100, 496 - 200, 596)
//        02-17 14:04:18.733 19036-19036/? I/douziXY: twoLocationInWindow : 100-496
//        02-17 14:04:18.733 19036-19036/? I/douziXY: twoLocationOnScreen : 100-496
//        02-17 14:04:18.733 19036-19036/? I/douziXY: ------------------------
//        02-17 14:04:18.733 19036-19036/? I/douziXY: three.getLeft() : 100
//        02-17 14:04:18.733 19036-19036/? I/douziXY: three.getTop() : 100
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeLocalVisibleRect : Rect(0, 0 - 100, 100)
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeGlobalVisibleRect : Rect(100, 796 - 200, 896)
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeLocationInWindow : 100-796
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeLocationOnScreen : 100-796
//        02-17 14:04:18.733 19036-19036/? I/douziXY: ------------------------
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeChild.getLeft() : 0
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeChild.getTop() : 0
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeChildLocalVisibleRect : Rect(0, 0 - 100, 100)
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeChildGlobalVisibleRect : Rect(100, 796 - 200, 896)
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeChildLocationInWindow : 100-796
//        02-17 14:04:18.733 19036-19036/? I/douziXY: threeChildLocationOnScreen : 100-796
// 02-17 14:04:18.733 19036-19036/? I/douziXY: =========printXYInfo()=========
//        02-17 14:04:18.733 19036-19036/? I/douziXY: one.getLeft() : 150
//        02-17 14:04:18.733 19036-19036/? I/douziXY: one.getTop() : 150
//        02-17 14:04:18.734 19036-19036/? I/douziXY: oneLocalVisibleRect : Rect(0, 0 - 100, 100)
//        02-17 14:04:18.734 19036-19036/? I/douziXY: oneGlobalVisibleRect : Rect(150, 246 - 250, 346)
//        02-17 14:04:18.734 19036-19036/? I/douziXY: oneLocationInWindow : 150-246
//        02-17 14:04:18.734 19036-19036/? I/douziXY: oneLocationOnScreen : 150-246
//        02-17 14:04:18.734 19036-19036/? I/douziXY: ------------------------
//        02-17 14:04:18.734 19036-19036/? I/douziXY: two.getLeft() : 100
//        02-17 14:04:18.734 19036-19036/? I/douziXY: two.getTop() : 100
//        02-17 14:04:18.734 19036-19036/? I/douziXY: twoLocalVisibleRect : Rect(0, 0 - 100, 100)
//        02-17 14:04:18.734 19036-19036/? I/douziXY: twoGlobalVisibleRect : Rect(150, 546 - 250, 646)
//        02-17 14:04:18.734 19036-19036/? I/douziXY: twoLocationInWindow : 150-546
//        02-17 14:04:18.734 19036-19036/? I/douziXY: twoLocationOnScreen : 150-546
//        02-17 14:04:18.734 19036-19036/? I/douziXY: ------------------------
//        02-17 14:04:18.734 19036-19036/? I/douziXY: three.getLeft() : 100
//        02-17 14:04:18.734 19036-19036/? I/douziXY: three.getTop() : 100
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeLocalVisibleRect : Rect(-50, -50 - 50, 50)
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeGlobalVisibleRect : Rect(100, 796 - 200, 896)
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeLocationInWindow : 100-796
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeLocationOnScreen : 100-796
//        02-17 14:04:18.734 19036-19036/? I/douziXY: ------------------------
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeChild.getLeft() : 0
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeChild.getTop() : 0
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeChildLocalVisibleRect : Rect(0, 0 - 50, 50)
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeChildGlobalVisibleRect : Rect(150, 846 - 200, 896)
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeChildLocationInWindow : 150-846
//        02-17 14:04:18.734 19036-19036/? I/douziXY: threeChildLocationOnScreen : 150-846