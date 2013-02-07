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
 * @author user
 */
@Entity
public class Pizza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String Excecoes;
    
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "idBorda")
    private Borda borda;
    
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "idTamanho")
    private Tamanho tamanho;

    @OneToMany(mappedBy = "pizza")
    private List<Sabor> sabores;

    
    
    public List<Sabor> getSabores() {
        return sabores;
    }

    public void setSabores(List<Sabor> sabores) {
        this.sabores = sabores;
    }
    
    public Borda getBorda() {
        return borda;
    }

    public void setBorda(Borda borda) {
        this.borda = borda;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
    
    public String getExcecoes() {
        return Excecoes;
    }

    public void setExcecoes(String Excecoes) {
        this.Excecoes = Excecoes;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
