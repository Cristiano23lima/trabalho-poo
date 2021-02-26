/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.model;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author cristiano
 */
public class Venda {
    private Integer id;
    private List<Pedido> pedidos;
    private Cliente cliente;
    private Double valorTotalVenda;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the pedidos
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * @param pedidos the pedidos to set
     */
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the valorTotal
     */
    public Double getValorTotalVenda() {
        return valorTotalVenda;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotalVenda(Double valorTotalVenda) {
        this.valorTotalVenda = valorTotalVenda;
    }

    /**
     * @return the createdAt
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    public Double calcularValorTotalCompra(List<Pedido> pedidos){
        if(pedidos.isEmpty()){
            return 0.0;
        }
        Double valorTotalVenda = 0.0;
        for(Pedido pedido: pedidos){
            valorTotalVenda += pedido.getValorTotal();
        }        
        
        return valorTotalVenda;
        
    }
        
}
