package com.javabootcamp.spring_boot.repository;

import com.javabootcamp.spring_boot.model.Role;
import com.javabootcamp.spring_boot.model.User;
import com.javabootcamp.spring_boot.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole,Long> {

    @Query("SELECT a.roleName from Role a, UserRole b where b.user.username =?1 and b.role.roleId = a.roleId")
    String findRoleByUsername(String username);
}
