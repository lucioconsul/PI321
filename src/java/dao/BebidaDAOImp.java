/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;



import entidade.Bebida;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lucio
 */
public class BebidaDAOImp extends Base_DAO_Imp<Bebida, Long> implements BebidaDAO{

    @Override
    public Bebida pesquisa_Por_Id(Long id) {
            session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Bebida bebida = (Bebida) session.get(Bebida.class, id);
        session.close();
        return bebida;
    }

    @Override
    public List<Bebida> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Bebida b");
        List<Bebida> bebidas = query.list();
        session.close();
        return bebidas;
    }

    @Override
    public List<Bebida> pesquisaLikeBebida(String bebida) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Bebida b WHERE b.nome like :valor");
        query.setString("valor", "%" + bebida + "%");
        List<Bebida>bebidas = query.list();
        session.close();
        return bebidas;
    }

    @Override
    public List<Bebida> pesquisaPorEstoque() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("SELECT b FROM Bebida b, EstoqueBebida e WHERE e.bebida = b.id AND e.qtd > 0");
        List<Bebida> bebidas = query.list();
        session.close();
        return bebidas;
    }


  
}
