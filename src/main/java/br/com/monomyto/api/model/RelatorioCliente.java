package br.com.monomyto.api.model;

public class RelatorioCliente {

	String nomeCliente;
	
	Long quantidadeProdutos;
	
	Double valorTotal;

	public RelatorioCliente() {}
	
	public RelatorioCliente(String nomeCliente, Long quantidadeProdutos, Double valorTotal) {
		super();
		this.nomeCliente = nomeCliente;
		this.quantidadeProdutos = quantidadeProdutos;
		this.valorTotal = valorTotal;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Long getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	public void setQuantidadeProdutos(Long quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}
