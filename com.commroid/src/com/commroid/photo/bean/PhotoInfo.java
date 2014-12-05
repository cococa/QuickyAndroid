package com.commroid.photo.bean;

import java.io.Serializable;

/**
 *cocoaSJ
 */
public class PhotoInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7312476139557285576L;
	private String name;
	private String path;
	private String type;
	private Long size;
	private String file;
	private boolean alpha;
	
	
	

	public boolean isAlpha() {
		return alpha;
	}
	public void setAlpha(boolean alpha) {
		this.alpha = alpha;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	
	
}
