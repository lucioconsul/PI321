/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Colaborador;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface ColaboradorDAO extends Base_DAO<Colaborador, Long>{
    Colaborador pesquisaColaborador(String cpf);
    
    List<Colaborador> pesquisaLikeNome(String colaborador);
    
}
