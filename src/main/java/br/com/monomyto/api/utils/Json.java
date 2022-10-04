package br.com.monomyto.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;

import br.com.monomyto.api.model.Cliente;
import br.com.monomyto.api.model.Produto;
import br.com.monomyto.api.model.Venda;
import br.com.monomyto.api.model.VendaPorProduto;
import br.com.monomyto.api.repositories.ClienteRepository;
import br.com.monomyto.api.repositories.ProdutoRepository;
import br.com.monomyto.api.repositories.VendaPorProdutoRepository;
import br.com.monomyto.api.repositories.VendaRepository;

@Component
public class Json {
	@Autowired
    ProdutoRepository produtoRepository;
	
	@Autowired
    ClienteRepository clienteRepository;
	
	@Autowired
	VendaRepository vendaRepository;
	
	@Autowired
	VendaPorProdutoRepository vendaPorProdutoRepository;
	
	@PostConstruct
	private void getArquivosJson() throws IOException {
		salvarProdutos();
		salvarClientes();
		salvarVendas();
	}
	
	private void salvarProdutos() throws IOException {
		Gson gson = new Gson();
		ObjectMapper mapper = JsonMapper.builder().findAndAddModules().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true).build();
		List<Produto> produtos = new ArrayList<>();
		
        ClassLoader classLoader = gson.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("json/Catalogo.json");
        String jsonString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        List<Produto> listaProdutos = Arrays.asList(mapper.readValue(jsonString , Produto[].class));

        for(Produto produto : listaProdutos) {
        	produtos.add(produto);
        }
        
        produtoRepository.saveAll(produtos);   
	}
	
	private void salvarClientes() throws IOException{
		Gson gson = new Gson();
		ObjectMapper mapper = JsonMapper.builder().findAndAddModules().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true).build();
		List<Cliente> clientes = new ArrayList<>();
		
        ClassLoader classLoader = gson.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("json/Clientes.json");
        String jsonString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        List<Cliente> listaClientes = Arrays.asList(mapper.readValue(jsonString , Cliente[].class));

        for(Cliente cliente : listaClientes) {
        	clientes.add(cliente);
        }
        
        clienteRepository.saveAll(clientes);   
    }
	
	private void salvarVendas() throws IOException{
 		Gson gson = new Gson();
		ObjectMapper mapper = JsonMapper.builder().findAndAddModules().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true).build();
		List<Venda> vendas = new ArrayList<>();
		List<VendaPorProduto> vendasPorProduto = new ArrayList<>();
    	
		ClassLoader classLoader = gson.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("json/Vendas.json");
        String jsonString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        
        List<Venda> listaVendas = Arrays.asList(mapper.readValue(jsonString , Venda[].class));
        
        for (Venda venda: listaVendas) {
        	venda.setCliente(clienteRepository.getReferenceById(venda.getIdCliente()));
        	for (VendaPorProduto vendaPorProduto : venda.getItens()) {
        		vendaPorProduto.setProduto(produtoRepository.getReferenceById(vendaPorProduto.getId().toString()));
        		vendaPorProduto.setVenda(venda);
        		vendasPorProduto.add(vendaPorProduto);
        	}
        	
        	vendas.add(venda);
        }
        
        vendaRepository.saveAll(vendas);
    }
	
}
