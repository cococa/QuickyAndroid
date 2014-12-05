package com.commroid.photo;

import java.util.List;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.commroid.BaseImgLoadListener;
import com.commroid.R;
import com.commroid.photo.bean.PhotoInfo;
import com.commroid.util.FileSizeUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * cocoaSJ
 */
public class PhotoInfoAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater layoutInflater;
	private List<PhotoInfo> list;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;

	public PhotoInfoAdapter(Context context, List<PhotoInfo> list,
			ImageLoader imageLoader) {
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
		this.list = list;
		this.imageLoader = imageLoader;
		options = new DisplayImageOptions.Builder()
		// .showStubImage(R.drawable.ic_launcher) // 设置图片下载期间显示的图片
		// .showImageForEmptyUri(R.drawable.ic_launcher) //
		// 设置图片Uri为空或是错误的时候显示的图片
		// .showImageOnFail(R.drawable.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.displayer(new SimpleBitmapDisplayer()) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public PhotoInfo getItem(int arg0) {

		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int arg0, View convertView, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = layoutInflater
					.inflate(R.layout.item_image_info, null);
			viewHolder = new ViewHolder();
			viewHolder.box = (CheckBox) convertView.findViewById(R.id.box_info);
			viewHolder.img = (ImageView) convertView
					.findViewById(R.id.img_info);
			viewHolder.text=(TextView) convertView.findViewById(R.id.text_info);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final PhotoInfo a = getItem(arg0);
		Log.e("===============", convertView.getWidth() + "");
		imageLoader.displayImage("file://" + a.getPath(), viewHolder.img,
				options, new BaseImgLoadListener());
		viewHolder.text.setText(a.getName()+"  "+FileSizeUtil.getMB(a.getSize()));
		viewHolder.box
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							getItem(arg0).setAlpha(true);
						} else {
							getItem(arg0).setAlpha(false);
						}
						notifyDataSetChanged();
					}
				});
		int brightness = 0;
		if (a.isAlpha()) {
			viewHolder.box.setChecked(true);
			brightness = -90;
		} else {
			viewHolder.box.setChecked(false);
			brightness = 0;
		}
		ColorMatrix matrix = new ColorMatrix();
		matrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
				brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
		viewHolder.img.setColorFilter(new ColorMatrixColorFilter(matrix));
		return convertView;
	}

	class ViewHolder {
		ImageView img;
		CheckBox box;
TextView text;
	}

}
