/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Cliente;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface ClienteDAO extends Base_DAO<Cliente, Long>{
    Cliente pesquisaCliente(Long id);
    
    List<Cliente> pesquisaLikeNome(String cliente);
    
    Cliente getByTelefone(String telefone);
    
}
