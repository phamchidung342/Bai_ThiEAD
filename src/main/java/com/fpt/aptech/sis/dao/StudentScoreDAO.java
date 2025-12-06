package com.fpt.aptech.sis.dao;

import com.fpt.aptech.sis.entity.StudentScore;
import com.fpt.aptech.sis.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class StudentScoreDAO {
    
    public List<StudentScore> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query query = em.createQuery("SELECT ss FROM StudentScore ss ORDER BY ss.studentScoreId");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<StudentScore> findAllWithDetails() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query query = em.createQuery(
                "SELECT ss FROM StudentScore ss " +
                "JOIN FETCH ss.student " +
                "JOIN FETCH ss.subject " +
                "ORDER BY ss.studentScoreId"
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public StudentScore findById(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(StudentScore.class, id);
        } finally {
            em.close();
        }
    }
    
    public boolean insert(StudentScore studentScore) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(studentScore);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
    
    public boolean update(StudentScore studentScore) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(studentScore);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
    
    public boolean delete(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            StudentScore studentScore = em.find(StudentScore.class, id);
            if (studentScore != null) {
                em.remove(studentScore);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}

