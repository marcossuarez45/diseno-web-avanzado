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

import com.example.demo.dao.iveterianariodao;
import com.example.demo.entity.veterinario;

@Controller
@SessionAttributes("veterinario")
public class veterinariocontroller {
	@Autowired
	private iveterianariodao veterinariodao;

	



	@RequestMapping(value = { "/listarveterinario"}, method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de mascotas");
		model.addAttribute("veterinario", veterinariodao.findAll());
		
		return "listarveterinario";
	}
	
	@RequestMapping(value = {"/listarExcelveterinario"}, method = RequestMethod.GET)
	public String listarExcel(Model model) {
		model.addAttribute("veterinario", veterinariodao.findAll());
		return "listarExcelveterinario";
	}

	@RequestMapping(value = {"/formveterinario"})
	public String crear(Map<String, Object> model) {

		veterinario veterinario = new veterinario();
		model.put("veterinario", veterinario);
		model.put("titulo", "Formulario de mascotas");
		return "formveterinario";
	}

	@RequestMapping(value = "/formveterinario/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		veterinario veterinario = null;

		if (id > 0) {
			veterinario = veterinariodao.findone(id);
		} else {
			return "redirect:/listarveterinario";
		}
		model.put("veterinario", veterinario);
		model.put("titulo", "Editar mascota");
		return "formveterinario";
	}

	@RequestMapping(value = "/formveterinario", method = RequestMethod.POST)
	public String guardar(@Valid veterinario veterinario, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de mascotas");
			return "formveterinario";
		}

		veterinariodao.save(veterinario);
		status.setComplete();
		return "redirect:listarveterinario";
	}

	@RequestMapping(value = "/eliminarveterinario/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			veterinariodao.delete(id);
		}
		return "redirect:/listarveterinario";
	}
	
	
}
