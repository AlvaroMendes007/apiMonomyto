package br.com.monomyto.api.util;

import br.com.monomyto.api.model.RelatorioCliente;

public class RelatorioClienteCreator {
	public static RelatorioCliente createRelatorioCliente() {
		RelatorioCliente relatorioCliente = new RelatorioCliente();
		relatorioCliente.setNomeCliente("Ricardo Pieper");
		relatorioCliente.setQuantidadeProdutos(2l);
		relatorioCliente.setValorTotal(220.00);
		
		return relatorioCliente;
	}
}
