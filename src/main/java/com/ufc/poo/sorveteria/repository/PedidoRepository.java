package com.ufc.poo.sorveteria.repository;

import javax.management.BadAttributeValueExpException;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Pedido;

public interface PedidoRepository {
    void save(Pedido pedido) throws NotFoundException, BadAttributeValueExpException;
    Pedido findById(Integer id);
    void edit(Pedido pedidos) throws NotFoundException ;
    void remove(Integer id) throws NotFoundException;
}
