/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Classes abstratas so devem ser usadas quando houver herança, tambem nao sendo
 * obrigatorio implementação
 *
 * @author tecnicom
 */
public abstract class Base_DAO_Imp<T, ID> implements Base_DAO<T, ID> {

    protected Session session;
    protected Transaction transaction;

    @Override
    public T salva(T entidade) {
        try {
            abreSessao();
            session.save(entidade);
        } catch (Exception e) {
        } finally {
            fechaSessao();
        }
        return entidade;

    }

    @Override
    public void altera(T entidade) {
        try {
            abreSessao();
            session.update(entidade);
        } catch (Exception e) {
        } finally {
            fechaSessao();
        }


    }

    @Override
    public void remove(T entidade) {
        try {
            abreSessao();
            session.delete(entidade);
        } catch (Exception e) {
        } finally {
            fechaSessao();
        }

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
