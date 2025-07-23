package com.iliasdev.dao;

import com.iliasdev.entity.Match;
import com.iliasdev.util.HibernateUtil;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDao implements Dao<Match> {
    @Getter
    private static final MatchDao INSTANCE = new MatchDao();

    @Override
    public Match save(Match match) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(match);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        return match;
    }


    public List<Match> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Match", Match.class).list();
        }
    }

}
