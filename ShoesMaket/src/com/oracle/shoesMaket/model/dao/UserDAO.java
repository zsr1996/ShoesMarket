package com.oracle.shoesMaket.model.dao;

import com.oracle.shoesMaket.model.bean.User;

public interface UserDAO {
    public User login(String email,String password);
    public  User getUserInfoByUserId(int userid);
}
