package com.oracle.shoesMaket.model.dao;

import java.util.ArrayList;

import com.oracle.shoesMaket.model.bean.Shoes;

public interface BaseDAO {
	public String  dirverClass="com.mysql.jdbc.Driver";
	public String  url="jdbc:mysql://localhost:3306/shoes";
	public String username="root";
	public String password="root";
	
	//Ìí¼Ó
	public boolean add(Object o);
	
	public boolean  delete(Object id);
	
	public boolean update(Object  o);
	
	public Object    list();

	
}
