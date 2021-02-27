/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.repository;

import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cristiano Simulando um banco de dados
 */
public class ClienteRepository {
    private static List<Cliente> clientes;

    public ClienteRepository() {
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
    }

    public void save(Cliente cliente) throws NotFoundException {
        if (cliente == null) {
            throw new NotFoundException("Cliente é nulo");
        }

        cliente.setCreatedAt(new Timestamp(new Date().getTime()));

        System.out.println("teste: " + clientes.size());

        clientes.add(cliente);
    }

    public Cliente findById(Integer id) {
        return clientes.stream().filter(cliente -> cliente.getId().equals(id)).findFirst().get();
    }

    public void edit(Cliente cliente) throws NotFoundException {
        Cliente clienteEdit = this.findById(cliente.getId());
        if (clienteEdit == null) {
            throw new NotFoundException("Cliente não encontrado.");
        }

        if (!cliente.getCpf().isEmpty()) {
            clienteEdit.setCpf(cliente.getCpf());
        }

        if (!cliente.getNome().isEmpty()) {
            clienteEdit.setNome(cliente.getNome());
        }

        if (!cliente.getTelefone().isEmpty()) {
            clienteEdit.setTelefone(cliente.getTelefone());
        }

        clienteEdit.setUpdatedAt(new Timestamp(new Date().getTime()));
        this.remove(clienteEdit.getId());// vai remover o valor antigo do array

        clientes.add(clienteEdit);// atualiza o array
    }

    public void remove(Integer id) throws NotFoundException {
        if (this.findById(id) == null) {
            throw new NotFoundException("Cliente não encontrado");
        }

        clientes = clientes.stream().filter(clienteSalvo -> !clienteSalvo.getId().equals(id))
                .collect(Collectors.toList());
    }

}
