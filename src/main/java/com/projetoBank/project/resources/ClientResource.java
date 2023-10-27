package com.projetoBank.project.resources;

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
import com.projetoBank.project.entities.Account;
import com.projetoBank.project.entities.Client;
import com.projetoBank.project.repositories.AccountRepository;
import com.projetoBank.project.repositories.ClientRepository;
import com.projetoBank.project.services.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {

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

	@PostMapping
	public ResponseEntity<Client> createUser(@RequestBody Client request) {

		request = service.insert(request);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request.getId())
				.toUri();
		return ResponseEntity.created(uri).body(request);

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
		account.setNumeroConta(request.getNumeroConta());
		account.setSaldo(request.getSaldo());
		account.setTipoConta(request.getTipoConta());
		
		bankAccountRepository.save(account);

		return ResponseEntity.ok().body(account);

	}

}
