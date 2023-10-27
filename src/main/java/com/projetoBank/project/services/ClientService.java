package com.projetoBank.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoBank.project.entities.Client;
import com.projetoBank.project.repositories.AccountRepository;
import com.projetoBank.project.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired AccountRepository accountRepository;
	
	public List<Client> findAll(){
		
		return repository.findAll();
	}
	
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		
		return obj.get();
	}
	
	public Client insert(Client obj) {
		return repository.save(obj);
	}
	

}
