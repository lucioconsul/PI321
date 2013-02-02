/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author tecnicom
 */
public class Fabrica_Sessao {
    private static SessionFactory sessionFactory;

    public static SessionFactory abreConexao() {
        if (sessionFactory == null) {
            Configuration cfg = new AnnotationConfiguration();
            cfg.configure("/dao/hibernate.cfg.xml");
            sessionFactory = cfg.buildSessionFactory();
        }
        return sessionFactory;
    }
}
