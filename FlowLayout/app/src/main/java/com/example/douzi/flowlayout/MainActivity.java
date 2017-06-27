package com.example.douzi.flowlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
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
        this.findViewById(R.id.data_change_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = sourceOne;
                adapter.notifyDataSetChanged();
            }
        });
        this.findViewById(R.id.data_change_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = sourceTwo;
                adapter.notifyDataSetChanged();
            }
        });
        this.findViewById(R.id.data_change_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = sourceThree;
                adapter.notifyDataSetChanged();
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
            public View getView(int position, View convertView) {
                TextView view = (TextView) convertView;
                if (view == null) {
                    view = new TextView(MainActivity.this);
                }
                view.setSingleLine();
                view.setEllipsize(TextUtils.TruncateAt.END);
                view.setText(data.get(position));
                FlowLayout.LayoutParams params = pagerFlowLayout.generateDefaultLayoutParams();
                params.setMargins(40, 40, 40, 40);
                view.setLayoutParams(params);
                switch (getItemViewType(position)) {
                    case 0:
                        view.setBackgroundColor(Color.parseColor("#009900"));
                        break;
                    case 1:
                        view.setBackgroundColor(Color.parseColor("#000099"));
                        break;
                    case 2:
                        view.setBackgroundColor(Color.parseColor("#006600"));
                        break;
                    case 3:
                        view.setBackgroundColor(Color.parseColor("#000066"));
                        break;
                }
                return view;
            }

            @Override
            public int getItemViewType(int position) {
                int type = position % 4;
                return type;
            }

            @Override
            public int getViewTypeCount() {
                return 4;
            }
        };
        data = sourceOne;
        pagerFlowLayout.setAdapter(adapter);
        pagerFlowLayout.nextPage();
    }

    private void initData() {
        sourceOne = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            sourceOne.add("1-" + i + "-测试以下呀哈");
        }
        sourceTwo = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            sourceTwo.add("2-" + i + "-测试以下呀哈");
        }
        sourceThree = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            sourceThree.add("3-" + i + "-测试以下呀哈");
        }
    }
}
