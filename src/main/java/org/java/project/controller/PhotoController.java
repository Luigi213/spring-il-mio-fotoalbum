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
@RequestMapping("/photo")
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public String getIndex(
			Model model
			) {
		
		List<Photo> photos = photoService.findAll();
		
		model.addAttribute("photos", photos);
		
		return "photo-index";
		
	}
	
	@GetMapping("/{id}")
	public String getPhoto(
			Model model,
			@PathVariable("id") int id
	) {
		Photo photo = photoService.findById(id).get();
		
		model.addAttribute("photo", photo);
		
		return "photo-show";
	}
	
	@GetMapping("/create")
	public String createPhoto(Model model) {
		
		List<Categoria> categorias = categoriaService.findAll();
		
		model.addAttribute("categorias", categorias);
		model.addAttribute("photo", new Photo());
		
		return "photo-create";
	}
	
	@PostMapping("/create")
	public String storePhoto(

			Model model,
			@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult
			) {
		
		if (bindingResult.hasErrors()) {
			
			List<Categoria> categorias = categoriaService.findAll();
			
			model.addAttribute("categorias", categorias);
			
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			
			return "photo-create";
		}
		
		photoService.save(photo);
		
		return "redirect:/photo";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePizza(
			@PathVariable("id") int id
			) {
		
		Photo photo = photoService.findById(id).get();
		
		photoService.delete(photo);
		
		return "redirect:/photo";
	}
}
