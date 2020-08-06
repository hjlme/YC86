package com.yc.favorites.bean;

public class Tag {
	
	/**
	 * 序列化版本编号
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private String tid;
	
	private String tname;
	
	private Integer tcount;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Integer getTcount() {
		return tcount;
	}

	public void setTcount(Integer tcount) {
		this.tcount = tcount;
	}

	@Override
	public String toString() {
		return "Tag [tid=" + tid + ", tname=" + tname + ", tcount=" + tcount + "]";
	}
	
	
}
