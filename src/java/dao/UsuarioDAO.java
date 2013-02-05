/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Usuario;

/**
 *
 * @author Aluno
 */
public interface UsuarioDAO extends Base_DAO<Usuario, Long>{
    Usuario pesquisaUsuario(String login, String senha);
    
    Usuario pesquisaByIdColab(Long idColab);
    
}
