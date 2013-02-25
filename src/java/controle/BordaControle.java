/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.BordaDAO;
import dao.BordaDAOImp;
import entidade.Borda;
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
@ManagedBean(name = "bordC")
@SessionScoped
public class BordaControle {

    private Borda borda;
    private BordaDAO bDAO;
    private DataModel model;

//#####################################################################################################################################
    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Borda getBorda() {
        if (borda == null) {
            borda = new Borda();
        }
        return borda;
    }

    public void setBorda(Borda Borda) {
        this.borda = Borda;
    }

//#####################################################################################################################################
    public String salvar() {
        bDAO = new BordaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (borda.getId() == null) {
            try {
                bDAO.salva(borda);
                context.addMessage(null, new FacesMessage("Sapore", "Borda salva com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar borda!"));
            }

        } else {
            try {
                bDAO.altera(borda);
                context.addMessage(null, new FacesMessage("Sapore", "Borda alterada com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar alterar borda!"));
            }

        }
        limpar();
        return "borda_cad.faces";
    }

//#####################################################################################################################################
    public String alterar() {

        borda = (Borda) model.getRowData();
        setBorda(borda);

        return "borda_cad";
    }

//#####################################################################################################################################
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nome = borda.getNome();
        try {
            bDAO = new BordaDAOImp();
            borda = (Borda) model.getRowData();
            bDAO.remove(borda);
            model = new ListDataModel(bDAO.pesquisaLikeBorda(nome));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Borda excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";
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

//#####################################################################################################################################
    public void pesquisaLikeNome() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
            bDAO = new BordaDAOImp();
        List<Borda> bordas = bDAO.pesquisaLikeBorda(borda.getNome());
        model = new ListDataModel(bordas);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar pesquisar borda!"));
        }
        
    }

//#####################################################################################################################################
    public String novaBorda() {
        limpar();
        borda = new Borda();
        return "borda_cad";
    }
}
