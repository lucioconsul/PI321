/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Colaborador;
import entidade.Funcao;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lucio
 */
public class FuncaoDAOImp extends Base_DAO_Imp<Funcao, Long> implements FuncaoDAO{

    @Override
    public Funcao pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Funcao func = (Funcao) session.get(Funcao.class, id);
        session.close();
        return func;
    }

    @Override
    public List<Funcao> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Funcao f");
        List<Funcao> funcs = query.list();
        session.close();
        return funcs;
    }
    

    @Override
    public List<Funcao> pesquisaLikeFuncao(String func) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Funcao f WHERE f.nome like :valor");
        query.setString("valor", "%" + func + "%");
        List<Funcao> funcs = query.list();
        session.close();
        return funcs;
    }
    
}
