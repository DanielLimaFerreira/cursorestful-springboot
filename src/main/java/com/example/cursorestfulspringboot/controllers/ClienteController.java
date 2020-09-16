package com.example.cursorestfulspringboot.controllers;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
/* 
A classe clienteController é uma classe que o new dela é feita pelo spring
E a partir dela constroi objetos, o responsável por criar os objetos é o spring quando usa 
a anotação @RestController
Essa Classe possui os métodos init(), getCliente(), getClienteByCodigo()
*/
@RestController
public class ClienteController {

  //colocando atributo na classe ClienteController
  //Os objetos cliente serão construídos e serão armazenados nessa lista (atributo clientes do tipo List)
  public List<Cliente>clientes; 
  //List é uma classe, mas é preciso importar = ctrl + '.' e import 'List'(java.util)

  //Um atributo faz parte da estrutura da classe e todo mundo enxerga ele
  //Uma variável é visível apenas no método em que foi declarada
  //Apenas classes, atributos e métodos podem ser públicas
  //Esse método constrói um array com 2 clientes e é preciso guardar o array em algum lugar
  
  //spring, assim que voce construir o objeto ClienteController chamar o método init()
  //Significa após a contrução do objeto
  @PostConstruct
  public void init(){
    Cliente c1 = new Cliente();
    c1.id = 1;
    c1.nome = "Jose";
    c1.endereco = "Rua X, 99";
    c1.saldo = 202.0;

    Cliente c2 = new Cliente();
    c2.id = 2;
    c2.nome = "Maria";
    c2.endereco = "Rua y, 59";
    c2.saldo = 444.0;

    Cliente c3 = new Cliente();
    c3.id = 3;
    c3.nome = "Fernanda";
    c3.endereco = "Rua W, 33";
    c3.saldo = 332.0;

    //clientes é um atributo do tipo list que armazena um array de objetos
    clientes = Arrays.asList(c1,c2,c3);
    //Arrays é uma classe utilitaria "java.util"
  }
  
  //Esse método vai devolver a lista de clientes(objetos)
  @GetMapping("/clientes")
  public List<Cliente> getCliente(){
    return clientes;
  }

  //Esse método vai devolver um cliente daquela lista
  @GetMapping("/clientes/{id}")
  public Cliente getClienteByCodigo(@PathVariable int id){
    /*
    c1 = null não é possivel fazer isso pois c1 é uma variável
    declarada no método init
    */
    Cliente cliente = null;

    //aux é um cara da lista
    //clientes é o array lista, na primeira execução aux é o cliente de id 1, na segunda cliente de id 2
    //na terceira cliente de id 3
    for(Cliente aux : clientes){
      if(aux.id == id){
        cliente = aux; // cliente é uma variavel, clientes é o atributo, aux é uma variável do for
        break;
        //cliente e aux apontam para o mesmo objeto
      }
    }
    return cliente;
  }
}
