package br.com.monomyto.api.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.monomyto.api.model.Cliente;
import br.com.monomyto.api.model.Produto;
import br.com.monomyto.api.model.RelatorioCliente;
import br.com.monomyto.api.model.RelatorioClientePorProduto;
import br.com.monomyto.api.model.RelatorioProduto;
import br.com.monomyto.api.model.Venda;
import br.com.monomyto.api.repositories.ClienteRepository;
import br.com.monomyto.api.repositories.ProdutoRepository;
import br.com.monomyto.api.repositories.VendaPorProdutoRepository;
import br.com.monomyto.api.repositories.VendaRepository;

@Service
public class VendaService {

	@Autowired
	VendaRepository vendaRepository;
	
	@Autowired
	VendaPorProdutoRepository vendaPorProdutoRepository;
	
	@Autowired
	ProdutoRepository produtorepository;
	
	@Autowired
	ClienteRepository clienteRepository;

	public Page<Venda> getVendas(String nomeCliente, String nomeProduto, LocalDate dataInicio, LocalDate dataFim, int pagina, int tamanhoPagina) {	
		return vendaRepository.findAll(getVendasComParametrosOpcionais(nomeCliente, nomeProduto, dataInicio, dataFim), PageRequest.of(pagina, tamanhoPagina));
	}
	
	public Specification<Venda> getVendasComParametrosOpcionais(String nomeCliente, String nomeProduto, LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nomeCliente != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("cliente").get("nome")), "%" + nomeCliente.toLowerCase() + "%"));
            }

            if (nomeProduto != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("itens").get("produto").get("nome")), "%" + nomeProduto.toLowerCase() + "%"));
            }

            if (dataInicio != null && dataFim != null) {
                predicates.add(criteriaBuilder.between(root.get("data"), dataInicio, dataFim));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
	
	public List<RelatorioCliente> getVendasPorCliente() { 
		List<Cliente> clientes = clienteRepository.findAll();
		List<RelatorioCliente> relatoriosCliente = new ArrayList<>();
	
		for (Cliente cliente : clientes) {
			RelatorioCliente relatorioCliente = new RelatorioCliente();
			
			relatorioCliente = vendaPorProdutoRepository.getRelatorioPorCliente(cliente);
			
			relatoriosCliente.add(relatorioCliente);			
		}
		
		return relatoriosCliente;
	}
	
	public List<RelatorioClientePorProduto> getVendasProdutoPorCliente(int limiteProdutos, LocalDate dataInicio, LocalDate dataFim) { 
		List<Cliente> clientes = clienteRepository.findAll();
		
		List<RelatorioClientePorProduto> relatoriosClienteProduto = new ArrayList<>();
		
		for (Cliente cliente : clientes) {
			List<RelatorioClientePorProduto> relatoriosClientePorProduto = new ArrayList<>();
			List<Produto> produtos = produtorepository.findAll();
			RelatorioClientePorProduto relatorioClienteProduto = new RelatorioClientePorProduto();
			
			for (Produto produto: produtos) {	
				if (dataInicio != null && dataFim != null) {
					relatorioClienteProduto = vendaPorProdutoRepository.getRelatorioPorClienteLimitadoPorData(cliente, produto, dataInicio, dataFim);
				} else {
					relatorioClienteProduto = vendaPorProdutoRepository.getRelatorioPorClienteLimitado(cliente, produto);	
				}

				if (relatorioClienteProduto != null) {
					relatoriosClientePorProduto.add(relatorioClienteProduto);	
				}
			}		
			
			Collections.sort(relatoriosClientePorProduto, Comparator.comparingDouble(RelatorioClientePorProduto::getQuantidadeProdutos).reversed());
			
			for (RelatorioClientePorProduto relatorioClientePorProduto : relatoriosClientePorProduto.stream().limit(limiteProdutos).collect(Collectors.toList())){
				relatoriosClienteProduto.add(relatorioClientePorProduto);
			}
		}
		
		return relatoriosClienteProduto;
	}
	
	public List<RelatorioProduto> getVendasPorProduto(String tipoFiltro, String filtroOrdenacao) { 
		List<Produto> produtos = produtorepository.findAll();
		List<RelatorioProduto> relatoriosProduto = new ArrayList<>();
	
		for (Produto produto : produtos) {
			RelatorioProduto relatorioProduto = new RelatorioProduto();
			
			relatorioProduto = vendaPorProdutoRepository.getRelatorioPorProduto(null, produto);	
			
			relatoriosProduto.add(relatorioProduto);			
		}
		
		if (tipoFiltro != null && filtroOrdenacao != null) {
			if (tipoFiltro.equals("quantidade")) {
				if (filtroOrdenacao.equals("asc")) {
					Collections.sort(relatoriosProduto, Comparator.comparingDouble(RelatorioProduto::getQuantidade));	
				} else if (filtroOrdenacao.equals("desc")) {
					Collections.sort(relatoriosProduto, Comparator.comparingDouble(RelatorioProduto::getQuantidade).reversed());
				}			
			} 
			else if (tipoFiltro.equals("valor")) {
				if (filtroOrdenacao.equals("asc")) {
					Collections.sort(relatoriosProduto, Comparator.comparingDouble(RelatorioProduto::getValorTotal));	
				} else if (filtroOrdenacao.equals("desc")) {
					Collections.sort(relatoriosProduto, Comparator.comparingDouble(RelatorioProduto::getValorTotal).reversed());
				}			
			} 
		}
		
		return relatoriosProduto;
	}
	
}
