
package classededados;

import java.util.ArrayList;
import java.util.List;


public class Cliente {
  
    private String nome;
    
    private long cpf;
    
      
    private int id_cliente;

    private List<Contato> contatos = new ArrayList<>();
    
  
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

   
    public int getId() {
        return id_cliente;
    }

    public void setId(int id_Cliente) {
        this.id_cliente = id_Cliente;
    }

    public List<Contato> getContatos() {
        return contatos;
    }
    
}
