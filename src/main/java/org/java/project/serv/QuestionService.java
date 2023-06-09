package org.java.project.serv;

import java.util.List;
import java.util.Optional;

import org.java.project.pojo.Question;
import org.java.project.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
	@Autowired
	QuestionRepo questionRepo;
	
	public List<Question> findAll() {
		
		return questionRepo.findAll();
	}
	public Question save(Question question) {
		
		return questionRepo.save(question);
	}
	public Optional<Question> findById(int id) {
		
		return questionRepo.findById(id);
	}
	public void delete(Question question) {
		questionRepo.delete(question);
	}
}
