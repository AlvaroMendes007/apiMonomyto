package br.com.monomyto.api.util;

import br.com.monomyto.api.model.Produto;

public class ProdutoCreator {

	public static Produto createProduto() {
		Produto produto = new Produto();
		produto.setId("a9807de2-1943-4744-b446-14c1cba5a9db");
		produto.setMarca("Cachaças Canuto");
		produto.setClassificacao("Ouro");
		produto.setNome("Canuto Golden 700ml");
		produto.setTeorAlcoolico(44L);
		produto.setRegiao("MG");
		produto.setPrecoAtual(110.00);
		
		return produto;
	}
}
