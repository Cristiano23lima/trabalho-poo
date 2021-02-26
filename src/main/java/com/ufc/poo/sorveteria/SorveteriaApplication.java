package com.ufc.poo.sorveteria;

import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.services.ClienteService;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristiano
 */
public class SorveteriaApplication {
    public static void main(String[] args) throws NotFoundException, Exception {
        Cliente cli = new Cliente();
        cli.setId(1);
        cli.setNome("Cristiano");
        cli.setCpf("123435235234");
        cli.setTelefone("888888888");
        
        ClienteService servs = new ClienteService();
        
        try{
            servs.cadastrar(cli);
        
            System.out.println(servs.buscar(1).getCpf());
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        
    }
}
