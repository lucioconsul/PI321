/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Acessos;
import entidade.Perfil;
import entidade.Usuario;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface AcessosDAO extends Base_DAO<Acessos, Long>{
    Acessos pesquisaAcesso(Perfil perfil, String pagina);
    
    List<Usuario> pesquisaLikeNome(String usuario);
    
}
