package com.projetoBank.project.Dto;

import com.projetoBank.project.Entities.Client;

public record AccountRequestDto (double saldo,Integer numeroConta,  String tipoConta, Client client){
}
