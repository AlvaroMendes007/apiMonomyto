package br.com.monomyto.api.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.monomyto.api.model.Cliente;
import br.com.monomyto.api.model.Venda;
import br.com.monomyto.api.model.VendaPorProduto;

public class VendaPorProdutoCreator {

	public static VendaPorProduto createVendaPorProduto() {
		Cliente cliente = new Cliente();
		Venda venda = new Venda();
		List<VendaPorProduto> listaVendaPorProduto = new ArrayList<>();
		VendaPorProduto vendaPorProduto = new VendaPorProduto();
		
		cliente.setId("963a997a-6bfc-41b2-8125-c27d2f4c4de4");
		cliente.setDataNascimento("1994-07-15");
		cliente.setNome("Ricardo Pieper");
		
		venda.setId("082aef42-ac5c-448a-a71f-1bd9573efeca");
		venda.setData(LocalDate.parse("2010-01-07"));
		venda.setCliente(cliente);
		
		vendaPorProduto.setId(UUID.randomUUID());
		vendaPorProduto.setProduto(ProdutoCreator.createProduto());
		vendaPorProduto.setPrecoUnitario(110);
		vendaPorProduto.setQuantidade(2);
		
		listaVendaPorProduto.add(vendaPorProduto);
		venda.setItens(listaVendaPorProduto);
		
		vendaPorProduto.setVenda(venda);
		
		return vendaPorProduto;
	}
}
