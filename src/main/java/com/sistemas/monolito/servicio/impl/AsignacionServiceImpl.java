package com.sistemas.monolito.servicio.impl;

import com.sistemas.monolito.dominio.Asignacion;
import com.sistemas.monolito.repositorio.AsignacionRepository;
import com.sistemas.monolito.servicio.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public List<Asignacion> listarTodos() {
        List<Asignacion> asignaciones = asignacionRepository.findAll();

        asignaciones.stream().forEach( (asignacion) ->{
            asignacion.getFase().equals(null);
            if(asignacion.getEmpleado() != null)
                asignacion.getEmpleado().equals(null);
        });

        return asignaciones;
    }

    @Override
    @Transactional
    public Optional<Asignacion> buscar(Long id) {
        Optional<Asignacion> buscado = asignacionRepository.findById(id);
        buscado.ifPresent( (asignacion) ->{
            asignacion.getOrden().equals(null);
            asignacion.getFase().equals(null);
            if (asignacion.getEmpleado() != null)
                asignacion.getEmpleado().equals(null);
        });

        return buscado;
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
