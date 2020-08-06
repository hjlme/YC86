package com.yc.favorites.bean;

public class TagFavorites {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private String tid;
	
	private Integer fid;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@Override
	public String toString() {
		return "TagFavorites [tid=" + tid + ", fid=" + fid + "]";
	}
	
	
}
