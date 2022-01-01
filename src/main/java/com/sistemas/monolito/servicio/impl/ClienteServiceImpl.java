package com.sistemas.monolito.servicio.impl;

import com.sistemas.monolito.dominio.Cliente;
import com.sistemas.monolito.repositorio.ClienteRepository;
import com.sistemas.monolito.servicio.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente agregar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscar(Long id) {
        Optional<Cliente> buscado = clienteRepository.findById(id);
        buscado.ifPresent( (cliente) -> cliente.getOrdenes().size());
        return buscado;
    }

    @Override
    public Cliente actualizar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
