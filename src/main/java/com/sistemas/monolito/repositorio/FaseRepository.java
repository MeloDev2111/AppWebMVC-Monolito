package com.sistemas.monolito.repositorio;

import com.sistemas.monolito.dominio.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaseRepository extends JpaRepository<Fase, Long> {

}
