package com.iliasdev.dao;

import com.iliasdev.entity.Player;
import com.iliasdev.util.HibernateUtil;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class PlayerDao implements Dao<Player>{
    @Getter
    private static final PlayerDao INSTANCE = new PlayerDao();

    @Override
    public Player save(Player player) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                session.persist(player);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        return player;
    }


    public Optional<Player> findByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Player WHERE name=:name";
            return session.createQuery(hql, Player.class)
                    .setParameter("name", name)
                    .setMaxResults(1)
                    .uniqueResultOptional();

        }
    }
}
