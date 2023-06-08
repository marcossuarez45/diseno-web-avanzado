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

import com.example.demo.dao.iasistentedao;
import com.example.demo.entity.asistente;




@Controller
@SessionAttributes("asistente")
public class asistentecontroller {

	@Autowired
	private iasistentedao asistentedao;

	



	@RequestMapping(value = { "/listar"}, method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de mascotas");
		model.addAttribute("asistente", asistentedao.findAll());
		
		return "listar";
	}
	
	@RequestMapping(value = {"/listarExcelasistente"}, method = RequestMethod.GET)
	public String listarExcel(Model model) {
		model.addAttribute("asistente", asistentedao.findAll());
		return "listarExcelasistente";
	}

	@RequestMapping(value = {"/form"})
	public String crear(Map<String, Object> model) {

		asistente asistente = new asistente();
		model.put("asistente", asistente);
		model.put("titulo", "Formulario de mascotas");
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		asistente asistente = null;

		if (id > 0) {
			asistente = asistentedao.findone(id);
		} else {
			return "redirect:/listar";
		}
		model.put("asistente", asistente);
		model.put("titulo", "Editar mascota");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid asistente asistente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de mascotas");
			return "form";
		}

		asistentedao.save(asistente);
		status.setComplete();
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			asistentedao.delete(id);
		}
		return "redirect:/listar";
	}
	
	
	
}
