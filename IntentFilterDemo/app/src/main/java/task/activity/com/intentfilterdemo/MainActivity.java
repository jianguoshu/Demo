package task.activity.com.intentfilterdemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go(View view) {
        Intent intent = new Intent();
        intent.setAction("com.douzi.filter.test.bb");
        intent.addCategory("android.intent.category.DEFAULT");

        PackageManager manager = this.getPackageManager();
        if (manager.resolveActivity(intent, PackageManager.MATCH_ALL) != null) {
            startActivity(intent);
        } else {
            Log.i("douzi", "no activity");
        }
    }
}
