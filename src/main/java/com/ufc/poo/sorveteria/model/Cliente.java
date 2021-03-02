/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.model;

import java.sql.Timestamp;
import javax.management.BadAttributeValueExpException;

/**
 *
 * @author cristiano
 */
public class Cliente extends Pessoa {

    private Integer id;
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

    /**
     * Validar o cpf do cliente, verificando se ele é valido
     */
    private Boolean validarCpf() {
        if (!this.cpf.isBlank() && !this.cpf.isEmpty()) {
            return true;
        }

        if (true) {// aqui tem que fazer a verificação de que o calculo do cpf é ok

        }

        return false;
    }

    public Boolean verificarCliente() throws BadAttributeValueExpException {
        if (nome.isBlank() || nome.isEmpty()) {
            throw new BadAttributeValueExpException("Campo nome é obrigátorio");
        }

        if (!validarCpf()) {
            throw new BadAttributeValueExpException("Campo CPF vazio ou inválido");
        }

        return true;
    }

}
