/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.repository.impl;

import com.ufc.poo.sorveteria.model.Venda;
import com.ufc.poo.sorveteria.repository.VendasRepository;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano Simulando um banco de dados
 */

public class VendasRepositoryImpl implements VendasRepository{
    private static List<Venda> vendas;

    public VendasRepositoryImpl() {
        if (vendas == null) {
            vendas = new ArrayList<>();
        }
    }

    @Override
    public void save(Venda venda) throws NotFoundException, BadAttributeValueExpException {
        if (venda == null) {
            throw new NotFoundException("Venda é nula");
        }else if(findById(venda.getId()) != null){
            throw new BadAttributeValueExpException("Venda com esse ID '"+venda.getId()+"' já cadastrada");
        }

        venda.setCreatedAt(new Timestamp(new Date().getTime()));

        vendas.add(venda);
    }

    @Override
    public Venda findById(Integer id) {
        return vendas.stream().filter(vendas -> vendas.getId().equals(id)).findFirst().get();
    }

    @Override
    public void edit(Venda vendas) throws NotFoundException {
        Venda vendasEdit = this.findById(vendas.getId());
        if (vendasEdit == null) {
            throw new NotFoundException("Venda não encontrado.");
        }

        if (!vendas.getPedidos().isEmpty()) {
            vendasEdit.setPedidos(vendas.getPedidos());
        }

        if (vendas.getCliente() != null) {
            vendasEdit.setCliente(vendas.getCliente());
        }

        if (!vendas.getValorTotalVenda().isNaN()) {
            vendasEdit.setValorTotalVenda(vendas.getValorTotalVenda());
        }

        vendasEdit.setUpdatedAt(new Timestamp(new Date().getTime()));
        this.remove(vendasEdit.getId());// vai remover o valor antigo do array

        vendas.add(vendasEdit);// atualiza o array
    }

    @Override
    public void remove(Integer id) throws NotFoundException {
        if (this.findById(id) == null) {
            throw new NotFoundException("Cliente não encontrado");
        }

        vendas = vendas.stream().filter(vendasSalvo -> !vendasSalvo.getId().equals(id)).collect(Collectors.toList());
    }
}
