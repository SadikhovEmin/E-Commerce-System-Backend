package com.example.ECommerceSystemBackend.Repository;


import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {

    @Query("select p from Product p where p.id = ?1")
    Product getProductByID (Integer id);

    @Query("select p from Product p where p.type = ?1")
    List<Product> getProductWithType (ProductType type);

}
