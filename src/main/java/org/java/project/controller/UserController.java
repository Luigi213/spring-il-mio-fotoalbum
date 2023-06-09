package org.java.project.controller;

import java.util.List;

import org.java.project.auth.pojo.User;
import org.java.project.auth.serv.UserService;
import org.java.project.pojo.Categoria;
import org.java.project.pojo.Photo;
import org.java.project.serv.CategoriaService;
import org.java.project.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/amministratori")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	@GetMapping("/photo")
    public String indexUserPhotos(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<Photo> userPhotos = userService.getFotosForCurrentUser(username);
        model.addAttribute("photos", userPhotos);
        return "user-index";
    }
	
	@GetMapping("/photo/{id}")
    public String showUserPhotos(Authentication authentication, Model model, @PathVariable("id") int id) {
        String username = authentication.getName();
        
        List<Photo> userPhotos = userService.getFotosForCurrentUser(username);
        
        for(Photo p : userPhotos) {
        	if(id == p.getId()) {        		
        		Photo photo = photoService.findById(id).get();
        		model.addAttribute("photo", photo);
        	}
        }
        return "user-show";
    }
	
	@GetMapping("/photo/create")
	public String createPhoto(Model model) {
		
		List<Categoria> categorias = categoriaService.findAll();
		
		model.addAttribute("categorias", categorias);
		model.addAttribute("photo", new Photo());
		
		return "user-create";
	}
	
	@PostMapping("/photo/create")
	public String storePhoto(
			Authentication authentication,
			Model model,
			@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult
			) {
		
		if (bindingResult.hasErrors()) {
			
			List<Categoria> categorias = categoriaService.findAll();
			
			model.addAttribute("categorias", categorias);
			
			model.addAttribute("photo", photo);
			model.addAttribute("errors", bindingResult);
			
			return "user-create";
		}
		
		String username = authentication.getName();
	    User user = userService.findByUsername(username).get();

	    user.addPhoto(photo);
	    photoService.save(photo);
		
		return "redirect:/amministratori/photo";
	}
	
	@GetMapping("/photo/delete/{id}")
	public String deletePizza(
			Authentication authentication,
			@PathVariable("id") int id
			) {
		String username = authentication.getName();
	    User user = userService.findByUsername(username).get();
              		
		Photo photo = photoService.findById(id).get();
		if (photo != null) {
	        user.removePhoto(photo);
	        photoService.delete(photo);
	    }

		return "redirect:/amministratori/photo";
	}
}
