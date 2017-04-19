package com.example.douzi.expendabletextview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by douzi on 2017/4/19.
 */

public class ExpendableTextView extends TextView {

    private boolean hasInit;

    public ExpendableTextView(Context context) {
        super(context);
    }

    public ExpendableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpendableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int lineVisual = 3; // 最大可见行数
    int linesTotal = 0; // 展示全部文本需要的行数
    boolean isExpend = false; // 记录展开收起状态

    @Override
    public void setText(CharSequence text, BufferType type) {
        expend(isExpend);
        super.setText(text, type);
        if (hasInit && text != null) {
            int width = getWidth();
            StaticLayout staticLayout = new StaticLayout(text, 0, text.length(), getPaint(), width,
                    Layout.Alignment.ALIGN_NORMAL, 0, 0, false, TextUtils.TruncateAt.END, 0);
            linesTotal = staticLayout.getLineCount();
            expend(isExpend);
        }
    }

    public boolean expendable() {
        return linesTotal > lineVisual;
    }


    public boolean isExpend() {
        return isExpend;
    }

    public void setExpend(boolean expend) {
        if (isExpend != expend) {
            expend(expend);
            requestLayout();
        }
    }

    private void expend(boolean expend) {
        isExpend = expend;
        if (isExpend) {
            setMaxLines(Integer.MAX_VALUE);
        } else {
            setMaxLines(lineVisual);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && !hasInit) {
            hasInit = true;
        }
    }
}
