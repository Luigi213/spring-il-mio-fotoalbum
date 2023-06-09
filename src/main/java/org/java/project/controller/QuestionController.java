package org.java.project.controller;

import java.util.List;

import org.java.project.pojo.Question;
import org.java.project.serv.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping
	public String getIndex(Model model) {
		
		List<Question> questions = questionService.findAll();
		
		model.addAttribute("questions",questions);
		
		return "question-index";
	}
	
	@GetMapping("/{id}")
	public String showQuestion(
			Model model,
			@PathVariable("id") int id
			) {
		
		Question question = questionService.findById(id).get();
		
		model.addAttribute("question",question);
		
		return "question-show";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteQuestion(
			@PathVariable("id") int id
			) {
		
		Question photo = questionService.findById(id).get();
		
		questionService.delete(photo);
		
		return "redirect:/question";
	}
}
