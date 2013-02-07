/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class TamanhoDAOImp extends Base_DAO_Imp<Tamanho, Long> implements TamanhoDAO{

    @Override
    public Tamanho pesquisa_Por_Id(Long id) {
       session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Tamanho tamanho = (Tamanho) session.get(Tamanho.class, id);
        session.close();
        return tamanho;
    }

    @Override
    public List<Tamanho> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Tamanho t");
        List<Tamanho> tam = query.list();
        session.close();
        return tam;
    }

    @Override
    public List<Tamanho> pesquisaLikeTamanho(String tamanho) {
         session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Tamanho t WHERE t.nome like :valor");
        query.setString("valor", "%" + tamanho + "%");
        List<Tamanho >tams = query.list();
        session.close();
        return tams;
    }

}
