package br.com.monomyto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.monomyto.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>, JpaSpecificationExecutor<Produto>{
}
