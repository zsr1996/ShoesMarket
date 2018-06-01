package com.oracle.shoesMaket.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.oracle.shoesMaket.model.bean.Shoes;
import com.oracle.shoesMaket.model.bean.User;

public class UserDAOImp extends BaseDAOImp implements UserDAO {

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		User user=(User)o;
		boolean result=false;
		Statement sta=null;
		try {
			sta=getSta();
			int count=sta.executeUpdate("insert into  users(userid,firstname,lastname,sex,email,password)   values(null,'"+user.getFirstname()+"','"+user.getLastname()+"','"+user.getSex()+"','"+user.getEmail()+"','"+user.getPassword()+"')");
			result=(count>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Object id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object list() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		User user=null;
		//jdbc连接数据库取这个用户的信息
		PreparedStatement sta=null;
		ResultSet rs=null;	
		try {
			System.out.println("开始查询该用户");
			sta=getPreSta("select *  from users where email=? and password=?");
			sta.setString(1, email);
			sta.setString(2, password);
			rs=sta.executeQuery();
			
			if(rs.next()) {
				user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setSex(rs.getString("sex"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setAge(rs.getInt("age"));
				user.setPhone(rs.getInt("phone"));
				user.setImage(rs.getString("image"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public User getUserInfoByUserId(int userid) {
		User  user=null;
		PreparedStatement  sta=null;
		ResultSet rs=null;
		try {
			  sta=getPreSta("select *  from users where userid=?");
			sta.setInt(1, userid);
			rs=sta.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setSex(rs.getString("sex"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				if(rs.getString("image")!=null)
				{
					user.setEmail(rs.getString("email"));
					user.setImage(rs.getString("image"));
					user.setFirstname(rs.getString("firstname"));					
					user.setLastname(rs.getString("lastname"));
					user.setSex(rs.getString("sex"));
					user.setAge(rs.getInt("age"));				
					user.setPhone(rs.getInt("phone"));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;	}

}
