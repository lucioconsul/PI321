/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Cliente;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lucio
 */
public class ClienteDAOImp extends Base_DAO_Imp<Cliente, Long> implements ClienteDAO{

    @Override
    public Cliente pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Cliente cliente = (Cliente) session.get(Cliente.class, id);
        session.close();
        return cliente;
    }

    @Override
    public List<Cliente> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Cliente c");
        List<Cliente> clientes = query.list();
        session.close();
        return clientes;
    }

    @Override
    public Cliente pesquisaCliente(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Cliente c WHERE c.cpf = :valor");
        query.setLong("valor", id);        
        Cliente resultado = (Cliente) query.uniqueResult();
        session.close();
        return resultado;
    }

    @Override
    public List<Cliente> pesquisaLikeNome(String cliente) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Cliente c WHERE c.nome like :valor");
        query.setString("valor", "%" + cliente + "%");
        List<Cliente> clientes = query.list();
        session.close();
        return clientes;
    }
    
}
