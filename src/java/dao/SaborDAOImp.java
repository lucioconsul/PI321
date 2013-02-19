/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Colaborador;
import entidade.Funcao;
import entidade.Pizza;
import entidade.Sabor;
import entidade.Tamanho;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lucio
 */
public class SaborDAOImp extends Base_DAO_Imp<Sabor, Long> implements SaborDAO{

    @Override
    public Sabor pesquisa_Por_Id(Long id) {
            session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Sabor sabor = (Sabor) session.get(Sabor.class, id);
        session.close();
        return sabor;
    }

    @Override
    public List<Sabor> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Sabor s");
        List<Sabor> sabores = query.list();
        session.close();
       
        return sabores;
    }

    @Override
    public List<Sabor> pesquisaLikeSabor(String sabor) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Sabor s WHERE s.nome like :valor");
        query.setString("valor", "%" + sabor + "%");
        List<Sabor>sabs = query.list();
        session.close();
        return sabs;
    }

  
}
