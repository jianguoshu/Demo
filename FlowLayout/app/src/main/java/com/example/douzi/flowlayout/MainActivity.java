package com.example.douzi.flowlayout;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<String> source = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            source.add(" " + i + "测试以下呀哈");
        }

        final PagerFlowLayout pagerFlowLayout = (PagerFlowLayout) this.findViewById(R.id.pager);
        pagerFlowLayout.setAdapter(new PagerFlowLayout.Adapter() {
            @Override
            public int getCount() {
                return source.size();
            }

            @Override
            public View getView(int position) {
                TextView view = new TextView(MainActivity.this);
                view.setText(source.get(position));
                return view;
            }

            @Override
            public int getItemViewType(int position) {
                return 1;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }
        });
//        pagerFlowLayout.nextPage();
        View btnNextPage = this.findViewById(R.id.tv_next_page);
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerFlowLayout.nextPage();
            }
        });
//
//        View autoNextPage = this.findViewById(R.id.auto_next_page);
//        autoNextPage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
    }

    private List<View> initViews() {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            TextView view = new TextView(this);
            view.setText(" " + i + " 测试仪下呀");
            view.setMaxLines(1);
            view.setEllipsize(TextUtils.TruncateAt.END);
            views.add(view);
        }
        return views;
    }
}
