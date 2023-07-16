package com.lojaonline.hermanos.br.repository;

import com.lojaonline.hermanos.br.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
