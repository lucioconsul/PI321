/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Usuario;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface UsuarioDAO extends Base_DAO<Usuario, Long>{
    Usuario pesquisaUsuario(String login, String senha);
    
    List<Usuario> pesquisaLikeNome(String usuario);
    
}
