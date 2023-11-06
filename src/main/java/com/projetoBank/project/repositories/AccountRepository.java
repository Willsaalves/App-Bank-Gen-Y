package com.projetoBank.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoBank.project.Entities.Account;


public  interface AccountRepository extends JpaRepository<Account, Long>{


}
