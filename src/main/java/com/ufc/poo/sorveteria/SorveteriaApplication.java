package com.ufc.poo.sorveteria;

import com.ufc.poo.sorveteria.model.Cliente;
import com.ufc.poo.sorveteria.model.Pedido;
import com.ufc.poo.sorveteria.model.Produto;
import com.ufc.poo.sorveteria.services.ClienteService;
import com.ufc.poo.sorveteria.services.PedidoService;
import com.ufc.poo.sorveteria.services.ProdutoService;
import com.ufc.poo.sorveteria.services.VendaService;
import com.ufc.poo.sorveteria.services.impl.ClienteServiceImpl;
import com.ufc.poo.sorveteria.services.impl.PedidoServiceImpl;
import com.ufc.poo.sorveteria.services.impl.ProdutoServiceImpl;
import com.ufc.poo.sorveteria.services.impl.VendaServiceImpl;
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
        ClienteService clienteService = new ClienteServiceImpl();
        ProdutoService produtoService = new ProdutoServiceImpl();
        PedidoService pedidoService = new PedidoServiceImpl();
        VendaService vendaService = new VendaServiceImpl();

        Cliente cli = new Cliente();
        cli.setId(1);
        cli.setNome("Cristiano");
        cli.setCpf("12343523523");
        cli.setTelefone("888888888");

        Cliente cli2 = new Cliente();
        cli2.setId(2);
        cli2.setNome("Cristiano");
        cli2.setCpf("12343523523");
        cli2.setTelefone("88888888");

        Produto acai = new Produto();
        acai.setId(1);
        acai.setNome("Açai");
        acai.setPreco(2.7); // 100 gramas
        acai.setQuantidadeDisponivel(10000); // gramas

        Pedido ped = new Pedido();
        ped.setId(1);
        ped.setProduto(acai);
        ped.setQuantidadeDesejada(100);
        ped.setValorTotal(700.0);

        Pedido ped2 = new Pedido();
        ped2.setId(2);
        ped2.setProduto(acai);
        ped2.setQuantidadeDesejada(100);
        ped2.setValorTotal(700.0);

        try {
            clienteService.cadastrar(cli);
            clienteService.cadastrar(cli2);
            produtoService.cadastrar(acai);
            pedidoService.cadastrar(ped);
            pedidoService.cadastrar(ped2);

            System.out.println(clienteService.buscar(1).getCpf());
            System.out.println(produtoService.buscar(1).getNome());
            System.out.println(pedidoService.buscar(2).getValorTotal());
        } catch (Exception e) {
            throw new Exception(e);
        }

    }
}
