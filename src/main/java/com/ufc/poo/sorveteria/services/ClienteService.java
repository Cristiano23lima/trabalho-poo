package com.ufc.poo.sorveteria.services;

import javax.management.BadAttributeValueExpException;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.repository.filter.ClienteFilter;
import java.util.List;

public interface ClienteService {
    Cliente cadastrar(Cliente cliente) throws NotFoundException, BadAttributeValueExpException;
    void remover(Integer id) throws NotFoundException;
    Cliente editar(Cliente cliente) throws NotFoundException, BadAttributeValueExpException;
    List<Cliente> buscar(ClienteFilter clienteFilter);
}
