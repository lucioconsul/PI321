/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Pedido;
import java.util.List;


/**
 *
 * @author Aluno
 */
public interface PedidoDAO extends Base_DAO<Pedido, Long>{
    List<Pedido> pesquisaByCliente(String telefone);
    
    List<Pedido> pesquisaPendentes();
    
}
