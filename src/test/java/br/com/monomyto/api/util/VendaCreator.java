package br.com.monomyto.api.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.monomyto.api.model.Cliente;
import br.com.monomyto.api.model.Venda;
import br.com.monomyto.api.model.VendaPorProduto;

public class VendaCreator {
	public static Venda createVenda(Cliente cliente) {
		Venda venda = new Venda();
		List<VendaPorProduto> listaVendaPorProduto = new ArrayList<>();
		VendaPorProduto vendaPorProduto = new VendaPorProduto();
		
		venda.setId("082aef42-ac5c-448a-a71f-1bd9573efeca");
		venda.setData(LocalDate.parse("2010-01-07"));
		venda.setCliente(cliente);
		
		vendaPorProduto.setId(UUID.randomUUID());
		vendaPorProduto.setProduto(ProdutoCreator.createProduto());
		vendaPorProduto.setQuantidade(2);
		vendaPorProduto.setPrecoUnitario(110l);
		vendaPorProduto.setVenda(venda);
		
		listaVendaPorProduto.add(vendaPorProduto);
		
		venda.setItens(listaVendaPorProduto);
		
		return venda;
	}
}
