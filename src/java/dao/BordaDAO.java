/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Borda;
import entidade.Funcao;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface BordaDAO extends Base_DAO<Borda, Long>{
       
    List<Borda> pesquisaLikeBorda(String borda);
    
}
