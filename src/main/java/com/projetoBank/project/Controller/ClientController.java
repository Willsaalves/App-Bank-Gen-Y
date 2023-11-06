package com.projetoBank.project.Controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetoBank.project.Dto.AccountRequestDto;
import com.projetoBank.project.Entities.Account;
import com.projetoBank.project.Entities.Client;
import com.projetoBank.project.Repositories.AccountRepository;
import com.projetoBank.project.Repositories.ClientRepository;
import com.projetoBank.project.Services.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

	@Autowired
	private ClientService service;

	@Autowired
	private AccountRepository bankAccountRepository;

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client obj = service.findById(id);

		return ResponseEntity.ok().body(obj);
	}

	@PostMapping("/createAccount/{id}")
	public ResponseEntity<?> createAccount(@PathVariable Long id, @RequestBody AccountRequestDto request)
			throws RelationNotFoundException {

		Client client = clientRepository.findById(id).orElse(null);

		if (client == null) {
			String mensagemDeErro = "Usuário não encontrado com o ID: " + id;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemDeErro);
		}
		
		
		Account account = new Account();
		
		

		account.setClient(client);
		account.setNumeroConta(request.numeroConta());
		account.setSaldo(request.saldo());
		account.setTipoConta(request.tipoConta());
		
		bankAccountRepository.save(account);

		return ResponseEntity.ok().body(account);

	}

}
