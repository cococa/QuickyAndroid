package com.commroid.webview;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.commroid.R;

/**
 * @author cocoaSJ ͨ�õ�webview version-1.0��ǰ�������ˣ�ˢ�£�
 * 
 */

public class BasicWebviewActivity extends Activity implements OnClickListener {

	protected WebView webview;
	private ProgressBar webview_progressBar;
	protected String URLString = "http://www.baidu.com";
	private ImageView webview_back_img;
	private ImageView webview_refresh_img;
	private ImageView webview_next_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_webview);
		initView();
		webview.loadUrl(URLString);
	}
	
	
	
	
	

	private void initView() {
		webview_progressBar = (ProgressBar) findViewById(R.id.webview_progressbar);
		this.webview = (WebView) findViewById(R.id.test_webview);
		webview_back_img = (ImageView) findViewById(R.id.webview_back_imageview);
		webview_back_img.setOnClickListener(this);
		webview_refresh_img = (ImageView) findViewById(R.id.webview_refresh_imageview);
		webview_refresh_img.setOnClickListener(this);
		webview_next_img = (ImageView) findViewById(R.id.webview_next_imageview);
		webview_next_img.setOnClickListener(this);
		// ���ÿ����Զ�����ͼƬ
		webview.getSettings().setLoadsImagesAutomatically(true);
		webview.getSettings().setBuiltInZoomControls(true);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setUseWideViewPort(true);
		webview.getSettings().setLoadWithOverviewMode(true);
		webview.getSettings().setDefaultTextEncodingName("utf-8");
		if (Build.VERSION.SDK_INT >= 8) {
			webview.getSettings().setPluginState(PluginState.ON);
		} else {
			// webview.getSettings().setPluginsEnabled(true);
		}
		webview.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				super.onProgressChanged(view, newProgress);
				webview_progressBar.setVisibility(View.VISIBLE);
				webview_progressBar.setProgress(0);
				webview_progressBar.setProgress(newProgress);
				webview_refresh_img.setEnabled(false);  //����ˢ�°�ť��ˢ����ɺ���ָ�
				if (newProgress == 100) {
					// webview������ɺ�
					webview_progressBar.setVisibility(View.GONE);
					webview_refresh_img.setEnabled(true);
					// webview ����ɷ��أ����ذ�ť���óɿ���
					if (webview.canGoBack()) {
						webview_back_img.setEnabled(true);
					} else {
						webview_back_img.setEnabled(false);
					}
					if (webview.canGoForward()) {
						webview_next_img.setEnabled(true);
					} else {
						webview_next_img.setEnabled(false);
					}

				}
			}

		});
		webview.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// Toast.makeText(WebviewActivity.this, "Oh no! " + description,
				// Toast.LENGTH_SHORT).show();
			}
		});

	}

	@Override
	public void onClick(View v) {
		if (null == webview) {
			return;
		}
		switch (v.getId()) {
		case R.id.webview_back_imageview:
			if (webview.canGoBack()) {
				webview.goBack();
			}
			break;
		case R.id.webview_refresh_imageview:
			webview.reload();
			break;
		case R.id.webview_next_imageview:
			webview.goForward();
			break;
		default:
			break;
		}

	}

}
