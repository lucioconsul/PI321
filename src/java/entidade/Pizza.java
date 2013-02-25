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
    
    @ManyToOne
    @JoinColumn(name="idPedido")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Pedido pedido;

    @OneToOne
    private Sabor sabor1;
    
    @OneToOne
    private Sabor sabor2;
    
    @OneToOne
    private Sabor sabor3;

//#####################################################################################################################################    
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public Sabor getSabor1() {
        return sabor1;
    }

    public void setSabor1(Sabor sabor1) {
        this.sabor1 = sabor1;
    }

    public Sabor getSabor2() {
        return sabor2;
    }

    public void setSabor2(Sabor sabor2) {
        this.sabor2 = sabor2;
    }

    public Sabor getSabor3() {
        return sabor3;
    }

    public void setSabor3(Sabor sabor3) {
        this.sabor3 = sabor3;
    }
}
