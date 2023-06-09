package org.java.project.api.controller;

import java.util.List;

import org.java.project.pojo.Photo;
import org.java.project.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v2")
public class PhotoApiController {
	
	@Autowired
	private PhotoService photoService;
	
	@GetMapping("/photos")
	public ResponseEntity<List<Photo>> getPizzas(
			@RequestParam(required = false) String titolo
			) {
		
		List<Photo> photos = photoService.findAll();

		if(titolo == null) {
			return new ResponseEntity<>(photos, HttpStatus.OK);
		} else if(!titolo.isEmpty()) {			
			List<Photo> photosName = photoService.findByTitolo(titolo);
			return new ResponseEntity<>(photosName, HttpStatus.OK);
		}
	
		return new ResponseEntity<>(photos, HttpStatus.OK);
	}
}
