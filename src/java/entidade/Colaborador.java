/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Cursos Livres
 */


@Entity
@PrimaryKeyJoinColumn(name = "idPessoa")// chave estrangeira pro join
public class Colaborador extends Pessoa implements Serializable{
    
    // static nao pertence ao objeto, compartilha a variavel // final é pq nunca mais vai mudar. 1L = 1 long
    private static final long serialVersionUID = 1L; 
    
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String rg;
    @Column(nullable = false)
    private String pis;
    @Column(nullable = false)
    private float salario;
    @Column(nullable = false)
    private int matricula;
    
    @OneToOne
    @JoinColumn(name="idFuncao") // assim a tabela pessoa vai ter coluna com id Funcao. tabela mais forte, melhor
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Funcao funcao;
   
    
    @OneToMany(mappedBy = "colaborador", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }


    
    
}
