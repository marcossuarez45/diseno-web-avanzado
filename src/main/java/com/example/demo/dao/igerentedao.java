package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.gerente;

public interface igerentedao {

	public List<gerente> findAll();

	public void save(gerente gerente);

	public void delete(Long id);

	public gerente findone(Long id);
	
}
