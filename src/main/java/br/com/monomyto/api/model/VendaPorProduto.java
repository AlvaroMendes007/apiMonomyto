package br.com.monomyto.api.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class VendaPorProduto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne(optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name = "fk_idProduto", referencedColumnName = "id", nullable = false)
	private Produto produto;

	private float precoUnitario;
	
	private int quantidade;
	
	@ManyToOne(optional=true, fetch=FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name = "fk_idVenda", referencedColumnName = "id", nullable = false)
	private Venda venda;

	public VendaPorProduto() {};
	
	public VendaPorProduto(UUID id, Produto produto, float precoUnitario, int quantidade, Venda venda) {
		super();
		this.id = id;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.venda = venda;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public float getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(float precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
