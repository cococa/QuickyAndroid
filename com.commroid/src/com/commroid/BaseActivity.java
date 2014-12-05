package com.commroid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.commroid.volleyE.JsonObjectPostRequest;

/**
 * 
 * @author shenjun (385811416@qq.com)
 * 
 */
public abstract class BaseActivity extends Activity {

	/*** 分页加载的size数 ***/
	public static final int SIZE = 10;

	/*** 获取tag name，打印日志，调试时使用 ***/
	public String Tag = getClass().getSimpleName();
	
	protected Context context;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题栏无
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(setContentViewById());
		initViews();
		initData();
		context= this;
	}

	/**
	 * 设置视图的资源id （example:R.layout.xxx）
	 * 
	 * @return 资源id
	 */
	protected abstract int setContentViewById();

	/**
	 * 初始化资源
	 */
	protected abstract void initViews();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 显示进度加载框
	 */
	public void show() {
		Toast.makeText(context, "show()",1).show();
	}

	/**
	 * 关闭进度加载框
	 */
	public void dissMiss() {
		Toast.makeText(context, "dissMiss()",1).show();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 关闭进度加载框
		dissMiss();
	}

	/**
	 * 1"yyyy年MM月dd日 HH时mm分ss秒" 2"yy/MM/dd HH:mm" 3"yyyy-MM-dd HH:mm:ss"
	 * 4"yyyy年MM月dd日 HH时mm分ss秒
	 * 
	 * @return
	 */
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return sdf.format(new Date());
	}
	
	
	/**
	 * get qingqiu 
	 * @param url
	 * @param map
	 * @method
	 */
	public void getJSONByVolleyGET(String url,Map<String,String> map,Response.Listener<JSONObject> l1,Response.ErrorListener l2) { 
		RequestQueue requestQueue = Volley.newRequestQueue(this); 
        show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( 
        		Request.Method.GET,  
                url,  
                null, 
                l1,  
                l2); 
        requestQueue.add(jsonObjectRequest); 
    } 
	
	/**
	 * post qingqiu 
	 * @param url
	 * @param map
	 * @method
	 */
	
	public void getJSONByVolleyPOST(String url,Map<String,String> map) { 
		RequestQueue requestQueue = Volley.newRequestQueue(this); 
        
        JsonObjectPostRequest jsonObjectRequest = new JsonObjectPostRequest( 
                url,  
                new Response.Listener<JSONObject>() { 
                    @Override 
                    public void onResponse(JSONObject response) { 
                       dissMiss();
                       onResponseSuccessString(response.toString());
                       onResponseSuccessObject(response);
                    }

                },  
                new Response.ErrorListener() { 
                    @Override 
                    public void onErrorResponse(VolleyError error) { 
                    	dissMiss();
                    	onResponseError(error);
                    } 
                },map); 
        requestQueue.add(jsonObjectRequest); 
    } 
	
	
	
	/**
	 * getJSONByVolley 的回调方法，正常返回，返回String 
	 * @param response
	 */
	public abstract void onResponseSuccessString(String response);
	
	/**
	 * getJSONByVolley 的回调方法，正常返回，返回JSONObject 
	 * @param response
	 */
	public abstract void onResponseSuccessObject(JSONObject response);
	
	/**
	 * getJSONByVolley 的回调方法，错误返回
	 * @param response
	 */
	public abstract void onResponseError(VolleyError error);
	
	
	
	

}
