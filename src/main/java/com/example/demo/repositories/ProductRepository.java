package com.example.demo.repositories;

import com.example.demo.models.entities.CategoryEntity;
import com.example.demo.models.entities.ProductEntity;
import com.example.demo.models.serviceModels.ProductBying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByCategory(CategoryEntity categoryEntity);

    @Modifying
    void deleteById(Long id);

    @Query("select sum(p.price) from ProductEntity p ")
    BigDecimal findTotalSum();

}
