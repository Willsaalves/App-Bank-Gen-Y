package com.projetoBank.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoBank.project.Entities.Client;


public  interface ClientRepository extends JpaRepository<Client, Long>{

}
