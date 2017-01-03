package util;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DAO.Categoria;
import DAO.Utente;

public class DbConnector {
	private static SessionFactory factory;

	public DbConnector() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Categoria> getCategorie() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Categoria> l = null;
		try {
			tx = session.beginTransaction();
			l = session.createQuery("FROM Categoria where sottocategoria=null").getResultList();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return l;
	}

	public Utente getUtente(String mail, String password) {
		Session session = factory.openSession();
		Transaction tx = null;
		Utente u = null;
		try {
			tx = session.beginTransaction();
			u = (Utente) session.createQuery("FROM Utente where Email=:email and Password=:passw")
					.setParameter("email", mail).setParameter("passw", password).getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
		return u;

	}

	public void registraNuovoAccount(String nome, String cognome, String indirizzo, String email, String password) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Utente utente = new Utente(email, nome, cognome, indirizzo, password, 0, 0);
			session.save(utente);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		DbConnector d = new DbConnector();
		for (Categoria c : d.getCategorie())
			System.out.println(c.getNome());
	}

}
