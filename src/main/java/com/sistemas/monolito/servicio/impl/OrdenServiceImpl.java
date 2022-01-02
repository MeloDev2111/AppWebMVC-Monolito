package com.sistemas.monolito.servicio.impl;

import com.sistemas.monolito.dominio.Orden;
import com.sistemas.monolito.repositorio.OrdenRepository;
import com.sistemas.monolito.servicio.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public Orden agregar(Orden orden) {


        return ordenRepository.save(orden);
    }

    @Override
    @Transactional
    public List<Orden> listarTodos() {
        List<Orden> listado = ordenRepository.findAll(
                Sort.by(Sort.Direction.ASC, "id")
        );
        listado.forEach((orden) -> orden.getCliente().equals(null));
        return listado;
    }

    @Override
    @Transactional
    public Optional<Orden> buscar(Long id) {
        Optional<Orden> buscado = ordenRepository.findById(id);
        buscado.ifPresent((orden)->{
            orden.getAsignaciones().forEach( (asignacion) ->{
                asignacion.getFase().equals(null);
                if (asignacion.getEmpleado() != null)
                    asignacion.getEmpleado().equals(null);
            });
        });
        return buscado;
    }

    @Override
    public Orden actualizar(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public void eliminar(Long id) {
        ordenRepository.deleteById(id);
    }
}
