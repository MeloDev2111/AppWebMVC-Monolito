package com.sistemas.monolito.servicio.impl;

import com.sistemas.monolito.dominio.Asignacion;
import com.sistemas.monolito.repositorio.AsignacionRepository;
import com.sistemas.monolito.servicio.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignacionServiceImpl implements AsignacionService {

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Override
    public Asignacion agregar(Asignacion asignacion) {
        return asignacionRepository.save(asignacion);
    }

    @Override
    public List<Asignacion> listarTodos() {
        return asignacionRepository.findAll();
    }

    @Override
    public Optional<Asignacion> buscar(Long id) {
        return asignacionRepository.findById(id);
    }

    @Override
    public Asignacion actualizar(Asignacion asignacion) {
        return asignacionRepository.save(asignacion);
    }

    @Override
    public void eliminar(Long id) {
        asignacionRepository.deleteById(id);
    }
}
