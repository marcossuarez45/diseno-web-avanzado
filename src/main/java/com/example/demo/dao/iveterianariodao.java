package com.example.demo.dao;

import java.util.List;


import com.example.demo.entity.veterinario;


public interface iveterianariodao {

	public List<veterinario> findAll();

	public void save(veterinario veterianrio);

	public void delete(Long id);

	public veterinario findone(Long id);
	
}
