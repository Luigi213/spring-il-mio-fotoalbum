package org.java.project.api.controller;

import org.java.project.api.controller.dto.QuestionDto;
import org.java.project.pojo.Question;
import org.java.project.serv.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class QuestionApiController {
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/question")
	public ResponseEntity<QuestionDto> storePizza(
			@RequestBody Question question
			){

		question = questionService.save(question);
		
		return new ResponseEntity<>(
				new QuestionDto(question), 
				HttpStatus.OK);	
	}
}
