/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Bebida;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface BebidaDAO extends Base_DAO<Bebida, Long>{
       
    List<Bebida> pesquisaLikeBebida(String bebida);
    
    List<Bebida> pesquisaPorEstoque();
    
    List<Bebida> pesquisaPorPedido(Long idPedido);
    
}
