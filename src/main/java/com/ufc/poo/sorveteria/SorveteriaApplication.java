package com.ufc.poo.sorveteria;

import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.model.Pedido;
import com.ufc.poo.sorveteria.model.Produto;
import com.ufc.poo.sorveteria.services.ClienteService;
import com.ufc.poo.sorveteria.services.PedidoService;
import com.ufc.poo.sorveteria.services.ProdutoService;
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
        cli.setCpf("12343523523");
        cli.setTelefone("888888888");

        ClienteService servs = new ClienteService();

        Produto acai = new Produto();
        acai.setId(1);
        acai.setNome("Açai");
        acai.setPreco(2.7); // 100 gramas
        acai.setQuantidadeDisponivel(10000); // gramas

        ProdutoService servss = new ProdutoService();

        Pedido ped = new Pedido();
        ped.setId(1);
        ped.setProduto(acai);
        ped.setQuantidadeDesejada(100);
        // ped.setValorTotal(calcularValorTotal());
        // teste

        PedidoService servvs = new PedidoService();

        try {
            servs.cadastrar(cli);
            servss.cadastrar(acai);
            servvs.cadastrar(ped);

            System.out.println(servs.buscar(1).getCpf());
            System.out.println(servss.buscar(1).getNome());
            System.out.println(servvs.buscar(1).getValorTotal());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
