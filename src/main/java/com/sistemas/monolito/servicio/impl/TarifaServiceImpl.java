package com.sistemas.monolito.servicio.impl;

import com.sistemas.monolito.dominio.Tarifa;
import com.sistemas.monolito.repositorio.TarifaRepository;
import com.sistemas.monolito.servicio.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaServiceImpl implements TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    @Override
    public Tarifa agregar(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    @Override
    public List<Tarifa> listarTodos() {
        return tarifaRepository.findAll();
    }

    @Override
    public Optional<Tarifa> buscar(Long id) {
        return tarifaRepository.findById(id);
    }

    @Override
    public Tarifa actualizar(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    @Override
    public void eliminar(Long id) {
        tarifaRepository.deleteById(id);
    }
}
