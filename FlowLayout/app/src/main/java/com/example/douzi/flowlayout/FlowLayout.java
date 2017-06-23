package com.example.douzi.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douzi on 2017/6/22.
 */

public class FlowLayout extends ViewGroup{
    public static final String TAG = "FlowLayout2";
    public static final int HORIZONTAL_GRAVITY_MASK = 0x000F;
    public static final int LEFT = 0x0001;
    public static final int RIGHT = LEFT << 1;
    public static final int CENTER_HORIZONTAL = LEFT << 2;

    public static final int VERTICAL_GRAVITY_MASK = 0x00F0;
    public static final int TOP = 0x0010;
    public static final int BOTTOM = TOP << 1;
    public static final int CENTER_VERTICAL = TOP << 2;

    public static final int CENTER = CENTER_HORIZONTAL | CENTER_VERTICAL;

    private int gravity = LEFT | CENTER_VERTICAL;
    private boolean childFullVisual; // 标示child是否完整显示或者可以压缩宽度
    private float maxBlankWidth; // 标示每行末尾可显示空白的最大尺寸
    private int maxLine; // 限制显示行数
//    private int visualViewNum = 0; // 记录可见的view个数

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        childFullVisual = ta.getBoolean(R.styleable.FlowLayout_childFullVisual, true);
        maxBlankWidth = ta.getDimension(R.styleable.FlowLayout_maxBlankWidth, 0);
        gravity = ta.getInt(R.styleable.FlowLayout_gravity, LEFT | CENTER_VERTICAL);
        maxLine = ta.getInt(R.styleable.FlowLayout_maxLine, Integer.MAX_VALUE);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int lineCur = 1; // 记录当前计算到的line num

        boolean isLintStart = true;

        int count = getChildCount();

        int maxWidth = View.MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();

        int widthUsedInLine = 0; // 一行中已占用的width
        int maxWidthAllLine = 0; // 所有行的最大宽度

        int heightUsed = 0; // 子view占用的height
        int maxHeightInLine = 0; // 一行中所有child的max height

        for (int i = 0; i < count; i++) {
            if (outLines(lineCur)) {// 超出最大行数限制，不再measure
                break;
            }
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (isLintStart) { // 新的一行开始处
                if (maxWidth - childWidth <= 0) { // 一个child占满一行--new line
                    maxWidthAllLine = Math.max(maxWidthAllLine, childWidth);
                    heightUsed += childHeight;

                    lineCur++;
                    isLintStart = true;
                    widthUsedInLine = 0;
                    maxHeightInLine = 0;
                } else { // 一行中第一个child
                    widthUsedInLine += childWidth;
                    maxWidthAllLine = Math.max(maxWidthAllLine, widthUsedInLine);
                    maxHeightInLine = Math.max(maxHeightInLine, childHeight);
                    isLintStart = false;
                }
                continue;
            } else {// 一行中已有child
                if (maxWidth - widthUsedInLine - childWidth == 0) { // child占满了剩余的空间--new line
                    maxWidthAllLine = Math.max(maxWidthAllLine, maxWidth);
                    maxHeightInLine = Math.max(maxHeightInLine, childHeight);
                    heightUsed += maxHeightInLine;

                    lineCur++;
                    isLintStart = true;
                    widthUsedInLine = 0;
                    maxHeightInLine = 0;
                    continue;
                }

                if (maxWidth - widthUsedInLine - childWidth > 0) { // child未占满剩余空间，continue
                    widthUsedInLine += childWidth;
                    maxWidthAllLine = Math.max(maxWidthAllLine, widthUsedInLine);
                    maxHeightInLine = Math.max(maxHeightInLine, childHeight);
                    isLintStart = false;
                    continue;
                }

                if (maxWidth - widthUsedInLine - childWidth < 0) { //  剩余空间不能完整显示child
                    if (needNewLine(maxWidth, widthUsedInLine, childWidth)) { // 需要新开一行--new line
                        maxWidthAllLine = Math.max(maxWidthAllLine, widthUsedInLine);
                        heightUsed += maxHeightInLine;

                        lineCur++;
                        if (outLines(lineCur)) { // 新的一行超出最大行数限制，结束measure；并清除下一行中view造成的影响
                            widthUsedInLine = 0;
                            maxHeightInLine = 0;
                            break;
                        } else {
                            widthUsedInLine = childWidth;
                            maxHeightInLine = childHeight;
                            isLintStart = false;
                        }
                    } else { // 压缩child填充剩余空间
                        measureChildWithMargins(child, widthMeasureSpec, widthUsedInLine, heightMeasureSpec, heightUsed);
                        lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
                        childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                        childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                        widthUsedInLine += childWidth;
                        maxWidthAllLine = Math.max(maxWidthAllLine, widthUsedInLine);
                        maxHeightInLine = Math.max(maxHeightInLine, childHeight);
                        heightUsed += maxHeightInLine;

                        widthUsedInLine = 0;
                        maxHeightInLine = 0;
                        lineCur++;
                        isLintStart = true;
                    }
                    continue;
                }
            }
        }

