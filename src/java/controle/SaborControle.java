/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.SaborDAO;
import dao.SaborDAOImp;
import entidade.Sabor;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

/**
 *
 * @author Lucio
 */
@ManagedBean(name = "sabC")
@SessionScoped
public class SaborControle {
    
    private Sabor sabor;
    private SaborDAO sDAO;
    
    public Sabor getSabor() {
        if(sabor == null){
            sabor = new Sabor();
        }
        return sabor;
    }

    public void setSabor(Sabor Sabor) {
        this.sabor = Sabor;
    }
    
//#####################################################################################################################################
    
    public String salvar(){
        sDAO = new SaborDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (sabor.getId() == null) {
            sDAO.salva(sabor);
        }else {
            sDAO.altera(sabor);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sabor alterada com Sucesso!", ""));
        }
        return "admin.faces";        
    }
    
//#####################################################################################################################################
    
    private void limpar() {
        sabor = null;    
    }
    
//#####################################################################################################################################
    
public String limpaPesquisa() {
        sabor = null;        
        return "pesqSabor";
    }    
}

