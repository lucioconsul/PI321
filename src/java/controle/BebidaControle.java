/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.BebidaDAO;
import dao.BebidaDAOImp;
import dao.EstoqueBebidaDAOImp;
import dao.EstoqueDAO;
import entidade.Bebida;
import entidade.EstoqueBebida;
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
    private EstoqueBebida estoq;
    private DataModel model;
    private String combo;
    private String atuali;

//#####################################################################################################################################
    public Bebida getBebida() {
        if (bebida == null) {
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

    public EstoqueBebida getEstoq() {
        if (estoq == null) {
            estoq = new EstoqueBebida();
        }
        return estoq;
    }

    public void setEstoq(EstoqueBebida estoq) {
        this.estoq = estoq;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public String getAtuali() {
        return atuali;
    }

    public void setAtuali(String atuali) {
        this.atuali = atuali;
    }

//#####################################################################################################################################
    public String salvar() {
        bDAO = new BebidaDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (bebida.getId() == null) {
            try {
                bDAO.salva(bebida);
                context.addMessage(null, new FacesMessage("Sapore", "Bebida salva com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar!"));
            }

        } else {
            try {
                bDAO.altera(bebida);
                context.addMessage(null, new FacesMessage("Sapore", "Bebida alterada com sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar alterar!"));
            }

        }
        limpar();
        return "cad_bebida.faces";
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
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bebida excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";
    }

//#####################################################################################################################################
    private void limpar() {
        bebida = null;
        estoq = null;
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
        return "cad_bebida";
    }

//#####################################################################################################################################
    public void pesquisaLikeNome() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            bDAO = new BebidaDAOImp();
            List<Bebida> bebidas = bDAO.pesquisaLikeBebida(bebida.getNome());
            model = new ListDataModel(bebidas);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar pesquisar!"));
        }

    }

//#####################################################################################################################################
    public String estoque() {
        FacesContext context = FacesContext.getCurrentInstance();
        bebida = (Bebida) model.getRowData();
        setBebida(bebida);
        try {
            EstoqueDAO eDAO = new EstoqueBebidaDAOImp();
            estoq = eDAO.pesquisaByBebida(bebida);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar pesquisar estoque!"));
        }
        return "bebida_estoque";
    }

//#####################################################################################################################################
    public String atualizaEstoque() {
        FacesContext context = FacesContext.getCurrentInstance();

        int n = estoq.getQtd() + Integer.parseInt(atuali);

        estoq.setQtd(n);
        estoq.setBebida(bebida);
        EstoqueDAO eDAO = new EstoqueBebidaDAOImp();
        try {
            if (estoq.getId() == null) {
                eDAO.salva(estoq);
                context.addMessage(null, new FacesMessage("Sapore", "Estoque atualizado com sucesso!"));
            } else {
                eDAO.altera(estoq);
                context.addMessage(null, new FacesMessage("Sapore", "Estoque atualizado com sucesso!"));
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar atualizar estoque!"));
        }

        limpar();
        setAtuali("");
        return "";
    }
}
