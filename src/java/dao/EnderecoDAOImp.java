/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entidade.Endereco;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author Lucio
 */
public class EnderecoDAOImp extends Base_DAO_Imp<Endereco, Long> implements EnderecoDAO{

    @Override
    public Endereco pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Endereco end = (Endereco) session.get(Endereco.class, id);
        session.close();
        return end;
    }

    @Override
    public List<Endereco> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Endereco e");
        List<Endereco> ends = query.list();
        session.close();
        return ends;
    }

    @Override
    public List<Endereco> pesquisaByRua(String rua) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Endereco e WHERE e.rua LIKE :valor");
        query.setString("valor", rua);
        List<Endereco> ends = query.list();
        session.close();
        return ends;
    }

    @Override
    public Endereco pesquisaByIdColab(Long idColab) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("SELECT e FROM Endereco e, Pessoa p WHERE e.pessoa = p.id AND p.id = :valor");
        query.setLong("valor" , idColab);
        Endereco resultado = (Endereco) query.uniqueResult();
        session.close();
        return resultado;
    }
    
    @Override
    public List<Endereco> pesquisaByIdPessoa(Long idPessoa){
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("SELECT e FROM Endereco e, Pessoa p WHERE e.pessoa = p.id AND p.id = :valor");
        query.setLong("valor" , idPessoa);
        List<Endereco> ends = query.list();
        session.close();
        return ends;
    }
    
}
