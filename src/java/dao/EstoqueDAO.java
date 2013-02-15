/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entidade.Bebida;
import entidade.EstoqueBebida;


/**
 *
 * @author Aluno
 */
public interface EstoqueDAO extends Base_DAO<EstoqueBebida, Long>{
    
    public EstoqueBebida pesquisaByBebida(Bebida bebida);
    
}
