/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Sabor;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface SaborDAO extends Base_DAO<Sabor, Long>{
       
    List<Sabor> pesquisaLikeSabor(String sabor);
    
}
