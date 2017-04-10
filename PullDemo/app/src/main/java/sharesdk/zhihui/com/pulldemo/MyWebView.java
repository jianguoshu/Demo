package sharesdk.zhihui.com.pulldemo;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

public class MyWebView extends WebView {



    public void setHeader(View header) {
        this.header = header;
    }

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final int[] mScrollOffset = new int[2];
    private final int[] mScrollConsumed = new int[2];
    private int mLastMotionY;

    View header;

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
                    vtev.offsetLocation(0, mScrollConsumed[1]);
                }
                mLastMotionY = y + mScrollConsumed[1];

                return super.onTouchEvent(vtev);
            default:
                return super.onTouchEvent(event);
        }
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        int consumedY = 0;

        if(dy > 0 && -header.getTranslationY() < header.getHeight()){
            consumedY = (int) Math.min(dy, header.getTranslationY() + header.getHeight());
            move(header, -consumedY);
            move(this, -consumedY);

        } else if(dy < 0 && header.getTranslationY() < 0){
            consumedY = (int) Math.max(dy, header.getTranslationY());
            move(header, -consumedY);
            move(this, -consumedY);
        }
        consumed[1] = consumedY;
        return consumedY != 0;
    }

    private void move(View view, int distance){
        view.setTranslationY(view.getTranslationY() + distance);
    }
}
