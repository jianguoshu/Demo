package task.activity.com.savestatedemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("douzi", "SecondActivity onSaveInstanceStateonSaveInstanceState");
    }

    @Override
    protected void onResume() {
        Log.i("douzi", "SecondActivity onResume");
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("douzi", "SecondActivity onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("douzi", "SecondActivity onStop");
    }


}
