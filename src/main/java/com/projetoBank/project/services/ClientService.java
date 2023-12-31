package com.projetoBank.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoBank.project.Entities.Client;
import com.projetoBank.project.Repositories.AccountRepository;
import com.projetoBank.project.Repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
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
