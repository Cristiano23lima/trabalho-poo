/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.services.impl;

import com.ufc.poo.sorveteria.model.Pedido;
import com.ufc.poo.sorveteria.repository.PedidoRepository;
import com.ufc.poo.sorveteria.services.PedidoService;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.util.NoSuchElementException;

import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano
 */

public class PedidoServiceImpl implements PedidoService{
    private static PedidoRepository pedidoRepository;

    public PedidoServiceImpl() {
        pedidoRepository = new PedidoRepository();
    }

    @Override
    public Pedido cadastrar(Pedido pedido) throws NotFoundException, BadAttributeValueExpException {
        try {
            pedidoRepository.save(pedido);
            System.out.println("Pedido salvo com sucesso.\n");
            return pedido;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch ( BadAttributeValueExpException e){
            throw new BadAttributeValueExpException(e.getMessage());
        }
    }

    @Override
    public void remover(Integer id) throws NotFoundException {
        try {
            pedidoRepository.remove(id);
            System.out.println("Pedido removido com sucesso.\n");
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public Pedido editar(Pedido pedido) throws NotFoundException {
        try {
            pedidoRepository.edit(pedido);
            System.out.println("Pedido editado com sucesso.\n");
            return pedido;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public Pedido buscar(Integer id) {
        try {
            return pedidoRepository.findById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Nenhum pedido encontrado com o id '" + id + "'");
        }
    }
}
