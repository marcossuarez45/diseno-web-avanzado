package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;




@Entity
@Table(name = "asistente")
public class asistente implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="no puede estar vacio")
	private String  propietario;
	
	@NotEmpty(message="no puede estar vacio")
	private String animal;
	
	@NotEmpty(message="no puede estar vacio")
	private String vacuna;
	
	@NotEmpty(message="no puede estar vacio")
	private String proveedor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getVacuna() {
		return vacuna;
	}

	public void setVacuna(String vacuna) {
		this.vacuna = vacuna;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
