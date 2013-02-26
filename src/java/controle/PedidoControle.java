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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.model.LazyDataModel;

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
    private BebidaDAO beDAO;
    private DataModel modelSabor;
    private DataModel modelSaborTodos;
    private DataModel modelBebida;
    private DataModel modelBebidaTodas;
    private DataModel modelEnderecos;
    private DataModel modelPizza;
    private DataModel modelPizzaPedido;
    private DataModel modelPedido;
    private boolean clienteNovo = false;
    private float teste;

//#####################################################################################################################################
    public String salvar() {

        FacesContext context = FacesContext.getCurrentInstance();
        //verifico se montou alguma pizza
        if (pizzas == null || pizzas.isEmpty()) {
            context.addMessage(null, new FacesMessage("Sapore", "O pedido não foi salvo porque não contém pizzas"));
            return "";
        }

        if (pedido == null) {
            pedido = new Pedido();
        }

        pedido.setPizzas(pizzas);

        //verifico se pegou algum cliente
        if (cliente == null || cliente.getNome().equals("") || cliente.getTelefone().equals("")) {
            context.addMessage(null, new FacesMessage("Sapore", "O pedido não foi salvo. Necessário vincular a um cliente"));
            return "";

            //se for cliente novo faço o cadastro:
        } else if (clienteNovo) {
            //verifico campos obrigatorios
            if (cliente.getNome().equals("") || cliente.getTelefone().equals("") || endereco.getRua().equals("") || endereco.getBairro().equals("")) {
                context.addMessage(null, new FacesMessage("Sapore", "O pedido não foi salvo. Cadastro de cliente incompleto"));
                return "";
            } else {
                cliDAO = new ClienteDAOImp();
                eDAO = new EnderecoDAOImp();
                endereco.setPessoa(cliente);
                ends = new ArrayList();
                ends.add(endereco);
                cliente.setEnderecos(ends);
                try {
                    cliDAO.salva(cliente);
                    eDAO.altera(endereco);
                    context.addMessage(null, new FacesMessage("Sapore", "Cadastro de cliente salvo com sucesso!"));
                } catch (Exception e) {
                    context.addMessage(null, new FacesMessage("Sapore", "Ocorreu um erro no cadastro do cliente. Favor verificar!"));
                }
            }
        }

        pedido.setCliente(cliente);

        //verifico se pegou um endereco
        if (endereco == null || endereco.getRua().equals("") || endereco.getBairro().equals("")) {
            context.addMessage(null, new FacesMessage("Sapore", "O pedido não foi salvo. Necessário selecionar um endereço para entrega"));
            return "";
        }
        pedido.setEndereco(endereco);
        pedido.setDelivery(true);
        pedido.setDia(new Date());
        pedido.setHora(new Timestamp(System.currentTimeMillis()));
        pedido.setMesa("0");
        pedido.setStatus("aguardando");

        pedido.setBebidas(bebidas);
        //diminuir bebida do estoque
        if (bebidas != null) {
            estDAO = new EstoqueBebidaDAOImp();
            for (Bebida bebida1 : bebidas) {
                try {
                    estoque = null;
                    estoque = new EstoqueBebida();
                    estoque = estDAO.pesquisaByBebida(bebida1);
                    estoque.setQtd(estoque.getQtd() - 1);
                    estDAO.altera(estoque);
                } catch (Exception e) {
                    context.addMessage(null, new FacesMessage("Sapore", "Ocorreu um erro na atualização do estoque de bebidas. Informar TI."));
                    Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        //pego sessão e seto o colaborador como atendente desde pedido
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            Usuario usu = (Usuario) session.getAttribute("autenticado");
            pedido.setAtendente(usu.getColaborador());
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Sapore", "Ocorreu um erro na autenticação do colaborador ao fazer o pedido. Informar TI."));
        }

        //salvo o pedido
        pDAO = new PedidoDAOImp();
        if (pedido.getId() == null) {
            try {
                pDAO.salva(pedido);
                context.addMessage(null, new FacesMessage("Sapore", "Pedido salvo com Sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar o Pedido!"));
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, e);
            }

        } else {
            try {
                pDAO.altera(pedido);
                context.addMessage(null, new FacesMessage("Sapore", "Pedido alterado com Sucesso!"));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar alterar o Pedido!"));
                Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, e);
            }

        }

        //salvo as pizzas
        /*
         piDAO = new PizzaDAOImp();
         for (Pizza pizza1 : pizzas) {
         pizza1.setPedido(pedido);
         try {
         piDAO.salva(pizza1);
         } catch (Exception e) {
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar as pizzas para este pedido!"));
         }
         }*/

        limpar();
        return "cadPedido.faces";
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
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido excluído com sucesso!", ""));
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
    private void limparPizza() {
        pizza = null;
    }

//#####################################################################################################################################
    private void limparSabores() {
        sabores = null;
    }

//#####################################################################################################################################
    public String limpaPesquisa() {
        pedido = null;
        return "pesqPedido";
    }

//#####################################################################################################################################
    public List<SelectItem> getComboTamanho() {
        FacesContext context = FacesContext.getCurrentInstance();
        TamanhoDAO tdao = new TamanhoDAOImp();
        try {
            tamanhos = tdao.getTodos();
            List<SelectItem> listaCombo = new ArrayList<SelectItem>();
            for (Tamanho tam : tamanhos) {
                listaCombo.add(new SelectItem(tam.getId(), tam.getNome()));
            }
            return listaCombo;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Ocorreu um erro ao tentar carregar o combo com os tamanhos. Informar TI."));
            return null;
        }

    }

//#####################################################################################################################################
    public List<SelectItem> getComboBorda() {
        FacesContext context = FacesContext.getCurrentInstance();
        BordaDAO tdao = new BordaDAOImp();
        try {
            List<Borda> bordas = tdao.getTodos();
            List<SelectItem> listaCombo = new ArrayList<SelectItem>();
            for (Borda bor : bordas) {
                listaCombo.add(new SelectItem(bor.getId(), bor.getNome()));
            }
            return listaCombo;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Ocorreu um erro ao tentar carregar o combo com as bordas. Informar TI."));
            return null;
        }
    }

//#####################################################################################################################################
    public void getTodosSabores() {
        FacesContext context = FacesContext.getCurrentInstance();
        SaborDAO sdao = new SaborDAOImp();
        try {
            List<Sabor> saboresl = sdao.getTodos();
            modelSaborTodos = new ListDataModel(saboresl);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Ocorreu um erro ao tentar buscar todos os sabores. Informar TI."));
        }

    }

//#####################################################################################################################################
    public void getTodasBebidas() {
        FacesContext context = FacesContext.getCurrentInstance();
        BebidaDAO bdao = new BebidaDAOImp();
        try {
            List<Bebida> bebidasl = bdao.pesquisaPorEstoque();
            modelBebidaTodas = new ListDataModel(bebidasl);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Ocorreu um erro ao tentar buscar todas as bebidas. Informar TI."));
        }

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
        pedido.setPrecoFinal(pedido.getPrecoFinal() + bebida.getPrecoVenda());
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
        pedido.setPrecoFinal(pedido.getPrecoFinal() - bebidas.get(x).getPrecoVenda());
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
                x = pizzas.indexOf(pi);
            }
        }

        float valor = 0;
        Long taman = pizza.getTamanho().getId();
        if (taman == 1L) {
            if (pizza.getSabor1() != null && pizza.getSabor1().getValorG() > valor) {
                valor = pizza.getSabor1().getValorG();
            }
            if (pizza.getSabor2() != null && pizza.getSabor2().getValorG() > valor) {
                valor = pizza.getSabor2().getValorG();
            }
            if (pizza.getSabor3() != null && pizza.getSabor3().getValorG() > valor) {
                valor = pizza.getSabor3().getValorG();
            }
        } else if (taman == 2L) {
            if (pizza.getSabor1() != null && pizza.getSabor1().getValorM() > valor) {
                valor = pizza.getSabor1().getValorM();
            }
            if (pizza.getSabor2() != null && pizza.getSabor2().getValorM() > valor) {
                valor = pizza.getSabor2().getValorM();
            }
            if (pizza.getSabor3() != null && pizza.getSabor3().getValorM() > valor) {
                valor = pizza.getSabor3().getValorM();
            }
        } else {
            if (pizza.getSabor1() != null && pizza.getSabor1().getValorP() > valor) {
                valor = pizza.getSabor1().getValorP();
            }
            if (pizza.getSabor2() != null && pizza.getSabor2().getValorP() > valor) {
                valor = pizza.getSabor2().getValorP();
            }
            if (pizza.getSabor3() != null && pizza.getSabor3().getValorP() > valor) {
                valor = pizza.getSabor3().getValorP();
            }
        }
        pedido.setPrecoFinal(pedido.getPrecoFinal() - valor);

        pizzas.remove(x);
        return "";
    }

