/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.TamanhoDAOImp;
import dao.TamanhoDAO;
import dao.TamanhoDAOImp;
import entidade.Tamanho;
import entidade.Tamanho;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Lucio
 */
@ManagedBean(name = "tamC")
@SessionScoped
public class TamanhoControle {
    
    private Tamanho tamanho;
    private TamanhoDAO tDAO;
    private DataModel model;
    
    public Tamanho getTamanho() {
        if(tamanho == null){
            tamanho = new Tamanho();
        }
        return tamanho;
    }

    public void setTamanho(Tamanho Tamanho) {
        this.tamanho = Tamanho;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
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
    
        public String alterar() {
        tamanho = (Tamanho) model.getRowData();                
        setTamanho(tamanho);        
        
        return "cad_tamanho";
    }  
        
//#####################################################################################################################################
        
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nome = tamanho.getNome();
        try {
            tDAO = new TamanhoDAOImp();
            tamanho = (Tamanho) model.getRowData();            
            tDAO.remove(tamanho);
            model = new ListDataModel(tDAO.pesquisaLikeTamanho(nome));
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Tamanho excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";
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

//#####################################################################################################################################
    
    public String novoTamanho() {
        limpar();
        tamanho = new Tamanho();
        return "cadTamanho";
    } 
    
//#####################################################################################################################################
    
      public void pesquisaLikeNome() {
        tDAO = new TamanhoDAOImp();
        List<Tamanho> tamanhoes = tDAO.pesquisaLikeTamanho(tamanho.getNome());
        model = new ListDataModel(tamanhoes);
    }      
}

