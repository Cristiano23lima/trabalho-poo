/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufc.poo.sorveteria.repository;

import com.ufc.poo.sorveteria.config.ConexaoMysql;
import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.exceptions.NotFoundException;
import com.ufc.poo.sorveteria.repository.filter.ClienteFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ClienteRepository {
    public ClienteRepository() {}

    
    public void save(Cliente cliente) throws NotFoundException, BadAttributeValueExpException {
        ClienteFilter filter = new ClienteFilter();
        filter.setCpf(cliente.getCpf());

        cliente.verificarCliente();//caso o cliente esteja ok ele segue adiante, se não ele joga uma excessão
        
        if (cliente == null) {
            throw new NotFoundException("Cliente é nulo");
        }else if(!this.whereSql(filter).isEmpty()){
            throw  new BadAttributeValueExpException("Cliente com esse id '"+cliente.getId()+"' já cadastrado");
        }

        cliente.setCreatedAt(new Timestamp(new Date().getTime()));
        
        //INSTRUÇÃO SQL
        this.insertSql(cliente);
    }

    
    public List<Cliente> findByCliente(ClienteFilter filter){
        if(filter == null){
            return null;   
        }

        try{
            return this.whereSql(filter);
        }catch(Exception e){
            return null;//caso não existe nenhum valor, ele irá retornar um erro, por isso o return null aqui
        }
    }

    
    public void edit(Cliente cliente) throws NotFoundException, BadAttributeValueExpException {
        Cliente clienteEdit = this.whereSqlById(cliente.getId());
        
        if (clienteEdit == null) {
            throw new NotFoundException("Cliente não encontrado.");
        }

        cliente.verificarCliente();

        clienteEdit.setCpf(cliente.getCpf());
        clienteEdit.setNome(cliente.getNome());
        clienteEdit.setTelefone(cliente.getTelefone());
        
        clienteEdit.setUpdatedAt(new Timestamp(new Date().getTime()));
        
        this.updateSql(clienteEdit);
    }

    
    public void remove(Integer id) throws NotFoundException {
        if (this.whereSqlById(id) == null) {
            throw new NotFoundException("Cliente não encontrado");
        }

        this.deleteSql(id);
    }
    
    private void deleteSql(Integer id){
        String sql = "DELETE FROM cliente WHERE id = ?";
        
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
            conexao = ConexaoMysql.openConnection();
            ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(ps != null) ps.close();
                if(conexao != null) conexao.close();
            }catch(Exception e){ e.printStackTrace();}
        }
    }
    
    private void insertSql(Cliente cliente){
        String sql = "INSERT INTO cliente(nome, cpf, telefone, createdAt, updatedAt)"+
                " VALUES(?,?,?,?,?)";
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
            conexao = ConexaoMysql.openConnection();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setTimestamp(4, cliente.getCreatedAt());            
            ps.setTimestamp(5, cliente.getUpdatedAt());

            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
           
                if(ps != null){
                    ps.close();
                }
                
                if(conexao != null){
                    conexao.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private void updateSql(Cliente cliente){
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, updatedAt = ? WHERE id = ?";
        Connection conexao = null;
        PreparedStatement ps = null;
        
        try{
            conexao = ConexaoMysql.openConnection();
            ps = conexao.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());       
            ps.setTimestamp(4, cliente.getUpdatedAt());
            ps.setInt(5, cliente.getId());

            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
           
                if(ps != null){
                    ps.close();
                }
                
                if(conexao != null){
                    conexao.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    private List<Cliente> whereSql(ClienteFilter filter){
        String sql = "SELECT * FROM cliente";
        List<String> clausulas = new ArrayList<>();
        if(filter.getId() != null){
            clausulas.add("id="+filter.getId());
        }
        if(filter.getNome() != null && !filter.getNome().isEmpty()){
            clausulas.add("nome LIKE "+filter.getNome()+"%");
        }
        if(filter.getCpf() != null && !filter.getCpf().isEmpty()){
            clausulas.add("cpf="+filter.getCpf());
        }
        
        if(!clausulas.isEmpty()){
            String clausulasWhere = clausulas.stream().collect(Collectors.joining(" AND "));//irá retornar uma string separando as clausulas por AND
            sql += " WHERE "+clausulasWhere;
        }
        
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Cliente> clienteList = new ArrayList<>();
        
        try{
            conexao = ConexaoMysql.openConnection();
            ps = conexao.prepareStatement(sql);
            
            resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                Cliente cliente = new Cliente();
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setCreatedAt(resultSet.getTimestamp("createdAt"));
                cliente.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                cliente.setId(resultSet.getInt("id"));
                clienteList.add(cliente);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(ps != null) ps.close();
                if(conexao != null) conexao.close();
            }catch(Exception e){ e.printStackTrace();}
        }
        
        return clienteList;
    }
    
    
    private Cliente whereSqlById(Integer id){
        String sql = "SELECT * FROM cliente WHERE id = ?";
        
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        Cliente cliente = new Cliente();
        try{
            conexao = ConexaoMysql.openConnection();
            ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setCreatedAt(resultSet.getTimestamp("createdAt"));
                cliente.setUpdatedAt(resultSet.getTimestamp("updatedAt"));
                cliente.setId(resultSet.getInt("id"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(ps != null) ps.close();
                if(conexao != null) conexao.close();
            }catch(Exception e){ e.printStackTrace();}
        }
        
        return cliente;
    }
}
