/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.services;

import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.repository.ClienteRepository;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.util.NoSuchElementException;

/**
 *
 * @author cristiano
 */
public class ClienteService {    
    private ClienteRepository clienteRepository;
    
    public ClienteService(){
        clienteRepository = new ClienteRepository();
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
             clienteRepository.remove(id);
             System.out.println("Cliente removido com sucesso.\n");
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }
    
    public Cliente editar(Cliente cliente) throws NotFoundException{
        try{
            clienteRepository.edit(cliente);
            System.out.println("Cliente editado com sucesso.\n");
            return cliente;
        }catch(NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }
    
    public Cliente buscar(Integer id){
        try{
            return clienteRepository.findById(id);
        }catch(NoSuchElementException e){
            throw new NoSuchElementException("Nenhum cliente encontrado com o id '"+id+"'");
        }
    }
        
}
