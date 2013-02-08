/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.BordaDAOImp;
import dao.BebidaDAOImp;
import dao.BebidaDAO;
import dao.BebidaDAOImp;
import entidade.Borda;

import entidade.Bebida;
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
@ManagedBean(name = "bebC")
@SessionScoped
public class BebidaControle {
    
    private Bebida bebida;
    private BebidaDAO bDAO;
    private DataModel model;
    
//#####################################################################################################################################
    
    public Bebida getBebida() {
        if(bebida == null){
            bebida = new Bebida();
        }
        return bebida;
    }

    public void setBebida(Bebida Bebida) {
        this.bebida = Bebida;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }
    
//#####################################################################################################################################
    
    public String salvar(){
        bDAO = new BebidaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (bebida.getId() == null) {
            bDAO.salva(bebida);
        }else {
            bDAO.altera(bebida);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bebida alterada com Sucesso!", ""));
        }
        return "admin.faces";        
    }
    
//#####################################################################################################################################
    
        public String alterar() {
        bebida = (Bebida) model.getRowData();                
        setBebida(bebida);        
        
        return "cad_bebida";
    }  
        
//#####################################################################################################################################
        
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nome = bebida.getNome();
        try {
            bDAO = new BebidaDAOImp();
            bebida = (Bebida) model.getRowData();            
            bDAO.remove(bebida);
            model = new ListDataModel(bDAO.pesquisaLikeBebida(nome));
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Bebida excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";
    }        
    
//#####################################################################################################################################
    
    private void limpar() {
        bebida = null;    
    }
    
//#####################################################################################################################################
    
public String limpaPesquisa() {
        bebida = null;        
        return "pesqBebida";
    }    

//#####################################################################################################################################
    
    public String novaBebida() {
        limpar();
        bebida = new Bebida();
        return "cadBebida";
    } 
    
//#####################################################################################################################################
    
      public void pesquisaLikeNome() {
        bDAO = new BebidaDAOImp();
        List<Bebida> bebidas = bDAO.pesquisaLikeBebida(bebida.getNome());
        model = new ListDataModel(bebidas);
    }       
}

