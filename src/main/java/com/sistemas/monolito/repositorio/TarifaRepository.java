package com.sistemas.monolito.repositorio;

import com.sistemas.monolito.dominio.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

}
