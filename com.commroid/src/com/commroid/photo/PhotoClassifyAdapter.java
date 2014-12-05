package com.commroid.photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.commroid.BaseImgLoadListener;
import com.commroid.R;
import com.commroid.photo.bean.PhotoInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * cocoaSJ
 */
public class PhotoClassifyAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater layoutInflater;
	private Map<String, ArrayList<PhotoInfo>> map;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;

	public PhotoClassifyAdapter(Context context,
			Map<String, ArrayList<PhotoInfo>> map, ImageLoader imageLoader) {
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
		this.map = map;
		this.imageLoader = imageLoader;
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_launcher) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.displayer(new SimpleBitmapDisplayer()) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象
	}

	@Override
	public int getCount() {
		Log.e("===size=====",map.size()+"");
		return map.size();
	}

	@Override
	public List<PhotoInfo> getItem(int arg0) {
		String str[] = map.keySet().toArray(new String[] {});
		return map.get(str[arg0]);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = layoutInflater.inflate(
					R.layout.item_image_classify, null);
			viewHolder = new ViewHolder();
			viewHolder.text = (TextView) convertView
					.findViewById(R.id.text_choice);
			viewHolder.img = (ImageView) convertView
					.findViewById(R.id.img_choice);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		
		PhotoInfo a = getItem(arg0).get(0);
		Log.e("===============",convertView.getWidth()+"");
		imageLoader.displayImage("file://"+a.getPath(), viewHolder.img, options, new BaseImgLoadListener());
		String str[] = map.keySet().toArray(new String[] {});
		viewHolder.text.setText(str[arg0]+"("+getItem(arg0).size()+")");
		return convertView;
	}
	
	
	class ViewHolder{
		ImageView  img;
		TextView text;
		
		
	}
	

}
