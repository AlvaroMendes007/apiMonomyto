package br.com.monomyto.api.model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	@Id
	private String id;

	private String nome;
	
	private LocalDate dataNascimento;

	public Cliente(String id, String nome, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	public Cliente() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) { 
		LocalDate localDate;
		String dataNascimentoFormatada = dataNascimento.toString();
        if(dataNascimentoFormatada.contains("/")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            localDate = LocalDate.parse(dataNascimentoFormatada, formatter);
        } else {
            localDate = LocalDate.parse(dataNascimentoFormatada, DateTimeFormatter.ISO_DATE);
        }
        this.dataNascimento = localDate;
	}

}
