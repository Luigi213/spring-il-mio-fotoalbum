package org.java.project.controller;

import java.util.List;

import org.java.project.auth.pojo.User;
import org.java.project.auth.serv.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/photo")
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private UserService userService;
	
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
	
	@PostMapping("/by/name")
	public String getNamePhoto(Model model, @RequestParam(required = false) String name) {
		
		List<Photo> photos = photoService.findByTitolo(name);
		
		model.addAttribute("photos", photos);
		
		return "photo-index";
	}
	
	@GetMapping("/update/{id}")
	public String editPhoto(
			Model model,
			@PathVariable("id") int id
		) {
		
		List<Categoria> categorias = categoriaService.findAll();
		
		List<User> users = userService.findAll();
		
		model.addAttribute("categorias", categorias);
		
		model.addAttribute("users", users);

		Photo photo = photoService.findById(id).get();
		
		model.addAttribute("photo", photo);
		
		return "photo-update";
	}
	
	@PostMapping("/update/{id}")
	public String updatePhoto(
			Model model,
			@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			
			return "photo-update";
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
