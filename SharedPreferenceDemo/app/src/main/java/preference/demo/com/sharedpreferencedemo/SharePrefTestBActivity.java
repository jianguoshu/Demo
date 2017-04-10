package preference.demo.com.sharedpreferencedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class SharePrefTestBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_pref_test_b);
    }
    /**
     * 写入sharepreference
     * @param view
     */
    public void onWriteClick(View view){
        SpUtil.put(this,"按钮B事件写入");
    }
    /**
     * 读入sharepreference，并显示
     * @param view
     */
    public void onReadClick(View view){
        String result =  SpUtil.get(this);
        result = TextUtils.isEmpty(result)?"null":result;
        TextView tv = (TextView) findViewById(R.id.tv_read);
        tv.setText(result);
    }
}
