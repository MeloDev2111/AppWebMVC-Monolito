package com.sistemas.monolito.repositorio;

import com.sistemas.monolito.dominio.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden,Long> {

}
