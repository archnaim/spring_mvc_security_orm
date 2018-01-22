package com.javabootcamp.spring_boot.repository;

import com.javabootcamp.spring_boot.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
}
