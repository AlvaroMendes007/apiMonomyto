package br.com.monomyto.api.service;

import static org.mockito.BDDMockito.given;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.monomyto.api.model.Cliente;
import br.com.monomyto.api.model.Produto;
import br.com.monomyto.api.repositories.ClienteRepository;
import br.com.monomyto.api.repositories.ProdutoRepository;
import br.com.monomyto.api.repositories.VendaPorProdutoRepository;
import br.com.monomyto.api.repositories.VendaRepository;
import br.com.monomyto.api.services.VendaService;
import br.com.monomyto.api.util.ClienteCreator;
import br.com.monomyto.api.util.ProdutoCreator;

@ExtendWith(MockitoExtension.class)
public class VendaServiceTest {
	
	@InjectMocks
	VendaService vendaService;

	@Mock
	ProdutoRepository produtoRepository;

	@Mock
	ClienteRepository clienteRepository;
	
	@Mock
	VendaRepository vendaRepository;

	@Mock
	VendaPorProdutoRepository vendaPorProdutoRepository;
	

	private Produto produto = ProdutoCreator.createProduto();
	private Cliente cliente = ClienteCreator.createCliente();
	/*private Venda venda = VendaCreator.createVenda(cliente);
	private VendaPorProduto vendaPorProduto = VendaPorProdutoCreator.createVendaPorProduto();
	private RelatorioCliente relatorioCliente = RelatorioClienteCreator.createRelatorioCliente();*/

	@Test
	void getRelatorioVendasTest(){

		given(produtoRepository.findAll()).willReturn(Arrays.asList(produto));
		given(clienteRepository.findAll()).willReturn(Arrays.asList(cliente));
		
		/*List<RelatorioCliente> vendas = vendaService.getVendasPorCliente();
		assertEquals(vendas.get(0).getNomeCliente(), relatorioCliente.getNomeCliente());
	 	assertEquals(vendas.get(0).getQuantidadeProdutos(), relatorioCliente.getQuantidadeProdutos());
		assertEquals(vendas.get(0).getValorTotal(), relatorioCliente.getValorTotal());*/
	}
}
