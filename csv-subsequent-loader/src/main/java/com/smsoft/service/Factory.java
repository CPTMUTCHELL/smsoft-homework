package com.smsoft.service;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class Factory {

    public static SessionFactory getSessionFactory() {
        Configuration config = new Configuration();
        config.configure();
        return config.buildSessionFactory();
    }
}

