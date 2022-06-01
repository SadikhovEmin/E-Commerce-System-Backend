package com.example.ECommerceSystemBackend.Repository;

import com.example.ECommerceSystemBackend.Model.Product;
import com.example.ECommerceSystemBackend.Model.enums.ProductType;
import com.example.ECommerceSystemBackend.Model.enums.ConfirmationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.id = ?1")
    Product getProductById(Integer id);

    @Query("select p from Product p where p.store.id = ?1")
    List<Product> getProductByStoreId(Integer id);

    @Query("select p.id from Product p where p.type = ?1")
    List<Integer> getProductWithType(ProductType type);

    @Query("select p from Product p where p.price = ?1")
    List<Integer> getProductWithPrice(Double price);

    @Query("select p from Product p where p.price < ?1")
    List<Integer> getProductLessThanPrice(Double price);

    @Query("select p from Product p where p.price > ?1")
    List<Integer> getProductGreaterThanPrice(Double price);

    @Query("select p from Product p where p.name = ?1")
    List<Product> getProductWithName(String name);

    @Query("select p from Product p order by p.price asc")
    List<Product> getProductInAscendingByPrice();

    @Query("select p from Product p order by p.price desc")
    List<Product> getProductInDescendingByPrice();

    @Modifying
    @Query("update Product set quantity = ?2 where id = ?1 ")
    @Transactional
    void updateProductQuantity(Integer id, Integer quantity);

    @Query("select count(p.id) from Product p")
    Integer getCount();
  
    @Modifying
    @Query("update Product set name = ?2 , price = ?3 , quantity = ?4 , type = ?5 , description = ?6  where id = ?1 ")
    @Transactional
    void updateProduct(Integer id,String name,Double price,Integer quantity,ProductType type,String description);

    @Modifying
    @Query("update Product set price = price - (price*?2)/100 where store.id = ?1 ")
    @Transactional
    void applyDiscountToPriceWithStoreId(Integer storeId, Double discount);

    @Query("select p from Product p where p.confirmationType = ?1")
    List<Product> getProductsBySuspended(ConfirmationType suspended);

    @Modifying
    @Transactional
    @Query("update Product p set p.suspended =?2 where p.id =?1")
    void suspendProduct(Integer id, Boolean isSuspended);
}
