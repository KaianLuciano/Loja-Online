package com.lojaonline.hermanos.br.repository;

import com.lojaonline.hermanos.br.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
}
