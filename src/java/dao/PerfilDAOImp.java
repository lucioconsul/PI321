/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Perfil;
import entidade.Pessoa;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lucio
 */
public class PerfilDAOImp extends Base_DAO_Imp<Perfil, Long> implements PerfilDAO{

    @Override
    public Perfil pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Perfil perfil = (Perfil) session.get(Perfil.class, id);
        session.close();
        return perfil;
    }

    @Override
    public List<Perfil> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Perfil p");
        List<Perfil> perfis = query.list();
        session.close();
        return perfis;
    }


    
}
