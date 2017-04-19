package com.example.douzi.expendabletextview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
    }

}
