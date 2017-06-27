package com.example.douzi.flowlayout;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private PagerFlowLayout.Adapter adapter;
    private List<String> sourceOne;
    private List<String> sourceTwo;
    private List<String> sourceThree;
    private PagerFlowLayout pagerFlowLayout;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initViews();
    }

    private void initViews() {
        initFlowLayout();
        this.findViewById(R.id.adapter_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = sourceOne;
                pagerFlowLayout.setAdapter(adapter);
            }
        });
        this.findViewById(R.id.adapter_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = sourceTwo;
                pagerFlowLayout.setAdapter(adapter);
            }
        });
        this.findViewById(R.id.adapter_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = sourceThree;
                pagerFlowLayout.setAdapter(adapter);
            }
        });

        View btnNextPage = this.findViewById(R.id.tv_next_page);
        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagerFlowLayout.nextPage();
            }
        });
    }

    private void initFlowLayout() {
        pagerFlowLayout = (PagerFlowLayout) this.findViewById(R.id.pager);
        adapter = new PagerFlowLayout.Adapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public View getView(int position) {
                TextView view = new TextView(MainActivity.this);
                view.setSingleLine();
                view.setText(data.get(position));
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
        };
        data = sourceOne;
        pagerFlowLayout.setAdapter(adapter);
        pagerFlowLayout.nextPage();
    }

    private void initData() {
        sourceOne = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            sourceOne.add(" " + i + "one-测试以下呀哈");
        }
        sourceTwo = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            sourceTwo.add(" " + i + "two-测试以下呀哈");
        }
        sourceThree = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            sourceThree.add(" " + i + "three-测试以下呀哈");
        }
    }
}
