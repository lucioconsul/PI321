/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.BebidaDAO;
import dao.BebidaDAOImp;
import dao.BordaDAO;
import dao.BordaDAOImp;
import dao.ClienteDAO;
import dao.ClienteDAOImp;
import dao.EnderecoDAO;
import dao.EnderecoDAOImp;
import dao.PedidoDAO;
import dao.PedidoDAOImp;
import dao.PessoaDAO;
import dao.PessoaDAOImp;
import dao.SaborDAO;
import dao.SaborDAOImp;
import dao.TamanhoDAO;
import dao.TamanhoDAOImp;
import entidade.Bebida;
import entidade.Borda;
import entidade.Cliente;
import entidade.Endereco;
import entidade.Pedido;
import entidade.Pessoa;
import entidade.Pizza;
import entidade.Sabor;
import entidade.Tamanho;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author Lucio
 */
@ManagedBean(name = "pedC")
@SessionScoped
public class PedidoControle {

    private Pedido pedido;
    private Pizza pizza;
    private Tamanho tamanho;
    private Borda borda;
    private Sabor sabor;
    private Bebida bebida;
    private ArrayList<Bebida> bebidas;
    private ArrayList<Sabor> sabores;
    private List<Endereco> ends;
    private PedidoDAO pDAO;
    private Cliente cliente;
    private Pessoa pessoa;
    private Endereco endereco;
    private EnderecoDAO eDAO;
    private ClienteDAO cDAO;
    private PessoaDAO peDAO;
    private DataModel modelSabor;
    private DataModel modelSaborTodos;
    private DataModel modelBebida;
    private DataModel modelBebidaTodas;
    private DataModel modelEnderecos;
    private String telefone;

//#####################################################################################################################################
    public String salvar() {
        pDAO = new PedidoDAOImp();
        FacesContext context = FacesContext.getCurrentInstance();
        if (pedido.getId() == null) {
            pDAO.salva(pedido);
        } else {
            pDAO.altera(pedido);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido alterado com Sucesso!", ""));
        }
        return "admin.faces";
    }

//#####################################################################################################################################
    public String alterar() {
        pedido = (Pedido) modelSabor.getRowData();
        setPedido(pedido);

        return "cadPedido";
    }

//#####################################################################################################################################
    public String excluir() {
        FacesContext context = FacesContext.getCurrentInstance();
        Long id = pedido.getId();
        try {
            pDAO = new PedidoDAOImp();
            pedido = (Pedido) modelSabor.getRowData();
            pDAO.remove(pedido);
            // aqui teria que atualizar o model pesqusando pelo nome pra vir vazio
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido excluída com sucesso!", ""));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possivel exclusão!", ""));
        }
        limpar();
        return "";
    }

//#####################################################################################################################################
    private void limpar() {
        pedido = null;
    }

//#####################################################################################################################################
    public String limpaPesquisa() {
        pedido = null;
        return "pesqPedido";
    }

//#####################################################################################################################################
    public String novaPedido() {
        limpar();
        pedido = new Pedido();
        return "cadPedido";
    }

//#####################################################################################################################################
    public List<SelectItem> getComboTamanho() {
        TamanhoDAO tdao = new TamanhoDAOImp();
        List<Tamanho> tamanhos = tdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Tamanho tam : tamanhos) {
            listaCombo.add(new SelectItem(tam.getId(), tam.getNome()));
        }
        return listaCombo;
    }

//#####################################################################################################################################
    public List<SelectItem> getComboBorda() {
        BordaDAO tdao = new BordaDAOImp();
        List<Borda> bordas = tdao.getTodos();
        List<SelectItem> listaCombo = new ArrayList<SelectItem>();
        for (Borda bor : bordas) {
            listaCombo.add(new SelectItem(bor.getId(), bor.getNome()));
        }
        return listaCombo;
    }

//#####################################################################################################################################
    public void getTodosSabores() {
        SaborDAO sdao = new SaborDAOImp();
        List<Sabor> saboresl = sdao.getTodos();
        modelSaborTodos = new ListDataModel(saboresl);
    }
    
//#####################################################################################################################################
    public void getTodasBebidas() {
        BebidaDAO bdao = new BebidaDAOImp();
        List<Bebida> bebidasl = bdao.getTodos();
        modelBebidaTodas = new ListDataModel(bebidasl);
    }    

