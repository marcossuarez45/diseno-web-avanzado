package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.asistente;


public interface iasistentedao {

	public List<asistente> findAll();

	public void save(asistente asistente);

	public void delete(Long id);

	public asistente findone(Long id);
	
}
