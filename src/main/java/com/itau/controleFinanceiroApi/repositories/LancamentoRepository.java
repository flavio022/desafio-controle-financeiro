package com.itau.controleFinanceiroApi.repositories;

import com.itau.controleFinanceiroApi.entities.Lancamento;
import com.itau.controleFinanceiroApi.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento,Long> {

    List<Lancamento> findByDataBetween(LocalDate to, LocalDate from);
    @Query("SELECT l FROM Lancamento l WHERE  l.data BETWEEN ?1 AND ?2 and l.subCategory in (?3)")
    List<Lancamento> findByCatAnDate(LocalDate dateFim, LocalDate dateIni, List<SubCategory> subCategory);

}
