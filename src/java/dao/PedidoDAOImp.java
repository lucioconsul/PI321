/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entidade.Pedido;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author Lucio
 */
public class PedidoDAOImp extends Base_DAO_Imp<Pedido, Long> implements PedidoDAO{

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
        List<Pedido> ends = query.list();
        session.close();
        return ends;
    }

    @Override
    public List<Pedido> pesquisaByDia(String dia) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Pedido p WHERE p.data LIKE :valor");
        query.setString("valor", dia);
        List<Pedido> ends = query.list();
        session.close();
        return ends;
    }

    
}
