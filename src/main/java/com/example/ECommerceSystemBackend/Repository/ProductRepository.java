package com.example.ECommerceSystemBackend.Repository;


import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {

    @Query("select p from Product p where p.id = ?1")
    Product getProductByID (Integer id);

    @Query("select p from Product p where p.store.id = ?1")
    List<Product> getProductByStoreID (Integer id);

    @Query("select p.id from Product p where p.type = ?1")
    List<Integer> getProductWithType (ProductType type);

    @Query("select p from Product p where p.price = ?1")
    List<Integer> getProductWithPrice (Double price);

    @Query("select p from Product p where p.price < ?1")
    List<Integer> getProductLessThanPrice (Double price);

    @Query("select p from Product p where p.price > ?1")
    List<Integer> getProductGreaterThanPrice (Double price);

    @Query("select p from Product p where p.name = ?1")
    List<Product> getProductWithName (String name);

    @Modifying
    @Query("update Product set quantity = ?2 where id = ?1 ")
    @Transactional
    void updateProductQuantity(Integer id, Integer quantity);



}
