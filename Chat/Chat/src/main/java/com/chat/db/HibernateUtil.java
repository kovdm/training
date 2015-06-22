package com.chat.db;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
    }

    /*public com.chat.packets.RegistrationPacket getClientById(int id) {
        Session session = null;
        com.chat.packets.RegistrationPacket com.chat.packets.RegistrationPacket = null;
        try {
            session = factory.openSession();
            //session.beginTransaction();
            com.chat.packets.RegistrationPacket = (com.chat.packets.RegistrationPacket) session.load(com.chat.packets.RegistrationPacket.class, id);
            Hibernate.initialize(com.chat.packets.RegistrationPacket);
            //session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            factory.close();
        }
        return com.chat.packets.RegistrationPacket;
    }

    public com.chat.packets.RegistrationPacket getClientByPhone(String phone) {
        Session session = null;
        com.chat.packets.RegistrationPacket com.chat.packets.RegistrationPacket = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            Query query = session
                    .createQuery("FROM test_table WHERE phone=:phone")
                    .setString("phone", phone);
            com.chat.packets.RegistrationPacket = (com.chat.packets.RegistrationPacket) query.uniqueResult();
            Hibernate.initialize(com.chat.packets.RegistrationPacket);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            factory.close();
        }
        return com.chat.packets.RegistrationPacket;
    }

    public com.chat.packets.RegistrationPacket deleteByPhone(String phone) {
        Session session = null;
        com.chat.packets.RegistrationPacket com.chat.packets.RegistrationPacket = getClientByPhone(phone);
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.delete(com.chat.packets.RegistrationPacket);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            factory.close();
        }
        return com.chat.packets.RegistrationPacket;
    }

    public com.chat.packets.RegistrationPacket updateByPhone(com.chat.packets.RegistrationPacket com.chat.packets.RegistrationPacket) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.update(com.chat.packets.RegistrationPacket);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            factory.close();
        }
        return com.chat.packets.RegistrationPacket;
    }*/
}
