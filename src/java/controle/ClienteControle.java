/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ClienteDAO;
import dao.ClienteDAOImp;
import dao.EnderecoDAO;
import dao.EnderecoDAOImp;
import dao.PessoaDAO;
import dao.PessoaDAOImp;
import dao.UsuarioDAO;
import dao.UsuarioDAOImp;
import entidade.Cliente;
import entidade.Endereco;
import entidade.Pessoa;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Aluno
 */
@ManagedBean(name = "clieC")
@SessionScoped
public class ClienteControle {

    private ClienteDAO cDAO;
    private PessoaDAO pDAO;
    private Endereco end;
    private Endereco end1;
    private Cliente cliente;
    private Pessoa pessoa;
    private DataModel model;

//#####################################################################################################################################    
    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteDAO getcDAO() {
        return cDAO;
    }

    public void setcDAO(ClienteDAO cDAO) {
        this.cDAO = cDAO;
    }

    public Endereco getEnd() {
        if (end == null) {
            end = new Endereco();
        }
        return end;
    }

    public void setEnd(Endereco end) {
        this.end = end;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public Endereco getEnd1() {
        if (end1 == null) {
            end1 = new Endereco();
        }
        return end1;
    }

    public void setEnd1(Endereco end1) {
        this.end1 = end1;
    }

//#####################################################################################################################################
    public String salvar() {
        cDAO = new ClienteDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (cliente == null) {
            context.addMessage(null, new FacesMessage("Sapore", "Preencha o formulário"));
        } else if (end == null && end1 == null) {
            context.addMessage(null, new FacesMessage("Sapore", "Preencha no mínimo um Endereço"));
        } else {
            if (cliente.getId() == null) {

                ArrayList<Endereco> enderecos = new ArrayList();
                enderecos.add(end);
                enderecos.add(end1);
                cliente.setEnderecos(enderecos);

                end.setPessoa(cliente);
                end1.setPessoa(cliente);

                try {
                    cDAO.salva(cliente);
                    context.addMessage(null, new FacesMessage("Sapore", "Cliente Salvo Com Sucesso!"));
                } catch (Exception e) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar"));
                    Logger.getLogger(ClienteControle.class.getName()).log(Level.SEVERE, null, e);
                }


            } else {

                ArrayList<Endereco> enderecos = new ArrayList();
                enderecos.add(end);
                enderecos.add(end1);
                cliente.setEnderecos(enderecos);

                end.setPessoa(cliente);

                try {
                    cDAO.altera(cliente);
                    context.addMessage(null, new FacesMessage("Sapore", "Cliente Alterado Com Sucesso!"));
                } catch (Exception e) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar alterar"));
                }
            }
        }
        limpar();
        return "admin.faces";
    }

//#####################################################################################################################################
    public void pesquisaLikeNome() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            cDAO = new ClienteDAOImp();
            List<Cliente> clientes = cDAO.pesquisaLikeNome(cliente.getNome());
            model = new ListDataModel(clientes);

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao pesquisar."));
        }

    }

//#####################################################################################################################################
    private void limpar() {
        cliente = null;
        end = null;
    }

//#####################################################################################################################################
    public String limpaPesquisa() {
        cliente = null;
        model = null;
        return "pesqCliente";
    }

//#####################################################################################################################################
    public String novoCliente() {
        limpar();
        cliente = new Cliente();
        return "cadCliente";
    }

//#####################################################################################################################################
    public String alterar() {
        FacesContext context = FacesContext.getCurrentInstance();
        cliente = (Cliente) model.getRowData();
        setCliente(cliente);

        try {
            EnderecoDAO eDAO = new EnderecoDAOImp();
            end = eDAO.pesquisaByIdColab(cliente.getId());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente alterado com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel alterar!", ""));
        }


        return "cadCliente";
    }

//#####################################################################################################################################
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nome = cliente.getNome();
        try {
            cDAO = new ClienteDAOImp();
            pDAO = new PessoaDAOImp();
            cliente = (Cliente) model.getRowData();
            //cliente = (Cliente) pessoa;
            //pDAO.remove(pessoa);
            cDAO.remove(cliente);
            model = new ListDataModel(cDAO.pesquisaLikeNome(nome));
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente excluído com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";
    }
}
