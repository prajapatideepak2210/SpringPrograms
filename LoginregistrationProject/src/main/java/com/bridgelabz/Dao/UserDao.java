package com.bridgelabz.Dao;

import com.bridgelabz.model.Login;
import com.bridgelabz.model.User;

public interface UserDao {
  int register(User user);
  User validateUser(Login login);
}
