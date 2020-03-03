package com.emilianosusmano.springboot.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emilianosusmano.springboot.dao.Dao;
import com.emilianosusmano.springboot.entities.EstadisticaMutante;
import com.emilianosusmano.springboot.entities.EstadisticaMutanteHibernate;
import com.emilianosusmano.springboot.hibernate.HibernateUtil;

@Repository
public class DaoImpl implements Dao {
	private final static Logger log = Logger.getLogger(DaoImpl.class);
	
	@Autowired
	SessionFactory sf;

	@Override
	public Long createAndStorePerson(EstadisticaMutante em) {
		EstadisticaMutanteHibernate theEvent = new EstadisticaMutanteHibernate();
		theEvent.setIsMutant(em.getIsMutant());
		theEvent.setDna(em.getDna());
		theEvent.setCantCadenas(em.getCantCadenas());
		sf.getCurrentSession().save(em);
		log.info("Insertado: " + theEvent + " Id: " + theEvent.getIdPersona());
		return theEvent.getIdPersona();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstadisticaMutanteHibernate> listPersons() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<EstadisticaMutanteHibernate> result = (List<EstadisticaMutanteHibernate>) session.createQuery("from EstadisticaMutanteHibernate").list();
		session.getTransaction().commit();
		for (EstadisticaMutanteHibernate evento : result) {
			log.info("Leido: " + evento);
		}
		return result;
	}

}
