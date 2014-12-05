package com.commroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.commroid.photo.ChoiceMainActivity;
import com.commroid.webview.BasicWebviewActivity;

public class MainActivity extends Activity implements OnClickListener {

	private Button choice;
	private Button webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		choice=(Button) findViewById(R.id.choice);
		choice.setOnClickListener(this);
		webview=(Button) findViewById(R.id.webview);
		webview.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		Intent it = null;
		switch (v.getId()) {
		case R.id.webview:
			it= new Intent(this, BasicWebviewActivity.class);
			startActivity(it);
			break;
			
		case R.id.choice:
			it= new Intent(this, ChoiceMainActivity.class);
			startActivity(it);
			break;

		default:
			break;
		}

	}

}
