package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.gerente;


@Repository
public class gerentedaoimpl implements igerentedao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<gerente> findAll() {

		return em.createQuery("from gerente").getResultList();
	}

	@Override
	@Transactional
	public void save(gerente gerente) {

		if (gerente.getId() != null && gerente.getId() > 0) {
			em.merge(gerente);
		} else {
			em.persist(gerente);
		}

	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findone(id));

	}

	@Override
	@Transactional(readOnly = true)
	public gerente findone(Long id) {

		return em.find(gerente.class, id);
	}

	

	
	
}
