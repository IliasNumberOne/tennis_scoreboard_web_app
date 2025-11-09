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

    public List<Match> findByNameWithPagination(String name, int page, int pageSize) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from Match m where lower(m.player1.name) like :name or lower(m.player2.name) like :name";
            return session.createQuery(hql, Match.class)
                    .setParameter("name", "%" + name.toLowerCase() + "%")
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
        }
    }


    public List<Match> getAllWithPagination(int page, int pageSize) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Match", Match.class)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
        }
    }

    public int countAllMatches() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createQuery("select count(m) from Match m", Long.class).uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }

    public int countMatchesByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createQuery(
                            "select count(m) from Match m " +
                                    "where lower(m.player1.name) like :name " +
                                    "or lower(m.player2.name) like :name",
                            Long.class)
                    .setParameter("name", "%" + name.toLowerCase() + "%")
                    .uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }

}
