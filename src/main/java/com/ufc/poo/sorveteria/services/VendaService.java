/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.services;

import com.ufc.poo.sorveteria.model.Venda;
import com.ufc.poo.sorveteria.repository.VendasRepository;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.util.NoSuchElementException;

import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano
 */

public class VendaService {
    private VendasRepository vendaRepository;

    public VendaService() {
        vendaRepository = new VendasRepository();
    }

    public Venda cadastrar(Venda venda) throws NotFoundException, BadAttributeValueExpException {
        try {
            vendaRepository.save(venda);
            System.out.println("Venda salva com sucesso.\n");
            return venda;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch ( BadAttributeValueExpException e){
            throw new BadAttributeValueExpException(e.getMessage());
        }
    }

    public void remover(Integer id) throws NotFoundException {
        try {
            vendaRepository.remove(id);
            System.out.println("Venda removida com sucesso.\n");
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public Venda editar(Venda venda) throws NotFoundException {
        try {
            vendaRepository.edit(venda);
            System.out.println("Venda editada com sucesso.\n");
            return venda;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public Venda buscar(Integer id) {
        try {
            return vendaRepository.findById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Nenhuma venda encontrada com o id '" + id + "'");
        }
    }
}
