/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.FuncaoDAOImp;
import dao.FuncaoDAO;
import dao.FuncaoDAOImp;
import entidade.Funcao;
import entidade.Funcao;
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
@ManagedBean(name = "funcC")
@SessionScoped
public class FuncaoControle {

    private Funcao funcao;
    private FuncaoDAO fDAO;
    private DataModel model;

    public Funcao getFuncao() {
        if (funcao == null) {
            funcao = new Funcao();
        }
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

//#####################################################################################################################################
    public String salvar() {
        fDAO = new FuncaoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (funcao.getId() == null) {
            try {
                fDAO.salva(funcao);
                context.addMessage(null, new FacesMessage("Sapore", "Função salva com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar Função!"));
            }

        } else {
            try {
                fDAO.altera(funcao);
                context.addMessage(null, new FacesMessage("Sapore", "Função alterada com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar função!"));
            }

        }
        limpar();
        return "cadFuncao.faces";
    }
    
//#####################################################################################################################################
    public String alterar() {

        funcao = (Funcao) model.getRowData();
        setFuncao(funcao);


        return "cadFuncao";
    }

//#####################################################################################################################################
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nome = funcao.getNome();
        try {
            fDAO = new FuncaoDAOImp();
            funcao = (Funcao) model.getRowData();
            fDAO.remove(funcao);
            model = new ListDataModel(fDAO.pesquisaLikeFuncao(nome));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcao excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";
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
    
//#####################################################################################################################################
    public String novaBorda() {
        limpar();
        funcao = new Funcao();
        return "cadFuncao";
    }

//#####################################################################################################################################
    public void pesquisaLikeNome() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
            fDAO = new FuncaoDAOImp();
        List<Funcao> funcoes = fDAO.pesquisaLikeFuncao(funcao.getNome());
        model = new ListDataModel(funcoes);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar pesquisar funcao!"));
        }
        
    }    
    
}
