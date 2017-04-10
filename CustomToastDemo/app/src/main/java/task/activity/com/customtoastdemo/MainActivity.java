package task.activity.com.customtoastdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private CustomToast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView) this.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient(){
        });
        webView.loadUrl("http://www.baidu.com");

        if (toast == null) {
            toast = new CustomToast();
        }
    }

    public void showToast(View view) {
        if (toast == null) {
            toast = new CustomToast();
        }
        toast.show(this, new CustomToast.onViewClickListener() {
            @Override
            public void onContentClick() {
                toast.hide();
            }

            @Override
            public void onOptionClick() {
                toast.hide();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }

            @Override
            public void onBackKeyDown() {
                toast.hide();
                finish();
            }
        });
    }
}
