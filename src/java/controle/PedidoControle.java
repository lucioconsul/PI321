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
import dao.EstoqueBebidaDAOImp;
import dao.EstoqueDAO;
import dao.PedidoDAO;
import dao.PedidoDAOImp;
import dao.PessoaDAO;
import dao.PessoaDAOImp;
import dao.PizzaDAO;
import dao.PizzaDAOImp;
import dao.SaborDAO;
import dao.SaborDAOImp;
import dao.TamanhoDAO;
import dao.TamanhoDAOImp;
import entidade.Bebida;
import entidade.Borda;
import entidade.Cliente;
import entidade.Colaborador;
import entidade.Endereco;
import entidade.EstoqueBebida;
import entidade.Pedido;
import entidade.Pessoa;
import entidade.Pizza;
import entidade.Sabor;
import entidade.Tamanho;
import entidade.Usuario;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

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
    private Cliente cliente;
    private String telefone;
    private Pessoa pessoa;
    private Endereco endereco;
    private EstoqueBebida estoque;
    private ArrayList<Bebida> bebidas;
    private ArrayList<Sabor> sabores;
    private ArrayList<Pizza> pizzas = new ArrayList();
    private List<Endereco> ends;
    private List<Tamanho> tamanhos;
    private PedidoDAO pDAO;
    private EnderecoDAO eDAO;
    private ClienteDAO cDAO;
    private PessoaDAO peDAO;
    private TamanhoDAO tDAO;
    private BordaDAO bDAO;
    private PizzaDAO piDAO;
    private ClienteDAO cliDAO;
    private EstoqueDAO estDAO;
    private DataModel modelSabor;
    private DataModel modelSaborTodos;
    private DataModel modelBebida;
    private DataModel modelBebidaTodas;
    private DataModel modelEnderecos;
    private DataModel modelPizza;
    private boolean clienteNovo = false;

//#####################################################################################################################################
    public String salvar() {

        FacesContext context = FacesContext.getCurrentInstance();
        //verifico se montou alguma pizza
        if(pizzas == null || pizzas.isEmpty()){
            context.addMessage(null, new FacesMessage("Sapore", "O pedido não foi salvo porque não contém pizzas"));
            return "";
        }
        pedido.setPizzas(pizzas);
        
        //verifico se pegou algum cliente
        if(cliente == null || cliente.getNome().equals("") || cliente.getTelefone().equals("")){
            context.addMessage(null, new FacesMessage("Sapore", "O pedido não foi salvo. Necessário vincular a um cliente"));
            return "";
            
        //se for cliente novo faço o cadastro:
        }else if(clienteNovo){
            //verifico campos obrigatorios
            if(cliente.getNome().equals("") || cliente.getTelefone().equals("") || endereco.getRua().equals("") || endereco.getBairro().equals("")){
                context.addMessage(null, new FacesMessage("Sapore", "O pedido não foi salvo. Cadastro de cliente incompleto"));
                return "";
            }else{
                cliDAO = new ClienteDAOImp();
                eDAO = new EnderecoDAOImp();
                endereco.setPessoa(cliente);
                ends = new ArrayList();
                ends.add(endereco);
                cliente.setEnderecos(ends);
                cliDAO.salva(cliente);
                eDAO.altera(endereco);
                context.addMessage(null, new FacesMessage("Sapore", "Cadastro de cliente salvo com sucesso!"));
            }
        }

        pedido.setCliente(cliente);
        
        //verifico se pegou um endereco
        if(endereco == null || endereco.getRua().equals("") || endereco.getBairro().equals("")){
            context.addMessage(null, new FacesMessage("Sapore", "O pedido não foi salvo. Necessário selecionar um endereço para entrega"));
            return "";
        }
        pedido.setEndereco(endereco);
        pedido.setDelivery(Boolean.TRUE);
        pedido.setDia(new Date());
        pedido.setHora(new Timestamp(System.currentTimeMillis()));
        pedido.setMesa("0");
        
        pedido.setBebidas(bebidas);
        //diminuir bebida do estoque
        if (bebidas != null) {
            estDAO = new EstoqueBebidaDAOImp();
            for (Bebida bebida1 : bebidas) {
                estoque = estDAO.pesquisaByBebida(bebida1);
                estoque.setQtd(estoque.getQtd() - 1);
                estDAO.altera(estoque);
            }
        }

        //pego sessão e seto o colaborador como atendente desde pedido
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuario usu = (Usuario) session.getAttribute("autenticado");
        pedido.setAtendente(usu.getColaborador());


        //salvo o pedido
        pDAO = new PedidoDAOImp();
        if (pedido.getId() == null) {
            pDAO.salva(pedido);
            context.addMessage(null, new FacesMessage("Sapore", "Pedido salvo com Sucesso!"));
        } else {
            pDAO.altera(pedido);
            context.addMessage(null, new FacesMessage("Sapore", "Pedido alterado com Sucesso!"));
        }

        //salvo as pizzas
        piDAO = new PizzaDAOImp();
        for (Pizza pizza1 : pizzas) {
            pizza1.setPedido(pedido);
            piDAO.salva(pizza1);
        }

        limpar();
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
        endereco = null;
        ends = null;
        pizza = null;
        pizzas = null;
        sabor = null;
        sabores = null;
        borda = null;
        tamanho = null;
        tamanhos = null;
        bebida = null;
        bebidas = null;
        cliente = null;
        pessoa = null;
        modelSabor = null;
        modelSaborTodos = null;
        modelBebida = null;
        modelBebidaTodas = null;
        modelEnderecos = null;
        modelPizza = null;

    }

