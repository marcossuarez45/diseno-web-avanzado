package com.example.demo.cotroller;

import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.dao.igerentedao;
import com.example.demo.entity.gerente;


@Controller
@SessionAttributes("gerente")
public class gerentecontroller {

	@Autowired
	private igerentedao gerentedao;

	



	@RequestMapping(value = { "/listargerente"}, method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de empleados");
		model.addAttribute("gerente", gerentedao.findAll());
		
		return "listargerente";
	}
	
	@RequestMapping(value = {"/listarExcelgerente"}, method = RequestMethod.GET)
	public String listarExcel(Model model) {
		model.addAttribute("gerente", gerentedao.findAll());
		return "listarExcelgerente";
	}

	@RequestMapping(value = {"/formgerente"})
	public String crear(Map<String, Object> model) {

		gerente gerente = new gerente();
		model.put("gerente", gerente);
		model.put("titulo", "Formulario de empleados");
		return "formgerente";
	}

	@RequestMapping(value = "/formgerente/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		gerente gerente = null;

		if (id > 0) {
			gerente = gerentedao.findone(id);
		} else {
			return "redirect:/listargerente";
		}
		model.put("gerente", gerente);
		model.put("titulo", "Editar empleado");
		return "formgerente";
	}

	@RequestMapping(value = "/formgerente", method = RequestMethod.POST)
	public String guardar(@Valid gerente gerente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de empleados");
			return "formgerente";
		}

		gerentedao.save(gerente);
		status.setComplete();
		return "redirect:listargerente";
	}

	@RequestMapping(value = "/eliminargerente/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			gerentedao.delete(id);
		}
		return "redirect:/listargerente";
	}
	
	
	
}
