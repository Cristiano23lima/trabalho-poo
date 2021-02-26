/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.services;

import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.repository.ClienteRepository;
import exceptions.NotFoundException;

/**
 *
 * @author cristiano
 */
public class ClienteService {
    final private ClienteRepository clienteRepository;
    
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    
    public Cliente cadastrar(Cliente cliente) throws NotFoundException{
        try{
            clienteRepository.save(cliente);
            System.out.println("Cliente salvo com sucesso.\n");
            return cliente;
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }
    
    public void remover(Integer id) throws NotFoundException{
        try{
             this.clienteRepository.remove(id);
             System.out.println("Cliente removido com sucesso.\n");
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }
    
    public Cliente editar(Cliente cliente) throws NotFoundException{
        try{
            this.clienteRepository.edit(cliente);
            System.out.println("Cliente editado com sucesso.\n");
            return cliente;
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }
    
    public Cliente buscar(Integer id){
        return this.clienteRepository.findById(id);
    }
        
}
