package com.example.cursorestfulspringboot.controllers;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* 
A classe clienteController é uma classe que o new dela é feita pelo spring
E a partir dela constroi objetos, o responsável por criar os objetos é o spring quando usa 
a anotação @RestController
Essa Classe possui os métodos init(), getCliente(), getClienteByCodigo()
*/
@RestController
@RequestMapping("/clientes")
public class ClienteController {
  
  //Anotação para o spring injetar o componente(objeto, ClienteRepository)aqui
 //Injeção de dependencias
 //Tirando o @component e o @autowired seria necessário fazer assim
 //private ClienteRepository repository = new ClienteRepository();
  @Autowired
  private ClienteRepository repository;

  //Esse método vai devolver a lista de clientes(objetos)
  @GetMapping
  public List<Cliente> getClientes(){
    return repository.getAllClientes();
  }

  //Esse método vai devolver um cliente daquela lista
  @GetMapping
  public ResponseEntity<Cliente> getClienteByCodigo(@PathVariable int id){
    Cliente cli = repository.getClienteById(id);
    //Existe um cliente com o id passado?
    if(cli != null){
      return ResponseEntity.ok(cli);//retorno 200 e o cliente
    }
    else{
      return ResponseEntity.notFound().build();//erro 404
    }
    
  }

  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody Cliente cliente){
    Cliente cli = repository.salvar(cliente);
    URI uri = URI.create("http://localhost:8080/clientes/" + cli.getId());
    return ResponseEntity.created(uri).build();
    //Void porque o body vem vazio, e ficou em headers
    //201 quando cria, precisa da URI
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remover(@PathVariable int id){
    Cliente cli = repository.getClienteById(id);

    //Existe cliente com o id passado
    if(cli != null){
      repository.remove(cli);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> atualizar(@PathVariable int id, @RequestBody Cliente cliente){
    if(repository.getClienteById(id) != null){
      cliente.setId(id);
      cliente = repository.update(cliente);
      return ResponseEntity.ok(cliente);
    }else{
      return ResponseEntity.notFound().build();
    }
  }
//Quando o postman mandar os dados, ele manda pelo body, o spring pegara esses dados
//e irá mapear isso dentro do Cliente

}
