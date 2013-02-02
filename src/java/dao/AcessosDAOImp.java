/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Acessos;
import entidade.Perfil;
import entidade.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class AcessosDAOImp extends Base_DAO_Imp<Acessos, Long> implements AcessosDAO{

    

    @Override
    public List<Usuario> pesquisaLikeNome(String usu) {
        throw new UnsupportedOperationException("Not supported yet.");
//        session = (Session) Fabrica_Sessao.abreConexao().openSession();
//        Query query = session.createQuery("FROM Usuario u WHERE u.nome like :valor");
//        query.setString("valor", "%" + usu + "%");
//        List<Usuario> usus = query.list();
//        session.close();
//        return usus;
    }

    @Override
    public Acessos pesquisa_Por_Id(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Acessos> getTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Acessos pesquisaAcesso(Perfil perfil, String pagina) {
        throw new UnsupportedOperationException("Not supported yet.");
//        session = (Session) Fabrica_Sessao.abreConexao().openSession();
//        Query query = session.createQuery("FROM Acessos a WHERE a.perfil.id = :valor AND a.pagina = :valor2");
//        query.setLong("valor", perfil.getId());
//        query.setString("valor2", pagina);
//        Acessos resultado = (Acessos) query.uniqueResult();
//        session.close();
//        return resultado;
    }

    
}
