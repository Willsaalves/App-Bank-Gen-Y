package com.projetoBank.project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoBank.project.Entities.Client;
import com.projetoBank.project.Entities.Employee;
import com.projetoBank.project.Repositories.EmployeeRepository;

@Service
public class EmployeeSerivce {
	
	@Autowired
	private EmployeeRepository repository;
	
	
	public List<Employee> findAll(){
		
		return repository.findAll();
	}
	
	public Employee findById(Long id) {
		Optional<Employee> obj = repository.findById(id);
		
		return obj.get();
	}
	
	public Employee insert(Employee obj) {
		return repository.save(obj);
	}
	

}
