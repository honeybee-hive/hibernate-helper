/*
 * UserRepository  1.0  2018-11-19
 */
package com.github.hibernate.helper.example.repository;

import com.github.hibernate.helper.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * [关于类内容的描述]
 *
 * @author zhuyan
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2018-11-19 zhuyan 初版
 */
public interface UserRepository extends JpaRepository<User, String> {

    User findByUserCode(String userCode);

    @Modifying
    @Query(value = "delete from sch_user u where u.flag = false", nativeQuery = true)
    void deleteFlagUsers();

}
