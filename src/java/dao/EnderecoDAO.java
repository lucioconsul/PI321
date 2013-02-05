/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Endereco;
import java.util.List;


/**
 *
 * @author Aluno
 */
public interface EnderecoDAO extends Base_DAO<Endereco, Long>{
    List<Endereco> pesquisaByRua(String rua);
    
    Endereco pesquisaByIdColab(Long idColab);
    
}
