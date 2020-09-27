package com.example.cursorestfulspringboot.controllers;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/* 
A classe clienteController é uma classe que o new dela é feita pelo spring
E a partir dela constroi objetos, o responsável por criar os objetos é o spring quando usa 
a anotação @RestController
Essa Classe possui os métodos init(), getCliente(), getClienteByCodigo()
*/
@RestController
public class ClienteController {
  
  //Anotação para o spring injetar o componente(objeto, ClienteRepository)aqui
 //Injeção de dependencias
 //Tirando o @component e o @autowired seria necessário fazer assim
 //private ClienteRepository repository = new ClienteRepository();
  @Autowired
  private ClienteRepository repository;

  //Esse método vai devolver a lista de clientes(objetos)
  @GetMapping("/clientes")
  public List<Cliente> getClientes(){
    return repository.getAllClientes();
  }

  //Esse método vai devolver um cliente daquela lista
  @GetMapping("/clientes/{id}")
  public Cliente getClienteByCodigo(@PathVariable int id){
    return repository.getClienteById(id);
  }

  @PostMapping("/clientes")
  public Cliente salvar(@RequestBody Cliente cliente){
    return repository.salvar(cliente);
  }
//Quando o postman mandar os dados, ele manda pelo body, o spring pegara esses dados
//e irá mapear isso dentro do Cliente 
}
