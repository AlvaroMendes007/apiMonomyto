package br.com.monomyto.api.model;

public class RelatorioProduto {
	String nomeProduto;
	
	Long quantidade;
	
	Double valorTotal;
	
	public RelatorioProduto() {}

	public RelatorioProduto(String nomeProduto, Long quantidade, Double valorTotal) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}	
}
