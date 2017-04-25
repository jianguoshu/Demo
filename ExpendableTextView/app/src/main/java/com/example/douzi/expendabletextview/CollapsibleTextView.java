package com.example.douzi.expendabletextview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class CollapsibleTextView extends TextView implements View.OnLayoutChangeListener {

    public static final int LINES_INVALID = -1;
    public static final int COLOR_INVALID = -1;
    int maxLines = LINES_INVALID; // 保存最大行数
    int totalLines = LINES_INVALID; // 总行数，每次设置text时计算更新此变量
    boolean isCollapsed; // 记录展开收起状态
    private int width;
    private CharSequence mText;

    private String expendText = "全文";
    private String ellipsisSymbol = "...";
    private String collapseText = "收起";
    private boolean collapsibleByHand = false; // 是否显示收起功能
    private int collapseSwitcherColor = COLOR_INVALID; // 展开、收起文字的颜色
    private int collapseSwitcherColorId = COLOR_INVALID; // 展开、收起文字的颜色

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
            String nameSpace = "http://schemas.android.com/apk/res-auto";
            maxLines = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", LINES_INVALID);
            String expendText = attrs.getAttributeValue(nameSpace, "expend_text");
            String collapseText = attrs.getAttributeValue(nameSpace, "collapse_text");
            String ellipsisSymbol = attrs.getAttributeValue(nameSpace, "ellipsis_symbol");
            if (!TextUtils.isEmpty(expendText)) {
                this.expendText = expendText;
            }
            if (!TextUtils.isEmpty(collapseText)) {
                this.collapseText = collapseText;
            }
            if (!TextUtils.isEmpty(ellipsisSymbol)) {
                this.ellipsisSymbol = ellipsisSymbol;
            }

            collapsibleByHand = attrs.getAttributeBooleanValue(nameSpace, "collapsible_by_hand", false);
            collapseSwitcherColor = attrs.getAttributeIntValue(nameSpace, "collapse_switcher_color", COLOR_INVALID);
            if (collapseSwitcherColor == COLOR_INVALID) {
                collapseSwitcherColorId = attrs.getAttributeResourceValue(nameSpace, "collapse_switcher_color_id", COLOR_INVALID);
            }
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
        SpannableStringBuilder textNew = processText(text);
        super.setText(textNew, type);
    }

    private SpannableStringBuilder processText(CharSequence text) {
        mText = text;
        SpannableStringBuilder resultBuilder = new SpannableStringBuilder();
        if (width > 0 && mText != null) {
            StaticLayout staticLayout = new StaticLayout(text, 0, text.length(), getPaint(), width,
                    Layout.Alignment.ALIGN_NORMAL, 0, 0, false, TextUtils.TruncateAt.END, 0);
            totalLines = staticLayout.getLineCount();
            if (collapsible()) {
                int start;
                int end;
                CharSequence strLine;
                Paint paint = getPaint();
                if (isCollapsed) {
                    for (int line = 0; line < maxLines; line++) {
                        start = staticLayout.getLineStart(line);
                        end = staticLayout.getLineVisibleEnd(line);
                        strLine = text.subSequence(start, end);
                        if (line < maxLines - 1) { // 每一行结尾加换行符防止行错乱
                            resultBuilder.append(strLine).append("\n");
                        } else { // 最后一行
                            float widthExtra = paint.measureText(expendText + ellipsisSymbol);
                            int indexCharLast = strLine.length() - 1;
                            while (widthExtra > 0 && indexCharLast >= 0) {
                                float widthCharLast = paint.measureText(String.valueOf(strLine.subSequence(indexCharLast, indexCharLast + 1)));
                                widthExtra -= widthCharLast;
                                indexCharLast--;
                            }
                            resultBuilder.append(strLine.subSequence(0, indexCharLast + 1));
                            resultBuilder.append(ellipsisSymbol).append(expendText);
                            ClickableSpan clickableSpan = getClickableSpan();
                            resultBuilder.setSpan(clickableSpan, resultBuilder.length() - expendText.length(), resultBuilder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                            setHighlightColor(Color.TRANSPARENT);
                            setMovementMethod(LinkMovementMethod.getInstance());
                        }
                    }
                } else if (collapsibleByHand) {
                    for (int line = 0; line < totalLines; line++) {
                        start = staticLayout.getLineStart(line);
                        end = staticLayout.getLineVisibleEnd(line);
                        strLine = text.subSequence(start, end);
                        if (line < totalLines - 1) { // 每一行结尾加换行符防止行错乱
                            resultBuilder.append(strLine).append("\n");
                        } else { // 最后一行
                            resultBuilder.append(strLine);
                            float desiredWidth = staticLayout.getDesiredWidth(strLine, getPaint());
                            float widthExtra = paint.measureText(collapseText);
                            if (widthExtra + desiredWidth > width) {
                                resultBuilder.append("\n");
                            }
                            resultBuilder.append(collapseText);
                            ClickableSpan clickableSpan = getClickableSpan();
                            resultBuilder.setSpan(clickableSpan, resultBuilder.length() - collapseText.length(), resultBuilder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                            setHighlightColor(Color.TRANSPARENT);
                            setMovementMethod(LinkMovementMethod.getInstance());
                        }
                    }
                }
            }
        }
        if (resultBuilder.length() == 0 && text != null) {
            resultBuilder.append(text);
        }
        return resultBuilder;
    }

    @NonNull
    private ClickableSpan getClickableSpan() {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                collapse(!isCollapsed);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                if (collapseSwitcherColor != COLOR_INVALID) {
                    ds.setColor(collapseSwitcherColor);
                } else if (collapseSwitcherColorId != COLOR_INVALID) {
                    ds.setColor(getResources().getColor(collapseSwitcherColorId));
                }
                ds.setUnderlineText(false);
            }
        };
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

    public void setExpendText(String expendText) {
        if (!TextUtils.isEmpty(expendText)) {
            this.expendText = expendText;
        }
    }

    public void setEllipsisSymbol(String ellipsisSymbol) {
        if (!TextUtils.isEmpty(expendText)) {
            this.ellipsisSymbol = ellipsisSymbol;
        }
    }

    public void setCollapseText(String collapseText) {
        if (!TextUtils.isEmpty(expendText)) {
            this.collapseText = collapseText;
        }
    }

    public void setCollapsibleByHand(boolean collapsibleByHand) {
        this.collapsibleByHand = collapsibleByHand;
    }

    public void setCollapseSwitcherColor(int collapseSwitcherColor) {
        this.collapseSwitcherColor = collapseSwitcherColor;
    }

    public void setCollapseSwitcherColorId(int collapseSwitcherColorId) {
        this.collapseSwitcherColorId = collapseSwitcherColorId;
    }
}
