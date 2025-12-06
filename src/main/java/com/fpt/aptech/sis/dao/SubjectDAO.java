package com.fpt.aptech.sis.dao;

import com.fpt.aptech.sis.entity.Subject;
import com.fpt.aptech.sis.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class SubjectDAO {
    
    public List<Subject> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query query = em.createQuery("SELECT s FROM Subject s ORDER BY s.subjectId");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Subject findById(Integer id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Subject.class, id);
        } finally {
            em.close();
        }
    }
}

