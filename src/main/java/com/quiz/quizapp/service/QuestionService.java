package com.quiz.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizapp.DAO.QuestionDao;
import com.quiz.quizapp.DAO.ResultDao;
import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.QuestionForm;
import com.quiz.quizapp.model.Result;

@Service
public class QuestionService {
	@Autowired
    QuestionDao questionDao;
	@Autowired
	ResultDao resultDao;
	@Autowired
	QuestionForm questionForm;
	
	public QuestionForm getAllQuestions() {
    	questionForm.setQuestions(questionDao.findAll());
    	return questionForm;
    }

    public QuestionForm getByCategory(String category){
		questionForm.setQuestions(questionDao.findByCategory(category));
		return questionForm;
    }
    
    public int getResult(QuestionForm questions) {
		int correct = 0;
		
		for(Question q: questions.getQuestions()) {
			if(q.getRightAnswer() == q.getChose())
				correct++;
		}
		return correct;
	}
    
    public String saveScore(Result result) {
    	Result saveResult = new Result();
    	saveResult.setTotalCorrect(result.getTotalCorrect());
    	saveResult.setUsername(result.getUsername());
    	resultDao.save(saveResult);
    	return "success";
    }
    
    public String addQuestion(Question question) {
    	questionDao.save(question);
		return "Success";
    }
    
    public String deleteQues(int id) {
    	questionDao.deleteById(id);
    	return id+" question deleted.";
    }
}
