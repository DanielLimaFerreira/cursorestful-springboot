package com.example.cursorestfulspringboot.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.cursorestfulspringboot.dto.ClienteDTO;
import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.repository.ClienteRepository;
import com.example.cursorestfulspringboot.service.ClienteService;

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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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

  //Na classe está marcado como @service, e é injetado utilizando o Autowired
  @Autowired
  private ClienteService servico;

  //Esse método vai devolver a lista de clientes(objetos)
  @GetMapping
  public List<Cliente> getClientes(){
    return repository.getAllClientes();
  }

  //Esse método vai devolver um cliente daquela lista
  @GetMapping("/{id}")
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
  public ResponseEntity<Void> salvar(@RequestBody ClienteDTO novoCliente, HttpServletRequest request, UriComponentsBuilder builder){
    Cliente cli = repository.salvar(servico.fromDTO(novoCliente));
    //Antes:
    //URI uri = URI.create("http://localhost:8080/clientes/" + cli.getId());
    //Depois 1:
    //URI uri = URI.create("http://localhost:8080" + request.getRequestURI() + "/" + cli.getId());
    //Depois 2:
    UriComponents uriComponents = builder.path(request.getRequestURI()+"/"+cli.getId()).build();
    return ResponseEntity.created(uriComponents.toUri()).build();
    //Void porque o body vem vazio, e ficou em headers
    //201 quando cria, precisa da URI

    //Com isso, constroi dinamicamente a uri quando cria um novo recurso
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
  public ResponseEntity<Cliente> atualizar(@PathVariable int id, @RequestBody ClienteDTO clienteDTO){
    if(repository.getClienteById(id) != null){
      Cliente cliente = servico.fromDTO(clienteDTO);
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