//#####################################################################################################################################
    public String encontraCliente() {

        FacesContext context = FacesContext.getCurrentInstance();
        cDAO = new ClienteDAOImp();
        eDAO = new EnderecoDAOImp();
        clienteNovo = false;
        if (!"".equals(telefone)) {
            try {
                cliente = cDAO.getByTelefone(telefone);
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Ocorreu um erro ao tentar encontrar o cliente. Informar TI."));
            }

            if (cliente == null) {
                context.addMessage(null, new FacesMessage("Sapore", "Cliente não cadastrado. Fazer cadastro"));
                clienteNovo = true;
                cliente = new Cliente();
                endereco = new Endereco();
                cliente.setTelefone(telefone);
                return "";
            }

            try {
                ends = eDAO.pesquisaByIdPessoa(cliente.getId());
                cliente.setEnderecos(ends);
                modelEnderecos = new ListDataModel(ends);
                setModelEnderecos(modelEnderecos);
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Ocorreu um erro ao tentar encontrar os endereços do cliente. Informar TI."));
            }

        } else {
            context.addMessage(null, new FacesMessage("Sapore", "Digite o telefone do cliente."));
            return "";
        }
        setCliente(cliente);
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

        if (sabores == null || sabores.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Sapore", "Escolha no mínimo um sabor"));
        } else {


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

            //pegando o valor maior pra pizza
            float valor = 0;
            Long taman = pizza.getTamanho().getId();
            if (taman == 1L) {
                if (pizza.getSabor1() != null && pizza.getSabor1().getValorG() > valor) {
                    valor = pizza.getSabor1().getValorG();
                }
                if (pizza.getSabor2() != null && pizza.getSabor2().getValorG() > valor) {
                    valor = pizza.getSabor2().getValorG();
                }
                if (pizza.getSabor3() != null && pizza.getSabor3().getValorG() > valor) {
                    valor = pizza.getSabor3().getValorG();
                }
            } else if (taman == 2L) {
                if (pizza.getSabor1() != null && pizza.getSabor1().getValorM() > valor) {
                    valor = pizza.getSabor1().getValorM();
                }
                if (pizza.getSabor2() != null && pizza.getSabor2().getValorM() > valor) {
                    valor = pizza.getSabor2().getValorM();
                }
                if (pizza.getSabor3() != null && pizza.getSabor3().getValorM() > valor) {
                    valor = pizza.getSabor3().getValorM();
                }
            } else {
                if (pizza.getSabor1() != null && pizza.getSabor1().getValorP() > valor) {
                    valor = pizza.getSabor1().getValorP();
                }
                if (pizza.getSabor2() != null && pizza.getSabor2().getValorP() > valor) {
                    valor = pizza.getSabor2().getValorP();
                }
                if (pizza.getSabor3() != null && pizza.getSabor3().getValorP() > valor) {
                    valor = pizza.getSabor3().getValorP();
                }
            }

            pedido.setPrecoFinal(pedido.getPrecoFinal() + valor);
            if (pizza.getBorda() != null && pizza.getBorda().getValor() > 0) {
                pedido.setPrecoFinal(pedido.getPrecoFinal() + pizza.getBorda().getValor());
            }
            pedido.setHora(new Timestamp(System.currentTimeMillis()));

            if (pizzas == null) {
                pizzas = new ArrayList();
            }
            pizzas.add(pizza);


            limparPizza();
            limparSabores();
            modelSabor = new ListDataModel();

            modelPizza = new ListDataModel(pizzas);
        }

    }

