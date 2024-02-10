package com.quiz.quizapp.controller;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.QuestionForm;
import com.quiz.quizapp.model.Result;
import com.quiz.quizapp.service.QuestionService;

@Controller
public class QuestionController {
	
	boolean submitted=false;
	@Autowired
	Result result;
	
	@Autowired
    QuestionService questionService;
	
	QuestionForm allQuestions;
	
	QuestionForm allResponse;
	
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}
	
	@PostMapping("/quiz")
    public String quiz(@RequestParam String username, @RequestParam String quiztype, Model model) {
    	result.setUsername(username);
    	if(quiztype.equals("miscellaneous"))
    		allQuestions =  questionService.getAllQuestions();
    	else
        	allQuestions = questionService.getByCategory(quiztype);
        model.addAttribute("allQuestions",allQuestions);
        model.addAttribute("category",quiztype);
        submitted=false;
        return "quiz.html";
    }
    
    @PostMapping("/submit")
	public String submit(@ModelAttribute("allQuestions") QuestionForm allQuestions, Model model) {
    	model.addAttribute("allQuestions",allQuestions);
    	allResponse=allQuestions;
		if(!submitted) {
			result.setTotalCorrect(questionService.getResult(allQuestions));
			questionService.saveScore(result);
			submitted = true;
		}
		model.addAttribute("totalNoOfQ",allQuestions.getQuestions().size());
		return "result.html";
	}
    
    @GetMapping("/report")
	public String report(Model model) {
    	model.addAttribute("allQuestions",allQuestions);
    	model.addAttribute("allResponse",allResponse);
    	return "report.html";
    }
    
    @GetMapping("admin")
    public String admin(Model model) {
    	allQuestions =  questionService.getAllQuestions();
    	model.addAttribute("allQuestions", allQuestions);
    	return "admin";
    }
    
    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question) {
    	questionService.addQuestion(question);
    	return "success";
    }
    
    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable int id) {
    	return questionService.deleteQues(id);
    }
    
 
}
