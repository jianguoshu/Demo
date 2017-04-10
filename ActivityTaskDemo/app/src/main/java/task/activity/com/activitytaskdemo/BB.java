package task.activity.com.activitytaskdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Log;
import android.view.View;

public class BB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("activity-task", "onCreate()");
        setContentView(R.layout.activity_bb);
    }
    public void go(View view) {
        Intent intent = new Intent(this, CC.class);
        this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("activity-task", "onDestroy()");
    }
}