//#####################################################################################################################################
    public String novoPedido() {
        limpar();
        pedido = new Pedido();
        return "cadPedido";
    }

//#####################################################################################################################################
    public void pesquisaLikeCliente() {
        FacesContext context = FacesContext.getCurrentInstance();
        modelPedido = null;

        try {
            pDAO = new PedidoDAOImp();
            List<Pedido> pedidos = pDAO.pesquisaByCliente(cliente.getNome());
            modelPedido = new ListDataModel(pedidos);

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao pesquisar."));
        }
    }

//#####################################################################################################################################
    public void pesquisaPendentes() {
        FacesContext context = FacesContext.getCurrentInstance();
        modelPedido = null;

        try {
            pDAO = new PedidoDAOImp();
            beDAO = new BebidaDAOImp();
            piDAO = new PizzaDAOImp();
            tDAO = new TamanhoDAOImp();
            bDAO = new BordaDAOImp();
            beDAO = new BebidaDAOImp();
            //trago pedidos pendentes
            List<Pedido> pedidos = pDAO.pesquisaPendentes();
            //se houver pedidos, percorro a lista
            if (pedidos != null || !pedidos.isEmpty()) {
                for (int i = 0; i < pedidos.size(); i++) {
                    //se for delivery, busco pizzas e bebidas de cada um pq o hibernate nao deixa transitar listar complexas
                    if (pedidos.get(i).getMesa().equals("0")) {
                        pedidos.get(i).setPizzas(piDAO.listaByPedido(pedidos.get(i).getId()));
                        pedidos.get(i).setBebidas(beDAO.pesquisaPorPedido(pedidos.get(i).getId()));
                        pedidos.get(i).setMesa("");
                    } else {
                        //se for pedido local via android, busco tamanho e borda pq só vem o id. cada pedido só tem uma pizza
                        pedidos.get(i).getPizzas().get(0).setTamanho(tDAO.pesquisa_Por_Id(pedidos.get(i).getPizzas().get(0).getTamanho().getId()));
                        pedidos.get(i).getPizzas().get(0).setBorda(bDAO.pesquisa_Por_Id(pedidos.get(i).getPizzas().get(0).getBorda().getId()));
                        //seto bebidas, tenho que buscar pq só vem o id tbm
                        for (int j = 0; j < pedidos.get(i).getBebidas().size(); j++) {
                            pedidos.get(i).getBebidas().set(j, beDAO.pesquisa_Por_Id(pedidos.get(i).getBebidas().get(j).getId()));
                        }
                    }
                }
            }

            modelPedido = new ListDataModel(pedidos);

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao pesquisar."));
        }
    }

