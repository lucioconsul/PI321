/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Pizza;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface PizzaDAO extends Base_DAO<Pizza, Long>{

    List<Pizza> listaByPedido(Long idPedido);
    
}
