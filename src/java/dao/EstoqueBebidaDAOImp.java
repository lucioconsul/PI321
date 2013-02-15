/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;



import entidade.Bebida;
import entidade.Cliente;
import entidade.EstoqueBebida;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lucio
 */
public class EstoqueBebidaDAOImp extends Base_DAO_Imp<EstoqueBebida, Long> implements EstoqueDAO{

    @Override
    public EstoqueBebida pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        EstoqueBebida estoque = (EstoqueBebida) session.get(Bebida.class, id);
        session.close();
        return estoque;
    }

    @Override
    public List<EstoqueBebida> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM EstoqueBebida b");
        List<EstoqueBebida> estoques = query.list();
        session.close();
        return estoques;
    }

    @Override
    public EstoqueBebida pesquisaByBebida(Bebida bebida) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM EstoqueBebida e WHERE e.bebida = :valor");
        query.setLong("valor", bebida.getId());        
        EstoqueBebida resultado = (EstoqueBebida) query.uniqueResult();
        session.close();
        return resultado;
    }
    


  
}
