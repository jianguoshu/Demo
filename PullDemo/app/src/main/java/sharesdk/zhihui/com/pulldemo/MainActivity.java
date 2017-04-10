package sharesdk.zhihui.com.pulldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TranslationWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView header = (TextView) this.findViewById(R.id.header);
        webView = (TranslationWebView) this.findViewById(R.id.webview);

        webView.setTransYHeight(200);

        webView.setTranslationListener(new TranslationWebView.onTranslationChangeListener() {
            @Override
            public void onTranslationYChanged(float transY) {
                Log.i("douzi", "onTranslationYChanged("+transY+")");
                webView.setTranslationY(transY);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        webView.loadUrl("http://www.baidu.com");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
        return true;
    }
}
