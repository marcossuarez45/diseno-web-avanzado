package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.asistente;


@Repository
public class asistentedaoimpl implements iasistentedao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<asistente> findAll() {

		return em.createQuery("from asistente").getResultList();
	}

	@Override
	@Transactional
	public void save(asistente asistente) {

		if (asistente.getId() != null && asistente.getId() > 0) {
			em.merge(asistente);
		} else {
			em.persist(asistente);
		}

	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findone(id));

	}

	@Override
	@Transactional(readOnly = true)
	public asistente findone(Long id) {

		return em.find(asistente.class, id);
	}
	
}
