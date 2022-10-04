package br.com.monomyto.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.monomyto.api.model.Produto;
import br.com.monomyto.api.services.ProdutoService;

@RestController
public class ProdutoController {

	@Autowired(required=true)
	private ProdutoService produtoService;
	
	@GetMapping("/produtos")
	public ResponseEntity<List<Produto>> getProdutos(@RequestParam (required = false, value = "id") String id, 
													 @RequestParam (required = false, value = "nome") String nome, 
													 @RequestParam (required = false, value = "teorMin") Long teorAlcoolicoMinimo, 
													 @RequestParam (required = false, value = "teorMax") Long teorAlcoolicoMaximo){
		
		
		return new ResponseEntity<>(produtoService.getProdutos(id, nome, teorAlcoolicoMinimo, teorAlcoolicoMaximo), HttpStatus.OK);
	}
}