//#####################################################################################################################################
    private void limparEndereco() {
        endereco = null;
    }

//#####################################################################################################################################
    private void limparCliente() {
        cliente = null;
    }

//#####################################################################################################################################
    private void limparPessoa() {
        pessoa = null;
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
        tamanhos = tdao.getTodos();
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
        List<Bebida> bebidasl = bdao.pesquisaPorEstoque();
        modelBebidaTodas = new ListDataModel(bebidasl);
    }

//#####################################################################################################################################
    public void escolherSabor() {
        sabor = (Sabor) modelSaborTodos.getRowData();
        setSabor(sabor);
        if (sabores == null) {
            sabores = new ArrayList();
        } else if (sabores.size() >= 3) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Hey", "Máximo 3 sabores por pizza!"));
            return;
        }
        sabores.add(sabor);
        modelSabor = new ListDataModel(sabores);

    }

//#####################################################################################################################################
    public void escolherBebida() {
        bebida = (Bebida) modelBebidaTodas.getRowData();
        setBebida(bebida);
        if (bebidas == null) {
            bebidas = new ArrayList();
        }
        bebidas.add(bebida);
        modelBebida = new ListDataModel(bebidas);

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
    public String excluiPizza() {
        pizza = (Pizza) modelPizza.getRowData();
        setPizza(pizza);
        int x = 0;
        for (Pizza pi : pizzas) {
            if (pi.getId() == pizza.getId()) {
                x = bebidas.indexOf(pi);
            }
        }
        pizzas.remove(x);
        return "";
    }

//#####################################################################################################################################
    public String encontraCliente() {
        cDAO = new ClienteDAOImp();
        eDAO = new EnderecoDAOImp();
        clienteNovo = false;
        if (!"".equals(telefone)){
            cliente = cDAO.getByTelefone(telefone);
            if (cliente == null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Sapore", "Cliente não cadastrado. Fazer cadastro"));
                clienteNovo = true;
                cliente = new Cliente();
                endereco = new Endereco();
                cliente.setTelefone(telefone);
                return "";
            }
            ends = eDAO.pesquisaByIdPessoa(cliente.getId());
            cliente.setEnderecos(ends);
            modelEnderecos = new ListDataModel(ends);
            setModelEnderecos(modelEnderecos);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Sapore", "Digite o telefone do cliente."));
            return "";
        }
        telefone = "";
        return "";
    }

//#####################################################################################################################################    
    public void escolherEndereco() {
        endereco = new Endereco();
        endereco = (Endereco) modelEnderecos.getRowData();
        setEndereco(endereco);
    }

//#####################################################################################################################################    
    public void adicionaPizza() {

        setPizza(pizza);
        if (pizza == null) {
            pizza = new Pizza();
        }
        if (tamanho == null) {
            tamanho = new Tamanho();
        } else {
            tDAO = new TamanhoDAOImp();
            tamanho = tDAO.pesquisa_Por_Id(tamanho.getId());
        }
        pizza.setTamanho(tamanho);
        if (borda == null) {
            borda = new Borda();
        } else {
            bDAO = new BordaDAOImp();
            borda = bDAO.pesquisa_Por_Id(borda.getId());
        }
        pizza.setBorda(borda);
        if (pedido == null) {
            pedido = new Pedido();
        }
        pizza.setPedido(pedido);
        if (sabores.size() >= 1) {
            pizza.setSabor1(sabores.get(0));
            if (sabores.size() >= 2) {
                pizza.setSabor2(sabores.get(1));
                if (sabores.size() == 3) {
                    pizza.setSabor3(sabores.get(2));
                }
            }
        }

        pizzas.add(pizza);

        modelPizza = new ListDataModel(pizzas);

    }

//#####################################################################################################################################    
    public String mostraSabor(int x) {

        return sabores.get(x).getNome();
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
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa getPessoa() {
        if (pessoa == null) {
            pessoa = new Pessoa();
        }
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getTelefone() {
        if (telefone == null) {
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

    public DataModel getModelPizza() {
        return modelPizza;
    }

    public void setModelPizza(DataModel modelPizza) {
        this.modelPizza = modelPizza;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Tamanho> getTamanhos() {
        getComboTamanho();
        return tamanhos;
    }

    public void setTamanhos(List<Tamanho> tamanhos) {
        this.tamanhos = tamanhos;
    }

    public EstoqueBebida getEstoque() {
        return estoque;
    }

    public void setEstoque(EstoqueBebida estoque) {
        this.estoque = estoque;
    }
}
