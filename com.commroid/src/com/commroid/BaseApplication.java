package com.commroid;
/**
 * sunshineHu
 */

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class BaseApplication extends Application{

	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initImageLoader(getApplicationContext());
	}

	
	/**
	 * 初始化imagerloader
	 * by sj
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)  //设置线程的优先级
				.denyCacheImageMultipleSizesInMemory()    //当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认会缓存多个不同的大小的相同图片
				.discCacheFileNameGenerator(new Md5FileNameGenerator())   //设置文件缓存的名字  Md5FileNameGenerator()：通过Md5将url生产文件的唯一名字
				.tasksProcessingOrder(QueueProcessingType.LIFO)   //设置图片下载和显示的工作队列排序 
				.writeDebugLogs() 
				// -----------------以下可以选择设置----------------------
				//.discCacheFileCount(100);    //设置缓存文件的数量
				
				.build();
		ImageLoader.getInstance().init(config);
	}


}
