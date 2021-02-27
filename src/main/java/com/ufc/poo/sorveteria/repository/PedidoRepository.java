/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.repository;

import com.ufc.poo.sorveteria.model.Pedido;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cristiano
 */

public class PedidoRepository {
    private static List<Pedido> pedidos;

    public PedidoRepository() {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
    }

    public void save(Pedido pedidos) throws NotFoundException {
        if (pedidos == null) {
            throw new NotFoundException("Pedido é nulo");
        }

        pedidos.setCreatedAt(new Timestamp(new Date().getTime()));

        pedidos.add(pedidos);
    }

    public Pedido findById(Integer id) {
        return pedidos.stream().filter(pedidos -> pedidos.getId().equals(id)).findFirst().get();
    }

    public void edit(Pedido pedidos) throws NotFoundException {
        Pedido pedidosEdit = this.findById(pedidos.getId());
        if (pedidosEdit == null) {
            throw new NotFoundException("Produto não encontrado.");
        }

        if (!pedidos.getProduto().isEmpty()) {
            pedidosEdit.setProduto(pedidos.getProduto());
        }

        if (pedidos.getQuantidadeDesejada() != null) {
            pedidosEdit.setQuantidadeDesejada(pedidos.getQuantidadeDesejada());
        }

        if (!pedidos.getValorTotal().isNaN()) {
            pedidosEdit.setValorTotal(pedidos.getValorTotal());
        }

        pedidosEdit.setUpdatedAt(new Timestamp(new Date().getTime()));
        this.remove(pedidosEdit.getId());// vai remover o valor antigo do array

        pedidos.add(pedidosEdit);// atualiza o array
    }

    public void remove(Integer id) throws NotFoundException {
        if (this.findById(id) == null) {
            throw new NotFoundException("Pedido não encontrado");
        }

        pedidos = pedidos.stream().filter(pedidosSalvo -> !pedidosSalvo.getId().equals(id))
                .collect(Collectors.toList());
    }
}
