/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.TamanhoDAO;
import dao.TamanhoDAOImp;
import entidade.Tamanho;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

/**
 *
 * @author Lucio
 */
@ManagedBean(name = "tamC")
@SessionScoped
public class TamanhoControle {
    
    private Tamanho tamanho;
    private TamanhoDAO tDAO;
    
    public Tamanho getTamanho() {
        if(tamanho == null){
            tamanho = new Tamanho();
        }
        return tamanho;
    }

    public void setTamanho(Tamanho Tamanho) {
        this.tamanho = Tamanho;
    }
    
//#####################################################################################################################################
    
    public String salvar(){
        tDAO = new TamanhoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (tamanho.getId() == null) {
            tDAO.salva(tamanho);
        }else {
            tDAO.altera(tamanho);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tamanho alterada com Sucesso!", ""));
        }
        return "admin.faces";        
    }
    
//#####################################################################################################################################
    
    private void limpar() {
        tamanho = null;    
    }
    
//#####################################################################################################################################
    
public String limpaPesquisa() {
        tamanho = null;        
        return "pesqTamanho";
    }    
}

