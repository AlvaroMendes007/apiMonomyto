package br.com.monomyto.api.model;

public class RelatorioClientePorProduto {

	String nomeCliente;
	
	String nomeProduto;
	
	Long quantidadeProdutos;
	
	Double valorTotal;

	public RelatorioClientePorProduto() {}
	
	public RelatorioClientePorProduto(String nomeCliente, String nomeProduto, Long quantidadeProdutos,
			Double valorTotal) {
		super();
		this.nomeCliente = nomeCliente;
		this.nomeProduto = nomeProduto;
		this.quantidadeProdutos = quantidadeProdutos;
		this.valorTotal = valorTotal;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
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
