package com.javabootcamp.spring_boot.repository;

import com.javabootcamp.spring_boot.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {

    Role findByRoleName(String rolename);

}
