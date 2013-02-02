/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *Classes abstratas so devem ser usadas quando houver herança, tambem nao sendo obrigatorio implementação
 * @author tecnicom
 */
public abstract class Base_DAO_Imp<T, ID> implements Base_DAO<T, ID> {

    protected Session session;
    protected Transaction transaction;

    @Override
    public T salva(T entidade) {
        abreSessao();
        session.save(entidade);
        fechaSessao();
        return entidade;
    }

    @Override
    public void altera(T entidade) {
        abreSessao();
        session.update(entidade);
        fechaSessao();
    }

    @Override
    public void remove(T entidade) {
        abreSessao();
        session.delete(entidade);
        fechaSessao();
    }

    protected void abreSessao() {
        SessionFactory sf = Fabrica_Sessao.abreConexao();
        session = sf.openSession();
        transaction = session.beginTransaction();
    }

    protected void fechaSessao() {
        transaction.commit();
        session.close();
    }
}
