package com.example.douzi.expendabletextview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by douzi on 2017/4/19.

     textView.setMaxLines(3);
     textView.setEllipsize(TextUtils.TruncateAt.END);
     textView.setWidth(1440);
     textView.setText("我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下");
     if (textView.expendable()) {
         textView.setExpend(true);
     }

 */

public class ExpendableTextView extends TextView {

    public static final int LINES_INVALID = -1;
    int maxLines = LINES_INVALID; // 保存最大行数
    int totalLines = LINES_INVALID; // 总行数，每次设置text时计算更新此变量
    boolean isExpend; // 记录展开收起状态
    private int width;

    public ExpendableTextView(Context context) {
        super(context);
        init(null);
    }

    public ExpendableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ExpendableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        if (attrs != null) {
            maxLines = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", -1);
        } else {
            maxLines = LINES_INVALID;
        }
        isExpend = maxLines == LINES_INVALID;
    }

    @Override
    public void setMaxLines(int maxLines) {
        super.setMaxLines(maxLines);
        this.maxLines = maxLines;
        isExpend = maxLines == LINES_INVALID;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        processTotalLines(text);
        super.setText(text, type);
    }

    private void processTotalLines(CharSequence text) {
        int width = getWidth();
        if (width <= 0) {
            width = this.width;
        }
        if (width > 0) {
            StaticLayout staticLayout = new StaticLayout(text, 0, text.length(), getPaint(), width,
                    Layout.Alignment.ALIGN_NORMAL, 0, 0, false, TextUtils.TruncateAt.END, 0);
            totalLines = staticLayout.getLineCount();
       }
    }

    /**
     * 具有了width和text时，返回结果才是正确的
     * @return
     */
    public boolean expendable() {
        return maxLines != LINES_INVALID && totalLines > maxLines;
    }


    public boolean isExpend() {
        return isExpend;
    }

    public void setWidth(int width) {
        this.width = width;
        super.setWidth(width);
    }

    public void setExpend(boolean expend) {
        if (isExpend != expend) {
            setExpendForce(expend);
        }
    }

    private void setExpendForce(boolean expend) {
        isExpend = expend;
        if (isExpend) {
            super.setMaxLines(Integer.MAX_VALUE);
        } else {
            super.setMaxLines(maxLines);
        }
    }
}
