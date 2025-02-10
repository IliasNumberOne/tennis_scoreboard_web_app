package com.iliasdev.dao;

import com.iliasdev.entity.Player;
import com.iliasdev.util.HibernateUtil;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class PlayerDao implements Dao<Player>{
    @Getter
    private static final PlayerDao INSTANCE = new PlayerDao();

    @Override
    public Player save(Player player) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.buildSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return player;
    }


    public Optional<Player> findByName(String name) {
        try(Session session = HibernateUtil.buildSessionFactory().openSession()) {
            String hql = "FROM Player WHERE name=:name";
            return session.createQuery(hql, Player.class)
                    .setParameter("name", name)
                    .setMaxResults(1)
                    .uniqueResultOptional();

        }
    }
}
