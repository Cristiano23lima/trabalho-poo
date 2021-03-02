package com.ufc.poo.sorveteria.repository;

import javax.management.BadAttributeValueExpException;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Venda;

public interface VendasRepository {
    void save(Venda venda) throws NotFoundException, BadAttributeValueExpException;
    Venda findById(Integer id);
    void edit(Venda vendas) throws NotFoundException;
    void remove(Integer id) throws NotFoundException;
}
