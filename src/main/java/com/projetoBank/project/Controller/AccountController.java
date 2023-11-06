package com.projetoBank.project.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoBank.project.Dto.TransactionsRequestDto;
import com.projetoBank.project.Entities.Account;
import com.projetoBank.project.Repositories.AccountRepository;
import com.projetoBank.project.Services.AccountService;

@RestController
@RequestMapping(value = "/BankAccounts")
public class AccountController {

	@Autowired
	private AccountService service;

	@Autowired
	private AccountRepository bankAccountRepository;

	@GetMapping
	public ResponseEntity<List<Account>> findAll() {
		List<Account> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Account> findById(@PathVariable Long id) {
		Account obj = service.findById(id);

		return ResponseEntity.ok().body(obj);
	}

	@PostMapping("/{id}/depositar")
	public ResponseEntity<String> depositar(@PathVariable Long id, @RequestBody TransactionsRequestDto request) {

		Account account = bankAccountRepository.findById(id).orElse(null);

		Double valor = request.getValor();

		if (account == null) {
			return ResponseEntity.notFound().build();
		}

		if (valor <= 0 || valor == null) {
			return ResponseEntity.badRequest().body("Valor de deposito invalido");
		}

		account.depositar(valor);

		bankAccountRepository.save(account);
		return ResponseEntity.ok("Depósito realizado com sucesso. Novo saldo : " + account.getSaldo());
	}

	@PostMapping("/{id}/sacar")
	public ResponseEntity<String> sacar(@PathVariable Long id, @RequestBody TransactionsRequestDto request) {

		Account account = bankAccountRepository.findById(id).orElse(null);

		Double valor = request.getValor();

		if (account == null) {
			return ResponseEntity.notFound().build();
		}

		if (valor <= 0 || valor == null) {
			return ResponseEntity.badRequest().body("Valor de saque invalido");
		}
		
		if(valor <= account.getSaldo()) {
			account.sacar(valor);
			bankAccountRepository.save(account);
		}
		else {
			return ResponseEntity.badRequest().body("Valor de saque invalido. Verifique o seu saldo");
		}
		

		return ResponseEntity.ok("Saque realizado com sucesso " + account.getSaldo());
	}

	@PostMapping("/{id}/transferir")
	public ResponseEntity<String> transferir(@PathVariable Long id, @RequestBody TransactionsRequestDto request) {
		// Obtenha a conta de origem

		Account origem = bankAccountRepository.findById(id).orElse(null);

		if (origem == null) {
			return ResponseEntity.notFound().build();
		}

		// Obtenha a conta de destino

		Long contaDestinoId = request.getId();
		

		Account destino = bankAccountRepository.findById(contaDestinoId).orElse(null);
		if (destino == null) {
			return ResponseEntity.badRequest().body("Conta de destino não encontrada");
		}

		Double valorTransferencia = request.getValor();

		if (valorTransferencia == null || valorTransferencia <= 0) {
			return ResponseEntity.badRequest().body("Valor de transferência inválido");
		}

		if (origem.getSaldo() < valorTransferencia) {
			return ResponseEntity.badRequest().body("Saldo insuficiente para a transferência");
		}

		// Realize a transferência
		double novoSaldoOrigem = origem.getSaldo() - valorTransferencia;
		double novoSaldoDestino = destino.getSaldo() + valorTransferencia;

		origem.setSaldo(novoSaldoOrigem);
		destino.setSaldo(novoSaldoDestino);

		// Atualize as contas no banco de dados
		bankAccountRepository.save(origem);
		bankAccountRepository.save(destino);

		return ResponseEntity.ok("Transferência realizada com sucesso. Novo saldo da origem: " + novoSaldoOrigem);
	}

}
