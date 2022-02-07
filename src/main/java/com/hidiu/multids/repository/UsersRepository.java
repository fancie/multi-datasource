package com.hidiu.multids.repository;

import com.hidiu.multids.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户Repository接口
 * @author fancie
 * @email 1084961@qq.com
 * @date 2022-02-01 09:40:47
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = "select t.* from users t where t.id = (?1)", nativeQuery = true)
    Users findByIdFromSql(Integer id);
}
