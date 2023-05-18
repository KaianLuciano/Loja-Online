package com.lojaonline.hermanos.br.repository;

import com.lojaonline.hermanos.br.models.ProdutoModels;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModels, Long> {
}
