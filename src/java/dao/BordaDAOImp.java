/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Borda;
import entidade.Colaborador;
import entidade.Funcao;
import entidade.Tamanho;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class BordaDAOImp extends Base_DAO_Imp<Borda, Long> implements BordaDAO{

    @Override
    public Borda pesquisa_Por_Id(Long id) {
         session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Borda borda = (Borda) session.get(Borda.class, id);
        session.close();
        return borda;
    }

    @Override
    public List<Borda> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Borda b");
        List<Borda> bor = query.list();
        session.close();
        return bor;
    }

    @Override
    public List<Borda> pesquisaLikeBorda(String borda) {
      session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Borda b WHERE b.nome like :valor");
        query.setString("valor", "%" + borda + "%");
        List<Borda>bord = query.list();
        session.close();
        return bord;
    }

  

}
