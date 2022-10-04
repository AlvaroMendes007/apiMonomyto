package br.com.monomyto.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.monomyto.api.model.Cliente;
import br.com.monomyto.api.services.ClienteService;

@RestController
public class ClienteController {

	@Autowired(required=true)
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getClientes(@RequestParam (required = false, value = "id") String id, 
			 									     @RequestParam (required = false, value = "nome") String nome,
													 @RequestParam (required = false, value = "dataInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio, 
													 @RequestParam (required = false, value = "dataFim") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim){
		
		
		return new ResponseEntity<>(clienteService.getClientes(id, nome, dataInicio, dataFim), HttpStatus.OK);
	}
}
