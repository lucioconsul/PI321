/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.IndexColumn;

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
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="idPedido")
    private Pedido pedido;

    @ManyToMany
    @JoinTable(name="sabor_pizza",
                joinColumns=@JoinColumn(name="id_pizza"),
                inverseJoinColumns=@JoinColumn(name="id_sabor"))
    @IndexColumn(name="id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Sabor> sabores;

//#####################################################################################################################################    
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

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
