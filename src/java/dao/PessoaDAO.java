/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Pessoa;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface PessoaDAO extends Base_DAO<Pessoa, Long>{
    Pessoa pesquisaPessoa(Long id);
    
    List<Pessoa> pesquisaLikeNome(String pessoa);
    
    Pessoa getByTelefone(String telefone);
    
}
