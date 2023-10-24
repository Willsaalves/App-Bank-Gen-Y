package com.projetoBank.project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projetoBank.project.entities.Account;
import com.projetoBank.project.entities.Client;
import com.projetoBank.project.repositories.AccountRepository;
import com.projetoBank.project.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Client u1 = new Client(null, "Rua laranjal, 234","Maria Brown","123456789-11", "maria@gmail.com", "988888888", "123456");
		Client u2 = new Client(null, "Rua das palmeiras,329","Alex Green","123456987-12" ,"alex@gmail.com", "977777777", "123456"); 
		
		Account a1 = new Account(null, 100.00, 002, "PF", u1);
		Account a2 = new Account(null, 200.00, 004, "PF", u2);
		Account a3 = new Account(null, 300.00, 005, "PJ", u1);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		accountRepository.saveAll(Arrays.asList(a1,a2,a3));
	}

}
