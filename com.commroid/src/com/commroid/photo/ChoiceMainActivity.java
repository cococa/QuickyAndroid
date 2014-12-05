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
import android.content.Intent;
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

public class ChoiceMainActivity extends Activity implements OnItemClickListener{

	private Button btn1;
	private View rr;
	private GridView show_gridview;
	private ContentResolver contentResolver;
	private ImageLoader imageLoader=ImageLoader.getInstance();
	private PhotoClassifyAdapter photoClassifyAdapter;
	private Map<String, ArrayList<PhotoInfo>> map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choice);
		rr = findViewById(R.id.rr);
		contentResolver = getContentResolver();
		initViews();
		// new PopupWindows(ChoiceActivity.this, rr);
	}

	private void initViews() {
		map =getClassify();
		show_gridview = (GridView) findViewById(R.id.show_gridview);
		photoClassifyAdapter=new PhotoClassifyAdapter(this, map, imageLoader);
//		for(int i =0;i<map.size();i++){
//		String str[]=map.keySet().toArray(new String[]{});
//			Log.e(str[i], map.get(str[i]).size()+""+map.get(str[i]).get(0).getPath());
//			
//		}
		show_gridview.setAdapter(photoClassifyAdapter);
		show_gridview.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String str[]=map.keySet().toArray(new String[]{});
		Intent it = new Intent(this, ChoiceInfoActivity.class);
		it.putExtra("list", map.get(str[arg2]));
		startActivity(it);
	}
	

	private Map<String, ArrayList<PhotoInfo>> getClassify() {
		String columns[] = new String[] { Media._ID, Media.BUCKET_ID,
				Media.PICASA_ID, Media.DATA, Media.DISPLAY_NAME, Media.TITLE,
				Media.SIZE, Media.BUCKET_DISPLAY_NAME };
		Cursor cur = contentResolver.query(Media.EXTERNAL_CONTENT_URI, columns,
				null, null, null);
		Map<String, ArrayList<PhotoInfo>> map = new HashMap<String, ArrayList<PhotoInfo>>();
		while (cur.moveToNext()) {
			int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
			int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
			int photoNameIndex = cur.getColumnIndexOrThrow(Media.DISPLAY_NAME);
			int photoTitleIndex = cur.getColumnIndexOrThrow(Media.TITLE);
			int photoSizeIndex = cur.getColumnIndexOrThrow(Media.SIZE);
			int bucketDisplayNameIndex = cur
					.getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
			int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);
			int picasaIdIndex = cur.getColumnIndexOrThrow(Media.PICASA_ID);
			int totalNum = cur.getCount();
			String setName = cur.getString(bucketDisplayNameIndex);
			PhotoInfo info = new PhotoInfo();
			info.setSize(Long.parseLong(cur.getString(photoSizeIndex)));
			info.setName(cur.getString(photoNameIndex));
			info.setPath(cur.getString(photoPathIndex));
			info.setFile(setName);
			Iterator<String> it = map.keySet().iterator();
			boolean is = false;
			while (it.hasNext()) {
				if (setName.equals(it.next())) {
					is = true;
				}
			}
			if (is) {
				map.get(setName).add(info);
			} else {
				ArrayList<PhotoInfo> list = new ArrayList<PhotoInfo>();
				list.add(info);
				map.put(setName, list);
			}
			is = false;
		}
		cur.close();
		return map;
	}

	public class PopupWindows extends PopupWindow {
		public PopupWindows(Context mContext, View parent) {

			View view = View
					.inflate(mContext, R.layout.item_popupwindows, null);
			view.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.fade_ins));
			final LinearLayout ll_popup = (LinearLayout) view
					.findViewById(R.id.ll_popup);
			ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
					R.anim.push_bottom_in_2));

			setWidth(LayoutParams.MATCH_PARENT);
			setHeight(LayoutParams.MATCH_PARENT);
			setBackgroundDrawable(new BitmapDrawable());
			setFocusable(true);
			setOutsideTouchable(true);
			setContentView(view);
			showAtLocation(parent, Gravity.BOTTOM, 0, 0);
			update();

			Button bt1 = (Button) view
					.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view
					.findViewById(R.id.item_popupwindows_Photo);
			Button bt3 = (Button) view
					.findViewById(R.id.item_popupwindows_cancel);
			bt1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dismiss();
				}
			});
			bt2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dismiss();
				}
			});
			bt3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dismiss();
				}
			});

		}
	}

	

}
// String _id = cur.getString(photoIDIndex);
// String name = cur.getString(photoNameIndex);
// String path = cur.getString(photoPathIndex);
// String title = cur.getString(photoTitleIndex);
// String size = cur.getString(photoSizeIndex);
// String bucketName = cur.getString(bucketDisplayNameIndex);
// String bucketId = cur.getString(bucketIdIndex);
// String picasaId = cur.getString(picasaIdIndex);

