/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.List;
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

/**
 *
 * @author Aluno
 */
@Entity
public class Menus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String label;
    private String url;
    private String icone;
    private String id_menu_pai;
    
    @ManyToMany
    @JoinTable(name="menu_perfil",
                joinColumns=@JoinColumn(name="id_menu"),
                inverseJoinColumns=@JoinColumn(name="id_perfil"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Perfil> perfis;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getId_menu_pai() {
        return id_menu_pai;
    }

    public void setId_menu_pai(String id_menu_pai) {
        this.id_menu_pai = id_menu_pai;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }
    

}
