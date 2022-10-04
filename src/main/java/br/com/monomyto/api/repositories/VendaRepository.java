package br.com.monomyto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.monomyto.api.model.Venda;

public interface VendaRepository extends JpaRepository<Venda,String>, JpaSpecificationExecutor<Venda>{

}
