package com.commroid.photo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;

import com.commroid.R;
import com.commroid.photo.bean.PhotoInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ChoiceInfoActivity extends Activity implements OnItemClickListener{

	
	private ImageLoader imageLoader=ImageLoader.getInstance();
	private PhotoInfoAdapter photoInfoAdapter;
	private GridView show_gridview;
	private List<PhotoInfo> list ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choiceinfo);
		list=(List<PhotoInfo>) getIntent().getSerializableExtra("list");
		initViews();
	}

	private void initViews() {
		show_gridview = (GridView) findViewById(R.id.show_gridview);
		photoInfoAdapter=new PhotoInfoAdapter(this, list, imageLoader);
		show_gridview.setAdapter(photoInfoAdapter);
		show_gridview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		list.get(arg2).setAlpha(true);
		photoInfoAdapter.notifyDataSetChanged();
	}

	

}

