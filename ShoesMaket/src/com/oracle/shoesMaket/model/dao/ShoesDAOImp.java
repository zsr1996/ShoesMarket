package com.oracle.shoesMaket.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oracle.shoesMaket.model.bean.Shoes;

public class ShoesDAOImp extends BaseDAOImp implements ShoesDao {

	@Override
	public boolean add(Object o) {
		return false;
	}

	@Override
	public boolean delete(Object id) {
		return false;
	}

	@Override
	public boolean update(Object o) {
		return false;
	}

	@Override
	public Object list() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 根据传入的数量查询出鞋子信息
	 */
	public ArrayList<Shoes>  listShoesByCount(int count){

		ArrayList<Shoes>  shoes=new  ArrayList<Shoes>();//义一个集合存储查询出来的所有鞋子信息
		ResultSet rs=null;
	try {
		rs=getSta().executeQuery("select *  from  shoes order by  shoeid desc  limit "+count);
		while(rs.next())
		{
			shoes.add(parsetResultToShoes(rs));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	disposeResource(getSta(), rs, getCon());
		return  shoes;
	}

	@Override
	public ArrayList<Shoes> getAllShoesByTuiguang() {
		ArrayList<Shoes>  allShoes=new  ArrayList<Shoes>();//定义一个集合存储查询出来的所有鞋子信息
		ResultSet rs=null;
	try {
		rs=getSta().executeQuery("select *  from  shoes   where  shifoutuiguang=1");
		while(rs.next())
		{
			allShoes.add(parsetResultToShoes(rs));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	disposeResource(getSta(), rs, getCon());
		return  allShoes;
	}

	@Override
	public Shoes getShoesInfoByShoesId(int shoeid) {
		
		Shoes s=new Shoes();
		ResultSet rs=null;
		try {
			rs=getSta().executeQuery("select *  from  shoes  where shoeid="+shoeid);
			if(rs.next())
			{
				s.setShoeid(shoeid);
				s.setPinpaiming(rs.getString("pinpaiming"));
				s.setShoujia(rs.getFloat("shoujia"));
				s.setGoumaishijian(rs.getString("goumaishijian"));
				s.setXiezitupian(rs.getString("xiezitupian"));
				s.setMiaoshu(rs.getString("miaoshu"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(getSta(), rs, getCon());
			return  s;
	}

	@Override
	public ArrayList<Shoes> searchShoesByCondition(String pinpai) {
		/**
		 * sql���Ӧ���ж�Ӧ�ı䶯
		 */
		System.out.println("52365");
		System.out.println(pinpai);
		String SQL="select *  from  shoes  where  1=1";
		if(pinpai!=null&&!pinpai.equals("")) {
			SQL+="   and  pinpaiming='" +pinpai+"'";
		}

		System.out.println(SQL);
		
		ArrayList<Shoes>  allShoes=new ArrayList<Shoes>();//定义一个集合存储查询出来的所有车辆信息
		ResultSet rs=null;
	try {
		rs=getSta().executeQuery(SQL);
		while(rs.next())
		{
			allShoes.add(parsetResultToShoes(rs));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	disposeResource(getSta(), rs, getCon());
		return  allShoes;
	}
	
	/**
	 * 这里是处理分页的代码
	 * @param page
	 * @param count
	 * @return
	 */
	@Override
	public ArrayList<Shoes> listShoesByPage(int page, int count) {
		ArrayList<Shoes> shoes = new ArrayList<Shoes>();// 定义一个集合存储查询出来的所有鞋子信息
		ResultSet rs = null;
		try {
			rs = getSta().executeQuery("select *  from  shoes limit  "+(page-1)*count+" ,"+count);
			while (rs.next()) {

				shoes.add(parsetResultToShoes(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		disposeResource(getSta(), rs, getCon());
		return shoes;
	}
	
	@Override
	public int getAllCountOfShoes() {
		int  n=0;
		ResultSet  rs=null;
		try {
			  rs=getSta().executeQuery("select count(shoeid)  from  shoes");
			  rs.next();
			  n=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public Shoes parsetResultToShoes(ResultSet rs)
	{
		Shoes s=null;
		try {
			s=new Shoes();
			s.setShoeid(rs.getInt("shoeid"));
			s.setPinpaiming(rs.getString("pinpaiming"));
			s.setShoujia(rs.getFloat("shoujia"));
			s.setGoumaishijian(rs.getString("goumaishijian"));
			s.setXiezitupian(rs.getString("xiezitupian"));
			s.setMiaoshu(rs.getString("miaoshu"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return s;
	}
}
