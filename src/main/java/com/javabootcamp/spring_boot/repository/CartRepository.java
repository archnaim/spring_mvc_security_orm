package com.javabootcamp.spring_boot.repository;

import com.javabootcamp.spring_boot.model.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {

    @Query("select b from User a, Cart b where a.username=?1 and a.userId = b.user.userId and b.product.productId = ?2")
    Optional<Cart> getByUsernameAndProductId(String username, Long ProductId);

    @Query("select b from User a, Cart b where a.username=?1 and a.userId = b.user.userId")
    List<Cart> getByUsername(String username);

}
