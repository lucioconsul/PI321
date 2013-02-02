/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author tecnicom
 */
public interface Base_DAO <T, ID> {
     T salva(T entidade);

    T pesquisa_Por_Id(ID id);

    void remove(T entidade);

    void altera(T entidade);

    List<T> getTodos();
    
}
