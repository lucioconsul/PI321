/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Tamanho;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface TamanhoDAO extends Base_DAO<Tamanho, Long>{
       
    List<Tamanho> pesquisaLikeTamanho(String tamanho);
    
}
