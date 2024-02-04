package com.quiz.quizapp.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Entity
@Table(name = "result")
public class Result {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private Integer totalCorrect = 0;
	public Result() {
		super();
	}
	public Result(Integer id, Integer totalCorrect, String username) {
		super();
		this.id = id;
		this.username = username;
		this.totalCorrect = totalCorrect;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getTotalCorrect() {
		return totalCorrect;
	}
	public void setTotalCorrect(Integer totalCorrect) {
		this.totalCorrect = totalCorrect;
	}
	
	
}
