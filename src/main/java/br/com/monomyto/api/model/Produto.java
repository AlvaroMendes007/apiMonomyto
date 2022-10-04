package br.com.monomyto.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	private String id;

	private String marca;

	private String classificacao;

	private String nome;

	private Long teorAlcoolico;

	private String regiao;

	private Double precoAtual;

	public Produto() {
	}

	public Produto(String id, String marca, String classificacao, String nome, Long teorAlcoolico, String regiao, Double precoAtual) {
		super();
		this.id = id;
		this.marca = marca;
		this.classificacao = classificacao;
		this.nome = nome;
		this.teorAlcoolico = teorAlcoolico;
		this.regiao = regiao;
		this.precoAtual = precoAtual;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(Long teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public Double getPrecoAtual() {
		return precoAtual;
	}

	public void setPrecoAtual(Double precoAtual) {
		this.precoAtual = precoAtual;
	}

}
