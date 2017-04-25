package com.example.douzi.expendabletextview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    String textOneLine = "这是一个textview的Demo";
    String textLargeLine = "这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo";
    private CollapsibleTextView textView;
    private View btnOneLine;
    private View btnMoreLine;
    private View btnExpend;
    private View btnShouqi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (CollapsibleTextView) this.findViewById(R.id.tv_1);

        View contentView = LayoutInflater.from(this).inflate(R.layout.new_text, null);
        final CollapsibleTextView textView2 = (CollapsibleTextView) contentView.findViewById(R.id.new_textview);
        textView2.setMaxLines(3);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        String text = "我来试一下test我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下";
        text = "gagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagagaggagagagaga";
        SpannableString builder = new SpannableString(text);
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
        builder.setSpan(span, 0, 10, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        textView2.setText(builder);
        textView2.collapse(true);
        textView2.setCollapsibleByHand(true);

        FrameLayout textContainer = (FrameLayout) this.findViewById(R.id.fl_text_container);
        textContainer.addView(contentView);

        btnOneLine = this.findViewById(R.id.tv_btn_line_one);
        btnOneLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textOneLine);
            }
        });

        btnMoreLine = this.findViewById(R.id.tv_btn_line_more);
        btnMoreLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textLargeLine);
            }
        });

        btnExpend = this.findViewById(R.id.tv_btn_expend);
        btnExpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.collapse(false);
                textView2.collapse(false);
            }
        });

        btnShouqi = this.findViewById(R.id.tv_btn_shouqi);
        btnShouqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.collapse(true);
                textView2.collapse(true);
            }
        });


    }


}
