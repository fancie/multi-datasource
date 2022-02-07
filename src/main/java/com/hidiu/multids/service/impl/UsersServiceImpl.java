package com.hidiu.multids.service.impl;

import com.hidiu.multids.annotation.TargetDataSource;
import com.hidiu.multids.config.DataSourceNames;
import com.hidiu.multids.entity.Users;
import com.hidiu.multids.repository.UsersRepository;
import com.hidiu.multids.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Override
    @TargetDataSource(value = DataSourceNames.slave)
    public Users findById(Integer id){
        return usersRepository.findByIdFromSql(id);
    }
}
