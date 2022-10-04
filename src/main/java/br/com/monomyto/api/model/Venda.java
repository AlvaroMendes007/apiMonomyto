package br.com.monomyto.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Venda {

	@Id
	private String id;
	
	@Transient
	private String idCliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_idCliente", referencedColumnName = "id", nullable = false)
	private Cliente cliente;
	
	private LocalDate data;
	
	@OneToMany(mappedBy = "venda", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<VendaPorProduto> itens = new ArrayList<>();

	public Venda() {};
	
	public Venda(String id, String idCliente, Cliente cliente, LocalDate data, List<VendaPorProduto> itens) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.data = data;
		this.itens = itens;
		this.idCliente = idCliente;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<VendaPorProduto> getItens() {
		return itens;
	}

	public void setItens(List<VendaPorProduto> itens) {
		this.itens = itens;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
}
