package com.ufc.poo.sorveteria.repository;

import javax.management.BadAttributeValueExpException;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Cliente;

public interface ClienteRepository {
    void save(Cliente cliente) throws NotFoundException, BadAttributeValueExpException;
    Cliente findById(Integer id);
    void edit(Cliente cliente) throws NotFoundException;
    void remove(Integer id) throws NotFoundException;
}
