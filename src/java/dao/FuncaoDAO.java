/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Funcao;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface FuncaoDAO extends Base_DAO<Funcao, Long>{
       
    List<Funcao> pesquisaLikeFuncao(String funcao);
    
}
