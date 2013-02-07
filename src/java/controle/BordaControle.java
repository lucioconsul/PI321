/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.BordaDAO;
import dao.BordaDAOImp;
import entidade.Borda;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

/**
 *
 * @author Lucio
 */
@ManagedBean(name = "borC")
@SessionScoped
public class BordaControle {
    
    private Borda borda;
    private BordaDAO bDAO;
    
    public Borda getBorda() {
        if(borda == null){
            borda = new Borda();
        }
        return borda;
    }

    public void setBorda(Borda Borda) {
        this.borda = Borda;
    }
    
//#####################################################################################################################################
    
    public String salvar(){
        bDAO = new BordaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (borda.getId() == null) {
            bDAO.salva(borda);
        }else {
            bDAO.altera(borda);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Borda alterada com Sucesso!", ""));
        }
        return "admin.faces";        
    }
    
//#####################################################################################################################################
    
    private void limpar() {
        borda = null;    
    }
    
//#####################################################################################################################################
    
public String limpaPesquisa() {
        borda = null;        
        return "pesqBorda";
    }    
}

