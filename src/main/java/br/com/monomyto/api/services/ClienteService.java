package br.com.monomyto.api.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.monomyto.api.model.Cliente;
import br.com.monomyto.api.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> getClientes(String id, String nome, LocalDate dataInicio, LocalDate dataFim) {	
		if (id != null) {
			Optional<Cliente> clientePorId = getClientePorId(id); 
			if(clientePorId.isPresent()){
				return Collections.singletonList(clientePorId.get());
            }
			throw new RuntimeException("Id inválido");
		} else {
			return clienteRepository.findAll(getClientesComParametrosOpcionais(nome, dataInicio, dataFim));	
		}
		
	}
	
	public Optional<Cliente> getClientePorId(String id) {
		Optional<Cliente> clienteRetorno = clienteRepository.findById(id);
		return clienteRetorno;
	}
	
	public Specification<Cliente> getClientesComParametrosOpcionais(String nome, LocalDate dataInicio, LocalDate dataFim){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            if (dataInicio != null && dataFim != null) {
            	if (dataFim.isBefore(dataInicio)) {
            		throw new RuntimeException("A data inicial não pode ser maior que a data final");
            	}
                predicates.add(criteriaBuilder.between(root.get("dataNascimento"), dataInicio, dataFim));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