//#####################################################################################################################################
    public String escolherSabor() {
        sabor = (Sabor) modelSaborTodos.getRowData();
        setSabor(sabor);
        if(sabores == null){
            sabores = new ArrayList();
        }
        sabores.add(sabor);
        modelSabor = new ListDataModel(sabores);

        return "";

    }
    
//#####################################################################################################################################
    public String escolherBebida() {
        bebida = (Bebida) modelBebidaTodas.getRowData();
        setBebida(bebida);
        if(bebidas == null){
            bebidas = new ArrayList();
        }
        bebidas.add(bebida);
        modelBebida = new ListDataModel(bebidas);

        return "";

    }    

//#####################################################################################################################################
    public String excluiSabor() {
        sabor = (Sabor) modelSabor.getRowData();
        setSabor(sabor);
        int x = 0;
        for (Sabor sab : sabores) {
            if (sab.getId() == sabor.getId()) {
                x = sabores.indexOf(sab);
            }
        }
        sabores.remove(x);
        return "";
    }
    
//#####################################################################################################################################
    public String excluiBebida() {
        bebida = (Bebida) modelBebida.getRowData();
        setBebida(bebida);
        int x = 0;
        for (Bebida beb : bebidas) {
            if (beb.getId() == bebida.getId()) {
                x = bebidas.indexOf(beb);
            }
        }
        bebidas.remove(x);
        return "";
    }    
    
//#####################################################################################################################################
    public void encontraCliente() {
        peDAO = new PessoaDAOImp();
        eDAO = new EnderecoDAOImp();
        pessoa = peDAO.getByTelefone(telefone);
        ends = eDAO.pesquisaByIdPessoa(pessoa.getId());
        pessoa.setEnderecos(ends);
        modelEnderecos = new ListDataModel(ends);
        setModelEnderecos(modelEnderecos);
    }     

//#####################################################################################################################################    
    public String escolherEndereco() {
        endereco = (Endereco) modelEnderecos.getRowData();
        setEndereco(endereco);
        return "";
    }      
    
    
    
//#####################################################################################################################################            
    public DataModel getModelSabor() {
        return modelSabor;
    }

    public void setModelSabor(DataModel modelSabor) {
        this.modelSabor = modelSabor;
    }

    public Pedido getPedido() {
        if (pedido == null) {
            pedido = new Pedido();
        }
        return pedido;
    }

    public void setPedido(Pedido Pedido) {
        this.pedido = Pedido;
    }

    public Pizza getPizza() {
        if (pizza == null) {
            pizza = new Pizza();
        }
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Tamanho getTamanho() {
        if (tamanho == null) {
            tamanho = new Tamanho();
        }
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Borda getBorda() {
        if (borda == null) {
            borda = new Borda();
        }
        return borda;
    }

    public void setBorda(Borda borda) {
        this.borda = borda;
    }

    public Sabor getSabor() {
        if (sabor == null) {
            sabor = new Sabor();
        }
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public DataModel getModelSaborTodos() {
        getTodosSabores();
        return modelSaborTodos;
    }

    public void setModelSaborTodos(DataModel modelSaborTodos) {
        this.modelSaborTodos = modelSaborTodos;
    }

    public ArrayList<Sabor> getSabores() {
        return sabores;
    }

    public void setSabores(ArrayList<Sabor> sabores) {
        this.sabores = sabores;
    }

    public DataModel getModelBebida() {
        return modelBebida;
    }

    public void setModelBebida(DataModel modelBebida) {
        this.modelBebida = modelBebida;
    }

    public DataModel getModelBebidaTodas() {
        getTodasBebidas();
        return modelBebidaTodas;
    }

    public void setModelBebidaTodas(DataModel modelBebidaTodas) {
        this.modelBebidaTodas = modelBebidaTodas;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public ArrayList<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Bebida> bebidas) {
        this.bebidas = bebidas;
    }

    public Cliente getCliente() {
        if(cliente == null){
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        if(endereco == null){
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa getPessoa() {
        if(pessoa == null){
            pessoa = new Pessoa();
        }
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTelefone() {
        if(telefone == null){
            telefone = new String();
        }
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEnds() {
        return ends;
    }

    public void setEnds(List<Endereco> ends) {
        this.ends = ends;
    }

    public DataModel getModelEnderecos() {
        return modelEnderecos;
    }

    public void setModelEnderecos(DataModel modelEnderecos) {
        this.modelEnderecos = modelEnderecos;
    }
}
