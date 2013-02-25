/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.SaborDAO;
import dao.SaborDAOImp;
import entidade.Sabor;
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
@ManagedBean(name = "sabC")
@SessionScoped
public class SaborControle {

    private Sabor sabor;
    private SaborDAO sDAO;
    private DataModel model;

//#####################################################################################################################################
    public Sabor getSabor() {
        if (sabor == null) {
            sabor = new Sabor();
        }
        return sabor;
    }

    public void setSabor(Sabor Sabor) {
        this.sabor = Sabor;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

//#####################################################################################################################################
    public String salvar() {
        sDAO = new SaborDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (sabor.getId() == null) {
            try {
                sDAO.salva(sabor);
                context.addMessage(null, new FacesMessage("Sapore", "Sabor salvo com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar Sabor!"));
            }

        } else {
            try {
                sDAO.altera(sabor);
                context.addMessage(null, new FacesMessage("Sapore", "Sabor alterado com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar alterar Sabor!"));
            }

        }
        return "admin.faces";
    }

//#####################################################################################################################################
    public String alterar() {
        sabor = (Sabor) model.getRowData();
        setSabor(sabor);

        return "cad_sabor";
    }

//#####################################################################################################################################
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nome = sabor.getNome();
        try {
            sDAO = new SaborDAOImp();
            sabor = (Sabor) model.getRowData();
            sDAO.remove(sabor);
            model = new ListDataModel(sDAO.pesquisaLikeSabor(nome));
            context.addMessage(null, new FacesMessage("Sapore", "Sabor excluído com sucesso!"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";
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

//#####################################################################################################################################
    public String novoSabor() {
        limpar();
        sabor = new Sabor();
        return "cadSabor";
    }

//#####################################################################################################################################
    public void pesquisaLikeNome() {
        FacesContext context = FacesContext.getCurrentInstance();
        sDAO = new SaborDAOImp();
        try {
            List<Sabor> sabores = sDAO.pesquisaLikeSabor(sabor.getNome());
            model = new ListDataModel(sabores);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Ocorreu um erro ao tentar pesquisar os sabores!"));
        }

    }
}
