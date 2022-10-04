package br.com.monomyto.api.util;

import br.com.monomyto.api.model.Cliente;

public class ClienteCreator {

	public static Cliente createCliente() {
		Cliente cliente = new Cliente();
		cliente.setId("963a997a-6bfc-41b2-8125-c27d2f4c4de4");
		cliente.setDataNascimento("1994-07-15");
		cliente.setNome("Ricardo Pieper");
		
		return cliente;
	}
}
