/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

/**
 *
 * @author Aluno
 */
@Entity
public class Perfil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nome;
    
    @ManyToMany
    @JoinTable(name="menu_perfil",
                joinColumns=@JoinColumn(name="id_perfil"),
                inverseJoinColumns=@JoinColumn(name="id_menu"))
    @IndexColumn(name="id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Menus> menus;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="acesso_perfil",
                joinColumns=@JoinColumn(name="id_perfil"),
                inverseJoinColumns=@JoinColumn(name="id_acesso"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Acessos> acessos;
    
    



    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Menus> getMenus() {
        return menus;
    }

    public void setMenus(List<Menus> menus) {
        this.menus = menus;
    }

    public List<Acessos> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<Acessos> acessos) {
        this.acessos = acessos;
    }



    

}
