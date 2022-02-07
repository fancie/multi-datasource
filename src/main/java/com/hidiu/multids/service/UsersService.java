package com.hidiu.multids.service;

import com.hidiu.multids.entity.Users;

public interface UsersService {

    Users save(Users user);

    Users findById(Integer id);
}
