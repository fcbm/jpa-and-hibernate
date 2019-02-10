package com.demo.hibenatebootstrap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.entity.Book;

public class TestHibernateSimpleBootstrap {

    private static final Logger LOG = LoggerFactory.getLogger(TestHibernateSimpleBootstrap.class);

    private EntityManagerFactory emf;

    @Before
    public void init() {
        LOG.info("Init");
        emf = Persistence.createEntityManagerFactory("book-persistence-unit");
    }

    @After
    public void shutdown() {
        LOG.info("Shutdown");
        emf.close();
    }

    /**
     * This test assumes that the book entity with ID 1 already exists on the database
     */
    @Test
    public void testEmFind() {
        LOG.info("testEmFind");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Book book = em.find(Book.class, 1L);
        LOG.info("{}", book);

        em.getTransaction().commit();
        em.close();
    }
}
