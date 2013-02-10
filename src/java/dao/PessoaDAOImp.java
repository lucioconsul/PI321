/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Colaborador;
import entidade.Pessoa;
import entidade.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lucio
 */
public class PessoaDAOImp extends Base_DAO_Imp<Pessoa, Long> implements PessoaDAO{

    @Override
    public Pessoa pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Pessoa pessoa = (Pessoa) session.get(Pessoa.class, id);
        session.close();
        return pessoa;
    }

    @Override
    public List<Pessoa> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Pessoa p");
        List<Pessoa> pessoas = query.list();
        session.close();
        return pessoas;
    }

    @Override
    public Pessoa pesquisaPessoa(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Pessoa p WHERE p.cpf = :valor");
        query.setLong("valor", id);        
        Pessoa resultado = (Pessoa) query.uniqueResult();
        session.close();
        return resultado;
    }

    @Override
    public List<Pessoa> pesquisaLikeNome(String pessoa) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Pessoa c WHERE c.nome like :valor");
        query.setString("valor", "%" + pessoa + "%");
        List<Pessoa> pessoas = query.list();
        session.close();
        return pessoas;
    }
    
}
