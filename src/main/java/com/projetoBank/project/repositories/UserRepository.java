package com.projetoBank.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoBank.project.entities.Client;


public  interface UserRepository extends JpaRepository<Client, Long>{

}
