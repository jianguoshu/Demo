package sharesdk.zhihui.com.seekbartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar mSeekBar;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBar = (SeekBar) this.findViewById(R.id.seekbar);
        mRatingBar = (RatingBar) this.findViewById(R.id.ratingbar);

        Button btn_jia = (Button) this.findViewById(R.id.btn_jia);
        Button btn_jian = (Button) this.findViewById(R.id.btn_jian);

        btn_jia.setOnClickListener(this);
        btn_jian.setOnClickListener(this);

        mSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mSeekBar.setProgress(currPro);
    }

    private int currPro = 0;
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jia:
                currPro += 10;
                mSeekBar.setProgress(currPro);
                break;
            case R.id.btn_jian:
                currPro -= 10;
                mSeekBar.setProgress(currPro);
                break;
        }
    }
}
