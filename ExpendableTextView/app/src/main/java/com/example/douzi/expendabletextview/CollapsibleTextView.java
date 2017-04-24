package com.example.douzi.expendabletextview;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class CollapsibleTextView extends TextView implements View.OnLayoutChangeListener {

    public static final int LINES_INVALID = -1;
    int maxLines = LINES_INVALID; // 保存最大行数
    int totalLines = LINES_INVALID; // 总行数，每次设置text时计算更新此变量
    boolean isCollapsed; // 记录展开收起状态
    private int width;
    private String mText;

    private String ellipsisText = "...全文";

    public CollapsibleTextView(Context context) {
        super(context);
        init(null);
    }

    public CollapsibleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CollapsibleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        maxLines = LINES_INVALID;
        if (attrs != null) {
            maxLines = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", LINES_INVALID);
        }
        isCollapsed = maxLines != LINES_INVALID;
    }

    @Override
    public void setMaxLines(int maxLines) {
        super.setMaxLines(maxLines);
        this.maxLines = maxLines;
        isCollapsed = maxLines != LINES_INVALID;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        String textNew = processText(text);
        super.setText(textNew, type);
    }

    private String processText(CharSequence text) {
        mText = text.toString();
        String resultText = mText;
        if (width > 0 && mText != null) {
            StaticLayout staticLayout = new StaticLayout(text, 0, text.length(), getPaint(), width,
                    Layout.Alignment.ALIGN_NORMAL, 0, 0, false, TextUtils.TruncateAt.END, 0);
            totalLines = staticLayout.getLineCount();
            if (isCollapsed && collapsible()) {
                StringBuilder result = new StringBuilder();
                int start;
                int end;
                String strLine;
                for (int line = 0; line < maxLines; line++) {
                    start = staticLayout.getLineStart(line);
                    end = staticLayout.getLineVisibleEnd(line);
                    strLine = text.subSequence(start, end).toString();
                    if (line < maxLines - 1) { // 每一行结尾加换行符防止行错乱
                        result.append(strLine).append("\n");
                    } else { // 最后一行
                        Paint paint = getPaint();
                        float widthExtra = paint.measureText(ellipsisText);
                        int indexCharLast = strLine.length() - 1;
                        while (widthExtra > 0 && indexCharLast >= 0) {
                            float widthCharLast = paint.measureText(String.valueOf(strLine.charAt(indexCharLast)));
                            widthExtra -= widthCharLast;
                            indexCharLast--;
                        }
                        result.append(strLine.substring(0, indexCharLast + 1));
                        result.append(ellipsisText);
                    }
                }
                resultText = result.toString();
            }
        }
        return resultText;
    }

    public boolean collapsible() {
        return width > 0 && maxLines != LINES_INVALID && totalLines > maxLines;
    }


    public boolean isCollapsed() {
        return isCollapsed;
    }

    public void setWidth(int width) {
        this.width = width;
        super.setWidth(width);
    }

    public void collapse(boolean collapse) {
        if (isCollapsed != collapse) {
            collapseForce(collapse);
        }
    }

    private void collapseForce(boolean collapse) {
        isCollapsed = collapse;
        if (isCollapsed) {
            super.setMaxLines(maxLines);
        } else {
            super.setMaxLines(Integer.MAX_VALUE);
        }
        setText(mText);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOnLayoutChangeListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeOnLayoutChangeListener(this);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        int widthNew = getWidth();
        if (widthNew != width) {
            width = widthNew;
            collapseForce(isCollapsed);
        }
    }
}
