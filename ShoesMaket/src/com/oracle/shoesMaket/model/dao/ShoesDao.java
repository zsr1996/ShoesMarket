package com.oracle.shoesMaket.model.dao;


import java.util.ArrayList;

import com.oracle.shoesMaket.model.bean.Shoes;

public interface ShoesDao  extends BaseDAO{
	/*
	 * ���һ�����Ը����û������������ѯ��ʾ����������Ķ��ֳ���Ϣ
	 */
	public ArrayList<Shoes>  listShoesByCount(int count);
	
	
	/**
	 * ����һ����ѯ��Ҫ�ƹ���ʾ������������ֳ���Ϣ�ķ���
	 */
	public  ArrayList<Shoes>  getAllShoesByTuiguang();
	
	/**
	 * 
	 * @param shoeid Ҫ��ѯ��Ь��ID
	 * @return  һ����ѯ����װ�õ�Shoes����
	 */
	public Shoes  getShoesInfoByShoesId(int shoeid);
	
	public ArrayList<Shoes>  searchShoesByCondition(String pinpai);
	public ArrayList<Shoes> listShoesByPage(int page, int count);
	public int getAllCountOfShoes();
}
