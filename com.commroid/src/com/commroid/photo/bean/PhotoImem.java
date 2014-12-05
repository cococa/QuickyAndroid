package com.commroid.photo.bean;

import java.util.List;

/**
 *cocoaSJ
 */
public class PhotoImem {

	private String name;
	private int count;
	private List<PhotoInfo>  photoList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<PhotoInfo> getPhotoList() {
		return photoList;
	}
	public void setPhotoList(List<PhotoInfo> photoList) {
		this.photoList = photoList;
	}
	
	
	
}
