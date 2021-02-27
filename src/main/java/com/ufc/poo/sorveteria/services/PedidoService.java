/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.services;

import com.ufc.poo.sorveteria.model.Pedido;
import com.ufc.poo.sorveteria.repository.PedidoRepository;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.util.NoSuchElementException;

/**
 *
 * @author cristiano
 */

public class PedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoService() {
        pedidoRepository = new PedidoRepository();
    }

    public Pedido cadastrar(Pedido pedido) throws NotFoundException {
        try {
            pedidoRepository.save(pedido);
            System.out.println("Pedido salvo com sucesso.\n");
            return pedido;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public void remover(Integer id) throws NotFoundException {
        try {
            pedidoRepository.remove(id);
            System.out.println("Pedido removido com sucesso.\n");
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public Pedido editar(Pedido pedido) throws NotFoundException {
        try {
            pedidoRepository.edit(pedido);
            System.out.println("Pedido editado com sucesso.\n");
            return pedido;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public Pedido buscar(Integer id) {
        try {
            return pedidoRepository.findById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Nenhum pedido encontrado com o id '" + id + "'");
        }
    }
}
