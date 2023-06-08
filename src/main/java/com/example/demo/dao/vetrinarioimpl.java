package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.veterinario;


@Repository
public class vetrinarioimpl implements iveterianariodao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<veterinario> findAll() {

		return em.createQuery("from veterinario").getResultList();
	}

	@Override
	@Transactional
	public void save(veterinario veterinario) {

		if (veterinario.getId() != null && veterinario.getId() > 0) {
			em.merge(veterinario);
		} else {
			em.persist(veterinario);
		}

	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findone(id));

	}

	@Override
	@Transactional(readOnly = true)
	public veterinario findone(Long id) {

		return em.find(veterinario.class, id);
	}

	
	
}
