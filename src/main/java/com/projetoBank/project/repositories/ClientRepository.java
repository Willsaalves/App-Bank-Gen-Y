package com.projetoBank.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoBank.project.entities.Client;


public  interface ClientRepository extends JpaRepository<Client, Long>{

}
