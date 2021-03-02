package com.ufc.poo.sorveteria.repository;

import javax.management.BadAttributeValueExpException;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Produto;

public interface ProdutoRepository {
    void save(Produto produto) throws NotFoundException, BadAttributeValueExpException;
    Produto findById(Integer id);
    void edit(Produto produto) throws NotFoundException;
    void remove(Integer id) throws NotFoundException;
}
