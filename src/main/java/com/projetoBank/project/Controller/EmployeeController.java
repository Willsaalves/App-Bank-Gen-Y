package com.projetoBank.project.Controller;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoBank.project.Dto.AccountRequestDto;
import com.projetoBank.project.Entities.Account;
import com.projetoBank.project.Entities.Client;
import com.projetoBank.project.Entities.Employee;
import com.projetoBank.project.Repositories.AccountRepository;
import com.projetoBank.project.Repositories.ClientRepository;
import com.projetoBank.project.Services.EmployeeSerivce;


@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeSerivce service;

	
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {
		Employee obj = service.findById(id);

		return ResponseEntity.ok().body(obj);
	}
	

}
