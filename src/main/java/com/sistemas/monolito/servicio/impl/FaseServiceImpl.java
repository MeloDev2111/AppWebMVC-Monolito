package com.sistemas.monolito.servicio.impl;

import com.sistemas.monolito.dominio.Fase;
import com.sistemas.monolito.repositorio.FaseRepository;
import com.sistemas.monolito.servicio.FaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaseServiceImpl implements FaseService {

    @Autowired
    private FaseRepository faseRepository;

    @Override
    public Fase agregar(Fase fase) {
        return faseRepository.save(fase);
    }

    @Override
    public List<Fase> listarTodos() {
        return faseRepository.findAll();
    }

    @Override
    public Optional<Fase> buscar(Long id) {
        return faseRepository.findById(id);
    }

    @Override
    public Fase actualizar(Fase fase) {
        return faseRepository.save(fase);
    }

    @Override
    public void eliminar(Long id) {
        faseRepository.deleteById(id);
    }
}
