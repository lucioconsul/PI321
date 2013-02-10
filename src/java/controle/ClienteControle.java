/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ClienteDAO;
import dao.ClienteDAOImp;
import dao.EnderecoDAO;
import dao.EnderecoDAOImp;
import dao.UsuarioDAO;
import dao.UsuarioDAOImp;
import entidade.Cliente;
import entidade.Endereco;
import java.util.ArrayList;
import java.util.List;
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
    private Endereco end;
    private Cliente cliente;
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

//#####################################################################################################################################
   
    public String salvar() {
        cDAO = new ClienteDAOImp();
        
        FacesContext context = FacesContext.getCurrentInstance();
        if (cliente.getId() == null) {

            ArrayList<Endereco> enderecos = new ArrayList();
            enderecos.add(end);
            cliente.setEnderecos(enderecos);

            cliente.setEnderecos(enderecos);

            end.setPessoa(cliente);

            cDAO.salva(cliente);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Salvo Com Sucesso!", ""));
        } else {
            cDAO.altera(cliente);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Alterado Com Sucesso!", ""));
        }
        limpar();
        return "admin.faces";
    }

//#####################################################################################################################################
   
    public void pesquisaLikeNome() {
        cDAO = new ClienteDAOImp();
        List<Cliente> clientes = cDAO.pesquisaLikeNome(cliente.getNome());
        model = new ListDataModel(clientes);
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
        cliente = (Cliente) model.getRowData();
        setCliente(cliente);
        
        UsuarioDAO uDAO = new UsuarioDAOImp();
        
        EnderecoDAO eDAO = new EnderecoDAOImp();
        end = eDAO.pesquisaByIdColab(cliente.getId());

        return "cadCliente";
    }

//#####################################################################################################################################
   
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nome = cliente.getNome();
        try {
            cDAO = new ClienteDAOImp();
            cliente = (Cliente) model.getRowData();
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
