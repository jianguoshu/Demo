package com.example.douzi.expendabletextview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    String textOneLine = "这是一个textview的Demo";
    String textLargeLine = "这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo这是一个textview的Demo";
    private ExpendableTextView textView;
    private View btnOneLine;
    private View btnMoreLine;
    private View btnExpend;
    private View btnShouqi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (ExpendableTextView) this.findViewById(R.id.tv_1);

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
                textView.setExpend(true);
            }
        });

        btnShouqi = this.findViewById(R.id.tv_btn_shouqi);
        btnShouqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setExpend(false);
            }
        });

        StaticLayout staticLayout = new StaticLayout(textView.getText(), 0, textView.getText().length(), textView.getPaint(), 1440,
                Layout.Alignment.ALIGN_NORMAL, 0, 0, true, TextUtils.TruncateAt.END, 0);
        Log.i(getClass().getSimpleName(), "staticLayout.getEllipsisCount(maxLines) : " + staticLayout.getEllipsisCount(3));
        Log.i(getClass().getSimpleName(), "staticLayout.getEllipsisStart(maxLines) : " + staticLayout.getEllipsisStart(3));
        Log.i(getClass().getSimpleName(), "staticLayout.getEllipsizedWidth() : " + staticLayout.getEllipsizedWidth());
        Log.i(getClass().getSimpleName(), "staticLayout.getLineEnd(maxLines) : " + staticLayout.getLineEnd(3));
        Log.i(getClass().getSimpleName(), "staticLayout.getLineVisibleEnd(maxLines) : " + staticLayout.getLineVisibleEnd(3));

        Log.i(getClass().getSimpleName(), "staticLayout.getEllipsisCount(2) : " + staticLayout.getEllipsisCount(2));
        Log.i(getClass().getSimpleName(), "staticLayout.getEllipsisStart(2) : " + staticLayout.getEllipsisStart(2));
        Log.i(getClass().getSimpleName(), "staticLayout.getEllipsizedWidth() : " + staticLayout.getEllipsizedWidth());
        Log.i(getClass().getSimpleName(), "staticLayout.getLineEnd(2) : " + staticLayout.getLineEnd(2));
        Log.i(getClass().getSimpleName(), "staticLayout.getLineVisibleEnd(2) : " + staticLayout.getLineVisibleEnd(2));


        View contentView = LayoutInflater.from(this).inflate(R.layout.new_text, null);
        ExpendableTextView textView = (ExpendableTextView) contentView.findViewById(R.id.new_textview);
        textView.setMaxLines(3);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setWidth(1440);
        String text = "我来试一下test我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下我来试一下";
        textView.setText(text);
        textView.setExpend(false);

        FrameLayout textContainer = (FrameLayout) this.findViewById(R.id.fl_text_container);
        textContainer.addView(contentView);


    }



}
