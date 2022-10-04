package br.com.monomyto.api.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.monomyto.api.model.Produto;
import br.com.monomyto.api.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> getProdutos(String id, String nome, Long teorAlcoolicoMinimo, Long teorAlcoolicoMaximo) {		
		if (id != null) {
			Optional<Produto> produtoPorId = getProdutoPorId(id); 
			if(produtoPorId.isPresent()){
				return Collections.singletonList(produtoPorId.get());
            }
			throw new RuntimeException("Id inválido");
		} else {
			return produtoRepository.findAll(getProdutosComParametrosOpcionais(nome, teorAlcoolicoMinimo, teorAlcoolicoMaximo));	
		}
		
	}
	
	public Optional<Produto> getProdutoPorId(String id) {
		Optional<Produto> produtoRetorno = produtoRepository.findById(id);
		return produtoRetorno;
	}
	
	public Specification<Produto> getProdutosComParametrosOpcionais(String nome, Long teorAlcoolicoMinimo, Long teorAlcoolicoMaximo){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            if (teorAlcoolicoMinimo != null && teorAlcoolicoMaximo != null) {
            	if (teorAlcoolicoMinimo > teorAlcoolicoMaximo) {
            		throw new RuntimeException("O valor minímo deve ser menor ou igual que o máximo");
            	}
                predicates.add(criteriaBuilder.between(root.get("teorAlcoolico"), teorAlcoolicoMinimo, teorAlcoolicoMaximo));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
