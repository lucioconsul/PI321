/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author Aluno
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)//define que essa é a classe PAI 
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private Boolean delivery;
    
    @Column(nullable = false)
    private String mesa;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date dia;
    
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date hora;
    
    @OneToOne
    private Colaborador atendente;
    
    @OneToOne
    private Colaborador entregador;
    
    @OneToOne
    private Cliente cliente;
    
    @ManyToMany
    @JoinTable(name="bebida_pedido",
                joinColumns=@JoinColumn(name="id_pedido"),
                inverseJoinColumns=@JoinColumn(name="id_bebida"))
    @IndexColumn(name="id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Bebida> bebidas;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Pizza> pizzas;

//#####################################################################################################################################    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(List<Bebida> bebidas) {
        this.bebidas = bebidas;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Colaborador getAtendente() {
        return atendente;
    }

    public void setAtendente(Colaborador atendente) {
        this.atendente = atendente;
    }

    public Colaborador getEntregador() {
        return entregador;
    }

    public void setEntregador(Colaborador entregador) {
        this.entregador = entregador;
    }


    
}
