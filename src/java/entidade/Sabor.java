/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author user
 */
@Entity
public class Sabor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private float valorM;
    private float valorP;
    private float valorG;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="idPizza")
    private Pizza pizza;
    

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValorG() {
        return valorG;
    }

    public void setValorG(float valorG) {
        this.valorG = valorG;
    }

    public float getValorM() {
        return valorM;
    }

    public void setValorM(float valorM) {
        this.valorM = valorM;
    }

    public float getValorP() {
        return valorP;
    }

    public void setValorP(float valorP) {
        this.valorP = valorP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
