package br.com.monomyto.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.monomyto.api.model.RelatorioCliente;
import br.com.monomyto.api.model.RelatorioClientePorProduto;
import br.com.monomyto.api.model.RelatorioProduto;
import br.com.monomyto.api.model.Venda;
import br.com.monomyto.api.services.VendaService;

@RestController
public class VendaController {

	@Autowired(required=true)
	private VendaService vendaService;
	
	@GetMapping("/vendas")
    public ResponseEntity<Page<Venda>> getVendas(@RequestParam (required = false, value = "nomeCliente") String nomeCliente, 
    											 @RequestParam (required = false, value = "nomeProduto") String nomeProduto,
										    	 @RequestParam (required = false, value = "dataInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio, 
										    	 @RequestParam (required = false, value = "dataFim") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim,
	                                             @RequestParam (defaultValue = "0") int pagina,
	                                             @RequestParam (defaultValue = "10")int tamanhoPagina) {

        return new ResponseEntity<>(vendaService.getVendas(nomeCliente, nomeProduto, dataInicio, dataFim, pagina, tamanhoPagina), HttpStatus.OK);
    }
	
	@GetMapping("/total-vendas-por-cliente")
    public ResponseEntity<List<RelatorioCliente>> getTotalVendasPorCliente() {

		return new ResponseEntity<>(vendaService.getVendasPorCliente(), HttpStatus.OK);
	}
	
	@GetMapping("/total-vendas-produtos-por-cliente")
    public ResponseEntity<List<RelatorioClientePorProduto>> getTotalVendasProdutosPorCliente(@RequestParam(defaultValue = "9999") int limiteProdutos,
					    	   @RequestParam (required = false, value = "dataInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio, 
					    	   @RequestParam (required = false, value = "dataFim") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim) {

		return new ResponseEntity<>(vendaService.getVendasProdutoPorCliente(limiteProdutos, dataInicio, dataFim), HttpStatus.OK);
	}
	
	@GetMapping("/total-vendas-por-produto")
    public ResponseEntity<List<RelatorioProduto>> getTotalVendasPorProduto(@RequestParam (required = false, value = "tipoFiltro") String tipoFiltro, 
					    	   											   @RequestParam (required = false, value = "filtroOrdenacao") String filtroOrdenacao) {

		return new ResponseEntity<>(vendaService.getVendasPorProduto(tipoFiltro, filtroOrdenacao), HttpStatus.OK);
	}
}

