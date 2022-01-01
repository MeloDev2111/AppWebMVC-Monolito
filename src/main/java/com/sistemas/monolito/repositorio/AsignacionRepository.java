package com.sistemas.monolito.repositorio;

import com.sistemas.monolito.dominio.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long>{

}
