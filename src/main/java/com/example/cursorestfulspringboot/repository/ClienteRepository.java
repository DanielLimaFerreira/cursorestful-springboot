package com.example.cursorestfulspringboot.repository;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import com.example.cursorestfulspringboot.model.Cliente;
import org.springframework.stereotype.Component;
//@component cria um componente do spring, eu posso usar esse componente
//em qualquer lugar, e o spring constroi esse objeto
//E quando constroi esse objeto automaticamente ele chama o init
@Component
public class ClienteRepository {
  
  //Os objetos Cliente serão construídos e serão armazenados nessa lista (atributo clientes do tipo List)
  private List<Cliente>clientes; 
  private int nextId;
  //List é uma classe, mas é preciso importar = ctrl + '.' e import 'List'(java.util)
  //Pois para uma classe enxergar a outra é necessário importar (quando estão em pacotes(pastas) diferentes)

  //Um atributo faz parte da estrutura da classe e todo mundo enxerga ele
  //Uma variável é visível apenas no método em que foi declarada
  //Apenas classes, atributos e métodos podem ser públicas
  //Esse método constrói um array com 2 clientes e é preciso guardar o array em algum lugar
  
  //spring, assim que voce construir o objeto ClienteController chamar o método init()
  //Significa após a contrução do objeto
  @PostConstruct
  public void init(){
    Cliente c1 = new Cliente();
    c1.setId(1);
    c1.setNome("Jose");
    c1.setEndereco("Rua X, 99");
    c1.setSaldo(202.0);

    Cliente c2 = new Cliente();
    c2.setId(2);
    c2.setNome("Maria");
    c2.setEndereco( "Rua y, 59");
    c2.setSaldo(444.0);

    Cliente c3 = new Cliente();
    c3.setId(3);
    c3.setNome("Fernanda");
    c3.setEndereco("Rua W, 33");
    c3.setSaldo(332.0);

    //clientes é um atributo do tipo list que armazena um array de objetos
    clientes = new ArrayList<Cliente>();
    clientes.add(c1);
    clientes.add(c2);
    clientes.add(c3);
    nextId = 4;
    //Arrays é uma classe utilitaria "java.util"
    //clientes = Arrays.asList(c1,c2,c3) -> Cria uma lista de tam fixo
    //Quando for salvar alguem nessa lista tem que usar esse formato
  }
  public List<Cliente> getAllClientes(){
    return clientes;
  }

  public Cliente getClienteById(int id){
    //aux é um cara da lista
    //clientes é o array lista, na primeira execução aux é o cliente de id 1, na segunda cliente de id 2
    //na terceira cliente de id 3
    for(Cliente aux : clientes){
      if(aux.getId() == id){
        return aux; // cliente é uma variavel, clientes é o atributo, aux é uma variável do for
        //cliente e aux apontam para o mesmo objeto
      }
    }
    return null;
  }
  public Cliente salvar(Cliente cliente) {
    cliente.setId(nextId++); //será o cod do novo cliente
    clientes.add(cliente);
    return cliente;
  }
public void remove(Cliente cli) {
  clientes.remove(cli);
}
public Cliente update(Cliente cliente) {
  Cliente aux = getClienteById(cliente.getId());
  if(aux != null){
    aux.setEndereco(cliente.getEndereco());
    aux.setNome(cliente.getNome());
    aux.setSaldo(cliente.getSaldo());
  }
  return aux;
}
}
//Tudo que manipula a lista ficou aqui