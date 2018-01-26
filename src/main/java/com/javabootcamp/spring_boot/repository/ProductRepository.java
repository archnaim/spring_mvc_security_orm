package com.javabootcamp.spring_boot.repository;

import com.javabootcamp.spring_boot.model.Cart;
import com.javabootcamp.spring_boot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    @Query("select c from User a, Cart b, Product c where a.username=?1 and a.userId = b.user.userId and b.product.productId = c.productId")
    List<Product> findProductsInCartByUsername(String username);

    Page<Product> findByType(String type, Pageable pageable);

    @Query("SELECT DISTINCT(type) FROM Product")
    List<String> findDistinctCategories();


}
