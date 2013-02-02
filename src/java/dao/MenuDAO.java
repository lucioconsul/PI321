/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Menus;
import entidade.Perfil;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface MenuDAO extends Base_DAO<Menus, Long>{
    List<Menus> pesquisaAcesso(Perfil perfil);
    
    List<Menus> pesquisaLikeNome(String menus);
    
}
