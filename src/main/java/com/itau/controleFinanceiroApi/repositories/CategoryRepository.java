package com.itau.controleFinanceiroApi.repositories;


import com.itau.controleFinanceiroApi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}