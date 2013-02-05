/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Cursos Livres
 */
public class UsuarioDAOImp extends Base_DAO_Imp<Usuario, Long> implements UsuarioDAO{

    @Override
    public Usuario pesquisa_Por_Id(Long id) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Usuario usu = (Usuario) session.get(Usuario.class, id);
        session.close();
        return usu;
    }

    @Override
    public List<Usuario> getTodos() {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Usuario u");
        List<Usuario> usus = query.list();
        session.close();
        return usus;
    }

    @Override
    public Usuario pesquisaUsuario(String login, String senha) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("FROM Usuario u WHERE u.login = :valor AND u.senha = :valor2");
        query.setString("valor", login);
        query.setString("valor2", senha);
        Usuario resultado = (Usuario) query.uniqueResult();
        session.close();
        return resultado;
    }

    @Override
    public Usuario pesquisaByIdColab(Long idColab) {
        session = (Session) Fabrica_Sessao.abreConexao().openSession();
        Query query = session.createQuery("SELECT u FROM Usuario u, Pessoa p WHERE u.colaborador = p.id AND p.id = :valor");
        
        query.setLong("valor" , idColab);
        Usuario resultado = (Usuario) query.uniqueResult();
        session.close();
        return resultado;
    }
    
}
