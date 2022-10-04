package br.com.monomyto.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.monomyto.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>, JpaSpecificationExecutor<Cliente>{
}
