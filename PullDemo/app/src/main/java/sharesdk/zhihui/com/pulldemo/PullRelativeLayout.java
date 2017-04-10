package sharesdk.zhihui.com.pulldemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;


/**
 * Created on 16/8/10.
 */
public class PullRelativeLayout extends RelativeLayout {

    private int topHidableHeigt = 0;// 向上滑动时 顶部隐藏的最大高度

    private onPullListener mListener;

    public PullRelativeLayout(Context context) {
        super(context);
    }

    public PullRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setmListener(onPullListener mListener) {
        this.mListener = mListener;
    }

    public void setTopHidableHeigt(int topHidableHeigt) {
        if (topHidableHeigt <= 0) {
            return;
        }
        this.topHidableHeigt = topHidableHeigt;
    }

    private boolean isPullState; // 滚动状态
    private boolean isPendingPullState; // 滚动状态
    private boolean hasCanceld; // 是否终端了事件
    private float pendingY;
    private float lastX;
    private float lastY;
    private int currPadding = 0; // 当前padding

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.i("douzi-pull", "action : " + ev.getAction() + " x : " + ev.getRawX() + " y : " + ev.getRawY());

        if (MotionEvent.ACTION_DOWN == ev.getAction()) {
            if (mListener != null && topHidableHeigt == 0) {
                setTopHidableHeigt(mListener.onGetTopHidableHeight());
            }
            reset();
            pendingY = ev.getRawY();
            lastX = ev.getRawX();
            lastY = ev.getRawY();
        }

        if (MotionEvent.ACTION_MOVE == ev.getAction()) {
            if (isPullState) {
                if (pull(lastY, ev.getRawY())) {
                    lastX = ev.getRawX();
                    lastY = ev.getRawY();
                    return true;
                } else {
                    if (hasCanceld) {
                        ev.setAction(MotionEvent.ACTION_DOWN);
                        hasCanceld = false;
                    }
                }
            } else {
                if (Math.abs(ev.getRawY() - lastY) > Math.abs(ev.getRawX() - lastX)) {
                    if (!isPendingPullState) {
                        pendingY = lastY;
                        isPendingPullState = true;
                    }
                } else {
                    isPendingPullState = false;
                }

                if (isPendingPullState && Math.abs(ev.getRawY() - pendingY) > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    isPullState = true;
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    hasCanceld = true;
//                    if (mListener != null) {
//                        mListener.onChildEnabled(false);
//                    }
                    if (pull(lastY, ev.getRawY())) {
                        lastX = ev.getRawX();
                        lastY = ev.getRawY();
                    }
                }
            }
            lastX = ev.getRawX();
            lastY = ev.getRawY();
        }

        return super.dispatchTouchEvent(ev);
    }

    private boolean pull(float start, float end) {
        Log.i("douzi-pull", "pull(" + start + ", " + end + ")");
        int paddingChanged = (int) (end - start);
        if (paddingChanged > 0 && currPadding >= 0) {
            return false;
        }

        if (paddingChanged < 0 && currPadding <= -topHidableHeigt) {
            return false;
        }

        int newPadding = currPadding + paddingChanged;
        if (newPadding < -topHidableHeigt) {
            newPadding = -topHidableHeigt;
        }
        if (newPadding > 0) {
            newPadding = 0;
        }
        updatePadding(newPadding);
        return true;
    }

    private void reset() {
        if (mListener != null) {
            mListener.onChildEnabled(true);
        }
        isPullState = false;
        isPendingPullState = true;
        hasCanceld = false;
    }

    public interface onPullListener {
        void onPullDown(int height);

        void onPullUp(int height);

        void onChildEnabled(boolean enabled);

        int onGetTopHidableHeight();
    }

    private void updatePadding(int padding) {
        Log.i("douzi-pull", "updatePadding(" + padding + ")");
        if (currPadding != padding) {
            currPadding = padding;
            Log.i("douzi-pull", "real-updatePadding(" + padding + ")");
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
            params.setMargins(0, padding, 0, 0);
            setLayoutParams(params);
        }
    }
}