        heightUsed += maxHeightInLine;

        int width = resolveSizeAndState(maxWidthAllLine + getPaddingLeft() + getPaddingRight(), widthMeasureSpec);
        int height = resolveSizeAndState(heightUsed + getPaddingTop() + getPaddingBottom(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    /**
     * 先分行，计算时child的大小包含margin
     * 排除了自身padding的影响
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCur = 1;

        int width = r - l - getPaddingLeft() - getPaddingRight(); // 处理padding
        int count = getChildCount();

        int widthUsed = 0;
        int heightUsed = getPaddingTop(); // 处理padding
        int maxHeight = 0;
        List<View> lineViews = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (outLines(lineCur)) { // 超出最大行数限制，停止layout
                return;
            }
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (!needNewLine(width, widthUsed, childWidth)) {
                widthUsed += childWidth;
                maxHeight = Math.max(maxHeight, childHeight);
                lineViews.add(child);
            } else {
                layoutLine(lineViews, width, widthUsed, heightUsed, heightUsed + maxHeight);
                lineCur++;
                heightUsed += maxHeight;

                lineViews.clear();
                widthUsed = childWidth;
                maxHeight = childHeight;
                lineViews.add(child);
            }
        }

        if (lineViews.size() > 0) {
            layoutLine(lineViews, width, widthUsed, heightUsed, heightUsed + maxHeight);
            lineCur++;
        }
    }

    private boolean outLines(int lineCur) {
        return lineCur > maxLine;
    }

    /**
     * layout每一行, 计算时child的大小包含margin
     * @param views
     * @param widthTotal 可用宽度，即不包含padding区域
     * @param widthUsed 一行中所有view需要的宽度之和
     * @param yStart
     * @param yEnd
     */
    private void layoutLine(List<View> views, int widthTotal, int widthUsed, int yStart, int yEnd) {
        int size = views.size();
        int widthValid = widthTotal - widthUsed;

        int gravityHorizontal = this.gravity & HORIZONTAL_GRAVITY_MASK;
        int xStart = 0;
        for (int i = 0; i < size; i++) {
            View child = views.get(i);
            final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (i == 0) {
                switch (gravityHorizontal) {
                    case LEFT:
                        xStart = getPaddingLeft();
                        break;
                    case CENTER_HORIZONTAL:
                        xStart = (int) (widthValid / 2.0f) + getPaddingLeft();
                        break;
                    case RIGHT:
                        xStart = widthValid + getPaddingLeft();
                        break;
                    default:
                        xStart = getPaddingLeft();
                        break;
                }

            }
            layoutView(child, xStart, childWidth, childHeight, yStart, yEnd);
            xStart += childWidth;
        }
    }

    /**
     * 参数的值受margin的影响，需要在此方法中排除margin的影响
     * @param view
     * @param xStart
     * @param width
     * @param height
     * @param yStart
     * @param yEnd
     */
    private void layoutView(View view, int xStart, int width, int height, int yStart, int yEnd) {
        int gravityVertical = this.gravity & VERTICAL_GRAVITY_MASK;

        int l = xStart;
        int r = xStart + width;
        int t;
        int b;

        switch (gravityVertical) {
            case TOP:
                t = yStart;
                b = yStart + height;
                break;
            case CENTER_VERTICAL:
                t = (int) ((yEnd + yStart - height) / 2.0f);
                b = (int) ((yEnd + yStart + height) / 2.0f);
                break;
            case BOTTOM:
                t = yEnd - height;
                b = yEnd;
                break;
            default:
                t = (int) ((yEnd + yStart - height) / 2.0f);
                b = (int) ((yEnd + yStart + height) / 2.0f);
                break;
        }

        final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

        // 去除margin
        l += lp.leftMargin;
        t += lp.topMargin;
        r -= lp.rightMargin;
        b -= lp.bottomMargin;

        view.layout(l, t, r, b);
    }

    /**
     * @param total 一行的全部空间
     * @param used  已用的空间
     * @param require 新需要的空间
     * @return
     */
    private boolean needNewLine(int total, int used, int require) {
        boolean isBlankEnough = total - used - require > 0;
        if (isBlankEnough) {
            return false;
        } else {
            if (childFullVisual) {
                return true;
            } else {
                return total - used <= maxBlankWidth;
            }
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new ViewGroup.MarginLayoutParams(p);
    }

    public static int resolveSizeAndState(int size, int measureSpec) {
        int result = size;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize =  View.MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case View.MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case View.MeasureSpec.AT_MOST:
                if (specSize < size) {
                    result = specSize | MEASURED_STATE_TOO_SMALL;
                } else {
                    result = size;
                }
                break;
            case View.MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

}
