package sharesdk.zhihui.com.pulldemo;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

public class TranslationWebView extends WebView {

    private float currTransY;

    private float transYHeight;

    private onTranslationChangeListener mListener;

    public void setTranslationListener(onTranslationChangeListener mListener) {
        this.mListener = mListener;
    }

    public void setTransYHeight(float transYHeight) {
        this.transYHeight = transYHeight;
    }

    public TranslationWebView(Context context) {
        super(context);
    }

    public TranslationWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslationWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final int[] mScrollOffset = new int[2];
    private final int[] mScrollConsumed = new int[2];
    private int mLastMotionY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionY = (int) event.getY();
                return super.onTouchEvent(event);
            case MotionEvent.ACTION_MOVE:
                MotionEvent vtev = MotionEvent.obtain(event);

                final int y = (int) event.getY();
                int deltaY = mLastMotionY - y;
                if (this.dispatchNestedPreScroll(0, deltaY, mScrollConsumed, mScrollOffset)) {
                    Log.i("douzi", "mScrollConsumed[1] : " + mScrollConsumed[1]);
                    vtev.offsetLocation(0, mScrollConsumed[1]);
                }
                mLastMotionY = y + mScrollConsumed[1];
                Log.i("douzi", "mLastMotionY : " + mLastMotionY);

                return super.onTouchEvent(vtev);
            default:
                return super.onTouchEvent(event);
        }
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        int consumedY = 0;

        if(dy > 0 && -currTransY < transYHeight){
            consumedY = (int) Math.min(dy, currTransY + transYHeight);
            currTransY += -consumedY;
            if (mListener != null) {
                mListener.onTranslationYChanged(currTransY);
            }
        } else if(dy < 0 && currTransY < 0){
            consumedY = (int) Math.max(dy, currTransY);
            currTransY += -consumedY;
            if (mListener != null) {
                mListener.onTranslationYChanged(currTransY);
            }
        }
        consumed[1] = consumedY;
        return consumedY != 0;
    }

    public interface onTranslationChangeListener {
        void onTranslationYChanged(float transY);
    }
}
