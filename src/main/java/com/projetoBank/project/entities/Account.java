package com.projetoBank.project.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank_account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double saldo;
	private Integer numeroConta;
	private String tipoConta;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public Account() {

	}

	public Account(Long id, double saldo, Integer numeroConta, String tipoConta, Client client) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.numeroConta = numeroConta;
		this.tipoConta = tipoConta;
		this.client = client;
	}
	
	
	  public void depositar(double valor) {
	        if (valor > 0) {
	            this.saldo = saldo + valor;
	        }
	    }

	    public void sacar(double valor){
	        if (valor > 0 && valor <= saldo) {
	            saldo -= valor;
	        } 
	    }

	    public void transferir(double valor, Account destino) {
	        if (valor > 0 && valor <= saldo) {
	            saldo -= valor;
	            destino.depositar(valor);
	        } 
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}




}
