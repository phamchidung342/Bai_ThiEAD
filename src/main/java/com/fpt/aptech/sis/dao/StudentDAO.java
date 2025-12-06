package com.fpt.aptech.sis.dao;

import com.fpt.aptech.sis.entity.Student;
import com.fpt.aptech.sis.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class StudentDAO {
    
    public List<Student> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query query = em.createQuery("SELECT s FROM Student s ORDER BY s.studentId");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Student findById(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }
    
    public boolean insert(Student student) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(student);
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
    
    public boolean update(Student student) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(student);
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
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
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