//#####################################################################################################################################
    public void listaPizzasByPedido(Long idPedido) {
        FacesContext context = FacesContext.getCurrentInstance();
        modelPedido = null;

        try {
            piDAO = new PizzaDAOImp();
            List<Pizza> pizzas1 = piDAO.listaByPedido(idPedido);
            modelPizzaPedido = new ListDataModel(pizzas1);

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao pesquisar."));
        }
    }

    //#####################################################################################################################################
    public String cancela() {
        FacesContext context = FacesContext.getCurrentInstance();
        pedido = (Pedido) modelPedido.getRowData();
        pedido.setStatus("cancelado");
        pDAO = new PedidoDAOImp();
        try {
            if (pedido.getMesa().equals("0")) {
                pDAO.alteraStatus(pedido.getStatus(), pedido.getId());
                context.addMessage(null, new FacesMessage("Sapore", "Pedido cancelado com sucesso!"));
            }else{
                pDAO.alteraStatusAndroid(pedido.getStatus(), pedido.getId());
                context.addMessage(null, new FacesMessage("Sapore", "Pedido cancelado com sucesso!"));
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao cancelar."));
            Logger.getLogger(PedidoControle.class.getName()).log(Level.SEVERE, null, e);
        }
        modelPedido = null;
        modelPedido = new ListDataModel();
        pesquisaPendentes();
        pesquisaPendentes();
        return "";
    }

    //#####################################################################################################################################
    public String entrega() {
        FacesContext context = FacesContext.getCurrentInstance();
        pedido = (Pedido) modelPedido.getRowData();
        pedido.setStatus("entregue");
        pDAO = new PedidoDAOImp();
        try {
            if (pedido.getMesa().equals("0")) {
                pDAO.alteraStatus(pedido.getStatus(), pedido.getId());
                context.addMessage(null, new FacesMessage("Sapore", "Pedido cancelado com sucesso!"));
            }else{
                pDAO.alteraStatusAndroid(pedido.getStatus(), pedido.getId());
                context.addMessage(null, new FacesMessage("Sapore", "Pedido cancelado com sucesso!"));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao entregar pedido."));
        }
        modelPedido = null;
        modelPedido = new ListDataModel();
        
        pesquisaPendentes();
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

    public float getTeste() {
        return teste;
    }

    public DataModel getModelPedido() {
        if (modelPedido == null) {
            pesquisaPendentes();
        }
        return modelPedido;
    }

    public void setModelPedido(DataModel modelPedido) {
        this.modelPedido = modelPedido;
    }

    public DataModel getModelPizzaPedido() {
        return modelPizzaPedido;
    }

    public void setModelPizzaPedido(DataModel modelPizzaPedido) {
        this.modelPizzaPedido = modelPizzaPedido;
    }
}
