package com.oracle.shoesMaket.model.dao;


import java.util.ArrayList;

import com.oracle.shoesMaket.model.bean.Shoes;

public interface ShoesDao  extends BaseDAO{
	/*
	 * 设计一个可以根据用户传入的数量查询显示出最近发布的二手车信息
	 */
	public ArrayList<Shoes>  listShoesByCount(int count);
	
	
	/**
	 * 定义一个查询需要推广显示到滚动区域二手车信息的方法
	 */
	public  ArrayList<Shoes>  getAllShoesByTuiguang();
	
	/**
	 * 
	 * @param shoeid 要查询的鞋子ID
	 * @return  一个查询并封装好的Shoes对象
	 */
	public Shoes  getShoesInfoByShoesId(int shoeid);
	
	public ArrayList<Shoes>  searchShoesByCondition(String pinpai);
	public ArrayList<Shoes> listShoesByPage(int page, int count);
	public int getAllCountOfShoes();
}
