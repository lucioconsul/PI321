/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.FuncaoDAO;
import dao.FuncaoDAOImp;
import entidade.Funcao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

/**
 *
 * @author Lucio
 */
@ManagedBean(name = "funcC")
@SessionScoped
public class FuncaoControle {
    
    private Funcao funcao;
    private FuncaoDAO fDAO;
    private DataModel model;

    public Funcao getFuncao() {
        if(funcao == null){
            funcao = new Funcao();
        }
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
    
//#####################################################################################################################################
    
    public String salvar(){
        fDAO = new FuncaoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (funcao.getId() == null) {
            fDAO.salva(funcao);
        }else {
            fDAO.altera(funcao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcao Alterado Com Sucesso!", ""));
        }
        return "admin.faces";        
    }
    
//#####################################################################################################################################
    
    private void limpar() {
        funcao = null;    
    }
    
//#####################################################################################################################################
    
public String limpaPesquisa() {
        funcao = null;
        model = null;
        return "pesqFuncao";
    }    
}

