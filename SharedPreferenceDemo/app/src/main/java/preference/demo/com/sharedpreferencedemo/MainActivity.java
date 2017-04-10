package preference.demo.com.sharedpreferencedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 写入sharepreference
     * @param view
     */
    public void onWriteClick(View view){
        SpUtil.put(this,"按钮A事件写入");
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

    public void onStartClick(View view){
        Intent intent = new Intent(this,SharePrefTestBActivity.class);
        startActivity(intent);
    }
}
