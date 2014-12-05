package com.commroid.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.widget.ListView;

import com.android.volley.VolleyError;
import com.commroid.BaseActivity;

public  class ListViewActivity extends  BaseActivity{

	private ListView listView;
	private ArrayList<String> list;
	
	@Override
	protected int setContentViewById() {
		return 0;
	}

	@Override
	protected void initViews() {
		
	}

	@Override
	protected void initData() {
		
	}

	@Override
	public void onResponseSuccessString(String response) {
		
	}

	@Override
	public void onResponseSuccessObject(JSONObject response) {
		
	}

	@Override
	public void onResponseError(VolleyError error) {
		
	}

	
	
	
	
	
}
