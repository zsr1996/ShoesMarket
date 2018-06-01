package com.oracle.shoesMaket.model.bean;

public class Shoes {
/**
 * shoeid int NOT NULL AUTO_INCREMENT,
	pinpaiming varchar(20) NOT NULL,
	shoujia float NOT NULL,
	goumaishijian varchar(20) NOT NULL,
	xiezitupian varchar(20),
	miaoshu text,
	PRIMARY KEY (shoeid)
 */
	private int shoeid;
	private String pinpaiming;
	private float shoujia;
	private String goumaishijian;
	private String xiezitupian;
	private String miaoshu;
	public int getShoeid() {
		return shoeid;
	}
	public void setShoeid(int shoeid) {
		this.shoeid = shoeid;
	}
	public String getPinpaiming() {
		return pinpaiming;
	}
	public void setPinpaiming(String pinpaiming) {
		this.pinpaiming = pinpaiming;
	}
	public float getShoujia() {
		return shoujia;
	}
	public void setShoujia(float shoujia) {
		this.shoujia = shoujia;
	}
	public String getGoumaishijian() {
		return goumaishijian;
	}
	public void setGoumaishijian(String goumaishijian) {
		this.goumaishijian = goumaishijian;
	}
	public String getXiezitupian() {
		return xiezitupian;
	}
	public void setXiezitupian(String xiezitupian) {
		this.xiezitupian = xiezitupian;
	}
	public String getMiaoshu() {
		return miaoshu;
	}
	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}
	
	public Shoes(int shoeid) {
		super();
		this.shoeid = shoeid;
	}
	public Shoes(int shoeid, String pinpaiming, float shoujia, String goumaishijian, String xiezitupian,
			String miaoshu) {
		super();
		this.shoeid = shoeid;
		this.pinpaiming = pinpaiming;
		this.shoujia = shoujia;
		this.goumaishijian = goumaishijian;
		this.xiezitupian = xiezitupian;
		this.miaoshu = miaoshu;
	}
	public Shoes() {
		super();
	}
	@Override
	public String toString() {
		return "shoes [shoeid=" + shoeid + ", pinpaiming=" + pinpaiming + ", shoujia=" + shoujia + ", goumaishijian="
				+ goumaishijian + ", xiezitupian=" + xiezitupian + ", miaoshu=" + miaoshu + "]";
	}
	
}
