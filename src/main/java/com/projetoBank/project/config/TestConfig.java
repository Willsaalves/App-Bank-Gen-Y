package com.projetoBank.project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projetoBank.project.Entities.Account;
import com.projetoBank.project.Entities.Client;
import com.projetoBank.project.Entities.Employee;
import com.projetoBank.project.Repositories.AccountRepository;
import com.projetoBank.project.Repositories.ClientRepository;
import com.projetoBank.project.Repositories.EmployeeRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClientRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Client u1 = new Client(null, "Rua laranjal, 234","Maria Brown","123456789-11", "maria@gmail.com", "988888888", "123456");
		Client u2 = new Client(null, "Rua das palmeiras,329","Alex Green","123456987-12" ,"alex@gmail.com", "977777777", "123456"); 
		
		Account a1 = new Account(null, 100.00, 002, "PF", u1);
		Account a2 = new Account(null, 200.00, 004, "PF", u2);
		Account a3 = new Account(null, 300.00, 005, "PJ", u1);
		
		
		Employee e1 = new Employee(null, "Roberto passos", "CTO", 20000.00f);
		Employee e2 = new Employee(null, "Jussara Robert", "Gerente", 12000.00f);
		
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		accountRepository.saveAll(Arrays.asList(a1,a2,a3));
		employeeRepository.saveAll(Arrays.asList(e1,e2));
	}

}
