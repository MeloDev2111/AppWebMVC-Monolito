package com.sistemas.monolito.servicio.impl;

import com.sistemas.monolito.dominio.Orden;
import com.sistemas.monolito.repositorio.OrdenRepository;
import com.sistemas.monolito.servicio.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Orden> listarTodos() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<Orden> buscar(Long id) {
        return ordenRepository.findById(id);
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
