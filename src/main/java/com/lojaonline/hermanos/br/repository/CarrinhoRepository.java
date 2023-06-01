package com.lojaonline.hermanos.br.repository;

import com.lojaonline.hermanos.br.models.CarrinhoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, Long> {
}
