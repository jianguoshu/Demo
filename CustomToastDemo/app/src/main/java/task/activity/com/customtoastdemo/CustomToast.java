package task.activity.com.customtoastdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created on 16/9/22.
 */


public class CustomToast implements View.OnClickListener {
    private Activity mActivity;
    private Handler cancelHandler;
    private int marginBottom = 200;
    private onViewClickListener viewClickListener;
    private WindowManager windowManager;
    private View mContentView;
    private WindowManager.LayoutParams mParams;

    public static final int FLAG_CANCEL_TOAST = 1;

    /**
     * 初始化toast
     *
     * @param activity
     */
    private void initToast(Activity activity) {
        mActivity = activity;
        cancelHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case FLAG_CANCEL_TOAST:
                        hide();
                        break;
                    default:
                        break;
                }
            }
        };
        windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.windowAnimations = R.style.custom_toast_anim_view;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.gravity = Gravity.BOTTOM;
        mParams.y = marginBottom;
        mParams.format = PixelFormat.TRANSLUCENT; // 显示效果

//        String packname = mContext.getPackageName();
//        PackageManager pm = context.getPackageManager();
//        boolean permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.SYSTEM_ALERT_WINDOW", packname));
//        if(permission){
//            mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;
//        }else{
//            mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
//        }

        mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//                |WindowManager.LayoutParams.FLAG_FULLSCREEN
//                |WindowManager.LayoutParams. FLAG_WATCH_OUTSIDE_TOUCH;
        //不允许获得焦点,toast的通性
        //  WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        //不允许接收触摸事件
        //  | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

    }

    /**
     * 显示toast
     *
     * @param activity
     */
    public final void show(Activity activity, final onViewClickListener viewClickListener) {
        mActivity = activity;
        hide();
        initToast(mActivity);
        this.viewClickListener = viewClickListener;

        mContentView = LayoutInflater.from(mActivity).inflate(R.layout.toast, null);
        LinearLayout content = (LinearLayout) mContentView.findViewById(R.id.ll_content);
        content.setOnClickListener(this);
        TextView option = (TextView) mContentView.findViewById(R.id.tv_option);
        option.setOnClickListener(this);

        windowManager.addView(mContentView, mParams);

        Message message = cancelHandler.obtainMessage(FLAG_CANCEL_TOAST);
        cancelHandler.sendMessageDelayed(message, 3000);

        mContentView.setFocusableInTouchMode(true);
        mContentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (viewClickListener != null) {
                        viewClickListener.onBackKeyDown();
                    }
                }
                return false;
            }
        });
    }

    /**
     * 隐藏toast
     */
    public final void hide() {
        if (cancelHandler != null) {
            cancelHandler.removeMessages(FLAG_CANCEL_TOAST);
            cancelHandler = null;
        }
        viewClickListener = null;
        if (mContentView != null) {
            if (mContentView.getParent() != null) {
                windowManager.removeView(mContentView);
            }
            mContentView = null;
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_content:
                if (viewClickListener != null) {
                    viewClickListener.onContentClick();
                }
                break;
            case R.id.tv_option:
                if (viewClickListener != null) {
                    viewClickListener.onOptionClick();
                }
                break;
        }
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public interface onViewClickListener {
        void onContentClick();

        void onOptionClick();

        void onBackKeyDown();
    }
}