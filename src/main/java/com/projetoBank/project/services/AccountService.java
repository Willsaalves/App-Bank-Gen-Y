package com.projetoBank.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoBank.project.Dto.AccountRequestDto;
import com.projetoBank.project.Entities.Account;
import com.projetoBank.project.Entities.Client;
import com.projetoBank.project.Repositories.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	
	public List<Account> findAll(){
		
		return repository.findAll();
	}
	
	public Account findById(Long id) {
		Optional<Account> obj = repository.findById(id);
		
		return obj.get();
	}
	
}
