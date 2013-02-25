/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Bebida;
import entidade.Pedido;
import entidade.Pizza;
import entidade.Pessoa;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lucio
 */
public class PizzaDAOImp extends Base_DAO_Imp<Pizza, Long> implements PizzaDAO{

    @Override
    public Pizza pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Pizza pizza = (Pizza) session.get(Pizza.class, id);
        session.close();
        return pizza;
    }

    @Override
    public List<Pizza> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Pizza p");
        List<Pizza> pizzas = query.list();
        session.close();
        return pizzas;
    }

    @Override
    public List<Pizza> listaByPedido(Long idPedido) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Pizza pi WHERE pi.pedido = :valor ");
        query.setLong("valor", idPedido);
        List<Pizza> pizzas = query.list();
        session.close();
        return pizzas;
    }

    
}
