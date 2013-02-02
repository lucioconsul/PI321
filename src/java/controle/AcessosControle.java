/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.AcessosDAO;
import dao.AcessosDAOImp;
import dao.UsuarioDAO;
import dao.UsuarioDAOImp;
import entidade.Acessos;
import entidade.Usuario;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aluno
 */
@ManagedBean(name = "aceC")
@SessionScoped
public class AcessosControle {

    private Usuario usu;
    private Acessos ace;
    private UsuarioDAO udao;
    private AcessosDAO adao;
//##############################################################################    

    public Usuario getUsu() {
        if (usu == null) {
            usu = new Usuario();
        }
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public UsuarioDAO getUdao() {
        return udao;
    }

    public void setUdao(UsuarioDAO udao) {
        this.udao = udao;
    }

    public AcessosDAO getAdao() {
        return adao;
    }

    public void setAdao(AcessosDAO adao) {
        this.adao = adao;
    }

    public Acessos getAce() {
        if (ace == null) {
            ace = new Acessos();
        }
        return ace;
    }

    public void setAce(Acessos ace) {
        this.ace = ace;
    }

//##############################################################################    
//    public String pesquisaAcesso(String pagina, Usuario usu) {
//        
//        String retorno = pagina;
//        adao = new AcessosDAOImp();
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        if (usu == null) {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Sem permisão!"));
//            limpar();
//            return "index.faces";
//        } else {
//            ace = adao.pesquisaAcesso(usu.getPerfil(), pagina);
//            if (ace == null) {
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Sem permisão!"));
//                limpar();
//               return "index.faces";
//            }
//        }
//        return retorno;
//    }
//##############################################################################        

    private void limpar() {
        usu = null;
        ace = null;
    }
}
