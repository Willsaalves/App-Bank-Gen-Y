package com.projetoBank.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoBank.project.Entities.Client;
import com.projetoBank.project.Entities.Employee;


public  interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
