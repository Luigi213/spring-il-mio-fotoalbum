package org.java.project.controller;

import java.util.List;

import org.java.project.pojo.Categoria;
import org.java.project.pojo.Photo;
import org.java.project.serv.CategoriaService;
import org.java.project.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public String getIndex(
			Model model
			) {
		
		List<Categoria> categorias = categoriaService.findAll();
		
		model.addAttribute("categorias", categorias);
		
		return "categoria-index";
		
	}
	
	@GetMapping("/{id}")
	public String getPhoto(
			Model model,
			@PathVariable("id") int id
	) {
		Categoria categoria = categoriaService.findById(id).get();
		
		model.addAttribute("categoria", categoria);
		
		return "categoria-show";
	}
	
	@GetMapping("/create")
	public String createPhoto(Model model) {
		
		List<Photo> photos = photoService.findAll();
		
		model.addAttribute("photos", photos);
		model.addAttribute("categoria", new Categoria());
		
		return "categoria-create";
	}
	
	@PostMapping("/create")
	public String storePhoto(

			Model model,
			@Valid @ModelAttribute Categoria categoria,
			BindingResult bindingResult
			) {
		
		if (bindingResult.hasErrors()) {
			
			List<Photo> photos = photoService.findAll();
			
			model.addAttribute("photos", photos);
			
			model.addAttribute("photo", categoria);
			model.addAttribute("errors", bindingResult);
			
			return "categoria-create";
		}
		
		categoriaService.save(categoria);
		
		return "redirect:/categoria";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePhoto(
			@PathVariable("id") int id
			) {
		
		Categoria categoria = categoriaService.findById(id).get();
		
		for(Photo p : categoria.getPhoto()) {
			p.removeCategoria(categoria);
			photoService.save(p);
		}
		
		categoriaService.delete(categoria);
		
		return "redirect:/categoria";
	}
}
