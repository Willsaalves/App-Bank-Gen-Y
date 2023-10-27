package com.projetoBank.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoBank.project.entities.Account;


public  interface AccountRepository extends JpaRepository<Account, Long>{


}
