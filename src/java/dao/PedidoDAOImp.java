/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controle.PedidoControle;
import entidade.Bebida;
import entidade.Borda;
import entidade.Pedido;
import entidade.Pizza;
import entidade.Sabor;
import entidade.Tamanho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import util.Fabrica_De_Conexao;

/**
 *
 * @author Lucio
 */
public class PedidoDAOImp extends Base_DAO_Imp<Pedido, Long> implements PedidoDAO {

    @Override
    public Pedido pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Pedido ped = (Pedido) session.get(Pedido.class, id);
        session.close();
        return ped;
    }

    @Override
    public List<Pedido> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Pedido p");
        List<Pedido> peds = query.list();
        session.close();
        return peds;
    }

    @Override
    public List<Pedido> pesquisaByCliente(String telefone) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("SELECT p FROM Pedido p, Cliente c WHERE p.cliente = c.id AND c.nome LIKE :valor");
        query.setString("valor", telefone);
        List<Pedido> peds = query.list();
        session.close();
        return peds;
    }

    @Override
    public List<Pedido> pesquisaPendentes() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("SELECT DISTINCT p FROM Pedido p  LEFT JOIN p.bebidas LEFT JOIN p.pizzas WHERE p.status = 'aguardando' ORDER BY p.id ");
        List<Pedido> peds = query.list();
        session.close();


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pedido pedido;
        try {
            String consulta = "SELECT * FROM pedido_mesa a WHERE a.status = 'aguardando' ORDER BY a.id ";
            conn = Fabrica_De_Conexao.abre_Conexao();
            ps = conn.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                pedido = new Pedido();
                pedido.setDelivery(false);
                pedido.setDia(new Date());
                pedido.setHora(new Timestamp(System.currentTimeMillis()));
                pedido.setId(rs.getLong("a.id"));
                pedido.setStatus(rs.getString("a.status"));

                pedido.setMesa(Integer.toString(rs.getInt("a.idMesa")));
                pedido.setStatus(rs.getString("a.status"));

                ArrayList<Bebida> bebidas = new ArrayList();
                Bebida bebida1 = new Bebida();
                bebida1.setId(Long.parseLong(Integer.toString(rs.getInt("a.idBebida1"))));
                Bebida bebida2 = new Bebida();
                bebida2.setId(Long.parseLong(Integer.toString(rs.getInt("a.idBebida2"))));
                bebidas.add(bebida1);
                bebidas.add(bebida2);
                pedido.setBebidas(bebidas);

                Borda borda = new Borda();
                borda.setId(Long.parseLong(Integer.toString(rs.getInt("a.idBorda"))));


                Tamanho tamanho = new Tamanho();
                tamanho.setId(Long.parseLong(Integer.toString(rs.getInt("a.idTamanho"))));

                Pizza pizza = new Pizza();
                pizza.setBorda(borda);
                pizza.setTamanho(tamanho);

                Sabor sabor1 = new Sabor();
                sabor1.setNome(rs.getString("a.sabor1"));
                Sabor sabor2 = new Sabor();
                sabor1.setNome(rs.getString("a.sabor2"));
                Sabor sabor3 = new Sabor();
                sabor1.setNome(rs.getString("a.sabor3"));
                pizza.setSabor1(sabor1);
                pizza.setSabor2(sabor2);
                pizza.setSabor3(sabor3);
                pizza.setExcecoes(rs.getString("a.obs"));
                ArrayList<Pizza> pizzas = new ArrayList();
                pizzas.add(pizza);

                pedido.setPizzas(pizzas);

                peds.add(pedido);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sapore", "Erro ao tentar salvar o Pedido!"));
        } finally {
            try {
                Fabrica_De_Conexao.fechaConexao(conn, ps, rs);
            } catch (Exception ex) {
                Logger.getLogger(PedidoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        return peds;
    }

    @Override
    public void alteraStatus(String status, Long id) {
        Connection conn = null;
        PreparedStatement ps = null;// 

        try {
            String SQL = "UPDATE pedido SET status=? WHERE id = ?";
            conn = Fabrica_De_Conexao.abre_Conexao();//abre a conecção
            ps = conn.prepareStatement(SQL);//vai pegar a conecção e preparar ela para mandar pro sgbd
            ps.setString(1, status);//
            ps.setLong(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(PedidoDAOImp.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                Fabrica_De_Conexao.fechaConexao(conn, ps);
            } catch (Exception ex) {
                Logger.getLogger(PedidoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alteraStatusAndroid(String status, Long id) {
        Connection conn = null;
        PreparedStatement ps = null;// 

        try {
            String SQL = "UPDATE pedido_mesa SET status=? WHERE id = ?";
            conn = Fabrica_De_Conexao.abre_Conexao();//abre a conecção
            ps = conn.prepareStatement(SQL);//vai pegar a conecção e preparar ela para mandar pro sgbd
            ps.setString(1, status);//
            ps.setLong(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(PedidoDAOImp.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                Fabrica_De_Conexao.fechaConexao(conn, ps);
            } catch (Exception ex) {
                Logger.getLogger(PedidoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
