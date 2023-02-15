package com.itau.controleFinanceiroApi.repositories;

import com.itau.controleFinanceiroApi.entities.Category;
import com.itau.controleFinanceiroApi.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    @Query("SELECT l FROM SubCategory l WHERE l.category=?1")
    List<SubCategory> findByCategory(Category category);
}

