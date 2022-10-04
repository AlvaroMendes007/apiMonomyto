package br.com.monomyto.api.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import br.com.monomyto.api.model.Cliente;
import br.com.monomyto.api.model.Produto;
import br.com.monomyto.api.model.RelatorioCliente;
import br.com.monomyto.api.model.RelatorioClientePorProduto;
import br.com.monomyto.api.model.RelatorioProduto;
import br.com.monomyto.api.model.VendaPorProduto;

public interface VendaPorProdutoRepository extends JpaRepository<VendaPorProduto,UUID>, JpaSpecificationExecutor<VendaPorProduto>{
	
	@Query(value = "SELECT new br.com.monomyto.api.model.RelatorioCliente("
			+ " cs.nome as nomeCliente,"
			+ " COUNT(*) as quantidade,"
			+ " SUM(vp.quantidade * vp.precoUnitario) as valorTotal)"
			+ " FROM Cliente cs"
			+ " JOIN Venda v on v.cliente.id = cs.id"
			+ " JOIN VendaPorProduto vp on vp.venda.id = v.id"
			+ " WHERE v.cliente = :cliente" 
			+ " GROUP BY cs.nome")			
	RelatorioCliente getRelatorioPorCliente(Cliente cliente);
	

	@Query(value = "SELECT new br.com.monomyto.api.model.RelatorioClientePorProduto("
			+ " cs.nome as nomeCliente,"
			+ " p.nome as nomeProduto,"
			+ " COUNT(*) as quantidade,"
			+ " SUM(vp.quantidade * vp.precoUnitario) as valorTotal)"
			+ " FROM Cliente cs"
			+ " JOIN Venda v on v.cliente.id = cs.id"
			+ " JOIN VendaPorProduto vp on vp.venda.id = v.id"
			+ " JOIN Produto p on p.id = vp.produto.id"
			+ " WHERE v.cliente = :cliente and vp.produto = :produto" 
			+ " GROUP BY cs.nome, p.nome "
			+ " ORDER BY quantidade DESC, valorTotal DESC")			
	RelatorioClientePorProduto getRelatorioPorClienteLimitado(Cliente cliente, Produto produto);
	
	@Query(value = "SELECT new br.com.monomyto.api.model.RelatorioClientePorProduto("
			+ " cs.nome as nomeCliente,"
			+ " p.nome as nomeProduto,"
			+ " COUNT(*) as quantidade,"
			+ " SUM(vp.quantidade * vp.precoUnitario) as valorTotal)"
			+ " FROM Cliente cs"
			+ " JOIN Venda v on v.cliente.id = cs.id"
			+ " JOIN VendaPorProduto vp on vp.venda.id = v.id"
			+ " JOIN Produto p on p.id = vp.produto.id"
			+ " WHERE v.cliente = :cliente and vp.produto = :produto" 
			+ " and v.data BETWEEN :dataInicial and :dataFim"
			+ " GROUP BY cs.nome, p.nome "
			+ " ORDER BY quantidade DESC, valorTotal DESC")			
	RelatorioClientePorProduto getRelatorioPorClienteLimitadoPorData(Cliente cliente, Produto produto, LocalDate dataInicial, LocalDate dataFim);
	
	@Query(value = "SELECT new br.com.monomyto.api.model.RelatorioProduto("
			+ " p.nome as nomeProduto,"
			+ " COUNT(*) as quantidade,"
			+ " SUM(vp.quantidade * vp.precoUnitario) as valorTotal)"
			+ " FROM Produto p"
			+ " JOIN VendaPorProduto vp on vp.produto.id = p.id"
			+ " WHERE p = :produto" 
			+ " GROUP BY p.nome")			
	RelatorioProduto getRelatorioPorProduto(Sort ordem, Produto produto);
}
