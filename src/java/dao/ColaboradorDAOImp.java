/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Colaborador;
import entidade.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class ColaboradorDAOImp extends Base_DAO_Imp<Colaborador, Long> implements ColaboradorDAO{

    @Override
    public Colaborador pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Colaborador colab = (Colaborador) session.get(Colaborador.class, id);
        session.close();
        return colab;
    }

    @Override
    public List<Colaborador> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Colaborador c");
        List<Colaborador> colabs = query.list();
        session.close();
        return colabs;
    }

    @Override
    public Colaborador pesquisaColaborador(String cpf) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Colaborador c WHERE c.cpf = :valor");
        query.setString("valor", cpf);        
        Colaborador resultado = (Colaborador) query.uniqueResult();
        session.close();
        return resultado;
    }

    @Override
    public List<Colaborador> pesquisaLikeNome(String colab) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Colaborador c WHERE c.nome like :valor");
        query.setString("valor", "%" + colab + "%");
        List<Colaborador> colabs = query.list();
        session.close();
        return colabs;
    }
    
}
