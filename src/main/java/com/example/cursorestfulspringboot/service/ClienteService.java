package com.example.cursorestfulspringboot.service;

import com.example.cursorestfulspringboot.dto.ClienteDTO;
import com.example.cursorestfulspringboot.model.Cliente;

import org.springframework.stereotype.Service;

//Service: É o spring framework que cria o cliente service, gerencia e injeta-o no local requirido
//O controlador vai usa-lo, quem gerencia é o springframework
@Service
public class ClienteService {
  //Converte cliente DTO(pattern) para cliente normal
  public Cliente fromDTO(ClienteDTO dto){
    Cliente cliente = new Cliente();
    cliente.setEndereco(dto.getEndereco());
    cliente.setNome(dto.getNome());

    return cliente;
  }
}
